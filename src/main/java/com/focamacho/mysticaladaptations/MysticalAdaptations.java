package com.focamacho.mysticaladaptations;

import com.blakebr0.mysticalagriculture.item.tool.EssenceBowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceCrossbowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceFishingRodItem;
import com.focamacho.mysticaladaptations.config.ConfigHolder;
import com.focamacho.mysticaladaptations.config.ConfigMysticalAdaptations;
import com.focamacho.mysticaladaptations.handlers.MobDropsHandler;
import com.focamacho.mysticaladaptations.handlers.TooltipHandler;
import com.focamacho.mysticaladaptations.init.ModItems;
import com.focamacho.mysticaladaptations.init.ModRegistry;
import com.focamacho.mysticaladaptations.util.Reference;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Reference.MOD_ID)
public class MysticalAdaptations {

    public static final Logger LOGGER = LogUtils.getLogger();

    public MysticalAdaptations(IEventBus modEventBus, ModContainer modContainer) {
        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, ConfigMysticalAdaptations.SPEC);

        // Register setup listeners
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        // Register mod content
        ModRegistry.register(modEventBus);

        // Register config event handler
        modEventBus.register(ConfigHolder.INSTANCE);
        // Register global event handlers
        NeoForge.EVENT_BUS.register(new MobDropsHandler());
        NeoForge.EVENT_BUS.register(new TooltipHandler());
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Common setup logic (if needed)
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.INSANIUM_BOW.get(), ResourceLocation.withDefaultNamespace("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.INSANIUM_BOW.get(), ResourceLocation.withDefaultNamespace("pulling"), EssenceBowItem.getPullingPropertyGetter());

            ItemProperties.register(ModItems.INSANIUM_CROSSBOW.get(), ResourceLocation.withDefaultNamespace("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.INSANIUM_CROSSBOW.get(), ResourceLocation.withDefaultNamespace("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.INSANIUM_CROSSBOW.get(), ResourceLocation.withDefaultNamespace("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.INSANIUM_CROSSBOW.get(), ResourceLocation.withDefaultNamespace("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());

            ItemProperties.register(ModItems.INSANIUM_FISHING_ROD.get(), ResourceLocation.withDefaultNamespace("cast"), EssenceFishingRodItem.getCastPropertyGetter());
        });
    }
}
