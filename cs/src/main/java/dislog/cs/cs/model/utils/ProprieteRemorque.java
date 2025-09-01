package dislog.cs.cs.model.utils;

public enum ProprieteRemorque {
    COMPTOIR_SERVICE("COMPTOIR SERVICE");

    private final String value;

    ProprieteRemorque(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}