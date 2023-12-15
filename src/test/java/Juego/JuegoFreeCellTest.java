package Juego;

import BarajaCartas.*;
import Estructuras.*;
import Cartas.*;
import org.junit.Test;

public class JuegoFreeCellTest {
    @Test
    public void test1(){
        //prueba que un juego recien creado no tenga cartas
        JuegoFreeCell juego = new JuegoFreeCell();

        BarajaDeCartas baraja = juego.obtenerBaraja();
        Carta ultCartaBaraja = baraja.verUltimaCarta();//reviso que la baraja este vacia
        assert (ultCartaBaraja==null);
        for (int i=1; i<9; i++){
            // revisa que las pilas esten vacias
            PilaDeCartas pila = juego.obtenerPila(i);
            Carta ultCarta = pila.verUltimaCarta();
            assert (ultCarta == null);
        }
        for (int i=1; i<5; i++){
            // revisa que los cimientos esten vacios
            Cimiento cimiento = juego.obtenerCimiento(i);
            Carta ultCarta = cimiento.verUltimaCarta();
            assert (ultCarta==null);
        }

        for (int i=1; i<5; i++){
            // revisa que los casilleros esten vacios
            Casillero casillero = juego.obtenerCasillero(i);
            Carta ultCarta = casillero.verCarta();
            assert (ultCarta==null);
        }

        //prueba que el juego no haya terminado
        assert(!juego.juegoTerminado());
    }

    @Test
    public void test2(){
        //pruebo que funcione pasar una carta de una pila a otra pila
        JuegoFreeCell juego = new JuegoFreeCell();

        Carta c = new CartaFigura('A', Palo.CORAZON);
        c.darVuelta();
        Carta c2 = new CartaNumero(2, Palo.CORAZON);
        c2.darVuelta();
        Carta c3 = new CartaNumero(9, Palo.DIAMANTE);
        c3.darVuelta();
        Carta c4 = new CartaNumero(10, Palo.PICA);
        c4.darVuelta();

        juego.obtenerPila(1).agregarCarta(c);
        juego.obtenerPila(1).agregarCarta(c2);
        juego.obtenerPila(1).agregarCarta(c3);
        juego.obtenerPila(2).agregarCarta(c4);
        //veo que se hayan agregado correcamente
        assert (juego.obtenerPila(1).verUltimaCarta() == c3);
        assert (juego.obtenerPila(2).verUltimaCarta() == c4);

        //intento pasar el 9 de Diamente de la pila 1 a la pila 2 que tiene el 10 de Pica");
        juego.moverPilaAPila(1,2,1);
        assert (juego.obtenerPila(2).verUltimaCarta() == c3);
        assert (juego.obtenerPila(1).verUltimaCarta() == c2);
    }

    @Test
    public void test3(){
        //pruebo que no se puede pasar una carta de una pila a la otra si no son de numero escalonado (decrecientemente) y palo diferente

        JuegoFreeCell juego = new JuegoFreeCell();

        Carta c = new CartaFigura('A', Palo.CORAZON);
        c.darVuelta();
        Carta c2 = new CartaNumero(2, Palo.CORAZON);
        c2.darVuelta();
        Carta c3 = new CartaNumero(10, Palo.PICA);
        c3.darVuelta();

        juego.obtenerPila(1).agregarCarta(c);
        juego.obtenerPila(1).agregarCarta(c2);
        juego.obtenerPila(2).agregarCarta(c3);

        //veo que se hayan agregado correcamente
        assert (juego.obtenerPila(1).verUltimaCarta() == c2);
        assert (juego.obtenerPila(2).verUltimaCarta() == c3);

        //intento pasar el 2 de Corazon de la pila 1 a la pila 2 que tiene el 10 de Pica");
        juego.moverPilaAPila(1,2,1);
        assert (juego.obtenerPila(2).verUltimaCarta() == c3);
        assert (juego.obtenerPila(1).verUltimaCarta() == c2);
    }

