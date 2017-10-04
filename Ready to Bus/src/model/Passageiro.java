package model;

public class Passageiro implements Usuario {

	private int idPassageiro;
	private String nome;
	private String login;
	private String senha;
	private String cpf;
	private String telefone;
	private Viagem viagem;

	public Passageiro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passageiro(String nome, String login, String senha, String cpf, String telefone,
			Viagem viagem) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.viagem = viagem;
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

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	@Override
	public String toString() {
		return "Passageiro [idPassageiro=" + idPassageiro + ", nome=" + nome + ", login=" + login + ", senha=" + senha
				+ ", cpf=" + cpf + ", telefone=" + telefone + ", viagem=" + viagem + "]";
	}

}
