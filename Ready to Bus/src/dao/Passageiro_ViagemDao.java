package dao;

import java.util.List;

import model.Passageiro;
import model.Passageiro_Viagem;
import model.Viagem;

public interface Passageiro_ViagemDao  extends CrudDao<Passageiro_Viagem>{

	public void alterarStatus(Passageiro entidade, Integer status, Viagem viagem);
	
	public void fazerCheck(Passageiro entidade, Boolean confirmacao);
	
	public List<Passageiro_Viagem> Lista(Passageiro_Viagem passageiro_viagem);
	
	Passageiro_Viagem get(Integer codigoViagem, Integer codigoPassageiro);

	
}
