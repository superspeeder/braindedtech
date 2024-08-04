package dev.superspeeder.braindedtech.common;

import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.NotImplementedException;

public class ParameterJsonDecoders {
    public static final IJsonDecoder<String> STRING = JsonElement::getAsString;
    public static final IJsonDecoder<Integer> INTEGER = JsonElement::getAsInt;
    public static final IJsonDecoder<Boolean> BOOLEAN = JsonElement::getAsBoolean;
    public static final IJsonDecoder<Double> NUMBER = JsonElement::getAsDouble;
    public static final IJsonDecoder<ResourceLocation> RESOURCE_LOCATION = element -> ResourceLocation.parse(element.getAsString());


    @SuppressWarnings("unchecked")
    public static <T> IJsonDecoder<T> get(ParameterType type) {
        switch (type) {
            case STRING -> {
                return (IJsonDecoder<T>)STRING;
            }
            case INTEGER -> {
                return (IJsonDecoder<T>)INTEGER;
            }
            case BOOLEAN -> {
                return (IJsonDecoder<T>)BOOLEAN;
            }
            case NUMBER -> {
                return (IJsonDecoder<T>)NUMBER;
            }
            case RESOURCE_LOCATION -> {
                return (IJsonDecoder<T>)RESOURCE_LOCATION;
            }
        }

        throw new NotImplementedException("Parameter type " + type + " is not implemented yet");
    }
}
