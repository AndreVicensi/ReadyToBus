package application;

import java.time.LocalTime;

import dao.DaoFactory;
import dao.Passageiro_ViagemDao;
import dao.ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import metodos.AplicacaoSessao;
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
	public Viagem viagem = new Viagem();
	// private MetodosLogin login = new MetodosLogin();
	private Image dirigindo = new Image("/visual/sim.png");
	private Image chegada = new Image("/visual/nao.png");
	LocalTime tempo;

	public static Integer codmotorista;

	private static Passageiro_ViagemDao passageiroViagemDao = DaoFactory.get().passageiro_ViagemDao();
	

	public void initialize() {

		//viagem = viagemDao.get();
		tbcPassageiro.setCellValueFactory(new PropertyValueFactory<>("passageiro"));
		tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("TelefoneNumero"));
		tbcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tbcCheck.setCellValueFactory(new PropertyValueFactory<>("ImagemClassificao"));
		
		codmotorista = AplicacaoSessao.motorista.getIdMotorista();
		tblLista.setItems(
				FXCollections.observableArrayList(passageiroViagemDao.ListaMotorista(codmotorista)));
		tbcCheck.setCellFactory(
				new Callback<TableColumn<Passageiro_Viagem, Image>, TableCell<Passageiro_Viagem, Image>>() {
					@Override
					public TableCell<Passageiro_Viagem, Image> call(TableColumn<Passageiro_Viagem, Image> param) {
						final ImageView imageView = new ImageView();
						TableCell<Passageiro_Viagem, Image> cell = new TableCell<Passageiro_Viagem, Image>() {
							protected void updateItem(Image item, boolean empty) {
								if (item != null) {
									imageView.setFitHeight(20);
									imageView.setFitWidth(20);
									imageView.setImage(item);
								}
							}
						};
						cell.setGraphic(imageView);
						return cell;
					}
				});
	}

	@FXML
	void onChegada(ActionEvent event) {
		// viagem é null entao nao vai funcionar mesmo!!!!!!!
		// viagemDao.alterarDiringindo(viagem, false);
		tempo = LocalTime.now();
		imgDirigindo.setImage(chegada);
		viagem.setChegada(tempo);
		viagemDao.alterarChegada(viagem, tempo);
	}

	@FXML
	void onDirigir(ActionEvent event) {
		viagemDao.alterarDiringindo(viagem, true);
		tempo = LocalTime.now();
		imgDirigindo.setImage(dirigindo);
		viagem.setSaida(tempo);
		viagemDao.alterarSaida(viagem, tempo);
	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

}
