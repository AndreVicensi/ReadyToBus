package metodos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import visual.Main;

public class MetodosTelas {
	public void carregarTela(String nome) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(nome));

		try {
			AnchorPane loginView = (AnchorPane) loader.load();
			Main.root.setCenter(loginView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void mostrarSobre() {
		Stage stage = new Stage();

		Text texto = new Text();
		stage.setScene(new Scene(new StackPane(texto)));
		stage.setTitle("Importante");
		texto.setText("True self é uma espécie de rede social offline\n\n\nCriado com o intuito de as pessoas"
				+ " interagirem entre elas,\nfazendo comentários(bons ou ruins) uma sobre as outras\n\nDesenvolvido por acadêmicos da UNOESC- Xanxerê "
				+ "do curso de\nTecnologia em Análise e Desenvolvimento de Sistemas - 3ª fase\n\nAndre Vicensi Uliana e João Victor Scanagatta\n\n\n\n TrueSelf® 2017");

		stage.setWidth(390);
		stage.setHeight(300);
		stage.show();
	}

	
	/*
	 * funciona so que o burro do eclipse abre o codigo html ao inves do site no navegador
	 * */
	public void abrirSite(String endereco) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(endereco));
			while (br.ready()) {
				String linha = br.readLine();
				System.out.println(linha);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
