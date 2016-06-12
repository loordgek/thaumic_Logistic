package com.example.examplemod.Util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PlayerUtil {
    public static EntityPlayer findplayer(World world ,String username){
        for (Object playerObject : world.playerEntities) {
            EntityPlayer player = (EntityPlayer) playerObject;
            if (player.getDisplayName().equals(username)){
            return player;}
        }
        return null;
    }
    public static EntityPlayer getFakePlayer(TileEntity tile) {
        return new FakePlayer(tile);
    }
}
