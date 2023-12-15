package Estructuras;

import BarajaCartas.Palo;
import Cartas.Carta;
import Cartas.CartaFigura;
import Cartas.CartaNumero;
import org.junit.Test;

public class CimientoTest {
    @Test
    public void test1() {
        //comprueba que un nuevo cimiento vacio se comporte bien
        Cimiento cimiento = new Cimiento();

        //revisa que no hay carta
        Carta ultimaCarta = cimiento.verUltimaCarta();

        assert (ultimaCarta == null);
    }

    @Test
    public void test2() {
        //
        Cimiento cimiento = new Cimiento();

        //revisa que no hay carta
        Carta ultimaCarta = cimiento.quitarCarta();

        assert (ultimaCarta == null);
    }

    @Test
    public void test3() {
        //comprueba que no se puede agregar otra carta
        //que no sea un As si el cimiento esta vacio
        Cimiento cimiento = new Cimiento();

        for (int i = 2; i < 11; i++) {
            //intenta agregar al cimiento desde el 2 al 10 de trebol
            Carta carta = new CartaNumero(i, Palo.TREBOL);
            cimiento.agregarCarta(carta);
        }
        Carta J = new CartaFigura('J', Palo.TREBOL);
        cimiento.agregarCarta(J);
        Carta Q = new CartaFigura('Q', Palo.TREBOL);
        cimiento.agregarCarta(Q);
        Carta K = new CartaFigura('K', Palo.TREBOL);
        cimiento.agregarCarta(K);
        Carta ultimaCarta = cimiento.verUltimaCarta();

        assert (ultimaCarta == null);
    }

    @Test
    public void test4() {
        //prueba agregar y eliminar una carta
        Cimiento cimiento = new Cimiento();
        Carta As = new CartaFigura('A', Palo.TREBOL);

        cimiento.agregarCarta(As);
        Carta ultCarta = cimiento.verUltimaCarta();
        assert (ultCarta == As);
        cimiento.quitarCarta();

        //revisa que quedo el cimiento vacio
        ultCarta = cimiento.verUltimaCarta();
        assert (ultCarta == null);
    }

    @Test
    public void test5() {
        //prueba completar el cimiento y revisa que siempre
        //se actualiza la ultima carta del cimiento
        Cimiento cimiento = new Cimiento();
        //Carta ultimaCarta = cimiento.verUltimaCarta();

        Carta As = new CartaFigura('A', Palo.TREBOL);
        cimiento.agregarCarta(As);
        Carta ultimaCarta = cimiento.verUltimaCarta();
        assert (ultimaCarta == As);

        for (int i = 2; i < 11; i++) {
            //intenta agregar al cimiento desde el 2 al 10 de trebol
            Carta carta = new CartaNumero(i, Palo.TREBOL);
            cimiento.agregarCarta(carta);
            ultimaCarta = cimiento.verUltimaCarta();
            assert (ultimaCarta == carta);
        }
        Carta J = new CartaFigura('J', Palo.TREBOL);
        cimiento.agregarCarta(J);
        ultimaCarta = cimiento.verUltimaCarta();
        assert (ultimaCarta == J);
        Carta Q = new CartaFigura('Q', Palo.TREBOL);
        cimiento.agregarCarta(Q);
        ultimaCarta = cimiento.verUltimaCarta();
        assert (ultimaCarta == Q);
        Carta K = new CartaFigura('K', Palo.TREBOL);
        cimiento.agregarCarta(K);
        ultimaCarta = cimiento.verUltimaCarta();
        assert (ultimaCarta == K);

        assert (cimiento.estaCompleto());

    }

    @Test
    public void test6() {
        //prueba agregar un AS de algun palo al cimiento
        // y luego no poder agregar un 2 de otro palo
        Cimiento cimiento = new Cimiento();
        Carta As = new CartaFigura('A', Palo.TREBOL);
        cimiento.agregarCarta(As);
        Carta ultCarta = cimiento.verUltimaCarta();
        assert (ultCarta == As);

        Carta DosDeCorazon = new CartaNumero(2, Palo.CORAZON);
        cimiento.agregarCarta(DosDeCorazon);

        //revisa que la ultima carta sigue siendo el As de Trebol
        ultCarta = cimiento.verUltimaCarta();
        assert (ultCarta == As);
    }
}
