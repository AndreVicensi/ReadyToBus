package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import metodos.MetodosTelas;

public class CadastroMotoristaController {

    @FXML
    private TextField tfLogin;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnSalvar;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfNomeMotorista;

    @FXML
    private TextField tfApelido;

    @FXML
    private PasswordField pfConfirmarSenha;

    private MetodosTelas tela = new MetodosTelas();
    
    @FXML
    void onSalvar(ActionEvent event) {

    }


    @FXML
    void onVoltar(ActionEvent event) {

    	tela.carregarTela("/visual/TelaEmpresa.fxml");
    }

}
