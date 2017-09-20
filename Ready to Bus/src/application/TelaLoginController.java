package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
		mostrarSobre();
	}

	@FXML
	void onCadastrar(ActionEvent event) {
		tela.carregarTela("/visual/TelaCadastroEmpresa.fxml");
	}



	public void mostrarSobre() {
		Stage stage = new Stage();

		Text texto = new Text();
		stage.setScene(new Scene(new StackPane(texto)));
		stage.setTitle("Importante");
		texto.setText("True self é uma espécie de rede social offline\n\n\nCriado com o intuito de as pessoas"
				+ " interagirem entre elas,\nfazendo comentários(bons ou ruins) uma sobre as outras\n\nDesenvolvido por acadêmicos da UNOESC- Xanxerê "
				+ "do curso de\nTecnologia em Análise e Desenvolvimento de Sistemas - 3ª fase\n\nAndre Vicensi Uliana e João Victor Scanagatta\n\n\n\n TrueSelf® 2017");

		stage.setWidth(390);
		stage.setHeight(300);
		stage.show();
	}

}
