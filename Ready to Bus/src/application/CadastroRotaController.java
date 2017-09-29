package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import metodos.MetodosTelas;

public class CadastroRotaController {

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	@FXML
	private TextField tfNomeRota;

	@FXML
	private ComboBox<?> cbxMotorista;

	MetodosTelas tela = new MetodosTelas();

	@FXML
	void onSalvar(ActionEvent event) {

	}

	@FXML
	void onVoltar(ActionEvent event) {

		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

}
