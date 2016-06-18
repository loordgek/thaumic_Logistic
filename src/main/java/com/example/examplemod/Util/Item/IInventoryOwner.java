package com.example.examplemod.util.item;

import net.minecraft.inventory.IInventory;

public interface IInventoryOwner {
    void onInventoryChanged(IInventory inventory);
}
