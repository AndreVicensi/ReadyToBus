package application;

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
import metodos.AplicacaoSessao;
import metodos.MetodosTelas;
import model.Passageiro_Viagem;
import model.Viagem;

public class ListaPassageiroController {

	@FXML
	private TableView<Passageiro_Viagem> tblLista;

	@FXML
	private TableColumn<Passageiro_Viagem, String> tbcPassageiro;

	@FXML
	private TableColumn<Passageiro_Viagem, String> tbcTelefone;

	@FXML
	private TableColumn<Passageiro_Viagem, Integer> tbcStatus;

	@FXML
	private TableColumn<Passageiro_Viagem, Image> tbcCheck;

	@FXML
	private Button btnConfirmarEmbarque;

	@FXML
	private Button btnAlterarStatus;

	@FXML
	private Label ldataDia;

	@FXML
	private Label lApelidoMotorista;

	@FXML
	private Button btnSair;

	public static Boolean temViagem = true;

	private MetodosTelas tela = new MetodosTelas();
	private static Passageiro_ViagemDao passageiroViagemDao = DaoFactory.get().passageiro_ViagemDao();

	private static Viagem viagem;

	public void initialize() {
		ldataDia.setText(AplicacaoSessao.viagem.getData().toString());

		// abaixo consigo pegar o nome da rota, mas se tento pegar o nome do
		// motorista nao da
		lApelidoMotorista.setText(AplicacaoSessao.passageiro_viagem.getViagem().getRota().getMotorista().getNome());
		
		
		tbcPassageiro.setCellValueFactory(new PropertyValueFactory<>("passageiro"));
		tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("TelefoneNumero"));
		tbcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tblLista.setItems(
				FXCollections.observableArrayList(passageiroViagemDao.Lista(AplicacaoSessao.passageiro_viagem)));
		tbcCheck.setCellFactory(
				new Callback<TableColumn<Passageiro_Viagem, Image>, TableCell<Passageiro_Viagem, Image>>() {
					@Override
					public TableCell<Passageiro_Viagem, Image> call(TableColumn<Passageiro_Viagem, Image> param) {
						final ImageView imageView = new ImageView();
						imageView.setFitWidth(20);
						imageView.setFitHeight(20);
						TableCell<Passageiro_Viagem, Image> cell = new TableCell<Passageiro_Viagem, Image>() {
							protected void updateItem(Image item, boolean empty) {
								if (item != null) {
									imageView.setImage(item);
								}
							}
						};
						cell.setGraphic(imageView);
						return cell;
					}
				});
		tbcCheck.setCellValueFactory(new PropertyValueFactory<>("ImagemClassificao"));
	}

	@FXML
	void onAlterarStatus(ActionEvent event) {
		tela.carregarTela("/visual/TelaStatusPassageiro.fxml");
	}

	@FXML
	void onConfirmarEmbarque(ActionEvent event) {
		passageiroViagemDao.fazerCheck(AplicacaoSessao.passageiro, !AplicacaoSessao.passageiro_viagem.isConfirmacao());
		tblLista.refresh();
	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

	public static void setViagem(Viagem viagem) {
		ListaPassageiroController.viagem = viagem;

	}

	public static Viagem getViagem() {
		return viagem;
	}

}
