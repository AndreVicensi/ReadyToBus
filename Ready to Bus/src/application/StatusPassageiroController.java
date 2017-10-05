
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import metodos.MetodosTelas;

public class StatusPassageiroController {

	@FXML
	private Button btnVaiVolta;

	@FXML
	private Button btnNaoVai;

	@FXML
	private Button btnSoVai;

	@FXML
	private Button btnSoVolta;

	@FXML
	private Button btnSair;

	private MetodosTelas tela = new MetodosTelas();

	@FXML
	void naoVai(ActionEvent event) {

	}

	@FXML
	void soVai(ActionEvent event) {

	}

	@FXML
	void soVolta(ActionEvent event) {

	}

	@FXML
	void vaiVolta(ActionEvent event) {

	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

}
