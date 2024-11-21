package org.example.petshopbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.stage.Stage;
import org.example.petshopbook.Model.DatabaseConnection;

public class ClienteController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField cpfField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField enderecoField;
    @FXML
    private ComboBox<String> statusProcuraComboBox;

    @FXML
    private void cadastrarCliente() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String telefone = telefoneField.getText();
        String endereco = enderecoField.getText();
        String statusProcura = statusProcuraComboBox.getValue();

        if (statusProcura == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um status de procura.");
            alert.showAndWait();
            return;
        }

        // Insere o cliente no banco de dados
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO cliente (nome, cpf, telefone, endereco, status_procura) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, cpf);
            statement.setString(3, telefone);
            statement.setString(4, endereco);
            statement.setString(5, statusProcura);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cliente cadastrado com sucesso: " + nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar o cliente: " + e.getMessage());
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