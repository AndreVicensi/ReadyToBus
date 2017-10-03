package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Empresa;
import model.Motorista;
import model.Viagem;

public class ViagemJdbc implements ViagemDao {

	private Conexao conexao;

	public ViagemJdbc(Conexao conexao) {
		this.conexao = conexao;
	}

	private MotoristaJdbc motoristaJdbc = new MotoristaJdbc(conexao);

	@Override
	public void inserir(Viagem entidade) {
		String insert = "insert into viagem values (idViagem,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, entidade.getMotorista().getIdMotorista());
			insertStmt.setString(2, entidade.getNome());
			insertStmt.executeUpdate();
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			entidade.setIdViagem(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer codigo) {
		String delete = "delete from motorista where idViagem = ?";
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
	public void alterar(Viagem entidade) {
		String update = "update viagem set idMotorista = ?,nome = ? where idViagem = ?";
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setInt(1, entidade.getMotorista().getIdMotorista());
			updateStmt.setString(2, entidade.getNome());
			updateStmt.executeUpdate();
			updateStmt.setInt(3, entidade.getIdViagem());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Viagem> listar() {
		Statement stmt = null;
		List<Viagem> viagems = new ArrayList<Viagem>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from viagem";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Viagem viagem = new Viagem();
				viagem.setIdViagem(rs.getInt("idViagem"));
				viagem.setNome(rs.getString("nome"));

				Motorista motorista = new Motorista();
				motorista.setIdMotorista(rs.getInt("idMotorista"));

				viagem.setMotorista(motorista);

				viagems.add(viagem);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return viagems;
	}

	@Override
	public Viagem get(Integer codigo) {
		Statement stmt = null;
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from viagem where idViagem = " + codigo;
			ResultSet rs = stmt.executeQuery(sql);
			Viagem viagem = new Viagem();
			viagem.setIdViagem(rs.getInt("idViagem"));
			viagem.setNome(rs.getString("nome"));
			viagem.setMotorista(motoristaJdbc.get(rs.getInt("idMotorista")));
			return viagem;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
