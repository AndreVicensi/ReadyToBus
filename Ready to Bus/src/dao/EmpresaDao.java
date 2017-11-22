package dao;

import java.util.List;

import model.Empresa;
import model.Relatorio;

public interface EmpresaDao extends CrudDao<Empresa> {

	public List<Relatorio> relatorioPassageiros(Integer codempresa, String order);

	public List<Relatorio> relatorioRotas(Integer codempresa, String order);

	public List<Relatorio> relatorioViagems(Integer codviagem);

}
