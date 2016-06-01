package com.example.examplemod;

import com.example.examplemod.Client.Gui.GuiArcane;
import com.example.examplemod.Inventroy.Container.ContainerArcane;
import com.example.examplemod.Tile.TileArcane;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by stefan on 28-4-2016.
 */
public class GuiHandler implements IGuiHandler {
    public enum GuiIds{
        ArcaneId
    }
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(GuiIds.values()[ID]){
            case ArcaneId:
                return new ContainerArcane(player.inventory,(TileArcane)world.getTileEntity(x,y,z));
        }
        throw new IllegalArgumentException("No gui with id " + ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(GuiIds.values()[ID]){
            case ArcaneId:
                return new GuiArcane(player.inventory,(TileArcane)world.getTileEntity(x,y,z));
        }
        throw new IllegalArgumentException("No gui with id " + ID);
    }
}
