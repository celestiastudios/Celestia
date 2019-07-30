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

package celestia.proxy;

import celestia.init.CelestiaItems;
import celestia.utils.ClientUtils;
import celestia.utils.Constants;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

public class ClientProxy extends CommonProxy
{
    public static EnumRarity celestiaItem = EnumHelper.addRarity("CelestiaRarity", TextFormatting.AQUA, "Celestial");
    private static List<Item> itemsToRegisterJson = Lists.newArrayList();

    @Override
    public void onPreInit(FMLPreInitializationEvent event)
    {
        super.onPreInit(event);
    }

    @Override
    public void registerVariants()
    {
        ClientProxy.addVariants();
    }

    @Override
    public void onInit(FMLInitializationEvent event)
    {
        ClientProxy.registerInventoryJsons();

        super.onInit(event);
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event)
    {
        super.onPostInit(event);
    }

    @Override
    public void postRegisterItem(Item item)
    {
        if (!item.getHasSubtypes())
        {
            ClientProxy.itemsToRegisterJson.add(item);
        }
    }

    private static void registerInventoryJsons()
    {
        for (Item toReg : ClientProxy.itemsToRegisterJson)
        {
            ClientUtils.registerItemJson(Constants.TEXTURE_PREFIX, toReg);
        }

        ClientUtils.registerItemJson(Constants.TEXTURE_PREFIX, CelestiaItems.cheeseWheel, 0, "cheese_wheel");
    }

    private static void addVariants()
    {
        addCoreVariant("food", "cheese_curd");
    }

    private static void addCoreVariant(String name, String... variants)
    {
        ClientUtils.addVariant(Constants.MOD_ID, name, variants);
    }
}
