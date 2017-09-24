package metodos;

import dao.DaoFactory;
import dao.EmpresaDao;
import model.Empresa;

public class MetodosLogin {
	
	private static EmpresaDao dao = DaoFactory.get().EmpresaDao();
	
	//metodo para pegar o login da empresa
	public Empresa getLogin(String login, String senha) {
			for (Empresa empresa : dao.listar()) {
				if (login.equals(empresa.getLogin())) {
					return empresa;
				}
			}
			return null;
	}
}
