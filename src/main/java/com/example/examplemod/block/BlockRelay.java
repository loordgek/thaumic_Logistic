package com.example.examplemod.block;

import com.example.examplemod.tile.TileRelay;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRelay extends BlockMain {

    public BlockRelay() {
        this.setBlockName("relay");
        this.setBlockTextureName("relay");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileRelay();
    }
}
