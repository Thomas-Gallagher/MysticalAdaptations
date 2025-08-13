package com.focamacho.mysticaladaptations.handlers;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.focamacho.mysticaladaptations.util.Reference;
import com.focamacho.mysticaladaptations.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.core.registries.BuiltInRegistries;

public class TooltipHandler {

    //Replace Tier 6 tooltips with a colored one
    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event) {
        ResourceLocation rl = BuiltInRegistries.ITEM.getKey(event.getItemStack().getItem());
        String modid = rl.getNamespace();

        if (modid.equals(Reference.MOD_ID) || modid.equals(MysticalAgriculture.MOD_ID)) {
            int index = -1;
            for (Component text : event.getToolTip()) {
                if (text.equals(ModTooltips.getTooltipForTier(6))) {
                    index = event.getToolTip().indexOf(text);
                }
            }
            if (index != -1) {
                event.getToolTip().set(index, Component.literal(ChatFormatting.GRAY +
                        String.format(Component.translatable("tooltip.mysticalagriculture.tier").getString(),
                                Utils.getColorFromTier(6) + "6")));
            }
        }
    }
}
