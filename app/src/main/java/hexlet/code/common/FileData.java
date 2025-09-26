package hexlet.code.common;

public record FileData(String key, Status status,
    Object oldVal, Object newVal) {
}
