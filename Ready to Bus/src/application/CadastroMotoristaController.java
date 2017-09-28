package application;

import dao.DaoFactory;
import dao.MotoristaDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import metodos.MetodosTelas;
import model.Empresa;
import model.Motorista;

public class CadastroMotoristaController {

	@FXML
	private TextField tfLogin;

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	@FXML
	private PasswordField pfSenha;

	@FXML
	private TextField tfNomeMotorista;

	@FXML
	private TextField tfApelido;

	@FXML
	private PasswordField pfConfirmarSenha;

	private MetodosTelas tela = new MetodosTelas();
	private Motorista motorista;
	private Empresa empresa;
	private static MotoristaDao motoristaDao = DaoFactory.get().motoristaDao();

	@FXML
	void onSalvar(ActionEvent event) {
		motorista = new Motorista(tfNomeMotorista.getText(), tfApelido.getText(), tfLogin.getText(), pfSenha.getText(),
				empresa);
		motoristaDao.inserir(motorista);
		limparCampos();
	}

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

	private void limparCampos() {
		tfNomeMotorista.setText("");
		tfApelido.setText("");
		tfLogin.setText("");
		pfSenha.setText("");
		pfConfirmarSenha.setText("");
	}
}
