package Cartas;


import BarajaCartas.Palo;

public class CartaFigura extends Carta{

    private final char letra;

    public CartaFigura(char letra, Palo palo) {
        super(palo);
        this.letra = letra;
    }

    public int getValorNumerico() {
        return switch (letra) {
            case 'J' -> 11;
            case 'Q' -> 12;
            case 'K' -> 13;
            default -> 1;
        };
    }

    @Override
    public String getValor() {
        return String.valueOf(getValorNumerico());
    }

    @Override
    public String getRepresentacion() {
        return letra + super.getRepresentacion();
    }

}