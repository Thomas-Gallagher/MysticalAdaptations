package com.focamacho.mysticaladaptations.config;

import com.focamacho.mysticaladaptations.util.Reference;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class ConfigHolder {

    public static final ConfigHolder INSTANCE = new ConfigHolder();

    // Mystical Agradditions
    public static int insaniumArmorAugments;
    public static int insaniumToolsAugments;

    // Mob Drops
    public static boolean witherInsanium;
    public static boolean dragonInsanium;

    public static void updateConfigs() {
        insaniumArmorAugments = ConfigMysticalAdaptations.CONFIG.insaniumArmorAugments.get();
        insaniumToolsAugments = ConfigMysticalAdaptations.CONFIG.insaniumToolsAugments.get();

        witherInsanium = ConfigMysticalAdaptations.CONFIG.witherInsanium.get();
        dragonInsanium = ConfigMysticalAdaptations.CONFIG.dragonInsanium.get();
    }

    @SubscribeEvent
    public void onModConfigEvent(final ModConfigEvent event) {
        final ModConfig config = event.getConfig();

        if (config.getModId().equals(Reference.MOD_ID) && config.getType() == ModConfig.Type.COMMON) {
            updateConfigs();
        }
    }
}
