/*
 * Celestia, the next-generation space & tech mod for Minecraft Forge.
 *
 *     Copyright (C) 2019-Onwards Celestia Studios
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package celestia;

import celestia.init.CelestiaItems;
import celestia.proxy.ClientProxy;
import celestia.proxy.CommonProxy;
import celestia.utils.CelestiaCreativeTab;
import celestia.utils.CelestiaUtils;
import celestia.utils.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.LinkedList;

@Mod(
          modid = Constants.MOD_ID,
          name = Constants.MOD_NAME,
          version = Constants.VERSION_DISPLAY,
          acceptedMinecraftVersions = Constants.ACCEPTED_VERSION
)
public class Celestia
{
    @Instance(Constants.MOD_ID)
    public static Celestia instance;

    @SidedProxy(clientSide = Constants.CLIENT_PROXY_PATH, serverSide = Constants.COMMON_PROXY_PATH)
    public static CommonProxy proxy;

    public static CelestiaCreativeTab celestiaItems;

    public static LinkedList<ItemStack> itemList = new LinkedList<>();
    public static LinkedList<Item> itemListTrue = new LinkedList<>();

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        proxy.onPreInit(event);

        Celestia.celestiaItems = new CelestiaCreativeTab(CreativeTabs.getNextID(), "celestia_items", null, null);

        CelestiaItems.initItems();
    }

    @EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        Celestia.celestiaItems.setItemForTab(new ItemStack(CelestiaItems.cheeseWheel));

        proxy.onInit(event);
    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent event)
    {
        proxy.onPostInit(event);
    }

    @EventBusSubscriber(modid = Constants.MOD_ID)
    public static class RegistrationHandler
    {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event)
        {
            CelestiaItems.registerItems(event.getRegistry());

            //RegisterSorted for blocks cannot be run until all the items have been registered
            if (CelestiaUtils.getEffectiveSide() == Side.CLIENT)
            {
                for (Item item : Celestia.itemListTrue)
                {
                    CelestiaItems.registerSorted(item);
                }
            }
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event)
        {
            proxy.registerVariants();
        }
    }
}
