package dislog.cs.cs.model.utils;

public enum ActionVehicule {
    EN_MARCHE("EN MARCHE"),
    EN_ARRET_DISPONIBLE("EN ARRET DISPONIBLE"),
    EN_ARRET_CHEZ_DISTRIBUTEUR("EN ARRET - CHEZ DISTRIBUTEUR"),
    EN_RENNOVATION("EN RENNOVATION");

    private final String value;

    ActionVehicule(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}