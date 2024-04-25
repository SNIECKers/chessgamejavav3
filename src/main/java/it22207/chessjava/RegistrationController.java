package it22207.chessjava;

import db.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private ChessApplication application;

    public void setApplication(ChessApplication application) {
        this.application = application;
    }

    @FXML
    private void registerUser() {
        if (application == null) {
            throw new IllegalStateException("ChessApplication не установлен");
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            if (DatabaseManager.isUserExists(username)) {
                System.out.println("Пользователь с таким именем уже существует");
            } else {
                DatabaseManager.registerUser(username, password);
                System.out.println("Пользователь успешно зарегистрирован");
                application.showLoginScene();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при регистрации пользователя");
        }
    }
}
