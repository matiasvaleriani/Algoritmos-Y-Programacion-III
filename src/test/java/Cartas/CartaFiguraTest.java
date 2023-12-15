package Cartas;

import BarajaCartas.Palo;
import org.junit.Test;

public class CartaFiguraTest {
    @Test
    public void test1(){
        //prueba que cartas de tipo figura devuelva valor entero esperado
        CartaFigura c1 = new CartaFigura('A', Palo.TREBOL);
        CartaFigura c2 = new CartaFigura('J', Palo.CORAZON);
        CartaFigura c3 = new CartaFigura('Q', Palo.PICA);
        CartaFigura c4 = new CartaFigura('K', Palo.DIAMANTE);

        assert (c1.getValorNumerico() == 1);
        assert (c2.getValorNumerico() == 11);
        assert (c3.getValorNumerico() == 12);
        assert (c4.getValorNumerico() == 13);
    }

    @Test
    public void test2(){
        //prueba que cartas de tipo figura devuelva valor string esperado
        CartaFigura c1 = new CartaFigura('A', Palo.TREBOL);
        CartaFigura c2 = new CartaFigura('J', Palo.CORAZON);
        CartaFigura c3 = new CartaFigura('Q', Palo.PICA);
        CartaFigura c4 = new CartaFigura('K', Palo.DIAMANTE);

        assert (c1.getValor().equals("1"));
        assert (c2.getValor().equals("11"));
        assert (c3.getValor().equals("12"));
        assert (c4.getValor().equals("13"));
    }
}