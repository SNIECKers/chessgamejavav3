package it22207.chessjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessApplication extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        try {
            showLoginScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoginScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Chess - Вход");
        primaryStage.setScene(scene);

        LoginController loginController = fxmlLoader.getController();
        loginController.setApplication(this); // Устанавливаем ссылку на ChessApplication

        primaryStage.show();
    }

    public void showMainScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("board.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Chess - Главная");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showRegistrationScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Chess - Регистрация");
        primaryStage.setScene(scene);
        primaryStage.show();

        RegistrationController registrationController = fxmlLoader.getController();
        registrationController.setApplication(this); // Устанавливаем ссылку на ChessApplication
    }



    public static void main(String[] args) {
        launch();
    }
}
