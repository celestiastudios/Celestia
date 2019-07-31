package celestia.init;

import celestia.Celestia;
import celestia.blocks.BlockCheese;
import celestia.items.ItemBlockCheese;
import celestia.utils.CelestiaUtils;
import celestia.utils.block.ISortableBlock;
import celestia.utils.block.SortCategoryBlock;
import celestia.utils.item.StackSorted;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Ordering;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CelestiaBlocks
{
    public static Block cheeseBlock;

    public static Map<SortCategoryBlock, List<StackSorted>> sortMapBlocks = Maps.newHashMap();

    public static void initBlocks()
    {
        CelestiaBlocks.cheeseBlock = new BlockCheese("cheese");

        CelestiaBlocks.registerBlocks();
        CelestiaBlocks.setHarvestLevels();
    }

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemClass, Object... itemCtorArgs)
    {
        String name = block.getTranslationKey().substring(5);

        if (block.getRegistryName() == null)
        {
            block.setRegistryName(name);
        }

        CelestiaUtils.registerCelestiaBlock(name, block);

        if (itemClass != null)
        {
            ItemBlock item = null;
            Class<?>[] ctorArgClasses = new Class<?>[itemCtorArgs.length + 1];

            ctorArgClasses[0] = Block.class;

            for (int idx = 1; idx < ctorArgClasses.length; idx++)
            {
                ctorArgClasses[idx] = itemCtorArgs[idx - 1].getClass();
            }

            try
            {
                Constructor<? extends ItemBlock> constructor = itemClass.getConstructor(ctorArgClasses);
                item = constructor.newInstance(ObjectArrays.concat(block, itemCtorArgs));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            if (item != null)
            {
                CelestiaUtils.registerCelestiaItem(name, item);

                if (item.getRegistryName() == null)
                {
                    item.setRegistryName(name);
                }
            }
        }
    }

    public static void registerBlocks(IForgeRegistry<Block> registry)
    {
        for (Block block : Celestia.blocksList)
        {
            registry.register(block);
        }
    }

    public static boolean registeringSorted = false;

    public static void registerSorted(Block block)
    {
        if (block instanceof ISortableBlock)
        {
            Item item = Item.getItemFromBlock(block);

            if (item == Items.AIR)
            {
                return;
            }

            ISortableBlock sortableBlock = (ISortableBlock) block;
            NonNullList<ItemStack> blocks = NonNullList.create();

            registeringSorted = true;

            block.getSubBlocks(null, blocks);

            registeringSorted = false;

            if (blocks.isEmpty())
            {
                blocks.add(new ItemStack(block));
            }

            for (ItemStack stack : blocks)
            {
                SortCategoryBlock categoryBlock = sortableBlock.getCategory(stack.getItemDamage());

                if (!sortMapBlocks.containsKey(categoryBlock))
                {
                    sortMapBlocks.put(categoryBlock, new ArrayList<>());
                }

                sortMapBlocks.get(categoryBlock).add(new StackSorted(stack.getItem(), stack.getItemDamage()));
            }
        }
        else if (block.getCreativeTab() == Celestia.celestiaBlocks)
        {
            throw new RuntimeException(block.getClass() + " must inherit " + ISortableBlock.class.getSimpleName() + "!");
        }
    }

    public static void finalizeSort()
    {
        List<StackSorted> itemOrderListBlocks = Lists.newArrayList();

        for (SortCategoryBlock type : SortCategoryBlock.values())
        {
            List<StackSorted> stackSorteds = sortMapBlocks.get(type);

            if (stackSorteds != null)
            {
                itemOrderListBlocks.addAll(stackSorteds);
            }
            else
            {
                System.out.println("ERROR: null sort stack: " + type.toString());
            }
        }

        Comparator<ItemStack> tabSorterBlocks = Ordering.explicit(itemOrderListBlocks).onResultOf(input -> new StackSorted(input.getItem(), input.getItemDamage()));
        Celestia.celestiaBlocks.setTabSorter(tabSorterBlocks);
    }

    private static void setHarvestLevel(Block block, String toolClass, int level, int meta)
    {
        block.setHarvestLevel(toolClass, level, block.getStateFromMeta(meta));
    }

    public static void setHarvestLevels()
    {

    }

    public static void registerBlocks()
    {
        registerBlock(CelestiaBlocks.cheeseBlock, ItemBlockCheese.class);
    }
}
