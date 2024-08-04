package dev.superspeeder.braindedtech.part;

import dev.superspeeder.braindedtech.common.Parameter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PartDefinition {
    private Map<String, Parameter<?>> parameters;

    public PartDefinition(Map<String, Parameter<?>> parameters) {
        this.parameters = parameters;
    }

    public boolean hasParameter(String name) {
        return parameters.containsKey(name);
    }

    public Map<String, Parameter<?>> getParameters() {
        return parameters;
    }

    public static class Builder {
        private Map<String, Parameter<?>> parameters = new HashMap<>();

        public Builder withParameter(String name, Parameter<?> parameter) {
            parameters.put(name, parameter);
            return this;
        }

        public Builder withParameters(Map<String, Parameter<?>> parameters) {
            this.parameters.putAll(parameters);
            return this;
        }

        public PartDefinition build() {
            return new PartDefinition(parameters);
        }
    }
}
