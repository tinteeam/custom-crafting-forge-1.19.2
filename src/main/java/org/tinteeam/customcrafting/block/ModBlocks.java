package org.tinteeam.customcrafting.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.tinteeam.customcrafting.item.item;
import org.tinteeam.customcrafting.CustomCrafting;
import org.tinteeam.customcrafting.item.ModCreativeModeTab;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CustomCrafting.MODID);



    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn,tab);
        return toReturn;
    }
    public  static final RegistryObject<Block> POWER_FURNACE = registerBlock("power_furnace",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f)
                    .requiresCorrectToolForDrops()),ModCreativeModeTab.customcrafting);
    public  static final RegistryObject<Block> MACHINE_FRAME = registerBlock("machine_frame",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f)
                    .requiresCorrectToolForDrops()),ModCreativeModeTab.customcrafting);
    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return item.ITEMS.register(name,()-> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


}