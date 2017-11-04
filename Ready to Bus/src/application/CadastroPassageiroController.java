package application;

import dao.DaoFactory;
import dao.PassageiroDao;
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
import metodos.MetodosTelas;
import model.Passageiro;

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
	private TextField tfCpfPassageiro;

	@FXML
	private TextField tfTelefonePassageiro;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	@FXML
	private TableView<Passageiro> tblPassageiro;

	@FXML
	private TableColumn<Passageiro, Passageiro> tbcNomePassageiro;

	MetodosTelas tela = new MetodosTelas();
	private static PassageiroDao passageiroDao = DaoFactory.get().passageiroDao();
	private Passageiro passageiro;
	private Mensagens msg = new Mensagens();
	private boolean editando;

	public void initialize() {

		// cbxRota.setItems(FXCollections.observableArrayList(viagemDao.listar()));
		tbcNomePassageiro.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tblPassageiro.setItems(FXCollections.observableArrayList(passageiroDao.listar()));
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

		passageiro = tblPassageiro.getSelectionModel().getSelectedItem();
		tfNomePassageiro.setText(passageiro.getNome());
		tfCpfPassageiro.setText(passageiro.getCpf());
		tfTelefonePassageiro.setText(passageiro.getTelefone());
		tfLogin.setText(passageiro.getLogin());
		pfSenha.setText(passageiro.getSenha());
		pfConfirmarSenha.setText(passageiro.getSenha());

		editando = true;
	}

	@FXML
	void onSalvar(ActionEvent event) {
		if (pfSenha.getText().equals(pfConfirmarSenha.getText())) {
			passageiro.setNome(tfNomePassageiro.getText());
			passageiro.setLogin(tfLogin.getText());
			passageiro.setSenha(pfSenha.getText());
			passageiro.setCpf(tfCpfPassageiro.getText());
			passageiro.setTelefone(tfTelefonePassageiro.getText());

			if (editando) {
				passageiroDao.alterar(passageiro);
				tblPassageiro.refresh(); // atualiza

			} else {
				passageiroDao.inserir(passageiro);
				tblPassageiro.getItems().add(passageiro); // adiciona na lista
			}

			msg.salvo();
			novo();
		} else {
			msg.erroSenha();
		}
	}

	@FXML
	void onExcluir(ActionEvent event) {

		tblPassageiro.getItems().remove(passageiro);
		passageiroDao.excluir((passageiro.getIdPassageiro()));
		novo();

	}

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaEmpresa.fxml");

	}

	void limparCampos() {
		tfCpfPassageiro.setText("");
		tfNomePassageiro.setText("");
		tfLogin.setText("");
		tfTelefonePassageiro.setText("");
		pfSenha.setText("");
		pfConfirmarSenha.setText("");
	}

	private void novo() {
		editando = false;
		passageiro = new Passageiro();
		limparCampos();
	}

}
