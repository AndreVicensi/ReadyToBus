package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import metodos.MetodosTelas;

public class TelaLoginController {

	@FXML
	private TextField tfLogin;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnEntrar;

	@FXML
	private Button btnSobre;

	@FXML
	private Button btnWeb;

	@FXML
	private PasswordField pfSenha;

	MetodosTelas tela = new MetodosTelas();
	@FXML
	void onSobre(ActionEvent event) {
		tela.mostrarSobre();
	}

	@FXML
	void onCadastrar(ActionEvent event) {
		tela.carregarTela("/visual/TelaCadastroEmpresa.fxml");
	}
	
	   @FXML
	    void onSite(ActionEvent event) {
		   tela.abrirSite("C:\\Users\\ander\\Documents\\web\\empresa/empresa.html");
	    }

}
