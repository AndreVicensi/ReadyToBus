package metodos;

import dao.DaoFactory;
import dao.EmpresaDao;
import dao.MotoristaDao;
import dao.PassageiroDao;
import model.Empresa;
import model.Motorista;
import model.Passageiro;

public class MetodosLogin {

	private static EmpresaDao empresaDao = DaoFactory.get().empresaDao();
	public static MotoristaDao motoristaDao = DaoFactory.get().motoristaDao();
	public static PassageiroDao passageiroDao = DaoFactory.get().passageiroDao();

	// metodo para pegar o login da empresa
	// lista colocando todos
	public Empresa getLoginEmpresa(String login) {
		for (Empresa empresa : empresaDao.listar()) {
			if (login.equals(empresa.getLogin())) {
				return empresa;
			}
		}
		return null;
	}

	public Motorista getLoginMotorista(String login) {
		for (Motorista motorista : motoristaDao.listar()) {
			if (login.equals(motorista.getLogin())) {
				return motorista;
			}
		}
		return null;
	}

	public Passageiro getLoginPassageiro(String login) {
		for (Passageiro passageiro : passageiroDao.listar()) {
			if (login.equals(passageiro.getLogin())) {
				return passageiro;
			}
		}
		return null;
	}

}
