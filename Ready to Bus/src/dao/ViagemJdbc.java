package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import conexao.Conexao;
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
		String insert = "insert into viagem values (idViagem,?,?,?,?,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, entidade.getMotorista().getIdMotorista());
			insertStmt.setDate(2, Date.valueOf(entidade.getData()));
			insertStmt.setString(3, entidade.getNome());
			insertStmt.setTime(4, Time.valueOf(entidade.getSaida()));
			insertStmt.setTime(5, Time.valueOf(entidade.getChegada()));
			insertStmt.setBoolean(6, entidade.getIndo());
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
		String update = "update viagem set idMotorista = ?, data = ?,nome = ?, saida = ?, chegada = ?,"
				+ "indo = ? where idViagem = ?";
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);	
			updateStmt.setInt(1, entidade.getMotorista().getIdMotorista());
			updateStmt.setDate(2, (Date) entidade.getData());
			updateStmt.setString(3, entidade.getNome());
			updateStmt.setTime(4, entidade.getSaida());
			updateStmt.setTime(5, entidade.getChegada());
			updateStmt.setBoolean(6, entidade.getIndo());
			updateStmt.executeUpdate();
			updateStmt.setInt(7, entidade.getIdViagem();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Viagem> listar() {
		Statement stmt = null;
		List<Viagem> rotas = new ArrayList<Viagem>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from viagem";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Viagem viagem = new Viagem();
				viagem.setIdViagem(rs.getInt("idViagem"));
				viagem.setMotorista(motoristaJdbc.get(rs.getInt("idMotorista")));
				// separar em uma variavel, pois se vier null do banco dá null pointer exception
				viagem.setData(rs.getDate("data").toLocalDate());
				viagem.setNome(rs.getString("nome"));
				viagem.setSaida(rs.getTime("saida"));
				viagem.setChegada(rs.getTime("chegada"));
				viagem.setIndo(rs.getBoolean("indo"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rotas;
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
			viagem.setMotorista(motoristaJdbc.get(rs.getInt("idMotorista")));
			viagem.setData(rs.getDate("data"));
			viagem.setNome(rs.getString("nome"));
			viagem.setSaida(rs.getTime("saida"));
			viagem.setChegada(rs.getTime("chegada"));
			viagem.setIndo(rs.getBoolean("indo"));
			return viagem;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
