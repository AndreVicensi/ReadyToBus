package dao;

import java.time.LocalTime;
import java.util.List;

import model.Viagem;

public interface ViagemDao extends CrudDao<Viagem> {

	public List<Viagem> listarMotorista(Integer codmotorista);

	void alterarChegada(Integer codviagem, LocalTime data);

	void alterarSaida(Integer codviagem, LocalTime data);

	void alterarDiringindo(Integer codviagem, Boolean dirigindo);

	public String getData(Integer codigo);

	public Viagem getHoras(Integer codigo);
}
