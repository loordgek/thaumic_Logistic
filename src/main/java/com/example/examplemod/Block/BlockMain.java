package com.example.examplemod.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * Created by stefan on 28-4-2016.
 */
public abstract class BlockMain extends BlockContainer {
    public BlockMain(Material material){
        super(material);
    }

    public BlockMain(){
        this(Material.rock);
    }
}
