package dev.superspeeder.braindedtech.material;

import dev.superspeeder.braindedtech.part.PartConfiguration;
import dev.superspeeder.braindedtech.part.PartDefinition;
import net.minecraft.util.FastColor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Material {
    private final Map<PartDefinition, PartConfiguration> partConfigurations;
    private final int tint;

    public Material(Map<PartDefinition, PartConfiguration> partConfigurations, int tint) {
        this.partConfigurations = partConfigurations;
        this.tint = tint;
    }

    public Collection<PartConfiguration> getPartConfigurations() {
        return partConfigurations.values();
    }

    public int getTint() {
        return tint;
    }

    public PartConfiguration getPartConfiguration(PartDefinition partDef) {
        return partConfigurations.get(partDef);
    }

    public static class Builder {
        private final Set<PartConfiguration> partConfigurations = new HashSet<>();
        private int tint;

        public Builder withPart(PartConfiguration partConfiguration) {
            partConfigurations.add(partConfiguration);
            return this;
        }

        public Builder withTint(int tint) {
            this.tint = tint;
            return this;
        }

        public Material build() {
            return new Material(partConfigurations.stream().collect((Collector<PartConfiguration, ?, Map<PartDefinition, PartConfiguration>>) Collectors.toUnmodifiableMap(PartConfiguration::getBase, pc -> pc)), tint);
        }
    }
}
