package com.example.examplemod.Inventroy.Slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotMatrix extends Slot implements IPhantomSlot{
    public SlotMatrix(IInventory inventory, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(inventory, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    @Override
    public boolean canAdjust() {
        return true;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return false;
    }
}
