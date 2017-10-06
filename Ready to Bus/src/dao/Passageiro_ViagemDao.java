package dao;

import model.Passageiro;
import model.Passageiro_Viagem;

public interface Passageiro_ViagemDao  extends CrudDao<Passageiro_Viagem>{

	public void alterarStatus(Passageiro entidade, Integer status);
	
	public void fazerCheck(Passageiro entidade, Boolean confirmacao);
}
