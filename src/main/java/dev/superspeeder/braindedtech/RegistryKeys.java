package dev.superspeeder.braindedtech;

import dev.superspeeder.braindedtech.material.Material;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class RegistryKeys {
    public static final ResourceKey<Registry<Material>> MATERIAL = key("material");


    private static <T> ResourceKey<Registry<T>> key(final String name) {
        return ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(BraindedTech.MODID, name));
    }
}
