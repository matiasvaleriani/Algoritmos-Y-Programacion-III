import Cartas.Carta;
import Estructuras.PilaDeCartas;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Juego.*;
import java.io.IOException;
import Controladores.*;

public class Solitario extends Application {

    JuegoKlondike partidaKlondike;
    JuegoFreeCell partidaFreeCell;
    Juego partida;
    private KlondikeController klondikeController;
    private FreeCellController freecellController;

    @FXML
    private Button btnKlondike;

    @FXML
    private Button btnFreeCell;

    @Override
    public void start(Stage stage) throws Exception {
        var loader = new FXMLLoader(getClass().getResource("inicio.fxml"));
        loader.setController(this);
        VBox ventana = loader.load();

        btnFreeCell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TableroFreeCell.fxml"));
                    Scene tablero = loader.load();
                    FreeCellController freeCellController = loader.getController();
                    freeCellController.initialize();
                    stage.setScene(tablero);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnKlondike.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TableroKlondike.fxml"));
                    Scene tablero = loader.load();
                    //KlondikeController klondikeController = loader.getController();
                    //klondikeController.initialize();
                    stage.setScene(tablero);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        var scene = new Scene(ventana, 640, 400);
        stage.setScene(scene);

        stage.show();
    }
}