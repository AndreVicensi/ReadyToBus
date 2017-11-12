package application;

import java.io.InputStream;

import dao.DaoFactory;
import dao.PassageiroDao;
import dao.RotaDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import metodos.AplicacaoSessao;
import metodos.MetodosTelas;
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
	private Button btnVoltar;

	@FXML
	void onVoltar(ActionEvent event) {
		tela.carregarTela("/visual/TelaEmpresa.fxml");
	}

	private MetodosTelas tela = new MetodosTelas();
	private static PassageiroDao passageiroDao = DaoFactory.get().passageiroDao();
	private static RotaDao rotaDao = DaoFactory.get().rotaDao();

	@FXML
	void onListaPassageiros(ActionEvent event) {
		InputStream stream = getClass().getResourceAsStream("/RelatorioPassageiros.jasper");

		try {

			JRDataSource dataSource = new JRBeanCollectionDataSource(
					passageiroDao.listarDaEmpresa(AplicacaoSessao.empresa.getIdEmpresa()));

			JasperPrint print = JasperFillManager.fillReport(stream, null, dataSource);

			JasperViewer.viewReport(print);
			JasperExportManager.exportReportToPdfFile(print, "relatorioPassageiros.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onListaRotas(ActionEvent event) {
		InputStream stream = getClass().getResourceAsStream("/RelatorioRotas.jasper");

		try {

			JRDataSource dataSource = new JRBeanCollectionDataSource(
					rotaDao.listarRelatorio(AplicacaoSessao.empresa.getIdEmpresa()));

			JasperPrint print = JasperFillManager.fillReport(stream, null, dataSource);

			JasperViewer.viewReport(print);
			JasperExportManager.exportReportToPdfFile(print, "relatorioRotas.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onSair(ActionEvent event) {
		tela.carregarTela("/visual/TelaLogin.fxml");
	}

}
