package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Passageiro;

public class CadastroViagemController {

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	@FXML
	private ComboBox<?> cbxRota;

	@FXML
	private ComboBox<Passageiro> cbxPassageiro;

	@FXML
	void onSalvar(ActionEvent event) {

	}

	@FXML
	void onVoltar(ActionEvent event) {

	}

}
