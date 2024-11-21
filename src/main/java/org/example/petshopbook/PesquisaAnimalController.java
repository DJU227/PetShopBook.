package org.example.petshopbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.example.petshopbook.Model.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PesquisaAnimalController {

    @FXML private ComboBox<String> tipoComboBox;
    @FXML private TextArea resultadosArea;
    @FXML private Button adotarButton;
    @FXML private ListView<String> listaAnimais;

    private String animalSelecionado = null; // ID do animal selecionado para adoção ou visualização de detalhes

    @FXML
    private void handleBusca() {
        String tipo = tipoComboBox.getValue();
        listaAnimais.getItems().clear(); // Limpa a lista de animais

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id_animal, nome FROM animal WHERE tipo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tipo);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String id = resultSet.getString("id_animal");

                // Adiciona o animal à lista, exibindo o nome
                listaAnimais.getItems().add(nome);
            }

            if (listaAnimais.getItems().isEmpty()) {
                resultadosArea.setText("Nenhum animal encontrado.");
            } else {
                resultadosArea.setText("Selecione um animal para ver mais detalhes.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultadosArea.setText("Erro ao buscar os animais: " + e.getMessage());
        }
    }

    @FXML
    private void handleSelecionarAnimal(MouseEvent event) {
        String nomeSelecionado = listaAnimais.getSelectionModel().getSelectedItem();

        if (nomeSelecionado == null) return;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM animal WHERE nome = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nomeSelecionado);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Obtém os detalhes do animal
                animalSelecionado = resultSet.getString("id_animal");
                String nome = resultSet.getString("nome");
                String tipo = resultSet.getString("tipo");
                int idade = resultSet.getInt("idade");
                String sexo = resultSet.getString("sexo");
                String raca = resultSet.getString("raca");
                String descricao = resultSet.getString("descricao");

                // Exibe os detalhes no `TextArea`
                resultadosArea.setText(
                        "Nome: " + nome + "\n" +
                                "Tipo: " + tipo + "\n" +
                                "Idade: " + idade + "\n" +
                                "Sexo: " + sexo + "\n" +
                                "Raça: " + raca + "\n" +
                                "Descrição: " + descricao + "\n"

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultadosArea.setText("Erro ao carregar detalhes do animal: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdotar() {
        if (animalSelecionado == null) {
            resultadosArea.setText("Selecione um animal antes de adotar.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM animal WHERE id_animal = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, animalSelecionado);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                resultadosArea.setText("Animal adotado com sucesso e removido do banco de dados!");
                animalSelecionado = null; // Reset após adoção
                handleBusca(); // Atualiza a lista de animais
            } else {
                resultadosArea.setText("Erro ao adotar o animal.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultadosArea.setText("Erro ao adotar o animal: " + e.getMessage());
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

            if (stage == null) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            }

            MainController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
