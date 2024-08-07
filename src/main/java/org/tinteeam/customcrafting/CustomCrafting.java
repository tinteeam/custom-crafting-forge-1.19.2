package org.tinteeam.customcrafting;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import org.slf4j.Logger;
//mod imports from the mod itself to use
import org.tinteeam.customcrafting.block.ModBlocks;
import org.tinteeam.customcrafting.block.entity.ModBlockEntities;
import org.tinteeam.customcrafting.item.item;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CustomCrafting.MODID)
public class CustomCrafting
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "customcrafting";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public CustomCrafting()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        modEventBus.addListener(this::commonSetup);

        //register item
        item.register(modEventBus);
        //register block
        ModBlocks.register(modEventBus);
        //register block entity
        ModBlockEntities.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        //log!
        LOGGER.info("the mod has loaded");
        LOGGER.info("this info tells that the mod is working");
        LOGGER.info("If any issues come you can always report them");
        LOGGER.info("report issues at https://github.com/tinteeam/custom-crafting-forge-1.19.2");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("the mod has loaded");
            LOGGER.info("this info tells that the mod is working");
            LOGGER.info("If any issues come you can always report them");
        }
    }
}
