package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
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
}
