package com.example.examplemod.Client.Gui;

import com.example.examplemod.inventroy.Container.ContainerArcane;
import com.example.examplemod.tile.TileArcane;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiArcane extends GuiMain {
    public TileArcane te;
    public GuiArcane(InventoryPlayer playerInventory, TileArcane te){
        super(new ContainerArcane(playerInventory, te), "Guiarcane", te);
        this.te = te;
    //    this.xSize = 200;
        this.ySize = 220;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
