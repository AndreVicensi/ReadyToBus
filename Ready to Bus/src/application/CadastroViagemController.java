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
import metodos.AplicacaoSessao;
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
	private Viagem viagemida;
	private Viagem viagemvolta;
	private Mensagens msg = new Mensagens();

	@FXML
	public void initialize() {
		cbxRota.setItems(
				FXCollections.observableArrayList(rotaDao.listarDaEmpresa(AplicacaoSessao.empresa.getIdEmpresa())));
	}

	@FXML
	void onSalvar(ActionEvent event) {
		//criar hora de saída

		LocalTime tempozerado = LocalTime.of(00, 00);
		
		viagemida = new Viagem(dpData.getValue(), tempozerado, tempozerado, false, false, cbxRota.getValue());
		viagemDao.inserir(viagemida);

		viagemvolta = new Viagem(dpData.getValue(), tempozerado, tempozerado, false, true, cbxRota.getValue());
		viagemDao.inserir(viagemvolta);
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
