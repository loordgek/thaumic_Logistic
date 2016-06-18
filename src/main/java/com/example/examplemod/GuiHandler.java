package com.example.examplemod;

import com.example.examplemod.Client.Gui.GuiArcane;
import com.example.examplemod.inventroy.Container.ContainerArcane;
import com.example.examplemod.tile.TileArcane;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    public enum GuiIds{
        ArcaneId
    }
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x,y,z);
        switch(GuiIds.values()[ID]){
            case ArcaneId:
                return new ContainerArcane(player.inventory,(TileArcane)te);
        }
        throw new IllegalArgumentException("No gui with id " + ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x,y,z);
        switch(GuiIds.values()[ID]){
            case ArcaneId:
                return new GuiArcane(player.inventory,(TileArcane)te);
        }
        throw new IllegalArgumentException("No gui with id " + ID);
    }
}
