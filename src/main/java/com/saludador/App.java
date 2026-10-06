package com.saludador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("saludador.fxml"));
        Scene scene = new Scene(loader.load(), 460, 340);
        scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());

        stage.setTitle("Sistema Saludador");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
