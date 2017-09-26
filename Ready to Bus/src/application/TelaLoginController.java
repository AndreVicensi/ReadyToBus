package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import metodos.MetodoConfereSenha;
import metodos.MetodosLogin;
import metodos.MetodosTelas;
import model.Empresa;
import model.Usuario;

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
	
	private Mensagens mensagens = new Mensagens();
	private MetodosTelas tela = new MetodosTelas();
	private MetodosLogin login = new MetodosLogin();
	private MetodoConfereSenha confereSenha;
	private Empresa usuario;

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

	@FXML
	void onLogin(ActionEvent event) {
		// pega o login do usuario
		usuario = login.getLoginEmpresa(tfLogin.getText());
		// coloca valores no confere senha
		confereSenha = new MetodoConfereSenha(usuario.getSenha(),pfSenha.getText());
		// confere a senha
		System.out.println(pfSenha.getText());
		System.out.println(usuario.getSenha());
		if(!confereSenha.isSenhaVazia() && confereSenha.isSenhaIgual()) {
			mensagens.erroSexo();
		} else {
			mensagens.erroSenha();
		}
		
	}
}
