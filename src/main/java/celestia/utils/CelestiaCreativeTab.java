package celestia.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Comparator;

public class CelestiaCreativeTab extends CreativeTabs
{
    private ItemStack itemForTab;
    private Comparator<ItemStack> tabSorter;

    public CelestiaCreativeTab(int index, String label, ItemStack itemForTab, Comparator<ItemStack> tabSorter)
    {
        super(index, label);

        this.itemForTab = itemForTab;
        this.tabSorter = tabSorter;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack createIcon()
    {
        return this.itemForTab;
    }

    public void setItemForTab(ItemStack itemForTab)
    {
        this.itemForTab = itemForTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslationKey()
    {
        return "item_group." + this.getTabLabel();
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> list)
    {
        super.displayAllRelevantItems(list);

        if (tabSorter != null)
        {
            try
            {
                list.sort(tabSorter);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void setTabSorter(Comparator<ItemStack> tabSorter)
    {
        this.tabSorter = tabSorter;
    }
}
