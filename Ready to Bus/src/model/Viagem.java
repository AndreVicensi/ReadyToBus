package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Viagem {

	private int idViagem;
	private LocalDate data;
	private LocalTime saida;
	private LocalTime chegada;
	private Boolean dirigindo;
	private Rota rota;

	public Viagem() {
		super();

	}



	public Viagem(LocalDate data, LocalTime saida, LocalTime chegada, Boolean dirigindo, Rota rota) {
		super();
		this.data = data;
		this.saida = saida;
		this.chegada = chegada;
		this.dirigindo = dirigindo;
		this.rota = rota;
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

	public Boolean getDirigindo() {
		return dirigindo;
	}

	public void setDirigindo(Boolean dirigindo) {
		this.dirigindo = dirigindo;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getData() {
		return data;
	}

	public int getIdViagem() {
		return idViagem;
	}

	public void setIdViagem(int idViagem) {
		this.idViagem = idViagem;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

}
