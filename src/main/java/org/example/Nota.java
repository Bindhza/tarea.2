package org.example;

/**
 * Representa una nota tomada en una reunion
 */
public class Nota {
    private final String contenido;

    public Nota(String s) {
        contenido = s;
    }

    /**
     * Obtiene el contenido de la nota
     *
     * @return un string que representa el contenido de la nota
     */
    public String getContenido() {
        return contenido;
    }

    public String toString() {
        return contenido;
    }
}
