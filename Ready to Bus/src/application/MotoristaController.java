package application;

import java.time.LocalTime;

import dao.DaoFactory;
import dao.ViagemDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import metodos.MetodosTelas;
import model.Passageiro_Viagem;
import model.Viagem;

public class MotoristaController {

	@FXML
	private TableView<Passageiro_Viagem> tblLista;

	@FXML
	private TableColumn<Passageiro_Viagem, String> tbcPassageiro;

	@FXML
	private TableColumn<Passageiro_Viagem, String> tbcTelefone;

	@FXML
	private TableColumn<Passageiro_Viagem, String> tbcStatus;

	@FXML
	private TableColumn<Passageiro_Viagem, Image> tbcCheck;

	@FXML
	private Button btnDirigir;

	@FXML
	private Button btnChegada;

	@FXML
	private Label ldataDia;

	@FXML
	private ImageView imgDirigindo;

	@FXML
	private Button btnSair;

	private MetodosTelas tela = new MetodosTelas();
	// private static MotoristaDao motoristaDao =
	// DaoFactory.get().motoristaDao();
	private static ViagemDao viagemDao = DaoFactory.get().viagemDao();
	public Viagem viagem;
	private Image dirigindo = new Image("/arquivos/sim.png");
	private Image chegada = new Image("/arquivos/nao.png");
	LocalTime tempo = LocalTime.now();

	@FXML
	void onChegada(ActionEvent event) {
		// esses metodos criados no MotoristaFdbc nao tao funcionando
		// viagemDao.alterarDiringindo(viagem, false);
		imgDirigindo.setImage(chegada);
		viagem.setChegada(tempo);
		viagemDao.alterarChegada(viagem, tempo);
	}

	@FXML
	void onDirigir(ActionEvent event) {
		// viagemDao.alterarDiringindo(viagem, true);
		imgDirigindo.setImage(dirigindo);
		viagem.setSaida(tempo);
		viagemDao.alterarSaida(viagem, tempo);

	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

}
