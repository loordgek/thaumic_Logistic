package com.example.examplemod.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileVisRelay;

public class TileRelay extends TileVisRelay {

    public TileRelay() {
    }

    @Override
    public boolean isSource() {
        return false;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!this.worldObj.isRemote) {
            TileEntity te = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
            if(te != null && te instanceof TileArcane) {
                TileArcane tm = (TileArcane)te;
                ItemStack wand = tm.wandinv.getStackInSlot(0);
                if(wand != null && wand.getItem() instanceof ItemWandCasting) {
                    AspectList aspectList = ((ItemWandCasting)wand.getItem()).getAspectsWithRoom(wand);
                    if(aspectList.size() > 0) {
                        Aspect[] arr$ = aspectList.getAspects();
                        int len$ = arr$.length;

                        for(int i = 0; i < len$; ++i) {
                            Aspect aspect = arr$[i];
                            int drain = Math.min(5, ((ItemWandCasting)wand.getItem()).getMaxVis(tm.wandinv.getStackInSlot(0)) - ((ItemWandCasting)wand.getItem()).getVis(tm.wandinv.getStackInSlot(0), aspect));
                            if(drain > 0) {
                                ((ItemWandCasting)wand.getItem()).addRealVis(tm.wandinv.getStackInSlot(0), aspect, this.consumeVis(aspect, drain), true);
                            }
                        }
                    }
                }
            }
        }

    }
}
