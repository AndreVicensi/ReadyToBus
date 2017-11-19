package dao;

import java.util.List;

import model.Empresa;
import model.Relatorio;

public interface EmpresaDao extends CrudDao<Empresa> {

	public List<Relatorio> relatorioRotas(Integer codempresa);

	public List<Relatorio> relatorioPassageiros(Integer codempresa);

}
