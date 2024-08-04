package dev.superspeeder.braindedtech.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public record Parameter<T>(
        ParameterType type,
        T defaultValue
) {

    @SuppressWarnings("unchecked")
    public Class<T> underlyingClass() {
        return (Class<T>)type.clazz;
    }

    public T readFromJsonValue(JsonElement jsonElement) {
        // reads from json element
        return type.parseFromJson(jsonElement);
    }
}
