package dev.superspeeder.braindedtech.part;

import dev.superspeeder.braindedtech.material.Material;

public record Part(PartConfiguration configuration, Material material) {

    public Part(PartDefinition definition, Material material) {
        this(material.getPartConfiguration(definition), material);
    }
}
