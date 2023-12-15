package Juego;


import Estructuras.*;
import BarajaCartas.*;
import Cartas.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class JuegoFreeCell extends Juego {
    private final ArrayList<Casillero> casilleros;

    public JuegoFreeCell() {

        this.cimientos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.cimientos.add(new Cimiento());
        }

        this.baraja = new BarajaDeCartas();

        this.pilas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            this.pilas.add(new PilaDeCartas());
        }

        this.casilleros = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.casilleros.add(new Casillero());
        }

        this.juegoGanado = false;
    }

    @Override
    public void semillaAleatoria() {
        baraja.generarCartasAleatorias();
        //repartir en pilas
        for (int i = 0; i < 8; i++) {
            PilaDeCartas p = pilas.get(i);
            int numCartas = (i < 4) ? 7 : 6;
            for (int j = 0; j < numCartas; j++) {
                Carta c = baraja.verUltimaCarta();
                if (c.esTapada()) {
                    c.darVuelta();
                }
                p.agregarCarta(c);
                baraja.sacarCarta();
            }
        }
        this.actualizarPartida();
    }


    public Casillero obtenerCasillero(int nroCasillero) {
        if (nroCasillero < 1 || nroCasillero > 4) {
            return null;
        }
        return casilleros.get(nroCasillero - 1);
    }

    @Override
    public PilaDeCartas obtenerPila(int nroPila) {
        if (nroPila < 1 || nroPila > 8) {
            return null;
        }
        return pilas.get(nroPila - 1);
    }

    private boolean esValorSiguienteEnPila(Carta tope, Carta nueva) {
        String valorTope = tope.getValor();
        String valorNueva = nueva.getValor();
        int valorTopeInt = Integer.parseInt(valorTope);
        int valorNuevaInt = Integer.parseInt(valorNueva);
        return valorTopeInt - valorNuevaInt == 1;
    }

    private boolean esColorDiferenteEnPila(Carta cartaNueva, Carta cartaTope) {
        Palo paloCartaNueva = cartaNueva.getPalo();
        Palo paloCartaTope = cartaTope.getPalo();
        Color colorCartaNueva = paloCartaNueva.getColor();
        Color colorCartaTope = paloCartaTope.getColor();
        return colorCartaNueva != colorCartaTope;
    }

    @Override
    public void moverPilaAPila(int nroOrigen, int nroDest, int cant) {
        //mueve una/s carta/s de una pila a otra
        //mueve la cantidad de cartas
        //deseada de una pila a otra
        if (1 > nroOrigen || 8 < nroOrigen || 1 > nroDest || 8 < nroDest) {
            return;
        }
        PilaDeCartas p1 = pilas.get(nroOrigen - 1);
        PilaDeCartas p2 = pilas.get(nroDest - 1);
        p2.pasarCartas(p1, cant);

        this.actualizarPartida();
    }

    public void verPila(int i){
        PilaDeCartas p = pilas.get(i-1);
        for(Carta c : p.obtenerPila()){
            System.out.print(c.getRepresentacion()+"  ");
        }
    }

    public void moverCasilleroAPila(int nroCasillero, int nroPila) {
        if (nroPila < 1 || nroPila > 8 || nroCasillero < 1 || nroCasillero > 4) {
            return;
        }
        Casillero c = casilleros.get(nroCasillero - 1);
        PilaDeCartas p = pilas.get(nroPila - 1);

        if (!c.estaVacio()) {
            Carta carta = c.verCarta();
            if ((p.verUltimaCarta() == null) || p.puedeAgregarCarta(carta)) {
                p.agregarCarta(carta);
                c.quitarCarta();
            }
        }
        gano();
        this.actualizarPartida();
    }

    public void moverCasilleroACasillero(int nroCasilleroInicio, int nroCasilleroDestino) {
        if (nroCasilleroInicio < 1 || nroCasilleroInicio > 4 || nroCasilleroDestino < 1 || nroCasilleroDestino > 4) {
            return;
        }
        Casillero c1 = casilleros.get(nroCasilleroInicio - 1);
        Casillero c2 = casilleros.get(nroCasilleroDestino - 1);

        if (!c1.estaVacio() && c2.estaVacio()) {
            Carta carta = c1.verCarta();
            c2.colocarCarta(carta);
            c1.quitarCarta();
        }

        this.actualizarPartida();
    }

    public void moverCasilleroACimiento(int nroCasillero, int nroCimiento) {
        //agrega una carta de un casillero a un cimiento
        if (nroCasillero < 1 || nroCasillero > 4 || nroCimiento < 1 || nroCimiento > 4) {
            return;
        }
        Casillero c = casilleros.get(nroCasillero - 1);
        Cimiento cimiento = cimientos.get(nroCimiento - 1);
        Carta carta = c.verCarta();
        if (cimiento.puedeAgregarCarta(carta)) {
            cimiento.agregarCarta(carta);
            c.quitarCarta();
        }
        gano();
        this.actualizarPartida();
    }

    public void moverPilaACasillero(int nroPila, int nroCasillero) {
        //agrega una carta de una pila a un casillero
        if (nroPila < 1 || nroPila > 8 || nroCasillero < 1 || nroCasillero > 4) {
            return;
        }
        PilaDeCartas pila = pilas.get(nroPila - 1);
        Casillero c = casilleros.get(nroCasillero - 1);
        Carta carta = pila.verUltimaCarta();
        if (c.estaVacio()) {
            c.colocarCarta(carta);
            pila.quitarCarta();
        }
        gano();
        this.actualizarPartida();
    }

    public JuegoFreeCell deserializar(String arch) throws IOException, ClassNotFoundException {
        ObjectInputStream obj = new ObjectInputStream(new BufferedInputStream(new FileInputStream(arch)));
        JuegoFreeCell juego = (JuegoFreeCell) obj.readObject();
        obj.close();
        return juego;
    }

    @Override
    public void actualizarPartida() {
        try {
            this.serializar("partidaFreeCell.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JuegoFreeCell recuperarPartida() {
        var juego = this;
        try {
            juego = this.deserializar("partidaFreeCell.txt");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return juego;
    }



}