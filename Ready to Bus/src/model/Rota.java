package model;

public class Rota {

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
	

}
