package com.example.atividade04_mensageria;

import mensageria.dao.ContatoDAO;
import model.Contato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField telefoneTextField;

    @FXML
    private Button salvarButton;

    @FXML
    private Button excluirButton;

    @FXML
    private Button listarButton;

    @FXML
    private TableView<Contato> contatosTable;

    @FXML
    private TableColumn<Contato, Integer> colunaId;

    @FXML
    private TableColumn<Contato, String> colunaNome;

    @FXML
    private TableColumn<Contato, String> colunaEmail;

    @FXML
    private TableColumn<Contato, String> colunaTelefone;

    private ContatoDAO contatoDAO;
    private ObservableList<Contato> contatoList;

    // Método de inicialização do controlador
    @FXML
    private void initialize() {
        contatoDAO = new ContatoDAO(); // Inicializa o DAO

        // Configura as colunas da tabela para corresponder aos atributos do modelo Contato
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        contatoList = FXCollections.observableArrayList();
        contatosTable.setItems(contatoList);

        System.out.println("Controller inicializado com sucesso!");
    }

    // Método para o botão Salvar
    @FXML
    private void salvarButtonAction(int id) {
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String telefone = telefoneTextField.getText();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            mostrarAlerta("Erro", "Todos os campos devem ser preenchidos!", Alert.AlertType.ERROR);
            return;
        }

        Contato contato = new Contato(id, nome, email, telefone);
        contatoDAO.salvarContato(contato);
        contatoList.add(contato);

        mostrarAlerta("Sucesso", "Contato salvo com sucesso!", Alert.AlertType.INFORMATION);
        limparCampos();
    }

    // Método para o botão Excluir
    @FXML
    private void excluirButtonAction() {
        Contato contatoSelecionado = contatosTable.getSelectionModel().getSelectedItem();
        if (contatoSelecionado != null) {
            contatoDAO.excluirContato(contatoSelecionado.getId());
            contatoList.remove(contatoSelecionado);
            mostrarAlerta("Sucesso", "Contato excluído com sucesso!", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Erro", "Nenhum contato selecionado para exclusão.", Alert.AlertType.ERROR);
        }
    }

    // Método para o botão Listar
    @FXML
    private void listarButtonAction() {
        contatoList.clear();
        contatoList.addAll(contatoDAO.listarContatos());
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Método para limpar os campos de texto após uma ação
    private void limparCampos() {
        nomeTextField.clear();
        emailTextField.clear();
        telefoneTextField.clear();
    }
}