    @Test
    public void test4(){
        //pruebo pasar varias cartas de una pila a la otra siempre y cuando estas seleccionadas a pasar
        // sean escalonadas (decrecientemente) y palo diferente

        JuegoFreeCell juego = new JuegoFreeCell();

        Carta c = new CartaFigura('A', Palo.CORAZON);
        c.darVuelta();
        Carta c2 = new CartaNumero(2, Palo.CORAZON);
        c2.darVuelta();
        Carta c3 = new CartaNumero(6, Palo.CORAZON);
        c3.darVuelta();
        Carta c4 = new CartaNumero(9, Palo.DIAMANTE);
        c4.darVuelta();
        Carta c5 = new CartaNumero(8, Palo.PICA);
        c5.darVuelta();
        Carta c6 = new CartaNumero(7, Palo.CORAZON);
        c6.darVuelta();
        Carta c7 = new CartaNumero(6, Palo.PICA);
        c7.darVuelta();
        Carta c8 = new CartaNumero(10, Palo.PICA);
        c8.darVuelta();


        juego.obtenerPila(1).agregarCarta(c);
        juego.obtenerPila(1).agregarCarta(c2);
        juego.obtenerPila(1).agregarCarta(c3);
        juego.obtenerPila(1).agregarCarta(c4);
        juego.obtenerPila(1).agregarCarta(c5);
        juego.obtenerPila(1).agregarCarta(c6);
        juego.obtenerPila(1).agregarCarta(c7);
        juego.obtenerPila(2).agregarCarta(c8);

        //veo que se hayan agregado correcamente
        assert (juego.obtenerPila(1).verUltimaCarta() == c7);
        assert (juego.obtenerPila(2).verUltimaCarta() == c8);

        //intento mover el 9 de Diamante, 8 de Pica, 7 de Corazon y 6 de Pica a la pila 2 que tiene el 10 de Pica");
        juego.moverPilaAPila(1,2,4);
        //Verifico las ultimas posiciones de cada pila
        assert (juego.obtenerPila(1).verUltimaCarta() == c3);
        assert (juego.obtenerPila(2).verUltimaCarta() == c7);

        //Veo que se hayan agregado las 4 cartas en la Pila 2, ademas de que en la pos 0 este el 10 de Pica
        assert(juego.obtenerPila(2).verCartaPos(0) == c8);
        assert(juego.obtenerPila(2).verCartaPos(1) == c4);
        assert(juego.obtenerPila(2).verCartaPos(2) == c5);
        assert(juego.obtenerPila(2).verCartaPos(3) == c6);
        assert(juego.obtenerPila(2).verCartaPos(4) == c7);
    }

    @Test
    public void test5(){
        //pruebo que no se pueden pasar varias cartas de una pila a la otra si no se cumplen las condiciones de orden y palos

        JuegoFreeCell juego = new JuegoFreeCell();

        Carta c = new CartaFigura('A', Palo.CORAZON);
        c.darVuelta();
        Carta c2 = new CartaNumero(2, Palo.CORAZON);
        c2.darVuelta();
        Carta c3 = new CartaNumero(6, Palo.CORAZON);
        c3.darVuelta();
        Carta c4 = new CartaNumero(9, Palo.DIAMANTE);
        c4.darVuelta();
        Carta c5 = new CartaNumero(8, Palo.PICA);
        c5.darVuelta();
        Carta c6 = new CartaNumero(7, Palo.CORAZON);
        c6.darVuelta();
        Carta c7 = new CartaNumero(6, Palo.PICA);
        c7.darVuelta();
        Carta c8 = new CartaNumero(2, Palo.PICA);
        c8.darVuelta();


        juego.obtenerPila(1).agregarCarta(c);
        juego.obtenerPila(1).agregarCarta(c2);
        juego.obtenerPila(1).agregarCarta(c3);
        juego.obtenerPila(1).agregarCarta(c4);
        juego.obtenerPila(1).agregarCarta(c5);
        juego.obtenerPila(1).agregarCarta(c6);
        juego.obtenerPila(1).agregarCarta(c7);
        juego.obtenerPila(2).agregarCarta(c8);

        //veo que se hayan agregado correcamente
        assert (juego.obtenerPila(1).verUltimaCarta() == c7);
        assert (juego.obtenerPila(2).verUltimaCarta() == c8);

        //intento mover el 9 de Diamante, 8 de Pica, 7 de Corazon y 6 de Pica a la pila 2 que tiene el 10 de Pica");
        juego.moverPilaAPila(1,2,4);
        //Verifico que no hubo cambios luego de intentar mover entre pilas
        assert (juego.obtenerPila(1).verUltimaCarta() == c7);
        assert (juego.obtenerPila(2).verUltimaCarta() == c8);
    }

