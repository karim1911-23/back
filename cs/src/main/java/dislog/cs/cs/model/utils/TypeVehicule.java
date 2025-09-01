package dislog.cs.cs.model.utils;

public enum TypeVehicule {
    FOURGON("FOURGON"),
    FOURGON_MINUS("FOURGON-"), // Using "FOURGON_MINUS" to handle the hyphen safely
    TRACTEUR("TRACTEUR"),
    TRACTEUR_FRIGO("TRACTEUR-FRIGO");

    private final String value;

    TypeVehicule(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}