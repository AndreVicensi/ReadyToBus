package application;

import dao.DaoFactory;
import dao.ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import metodos.MetodosTelas;
import model.Viagem;

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
	private Button btnVerLista;

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
	
	public void initialize() {
		tela.carregarImagem(imgDirigindo, true);
		
		cbxViagem.setItems(FXCollections.observableArrayList(viagemDao.listar()));
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
	void verLista(ActionEvent event) {
		// System.out.println(viagemDao.get(3).getDirigindo());
		ListaViagemController.codviagem = cbxViagem.getValue().getIdViagem();
		tela.carregarTela("/visual/TelaListaViagem.fxml");
		
	}
	
	@FXML
	void onSetarValor(ActionEvent event) {
	lNomeMotorista.setText(cbxViagem.getValue().getRota().getMotorista().getNome());
	lRota.setText(cbxViagem.getValue().getRota().getText());
	lApelidoMotorista.setText(cbxViagem.getValue().getRota().getMotorista().getApelido());
	lSaidaIda.setText(cbxViagem.getValue().getSaida().toString());
	lChegadaIda.setText(cbxViagem.getValue().getChegada().toString());
	
	}


	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

}
