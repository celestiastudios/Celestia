package celestia.items;

import celestia.Celestia;
import celestia.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockCheese extends ItemBlockDesc
{
    public ItemBlockCheese(Block block)
    {
        super(block);

        this.setMaxStackSize(1);
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return Celestia.celestiaBlocks;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxy.celestiaItem;
    }
}
