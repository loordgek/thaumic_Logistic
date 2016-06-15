package com.example.examplemod.Tile;

import com.example.examplemod.Util.Item.IInventoryOwner;
import com.example.examplemod.Util.Item.InventoryConcatenator;
import com.example.examplemod.Util.Item.InventorySimple;
import com.example.examplemod.Util.LogHelper;
import com.example.examplemod.Util.RecipeUtil;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TileArcane extends TileEntity implements IInventory, IInventoryOwner {
    public InventorySimple result = new InventorySimple(1,1,"result", this);
    public InventorySimple wandinv = new InventorySimple(1,1,"wand", this);
    public InventorySimple maininv = new InventorySimple(64,18,"maininv", this);
    public InventorySimple matrix = new InventorySimple(1,9,"matrix", this);
    private GameProfile playerprofile;
    private final String TAG_PLAYER = "TRANSMEED_PLAYER";
    private int timer = 0;

    private IInventory inv = InventoryConcatenator.make().add(maininv).add(matrix).add(result);

    public TileArcane() {}

    public EntityPlayer getPlayer() {
        if (playerprofile != null) {
            return MinecraftServer.getServer().getConfigurationManager().func_152612_a(playerprofile.getName());
        }
        return null;
    }

    private void SetResult(){
        if (getPlayer() == null) return;
        if (wandinv.getStackInSlot(0) == null) return;
        ItemWandCasting itemWandCasting = (ItemWandCasting)wandinv.getStackInSlot(0).getItem();
        IArcaneRecipe arcaneRecipe = RecipeUtil.INSTANCE.findMatchingArcaneResult(matrix,0,9,getPlayer());
        if (arcaneRecipe == null) return;
        LogHelper.info(arcaneRecipe.getRecipeOutput());
        if (itemWandCasting.consumeAllVisCrafting(wandinv.getStackInSlot(0), getPlayer(), arcaneRecipe.getAspects(),false)) return;
        result.setInventorySlotContents(0,arcaneRecipe.getRecipeOutput());
    }
    public void PlacedBy(EntityPlayer player){
        playerprofile = player.getGameProfile();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote){
            if (timer == 0){
                timer = 20;
                tick();
            }
            timer--;
        }
    }
    public void tick(){
        SetResult();
        if (getPlayer() != null){
            LogHelper.info(getPlayer());
       }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if(tag.hasKey(TAG_PLAYER)){
            playerprofile = NBTUtil.func_152459_a(tag.getCompoundTag(TAG_PLAYER));
        }
        maininv.readFromNBT(tag, maininv.getInventoryName());
        wandinv.readFromNBT(tag, wandinv.getInventoryName());
        matrix.readFromNBT(tag, matrix.getInventoryName());
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (this.playerprofile != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            NBTUtil.func_152460_a(nbt, playerprofile);
            tag.setTag(TAG_PLAYER, nbt);
        }
        maininv.writeToNBT(tag, maininv.getInventoryName());
        wandinv.writeToNBT(tag, wandinv.getInventoryName());
        matrix.writeToNBT(tag, matrix.getInventoryName());
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
