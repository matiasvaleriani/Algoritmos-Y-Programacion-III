package Estructuras;

import BarajaCartas.Palo;
import Cartas.Carta;
import Cartas.CartaNumero;
import org.junit.Test;


public class PilaDeCartasTest {
    @Test
    public void test1(){
        //comprueba que una pila vacia se comporta como tal
        PilaDeCartas pila = new PilaDeCartas();

        assert(pila.cant()==0);
        Carta ultCarta = pila.verUltimaCarta();
        assert (ultCarta==null);
        ultCarta = pila.quitarCarta();
        assert (ultCarta==null);
        assert(pila.cant()==0);
    }

    @Test
    public void test2(){
        //prueba agregar cartas y que estas se agreguen de forma correcta
        PilaDeCartas pila = new PilaDeCartas();

        Carta c1 = new CartaNumero(2, Palo.TREBOL);
        pila.agregarCarta(c1);
        Carta tope = pila.verUltimaCarta();
        assert (tope==c1);
        assert (pila.cant()==1);
        Carta c2 = new CartaNumero(3, Palo.TREBOL);
        pila.agregarCarta(c2);
        tope = pila.verUltimaCarta();
        assert (tope==c2);
        assert (pila.cant()==2);

        Carta ultima = pila.quitarCarta();
        assert (ultima==c2);
        assert (pila.cant()==1);
        ultima = pila.quitarCarta();
        assert (ultima==c1);
        assert (pila.cant()==0);
    }


    @Test
    public void test3(){
        //prueba el pasaje de varias cartas a la vez
        PilaDeCartas pila1 = new PilaDeCartas();
        PilaDeCartas pila2 = new PilaDeCartas();
        Carta c1 = new CartaNumero(2,Palo.CORAZON);
        Carta c2 = new CartaNumero(3,Palo.TREBOL);
        Carta c3 = new CartaNumero(4,Palo.CORAZON);
        Carta c4 = new CartaNumero(5,Palo.TREBOL);
        c1.darVuelta();
        c2.darVuelta();
        c3.darVuelta();
        c4.darVuelta();
        pila1.agregarCarta(c4);
        pila2.agregarCarta(c3);
        pila2.agregarCarta(c2);
        pila2.agregarCarta(c1);

        pila1.pasarCartas(pila2,3);
        Carta ultCarta = pila1.verUltimaCarta();
        assert (ultCarta==c1);
        pila1.quitarCarta();
        ultCarta = pila1.verUltimaCarta();
        assert (ultCarta==c2);
        pila1.quitarCarta();
        ultCarta = pila1.verUltimaCarta();
        assert (ultCarta==c3);
        pila1.quitarCarta();
        ultCarta = pila1.verUltimaCarta();
        assert (ultCarta==c4);
        pila1.quitarCarta();
        assert (pila1.cant()==0);
    }

    @Test
    public void test4(){
        //prueba el pasar varias cartas de forma erronea
        PilaDeCartas pila1 = new PilaDeCartas();
        PilaDeCartas pila2 = new PilaDeCartas();
        Carta c1 = new CartaNumero(2,Palo.CORAZON);
        Carta c2 = new CartaNumero(3,Palo.TREBOL);
        Carta c3 = new CartaNumero(4,Palo.CORAZON);
        Carta c4 = new CartaNumero(6,Palo.TREBOL);
        c1.darVuelta();
        c2.darVuelta();
        c3.darVuelta();
        c4.darVuelta();
        pila1.agregarCarta(c4);
        pila2.agregarCarta(c3);
        pila2.agregarCarta(c2);
        pila2.agregarCarta(c1);

        pila1.pasarCartas(pila2,3);//intento pasar las cartas
        //reviso que las pilas no se modificaron
        Carta ultCarta1 = pila1.quitarCarta();
        assert (ultCarta1==c4);
        Carta ultCarta2 = pila2.quitarCarta();
        assert (ultCarta2==c1);
        ultCarta2 = pila2.quitarCarta();
        assert (ultCarta2==c2);
        ultCarta2 = pila2.quitarCarta();
        assert (ultCarta2==c3);
    }
}