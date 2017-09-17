package visual;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static BorderPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("TelaInicialStyle.css").toExternalForm());
			primaryStage.setScene(scene);

			primaryStage.setMaximized(true);

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("TelaLogin.fxml"));

			AnchorPane loginView = (AnchorPane) loader.load();
			root.setCenter(loginView);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static BorderPane getRoot() {
		return root;
	}
	


}
