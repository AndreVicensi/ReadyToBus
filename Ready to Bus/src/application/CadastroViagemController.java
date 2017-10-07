package application;

import java.time.LocalTime;

import dao.DaoFactory;
import dao.RotaDao;
import dao.ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import metodos.MetodosTelas;
import model.Rota;
import model.Viagem;

public class CadastroViagemController {


	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	@FXML
	private ComboBox<Rota> cbxRota;

	@FXML
	private DatePicker dpData;

	MetodosTelas tela = new MetodosTelas();

	private static RotaDao rotaDao = DaoFactory.get().rotaDao();
	private static ViagemDao viagemDao = DaoFactory.get().viagemDao();
	private Viagem viagem;
	private Mensagens msg = new Mensagens();

	@FXML
	public void initialize() {
		cbxRota.setItems(FXCollections.observableArrayList(rotaDao.listar()));
	}

	@FXML
	void onSalvar(ActionEvent event) {

		LocalTime tempo = LocalTime.now();
		viagem = new Viagem(dpData.getValue(), tempo, tempo, false, cbxRota.getValue());
		viagemDao.inserir(viagem);
		msg.salvo();
		limparCampos();
	}

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

	void limparCampos() {
		cbxRota.setValue(null);
		dpData.setValue(null);
	}
}
