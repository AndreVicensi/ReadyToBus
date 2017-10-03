package model;

public class Viagem {

	private int idViagem;
	private Motorista motorista;
	private String nome;

	public Viagem() {
		super();
	}

	public Viagem(Motorista motorista, String nome) {
		super();
		this.motorista = motorista;
		this.nome = nome;
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
