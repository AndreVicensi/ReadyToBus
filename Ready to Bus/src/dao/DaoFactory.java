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
	
	public MotoristaDao motoristaDao() {
		return new MotoristaJdbc(conexao);
	}
	
	public PassageiroDao passageiroDao() {
		return new PassageiroJdbc(conexao);
	}
	
	public ViagemDao viagemDao() {
		return new ViagemJdbc(conexao);
	}
}
