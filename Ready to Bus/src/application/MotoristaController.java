package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class MotoristaController {

	@FXML
	private ImageView imgDirigindo1;

	@FXML
	private TableView<?> tblLista;

	@FXML
	private TableColumn<?, ?> tbcPassageiro;

	@FXML
	private TableColumn<?, ?> tbcTelefone;

	@FXML
	private TableColumn<?, ?> tbcStatus;

	@FXML
	private TableColumn<?, ?> tbcCheck;

	@FXML
	private Button btnDirigir;

	@FXML
	private Button btnChegada;

	@FXML
	private Label ldataDia;

	@FXML
	private ImageView imgDirigindo;

}
