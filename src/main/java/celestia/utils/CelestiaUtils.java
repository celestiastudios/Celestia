package celestia.utils;

import celestia.Celestia;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class CelestiaUtils
{
    public static void registerCelestiaItem(String key, Item item)
    {
        registerCelestiaItem(key, new ItemStack(item));
    }

    public static void registerCelestiaItem(String key, Item item, int metadata)
    {
        registerCelestiaItem(key, new ItemStack(item, 1, metadata));
    }

    public static void registerCelestiaItem(String key, ItemStack stack)
    {
        Celestia.itemList.add(stack);
    }

    /**
     * Custom getEffectiveSide method, covering more cases than FMLCommonHandler
     */
    public static Side getEffectiveSide()
    {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER || Thread.currentThread().getName().startsWith("Netty Epoll Server IO"))
        {
            return Side.SERVER;
        }

        return Side.CLIENT;
    }
}
