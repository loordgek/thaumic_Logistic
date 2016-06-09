package com.example.examplemod.Tile;

import com.example.examplemod.Util.Item.IInventoryOwner;
import com.example.examplemod.Util.Item.InventoryConcatenator;
import com.example.examplemod.Util.Item.InventorySimple;
import com.example.examplemod.Util.LogHelper;
import com.example.examplemod.Util.PlayerIdentifier;
import com.example.examplemod.Util.RecipeUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TileArcane extends TileEntity implements IInventory, IInventoryOwner {
    public InventorySimple result = new InventorySimple(1,1,"result", this);
    public InventorySimple wandinv = new InventorySimple(1,1,"wand", this);
    public InventorySimple maininv = new InventorySimple(64,18,"maininv", this);
    public InventorySimple matrix = new InventorySimple(1,9,"matrix", this);
    public PlayerIdentifier placedBy = null;
    public EntityPlayer player = null;
    private int Timer = 0;
    private boolean firsttick = true;


    private IInventory inv = InventoryConcatenator.make().add(maininv).add(matrix).add(result);

    public TileArcane() {
    }
    private void ticktimer(){
        if (Timer == 0){
            Timer = 20;
            tick();
        }
        Timer--;
    }

    private void tick(){
//        if (result.getStackInSlot(0) == null) SetResult();
        if (placedBy != null) {
            LogHelper.info(placedBy.getUsername());
        }
    }

    private void SetResult(){
        if (wandinv.getStackInSlot(0) == null) return;
        ItemWandCasting itemWandCasting = (ItemWandCasting)wandinv.getStackInSlot(0).getItem();
        IArcaneRecipe arcaneRecipe = RecipeUtil.INSTANCE.findMatchingArcaneResult(matrix,0,9,player);
        if (itemWandCasting.consumeAllVisCrafting(wandinv.getStackInSlot(0), player, arcaneRecipe.getAspects(),false)) return;
        result.setInventorySlotContents(0,arcaneRecipe.getRecipeOutput());
    }
    public void PlacedBy(EntityPlayer player){
        this.placedBy = PlayerIdentifier.get(player);
        this.player = player;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(worldObj.isRemote){
            ticktimer();
        }
        if (firsttick){
            tick1time();
            firsttick = false;
        }

    }
    private void tick1time() {
        if (placedBy != null) {
            this.player = worldObj.getPlayerEntityByName(placedBy.getUsername());
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey("placedBy")) {
            String name = tag.getString("placedBy");
            placedBy = PlayerIdentifier.convertFromUsername(name);
        } else {
            placedBy = PlayerIdentifier.readFromNBT(tag, "placedBy");
        }
        String player = tag.getString("player");
        this.player = worldObj.getPlayerEntityByName(player);
        maininv.readFromNBT(tag, maininv.getInventoryName());
        wandinv.readFromNBT(tag, wandinv.getInventoryName());
        matrix.readFromNBT(tag, matrix.getInventoryName());
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (placedBy != null) {
            placedBy.writeToNBT(tag, "placedBy");
        }
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
        LogHelper.info("hello");
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }
}
