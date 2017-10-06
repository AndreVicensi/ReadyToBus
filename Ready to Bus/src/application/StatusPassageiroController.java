
package application;

import dao.DaoFactory;
import dao.MotoristaDao;
import dao.PassageiroDao;
import dao.Passageiro_ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import metodos.AplicacaoSessao;
import metodos.MetodosTelas;
import model.Passageiro;
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

	private MetodosTelas tela = new MetodosTelas();

	private static Passageiro_ViagemDao passageiroViagemDao = DaoFactory.get().passageiro_ViagemDao();

	private Passageiro_Viagem passageiro_viagem;

	public void initialize() {

		// passageiro_viagem = new Passageiro_Viagem(AplicacaoSessao.passageiro,
		// AplicacaoSessao.passageiro.getViagem());
		passageiroViagemDao.inserir(passageiro_viagem);
	}

	@FXML
	void naoVai(ActionEvent event) {
		passageiroViagemDao.alterarStatus(AplicacaoSessao.passageiro, 2);
	}

	@FXML
	void soVai(ActionEvent event) {
		passageiroViagemDao.alterarStatus(AplicacaoSessao.passageiro, 3);
	}

	@FXML
	void soVolta(ActionEvent event) {
		passageiroViagemDao.alterarStatus(AplicacaoSessao.passageiro, 4);
	}

	@FXML
	void vaiVolta(ActionEvent event) {
		passageiroViagemDao.alterarStatus(AplicacaoSessao.passageiro, 1);
	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

}
