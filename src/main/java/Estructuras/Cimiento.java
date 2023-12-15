package Estructuras;

import Cartas.Carta;

import java.io.Serializable;
import java.util.Stack;

public class Cimiento implements Serializable {
    private final Stack<Carta> cartas;

    public Cimiento() {
        cartas = new Stack<>();
    }

    public boolean agregarCarta(Carta carta) {
        if (puedeAgregarCarta(carta)) {
            cartas.push(carta);
            return true;
        } else {
        return false;
        }
    }

    public boolean estaVacio(){
        return cartas.isEmpty();
    }

    public Carta quitarCarta() {
        if (!estaVacio()) return cartas.pop();
        else return null;
    }


    public boolean puedeAgregarCarta(Carta carta) {
        if (cartas.isEmpty()) {
            return carta.getValor().equals("1");
        } else {
            Carta cartaSuperior = cartas.peek();

            return carta.getPalo() == cartaSuperior.getPalo() && esValorSiguiente(cartaSuperior,carta);
        }
    }

    private boolean esValorSiguiente(Carta tope, Carta nueva) {
        String valorTope = tope.getValor();
        String valorNueva = nueva.getValor();
        int a = Integer.parseInt(valorTope);
        int b = Integer.parseInt(valorNueva);
        int dif = b-a;
        return dif == 1;
    }

    public Carta verUltimaCarta() {
        if (!cartas.isEmpty()) {
            return cartas.peek();
        } else {
            return null;
        }
    }

    public boolean estaCompleto(){
        return cartas.size()==13;
    }
}