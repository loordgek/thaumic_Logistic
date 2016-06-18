package com.example.examplemod.util.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class IinventoryUtil {

    public static boolean removeSets(IInventory inventory, int count, ItemStack[] set, EntityPlayer player, boolean stowContainer, boolean oreDictionary, boolean craftingTools, boolean doRemove) {
        ItemStack[] stock = getStacks(inventory);

        if (doRemove) {
            ItemStack[] removed = removeSets(inventory, count, set, player, stowContainer, oreDictionary, craftingTools);
            return removed != null && removed.length >= count;
        } else {
            return containsSets(set, stock, oreDictionary, craftingTools) >= count;
        }
    }

    public static ItemStack[] removeSets(IInventory inventory, int count, ItemStack[] set, EntityPlayer player, boolean stowContainer, boolean oreDictionary, boolean craftingTools) {
        ItemStack[] removed = new ItemStack[set.length];
        ItemStack[] stock = getStacks(inventory);

        if (containsSets(set, stock, oreDictionary, craftingTools) < count) {
            return null;
        }

        for (int i = 0; i < set.length; i++) {
            if (set[i] == null) {
                continue;
            }
            ItemStack stackToRemove = set[i].copy();
            stackToRemove.stackSize *= count;

            // try to remove the exact stack first
            ItemStack removedStack = removeStack(inventory, stackToRemove, player, stowContainer, false);
            if (removedStack == null) {
                // remove crafting equivalents next
                removedStack = removeStack(inventory, stackToRemove, player, stowContainer, oreDictionary);
            }

            removed[i] = removedStack;
        }
        return removed;
    }

    public static ItemStack[] getStacks(IInventory inventory) {
        ItemStack[] stacks = new ItemStack[inventory.getSizeInventory()];
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            stacks[i] = inventory.getStackInSlot(i);
        }
        return stacks;
    }

    private static ItemStack removeStack(IInventory inventory, ItemStack stackToRemove, EntityPlayer player, boolean stowContainer, boolean oreDictionary) {
        for (int j = 0; j < inventory.getSizeInventory(); j++) {
            ItemStack stackInSlot = inventory.getStackInSlot(j);
            if (stackInSlot == null) continue;
            int index = findstackininv(stackToRemove, inventory);
            if (index == -1) return null;

            ItemStack removed = inventory.decrStackSize(index, stackToRemove.stackSize);
            stackToRemove.stackSize -= removed.stackSize;

            if (stackToRemove.stackSize == 0) {
                return removed;
            }


        }
        return null;
    }

    public static int findstackininv(ItemStack stack, IInventory inventory){
        for (int j = 0; j < inventory.getSizeInventory(); j++){
            if (inventory.getStackInSlot(j) == null) continue;
            if (inventory.getStackInSlot(j).getItem()  == stack.getItem() && inventory.getStackInSlot(j).getItemDamage()  == stack.getItemDamage()){
                return j;
            }
        }
        return -1;
    }

    public static ItemStack[] condenseStacks(ItemStack[] stacks) {
    List<ItemStack> condensed = new ArrayList<ItemStack>();

    for (ItemStack stack : stacks) {
        if (stack == null) {
            continue;
        }
        if (stack.stackSize <= 0) {
            continue;
        }

        boolean matched = false;
        for (ItemStack cached : condensed) {
            if (cached.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(cached, stack)) {
                cached.stackSize += stack.stackSize;
                matched = true;
            }
        }

        if (!matched) {
            ItemStack cached = stack.copy();
            condensed.add(cached);
        }

    }

    return condensed.toArray(new ItemStack[condensed.size()]);
    }

    public static int containsSets(ItemStack[] set, ItemStack[] stock, boolean oreDictionary, boolean craftingTools) {
        int totalSets = 0;

        ItemStack[] condensedRequired = condenseStacks(set);
        ItemStack[] condensedOffered = condenseStacks(stock);

        for (ItemStack req : condensedRequired) {

            int reqCount = 0;
            for (ItemStack offer : condensedOffered) {
                if (IsItemStackEqual(req, offer)) {
                    int stackCount = (int) Math.floor(offer.stackSize / req.stackSize);
                    reqCount = Math.max(reqCount, stackCount);
                }
            }

            if (reqCount == 0) {
                return 0;
            } else if (totalSets == 0) {
                totalSets = reqCount;
            } else if (totalSets > reqCount) {
                totalSets = reqCount;
            }
        }

        return totalSets;
    }

    public static boolean IsItemStackEqual(ItemStack stack1,ItemStack stack2){
        if (stack1 == null || stack2 == null) return false;
        if (!stack1.isItemEqual(stack2))return false;
        return stack1.getTagCompound() == stack2.getTagCompound();

    }
}