package application;

import java.time.LocalTime;

import componente.TextFieldFormatter;
import dao.DaoFactory;
import dao.RotaDao;
import dao.ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
	private TextField tfSaidaIda;

	@FXML
	private TextField tfSaidaVolta;

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

	String vazio = "";

	@FXML
	public void initialize() {
		cbxRota.setItems(
				FXCollections.observableArrayList(rotaDao.listarDaEmpresa(AplicacaoSessao.empresa.getIdEmpresa())));

		tfSaidaIda.setPromptText("00:00");
		tfSaidaVolta.setPromptText("00:00");
	}

	@FXML
	void onSalvar(ActionEvent event) {

		if (tfSaidaIda.equals(vazio) || dpData.equals(null) || cbxRota.equals(vazio)) {
			LocalTime tempozerado = LocalTime.of(00, 00);

			String tempo1 = tfSaidaIda.getText().substring(0, 2);
			String tempo2 = tfSaidaIda.getText().substring(3, 5);
			int hora = Integer.parseInt(tempo1);
			int minuto = Integer.parseInt(tempo2);
			LocalTime temposaidaida = LocalTime.of(hora, minuto);

			String tempo3 = tfSaidaVolta.getText().substring(0, 2);
			String tempo4 = tfSaidaVolta.getText().substring(3, 5);
			int hora1 = Integer.parseInt(tempo3);
			int minuto1 = Integer.parseInt(tempo4);
			LocalTime temposaidavolta = LocalTime.of(hora1, minuto1);

			viagemida = new Viagem(dpData.getValue(), temposaidaida, tempozerado, false, true, cbxRota.getValue());
			viagemDao.inserir(viagemida);

			viagemvolta = new Viagem(dpData.getValue(), temposaidavolta, tempozerado, false, false, cbxRota.getValue());
			viagemDao.inserir(viagemvolta);
			msg.salvo();
			limparCampos();
		} else {
			msg.erroPrenchimento();
		}
	}

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

	void limparCampos() {
		cbxRota.setValue(null);
		dpData.setValue(null);
		tfSaidaIda.setText("");
		tfSaidaVolta.setText("");
	}

	@FXML
	void tfSaidaIdaKeyReleased(KeyEvent event) {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("##:##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfSaidaIda);
		tff.formatter();

	}

	@FXML
	void tfSaidaVoltaKeyReleased(KeyEvent event) {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("##:##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfSaidaVolta);
		tff.formatter();
	}

}
