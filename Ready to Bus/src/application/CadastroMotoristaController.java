package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroMotoristaController {

    @FXML
    private TextField tfLogin;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEntrar;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfNomeEmpresa;

    @FXML
    private TextField tfCnpj;

    MetodosTelas tela = new MetodosTelas();
    
    @FXML
    void onSalvar(ActionEvent event) {

    }

    @FXML
    void onVoltar(ActionEvent event) {

    	tela.carregarTela("/visual/TelaLogin.fxml");
    }

}