    @Test
    public void test6(){
        //pruebo agregar cartas al cimiento

        JuegoFreeCell juego = new JuegoFreeCell();

        //agrego el A de Trebol a la Pila 1 y luego lo agrego a un cimiento

        Carta asTrebol = new CartaFigura('A', Palo.TREBOL);
        asTrebol.darVuelta();
        juego.obtenerPila(1).agregarCarta(asTrebol);
        assert (juego.obtenerPila(1).verUltimaCarta() == asTrebol);
        assert (juego.obtenerCimiento(1).verUltimaCarta() == null);

        juego.moverPilaACimiento(1,1);
        //veo que se haya agregado correcamente al cimiento y que la pila 1 quede vacia
        assert (juego.obtenerCimiento(1).verUltimaCarta() == asTrebol);
        assert (juego.obtenerPila(1).verUltimaCarta() == null);
    }

    @Test
    public void test7(){
        //intento mover de un casillero a una pila vacia, y de otro casillero a una pila con alguna carta.

        JuegoFreeCell juego = new JuegoFreeCell();

        Carta c = new CartaNumero(6, Palo.CORAZON);
        c.darVuelta();
        Carta c2 = new CartaNumero(8, Palo.PICA);
        c2.darVuelta();
        Carta c3 = new CartaNumero(5, Palo.TREBOL);
        c3.darVuelta();

        juego.obtenerPila(2).agregarCarta(c);
        juego.obtenerCasillero(1).colocarCarta(c2);
        juego.obtenerCasillero(2).colocarCarta(c3);

        //Verifico que se haya agregado a la pila 2 y a los dos casilleros
        // Ademas la pila 1 debe estar vacia.

        assert (juego.obtenerPila(1).verUltimaCarta() == null);
        assert (juego.obtenerPila(2).verUltimaCarta() == c);
        assert (juego.obtenerCasillero(1).verCarta() == c2);
        assert (juego.obtenerCasillero(2).verCarta() == c3);

        juego.moverCasilleroAPila(1,1);
        juego.moverCasilleroAPila(2,2);

        //verifico resultados
        assert (juego.obtenerPila(1).verUltimaCarta() == c2);
        assert (juego.obtenerPila(2).verUltimaCarta() == c3);
        assert (juego.obtenerCasillero(1).verCarta() == null);
        assert (juego.obtenerCasillero(2).verCarta() == null);
    }

    @Test
    public void test8(){
        //intento mover de un casillero a una cimiento vacio, y de un casillero a un cimiento no vacio

        JuegoFreeCell juego = new JuegoFreeCell();

        Carta asTrebol = new CartaFigura('A', Palo.TREBOL);
        asTrebol.darVuelta();

        Carta asPica = new CartaFigura('A', Palo.PICA);
        asTrebol.darVuelta();

        Carta c = new CartaNumero(6, Palo.CORAZON);
        c.darVuelta();

        Carta c2 = new CartaNumero(2, Palo.PICA);
        c2.darVuelta();

        juego.obtenerCimiento(3).agregarCarta(asPica);
        juego.obtenerCasillero(1).colocarCarta(asTrebol);
        juego.obtenerCasillero(2).colocarCarta(c);
        juego.obtenerCasillero(3).colocarCarta(c2);

        //Verifico que se haya agregado al cimiento.

        assert (juego.obtenerCimiento(3).verUltimaCarta() == asPica);
        assert (juego.obtenerCasillero(1).verCarta() == asTrebol);
        assert (juego.obtenerCasillero(2).verCarta() == c);
        assert (juego.obtenerCasillero(3).verCarta() == c2);

        juego.moverCasilleroACimiento(1,1);
        juego.moverCasilleroACimiento(2,2);
        juego.moverCasilleroACimiento(3,3);

        //verifico resultados
        assert (juego.obtenerCimiento(1).verUltimaCarta() == asTrebol);
        assert (juego.obtenerCimiento(2).verUltimaCarta() == null);
        assert (juego.obtenerCimiento(3).verUltimaCarta() == c2);
        assert (juego.obtenerCasillero(1).verCarta() == null);
        assert (juego.obtenerCasillero(2).verCarta() == c);
        assert (juego.obtenerCasillero(3).verCarta() == null);
    }

