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

import celestia.proxy.CommonProxy;
import celestia.utils.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        proxy.onPreInit(event);
    }

    @EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        proxy.onInit(event);
    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent event)
    {
        proxy.onPostInit(event);
    }
}
