package celestia.utils;

import celestia.Celestia;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
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

    public static void registerCelestiaBlock(String key, Block block)
    {
        Celestia.blocksList.add(block);
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

    public static String translate(String key)
    {
        String result = I18n.translateToLocal(key);

        int comment = result.indexOf('#');

        String ret = (comment > 0) ? result.substring(0, comment).trim() : result;

        for (int i = 0; i < key.length(); ++i)
        {
            Character c = key.charAt(i);

            if (Character.isUpperCase(c))
            {
                System.err.println(ret);
            }
        }

        return ret;
    }
}
