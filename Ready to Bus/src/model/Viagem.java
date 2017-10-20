package model;

import java.time.LocalDate;
import java.time.LocalTime;

import componente.RenderizaCombo;

public class Viagem implements RenderizaCombo {

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

	// nao sei como pegar o nome da rota, só o id

	@Override
	public String toString() {
		return "Viagem " + rota.getNome() + " data=" + data + ", saida=" + saida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idViagem;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viagem other = (Viagem) obj;
		if (idViagem != other.idViagem)
			return false;
		return true;
	}

	@Override
	public String getText() {
		return "Origem/Destino= " + rota.getNome() + ", Hora da saída= " + saida;
	}

}
