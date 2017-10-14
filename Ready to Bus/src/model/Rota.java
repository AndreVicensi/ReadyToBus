package model;

import componente.RenderizaCombo;

public class Rota implements RenderizaCombo {

	private int idRota;
	private Motorista motorista;
	private String nome;

	public Rota() {
		super();
	}

	public Rota(Motorista motorista, String nome) {
		super();
		this.motorista = motorista;
		this.nome = nome;
	}

	public int getIdRota() {
		return idRota;
	}

	public void setIdRota(int idRota) {
		this.idRota = idRota;
	}

	public Motorista getMotorista() {
		return motorista;
	}



	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRota;
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
		Rota other = (Rota) obj;
		if (idRota != other.idRota)
			return false;
		return true;
	}

	@Override
	public String getText() {
		return nome;
	}


	
	

}
