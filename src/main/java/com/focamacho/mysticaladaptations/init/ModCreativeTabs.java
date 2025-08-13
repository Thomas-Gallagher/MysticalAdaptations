package com.focamacho.mysticaladaptations.init;

import com.focamacho.mysticaladaptations.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public class ModCreativeTabs {

    public static CreativeModeTab MYSTICAL_TAB;

    @SubscribeEvent
    public static void registerTabs(RegisterEvent event) {
        MYSTICAL_TAB = CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.mysticaladaptations"))
                .icon(() -> new ItemStack(ModItems.INSANIUM_ESSENCE.get()))
                .displayItems((params, output) -> {
                    output.accept(ModItems.INSANIUM_ESSENCE.get());
                    // Add more items/blocks as needed
                })
                .build();

        event.register(
                Registries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "mystical_tab"),
                () -> MYSTICAL_TAB
        );
    }
}
