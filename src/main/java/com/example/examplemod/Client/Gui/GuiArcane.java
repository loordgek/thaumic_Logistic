package com.example.examplemod.Client.Gui;

import com.example.examplemod.Inventroy.Container.ContainerArcane;
import com.example.examplemod.Tile.TileArcane;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.InventoryPlayer;

import java.util.List;

public class GuiArcane extends GuiMain {
    TileArcane te;
    public GuiArcane(InventoryPlayer playerInventory, TileArcane te){
        super(new ContainerArcane(playerInventory, te), "camoMine", te);
        this.te = te;
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
