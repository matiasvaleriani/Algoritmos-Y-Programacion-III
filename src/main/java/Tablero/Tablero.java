package Tablero;

import Juego.*;

public class Tablero {
    Juego partida;
    public Tablero(){
    }

    public void JugarKlondike(){
        JuegoKlondike juego = new JuegoKlondike();
        this.partida = juego;
    }

    public void JugarFreeCell(){
        JuegoFreeCell juego = new JuegoFreeCell();
        this.partida = juego;
    }
}
