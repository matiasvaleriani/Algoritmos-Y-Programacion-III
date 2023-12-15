package Cartas;

import BarajaCartas.Palo;

public class CartaNumero extends Carta {

    private final int numero;

    public CartaNumero(int numero, Palo palo) {
        super(palo);
        this.numero = numero;
    }

    @Override
    public String getValor() {
        return String.valueOf(numero);
    }

    @Override
    public String getRepresentacion() {
        return numero + super.getRepresentacion();
    }
}