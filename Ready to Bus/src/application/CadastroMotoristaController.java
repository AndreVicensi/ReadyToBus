package application;

import dao.DaoFactory;
import dao.MotoristaDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import metodos.AplicacaoSessao;
import metodos.MetodosTelas;
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

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	@FXML
	private TableView<Motorista> tblMotorista;

	@FXML
	private TableColumn<Motorista, Motorista> tbcNomeMotorista;

	private boolean editando;
	private MetodosTelas tela = new MetodosTelas();
	private Motorista motorista;
	private Mensagens msg = new Mensagens();
	private static MotoristaDao motoristaDao = DaoFactory.get().motoristaDao();

	@FXML
	public void initialize() {
		tbcNomeMotorista.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tblMotorista.setItems(FXCollections.observableArrayList(motoristaDao.listarDaEmpresa(AplicacaoSessao.empresa.getIdEmpresa())));
		novo();
	}

	@FXML
	void onNovo(ActionEvent event) {
		novo();
	}

	@FXML
	void onEditar(MouseEvent event) {
		if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
			;

		motorista = tblMotorista.getSelectionModel().getSelectedItem();
		tfNomeMotorista.setText(motorista.getNome());
		tfApelido.setText(motorista.getApelido());
		tfLogin.setText(motorista.getLogin());
		pfSenha.setText(motorista.getSenha());
		pfConfirmarSenha.setText(motorista.getSenha());
		motorista.setEmpresa(AplicacaoSessao.empresa);

		editando = true;
	}

	@FXML
	void onSalvar(ActionEvent event) {
		if (pfSenha.getText().equals(pfConfirmarSenha.getText())) {
			motorista.setNome(tfNomeMotorista.getText());
			motorista.setApelido(tfApelido.getText());
			motorista.setLogin(tfLogin.getText());
			motorista.setSenha(pfSenha.getText());
			motorista.setEmpresa(AplicacaoSessao.empresa);
			if (editando) {
				motoristaDao.alterar(motorista);
				tblMotorista.refresh(); // atualiza

			} else {
				motoristaDao.inserir(motorista);
				tblMotorista.getItems().add(motorista); // adiciona na lista
			}

			msg.salvo();
			novo();
		} else {
			msg.erroSenha();
		}
	}

	@FXML
	void onExcluir(ActionEvent event) {

		tblMotorista.getItems().remove(motorista);
		motoristaDao.excluir((motorista.getIdMotorista()));
		novo();

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

	private void novo() {
		editando = false;
		motorista = new Motorista();
		limparCampos();
	}

}
