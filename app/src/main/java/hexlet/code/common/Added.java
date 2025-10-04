package hexlet.code.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Added(
    @JsonProperty("FIELD") String key,
    @JsonProperty("STATUS") Status status,
    @JsonProperty("NEW_VALUE") Object newValue
) implements DiffRow { }
