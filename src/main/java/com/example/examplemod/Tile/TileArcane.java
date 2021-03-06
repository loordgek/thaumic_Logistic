package com.example.examplemod.Tile;

import com.example.examplemod.Util.Item.IInventoryOwner;
import com.example.examplemod.Util.Item.InventoryConcatenator;
import com.example.examplemod.Util.Item.InventorySimple;
import com.example.examplemod.Util.LogHelper;
import com.example.examplemod.Util.RecipeUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TileArcane extends TileEntity implements IInventory, IInventoryOwner {
    private InventorySimple result = new InventorySimple(1,1,"result", this);
    private InventorySimple wandinv = new InventorySimple(1,1,"wand", this);
    private InventorySimple maininv = new InventorySimple(64,18,"maininv", this);
    private InventorySimple matrix = new InventorySimple(1,9,"matrix", this);


    private IInventory inv = InventoryConcatenator.make().add(maininv).add(matrix).add(result);

    public TileArcane() {
    }

    private void SetResult(){
        if (wandinv.getStackInSlot(0) == null) return;
        ItemWandCasting itemWandCasting = (ItemWandCasting)wandinv.getStackInSlot(0).getItem();
        IArcaneRecipe arcaneRecipe = RecipeUtil.INSTANCE.findMatchingArcaneResult(matrix,0,9,null);
        if (itemWandCasting.consumeAllVisCrafting(wandinv.getStackInSlot(0), null, arcaneRecipe.getAspects(),false)) return;
        result.setInventorySlotContents(0,arcaneRecipe.getRecipeOutput());
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(worldObj.isRemote){
            if (result.getStackInSlot(0) == null) SetResult();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        maininv.readFromNBT(tag, maininv.getInventoryName());
        wandinv.readFromNBT(tag, wandinv.getInventoryName());
        matrix.readFromNBT(tag, matrix.getInventoryName());
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        maininv.writeToNBT(tag, maininv.getInventoryName());
        wandinv.writeToNBT(tag, wandinv.getInventoryName());
        matrix.writeToNBT(tag, matrix.getInventoryName());
        LogHelper.info(tag);
    }

    @Override
    public int getSizeInventory() {
        return inv.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int id) {
        if (id >inv.getSizeInventory()) {
            LogHelper.error(id);
            return null;
        }
        return inv.getStackInSlot(id);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        return inv.decrStackSize(slot,amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return inv.getStackInSlotOnClosing(slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        inv.setInventorySlotContents(slot,itemStack);
    }

    @Override
    public String getInventoryName() {
        return "lol";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
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

    @Override
    public void onInventoryChanged(IInventory inventory) {
        markDirty();
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }
}
