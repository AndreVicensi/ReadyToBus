package dao;

import conexao.Conexao;
import conexao.ConexaoProducao;
import conexao.ConexaoTeste;

public class DaoFactory {
	
	private static DaoFactory daoFactory;
	
	Conexao conexao = new ConexaoProducao(); 
	
	public static DaoFactory get() {
		if(daoFactory == null) {
			daoFactory = new DaoFactory();
			String ambiente = System.getProperty("ambiente");
			if(ambiente.equals("test")) {
				daoFactory.conexao = new ConexaoTeste();
			}
		}
		return daoFactory;
	}
	
	
	public EmpresaDao EmpresaDao() {
		return new EmpresaJdbc(conexao);
	}
	
}
