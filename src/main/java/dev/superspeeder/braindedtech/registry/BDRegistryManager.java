package dev.superspeeder.braindedtech.registry;

import dev.superspeeder.braindedtech.event.mod.BDRegisterEvent;
import dev.superspeeder.braindedtech.material.Material;
import dev.superspeeder.braindedtech.part.PartDefinition;
import net.neoforged.fml.ModLoader;


public class BDRegistryManager {
    public static final BDRegistry<PartDefinition> PARTS = new BDRegistry<>();
    public static final BDRegistry<Material> MATERIALS = new BDRegistry<>();


    private static final BDRegistry<?>[] REGISTRIES = new BDRegistry<?>[] {
            PARTS, MATERIALS
    };

    public static void setupRegistries() {
    }

    public static void registerAll() {
        for (BDRegistry<?> registry : REGISTRIES) registry.unfreeze();

        ModLoader.postEventWrapContainerInModOrder(new BDRegisterEvent(PartDefinition.class, PARTS));
        ModLoader.postEventWrapContainerInModOrder(new BDRegisterEvent(Material.class, MATERIALS));

        for (BDRegistry<?> registry : REGISTRIES) registry.freeze();
    }
}
