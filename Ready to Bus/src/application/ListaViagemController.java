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
	private TableColumn<Passageiro_Viagem, String> tbcStatus;

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
	
	public void initialize() {
		// esta vindo vazio
		//lApelidoMotorista.setText(passageiroViagemDao.getMotorista(codviagem).getNome());
		
		tbcPassageiro.setCellValueFactory(new PropertyValueFactory<>("passageiro"));
		tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("TelefoneNumero"));
		tbcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tbcCheck.setCellValueFactory(new PropertyValueFactory<>(""));
		tblLista.setItems(
				FXCollections.observableArrayList(passageiroViagemDao.ListaViagem(codviagem)));
		
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
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

	@FXML
	void onVoltar(ActionEvent event) {

		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

}
