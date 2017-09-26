package dao;

import conexao.Conexao;
import conexao.ConexaoProducao;

public class DaoFactory {
	
	private static DaoFactory daoFactory;
	
	Conexao conexao = new ConexaoProducao(); 
	
	public static DaoFactory get() {
		if(daoFactory == null) {
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}
	
	
	public EmpresaDao EmpresaDao() {
		return new EmpresaJdbc(conexao);
	}
	
}
