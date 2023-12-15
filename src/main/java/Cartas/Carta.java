package Cartas;

import BarajaCartas.Palo;

import java.io.Serializable;

public abstract class Carta implements Serializable {

    private boolean estaTapada;
    private final Palo palo;

    public Carta(Palo palo) {
        this.estaTapada = true;
        this.palo = palo;
    }

    public Palo getPalo() {
        return palo;
    }

    public abstract String getValor();

    public boolean esTapada() {
        return estaTapada;
    }

    public void darVuelta() {
        estaTapada = !estaTapada;
    }


    public String getRepresentacion() {
        return " de " + palo;
    }
}