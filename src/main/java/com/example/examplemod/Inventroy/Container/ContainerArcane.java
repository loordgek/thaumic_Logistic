package com.example.examplemod.Inventroy.Container;

import com.example.examplemod.Inventroy.Slot.SlotMatrix;
import com.example.examplemod.Inventroy.Slot.SlotResult;
import com.example.examplemod.Inventroy.Slot.Slotwand;
import com.example.examplemod.Tile.TileArcane;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerArcane extends ContainerMain {
    TileArcane te;
    public ContainerArcane(InventoryPlayer playerInventory, TileArcane te) {
        addPlayerSlots(playerInventory, 8, 84);
        addSlotToContainer(new Slotwand(te.wandinv,0,115,-12));
        addSlotToContainer(new SlotResult(te.result,0,123,6));
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                addSlotToContainer(new SlotMatrix(te.matrix, j + i * 3,20 + j * 18,-30 + i * 18));
            }
        }
        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(te.maininv, j + i * 9,5 + j * 18, 35 + i * 18));
            }
        }
        this.te = te;

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
