package application;

import java.io.InputStream;

import dao.DaoFactory;
import dao.EmpresaDao;
import dao.ViagemDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import metodos.AplicacaoSessao;
import metodos.MetodosTelas;
import model.Viagem;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class TelaRelatorios {

	@FXML
	private Button btnSair;

	@FXML
	private Button btnListaPassgeiros;

	@FXML
	private Button btnListaRotas;

	@FXML
	private Button btnListaViagems;

	@FXML
	private Button btnVoltar;

	@FXML
	private ToggleGroup familia;

	@FXML
	private ToggleGroup familia1;

	@FXML
	private RadioButton btnNomePassageiroAsc;

	@FXML
	private RadioButton btnNomePassageiroDesc;

	@FXML
	private RadioButton rotasAsc;

	@FXML
	private RadioButton rotasDesc;

	@FXML
	private ComboBox<Viagem> cbxViagem;

	public String ordenacaoP = "asc";
	public String ordenacaoR = "asc";
	private static ViagemDao viagemDao = DaoFactory.get().viagemDao();

	public void initialize() {

		cbxViagem.setItems(FXCollections.observableArrayList(viagemDao.listar()));

	}

	@FXML
	void rotasAsc(ActionEvent event) {
		ordenacaoR = "asc";
	}

	@FXML
	void rotasDesc(ActionEvent event) {
		ordenacaoR = "desc";
	}

	@FXML
	void nomePassageiroAsc(ActionEvent event) {
		ordenacaoP = "asc";
	}

	@FXML
	void nomePassageiroDesc(ActionEvent event) {
		ordenacaoP = "desc";
	}

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

	private MetodosTelas tela = new MetodosTelas();
	private static EmpresaDao empresaDao = DaoFactory.get().empresaDao();

	@FXML
	void onListaPassageiros(ActionEvent event) {
		InputStream url = getClass().getResourceAsStream("/resources/RelatorioPassageiros.jasper");

		try {

			JRDataSource dataSource = new JRBeanCollectionDataSource(
					empresaDao.relatorioPassageiros(AplicacaoSessao.empresa.getIdEmpresa(), ordenacaoP));

			JasperPrint print = JasperFillManager.fillReport(url, null, dataSource);

			JasperViewer.viewReport(print, false);
			JasperExportManager.exportReportToPdfFile(print, "relatorioPassageiros.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onListaRotas(ActionEvent event) {
		InputStream stream = getClass().getResourceAsStream("/resources/RelatorioRotas.jasper");

		try {

			JRDataSource dataSource = new JRBeanCollectionDataSource(
					empresaDao.relatorioRotas(AplicacaoSessao.empresa.getIdEmpresa(), ordenacaoR));

			JasperPrint print = JasperFillManager.fillReport(stream, null, dataSource);

			JasperViewer.viewReport(print, false);
			JasperExportManager.exportReportToPdfFile(print, "relatorioRotas.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onListaViagems(ActionEvent event) {
		InputStream stream = getClass().getResourceAsStream("/resources/RelatorioViagems.jasper");

		try {

			JRDataSource dataSource = new JRBeanCollectionDataSource(
					empresaDao.relatorioViagems(cbxViagem.getValue().getIdViagem()));

			JasperPrint print = JasperFillManager.fillReport(stream, null, dataSource);

			JasperViewer.viewReport(print, false);
			JasperExportManager.exportReportToPdfFile(print, "relatorioViagems.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

}
