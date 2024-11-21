package org.example.petshopbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.petshopbook.Model.DatabaseConnection;

public class AnimalController {

    @FXML private TextField nomeField;
    @FXML private ComboBox<String> tipoComboBox;
    @FXML private TextField idadeField;
    @FXML private TextField sexoField;
    @FXML private TextField racaField;
    @FXML private TextArea descricaoField;

    @FXML
    private void handleCadastro() {
        String nome = nomeField.getText();
        String tipo = tipoComboBox.getValue();
        String idade = idadeField.getText();
        String sexo = sexoField.getText();
        String raca = racaField.getText();
        String descricao = descricaoField.getText();

        // Insere os dados no banco de dados
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO animal (nome, tipo, idade, sexo, raca, descricao) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, tipo);
            statement.setString(3, idade);
            statement.setString(4, sexo);
            statement.setString(5, raca);
            statement.setString(6, descricao);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Animal cadastrado com sucesso: " + nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar o animal: " + e.getMessage());
        }

    }
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private void handleVoltarAoMenu(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = fxmlLoader.load();

            // Verifique se 'stage' está inicializado
            if (stage == null) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            }

            // Se 'stage' ainda não estiver inicializado, recupere a referência da janela
            MainController controller = fxmlLoader.getController();
            controller.setStage(stage); // Passa o 'stage' ao novo controlador

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

