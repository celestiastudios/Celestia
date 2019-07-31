package celestia.init;

import celestia.Celestia;
import celestia.utils.item.ISortableItem;
import celestia.items.ItemCheeseWheel;
import celestia.utils.CelestiaUtils;
import celestia.utils.item.SortCategoryItem;
import celestia.utils.item.StackSorted;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CelestiaItems
{


    public static Item cheeseWheel;

    public static Map<SortCategoryItem, List<StackSorted>> sortMapItems = Maps.newHashMap();

    public static void initItems()
    {
        CelestiaItems.cheeseWheel = new ItemCheeseWheel(1, 0.1F, false);

        registerItems();
    }

    public static void registerSorted(Item item)
    {
        if (item instanceof ISortableItem)
        {
            ISortableItem sortableItem = (ISortableItem) item;
            NonNullList<ItemStack> items = NonNullList.create();

            item.getSubItems(Celestia.celestiaItems, items);

            for (ItemStack stack : items)
            {
                SortCategoryItem categoryItem = sortableItem.getCategory(stack.getItemDamage());

                if (!sortMapItems.containsKey(categoryItem))
                {
                    sortMapItems.put(categoryItem, new ArrayList<>());
                }

                sortMapItems.get(categoryItem).add(new StackSorted(stack.getItem(), stack.getItemDamage()));
            }
        }
        else if (item.getCreativeTab() == Celestia.celestiaItems)
        {
            throw new RuntimeException(item.getClass() + " must inherit " + ISortableItem.class.getSimpleName() + "!");
        }
    }

    public static void registerItems()
    {
        CelestiaItems.registerItem(CelestiaItems.cheeseWheel);
    }

    public static void registerItem(Item item)
    {
        String name = item.getTranslationKey().substring(5);

        if (item.getRegistryName() == null)
        {
            item.setRegistryName(name);
        }

        CelestiaUtils.registerCelestiaItem(name, item);
        Celestia.itemListTrue.add(item);
        Celestia.proxy.postRegisterItem(item);
    }

    public static void registerItems(IForgeRegistry<Item> registry)
    {
        for (ItemStack item : Celestia.itemList)
        {
            registry.register(item.getItem());
        }
    }

    public static void finalizeSort()
    {
        List<StackSorted> itemOrderListItems = Lists.newArrayList();

        for (SortCategoryItem type : SortCategoryItem.values())
        {
            List stackSorteds = sortMapItems.get(type);

            if (stackSorteds != null)
            {
                itemOrderListItems.addAll(stackSorteds);
            }
            else
            {
                System.out.println("ERROR: null sort stack: " + type.toString());
            }
        }

        Comparator<ItemStack> tabSorterItems = Ordering.explicit(itemOrderListItems).onResultOf(input -> new StackSorted(input.getItem(), input.getItemDamage()));

        Celestia.celestiaItems.setTabSorter(tabSorterItems);
    }
}
