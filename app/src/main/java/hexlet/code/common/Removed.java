package hexlet.code.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Removed(
    @JsonProperty("FIELD") String key,
    @JsonProperty("STATUS") Status status,
    @JsonProperty("OLD_VALUE") Object oldValue
) implements DiffRow { }
