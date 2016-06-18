package com.example.examplemod.inventroy.Container;

import com.example.examplemod.inventroy.Slot.SlotMatrix;
import com.example.examplemod.inventroy.Slot.SlotResult;
import com.example.examplemod.inventroy.Slot.Slotwand;
import com.example.examplemod.tile.TileArcane;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerArcane extends ContainerMain {
    TileArcane te;
    public ContainerArcane(InventoryPlayer playerInventory, TileArcane te) {
        addPlayerSlots(playerInventory, 8, 134);
        addSlotToContainer(new Slotwand(te.wandinv,0,119,20));
        addSlotToContainer(new SlotResult(te.result,0,101,38));
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                addSlotToContainer(new SlotMatrix(te.matrix, j + i * 3,8 + j * 18,20 + i * 18));
            }
        }
        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(te.maininv, j + i * 9,8 + j * 18, 85 + i * 18));
            }
        }
        this.te = te;

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            //From here change accordingly...
            if(slotIndex == 0) {

            } else if (slotIndex == 1){
                return null;

            } else if (slotIndex > 1 && slotIndex <= 19){
                return null;


            } else if (slotIndex > 19 && slotIndex <= 28)
                return null;

            //...till here.

            if(itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }

            if(itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}

