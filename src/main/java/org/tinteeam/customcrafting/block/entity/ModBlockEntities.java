package org.tinteeam.customcrafting.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.tinteeam.customcrafting.CustomCrafting;
import org.tinteeam.customcrafting.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CustomCrafting.MODID);

    public static final RegistryObject<BlockEntityType<PowerFurnaceBlockEntity>> POWER_FURNACE =
            BLOCK_ENTITIES.register("power_furnace", () ->
                    BlockEntityType.Builder.of(PowerFurnaceBlockEntity::new,
                            ModBlocks.POWER_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
