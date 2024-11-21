package org.example.petshopbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.petshopbook.Model.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuscaClienteController {

    @FXML
    private TextField especieField; // Campo para inserir a espécie do animal
    @FXML
    private Button buscarButton;    // Botão para acionar a busca
    @FXML
    private ListView<String> clienteListView; // Lista para exibir os clientes encontrados

    // Função acionada ao clicar no botão de busca
    @FXML
    private void handleBuscarClientes() {
        String especie = especieField.getText().trim();

        // Verifica se o campo de espécie está vazio
        if (especie.isEmpty()) {
            showAlert("Campo vazio", "Por favor, insira uma espécie para buscar.");
            return;
        }

        // Chama a função para buscar os clientes interessados na espécie
        List<String> clientes = buscarClientesPorEspecie(especie);

        // Exibe os resultados na ListView
        clienteListView.getItems().clear();
        if (clientes.isEmpty()) {
            clienteListView.getItems().add("Nenhum cliente interessado encontrado.");
        } else {
            clienteListView.getItems().addAll(clientes);
        }
    }

    // Função para buscar clientes interessados em uma espécie específica no banco de dados
    private List<String> buscarClientesPorEspecie(String especie) {
        List<String> clientes = new ArrayList<>();

        // Conexão e consulta ao banco de dados
        try (Connection conn = DatabaseConnection.getConnection()) { // Usa uma classe fictícia `DatabaseConnection`
            String query = "SELECT nome, telefone FROM Cliente WHERE status_procura = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, especie);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                clientes.add("Nome: " + nome + ", Telefone: " + telefone);
            }
        } catch (SQLException e) {
            showAlert("Erro no banco de dados", "Não foi possível buscar clientes: " + e.getMessage());
            e.printStackTrace();
        }

        return clientes;
    }

    // Função auxiliar para exibir alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
