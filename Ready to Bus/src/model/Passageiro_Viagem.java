package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Passageiro_Viagem {

	private Passageiro passageiro;
	private Viagem viagem;
	private int status;
	private boolean confirmacao;
	private LocalDate data;
	private LocalTime chegada;
	private LocalTime saida;
	private Boolean indo;

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

	public LocalTime getChegada() {
		return chegada;
	}

	public void setChegada(LocalTime chegada) {
		this.chegada = chegada;
	}

	public LocalTime getSaida() {
		return saida;
	}

	public void setSaida(LocalTime saida) {
		this.saida = saida;
	}

	public Boolean getIndo() {
		return indo;
	}

	public void setIndo(Boolean indo) {
		this.indo = indo;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getData() {
		return data;
	}
}
