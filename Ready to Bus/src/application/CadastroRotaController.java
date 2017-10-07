package application;

import dao.DaoFactory;
import dao.MotoristaDao;
import dao.RotaDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import metodos.MetodosTelas;
import model.Motorista;
import model.Rota;

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
	private static RotaDao rotaDao = DaoFactory.get().rotaDao();
	private Rota rota;
	private Mensagens msg = new Mensagens();

	@FXML
	public void initialize() {
		cbxMotorista.setItems(FXCollections.observableArrayList(motoristaDao.listar()));
	}

	@FXML
	void onSalvar(ActionEvent event) {
		rota = new Rota(cbxMotorista.getValue(), tfNomeRota.getText());
		rotaDao.inserir(rota);
		msg.salvo();
		limparCampos();
	}

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

	
   void limparCampos() {
	  tfNomeRota.setText("");
	  cbxMotorista.setValue(null);
  }
}
