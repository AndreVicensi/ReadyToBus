package model;

import java.time.LocalTime;
import java.util.Date;

public class Viagem {
	
	private int idViagem;
	private Motorista motorista;
	private Date data;
	private String nome;
	private LocalTime chegada;
	private LocalTime saida;
	private Boolean indo;
	
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
	public int getIdViagem() {
		return idViagem;
	}
	public void setIdViagem(int idViagem) {
		this.idViagem = idViagem;
	}
	public Motorista getMotorista() {
		return motorista;
	}
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
