package org.example;

/**
 * Enumeración que representa los distintos tipos de reunion
 */
public enum tipoReunion {
    TECNICA(),
    MARKETING(),
    OTRO();

    public String toString() {
        switch (this) {
            case TECNICA -> {
                return "Tecnica";
            }
            case MARKETING -> {
                return "Marketing";
            }
            default -> {
                return "Otro";
            }
        }
    }

}
