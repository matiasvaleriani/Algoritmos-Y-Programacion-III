package Estructuras;
import Cartas.Carta;

import java.io.Serializable;

public class Casillero implements Serializable {
    private Carta carta;

    public Casillero() {
        this.carta = null;
    }

    public boolean estaVacio() {
        return carta == null;
    }

    public void colocarCarta(Carta carta) {
        if (estaVacio()) {
            this.carta = carta;
        } else {
            System.out.println("El casillero ya está ocupado.");
        }
    }

    public Carta quitarCarta() {
        if (!estaVacio()) {
            Carta cartaEnCasillero = carta;
            carta = null;
            return cartaEnCasillero;
        } else {
            System.out.println("El casillero está vacío.");
            return null;
        }
    }

    public Carta verCarta() {
        return carta;
    }

}