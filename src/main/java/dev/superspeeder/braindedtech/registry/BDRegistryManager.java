package dev.superspeeder.braindedtech.registry;

import dev.superspeeder.braindedtech.event.mod.BDRegisterEvent;
import dev.superspeeder.braindedtech.material.Material;
import dev.superspeeder.braindedtech.part.Part;
import net.neoforged.fml.ModLoader;


public class BDRegistryManager {
    public static void setupRegistries() {

    }


    public static void registerAll() {
        ModLoader.postEventWrapContainerInModOrder(new BDRegisterEvent<Part>());
        ModLoader.postEventWrapContainerInModOrder(new BDRegisterEvent<Material>());
    }
}
