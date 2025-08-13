package com.focamacho.mysticaladaptations.init;

import com.focamacho.mysticaladaptations.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModCreativeTabs {

    // Create a DeferredRegister for creative tabs - this is what ModRegistry expects
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

    // Register the Mystical Adaptations creative tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MYSTICAL_TAB =
            CREATIVE_MODE_TABS.register("mystical_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.mysticaladaptations"))
                    .icon(() -> new ItemStack(ModItems.INSANIUM_ESSENCE.get()))
                    .displayItems((params, output) -> {
                        // Base items
                        output.accept(ModItems.INSANIUM_INGOT.get());
                        output.accept(ModItems.INSANIUM_ESSENCE.get());

                        // Tools
                        output.accept(ModItems.INSANIUM_SWORD.get());
                        output.accept(ModItems.INSANIUM_PICKAXE.get());
                        output.accept(ModItems.INSANIUM_SHOVEL.get());
                        output.accept(ModItems.INSANIUM_AXE.get());
                        output.accept(ModItems.INSANIUM_HOE.get());
                        output.accept(ModItems.INSANIUM_STAFF.get());
                        output.accept(ModItems.INSANIUM_PAXEL.get());
                        output.accept(ModItems.INSANIUM_BOW.get());
                        output.accept(ModItems.INSANIUM_CROSSBOW.get());
                        output.accept(ModItems.INSANIUM_SHEARS.get());
                        output.accept(ModItems.INSANIUM_FISHING_ROD.get());
                        output.accept(ModItems.INSANIUM_SICKLE.get());
                        output.accept(ModItems.INSANIUM_SCYTHE.get());

                        // Armor
                        output.accept(ModItems.INSANIUM_HELMET.get());
                        output.accept(ModItems.INSANIUM_CHESTPLATE.get());
                        output.accept(ModItems.INSANIUM_LEGGINGS.get());
                        output.accept(ModItems.INSANIUM_BOOTS.get());

                        // Utility
                        output.accept(ModItems.INSANIUM_WATERING_CAN.get());
                    })
                    .build());
}
