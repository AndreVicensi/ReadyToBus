package application;

import java.util.Timer;
import java.util.TimerTask;

import dao.DaoFactory;
import dao.ViagemDao;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import metodos.MetodosTelas;
import model.Viagem;
import model.Viagem_Ida_Volta;

public class EmpresaController {

	@FXML
	private Label lRota;

	@FXML
	private Button btnCadsatroMotorista;

	@FXML
	private Button btnCadastroRota;

	@FXML
	private Button btnCadastroPassageiro;

	@FXML
	private Label lApelidoMotorista;

	@FXML
	private Label lNomeMotorista;

	@FXML
	private Button btnVerListaIda;

	@FXML
	private Button btnVerListaVolta;

	@FXML
	private ImageView imgDirigindo;

	@FXML
	private Label lSaidaIda;

	@FXML
	private Label lChegadaIda;

	@FXML
	private Label lSaidaVolta;

	@FXML
	private Label lChegadaVolta;

	@FXML
	private Button btnSair;

	@FXML
	private Button btnCadastrarViagem;

	@FXML
	private Button btnRelatorios;

	@FXML
	private ComboBox<Viagem> cbxViagem;

	private MetodosTelas tela = new MetodosTelas();
	private static ViagemDao viagemDao = DaoFactory.get().viagemDao();

	public Viagem viagem;

	public static final long TEMPO = (1000 * 1); // atualiza o site a cada 10
													// segundos

	public void initialize() {

		cbxViagem.setItems(FXCollections.observableArrayList(viagemDao.listar()));

		if (ListaViagemController.temViagem) {
			// fazer combobox vir preenchido já

			cbxViagem.setValue(ListaViagemController.getViagem());
			// lNomeMotorista.setText(cbxViagem.getValue().getRota().getMotorista().getNome());
			ListaViagemController.setViagem(cbxViagem.getValue());

		}

		carregarLista();

	}

	@FXML
	void cadastrarMotorista(ActionEvent event) {

		tela.carregarTela("/visual/TelaCadastroMotorista.fxml");
	}

	@FXML
	void cadastrarPassageiro(ActionEvent event) {

		tela.carregarTela("/visual/TelaCadastroPassageiro.fxml");
	}

	@FXML
	void cadastrarRota(ActionEvent event) {

		tela.carregarTela("/visual/TelaCadastroRota.fxml");
	}

	@FXML
	void cadastrarViagem(ActionEvent event) {

		tela.carregarTela("/visual/TelaCadastroViagem.fxml");
	}

	@FXML
	void onRelatorios(ActionEvent event) {
		tela.carregarTela("/visual/TelaRelatorios.fxml");
	}

	@FXML
	void verListaIda(ActionEvent event) {

		if (cbxViagem.getValue().getIda().equals(true)) {
			ListaViagemController.codviagemIda = cbxViagem.getValue().getIdViagem();
			ListaViagemController.ida = true;
		} else {
			ListaViagemController.codviagemVolta = cbxViagem.getValue().getIdViagem() + 1;
			ListaViagemController.ida = false;
		}
		tela.carregarTela("/visual/TelaListaViagem.fxml");
	}

	@FXML
	void verListaVolta(ActionEvent event) {

		if (cbxViagem.getValue().getIda().equals(true)) {
			ListaViagemController.codviagemIda = cbxViagem.getValue().getIdViagem() - 1;
			ListaViagemController.ida = true;
		} else {
			ListaViagemController.codviagemVolta = cbxViagem.getValue().getIdViagem();
			ListaViagemController.ida = false;
		}
		tela.carregarTela("/visual/TelaListaViagem.fxml");
	}

	public void carregarLista() {

		Timer timer = null;
		if (timer == null) {
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {
				public void run() {
					try {
						// isso permite atualizar cmoponentes em otura thread
						Platform.runLater(() -> {
							cbxViagem.setItems(FXCollections.observableArrayList(viagemDao.listar()));
						});

						// chamar metodo

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
		}
	}

	@FXML
	void onSetarValor(ActionEvent event) {
		lNomeMotorista.setText(cbxViagem.getValue().getRota().getMotorista().getNome());
		lRota.setText(cbxViagem.getValue().getRota().getText());
		lApelidoMotorista.setText(cbxViagem.getValue().getRota().getMotorista().getApelido());

		ListaViagemController.setViagem(cbxViagem.getValue());

		if (cbxViagem.getValue().getDirigindo().equals(true)) {
			tela.carregarImagem(imgDirigindo, true);
		} else {
			tela.carregarImagem(imgDirigindo, false);
		}

		Viagem_Ida_Volta viagemIdaVolta = new Viagem_Ida_Volta();

		if (cbxViagem.getValue().getIda().equals(true)) {

			lSaidaIda.setText(cbxViagem.getValue().getSaida().toString());
			lChegadaIda.setText(cbxViagem.getValue().getChegada().toString());

			viagemIdaVolta.setVolta(viagemDao.getHoras(cbxViagem.getValue().getIdViagem() + 1));

			lSaidaVolta.setText(viagemIdaVolta.getVolta().getSaida().toString());
			lChegadaVolta.setText(viagemIdaVolta.getVolta().getSaida().toString());
		} else {

			viagemIdaVolta.setIda(viagemDao.getHoras(cbxViagem.getValue().getIdViagem() - 1));

			lSaidaIda.setText(viagemIdaVolta.getIda().getSaida().toString());
			lChegadaIda.setText(viagemIdaVolta.getIda().getSaida().toString());

			lSaidaVolta.setText(cbxViagem.getValue().getSaida().toString());
			lChegadaVolta.setText(cbxViagem.getValue().getChegada().toString());
		}

	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");

	}

}
