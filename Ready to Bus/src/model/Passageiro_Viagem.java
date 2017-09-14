package model;

public class Passageiro_Viagem {
	
	private Passageiro passageiro;
	private Viagem viagem;
	private int status;
	private boolean confirmacao;
	
	public Passageiro getPassageiro() {
		return passageiro;
	}
	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}
	public Viagem getViagem() {
		return viagem;
	}
	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
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
	
}
