package dao;

import model.Passageiro;
import model.Passageiro_Viagem;
import model.Viagem;

public interface Passageiro_ViagemDao  extends CrudDao<Passageiro_Viagem>{

	public void alterarStatus(Passageiro entidade, Integer status, Viagem viagem);
	
	public void fazerCheck(Passageiro entidade, Boolean confirmacao);
	
	public void viewLista(Passageiro passageiro, Passageiro_Viagem passageiro_viagem);
}
