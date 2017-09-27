package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import metodos.MetodosTelas;

public class EmpresaController {

    @FXML
    private Label lRota;

    @FXML
    private Button btnCadsatroMotorista;

    @FXML
    private Button btnCadastroRota;

    @FXML
    private Button btnCadastroPassageiro;

    @FXML
    private Label lApelidoMotorista;

    @FXML
    private Button btnCadsatroMotorista1;
    
    private MetodosTelas tela = new MetodosTelas();

    @FXML
    void cadastrarMotorista(ActionEvent event) {

    	tela.carregarTela("/visual/TelaCadastroMotorista.fxml");
    }

    @FXML
    void cadastrarPassageiro(ActionEvent event) {

    }

    @FXML
    void cadastrarRota(ActionEvent event) {

    }

}
