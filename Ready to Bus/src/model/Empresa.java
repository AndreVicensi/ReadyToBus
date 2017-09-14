package model;

public class Empresa {
	
	private int idEmpresa;
	private String nome;
	private String cnpj;
	private String login;
	private String senha;
	
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
	
	/*
	 * BufferedReader br = new BufferedReader(new FileReader("c:/arquivo.html"));
while(br.ready()){
   String linha = br.readLine();
   System.out.println(linha);
}
br.close();
	 */
}
