package application;

import dao.DaoFactory;
import dao.PassageiroDao;
import dao.ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import metodos.MetodosTelas;
import model.Passageiro;
import model.Viagem;

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
	private ComboBox<Viagem> cbxRota;

	@FXML
	private TextField tfCpfPassageiro;

	@FXML
	private TextField tfTelefonePassageiro;

	MetodosTelas tela = new MetodosTelas();
	private static PassageiroDao passageiroDao = DaoFactory.get().passageiroDao();
	private Passageiro passageiro;
	private ViagemDao viagemDao = DaoFactory.get().viagemDao();
	private Mensagens msg = new Mensagens();

	public void initialize() {
		cbxRota.setItems(FXCollections.observableArrayList(viagemDao.listar()));
	}

	@FXML
	void onSalvar(ActionEvent event) {
		passageiro = new Passageiro(0, tfNomePassageiro.getText(), tfLogin.getText(), pfSenha.getText(),
				tfCpfPassageiro.getText(), tfTelefonePassageiro.getText(), cbxRota.getValue());
		passageiroDao.inserir(passageiro);
		msg.salvo();
		limparCampos();
	}

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaEmpresa.fxml");
		System.out.println(viagemDao.listar().toString());
	}

	void limparCampos() {
		tfCpfPassageiro.setText("");
		tfNomePassageiro.setText("");
		tfLogin.setText("");
		tfTelefonePassageiro.setText("");
		cbxRota.setValue(null);
	}

}
