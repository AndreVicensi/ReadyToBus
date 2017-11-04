package model;

import java.io.InputStream;

import javafx.scene.image.Image;
import visual.Main;

public class Passageiro_Viagem {

	private Passageiro passageiro;
	private Viagem viagem;
	private Integer idViagem;
	private Integer idPassageiro;
	private int status;
	private boolean confirmacao;

	public Passageiro_Viagem() {
		super();
	}

	public Integer getIdViagem() {
		return idViagem;
	}

	public void setIdViagem(Integer idViagem) {
		this.idViagem = idViagem;
	}

	public Integer getIdPassageiro() {
		return idPassageiro;
	}

	public void setIdPassageiro(Integer idPassageiro) {
		this.idPassageiro = idPassageiro;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public Passageiro_Viagem(Passageiro passageiro, Viagem viagem) {
		super();
		this.passageiro = passageiro;
		this.viagem = viagem;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(boolean confirmacao) {
		this.confirmacao = confirmacao;
	}

	public String getTelefoneNumero() {
		return passageiro.getTelefone();
	}

	public Image getImagemCheck() {
		Image img;
		InputStream input;
		if (isConfirmacao() == true) {
			input = Main.class.getResourceAsStream("simpequena.png");
			img = new Image(input);
		} else {
			input = Main.class.getResourceAsStream("naopequena.png");
			img = new Image(input);
		}
		return img;
	}

	public Image getImagemStatus() {
		Image img;
		switch (getStatus()) {
		case 1:
			img = new Image("");
			break;

		case 2:
			img = new Image("");
			break;

		case 3:
			img = new Image("");
			break;

		case 4:
			img = new Image("");
			break;

		default:
			img = new Image("");
			break;
		}
		return img;
	}
}
