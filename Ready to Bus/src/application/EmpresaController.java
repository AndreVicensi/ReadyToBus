package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import metodos.MetodosTelas;

public class EmpresaController {

	@FXML
	private Label lRota1;

	@FXML
	private Button btnCadsatroMotorista;

	@FXML
	private Button btnCadastroRota;

	@FXML
	private Button btnCadastroPassageiro;

	@FXML
	private Label lApelidoMotorista1;

	@FXML
	private Button btnVerLista1;

	@FXML
	private ImageView imgDirigindo1;

	@FXML
	private Label lRota2;

	@FXML
	private Label lApelidoMotorista2;

	@FXML
	private Button btnVerLista2;

	@FXML
	private ImageView imgDirigindo2;

	@FXML
	private Label lRota3;

	@FXML
	private Label lApelidoMotorista3;

	@FXML
	private Button btnVerLista3;

	@FXML
	private ImageView imgDirigindo3;

	private MetodosTelas tela = new MetodosTelas();

	@FXML
	void cadastrarMotorista(ActionEvent event) {

		tela.carregarTela("/visual/TelaCadastroMotorista.fxml");
	}

	@FXML
	void cadastrarPassageiro(ActionEvent event) {

		tela.carregarTela("/visual/TelaCadastroPassageiro.fxml");
	}

	@FXML
	void cadastrarRota(ActionEvent event) {

		tela.carregarTela("/visual/TelaCadastroRota.fxml");
	}

	@FXML
	void verLista1(ActionEvent event) {

	}

	@FXML
	void verLista2(ActionEvent event) {

	}

	@FXML
	void verLista3(ActionEvent event) {

	}

}
