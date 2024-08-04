package dev.superspeeder.braindedtech.common;

import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;

public enum ParameterType {
    STRING(String.class),
    INTEGER(Integer.class),
    BOOLEAN(Boolean.class),
    NUMBER(Double.class),
    RESOURCE_LOCATION(ResourceLocation.class),
    ;

    public final Class<?> clazz;

    ParameterType(Class<?> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public <T> T parseFromJson(JsonElement jsonElement) {
        return (T)ParameterJsonDecoders.get(this).decode(jsonElement);
    }
}
