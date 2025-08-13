package com.focamacho.mysticaladaptations.lib;

import com.focamacho.mysticaladaptations.init.ModItems;
import com.focamacho.mysticaladaptations.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterial {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, Reference.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> INSANIUM =
            ARMOR_MATERIALS.register("insanium", () -> {

                Map<ArmorItem.Type, Integer> defenseMap = Map.of(
                        ArmorItem.Type.BOOTS, 4,
                        ArmorItem.Type.LEGGINGS, 7,
                        ArmorItem.Type.CHESTPLATE, 9,
                        ArmorItem.Type.HELMET, 5
                );

                List<ArmorMaterial.Layer> layers = List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "insanium"))
                );

                TagKey<Item> repairTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingots/insanium"));

                return new ArmorMaterial(
                        defenseMap,        // Defense values per armor type
                        20,                // Enchantability
                        SoundEvents.ARMOR_EQUIP_NETHERITE, // Equip sound
                        () -> Ingredient.of(ModItems.INSANIUM_INGOT.get()), // Repair ingredient supplier
                        layers,            // Armor layers
                        4.0F,              // Toughness
                        0.2F               // Knockback resistance
                );
            });
}
