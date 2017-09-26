package model;

public class Empresa implements Usuario {
	
	private int idEmpresa;
	private String nome;
	private String cnpj;
	private String login;
	private String senha;
	
	public Empresa() {
		super();
	}

	public Empresa(String nome, String cnpj, String login, String senha) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.login = login;
		this.senha = senha;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
