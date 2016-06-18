package com.example.examplemod;

import com.example.examplemod.block.BlockArcane;
import com.example.examplemod.block.BlockRelay;
import com.example.examplemod.tile.TileArcane;
import com.example.examplemod.tile.TileRelay;
import com.example.examplemod.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

@Mod(modid = Reference.MOD.MOD_ID, version = Reference.MOD.MOD_VERSION)
public class thaumicLogisticMod
{

    @Mod.Instance(Reference.MOD.MOD_ID)
    public static thaumicLogisticMod INSTANCE;

    public static final Block blockArcane = new BlockArcane();
    public static final Block blockRelay = new BlockRelay();

    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
        GameRegistry.registerBlock(blockArcane, "arcane");
        GameRegistry.registerBlock(blockRelay, "relay");
        GameRegistry.registerTileEntity(TileArcane.class, "name");
        GameRegistry.registerTileEntity(TileRelay.class, "lol");
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());

    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        recipe.addrecipe();
    }
}
