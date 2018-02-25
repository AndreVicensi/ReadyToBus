package model;

public class Viagem_Ida_Volta {

	private int idIdaVolta;
	private Viagem ida;
	private Viagem volta;

	public Viagem_Ida_Volta() {
		super();
	}

	public Viagem_Ida_Volta(int idIdaVolta, Viagem ida, Viagem volta) {
		super();
		this.idIdaVolta = idIdaVolta;
		this.ida = ida;
		this.volta = volta;
	}

	public int getIdIdaVolta() {
		return idIdaVolta;
	}

	public void setIdIdaVolta(int idIdaVolta) {
		this.idIdaVolta = idIdaVolta;
	}

	public Viagem getIda() {
		return ida;
	}

	public void setIda(Viagem ida) {
		this.ida = ida;
	}

	public Viagem getVolta() {
		return volta;
	}

	public void setVolta(Viagem volta) {
		this.volta = volta;
	}

}
