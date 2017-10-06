package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Rota;
import model.Viagem;

public class ViagemJdbc implements ViagemDao {

	private Conexao conexao;

	public ViagemJdbc(Conexao conexao) {
		this.conexao = conexao;
	}

	@Override
	public void inserir(Viagem entidade) {
		String insert = "insert into viagem values (idViagem,?,?,?,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDate(1, Date.valueOf(entidade.getData()));
			insertStmt.setTime(2, Time.valueOf(entidade.getSaida()));
			insertStmt.setTime(3, Time.valueOf(entidade.getChegada()));
			insertStmt.setBoolean(4, entidade.getDirigindo());
			insertStmt.setInt(5, entidade.getRota().getIdRota());
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
		String delete = "delete from viagem where idViagem = ?";
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
		String update = "update viagem set data = ?, saida = ?, chegada = ?, dirigindo = ?, idRota = ?"
				+ " where idViagem = ?";
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setDate(1, Date.valueOf(entidade.getData()));
			updateStmt.setTime(2, Time.valueOf(entidade.getSaida()));
			updateStmt.setTime(3, Time.valueOf(entidade.getChegada()));
			updateStmt.setBoolean(4, entidade.getDirigindo());
			updateStmt.setInt(5, entidade.getRota().getIdRota());
			updateStmt.executeUpdate();
			updateStmt.setInt(6, entidade.getIdViagem());
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
				viagem.setData(rs.getDate("data").toLocalDate());
				viagem.setSaida(rs.getTime("saida").toLocalTime());
				viagem.setChegada(rs.getTime("chegada").toLocalTime());
				Rota rota = new Rota();
				rota.setIdRota(rs.getInt("idRota"));
				viagem.setRota(rota);
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
			viagem.setData(rs.getDate("data").toLocalDate());
			viagem.setSaida(rs.getTime("saida").toLocalTime());
			viagem.setChegada(rs.getTime("chegada").toLocalTime());
			Rota rota = new Rota();
			rota.setIdRota(rs.getInt("idRota"));
			viagem.setRota(rota);
			return viagem;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
