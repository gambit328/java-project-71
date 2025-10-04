package hexlet.code.common;

public interface DiffRow {

    String key();
    Status status();
    enum Status { UNCHANGED, CHANGED, REMOVED, ADDED }
}