    @Test
    public void test9(){
        //Intento mover de una pila a un casillero y de una pila vacia un casillero

        JuegoFreeCell juego = new JuegoFreeCell();

        Carta c = new CartaFigura('A', Palo.CORAZON);
        c.darVuelta();
        Carta c2 = new CartaNumero(2, Palo.CORAZON);
        c2.darVuelta();
        Carta c3 = new CartaNumero(6, Palo.CORAZON);
        c3.darVuelta();
        Carta c4 = new CartaNumero(9, Palo.DIAMANTE);
        c4.darVuelta();

        juego.obtenerPila(1).agregarCarta(c);
        juego.obtenerPila(1).agregarCarta(c2);
        juego.obtenerPila(1).agregarCarta(c3);
        juego.obtenerPila(1).agregarCarta(c4);

        assert (juego.obtenerPila(1).verUltimaCarta() == c4);
        assert (juego.obtenerPila(2).verUltimaCarta() == null);
        assert (juego.obtenerCasillero(1).verCarta() == null);
        assert (juego.obtenerCasillero(2).verCarta() == null);

        juego.moverPilaACasillero(1,1);
        juego.moverPilaACasillero(2,2);

        //verifico resultados
        assert (juego.obtenerPila(1).verUltimaCarta() == c3);
        assert (juego.obtenerPila(2).verUltimaCarta() == null);
        assert (juego.obtenerCasillero(1).verCarta() == c4);
        assert (juego.obtenerCasillero(2).verCarta() == null);
    }

    @Test
    public void test10(){
        //intento mover a casilleros ocupados, y mover entre casilleros

        JuegoFreeCell juego = new JuegoFreeCell();

        Carta c = new CartaFigura('A', Palo.CORAZON);
        c.darVuelta();
        Carta c2 = new CartaNumero(2, Palo.CORAZON);
        c2.darVuelta();
        Carta c3 = new CartaNumero(9, Palo.CORAZON);
        c3.darVuelta();

        juego.obtenerPila(1).agregarCarta(c);
        juego.obtenerCasillero(1).colocarCarta(c2);
        juego.obtenerCasillero(3).colocarCarta(c3);

        assert (juego.obtenerPila(1).verUltimaCarta() == c);
        assert (juego.obtenerCasillero(1).verCarta() == c2);
        assert (juego.obtenerCasillero(2).verCarta() == null);
        assert (juego.obtenerCasillero(3).verCarta() == c3);

        juego.moverPilaACasillero(1,1);
        assert (juego.obtenerPila(1).verUltimaCarta() == c);
        assert (juego.obtenerCasillero(1).verCarta() == c2);

        juego.moverCasilleroACasillero(1,2);
        assert (juego.obtenerCasillero(1).verCarta() == null);
        assert (juego.obtenerCasillero(2).verCarta() == c2);

        juego.moverCasilleroACasillero(2,3);
        assert (juego.obtenerCasillero(2).verCarta() == c2);
        assert (juego.obtenerCasillero(3).verCarta() == c3);
    }

    @Test
    public void test11(){
        //prueba que las pilas se serializan y
        //desearilazan correctamente
        JuegoFreeCell f = new JuegoFreeCell();
        Carta ocho = new CartaNumero(8,Palo.TREBOL);
        Carta nueve = new CartaNumero(9,Palo.DIAMANTE);
        PilaDeCartas p1 = f.obtenerPila(1);
        PilaDeCartas p2 = f.obtenerPila(2);
        ocho.darVuelta();
        nueve.darVuelta();
        p1.agregarCarta(ocho);
        p2.agregarCarta(nueve);
        f.actualizarPartida();

        JuegoFreeCell f2 = f.recuperarPartida();
        PilaDeCartas p1_recu = f2.obtenerPila(1);
        PilaDeCartas p2_recu = f2.obtenerPila(2);
        Carta ult_p1 = p1_recu.verUltimaCarta();
        Carta ult_p2 = p2_recu.verUltimaCarta();

        assert(ult_p1.getPalo()==ocho.getPalo());
        assert(ult_p1.getValor().equals(ocho.getValor()));

        assert(ult_p2.getPalo()==nueve.getPalo());
        assert(ult_p2.getValor().equals(nueve.getValor()));
    }

