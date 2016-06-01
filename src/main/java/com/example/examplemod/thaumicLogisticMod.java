package com.example.examplemod;

import com.example.examplemod.Block.BlockArcane;
import com.example.examplemod.Tile.TileArcane;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

@Mod(modid = thaumicLogisticMod.MODID, version = thaumicLogisticMod.VERSION)
public class thaumicLogisticMod
{
    public static final String MODID = "thaumicLogistic";
    public static final String VERSION = "1.0";

    @Mod.Instance(thaumicLogisticMod.MODID)
    public static thaumicLogisticMod INSTANCE;

    public static final Block blockArcane = new BlockArcane();

    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
        GameRegistry.registerBlock(blockArcane, "name");
        GameRegistry.registerTileEntity(TileArcane.class, "name");
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());

    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    }
}
