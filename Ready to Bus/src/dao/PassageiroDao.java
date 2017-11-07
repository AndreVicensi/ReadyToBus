package dao;

import java.util.List;

import model.Passageiro;

public interface PassageiroDao extends CrudDao<Passageiro> {


	public List<Passageiro> listarDaEmpresa(Integer codempresa);



}
