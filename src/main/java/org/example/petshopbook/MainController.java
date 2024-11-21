package org.example.petshopbook;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class MainController {

    private Stage stage;

    // Define a `Stage` principal no controlador
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handlePesquisaAnimal() {
        loadScene("PesquisaAnimal.fxml");
    }

    @FXML
    private void handleCadastroAnimal() {
        loadScene("Animal.fxml");
    }

    @FXML
    private void handleBuscaCliente() {
        loadScene("BuscarCliente.fxml");
    }

    @FXML
    private void handleCadastroCliente() {
        loadScene("Cliente.fxml");
    }

    private void loadScene(String fxmlFile) {
        if (stage == null) {
            System.out.println("Erro: Stage não foi inicializado.");
            return;  // Verifica se a stage foi definida
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Define a cena no stage principal
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Configura a tela para ficar cheia quando a cena for carregada
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    // Exibir uma mensagem quando pressionar ESC
                    showEscapeMessage();

                    // Opcional: Configurar para sair do fullscreen
                    stage.setFullScreen(false);
                }
            });

            // Configura o menu inicial em tela cheia
            stage.setFullScreen(true); // Isso coloca a janela em tela cheia

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEscapeMessage() {
        // Exibe uma mensagem por um curto período
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText("Aperte ESC novamente para sair do modo tela cheia!");

        // Fecha a mensagem após 2 segundos
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> alert.close());
        pause.play();

        alert.show();
    }
}
