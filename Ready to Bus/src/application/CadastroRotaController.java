package application;

import dao.DaoFactory;
import dao.MotoristaDao;
import dao.RotaDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import metodos.AplicacaoSessao;
import metodos.MetodosTelas;
import model.Motorista;
import model.Rota;

public class CadastroRotaController {

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnNovo;

	@FXML
	private TableView<Rota> tblRotas;

	@FXML
	private TableColumn<Rota, Rota> tbcNomeRotas;

	@FXML
	private Button btnExcluir;

	@FXML
	private TextField tfNomeRota;

	@FXML
	private ComboBox<Motorista> cbxMotorista;

	MetodosTelas tela = new MetodosTelas();
	private MotoristaDao motoristaDao = DaoFactory.get().motoristaDao();
	private static RotaDao rotaDao = DaoFactory.get().rotaDao();
	private Rota rota;
	private Mensagens msg = new Mensagens();
	private boolean editando;

	String vazio = "";

	@FXML
	public void initialize() {
		cbxMotorista.setItems(FXCollections
				.observableArrayList(motoristaDao.listarDaEmpresa(AplicacaoSessao.empresa.getIdEmpresa())));
		tbcNomeRotas.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tblRotas.setItems(
				FXCollections.observableArrayList(rotaDao.listarDaEmpresa(AplicacaoSessao.empresa.getIdEmpresa())));
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
		rota = tblRotas.getSelectionModel().getSelectedItem();

		tfNomeRota.setText(rota.getNome());
		cbxMotorista.setValue(rota.getMotorista());

		editando = true;
	}

	@FXML
	void onSalvar(ActionEvent event) {
		if (tfNomeRota.equals(vazio) || cbxMotorista.equals(null)) {
			rota.setNome(tfNomeRota.getText());
			rota.setMotorista(cbxMotorista.getValue());

			if (editando) {
				rotaDao.alterar(rota);
				tblRotas.refresh(); // atualiza

			} else {
				rotaDao.inserir(rota);
				tblRotas.getItems().add(rota); // adiciona na lista
			}

			msg.salvo();
			novo();

		} else {
			msg.erroPrenchimento();
		}
	}

	@FXML
	void onExcluir(ActionEvent event) {

		tblRotas.getItems().remove(rota);
		rotaDao.excluir((rota.getIdRota()));
		novo();

	}

	private void novo() {
		editando = false;
		rota = new Rota();
		limparCampos();
	}

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

	void limparCampos() {
		tfNomeRota.setText("");
		cbxMotorista.setValue(null);
	}
}
