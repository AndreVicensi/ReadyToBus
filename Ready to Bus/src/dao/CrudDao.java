package dao;

import java.util.List;

public interface CrudDao<T> {
	
	void inserir(T entidade);
	
	void excluir(Integer codigo);
	
	void alterar(T entidade);
	
	List<T> listar();
	
	T get(Integer codigo);
	
}
