package model;

import javafx.scene.image.Image;

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
		if(isConfirmacao() == true) {
			img  = new Image("/visual/simpequeno.png");
		} else {
			img = new Image("/visual/naopequeno.png");
		}
		return img;
	}

}
