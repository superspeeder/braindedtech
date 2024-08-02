package dev.superspeeder.braindedtech.registry;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class BDRegistry<T> {
    private static final Logger LOGGER = LogUtils.getLogger();


    private boolean frozen = true;
    private final HashMap<ResourceLocation, T> entriesByResourceLocation = new HashMap<>();
    private final HashMap<T, ResourceLocation> resourceLocationFromEntry = new HashMap<>();

    // these are purposefully package-protected because only BDRegistryManager should call them
    void freeze() {
        frozen = true;
    }

    void unfreeze() {
        frozen = false;
    }

    public void register(ResourceLocation key, T entry) {
        if (frozen) {
            throw new IllegalStateException("Registry is frozen, entries cannot be added");
        }

        if (key == null) {
            throw new NullPointerException("Registry entry key cannot be null");
        }

        if (entry == null) {
            throw new NullPointerException("Registry entry cannot be null");
        }

        if (entriesByResourceLocation.containsKey(key)) {
            LOGGER.error("Cannot register duplicate key: {}", key);
            return;
        }

        if (resourceLocationFromEntry.containsKey(entry)) {
            LOGGER.error("Cannot register duplicate objects with (possibly different) key: {}", key);
            return;
        }

        // all validation passed
        _register(key, entry);
    }

    private void _register(ResourceLocation key, T entry) {
        entriesByResourceLocation.put(key, entry);
        resourceLocationFromEntry.put(entry, key);

        // todo: any registry callbacks
    }

    public boolean isPresent(ResourceLocation key) {
        return entriesByResourceLocation.containsKey(key);
    }

    public boolean isPresent(T entry) {
        return resourceLocationFromEntry.containsKey(entry);
    }

    public T resolve(ResourceLocation key) {
        return entriesByResourceLocation.get(key);
    }

    public ResourceLocation resolveKey(T entry) {
        return resourceLocationFromEntry.get(entry);
    }

    public Collection<ResourceLocation> getAllKeys() {
        return entriesByResourceLocation.keySet();
    }

    public Collection<T> getAllEntries() {
        return resourceLocationFromEntry.keySet();
    }
}
