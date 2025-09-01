package dislog.cs.cs.model.utils;

public enum TonnageRemorque {
    T_32("32T");

    private final String value;

    TonnageRemorque(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}