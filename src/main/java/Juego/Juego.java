package Juego;

import java.io.Serializable;
import java.util.ArrayList;
import Estructuras.*;
import Cartas.*;
import java.io.*;

public abstract class Juego implements Serializable {
    protected ArrayList<Cimiento> cimientos;
    protected BarajaDeCartas baraja;
    protected ArrayList<PilaDeCartas> pilas;
    protected Boolean juegoGanado;

    public abstract void semillaAleatoria();

    public BarajaDeCartas obtenerBaraja() {
        return baraja;
    }

    public void gano() {
        for (Cimiento cimiento : cimientos) {
            if (!cimiento.estaCompleto()) {
                return;
            }
        }
        juegoGanado = true;
        this.actualizarPartida();
    }

    public boolean juegoTerminado() {
        gano();
        this.actualizarPartida();
        return juegoGanado;
    }

    public Cimiento obtenerCimiento(int nroCimiento) {
        if (nroCimiento < 1 || nroCimiento > 4) {
            return null;
        }
        return cimientos.get(nroCimiento - 1);
    }

    public abstract PilaDeCartas obtenerPila(int nroPila);

    public abstract void moverPilaAPila(int nroOrigen, int nroDest, int cant);

    public void moverPilaACimiento(int nroPila, int nroCimiento) {
        //agrega una carta de una pila a un cimiento
        if (nroPila < 1 || nroPila > 8 || nroCimiento < 1 || nroCimiento > 4) {
            return;
        }
        PilaDeCartas pila = pilas.get(nroPila - 1);
        Cimiento cimiento = cimientos.get(nroCimiento - 1);
        Carta c = pila.verUltimaCarta();
        if (cimiento.puedeAgregarCarta(c)) {
            cimiento.agregarCarta(c);
            pila.quitarCarta();
            if (pila.cant()!=0 && pila.verUltimaCarta().esTapada()){
                pila.verUltimaCarta().darVuelta();
            }
        }
        gano();
        this.actualizarPartida();
}

    public void serializar(String arch) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(arch)));
        obj.writeObject(this);
        obj.close();
    }

    public abstract void actualizarPartida();

    public abstract Juego recuperarPartida();
}