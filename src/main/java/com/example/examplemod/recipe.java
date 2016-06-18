package com.example.examplemod;

import com.example.examplemod.util.RecipeUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;


public class recipe {
    public static void addrecipe(){
        ItemStack LPcrfaft = RecipeUtil.parseStringAsItemStack("LogisticsPipes:logisticsSolidBlock#3",false);
        ItemStack alu = RecipeUtil.parseStringAsItemStack("Thaumcraft:ItemResource#2",false);
        ItemStack qsilver = RecipeUtil.parseStringAsItemStack("Thaumcraft:ItemResource#3",false);
        ItemStack vis = RecipeUtil.parseStringAsItemStack("Thaumcraft:blockMetalDevice#2",false);
        GameRegistry.addRecipe(new ItemStack(thaumicLogisticMod.blockArcane) ,
                "WIW",
                "ILI",
                "WIW",
                'L', LPcrfaft,
                'W', alu,
                'I', qsilver);

        GameRegistry.addRecipe(new ItemStack(thaumicLogisticMod.blockRelay) ,
                "WLW",
                "...",
                "WLW",
                'L', vis,
                'W', alu,
                'I', qsilver);

    }
}
