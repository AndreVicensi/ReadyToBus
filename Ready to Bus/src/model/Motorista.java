package model;

public class Motorista implements Usuario {

	private int idMotorista;
	private String nome;
	private String apelido;
	private String login;
	private String senha;
	private Empresa empresa;

	public Motorista() {
		super();
	}

	public Motorista(String nome, String apelido, String login, String senha, Empresa empresa) {
		super();
		this.nome = nome;
		this.apelido = apelido;
		this.login = login;
		this.senha = senha;
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
	
	



}
