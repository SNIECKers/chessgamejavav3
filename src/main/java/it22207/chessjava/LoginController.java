package it22207.chessjava;

import db.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    private ChessApplication application;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    public void setApplication(ChessApplication application) {
        this.application = application;
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            if (DatabaseManager.isValidUser(username, password)) {
                application.showMainScene();
            } else {
                errorLabel.setText("Неверное имя пользователя или пароль");
            }
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Ошибка ввода-вывода");
        }
    }

    @FXML
    private void switchToRegistration() {
        try {
            application.showRegistrationScene();
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Ошибка ввода-вывода");
        }
    }

}
