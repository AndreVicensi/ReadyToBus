package dao;

import model.Motorista;

public interface MotoristaDao extends CrudDao<Motorista> {

	public void alterarDiringindo(Motorista entidade, Boolean dirigindo);
}
