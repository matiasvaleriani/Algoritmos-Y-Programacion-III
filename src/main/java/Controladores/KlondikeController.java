package Controladores;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import BarajaCartas.Palo;
import Estructuras.*;
import Cartas.*;

import Juego.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class KlondikeController {
    public Rectangle Cimiento1,Cimiento2,Cimiento3,Cimiento4,Basura,Baraja;
    public Rectangle C1_1,C1_2,C1_3,C1_4,C1_5,C1_6,C1_7,C1_8,C1_9,C1_10,C1_11,C1_12,C1_13;
    public Rectangle C2_1,C2_2,C2_3,C2_4,C2_5,C2_6,C2_7,C2_8,C2_9,C2_10,C2_11,C2_12,C2_13;
    public Rectangle C3_1,C3_2,C3_3,C3_4,C3_5,C3_6,C3_7,C3_8,C3_9,C3_10,C3_11,C3_12,C3_13;
    public Rectangle C4_1,C4_2,C4_3,C4_4,C4_5,C4_6,C4_7,C4_8,C4_9,C4_10,C4_11,C4_12,C4_13;
    public Rectangle C5_1,C5_2,C5_3,C5_4,C5_5,C5_6,C5_7,C5_8,C5_9,C5_10,C5_11,C5_12,C5_13;
    public Rectangle C6_1,C6_2,C6_3,C6_4,C6_5,C6_6,C6_7,C6_8,C6_9,C6_10,C6_11,C6_12,C6_13;
    public Rectangle C7_1,C7_2,C7_3,C7_4,C7_5,C7_6,C7_7,C7_8,C7_9,C7_10,C7_11,C7_12,C7_13;
    public  Text L1_1,L1_2,L1_3,L1_4,L1_5,L1_6,L1_7,L1_8,L1_9,L1_10,L1_11,L1_12,L1_13;
    public  Text L2_1,L2_2,L2_3,L2_4,L2_5,L2_6,L2_7,L2_8,L2_9,L2_10,L2_11,L2_12,L2_13;
    public Text L3_1,L3_2,L3_3,L3_4,L3_5,L3_6,L3_7,L3_8,L3_9,L3_10,L3_11,L3_12,L3_13;
    public Text L4_1,L4_2,L4_3,L4_4,L4_5,L4_6,L4_7,L4_8,L4_9,L4_10,L4_11,L4_12,L4_13;
    public Text L5_1,L5_2,L5_3,L5_4,L5_5,L5_6,L5_7,L5_8,L5_9,L5_10,L5_11,L5_12,L5_13;
    public Text L6_1,L6_2,L6_3,L6_4,L6_5,L6_6,L6_7,L6_8,L6_9,L6_10,L6_11,L6_12,L6_13;
    public Text L7_1,L7_2,L7_3,L7_4,L7_5,L7_6,L7_7,L7_8,L7_9,L7_10,L7_11,L7_12,L7_13;
    public Text L_C1,L_C2,L_C3,L_C4, L_B;
    public Rectangle ventanaRecuperar,ventanaGanador;
    public Text txtRecuperacion,txtGanador;
    public Button btnSi,btnNo,btnNuevaPartida;
    private JuegoKlondike juego;
    private ArrayList<Rectangle> cartas_p1,cartas_p2,cartas_p3,cartas_p4,cartas_p5,cartas_p6,cartas_p7;
    private ArrayList<Text> label_p1,label_p2,label_p3,label_p4,label_p5,label_p6,label_p7;
    private ArrayList<ArrayList> pilas_vista;
    private ArrayList<Rectangle> cimientos_vista;
    private Carta cartaSeleccionada = null;
    private Rectangle rectanguloSeleccionado = null;
    private boolean partidaNueva=false;

    String obtenerIDCartaSeleccionada(Rectangle rSeleccionado){
        if (rSeleccionado != null){
            return rSeleccionado.getId();
        }
        return null;
    }

    @FXML
    public void initialize() {
        generarArrays();
        juego = new JuegoKlondike();

        try {
            juego=juego.recuperarPartida();
            if (!partidaNueva) {
                mostrarVentanaRecuperar();
            }
        }catch (RuntimeException e){
            juego.semillaAleatoria();
            partidaNueva=true;
            actualizarVista();
        }

    }

    public void recuperaPartida(){
        taparVentanaRecuperar();
        actualizarVista();
    }

    public void partidaNueva(){
        juego = new JuegoKlondike();
        juego.semillaAleatoria();
        taparVentanaRecuperar();
        actualizarVista();
    }

    private void mostrarVentanaRecuperar(){
        btnSi.setDisable(false);btnSi.setVisible(true);
        btnNo.setDisable(false);btnNo.setVisible(true);
        ventanaRecuperar.setDisable(false);ventanaRecuperar.setVisible(true);
        txtRecuperacion.setDisable(false);txtRecuperacion.setVisible(true);
    }


    public void taparVentanaRecuperar(){
        btnSi.setDisable(true);btnSi.setVisible(false);
        btnNo.setDisable(true);btnNo.setVisible(false);
        ventanaRecuperar.setDisable(true);ventanaRecuperar.setVisible(false);
        txtRecuperacion.setDisable(true);txtRecuperacion.setVisible(false);

    }

    public void generarArrays(){
        this.cartas_p1 = new ArrayList<>();cartas_p1.add(C1_1);cartas_p1.add(C1_2);cartas_p1.add(C1_3);cartas_p1.add(C1_4);cartas_p1.add(C1_5);cartas_p1.add(C1_6);cartas_p1.add(C1_7);cartas_p1.add(C1_8);cartas_p1.add(C1_9);cartas_p1.add(C1_10);cartas_p1.add(C1_11);cartas_p1.add(C1_12);cartas_p1.add(C1_13);
        this.cartas_p2 = new ArrayList<>();cartas_p2.add(C2_1);cartas_p2.add(C2_2);cartas_p2.add(C2_3);cartas_p2.add(C2_4);cartas_p2.add(C2_5);cartas_p2.add(C2_6);cartas_p2.add(C2_7);cartas_p2.add(C2_8);cartas_p2.add(C2_9);cartas_p2.add(C2_10);cartas_p2.add(C2_11);cartas_p2.add(C2_12);cartas_p2.add(C2_13);
        this.cartas_p3 = new ArrayList<>();cartas_p3.add(C3_1);cartas_p3.add(C3_2);cartas_p3.add(C3_3);cartas_p3.add(C3_4);cartas_p3.add(C3_5);cartas_p3.add(C3_6);cartas_p3.add(C3_7);cartas_p3.add(C3_8);cartas_p3.add(C3_9);cartas_p3.add(C3_10);cartas_p3.add(C3_11);cartas_p3.add(C3_12);cartas_p3.add(C3_13);
        this.cartas_p4 = new ArrayList<>();cartas_p4.add(C4_1);cartas_p4.add(C4_2);cartas_p4.add(C4_3);cartas_p4.add(C4_4);cartas_p4.add(C4_5);cartas_p4.add(C4_6);cartas_p4.add(C4_7);cartas_p4.add(C4_8);cartas_p4.add(C4_9);cartas_p4.add(C4_10);cartas_p4.add(C4_11);cartas_p4.add(C4_12);cartas_p4.add(C4_13);
        this.cartas_p5 = new ArrayList<>();cartas_p5.add(C5_1);cartas_p5.add(C5_2);cartas_p5.add(C5_3);cartas_p5.add(C5_4);cartas_p5.add(C5_5);cartas_p5.add(C5_6);cartas_p5.add(C5_7);cartas_p5.add(C5_8);cartas_p5.add(C5_9);cartas_p5.add(C5_10);cartas_p5.add(C5_11);cartas_p5.add(C5_12);cartas_p5.add(C5_13);
        this.cartas_p6 = new ArrayList<>();cartas_p6.add(C6_1);cartas_p6.add(C6_2);cartas_p6.add(C6_3);cartas_p6.add(C6_4);cartas_p6.add(C6_5);cartas_p6.add(C6_6);cartas_p6.add(C6_7);cartas_p6.add(C6_8);cartas_p6.add(C6_9);cartas_p6.add(C6_10);cartas_p6.add(C6_11);cartas_p6.add(C6_12);cartas_p6.add(C6_13);
        this.cartas_p7 = new ArrayList<>();cartas_p7.add(C7_1);cartas_p7.add(C7_2);cartas_p7.add(C7_3);cartas_p7.add(C7_4);cartas_p7.add(C7_5);cartas_p7.add(C7_6);cartas_p7.add(C7_7);cartas_p7.add(C7_8);cartas_p7.add(C7_9);cartas_p7.add(C7_10);cartas_p7.add(C7_11);cartas_p7.add(C7_12);cartas_p7.add(C7_13);

        this.pilas_vista = new ArrayList<>();pilas_vista.add(cartas_p1);pilas_vista.add(cartas_p2);pilas_vista.add(cartas_p3);pilas_vista.add(cartas_p4);pilas_vista.add(cartas_p5);pilas_vista.add(cartas_p6);pilas_vista.add(cartas_p7);

        this.label_p1 = new ArrayList<>();label_p1.add(L1_1);label_p1.add(L1_2);label_p1.add(L1_3);label_p1.add(L1_4);label_p1.add(L1_5);label_p1.add(L1_6);label_p1.add(L1_7);label_p1.add(L1_8);label_p1.add(L1_9);label_p1.add(L1_10);label_p1.add(L1_11);label_p1.add(L1_12);label_p1.add(L1_13);
        this.label_p2 = new ArrayList<>();label_p2.add(L2_1);label_p2.add(L2_2);label_p2.add(L2_3);label_p2.add(L2_4);label_p2.add(L2_5);label_p2.add(L2_6);label_p2.add(L2_7);label_p2.add(L2_8);label_p2.add(L2_9);label_p2.add(L2_10);label_p2.add(L2_11);label_p2.add(L2_12);label_p2.add(L2_13);
        this.label_p3 = new ArrayList<>();label_p3.add(L3_1);label_p3.add(L3_2);label_p3.add(L3_3);label_p3.add(L3_4);label_p3.add(L3_5);label_p3.add(L3_6);label_p3.add(L3_7);label_p3.add(L3_8);label_p3.add(L3_9);label_p3.add(L3_10);label_p3.add(L3_11);label_p3.add(L3_12);label_p3.add(L3_13);
        this.label_p4 = new ArrayList<>();label_p4.add(L4_1);label_p4.add(L4_2);label_p4.add(L4_3);label_p4.add(L4_4);label_p4.add(L4_5);label_p4.add(L4_6);label_p4.add(L4_7);label_p4.add(L4_8);label_p4.add(L4_9);label_p4.add(L4_10);label_p4.add(L4_11);label_p4.add(L4_12);label_p4.add(L4_13);
        this.label_p5 = new ArrayList<>();label_p5.add(L5_1);label_p5.add(L5_2);label_p5.add(L5_3);label_p5.add(L5_4);label_p5.add(L5_5);label_p5.add(L5_6);label_p5.add(L5_7);label_p5.add(L5_8);label_p5.add(L5_9);label_p5.add(L5_10);label_p5.add(L5_11);label_p5.add(L5_12);label_p5.add(L5_13);
        this.label_p6 = new ArrayList<>();label_p6.add(L6_1);label_p6.add(L6_2);label_p6.add(L6_3);label_p6.add(L6_4);label_p6.add(L6_5);label_p6.add(L6_6);label_p6.add(L6_7);label_p6.add(L6_8);label_p6.add(L6_9);label_p6.add(L6_10);label_p6.add(L6_11);label_p6.add(L6_12);label_p6.add(L6_13);
        this.label_p7 = new ArrayList<>();label_p7.add(L7_1);label_p7.add(L7_2);label_p7.add(L7_3);label_p7.add(L7_4);label_p7.add(L7_5);label_p7.add(L7_6);label_p7.add(L7_7);label_p7.add(L7_8);label_p7.add(L7_9);label_p7.add(L7_10);label_p7.add(L7_11);label_p7.add(L7_12);label_p7.add(L7_13);

         this.cimientos_vista = new ArrayList<>();cimientos_vista.add(Cimiento1);cimientos_vista.add(Cimiento2);cimientos_vista.add(Cimiento3);cimientos_vista.add(Cimiento4);

    }

    private String aString(Palo p){
        if (p.equals(Palo.TREBOL)){
            return "♣";
        }
        if (p.equals(Palo.PICA)){
            return "♠";
        }
        if (p.equals(Palo.CORAZON)){
            return "♥";
        }
        return "♦";
    }

    private void mostrarPilas(){
        ArrayList<ArrayList> pilas = new ArrayList<>();pilas.add(cartas_p1);pilas.add(cartas_p2);pilas.add(cartas_p3);pilas.add(cartas_p4);pilas.add(cartas_p5);pilas.add(cartas_p6);pilas.add(cartas_p7);
        ArrayList<ArrayList> labels = new ArrayList<>();labels.add(label_p1);labels.add(label_p2);labels.add(label_p3);labels.add(label_p4);labels.add(label_p5);labels.add(label_p6);labels.add(label_p7);

        for (int i=0; i<7; i++){
            PilaDeCartas pila_juego = juego.obtenerPila(i+1);
            ArrayList<Rectangle> pila_vista = pilas.get(i);
            ArrayList<Carta> cartas_juego = pila_juego.obtenerPila();
            ArrayList<Text> label = labels.get(i);

            for (int j = 0; j<cartas_juego.size(); j++) {
                Carta c = cartas_juego.get(j);
                Rectangle r = pila_vista.get(j);
                Text l = label.get(j);
                r.setVisible(true);
                r.setOpacity(1);
                r.setFill(Color.WHITE);
                if (c.esTapada()) {
                    r.setFill(Color.RED);
                } else {
                    r.setFill(Color.WHITE);
                    r.setDisable(false);
                    l.setText(valoryPalo(c));
                    l.setVisible(true);
                    Palo palo = c.getPalo();
                    if (palo==Palo.CORAZON || palo == Palo.DIAMANTE){
                        l.setFill(Color.RED);
                    }else{
                        l.setFill(Color.BLACK);
                    }
                }
            }
            if (cartas_juego.size()==0){
                Rectangle r = pila_vista.get(0);
                r.setVisible(true);
                r.setFill(Color.GRAY);
                r.setOpacity(0.4);
            }

        }
    }

    private String valoryPalo(Carta c){
        String palo = aString(c.getPalo());
        String valor = pasarALetra(c.getValor());
        return valor + " " + palo;
    }

    private String pasarALetra(String valor){
        if (Objects.equals(valor, "1")){
            return "A";
        } else if (Objects.equals(valor, "11")){
            return "J";
        } else if (Objects.equals(valor, "12")){
            return "Q";
        }else if (Objects.equals(valor, "13")){
            return "K";
        }
        return valor;
    }

    @FXML
    private void actualizarVista(){
        borrarPilas();
        mostrarPilas();
        mostrarCimientos();
        mostrarBasura();
        mostrarBaraja();

        juegoTerminado();
    }

    @FXML
    public void juegoTerminado() {
        if (juego.juegoTerminado()) {
            txtGanador.setVisible(true);
            ventanaGanador.setVisible(true);
            btnNuevaPartida.setDisable(false);
            btnNuevaPartida.setVisible(true);
        }
    }

    @FXML
    public void nuevaPartida(){
        txtGanador.setVisible(false);
        ventanaGanador.setVisible(false);
        btnNuevaPartida.setDisable(true);
        btnNuevaPartida.setVisible(false);
        juego = new JuegoKlondike(); juego.semillaAleatoria();
        actualizarVista();
    }

    @FXML
    private void borrarPilas(){
        ArrayList<ArrayList> pilas = new ArrayList<>();pilas.add(cartas_p1);pilas.add(cartas_p2);pilas.add(cartas_p3);pilas.add(cartas_p4);pilas.add(cartas_p5);pilas.add(cartas_p6);pilas.add(cartas_p7);
        ArrayList<ArrayList> labels = new ArrayList<>();labels.add(label_p1);labels.add(label_p2);labels.add(label_p3);labels.add(label_p4);labels.add(label_p5);labels.add(label_p6);labels.add(label_p7);

        //borra las pilas
        for(ArrayList<Rectangle> p : pilas){
            for (Rectangle carta : p){
                carta.setVisible(false);
            }
        }
        for (ArrayList<Text> l : labels){
            for (Text label :l){
                label.setVisible(false);
            }
        }
    }

    private void mostrarBaraja(){
        if (juego.obtenerBaraja().cantidad()==0){
            Baraja.setOpacity(0.4);
            Baraja.setFill(Color.GRAY);
            return;
        }
        Baraja.setOpacity(1);
        Baraja.setFill(Color.RED);
    }

    private void mostrarBasura(){
        if(juego.obtenerBasura()!=null){
            Basura.setOpacity(1);
            Basura.setFill(Color.WHITE);
            L_B.setText(valoryPalo(juego.obtenerBasura()));
            L_B.setOpacity(1);
            L_B.setVisible(true);
            Palo palo = juego.obtenerBasura().getPalo();
            if (palo==Palo.CORAZON || palo ==Palo.DIAMANTE){
                L_B.setFill(Color.RED);
            }else{
                L_B.setFill(Color.BLACK);
            }
            return;
        }
        Basura.setOpacity(0.4);
        Basura.setFill(Color.GRAY);
        L_B.setVisible(false);
    }


    @FXML
    public void mostrarCimientos(){
        ArrayList<Rectangle> vista_cimientos = new ArrayList<>();vista_cimientos.add(Cimiento1);vista_cimientos.add(Cimiento2);vista_cimientos.add(Cimiento3);vista_cimientos.add(Cimiento4);
        ArrayList<Text> labels = new ArrayList<>();labels.add(L_C1);labels.add(L_C2);labels.add(L_C3);labels.add(L_C4);
        for(int i=0; i<4; i++){
            Cimiento c = juego.obtenerCimiento(i+1);
            Rectangle c_vista = vista_cimientos.get(i);
            Text l = labels.get(i);
            Carta carta = c.verUltimaCarta();
            if (carta==null){
                c_vista.setFill(Color.GRAY);
                c_vista.setOpacity(0.4);
                l.setVisible(false);
            }else {
                c_vista.setFill(Color.WHITE);
                l.setVisible(true);
                c_vista.setOpacity(1);
                l.setText(valoryPalo(carta));
                Palo palo = carta.getPalo();
                if (palo==Palo.CORAZON || palo ==Palo.DIAMANTE){
                    l.setFill(Color.RED);
                }else{
                    l.setFill(Color.BLACK);
                }
            }
        }
    }


    private Carta obtenerCartaDesdeRectangulo(Rectangle rectangulo) {
        for (int i = 0; i < cartas_p1.size(); i++) {
            if (cartas_p1.get(i) == rectangulo) {
                return juego.obtenerPila(1).verCartaPos(i);
            } else if (cartas_p2.get(i) == rectangulo) {
                return juego.obtenerPila(2).verCartaPos(i);
            } else if (cartas_p3.get(i) == rectangulo) {
                return juego.obtenerPila(3).verCartaPos(i);
            } else if (cartas_p4.get(i) == rectangulo) {
                return juego.obtenerPila(4).verCartaPos(i);
            } else if (cartas_p5.get(i) == rectangulo) {
                return juego.obtenerPila(5).verCartaPos(i);
            } else if (cartas_p6.get(i) == rectangulo) {
                return juego.obtenerPila(6).verCartaPos(i);
            } else if (cartas_p7.get(i) == rectangulo) {
                return juego.obtenerPila(7).verCartaPos(i);
            }
        }
        return null;
    }


    private void resaltarCartaSeleccionada(Rectangle rectangulo) {
        rectangulo.setStroke(Color.RED);
    }

    public int encontrarCartaEnPila(PilaDeCartas pila, Carta carta) {
        int cantidad = pila.cant();
        for (int i = 0; i < cantidad; i++) {
            if (pila.verCartaPos(i) == carta) {
                return i;
            }
        }
        return -1;
    }


    //Busco la pila a la que pertenece la carta pasada por param
    private int encontrarPilaDeCarta(Carta carta) {

        for (int i = 0; i < 7; i++) {
            var pilaActual = juego.obtenerPila(i+1);
            for(int j = 0; j< pilaActual.cant(); j++){
                if (pilaActual.verCartaPos(j) == carta) {
                    return i+1;
                }
            }
        }
        return -1;
    }

    private int encontrarCasillero(String idCasillero) {
        switch (idCasillero) {
            case "Casillero1":
                return 1;
            case "Casillero2":
                return 2;
            case "Casillero3":
                return 3;
            default:
                return 4;
        }
    }

    private int encontrarCimiento(String idCimiento) {
        switch (idCimiento) {
            case "Cimiento1":
                return 1;
            case "Cimiento2":
                return 2;
            case "Cimiento3":
                return 3;
            default:
                return 4;
        }
    }
    private boolean validarRectanguloVacioClicado(Rectangle rectangulo){
        return rectangulo.getId().equals("Casillero1") || rectangulo.getId().equals("Casillero2") ||
                rectangulo.getId().equals("Casillero3") || rectangulo.getId().equals("Casillero4") ||
                rectangulo.getId().equals("Cimiento1") || rectangulo.getId().equals("Cimiento2") ||
                rectangulo.getId().equals("Cimiento3") || rectangulo.getId().equals("Cimiento4");
    }
    private void deseleccionarTodasLasCartas() {
        ArrayList<ArrayList<Rectangle>> pilas = new ArrayList<>();pilas.add(cartas_p1);pilas.add(cartas_p2);pilas.add(cartas_p3);pilas.add(cartas_p4);pilas.add(cartas_p5);pilas.add(cartas_p6);pilas.add(cartas_p7);
        ArrayList<Rectangle> vista_cimientos = new ArrayList<>();vista_cimientos.add(Cimiento1);vista_cimientos.add(Cimiento2);vista_cimientos.add(Cimiento3);vista_cimientos.add(Cimiento4);

        for (ArrayList<Rectangle> pila : pilas) {
            for (Rectangle carta : pila) {
                carta.setStroke(Color.BLACK);
            }
        }
        for (Rectangle cimiento : vista_cimientos) {
            cimiento.setStroke(Color.BLACK);
        }
    }

    @FXML
    private void SeleccionarCarta(MouseEvent event) {
        List<String> idsPilas = Arrays.asList(
                "C1_1", "C1_2", "C1_3", "C1_4", "C1_5", "C1_6", "C1_7", "C1_8", "C1_9", "C1_10", "C1_11", "C1_12", "C1_13",
                "C2_1", "C2_2", "C2_3", "C2_4", "C2_5", "C2_6", "C2_7", "C2_8", "C2_9", "C2_10", "C2_11", "C2_12", "C2_13",
                "C3_1", "C3_2", "C3_3", "C3_4", "C3_5", "C3_6", "C3_7", "C3_8", "C3_9", "C3_10", "C3_11", "C3_12", "C3_13",
                "C4_1", "C4_2", "C4_3", "C4_4", "C4_5", "C4_6", "C4_7", "C4_8", "C4_9", "C4_10", "C4_11", "C4_12", "C4_13",
                "C5_1", "C5_2", "C5_3", "C5_4", "C5_5", "C5_6", "C5_7", "C5_8", "C5_9", "C5_10", "C5_11", "C5_12", "C5_13",
                "C6_1", "C6_2", "C6_3", "C6_4", "C6_5", "C6_6", "C6_7", "C6_8", "C6_9", "C6_10", "C6_11", "C6_12", "C6_13",
                "C7_1", "C7_2", "C7_3", "C7_4", "C7_5", "C7_6", "C7_7", "C7_8", "C7_9", "C7_10", "C7_11", "C7_12", "C7_13"
        );
        List<String> idsCimientos = Arrays.asList("Cimiento1", "Cimiento2", "Cimiento3", "Cimiento4");

        Rectangle rectangulo = (Rectangle) event.getSource();
        Carta cartaClicada = obtenerCartaDesdeRectangulo(rectangulo);
        String idRectangulo = rectangulo.getId();
        if (rectangulo.getId().equals("Baraja")){
            if (juego.obtenerBaraja().cantidad()==0){
                juego.reiniciarBaraja();
            }else {
                juego.barajaABasura();
            }
            cartaSeleccionada = null;
            rectanguloSeleccionado = null;
            actualizarVista();
            deseleccionarTodasLasCartas();
            return;
        }
        if (rectangulo.getId().equals("Basura")){
            if (cartaSeleccionada == null) {
                cartaSeleccionada = cartaClicada;
                rectanguloSeleccionado = rectangulo;
                resaltarCartaSeleccionada(rectangulo);
            }else {
                if (idsPilas.contains(rectanguloSeleccionado.getId())) {
                    //caso mover basura a pila
                    int pos_dest;
                    for (ArrayList<ArrayList<Rectangle>> p : pilas_vista) {
                        if (p.contains(rectangulo)) {
                            pos_dest = pilas_vista.indexOf(p) + 1;
                            juego.basuraAPila(pos_dest);
                        }
                    }
                    cartaSeleccionada = null;
                    rectanguloSeleccionado = null;
                    actualizarVista();
                    deseleccionarTodasLasCartas();
                    return;
                }
            }
        }
        if (rectanguloSeleccionado!=null && idsPilas.contains(rectangulo.getId()) && cimientos_vista.contains(rectanguloSeleccionado)){
            int pos_cim = cimientos_vista.indexOf(rectanguloSeleccionado)+1;
            int pos_dest;
            for (ArrayList<ArrayList<Rectangle>> p : pilas_vista) {
                if (p.contains(rectangulo)) {
                    pos_dest = pilas_vista.indexOf(p)+1;
                    juego.moverCimientoAPila(pos_cim,pos_dest);
                }
            }
            cartaSeleccionada = null;
            rectanguloSeleccionado = null;
            actualizarVista();
            deseleccionarTodasLasCartas();
            return;
        }
        if (rectanguloSeleccionado!=null && idsPilas.contains(rectangulo.getId()) && rectanguloSeleccionado.getId().equals("Basura")){
            int pos_dest;
            for (ArrayList<ArrayList<Rectangle>> p : pilas_vista) {
                if (p.contains(rectangulo)) {
                    pos_dest = pilas_vista.indexOf(p)+1;
                    juego.basuraAPila(pos_dest);
                }
            }
            cartaSeleccionada = null;
            rectanguloSeleccionado = null;
            actualizarVista();
            deseleccionarTodasLasCartas();
            return;
        }
        if (idsCimientos.contains(rectangulo.getId()) && rectanguloSeleccionado!=null && rectanguloSeleccionado.getId().equals("Basura")){
            //caso basura a cimiento
            int pos_cim = cimientos_vista.indexOf(rectangulo);
            juego.basuraACimiento(pos_cim+1);
            cartaSeleccionada = null;
            rectanguloSeleccionado = null;
            actualizarVista();
            deseleccionarTodasLasCartas();
            return;
        }
        if (idsCimientos.contains(rectangulo.getId())){
            if (cartaSeleccionada == null) {
                cartaSeleccionada = cartaClicada;
                rectanguloSeleccionado = rectangulo;
                resaltarCartaSeleccionada(rectangulo);
            }
        }

        if (cartaSeleccionada == null) {
            cartaSeleccionada = cartaClicada;
            rectanguloSeleccionado = rectangulo;

            resaltarCartaSeleccionada(rectangulo);
        } else {

            if (idsPilas.contains(obtenerIDCartaSeleccionada(rectanguloSeleccionado)) && idsPilas.contains(idRectangulo)) {

                int pilaOrigen = encontrarPilaDeCarta(cartaSeleccionada);
                int pilaDestino = encontrarPilaDeCarta(cartaClicada);
                if (pilaOrigen!=-1 && pilaDestino==-1){
                    //caso pasar a pila vacia
                        int cant = juego.obtenerPila(pilaOrigen).cant() - encontrarCartaEnPila(juego.obtenerPila(pilaOrigen), cartaSeleccionada);
                        int pos_dest;
                        for (ArrayList<ArrayList<Rectangle>> p : pilas_vista) {
                            if (p.contains(rectangulo)) {
                                pos_dest = pilas_vista.indexOf(p)+1;
                                juego.moverPilaAPila(pilaOrigen,pos_dest,cant);

                        }
                    }
                    cartaSeleccionada = null;
                    rectanguloSeleccionado = null;
                    actualizarVista();
                    deseleccionarTodasLasCartas();
                    return;
                }
                if (pilaOrigen != -1 && pilaDestino != -1 && pilaOrigen != pilaDestino) {
                    int cartasASeleccionar = juego.obtenerPila(pilaOrigen).cant() - encontrarCartaEnPila(juego.obtenerPila(pilaOrigen), cartaSeleccionada);
                    juego.moverPilaAPila(pilaOrigen, pilaDestino, cartasASeleccionar);

                    cartaSeleccionada = null;
                    rectanguloSeleccionado = null;
                    actualizarVista();
                    deseleccionarTodasLasCartas();
                }
            }

            if (idsPilas.contains(obtenerIDCartaSeleccionada(rectanguloSeleccionado)) && idsCimientos.contains(rectangulo.getId())) {
                int pilaOrigen = encontrarPilaDeCarta(cartaSeleccionada);
                if (pilaOrigen==-1) {
                    int origen;
                    for (ArrayList<ArrayList<Rectangle>> p : pilas_vista) {
                        if (p.contains(rectangulo)) {
                            origen = pilas_vista.indexOf(p) + 1;
                            juego.moverPilaACimiento(origen, encontrarCimiento(idRectangulo));
                        }
                    }
                }else if (cartaSeleccionada == juego.obtenerPila(pilaOrigen).verUltimaCarta()) {
                    juego.moverPilaACimiento(pilaOrigen, encontrarCimiento(idRectangulo));
                }
                cartaSeleccionada = null;
                rectanguloSeleccionado = null;
                actualizarVista();
                deseleccionarTodasLasCartas();
            }

        }
    }
}