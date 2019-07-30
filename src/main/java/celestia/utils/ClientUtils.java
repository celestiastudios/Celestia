package celestia.utils;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientUtils
{
    public static void addVariant(String modID, String name, String... variants)
    {
        Item itemBlockVariants = Item.REGISTRY.getObject(new ResourceLocation(modID, name));
        ResourceLocation[] variants0 = new ResourceLocation[variants.length];

        for (int i = 0; i < variants.length; ++i)
        {
            variants0[i] = new ResourceLocation(modID + ":" + variants[i]);
        }
    }

    public static void registerItemJson(String texturePrefix, Item item)
    {
        registerItemJson(texturePrefix, item, 0, item.getTranslationKey().substring(5));
    }

    public static void registerItemJson(String texturePrefix, Item item, int meta, String name)
    {
//        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(texturePrefix + name, "inventory"));
        FMLClientHandler.instance().getClient().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(texturePrefix + name, "inventory"));
    }
}
