package com.example.examplemod.Inventroy.Container;

import com.example.examplemod.Inventroy.Slot.IPhantomSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerMain extends Container {



    protected void addPlayerSlots(InventoryPlayer playerInventory, int x, int y){
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, x + j * 18, y + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(playerInventory, i, x + i * 18, y + 58));
        }
    }
    @Override
    public ItemStack slotClick(int slotNum, int mouseButton, int modifier, EntityPlayer player) {

        Slot slot = slotNum < 0 ? null : (Slot) inventorySlots.get(slotNum);
        if (slot instanceof IPhantomSlot) { return slotClickPhantom(slot, mouseButton, modifier, player); }
        return super.slotClick(slotNum, mouseButton, modifier, player);
    }

    /**
     * This method is copied from the BuildCraft code, which can be found here: https://github.com/BuildCraft/BuildCraft
     * @author CovertJaguar <http://www.railcraft.info>
     */
    private ItemStack slotClickPhantom(Slot slot, int mouseButton, int modifier, EntityPlayer player) {

        ItemStack stack = null;

        if (mouseButton == 2) {
            if (((IPhantomSlot) slot).canAdjust()) {
                slot.putStack(null);
            }
        } else if (mouseButton == 0 || mouseButton == 1) {
            InventoryPlayer playerInv = player.inventory;
            slot.onSlotChanged();
            ItemStack stackSlot = slot.getStack();
            ItemStack stackHeld = playerInv.getItemStack();

            if (stackSlot != null) {
                stack = stackSlot.copy();
            }

            if (stackSlot == null) {
                if (stackHeld != null && slot.isItemValid(stackHeld)) {
                    fillPhantomSlot(slot, stackHeld, mouseButton, modifier);
                }
            } else if (stackHeld == null) {
                adjustPhantomSlot(slot, mouseButton, modifier);
                slot.onPickupFromSlot(player, playerInv.getItemStack());
            } else if (slot.isItemValid(stackHeld)) {
                if (canStacksMerge(stackSlot, stackHeld)) {
                    adjustPhantomSlot(slot, mouseButton, modifier);
                } else {
                    fillPhantomSlot(slot, stackHeld, mouseButton, modifier);
                }
            }
        }
        return stack;
    }

    /**
     * This method is copied from the BuildCraft code, which can be found here: https://github.com/BuildCraft/BuildCraft
     * @author CovertJaguar <http://www.railcraft.info>
     */
    public boolean canStacksMerge(ItemStack stack1, ItemStack stack2) {

        if (stack1 == null || stack2 == null) return false;
        if (!stack1.isItemEqual(stack2)) return false;
        if (!ItemStack.areItemStackTagsEqual(stack1, stack2)) return false;
        return true;

    }

    /**
     * This method is copied from the BuildCraft code, which can be found here: https://github.com/BuildCraft/BuildCraft
     * @author CovertJaguar <http://www.railcraft.info>
     */
    protected void adjustPhantomSlot(Slot slot, int mouseButton, int modifier) {

        if (!((IPhantomSlot) slot).canAdjust()) { return; }
        ItemStack stackSlot = slot.getStack();
        int stackSize;
        if (modifier == 1) {
            stackSize = mouseButton == 0 ? (stackSlot.stackSize + 1) / 2 : stackSlot.stackSize * 2;
        } else {
            stackSize = mouseButton == 0 ? stackSlot.stackSize - 1 : stackSlot.stackSize + 1;
        }

        if (stackSize > slot.getSlotStackLimit()) {
            stackSize = slot.getSlotStackLimit();
        }

        stackSlot.stackSize = stackSize;

        if (stackSlot.stackSize <= 0) {
            slot.putStack((ItemStack) null);
        }
    }

    /**
     * This method is copied from the BuildCraft code, which can be found here: https://github.com/BuildCraft/BuildCraft
     * @author CovertJaguar <http://www.railcraft.info>
     */
    protected void fillPhantomSlot(Slot slot, ItemStack stackHeld, int mouseButton, int modifier) {

        if (!((IPhantomSlot) slot).canAdjust()) { return; }
        int stackSize = mouseButton == 0 ? stackHeld.stackSize : 1;
        if (stackSize > slot.getSlotStackLimit()) {
            stackSize = slot.getSlotStackLimit();
        }
        ItemStack phantomStack = stackHeld.copy();
        phantomStack.stackSize = stackSize;

        slot.putStack(phantomStack);
    }
}
