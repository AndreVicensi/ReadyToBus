
package application;

import componente.ListCellBean;
import componente.StringConverterBean;
import dao.DaoFactory;
import dao.Passageiro_ViagemDao;
import dao.ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import metodos.AplicacaoSessao;
import metodos.MetodosTelas;
import model.Passageiro_Viagem;
import model.Viagem;

public class StatusPassageiroController {

	@FXML
	private Button btnVaiVolta;

	@FXML
	private Button btnNaoVai;

	@FXML
	private Button btnSoVai;

	@FXML
	private Button btnSoVolta;

	@FXML
	private Button btnSair;

	@FXML
	private ComboBox<Viagem> cbxViagem;

	private MetodosTelas tela = new MetodosTelas();

	private static Passageiro_ViagemDao passageiroViagemDao = DaoFactory.get().passageiro_ViagemDao();
	private static ViagemDao viagemDao = DaoFactory.get().viagemDao();

	private Passageiro_Viagem passageiro_viagem;

	public void initialize() {

		if (ListaPassageiroController.temViagem) {
			// fazer combobox vir preenchido já

			cbxViagem.setValue(ListaPassageiroController.getViagem());

		}

		cbxViagem.setCellFactory((comboBox) -> {
			return new ListCellBean<Viagem>();
		});
		cbxViagem.setConverter(new StringConverterBean<>());

		cbxViagem.setItems(FXCollections.observableArrayList(viagemDao.listar()));

	}

	@FXML
	void naoVai(ActionEvent event) {
		passageiroViagemDao.alterarStatus(AplicacaoSessao.passageiro, 2, cbxViagem.getValue());
		tela.carregarTela("/visual/TelaListaPassageiro.fxml");
	}

	@FXML
	void soVai(ActionEvent event) {
		passageiroViagemDao.alterarStatus(AplicacaoSessao.passageiro, 3, cbxViagem.getValue());
		tela.carregarTela("/visual/TelaListaPassageiro.fxml");
	}

	@FXML
	void soVolta(ActionEvent event) {
		passageiroViagemDao.alterarStatus(AplicacaoSessao.passageiro, 4, cbxViagem.getValue());
		tela.carregarTela("/visual/TelaListaPassageiro.fxml");
	}

	@FXML
	void vaiVolta(ActionEvent event) {
		passageiroViagemDao.alterarStatus(AplicacaoSessao.passageiro, 1, cbxViagem.getValue());
		tela.carregarTela("/visual/TelaListaPassageiro.fxml");
	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

	@FXML
	void onSetarValor(ActionEvent event) {
		passageiro_viagem = new Passageiro_Viagem(AplicacaoSessao.passageiro, cbxViagem.getValue());
		passageiroViagemDao.inserir(passageiro_viagem);
		AplicacaoSessao.viagem = cbxViagem.getValue();
		AplicacaoSessao.passageiro_viagem = passageiro_viagem;

		ListaPassageiroController.setViagem(cbxViagem.getValue());
	}
}
