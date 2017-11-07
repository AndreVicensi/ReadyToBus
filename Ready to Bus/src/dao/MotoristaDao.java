package dao;

import java.util.List;

import model.Motorista;

public interface MotoristaDao extends CrudDao<Motorista> {

	public List<Motorista> listarDaEmpresa(Integer codempresa);

}
