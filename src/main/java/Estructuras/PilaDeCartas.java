package Estructuras;

import BarajaCartas.Color;
import Cartas.Carta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class PilaDeCartas implements Serializable {

    private final ArrayList<Carta> cartas;

    public PilaDeCartas() {
        cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        if(carta.esTapada()){
            carta.darVuelta();
        }
        cartas.add(carta);
    }

    public int cant() {
        return cartas.size();
    }

    public void pasarCartas(PilaDeCartas origen, int cantidad) {

        //pasas cartas de un array a otro
        if (cantidad <= 0 || origen.cant() < cantidad || !sePuedenPasar(origen,cantidad)) {
            return;
        }
        if (this.cartas.isEmpty()){
            if (!origen.obtenerPila().get(origen.cant()-cantidad).getValor().equals("13")) {
                return;
            }
            if (cantidad==1) {
                if (origen.verUltimaCarta().getValor().equals("13")) {
                    this.cartas.add(origen.verUltimaCarta());
                    origen.quitarCarta();
                }
            }else{

                ArrayList<Carta> cartasApasar = origen.obtenerPila();
                for (int pos= origen.cartas.size()-(cantidad); pos<cartasApasar.size()-2; pos++){
                    System.out.println("ENTRA ACA");
                    Carta act = cartasApasar.get(pos);
                    Carta sig = cartasApasar.get(pos+1);
                    System.out.println("ACT: "+act+" SIG: "+sig);
                    if (act.getPalo().getColor().equals(sig.getPalo().getColor()) || !esValorSiguiente(act,sig)){
                        return;
                    }
                }
                for(int i = 0; i<cantidad;i++){
                    cartas.add(cartasApasar.get(cartasApasar.size()-cantidad+i));
                }
                for (int i = 0; i<cantidad;i++){
                    origen.quitarCarta();
                }
            }
            return;
        }
        int ult = posUltimo(origen.cartas);
        int posPrim = (ult - cantidad + 1);
        Carta prim = origen.cartas.get(posPrim);
        if (prim.esTapada()) {
            return;
        }
        Carta tope = cartas.get(posUltimo(cartas));
        if (!esColorDiferente(tope, prim) || !esValorSiguiente(tope, prim)) {
            return;
        }
        Stack<Carta> pAux = new Stack<>();
        for (int i = 0; i < cantidad; i++) {
            Carta c = origen.quitarCarta();
            pAux.push(c);
        }
        while (!pAux.isEmpty()) {
            Carta c = pAux.pop();
            cartas.add(c);
        }
        if (origen.cant() != 0 && origen.verUltimaCarta().esTapada()) {
            Carta c = origen.quitarCarta();
            c.darVuelta();
            origen.agregarCarta(c);
        }
    }

    public boolean sePuedenPasar(PilaDeCartas origen, int cantidad){
        Stack<Carta> p_aux = new Stack<>();
        if(cartas.isEmpty() && !origen.verUltimaCarta().esTapada()){
            return true;
        }
        for (int i=0 ; i<cantidad; i++){
            Carta c = origen.quitarCarta();
            if (!p_aux.isEmpty()){
                Carta tope = p_aux.peek();
                if (!esColorDiferente(tope,c) || !esValorSiguiente(c, tope)){
                    origen.agregarCarta(c);
                    while (!p_aux.isEmpty()){
                        origen.agregarCarta(p_aux.pop());
                    }
                    return false;
                }
            }
            p_aux.add(c);
        }
        while (!p_aux.isEmpty()){
            origen.agregarCarta(p_aux.pop());
        }
        return true;
    }


    private boolean ultCartaTapada() {
        Carta c = quitarCarta();
        if (c.esTapada()) {
            agregarCarta(c);
            return true;
        }
        return false;
    }


    private boolean esValorSiguiente(Carta tope, Carta prim) {
        String valorTope = tope.getValor();
        String valorPrim = prim.getValor();
        int a = Integer.parseInt(valorTope);
        int b = Integer.parseInt(valorPrim);
        int dif = a - b;
        return dif == 1;
    }

    public int posUltimo(ArrayList<Carta> p) {
        return p.size() - 1;
    }

    public Carta quitarCarta() {
        if (!cartas.isEmpty()) {
            int ult = posUltimo(cartas);
            return cartas.remove(ult);
        } else {
            return null; // La pila de cartas está vacía
        }
    }

    public boolean puedeAgregarCarta(Carta carta) {
        if (cartas.isEmpty()) {
            if (carta.getValor().equals("13")){
                return true;
            }
            // si esta vacia , solo se puede agregar un Rey
            return carta.getValor().equals("K");
        } else {
            int p = posUltimo(cartas);
            Carta cartaSuperior = cartas.get(p);
            return esValorSiguiente(cartaSuperior, carta) && esColorDiferente(carta, cartaSuperior);
        }
    }

    private boolean esColorDiferente(Carta cartaNueva, Carta cartaSuperior) {
        Enum<Color> colorCartaNueva = cartaNueva.getPalo().getColor();
        Enum<Color> colorCartaSuperior = cartaSuperior.getPalo().getColor();
        return !colorCartaNueva.equals(colorCartaSuperior);
    }

    public Carta verUltimaCarta() {
        if (!cartas.isEmpty()) {
            int ult = posUltimo(cartas);
            return cartas.get(ult);
        } else {
            return null;
        }
    }

    public Carta verCartaPos(int i) {
        if (cartas.size() >= i + 1) {
            return cartas.get(i);
        }
        return null;
    }

    public ArrayList<Carta> obtenerPila(){
        return cartas;
    }
}





