package hexlet.code.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Changed(
    @JsonProperty("FIELD") String key,
    @JsonProperty("STATUS") Status status,
    @JsonProperty("OLD_VALUE") Object oldValue,
    @JsonProperty("NEW_VALUE") Object newValue
) implements DiffRow { }
