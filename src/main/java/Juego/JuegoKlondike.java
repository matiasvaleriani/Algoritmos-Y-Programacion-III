package Juego;

import Estructuras.BarajaDeCartas;
import Cartas.Carta;
import Estructuras.Cimiento;
import Estructuras.PilaDeCartas;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Stack;

public class JuegoKlondike extends Juego {
    private final Stack<Carta> basura;

    public JuegoKlondike() {

        this.cimientos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.cimientos.add(new Cimiento());
        }

        this.baraja = new BarajaDeCartas();

        this.pilas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            this.pilas.add(new PilaDeCartas());
        }

        this.basura = new Stack<>();

        this.juegoGanado = false;


    }

    @Override
    public void semillaAleatoria() {
        baraja.generarCartasAleatorias();
        //repartir en pilas
        for (int i = 0; i < 7; i++) {
            PilaDeCartas p = pilas.get(i);
            for (int j = 0; j < i; j++) {
                Carta c = baraja.verUltimaCarta();
                if (!c.esTapada()) {
                    c.darVuelta();
                }
                p.agregarCarta(c);
                baraja.sacarCarta();
            }
            Carta ult = baraja.verUltimaCarta();
            if (ult.esTapada()) {
                ult.darVuelta();
            }
            p.agregarCarta(ult);
            baraja.sacarCarta();
        }
        this.actualizarPartida();
    }

    @Override
    public PilaDeCartas obtenerPila(int nroPila) {
        if (nroPila < 1 || nroPila > 7) {
            return null;
        }
        return pilas.get(nroPila - 1);
    }

    public Carta obtenerBasura() {
        if (basura.isEmpty()){
            return null;
        }
        return basura.peek();
    }

    public Stack<Carta> getBasura(){
        return basura;
    }


    public void basuraAPila(int nroPila) {
        //mueve el tope de la baraja a una pila
        if (nroPila < 1 || nroPila > 7) {
            return;
        }
        PilaDeCartas p = pilas.get(nroPila - 1);
        Carta c = basura.peek();
        if (p.puedeAgregarCarta(c)) {
            p.agregarCarta(c);
            basura.pop();
        }

        this.actualizarPartida();
    }

    public void basuraACimiento(int nroCimiento) {
        //mueve una carta de la baraja a un cimiento
        if (nroCimiento < 1 || nroCimiento > 4) {
            return;
        }
        Cimiento cimiento = cimientos.get(nroCimiento - 1);
        Carta c = basura.peek();
        if(cimiento.agregarCarta(c)) {
            basura.pop();
        }
        gano();
        this.actualizarPartida();
    }

    public void barajaABasura() {
        //mueve una carta de la baraja a la basura
        Carta c = baraja.verUltimaCarta();
        basura.add(c);
        baraja.sacarCarta();

        if (baraja.cantidad() != 0) {
            c = baraja.verUltimaCarta();
            c.darVuelta();
            baraja.sacarCarta();
            baraja.agregarCarta(c);
        }

        this.actualizarPartida();
    }

    public void reiniciarBaraja() {
        //vacia la basura y reinicia la baraja
        if (baraja.verUltimaCarta() != null) {
            return;
        }
        while (!basura.isEmpty()) {
            Carta c = basura.pop();
            c.darVuelta();
            baraja.agregarCarta(c);
        }

        this.actualizarPartida();
    }

    public void moverCimientoAPila(int nroCimiento, int nroPila) {
        //mueve una carta de un cimiento a una pila
        if (nroPila < 1 || nroPila > 8 || nroCimiento < 1 || nroCimiento > 4) {
            return;
        }
        Cimiento cimiento = cimientos.get(nroCimiento - 1);
        if (cimiento.estaVacio()){return;}
        PilaDeCartas p = pilas.get(nroPila - 1);
        Carta carta = cimiento.verUltimaCarta();
        if (p.puedeAgregarCarta(carta)) {
            System.out.println("ENTRA ACA");
            p.agregarCarta(carta);
            cimiento.quitarCarta();
        }
        gano();
        this.actualizarPartida();
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

    public JuegoKlondike deserializar(String arch) throws IOException,ClassNotFoundException{
        ObjectInputStream obj = new ObjectInputStream(new BufferedInputStream(new FileInputStream(arch)));
        JuegoKlondike juego = (JuegoKlondike) obj.readObject();
        obj.close();
        return juego;
    }

    @Override
    public void actualizarPartida(){
        try {
            this.serializar("partidaKlondike.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JuegoKlondike recuperarPartida(){
        var juego = this;
        try {
            juego = this.deserializar("partidaKlondike.txt");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return juego;
    }
}
