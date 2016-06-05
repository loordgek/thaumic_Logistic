package com.example.examplemod.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;


public abstract class BlockMain extends BlockContainer {
    public BlockMain(Material material){
        super(material);
    }

    public BlockMain(){
        this(Material.rock);
    }
}
