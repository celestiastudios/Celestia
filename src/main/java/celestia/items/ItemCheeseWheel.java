package celestia.items;

import celestia.Celestia;
import celestia.proxy.ClientProxy;
import celestia.utils.item.ISortableItem;
import celestia.utils.item.SortCategoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemCheeseWheel extends ItemFood implements ISortableItem
{
    public ItemCheeseWheel(int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);

        setTranslationKey("cheese_wheel");
        setRegistryName("cheese_wheel");
    }

    public ItemCheeseWheel(int amount, boolean isWolfFood)
    {
        this(amount, 0.6F, isWolfFood);
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab()
    {
        return Celestia.celestiaItems;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack item)
    {
        return ClientProxy.celestiaItem;
    }

    @Override
    public SortCategoryItem getCategory(int meta)
    {
        return SortCategoryItem.GENERAL;
    }
}
