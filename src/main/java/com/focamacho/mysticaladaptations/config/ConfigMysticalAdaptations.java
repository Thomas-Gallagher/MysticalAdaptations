package com.focamacho.mysticaladaptations.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigMysticalAdaptations {

    public static final General CONFIG;
    public static final ModConfigSpec SPEC;

    static {
        Pair<General, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(General::new);
        CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }

    public static class General {
        public final ModConfigSpec.IntValue insaniumArmorAugments;
        public final ModConfigSpec.IntValue insaniumToolsAugments;
        public final ModConfigSpec.BooleanValue witherInsanium;
        public final ModConfigSpec.BooleanValue dragonInsanium;

        public General(ModConfigSpec.Builder builder) {
            builder.push("Mystical Agradditions");

            insaniumArmorAugments = builder.defineInRange("insanium_armor_augments", 2, 1, 2);
            insaniumToolsAugments = builder.defineInRange("insanium_tools_augments", 2, 1, 2);

            builder.pop();
            builder.push("Mob Drops");

            witherInsanium = builder.define("wither_insanium", true);
            dragonInsanium = builder.define("dragon_insanium", true);

            builder.pop();
        }
    }
}
