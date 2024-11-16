package com.example.atividade04_mensageria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import mensageria.dao.ContatoDAO;
import model.Contato;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private TableView<Contato> table;
    private ContatoDAO contatoDAO = new ContatoDAO();

    // Campos para inserir o nome e telefone
    private TextField nomeField;
    private TextField telefoneField;

    @Override
    public void start(Stage primaryStage) {
        table = new TableView<>();
        atualizarTabela();

        // Criando campos de texto para inserir nome e telefone
        nomeField = new TextField();
        nomeField.setPromptText("Nome");

        telefoneField = new TextField();
        telefoneField.setPromptText("Telefone");

        // Botões de Salvar e Excluir
        Button salvarButton = new Button("Salvar");
        salvarButton.setOnAction(e -> salvarContato());

        Button excluirButton = new Button("Excluir");
        excluirButton.setOnAction(e -> excluirContato());

        // Layout com campos de texto e botões
        VBox layout = new VBox(10, table, nomeField, telefoneField, salvarButton, excluirButton);
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cadastro de Contatos");
        primaryStage.show();
    }

    private void atualizarTabela() {
        ObservableList<Contato> contatos = FXCollections.observableArrayList(contatoDAO.listarContatos());
        table.setItems(contatos);
    }

    private void salvarContato() {
        // Recuperando os dados inseridos no formulário
        String nome = nomeField.getText();
        String telefone = telefoneField.getText();

        // Validando os dados
        if (nome.isEmpty() || telefone.isEmpty()) {
            showAlert("Erro", "Nome e Telefone são obrigatórios!");
            return;
        }

        // Criando o objeto Contato
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setTelefone(telefone);

        // Salvando no banco de dados
        contatoDAO.salvarContato(contato);

        // Limpando os campos de texto
        nomeField.clear();
        telefoneField.clear();

        // Atualizando a tabela
        atualizarTabela();
    }

    private void excluirContato() {
        Contato contatoSelecionado = table.getSelectionModel().getSelectedItem();
        if (contatoSelecionado != null) {
            contatoDAO.excluirContato(contatoSelecionado.getId());
            atualizarTabela();
        }
    }

    // Função para exibir alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
