package application;

import dao.DaoFactory;
import dao.Passageiro_ViagemDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import metodos.MetodosTelas;
import model.Passageiro_Viagem;

public class ListaPassageiroController {

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
	private Button btnConfirmarEmbarque;

	@FXML
	private Button btnAlterarStatus;

	@FXML
	private Label ldataDia;

	@FXML
	private Label lApelidoMotorista;

	@FXML
	private Button btnSair;

	private MetodosTelas tela = new MetodosTelas();
	private static Passageiro_ViagemDao passageiroViagemDao = DaoFactory.get().passageiro_ViagemDao();
	
	public void initialize() {
		tbcPassageiro.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tbcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tbcCheck.setCellValueFactory(new PropertyValueFactory<>("confirmacao"));
		//como setar atributos de objetos diferentes num array list?
		//tblLista.setItems(FXCollections.observableArrayList(
		//passageiroViagemDao.viewLista(AplicacaoSessao.passageiro, AplicacaoSessao.passageiro_viagem));
	}

	@FXML
	void onAlterarStatus(ActionEvent event) {
		
	}

	@FXML
	void onConfirmarEmbarque(ActionEvent event) {

	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

}
