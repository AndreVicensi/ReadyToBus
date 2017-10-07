package dao;

import java.time.LocalTime;

import model.Viagem;

public interface ViagemDao extends CrudDao<Viagem> {

	public void alterarDiringindo(Viagem entidade, Boolean dirigindo);

	void alterarSaida(Viagem entidade, LocalTime tempo);

	void alterarChegada(Viagem entidade, LocalTime tempo);
}
