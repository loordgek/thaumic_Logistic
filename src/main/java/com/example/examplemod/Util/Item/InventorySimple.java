package com.example.examplemod.Util.Item;

import com.example.examplemod.Util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventorySimple implements IInventory{
    private ItemStack[] Stacks;
    private int StackSize;
    private int InvSize;
    private String name;
    private IInventoryOwner owner;



    public InventorySimple(int Stacksize, int invSize, String name, IInventoryOwner owner) {
        this.StackSize = Stacksize;
        this.InvSize = invSize;
        this.name = name;
        this.Stacks = new ItemStack[invSize];
        this.owner = owner;
    }

    public void readFromNBT(NBTTagCompound tag, String key) {
        Stacks = new ItemStack[getSizeInventory()];
        NBTTagList camoStackTag = tag.getTagList(key, 10);

        for(int i = 0; i < camoStackTag.tagCount(); i++) {
            NBTTagCompound t = camoStackTag.getCompoundTagAt(i);
            int index = t.getByte("index");
            if(index >= 0 && index < Stacks.length) {
                    Stacks[index] = ItemStack.loadItemStackFromNBT(t);
                markDirty();
            }
        }
    }

    public void writeToNBT(NBTTagCompound tag, String key) {
        NBTTagList camoStackTag = new NBTTagList();
        for(int i = 0; i < Stacks.length; i++) {
            ItemStack stack = Stacks[i];
            if(stack != null) {
                NBTTagCompound t = new NBTTagCompound();
                stack.writeToNBT(t);
                t.setByte("index", (byte)i);
                camoStackTag.appendTag(t);
            }
        }
        tag.setTag(key, camoStackTag);
        LogHelper.info(camoStackTag);
        markDirty();
    }

    @Override
    public int getSizeInventory() {
        return InvSize;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index >InvSize){
            LogHelper.error(index + name);
            return null;
        }
        return Stacks[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.Stacks[index] != null) {
            if (this.Stacks[index].stackSize <= count) {
                ItemStack stack = this.Stacks[index];
                this.Stacks[index] = null;

                this.markDirty();
                return stack;
            } else {
                ItemStack stack = this.Stacks[index].splitStack(count);

                if (this.Stacks[index].stackSize == 0) {
                    this.Stacks[index] = null;
                }

                this.markDirty();
                return stack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if(Stacks[slot] != null) {
            ItemStack itemstack = Stacks[slot];
            Stacks[slot] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot >= Stacks.length) {
            return;
        }
        Stacks[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        markDirty();

    }

    @Override
    public String getInventoryName() {
        return name;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return StackSize;
    }

    @Override
    public void markDirty() {
        owner.onInventoryChanged(this);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }
}
