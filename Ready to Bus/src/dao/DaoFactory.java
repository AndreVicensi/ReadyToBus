package dao;

import conexao.Conexao;
import conexao.ConexaoProducao;

public class DaoFactory {

	private static DaoFactory daoFactory;

	Conexao conexao = new ConexaoProducao();

	public static DaoFactory get() {
		if (daoFactory == null) {
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}

	public EmpresaDao empresaDao() {
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

	public Passageiro_ViagemDao passageiro_ViagemDao() {
		return new Passageiro_ViagemJdbc(conexao);
	}

	public RotaDao rotaDao() {
		return new RotaJdbc(conexao);
	}

}
