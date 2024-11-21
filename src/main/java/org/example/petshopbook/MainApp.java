package org.example.petshopbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petshopbook/Menu.fxml"));
            AnchorPane mainMenu = loader.load();

            MainController controller = loader.getController();
            controller.setStage(primaryStage);

            Scene scene = new Scene(mainMenu);
            primaryStage.setScene(scene);
            primaryStage.setTitle("PetShop - Adoção e Venda de Animais");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
