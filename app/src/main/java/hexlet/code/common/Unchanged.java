package hexlet.code.common;

import com.fasterxml.jackson.annotation.JsonProperty;


public record Unchanged(
    @JsonProperty("FIELD") String key,
    @JsonProperty("STATUS") Status status,
    @JsonProperty("OLD_VALUE") Object oldValue
) implements DiffRow { }
