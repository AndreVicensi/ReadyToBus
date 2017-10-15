package application;

import dao.DaoFactory;
import dao.Passageiro_ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import metodos.AplicacaoSessao;
import metodos.MetodosTelas;
import model.Passageiro;
import model.Passageiro_Viagem;
import model.Viagem;

public class ListaPassageiroController {

	@FXML
	private TableView<Passageiro> tblLista;

	@FXML
	private TableColumn<Passageiro, String> tbcPassageiro;

	@FXML
	private TableColumn<Passageiro, Integer> tbcTelefone;

	@FXML
	private TableColumn<Passageiro_Viagem, Integer> tbcStatus;

	@FXML
	private TableColumn<Passageiro_Viagem, ImageView> tbcCheck;

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
		//lApelidoMotorista.setText(AplicacaoSessao.viagem.getRota().getMotorista().getNome());
		tbcPassageiro.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tbcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tbcCheck.setCellValueFactory(new PropertyValueFactory<>("confirmacao"));
		tblLista.setItems(FXCollections.observableArrayList(passageiroViagemDao.Lista(AplicacaoSessao.passageiro_viagem)));
	}

	@FXML
	void onAlterarStatus(ActionEvent event) {
		tela.carregarTela("/visual/TelaStatusPassageiro.fxml");
	}

	@FXML
	void onConfirmarEmbarque(ActionEvent event) {

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
