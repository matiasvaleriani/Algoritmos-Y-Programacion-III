package Juego;

import Estructuras.BarajaDeCartas;
import BarajaCartas.Palo;
import Cartas.Carta;
import Cartas.CartaFigura;
import Cartas.CartaNumero;
import Estructuras.Cimiento;
import Estructuras.PilaDeCartas;
import org.junit.Test;

public class JuegoKlondikeTest {

    @Test
    public void test1(){
        //prueba que un juego recien creado no tenga cartas
        JuegoKlondike juego = new JuegoKlondike();

        BarajaDeCartas baraja = juego.obtenerBaraja();
        Carta ultCartaBaraja = baraja.verUltimaCarta();//reviso que la baraja este vacia
        assert (ultCartaBaraja==null);
        for (int i=1; i<8; i++){
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

        //prueba que el juego no haya terminado
        assert(!juego.juegoTerminado());
    }}

/*
    @Test
    public void test2(){
        //pruebo que funcione pasar cartas de la baraja a una pila, la basura o a un cimiento
        JuegoKlondike juego = new JuegoKlondike();

        Cimiento cimiento = juego.obtenerCimiento(1);
        PilaDeCartas pila = juego.obtenerPila(1);
        BarajaDeCartas baraja = juego.obtenerBaraja();

        Carta cinco = new CartaNumero(5, Palo.TREBOL);
        Carta cuatro = new CartaNumero(4,Palo.CORAZON);
        Carta diez = new CartaNumero(10,Palo.TREBOL);
        Carta as = new CartaFigura('A',Palo.DIAMANTE);

        //agrega el 5 a pila
        cinco.darVuelta();
        pila.agregarCarta(cinco);
        assert(pila.verUltimaCarta() == cinco);

        diez.darVuelta();
        baraja.agregarCarta(as);
        baraja.agregarCarta(cuatro);
        baraja.agregarCarta(diez);
        //primero ingresa el 10, luego el 4 y como ultima carta esta el as
        assert(baraja.verUltimaCarta() == diez);

        //paso el 10 a basura y tengo de tope al 4
        juego.barajaABasura();
        assert(baraja.verUltimaCarta() == cuatro);
        juego.barajaABasura();


        //paso el 4 (rojo) desde la basura a la pila que tiene el 5 (negro)
        juego.basuraAPila(1);
        assert(pila.verUltimaCarta() == cuatro);
        assert (juego.obtenerBasura() == diez);

        juego.barajaABasura();
        juego.basuraACimiento(1);//paso el as al cimiento
        juego.reiniciarBaraja();//como esta vacia la baraja la reinicio y el 10 vuelve a la baraja


        //reviso que quede el estado deseado
        assert(baraja.verUltimaCarta() == diez);
        assert(pila.verUltimaCarta() == cuatro);
        assert (cimiento.verUltimaCarta() == as);

    }

    @Test
    public void test3(){
        //pruebo que funcione pasar cartas entre pilas y entre pilas y cimientos
        JuegoKlondike juego = new JuegoKlondike();
        Cimiento cimiento1 = juego.obtenerCimiento(1);
        Cimiento cimiento2 = juego.obtenerCimiento(2);
        PilaDeCartas pila1 = juego.obtenerPila(1);
        PilaDeCartas pila2 = juego.obtenerPila(2);
        PilaDeCartas pila3 = juego.obtenerPila(3);

        //cimiento1
        Carta asTrebol = new CartaFigura('A',Palo.TREBOL);
        asTrebol.darVuelta();
        cimiento1.agregarCarta(asTrebol);
        Carta dosTrebol = new CartaNumero(2,Palo.TREBOL);
        dosTrebol.darVuelta();
        cimiento1.agregarCarta(dosTrebol);

        //pila1
        Carta tresCorazon = new CartaNumero(3,Palo.CORAZON);
        tresCorazon.darVuelta();
        pila1.agregarCarta(tresCorazon);//se agrega destapada

        //pila 2
        Carta asCorazon = new CartaFigura('A',Palo.CORAZON);
        Carta cincoTrebol = new CartaNumero(5,Palo.TREBOL);
        Carta cuatroCorazon = new CartaNumero(4,Palo.CORAZON);
        pila2.agregarCarta(asCorazon);//se agrega tapada
        cincoTrebol.darVuelta();
        pila2.agregarCarta(cincoTrebol);//se agrega destapada
        cuatroCorazon.darVuelta();
        pila2.agregarCarta(cuatroCorazon);//se agrega destapada

        //pila3
        Carta seisCorazon = new CartaNumero(6,Palo.CORAZON);
        seisCorazon.darVuelta();
        pila3.agregarCarta(seisCorazon);

        juego.moverPilaAPila(2,3,2);//muevo 2 cartas (5 y 4) de la pila2 a pila3
        //se destapa el as de corazon
        juego.moverCimientoAPila(1,1);//paso el 2 de trebol del cimiento a la pila 1
        juego.moverPilaACimiento(2,2);//paso el as de corazon al 2do cimiento (vacio)

        //compruebo que el estado del juego es el correcto
        //pila1: primero el 2 de Trebol y luego el 3 de Corazones
        Carta ultPila1 = pila1.quitarCarta();
        assert (ultPila1==dosTrebol);
        ultPila1 = pila1.quitarCarta();
        assert (ultPila1==tresCorazon);

        //pila2: debe estar vacia
        Carta ultPila2 = pila2.quitarCarta();
        assert (ultPila2==null);

        //pila3: debe tener al 4 de Corazones, 5 de Trebol y al 6 de Corazones
        Carta ultPila3 = pila3.quitarCarta();
        assert (ultPila3==cuatroCorazon);
        ultPila3 = pila3.quitarCarta();
        assert (ultPila3==cincoTrebol);
        ultPila3 = pila3.quitarCarta();
        assert (ultPila3==seisCorazon);

        //cimiento1: debe tener al as de Trebol
        Carta ultCimiento1 = cimiento1.quitarCarta();
        assert (ultCimiento1==asTrebol);

        //cimiento2: debe tener al as de Corazones
        Carta ultCimiento2 = cimiento2.quitarCarta();
        assert (ultCimiento2==asCorazon);

    }

        @Test
    public void test4(){
        //prueba que comprueba bien cuando se gana
        JuegoKlondike juego = new JuegoKlondike();
        Cimiento cimiento1 = juego.obtenerCimiento(1);
        Cimiento cimiento2 = juego.obtenerCimiento(2);
        Cimiento cimiento3 = juego.obtenerCimiento(3);
        Cimiento cimiento4 = juego.obtenerCimiento(4);

        //lleno el cimiento1
        Carta asTrebol = new CartaFigura('A',Palo.TREBOL);
        asTrebol.darVuelta();
        cimiento1.agregarCarta(asTrebol);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.TREBOL);
            c.darVuelta();
            cimiento1.agregarCarta(c);

        }
        Carta jTrebol = new CartaFigura('J',Palo.TREBOL);
        jTrebol.darVuelta();
        cimiento1.agregarCarta(jTrebol);
        Carta qTrebol = new CartaFigura('Q',Palo.TREBOL);
        qTrebol.darVuelta();
        cimiento1.agregarCarta(qTrebol);
        Carta kTrebol = new CartaFigura('K',Palo.TREBOL);
        kTrebol.darVuelta();
        cimiento1.agregarCarta(kTrebol);
        assert(!juego.juegoTerminado());//reviso que el juego no este ganado aun


        //lleno el cimiento2
        Carta asCorazon = new CartaFigura('A',Palo.CORAZON);
        asCorazon.darVuelta();
        cimiento2.agregarCarta(asCorazon);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.CORAZON);
            c.darVuelta();
            cimiento2.agregarCarta(c);

        }
        Carta jCorazon = new CartaFigura('J',Palo.CORAZON);
        jCorazon.darVuelta();
        cimiento2.agregarCarta(jCorazon);
        Carta qCorazon = new CartaFigura('Q',Palo.CORAZON);
        qCorazon.darVuelta();
        cimiento2.agregarCarta(qCorazon);
        Carta kCorazon = new CartaFigura('K',Palo.CORAZON);
        kCorazon.darVuelta();
        cimiento2.agregarCarta(kCorazon);
        assert(!juego.juegoTerminado());//reviso que el juego no este ganado aun


        //lleno el cimiento3
        Carta asPica = new CartaFigura('A',Palo.PICA);
        asPica.darVuelta();
        cimiento3.agregarCarta(asPica);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.PICA);
            c.darVuelta();
            cimiento3.agregarCarta(c);

        }
        Carta jPica = new CartaFigura('J',Palo.PICA);
        jPica.darVuelta();
        cimiento3.agregarCarta(jPica);
        Carta qPica = new CartaFigura('Q',Palo.PICA);
        qPica.darVuelta();
        cimiento3.agregarCarta(qPica);
        Carta kPica = new CartaFigura('K',Palo.PICA);
        kPica.darVuelta();
        cimiento3.agregarCarta(kPica);
        assert(!juego.juegoTerminado());//reviso que el juego no este ganado aun


        //lleno el cimiento4
        Carta asDiamante = new CartaFigura('A',Palo.DIAMANTE);
        asDiamante.darVuelta();
        cimiento4.agregarCarta(asDiamante);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.DIAMANTE);
            c.darVuelta();
            cimiento4.agregarCarta(c);

        }
        Carta jDiamante = new CartaFigura('J',Palo.DIAMANTE);
        jDiamante.darVuelta();
        cimiento4.agregarCarta(jDiamante);
        Carta qDiamante = new CartaFigura('Q',Palo.DIAMANTE);
        qDiamante.darVuelta();
        cimiento4.agregarCarta(qDiamante);
        Carta kDiamante = new CartaFigura('K',Palo.DIAMANTE);
        kDiamante.darVuelta();
        cimiento4.agregarCarta(kDiamante);

        //chequeo que el juego este ganado
        assert(juego.juegoTerminado());
    }

    @Test
    public void test5(){
        //pruebo ganar desde una pila
        JuegoKlondike juego = new JuegoKlondike();
        Cimiento cimiento1 = juego.obtenerCimiento(1);
        Cimiento cimiento2 = juego.obtenerCimiento(2);
        Cimiento cimiento3 = juego.obtenerCimiento(3);
        Cimiento cimiento4 = juego.obtenerCimiento(4);
        PilaDeCartas pila1 = juego.obtenerPila(1);
        PilaDeCartas pila2 = juego.obtenerPila(2);

        //lleno el cimiento1
        Carta asTrebol = new CartaFigura('A',Palo.TREBOL);
        asTrebol.darVuelta();
        cimiento1.agregarCarta(asTrebol);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.TREBOL);
            c.darVuelta();
            cimiento1.agregarCarta(c);

        }
        Carta jTrebol = new CartaFigura('J',Palo.TREBOL);
        jTrebol.darVuelta();
        cimiento1.agregarCarta(jTrebol);
        Carta qTrebol = new CartaFigura('Q',Palo.TREBOL);
        qTrebol.darVuelta();
        cimiento1.agregarCarta(qTrebol);
        Carta kTrebol = new CartaFigura('K',Palo.TREBOL);
        kTrebol.darVuelta();
        cimiento1.agregarCarta(kTrebol);

        //lleno el cimiento2
        Carta asCorazon = new CartaFigura('A',Palo.CORAZON);
        asCorazon.darVuelta();
        cimiento2.agregarCarta(asCorazon);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.CORAZON);
            c.darVuelta();
            cimiento2.agregarCarta(c);

        }
        Carta jCorazon = new CartaFigura('J',Palo.CORAZON);
        jCorazon.darVuelta();
        cimiento2.agregarCarta(jCorazon);
        Carta qCorazon = new CartaFigura('Q',Palo.CORAZON);
        qCorazon.darVuelta();
        cimiento2.agregarCarta(qCorazon);
        Carta kCorazon = new CartaFigura('K',Palo.CORAZON);
        kCorazon.darVuelta();
        cimiento2.agregarCarta(kCorazon);

        //lleno el cimiento3
        Carta asPica = new CartaFigura('A',Palo.PICA);
        asPica.darVuelta();
        cimiento3.agregarCarta(asPica);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.PICA);
            c.darVuelta();
            cimiento3.agregarCarta(c);

        }
        Carta jPica = new CartaFigura('J',Palo.PICA);
        jPica.darVuelta();
        cimiento3.agregarCarta(jPica);

        //lleno el cimiento4
        Carta asDiamante = new CartaFigura('A',Palo.DIAMANTE);
        asDiamante.darVuelta();
        cimiento4.agregarCarta(asDiamante);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.DIAMANTE);
            c.darVuelta();
            cimiento4.agregarCarta(c);

        }
        Carta jDiamante = new CartaFigura('J',Palo.DIAMANTE);
        jDiamante.darVuelta();
        cimiento4.agregarCarta(jDiamante);
        Carta qDiamante = new CartaFigura('Q',Palo.DIAMANTE);
        qDiamante.darVuelta();
        cimiento4.agregarCarta(qDiamante);

        Carta qPica = new CartaFigura('Q',Palo.PICA);
        Carta kPica = new CartaFigura('K',Palo.PICA);
        Carta kDiamante = new CartaFigura('K',Palo.DIAMANTE);
        qPica.darVuelta();
        kPica.darVuelta();
        kDiamante.darVuelta();
        pila1.agregarCarta(kDiamante);
        pila1.agregarCarta(qPica);
        pila2.agregarCarta(kPica);

        juego.moverPilaACimiento(1,3);
        juego.moverPilaACimiento(1,4);
        juego.moverPilaACimiento(2,3);

        assert (juego.juegoTerminado());
    }

    @Test
    public void test6(){
        //pruebo ganar desde la baraja
        JuegoKlondike juego = new JuegoKlondike();
        Cimiento cimiento1 = juego.obtenerCimiento(1);
        Cimiento cimiento2 = juego.obtenerCimiento(2);
        Cimiento cimiento3 = juego.obtenerCimiento(3);
        Cimiento cimiento4 = juego.obtenerCimiento(4);
        BarajaDeCartas baraja = juego.obtenerBaraja();

        //lleno el cimiento1
        Carta asTrebol = new CartaFigura('A',Palo.TREBOL);
        asTrebol.darVuelta();
        cimiento1.agregarCarta(asTrebol);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.TREBOL);
            c.darVuelta();
            cimiento1.agregarCarta(c);

        }
        Carta jTrebol = new CartaFigura('J',Palo.TREBOL);
        jTrebol.darVuelta();
        cimiento1.agregarCarta(jTrebol);
        Carta qTrebol = new CartaFigura('Q',Palo.TREBOL);
        qTrebol.darVuelta();
        cimiento1.agregarCarta(qTrebol);
        Carta kTrebol = new CartaFigura('K',Palo.TREBOL);
        kTrebol.darVuelta();
        cimiento1.agregarCarta(kTrebol);

        //lleno el cimiento2
        Carta asCorazon = new CartaFigura('A',Palo.CORAZON);
        asCorazon.darVuelta();
        cimiento2.agregarCarta(asCorazon);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.CORAZON);
            c.darVuelta();
            cimiento2.agregarCarta(c);

        }
        Carta jCorazon = new CartaFigura('J',Palo.CORAZON);
        jCorazon.darVuelta();
        cimiento2.agregarCarta(jCorazon);
        Carta qCorazon = new CartaFigura('Q',Palo.CORAZON);
        qCorazon.darVuelta();
        cimiento2.agregarCarta(qCorazon);
        Carta kCorazon = new CartaFigura('K',Palo.CORAZON);
        kCorazon.darVuelta();
        cimiento2.agregarCarta(kCorazon);

        //lleno el cimiento3
        Carta asPica = new CartaFigura('A',Palo.PICA);
        asPica.darVuelta();
        cimiento3.agregarCarta(asPica);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.PICA);
            c.darVuelta();
            cimiento3.agregarCarta(c);

        }
        Carta jPica = new CartaFigura('J',Palo.PICA);
        jPica.darVuelta();
        cimiento3.agregarCarta(jPica);

        //lleno el cimiento4
        Carta asDiamante = new CartaFigura('A',Palo.DIAMANTE);
        asDiamante.darVuelta();
        cimiento4.agregarCarta(asDiamante);
        for (int i=2; i<11; i++){
            Carta c = new CartaNumero(i,Palo.DIAMANTE);
            c.darVuelta();
            cimiento4.agregarCarta(c);

        }
        Carta jDiamante = new CartaFigura('J',Palo.DIAMANTE);
        jDiamante.darVuelta();
        cimiento4.agregarCarta(jDiamante);
        Carta qDiamante = new CartaFigura('Q',Palo.DIAMANTE);
        qDiamante.darVuelta();
        cimiento4.agregarCarta(qDiamante);

        Carta qPica = new CartaFigura('Q',Palo.PICA);
        Carta kPica = new CartaFigura('K',Palo.PICA);
        Carta kDiamante = new CartaFigura('K',Palo.DIAMANTE);
        qPica.darVuelta();

        baraja.agregarCarta(kDiamante);
        baraja.agregarCarta(kPica);
        baraja.agregarCarta(qPica);

        juego.basuraACimiento(3);
        juego.basuraACimiento(3);
        juego.basuraACimiento(4);

        assert (juego.juegoTerminado());
    }

    @Test
    public void test7() {
        //pruebo que al sacar una carta de una pila la anterior se destape
        JuegoKlondike juego = new JuegoKlondike();
        PilaDeCartas p1 = juego.obtenerPila(1);
        PilaDeCartas p2 = juego.obtenerPila(2);
        Carta c1 = new CartaNumero(1,Palo.TREBOL);
        Carta c2 = new CartaNumero(2,Palo.CORAZON);
        Carta c3 = new CartaNumero(3,Palo.TREBOL);

        p1.agregarCarta(c3);
        c1.darVuelta();
        p1.agregarCarta(c1);
        c2.darVuelta();
        p2.agregarCarta(c2);
        p2.pasarCartas(p1,1);//paso el as de trebol a la pila 2
        Carta ultPila1 = p1.verUltimaCarta();//esta es el 3 de Trebol que antes estaba tapado

        assert(!ultPila1.esTapada());//compruebo que se destapo el 3 de Trebol
    }

    @Test
    public void test8() {
        //pruebo generar una semilla aleatoria
        JuegoKlondike juego = new JuegoKlondike();
        juego.semillaAleatoria();

        for(int i=1; i<8; i++){
            PilaDeCartas pila = juego.obtenerPila(i);
            Carta ultimaCarta = pila.verUltimaCarta();
            assert(ultimaCarta!=null);//compruebo que hay una carta
            assert(!ultimaCarta.esTapada());//compruebo que no este tapada
        }

        for (int i=1; i<4; i++){
            Cimiento cimiento = juego.obtenerCimiento(i);
            Carta ultimaCarta = cimiento.verUltimaCarta();
            assert(ultimaCarta==null);//comprueba que el cimiento esta vacia
        }

        assert(!juego.juegoTerminado());//compruebo que el juego no esta ganado
    }

    @Test
    public void test9() {
        JuegoKlondike k = new JuegoKlondike();
        Carta ocho = new CartaNumero(8,Palo.TREBOL);
        Carta nueve = new CartaNumero(9,Palo.DIAMANTE);
        PilaDeCartas p1 = k.obtenerPila(1);
        PilaDeCartas p2 = k.obtenerPila(2);
        ocho.darVuelta();
        nueve.darVuelta();
        p1.agregarCarta(ocho);
        p2.agregarCarta(nueve);
        k.actualizarPartida();

        JuegoKlondike k2 = k.recuperarPartida();
        PilaDeCartas p1_recu = k2.obtenerPila(1);
        PilaDeCartas p2_recu = k2.obtenerPila(2);
        Carta ult_p1 = p1_recu.verUltimaCarta();
        Carta ult_p2 = p2_recu.verUltimaCarta();

        assert(ult_p1.getPalo()==ocho.getPalo());
        assert(ult_p1.getValor().equals(ocho.getValor()));

        assert(ult_p2.getPalo()==nueve.getPalo());
        assert(ult_p2.getValor().equals(nueve.getValor()));

        k2.moverPilaAPila(1,2,1);
        //prueba que en el juego recuperado las cartas siguen estando y se puede seguir jugando

    }

    @Test
    public void test10(){
        //comprueba que los cimientos siguen igual luego de la deserealizacion
        JuegoKlondike k = new JuegoKlondike();
        Cimiento c1 = k.obtenerCimiento(1);
        Cimiento c2 = k.obtenerCimiento(2);
        Carta unoCorazon = new CartaFigura('A',Palo.CORAZON);
        Carta dosCorazon = new CartaNumero(2,Palo.CORAZON);
        Carta unoTrebol = new CartaFigura('A',Palo.TREBOL);
        c1.agregarCarta(unoTrebol);
        c2.agregarCarta(unoCorazon);
        c2.agregarCarta(dosCorazon);
        k.actualizarPartida();

        JuegoKlondike k2 = k.recuperarPartida();
        Cimiento c1_recup = k2.obtenerCimiento(1);
        Cimiento c2_recup = k2.obtenerCimiento(2);

        while(!c1.estaVacio()){
            Carta c_original = c1.verUltimaCarta();
            Carta c_recuperada = c1_recup.verUltimaCarta();
            assert(c_recuperada.getValor().equals(c_original.getValor()));
            assert(c_recuperada.getPalo()==c_original.getPalo());
            c1.quitarCarta();
            c1_recup.quitarCarta();
        }

        while(!c2.estaVacio()){
            Carta c_original = c2.verUltimaCarta();
            Carta c_recuperada = c2_recup.verUltimaCarta();
            assert(c_recuperada.getValor().equals(c_original.getValor()));
            assert(c_recuperada.getPalo()==c_original.getPalo());
            c2.quitarCarta();
            c2_recup.quitarCarta();
        }
    }

    @Test
    public void test11(){
        //Prueba que se serializa y se recupera una partida
        //aleatoria de forma correcta
        JuegoKlondike k = new JuegoKlondike();
        k.semillaAleatoria();
        JuegoKlondike k2 = k.recuperarPartida();

        for(int i=1; i<8; i++){
            PilaDeCartas p_orig = k.obtenerPila(i);
            PilaDeCartas p_recup = k2.obtenerPila(i);
            Carta ult_orig = p_orig.verUltimaCarta();
            Carta ult_recup = p_recup.verUltimaCarta();
            assert(ult_orig.getPalo()==ult_recup.getPalo());
            assert(ult_orig.getValor().equals(ult_recup.getValor()));
        }

        for(int i=1; i<5; i++){
            Cimiento c_orig = k.obtenerCimiento(i);
            Cimiento c_recup = k2.obtenerCimiento(i);
            assert(c_orig.estaVacio());
            assert(c_recup.estaVacio());
        }

        BarajaDeCartas baraja_orig = k.obtenerBaraja();
        BarajaDeCartas baraja_recup = k2.obtenerBaraja();

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
*/