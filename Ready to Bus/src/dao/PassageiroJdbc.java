package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import conexao.Conexao;
import model.Passageiro;

public class PassageiroJdbc implements PassageiroDao {

	private Conexao conexao;

	public PassageiroJdbc(Conexao conexao) {
		this.conexao = conexao;
	}

	@Override
	public void inserir(Passageiro entidade) {
		String insert = "insert into motorista values (idPassageiro,?,?,?,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, entidade.getNome());
			insertStmt.setString(2, entidade.getLogin());
			insertStmt.setString(3, entidade.getSenha());
			insertStmt.setString(4, entidade.getCpf());
			insertStmt.setString(5, entidade.getTelefone());
			insertStmt.executeUpdate();
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			entidade.setIdPassageiro(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer codigo) {
		String delete = "delete from passageiro where idPassageiro = ?";
		PreparedStatement deleteStmt;
		try {
			deleteStmt = conexao.get().prepareStatement(delete);
			deleteStmt.setLong(1, codigo);
			deleteStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Passageiro entidade) {
		String update = "update passageiro set nome = ?, login = ?, senha = ?,cpf = ?,telefone = ?"
				+ " where idPassageiro = ?";
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setString(1, entidade.getNome());
			updateStmt.setString(2, entidade.getLogin());
			updateStmt.setString(3, entidade.getSenha());
			updateStmt.setString(4, entidade.getCpf());
			updateStmt.setString(5, entidade.getTelefone());
			updateStmt.setInt(6, entidade.getIdPassageiro());
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Passageiro> listar() {
		Statement stmt = null;
		List<Passageiro> passageiros = new ArrayList<Passageiro>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from passageiro";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Passageiro passageiro = new Passageiro();
				passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
				passageiro.setNome(rs.getString("nome"));
				passageiro.setLogin(rs.getString("login"));
				passageiro.setSenha(rs.getString("senha"));
				passageiro.setCpf(rs.getString("cpf"));
				passageiro.setTelefone(rs.getString("telefone"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return passageiros;
	}

	@Override
	public Passageiro get(Integer codigo) {
		Statement stmt = null;
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from passageiro where idPassageiro = " + codigo;
			ResultSet rs = stmt.executeQuery(sql);
			Passageiro passageiro = new Passageiro();
			passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
			passageiro.setNome(rs.getString("nome"));
			passageiro.setLogin(rs.getString("login"));
			passageiro.setSenha(rs.getString("senha"));
			passageiro.setCpf(rs.getString("cpf"));
			passageiro.setTelefone(rs.getString("telefone"));
			return passageiro;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}


}
