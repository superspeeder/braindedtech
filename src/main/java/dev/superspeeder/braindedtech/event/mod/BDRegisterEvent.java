package dev.superspeeder.braindedtech.event.mod;

import dev.superspeeder.braindedtech.registry.BDRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;

public class BDRegisterEvent extends Event implements IModBusEvent {
    private final Class<?> clazz;
    private final BDRegistry<?> registry;

    public BDRegisterEvent(Class<?> clazz, BDRegistry<?> registry) {
        this.clazz = clazz;
        this.registry = registry;
    }

    public Class<?> getRegistryType() {
        return clazz;
    }

    @SuppressWarnings("unchecked")
    public <T> BDRegistry<T> getRegistry() {
        return (BDRegistry<T>)registry;
    }

    public <T> void register(ResourceLocation key, T entry) {
        getRegistry().register(key, entry);
    }
}
