package model;

public class Motorista extends Usuario {

	private int idMotorista;
	private String nome;
	private String apelido;
	private Empresa empresa;

	public Motorista() {
		super();
	}

	public Motorista(String nome, String apelido, String login, String senha, Empresa empresa) {
		super();
		this.nome = nome;
		this.apelido = apelido;
		super.login = login;
		super.senha = senha;
		this.empresa = empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public int getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(int idMotorista) {
		this.idMotorista = idMotorista;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMotorista;
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
		Motorista other = (Motorista) obj;
		if (idMotorista != other.idMotorista)
			return false;
		return true;
	}

}
