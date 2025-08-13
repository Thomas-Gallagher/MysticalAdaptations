package com.focamacho.mysticaladaptations;

import com.blakebr0.cucumber.util.FeatureFlagDisplayItemGenerator;
import com.blakebr0.mysticalagriculture.item.tool.EssenceBowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceCrossbowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceFishingRodItem;
import com.focamacho.mysticaladaptations.config.ConfigHolder;
import com.focamacho.mysticaladaptations.config.ConfigMysticalAdaptations;
import com.focamacho.mysticaladaptations.handlers.MobDropsHandler;
import com.focamacho.mysticaladaptations.handlers.TooltipHandler;
import com.focamacho.mysticaladaptations.init.ModAugments;
import com.focamacho.mysticaladaptations.init.ModItems;
import com.focamacho.mysticaladaptations.init.ModRegistry;
import com.focamacho.mysticaladaptations.util.Reference;
import com.focamacho.mysticaladaptations.util.Utils;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mysticaladaptations")
public class MysticalAdaptations {

    private static final Logger LOGGER = LogManager.getLogger();

    public MysticalAdaptations() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigMysticalAdaptations.spec);

        IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        modEventBus.register(this);

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new MobDropsHandler());

        ModRegistry.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        //SeedExtractorRecipeHandler.initRecipes();
        ConfigHolder.updateConfigs();
        if(Utils.isVampirismLoaded) {
            if(!ConfigHolder.thirstlessAugment) ModAugments.THIRSTLESS.setEnabled(false);
            if(!ConfigHolder.daywalkerAugment) ModAugments.DAYWALKER.setEnabled(false);
        }
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.register(new TooltipHandler());
        ModRegistry.registerClient();

        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.INSANIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.INSANIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.INSANIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.INSANIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.INSANIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.INSANIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());

            ItemProperties.register(ModItems.INSANIUM_FISHING_ROD.get(), new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
        });
    }

    @SubscribeEvent
    public void onModConfigEvent(final ModConfigEvent event) {
        final ModConfig config = event.getConfig();

        if (config.getSpec() == ConfigMysticalAdaptations.spec) {
            ConfigHolder.updateConfigs();
        }
    }
}
