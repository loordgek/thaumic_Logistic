package com.example.examplemod.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeUtil {
    public static ItemStack parseStringAsItemStack(String str, boolean allowWildcard){
        str = str.trim();
        int count = 1;
        int meta;
        if(allowWildcard){
            meta = OreDictionary.WILDCARD_VALUE;
        } else {
            meta = 0;
        }
        int nameStart = 0;
        int nameEnd = str.length();
        if(str.contains("*")){
            count = Integer.parseInt(str.substring(0,str.indexOf("*")).trim());
            nameStart = str.indexOf("*")+1;
        }
        if(str.contains("#")){
            meta = Integer.parseInt(str.substring(str.indexOf("#")+1,str.length()).trim());
            nameEnd = str.indexOf("#");
        }
        String id = str.substring(nameStart,nameEnd).trim();
        String mod = id.substring(0,id.indexOf(":")).trim();
        String name = id.substring(id.indexOf(":")+1,id.length()).trim();
        if(GameRegistry.findBlock(mod, name) != null){
            // is a block
            return new ItemStack(GameRegistry.findBlock(mod, name),count,meta);
        } else if(GameRegistry.findItem(mod, name) != null){
            // is an item
            return new ItemStack(GameRegistry.findItem(mod, name),count,meta);
        } else {
            // item not found
            LogHelper.warn("Failed to find item or block for ID '"+id+"'");
            return null;
        }
    }
}
