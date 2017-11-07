package dao;

import java.util.List;

import model.Rota;

public interface RotaDao extends CrudDao<Rota>{


	public List<Rota> listarDaEmpresa(Integer codempresa);
	


}
