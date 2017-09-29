package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import metodos.MetodosTelas;

public class CadastroPassageiroController {

	@FXML
	private TextField tfLogin;

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	@FXML
	private PasswordField pfSenha;

	@FXML
	private TextField tfNomePassageiro;

	@FXML
	private PasswordField pfConfirmarSenha;

	@FXML
	private ComboBox<?> cbxRota;

	// @FXML
	// private ComboBox<Rota> cbxRota;

	@FXML
	private TextField tfCpfPassageiro;

	@FXML
	private TextField tfTelefonePassageiro;

	MetodosTelas tela = new MetodosTelas();

	// private static ViagemDao viagemDao = DaoFactory.get().ViagemDao();

	void initialize() {
		// cbxRota.setItems(FXCollections.observableArrayList(rotaDao.listar()));
	}

	@FXML
	void onSalvar(ActionEvent event) {

	}

	@FXML
	void onVoltar(ActionEvent event) {

		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

}
