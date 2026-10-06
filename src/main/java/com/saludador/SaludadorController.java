package com.saludador;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SaludadorController {

    private static final int EDAD_MINIMA = 0;
    private static final int EDAD_MAXIMA = 120;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField edadField;

    @FXML
    private ComboBox<String> momentoCombo;

    @FXML
    private Label mensajeLabel;

    @FXML
    private void initialize() {
        momentoCombo.getItems().setAll("AM", "PM");
        mostrarMensaje("", false);
    }

    @FXML
    private void onSolicitarSaludo() {
        String nombre = texto(nombreField);
        String edadTexto = texto(edadField);
        String momento = momentoCombo.getValue();

        String error = validar(nombre, edadTexto, momento);
        if (error != null) {
            mostrarMensaje(error, true);
            return;
        }

        int edad = Integer.parseInt(edadTexto);
        String saludo = "AM".equals(momento) ? "Buenos días" : "Buenas tardes";
        String anios = edad == 1 ? "año" : "años";
        mostrarMensaje(saludo + ", " + nombre + ". Tienes " + edad + " " + anios + ".", false);
    }

    private String validar(String nombre, String edadTexto, String momento) {
        if (nombre.isEmpty() && edadTexto.isEmpty() && (momento == null || momento.isBlank())) {
            return "Completa el nombre, la edad y la selección AM/PM.";
        }
        if (nombre.isEmpty()) {
            return "El nombre no puede estar vacío.";
        }
        if (edadTexto.isEmpty()) {
            return "La edad no puede estar vacía.";
        }
        if (momento == null || momento.isBlank()) {
            return "Selecciona AM o PM.";
        }
        if (!edadTexto.matches("\\d+")) {
            return "La edad debe ser un número entero válido.";
        }

        int edad = Integer.parseInt(edadTexto);
        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            return "La edad debe estar entre " + EDAD_MINIMA + " y " + EDAD_MAXIMA + ".";
        }
        return null;
    }

    private void mostrarMensaje(String texto, boolean error) {
        mensajeLabel.setText(texto);
        mensajeLabel.getStyleClass().removeAll("mensaje-ok", "mensaje-error");
        if (!texto.isEmpty()) {
            mensajeLabel.getStyleClass().add(error ? "mensaje-error" : "mensaje-ok");
        }
    }

    private static String texto(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }
}
