package dao;

import model.Viagem;

public interface ViagemDao extends CrudDao<Viagem> {

	public void alterarDiringindo(Viagem entidade, Boolean dirigindo);
}
