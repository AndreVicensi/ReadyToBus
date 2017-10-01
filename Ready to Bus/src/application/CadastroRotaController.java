package application;

import dao.DaoFactory;
import dao.MotoristaDao;
import dao.ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import metodos.MetodosTelas;
import model.Motorista;
import model.Viagem;

public class CadastroRotaController {

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	@FXML
	private TextField tfNomeRota;

	@FXML
	private ComboBox<Motorista> cbxMotorista;

	MetodosTelas tela = new MetodosTelas();
	private MotoristaDao motoristaDao = DaoFactory.get().motoristaDao();
	private static ViagemDao viagemDao = DaoFactory.get().viagemDao();
	private Viagem viagem;

	@FXML
	public void initialize() {
		cbxMotorista.setItems(FXCollections.observableArrayList(motoristaDao.listar()));
	}

	@FXML
	void onSalvar(ActionEvent event) {
		viagem = new Viagem(cbxMotorista.getValue(), tfNomeRota.getText());
		viagemDao.inserir(viagem);
	}

	@FXML
	void onVoltar(ActionEvent event) {

		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

}
