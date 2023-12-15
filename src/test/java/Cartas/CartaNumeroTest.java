package Cartas;

import BarajaCartas.Palo;
import org.junit.Test;

public class CartaNumeroTest {
    @Test
    public void test1(){
        //prueba que cartas de tipo numero devuelva valor esperado
        CartaNumero c1 = new CartaNumero(2, Palo.TREBOL);
        CartaNumero c2 = new CartaNumero(10, Palo.CORAZON);
        CartaNumero c3 = new CartaNumero(4, Palo.PICA);
        CartaNumero c4 = new CartaNumero(7, Palo.DIAMANTE);

        assert (c1.getValor().equals("2"));
        assert (c2.getValor().equals("10"));
        assert (c3.getValor().equals("4"));
        assert (c4.getValor().equals("7"));
    }
}