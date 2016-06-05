package com.example.examplemod.Util.Item;

import net.minecraft.inventory.IInventory;

public interface IInventoryOwner {
    void onInventoryChanged(IInventory inventory);
}
