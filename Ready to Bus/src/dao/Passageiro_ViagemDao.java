package dao;

import java.util.List;

import model.Motorista;
import model.Passageiro;
import model.Passageiro_Viagem;
import model.Viagem;

public interface Passageiro_ViagemDao extends CrudDao<Passageiro_Viagem> {

	public void alterarStatus(Passageiro entidade, Integer status, Viagem viagem);

	public void fazerCheck(Passageiro entidade, Boolean confirmacao, Viagem viagem);

	public List<Passageiro_Viagem> Lista(Passageiro_Viagem passageiro_viagem);

	public List<Passageiro_Viagem> ListaViagem(Integer codviagem);

	public Motorista getMotorista(Integer codviagem);

	public List<Passageiro_Viagem> ListaMotorista(Integer codmotorista, Integer codviagem);

}
