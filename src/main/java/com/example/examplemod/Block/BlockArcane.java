package com.example.examplemod.Block;

import com.example.examplemod.GuiHandler;
import com.example.examplemod.Tile.TileArcane;
import com.example.examplemod.thaumicLogisticMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by stefan on 28-4-2016.
 */
public class BlockArcane extends BlockMain {
    public BlockArcane(){
        this.setBlockName("lol");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileArcane();
    }

    @Override
    public boolean onBlockActivated(World world, int X, int Y, int Z, EntityPlayer player, int p_149727_6_, float hitX, float hitY, float hiyZ) {
        player.openGui(thaumicLogisticMod.INSTANCE, GuiHandler.GuiIds.ArcaneId.ordinal(),world,X,Y,Z);
        return true;
    }
}