    @Test
    public void test12() {
        //comprueba que los cimientos siguen igual luego de la deserealizacion
        JuegoFreeCell f = new JuegoFreeCell();
        Cimiento c1 = f.obtenerCimiento(1);
        Cimiento c2 = f.obtenerCimiento(2);
        Carta unoCorazon = new CartaFigura('A', Palo.CORAZON);
        Carta dosCorazon = new CartaNumero(2, Palo.CORAZON);
        Carta unoTrebol = new CartaFigura('A', Palo.TREBOL);
        c1.agregarCarta(unoTrebol);
        c2.agregarCarta(unoCorazon);
        c2.agregarCarta(dosCorazon);
        f.actualizarPartida();

        JuegoFreeCell f2 = f.recuperarPartida();
        Cimiento c1_recup = f2.obtenerCimiento(1);
        Cimiento c2_recup = f2.obtenerCimiento(2);

        while (!c1.estaVacio()) {
            Carta c_original = c1.verUltimaCarta();
            Carta c_recuperada = c1_recup.verUltimaCarta();
            assert (c_recuperada.getValor().equals(c_original.getValor()));
            assert (c_recuperada.getPalo() == c_original.getPalo());
            c1.quitarCarta();
            c1_recup.quitarCarta();
        }

        while (!c2.estaVacio()) {
            Carta c_original = c2.verUltimaCarta();
            Carta c_recuperada = c2_recup.verUltimaCarta();
            assert (c_recuperada.getValor().equals(c_original.getValor()));
            assert (c_recuperada.getPalo() == c_original.getPalo());
            c2.quitarCarta();
            c2_recup.quitarCarta();
        }
    }

    @Test
    public void test13() {
        //prueba que los casilleros se serializan
        //y deserealizan correctamente
        JuegoFreeCell f = new JuegoFreeCell();
        Casillero c1 = f.obtenerCasillero(1);
        Casillero c2 = f.obtenerCasillero(2);
        Casillero c3 = f.obtenerCasillero(3);
        Casillero c4 = f.obtenerCasillero(4);
        Carta carta1 = new CartaNumero(3,Palo.CORAZON);
        Carta carta2 = new CartaNumero(6,Palo.CORAZON);
        Carta carta3 = new CartaNumero(4,Palo.DIAMANTE);
        Carta carta4 = new CartaNumero(8,Palo.TREBOL);
        c1.colocarCarta(carta1);
        c2.colocarCarta(carta2);
        c3.colocarCarta(carta3);
        c4.colocarCarta(carta4);
        f.actualizarPartida();

        JuegoFreeCell f2 = f.recuperarPartida();

        for(int i=1; i<5; i++){
            Casillero cas_orig = f.obtenerCasillero(i);
            Casillero cas_recup = f2.obtenerCasillero(i);
            Carta carta_orig = cas_orig.verCarta();
            Carta carta_recup = cas_recup.verCarta();

            assert (carta_orig.getPalo()==carta_recup.getPalo());
            assert (carta_orig.getValor().equals(carta_recup.getValor()));
        }


    }

    @Test
    public void test14(){
        //Prueba que se serializa y se recupera una partida
        //aleatoria de forma correcta
        JuegoFreeCell f = new JuegoFreeCell();
        f.semillaAleatoria();
        JuegoFreeCell f2 = f.recuperarPartida();

        for(int i=1; i<8; i++){
            PilaDeCartas p_orig = f.obtenerPila(i);
            PilaDeCartas p_recup = f2.obtenerPila(i);
            Carta ult_orig = p_orig.verUltimaCarta();
            Carta ult_recup = p_recup.verUltimaCarta();
            assert(ult_orig.getPalo()==ult_recup.getPalo());
            assert(ult_orig.getValor().equals(ult_recup.getValor()));
        }

        for(int i=1; i<5; i++){
            Cimiento c_orig = f.obtenerCimiento(i);
            Cimiento c_recup = f2.obtenerCimiento(i);
            assert(c_orig.estaVacio());
            assert(c_recup.estaVacio());
        }

        BarajaDeCartas baraja_orig = f.obtenerBaraja();
        BarajaDeCartas baraja_recup = f2.obtenerBaraja();

        while(baraja_recup.cantidad()!=0){
            Carta c_orig = baraja_orig.verUltimaCarta();
            Carta c_recup = baraja_recup.verUltimaCarta();

            assert(c_orig.getValor().equals(c_recup.getValor()));
            assert(c_orig.getPalo()==c_recup.getPalo());

            baraja_orig.sacarCarta();
            baraja_recup.sacarCarta();
        }
    }
}