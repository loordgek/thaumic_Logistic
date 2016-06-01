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
        addSlotToContainer(new Slotwand(te,0,20,20));
        addSlotToContainer(new SlotResult(te,1,40,40));
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                addSlotToContainer(new SlotMatrix(te, j + i * 9, j * 18, i * 18));
            }
        }
        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(te, j + i * 9 , j * 18,+ i * 18));
            }
        }
        this.te = te;

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
