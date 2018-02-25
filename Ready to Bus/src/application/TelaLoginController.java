package application;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import metodos.AplicacaoSessao;
import metodos.MetodoConfereSenha;
import metodos.MetodosLogin;
import metodos.MetodosTelas;
import model.Empresa;
import model.Motorista;
import model.Passageiro;
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
	private Usuario usuario;

	@FXML
	void onSobre(ActionEvent event) {
		tela.mostrarSobre();
	}

	@FXML
	void onCadastrar(ActionEvent event) {
		tela.carregarTela("/visual/TelaCadastroEmpresa.fxml");
	}

	@FXML
	void onSite(ActionEvent event) throws IOException {
		// tela.abrirSite("C:\\Users\\ander\\Documents\\web\\empresa/empresa.html");

		String url = new File("site/index.html").getAbsolutePath();
		// assim funciona
		Runtime.getRuntime().exec("cmd.exe /C start chrome.exe file://" + url.replaceAll(" ", "%20"));
		// coloca o caminho do nosso site após o firefox.exe no lugar do http...
	}

	@FXML
	void onLogin(ActionEvent event) {

		if (tfLogin.getText().isEmpty() || pfSenha.getText().isEmpty()) {
			mensagens.erroPrenchimento();
		} else {

			// pega o login do usuario
			usuario = login.getLoginEmpresa(tfLogin.getText());
			if (usuario == null) {
				usuario = login.getLoginMotorista(tfLogin.getText());
			}
			if (usuario == null) {
				usuario = login.getLoginPassageiro(tfLogin.getText());
			}

			// coloca valores no confere senha
			confereSenha = new MetodoConfereSenha(usuario.getSenha(), pfSenha.getText());

			// confere a senha
			if (!confereSenha.isSenhaVazia() && confereSenha.isSenhaIgual() && usuario != null) {
				if (usuario instanceof Empresa) {
					AplicacaoSessao.empresa = (Empresa) usuario;
					tela.carregarTela("/visual/TelaEmpresa.fxml");
				} else if (usuario instanceof Motorista) {
					AplicacaoSessao.motorista = (Motorista) usuario;
					tela.carregarTela("/visual/TelaMotorista.fxml");
				} else if (usuario instanceof Passageiro) {
					AplicacaoSessao.passageiro = (Passageiro) usuario;
					tela.carregarTela("/visual/TelaStatusPassageiro.fxml");
				}
			} else {
				mensagens.erroSenha();
			}
		}
	}
}
