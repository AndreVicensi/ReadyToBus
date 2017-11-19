package application;

import componente.TextFieldFormatter;
import dao.DaoFactory;
import dao.EmpresaDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import metodos.MetodosTelas;
import model.Empresa;

public class CadastroEmpresaController {

	@FXML
	private TextField tfLogin;

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	@FXML
	private PasswordField pfSenha;

	@FXML
	private TextField tfNomeEmpresa;

	@FXML
	private TextField tfCnpj;

	@FXML
	private PasswordField pfConfirmarSenha;

	private MetodosTelas tela = new MetodosTelas();
	private Empresa empresa;
	private static EmpresaDao empresaDao = DaoFactory.get().empresaDao();
	private Mensagens msg = new Mensagens();

	@FXML
	public void initialize() {

	}
	
	@FXML
	void onSalvar(ActionEvent event) {
		if (pfSenha.getText().equals(pfConfirmarSenha.getText())) {
			empresa = new Empresa(tfNomeEmpresa.getText(), tfCnpj.getText(), tfLogin.getText(), pfSenha.getText());
			empresaDao.inserir(empresa);
			limparCampos();
			msg.salvo();
			tela.carregarTela("/visual/TelaLogin.fxml");
		} else {
			msg.erroSenha();
		}
	}

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

	private void limparCampos() {
		tfNomeEmpresa.setText("");
		tfCnpj.setText("");
		tfLogin.setText("");
		pfSenha.setText("");
		pfConfirmarSenha.setText("");
	}
	
	@FXML
	void tfCnpjKeyReleased(KeyEvent event) {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("##.###.###/####-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfCnpj);
		tff.formatter();

	}

}
