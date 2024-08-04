package dev.superspeeder.braindedtech.part;

import dev.superspeeder.braindedtech.common.Parameter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PartConfiguration {
    private PartDefinition base;

    private Map<String, Object> parameters;

    public PartConfiguration(PartDefinition base, Map<String, Object> parameters) {
        this.base = base;
        this.parameters = new HashMap<>();

        for (Map.Entry<String, Parameter<?>> parameter : base.getParameters().entrySet()) {
            if (parameters.containsKey(parameter.getKey())) {
                this.parameters.put(parameter.getKey(), parameters.get(parameter.getKey()));
            } else {
                this.parameters.put(parameter.getKey(), parameter.getValue().defaultValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getParameter(String parameter) {
        if (parameters.containsKey(parameter)) {
            return (T) parameters.get(parameter);
        }

        throw new IllegalArgumentException("Parameter " + parameter + " not found");
    }

    public PartDefinition getBase() {
        return base;
    }

    public static class Builder {
        private PartDefinition base;
        private Map<String, Object> parameters = new HashMap<>();

        public Builder(PartDefinition base) {
            this.base = base;
        }

        public Builder() {
        }

        public Builder withBase(PartDefinition base) {
            this.base = base;
            return this;
        }

        public <T> Builder withParameter(String parameter, T value) {
            this.parameters.put(parameter, value);
            return this;
        }


        public Builder withParameters(Map<String, Object> parameters) {
            this.parameters.putAll(parameters);
            return this;
        }

        public PartConfiguration build() {
            return new PartConfiguration(Objects.requireNonNull(base), parameters);
        }
    }
}
