package Estructuras;

import BarajaCartas.Palo;
import Cartas.Carta;
import Cartas.CartaFigura;
import Cartas.CartaNumero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BarajaDeCartas implements Serializable {

    private final Stack<Carta> cartas;
    private final List<Carta> cartasGeneradas; // Lista temporal para generar cartas

    public BarajaDeCartas() {
        this.cartas = new Stack<>();
        this.cartasGeneradas = new ArrayList<>();
    }

    public int cantidad(){
        return cartas.size();
    }

    public void sacarCarta(){
        //quita la ultima carta de la baraja
        if (verUltimaCarta() != null) {
            cartas.pop();
        }
    }

    public Carta verUltimaCarta() {
        if (!cartas.isEmpty()) {
            return cartas.peek(); // Devuelve la carta en la cima de la pila sin eliminarla
        } else {
            return null;
        }
    }

    public void generarCartasAleatorias() {
        generarCartasNumeros();
        generarCartasFiguras();
        Collections.shuffle(cartasGeneradas);
        cartas.addAll(cartasGeneradas); // Agregar las cartas mezcladas a la pila
        Carta ult = cartas.pop();
        ult.darVuelta();
        cartas.push(ult);
    }

    public void agregarCarta(Carta c){
        cartas.add(c);
    }

    private void generarCartasNumeros() {
        final int MIN_VALOR = 2;
        final int MAX_VALOR = 10;
        Palo[] palos = Palo.values();

        for (Palo paloActual : palos) {
            for (int i = MIN_VALOR; i <= MAX_VALOR; i++) {
                this.cartasGeneradas.add(new CartaNumero(i, paloActual));
            }
        }
    }

    private void generarCartasFiguras() {
        char[] letras = {'A', 'J', 'Q', 'K'};
        Palo[] palos = Palo.values();
        for (Palo paloActual : palos) {
            for (char letraActual : letras) {
                this.cartasGeneradas.add(new CartaFigura(letraActual, paloActual));
            }
        }
    }
}
