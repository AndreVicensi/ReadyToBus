package application;

import java.util.Timer;
import java.util.TimerTask;

import dao.DaoFactory;
import dao.Passageiro_ViagemDao;
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
import metodos.MetodosTelas;
import model.Passageiro_Viagem;

public class ListaViagemController {

	@FXML
	private TableView<Passageiro_Viagem> tblLista;

	@FXML
	private TableColumn<Passageiro_Viagem, String> tbcPassageiro;

	@FXML
	private TableColumn<Passageiro_Viagem, String> tbcTelefone;

	@FXML
	private TableColumn<Passageiro_Viagem, Image> tbcStatus;

	@FXML
	private TableColumn<Passageiro_Viagem, Image> tbcCheck;

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSair;

	@FXML
	private Label lSaidaIda;

	@FXML
	private Label lChegadaIda;

	@FXML
	private Label lSaidaVolta;

	@FXML
	private Label lChegadaVolta;

	@FXML
	private Label lApelidoMotorista;

	private MetodosTelas tela = new MetodosTelas();

	public static Integer codviagem;

	private static Passageiro_ViagemDao passageiroViagemDao = DaoFactory.get().passageiro_ViagemDao();

	public static final long TEMPO = (1000 * 10); // atualiza a cada 10 segundos

	public void initialize() {

		tbcPassageiro.setCellValueFactory(new PropertyValueFactory<>("passageiro"));
		tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("TelefoneNumero"));
		tbcStatus.setCellValueFactory(new PropertyValueFactory<>("ImagemStatus"));
		tbcCheck.setCellValueFactory(new PropertyValueFactory<>("ImagemCheck"));

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

		tbcStatus.setCellFactory(
				new Callback<TableColumn<Passageiro_Viagem, Image>, TableCell<Passageiro_Viagem, Image>>() {
					@Override
					public TableCell<Passageiro_Viagem, Image> call(TableColumn<Passageiro_Viagem, Image> param) {
						final ImageView imageView1 = new ImageView();
						TableCell<Passageiro_Viagem, Image> cell = new TableCell<Passageiro_Viagem, Image>() {
							protected void updateItem(Image item1, boolean empty) {
								if (item1 != null) {
									imageView1.setFitHeight(20);
									imageView1.setFitWidth(100);
									imageView1.setImage(item1);
								}
							}
						};
						cell.setGraphic(imageView1);
						return cell;
					}
				});

		tblLista.setItems(FXCollections.observableArrayList(passageiroViagemDao.ListaViagem(codviagem)));

		carregarLista();
	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

	@FXML
	void onVoltar(ActionEvent event) {

		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

	public void carregarLista() {

		Timer timer = null;
		if (timer == null) {
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {
				public void run() {
					try {
						tblLista.setItems(
								FXCollections.observableArrayList(passageiroViagemDao.ListaViagem(codviagem)));
						// chamar metodo
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
		}
	}

}
