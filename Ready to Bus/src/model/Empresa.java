package model;

public class Empresa extends Usuario {

	private int idEmpresa;
	private String nome;
	private String cnpj;

	public Empresa() {
		super();
	}

	public Empresa(String nome, String cnpj, String login, String senha) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		super.login = login;
		super.senha = senha;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
