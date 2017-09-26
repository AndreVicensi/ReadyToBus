package application;

import dao.DaoFactory;
import dao.EmpresaDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import metodos.MetodosTelas;
import model.Empresa;

public class CadastroEmpresaController {

    @FXML
    private TextField tfLogin;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEntrar;

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
    private static EmpresaDao empresaDao = DaoFactory.get().EmpresaDao();
    
    @FXML
    void onSalvar(ActionEvent event) {
    	empresa = new Empresa(tfNomeEmpresa.getText(),tfCnpj.getText(),tfLogin.getText(),pfSenha.getText());
    	empresaDao.inserir(empresa);
    	novo();
    }

    @FXML
    void onVoltar(ActionEvent event) {

    	tela.carregarTela("/visual/TelaLogin.fxml");
    }
    
	private void novo() {
		empresa = new Empresa();
		limparCampos();
	}

	private void limparCampos() {
		tfNomeEmpresa.setText("");
		tfCnpj.setText("");
		tfLogin.setText("");
		pfSenha.setText("");
	}

}
