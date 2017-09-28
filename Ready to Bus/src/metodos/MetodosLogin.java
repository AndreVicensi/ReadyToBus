package metodos;

import dao.DaoFactory;
import dao.EmpresaDao;
import model.Empresa;

public class MetodosLogin {
	
	private static EmpresaDao empresaDao = DaoFactory.get().EmpresaDao();
	
	//metodo para pegar o login da empresa
	public Empresa getLoginEmpresa(String login) {
			for (Empresa empresa : empresaDao.listar()) {
				if (login.equals(empresa.getLogin())) {
					return empresa;
				}
			}
			return null;
	}
	
	/*
	 * Duvidas:
	 * Como fazer o cadastro usando a interface usuario;
	 * Como setar o boolean no mySQL;
	 * como pegar a empresa no mySQL;
	 * como pegar a empresa no cadastro;
	 */
}
