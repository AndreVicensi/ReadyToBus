package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Mensagens {
	
	public void erroSenha() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText("Ação inválida");
		alert.setContentText("Preencha a SENHA");
		alert.showAndWait();

	}


	public void erroPrenchimento() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText("Ação inválida");
		alert.setContentText("Preencha TODOS os campos");
		alert.showAndWait();
	}
	
	public void cadastroPassageiro() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText("Ação inválida");
		alert.setContentText("Preencha os campos Nome e Login");
		alert.showAndWait();
	}
	
	public void erroPrenchimentoLogin() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText("Ação inválida");
		alert.setContentText("Login ou senha incorretos");
		alert.showAndWait();
	}

	public void erroLoginJaExiste() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText("Ação inválida");
		alert.setContentText("Login já existe");
		alert.showAndWait();
	}

	public void senhaNaoExiste() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText("As senhas não são iguais");
		alert.setContentText("Digite a senha corretamente");
		alert.showAndWait();
	}
	
	public void salvo() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Salvo");
		alert.setHeaderText("salvo com sucesso");
		alert.showAndWait();
	}
}
