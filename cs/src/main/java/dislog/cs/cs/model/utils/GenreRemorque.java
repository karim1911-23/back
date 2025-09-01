package dislog.cs.cs.model.utils;

public enum GenreRemorque {
    PLATEAU_BACHE("S/R plateau baché"),
    FRIGORIFIQUE("S/R FRIGORIFIQUE"),
    FOURGON("S/R FOURGON"),
    SEMI_REMORQUE_FOURGON_FRIGORIFIQUE("SEMI REMORQUE FOURGON FRIGORIFIQUE");

    private final String value;

    GenreRemorque(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}