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

    @Override
    public void start(Stage primaryStage) {
        table = new TableView<>();
        atualizarTabela();

        Button salvarButton = new Button("Salvar");
        salvarButton.setOnAction(e -> salvarContato());

        Button excluirButton = new Button("Excluir");
        excluirButton.setOnAction(e -> excluirContato());

        VBox layout = new VBox(10, table, salvarButton, excluirButton);
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
        // Implementar lógica para capturar dados e salvar contato
    }

    private void excluirContato() {
        Contato contatoSelecionado = table.getSelectionModel().getSelectedItem();
        if (contatoSelecionado != null) {
            contatoDAO.excluirContato(contatoSelecionado.getId());
            atualizarTabela();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
