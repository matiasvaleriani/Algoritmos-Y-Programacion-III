package BarajaCartas;

import java.io.Serializable;

public enum Palo implements Serializable {
    DIAMANTE(Color.ROJO),
    CORAZON(Color.ROJO),
    PICA(Color.NEGRO),
    TREBOL(Color.NEGRO);

    private final Color color;

    Palo(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}