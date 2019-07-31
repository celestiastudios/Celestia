package celestia.items;

import celestia.utils.item.IShiftDescription;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockDesc extends CelestiaItemBlock
{
    public ItemBlockDesc(Block block)
    {
        super(block);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
    {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> info, ITooltipFlag flagIn)
    {
        if (this.getBlock() instanceof IShiftDescription && ((IShiftDescription) this.getBlock()).showDescription(stack.getItemDamage()))
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                info.addAll(FMLClientHandler.instance().getClient().fontRenderer.listFormattedStringToWidth(((IShiftDescription) this.getBlock()).getShiftDescription(stack.getItemDamage()), 150));
            }
        }
    }
}
