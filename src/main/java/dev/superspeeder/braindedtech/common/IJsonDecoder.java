package dev.superspeeder.braindedtech.common;

import com.google.gson.JsonElement;

@FunctionalInterface
public interface IJsonDecoder<T> {
    T decode(JsonElement element);
}
