package model;

public class Passageiro extends Usuario {

	private int idPassageiro;
	private String nome;
	private String cpf;
	private String telefone;
	private Empresa empresa;

	public Passageiro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passageiro(String nome, String login, String senha, String cpf, String telefone) {
		super();
		this.nome = nome;
		super.login = login;
		super.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;

	}

	public int getIdPassageiro() {
		return idPassageiro;
	}

	public void setIdPassageiro(int idPassageiro) {
		this.idPassageiro = idPassageiro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPassageiro;
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
		Passageiro other = (Passageiro) obj;
		if (idPassageiro != other.idPassageiro)
			return false;
		return true;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
