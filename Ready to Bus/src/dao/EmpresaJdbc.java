package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import conexao.Conexao;
import model.Empresa;

public class EmpresaJdbc implements EmpresaDao {

	private Conexao conexao;
	
	public EmpresaJdbc(Conexao conexao) {
		this.conexao = conexao;
	}
	
	// insere uma empresa no banco de dados
	@Override
	public void inserir(Empresa entidade) {
		String insert = "insert into empresa values (idEmpresa,?,?,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, entidade.getNome());
			insertStmt.setString(2, entidade.getCnpj());
			insertStmt.setString(3, entidade.getLogin());
			insertStmt.setString(4, entidade.getSenha());
			insertStmt.executeUpdate();
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			entidade.setIdEmpresa(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// exclui uma empresa passando o id dela
	@Override
	public void excluir(Integer codigo) {
		String delete = "delete from empresa where idEmpresa = ?";
		PreparedStatement deleteStmt;
		try {
			deleteStmt = conexao.get().prepareStatement(delete);
			deleteStmt.setLong(1, codigo);
			deleteStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// altera os dados da empresa
	@Override
	public void alterar(Empresa entidade) {
		String update = "update empresa set nome = ?, cnpj = ?, login = ?, senha = ? where codigo = ?";
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);	
			updateStmt.setString(1, entidade.getNome());
			updateStmt.setString(2, entidade.getCnpj());
			updateStmt.setString(3, entidade.getLogin());
			updateStmt.setString(4, entidade.getSenha());
			updateStmt.setInt(5, entidade.getIdEmpresa());
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// lista todas as empresas, usado para pegar o login
	@Override
	public List<Empresa> listar() {
		Statement stmt = null;
		List<Empresa> empresas = new ArrayList<Empresa>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from empresa";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			     Empresa empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setNome(rs.getString("nome"));
				empresa.setCnpj(rs.getString("cnpj"));
				empresa.setLogin(rs.getString("login"));
				empresa.setSenha(rs.getString("senha"));
				empresas.add(empresa);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return empresas;
	}

	// seleciona uma empresa com base no seu id
	// vai ser usado no login
	@Override
	public Empresa get(Integer codigo) {
		Statement stmt = null;
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from empresa where codigo = "+codigo;
			ResultSet rs = stmt.executeQuery(sql);
				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setNome(rs.getString("nome"));
				empresa.setCnpj(rs.getString("cnpj"));
				empresa.setLogin(rs.getString("login"));
				empresa.setSenha(rs.getString("senha"));
				return empresa;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
