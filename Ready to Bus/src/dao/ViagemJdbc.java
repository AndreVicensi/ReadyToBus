package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Motorista;
import model.Rota;
import model.Viagem;

public class ViagemJdbc implements ViagemDao {

	private Conexao conexao;

	public ViagemJdbc(Conexao conexao) {
		this.conexao = conexao;
	}

	private RotaJdbc rotaJdbc = new RotaJdbc(conexao);

	@Override
	public void inserir(Viagem entidade) {
		String insert = "insert into viagem values (idViagem,?,?,?,?,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDate(1, Date.valueOf(entidade.getData()));
			insertStmt.setTime(2, Time.valueOf(entidade.getSaida()));
			insertStmt.setTime(3, Time.valueOf(entidade.getChegada()));
			insertStmt.setBoolean(4, entidade.getDirigindo());
			insertStmt.setInt(5, entidade.getRota().getIdRota());
			insertStmt.setBoolean(6, entidade.getIda());
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
		String update = "update viagem set data = ?, saida = ?, chegada = ?, dirigindo = ?, idRota = ?, ida= ?"
				+ " where idViagem = ?";
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setDate(1, Date.valueOf(entidade.getData()));
			updateStmt.setTime(2, Time.valueOf(entidade.getSaida()));
			updateStmt.setTime(3, Time.valueOf(entidade.getChegada()));
			updateStmt.setBoolean(4, entidade.getDirigindo());
			updateStmt.setInt(5, entidade.getRota().getIdRota());
			updateStmt.setBoolean(6, entidade.getIda());
			updateStmt.executeUpdate();
			updateStmt.setInt(7, entidade.getIdViagem());
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
			String sql = "select v.*, r.nome, m.idmotorista, m.nome, m.apelido from viagem v join rota r on v.idrota = r.idrota "
					+ "join motorista m on r.idmotorista = m.idmotorista";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Viagem viagem = new Viagem();
				viagem.setIdViagem(rs.getInt("idViagem"));
				viagem.setData(rs.getDate("data").toLocalDate());
				viagem.setSaida(rs.getTime("saida").toLocalTime());
				viagem.setChegada(rs.getTime("chegada").toLocalTime());
				viagem.setIda(rs.getBoolean("ida"));

				Rota rota = new Rota();
				rota.setIdRota(rs.getInt("idrota"));
				rota.setNome(rs.getString("nome"));

				Motorista motorista = new Motorista();
				motorista.setIdMotorista(rs.getInt("idMotorista"));
				motorista.setNome(rs.getString("m.nome"));
				motorista.setApelido(rs.getString("m.apelido"));

				rota.setMotorista(motorista);
				viagem.setRota(rota);
				viagems.add(viagem);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return viagems;
	}

	@Override
	public List<Viagem> listarMotorista(Integer codmotorista) {
		Statement stmt = null;
		List<Viagem> viagems = new ArrayList<Viagem>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select v.*, r.nome, m.idmotorista, m.nome, m.apelido from viagem v join rota r on v.idrota = r.idrota "
					+ "join motorista m on r.idmotorista = m.idmotorista where m.idmotorista=" + codmotorista;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Viagem viagem = new Viagem();
				viagem.setIdViagem(rs.getInt("idViagem"));
				viagem.setData(rs.getDate("data").toLocalDate());
				viagem.setSaida(rs.getTime("saida").toLocalTime());
				viagem.setChegada(rs.getTime("chegada").toLocalTime());
				viagem.setIda(rs.getBoolean("ida"));

				Rota rota = new Rota();
				rota.setIdRota(rs.getInt("idrota"));
				rota.setNome(rs.getString("nome"));

				Motorista motorista = new Motorista();
				motorista.setIdMotorista(rs.getInt("idMotorista"));
				motorista.setNome(rs.getString("m.nome"));
				motorista.setApelido(rs.getString("m.apelido"));

				rota.setMotorista(motorista);
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
			rs.next();
			Viagem viagem = new Viagem();
			viagem.setIdViagem(rs.getInt("idViagem"));
			viagem.setData(rs.getDate("data").toLocalDate());
			viagem.setSaida(rs.getTime("saida").toLocalTime());
			viagem.setChegada(rs.getTime("chegada").toLocalTime());
			viagem.setRota(rotaJdbc.get(rs.getInt("idrota")));
			viagem.setDirigindo(rs.getBoolean("dirigindo"));
			viagem.setIda(rs.getBoolean("ida"));
			return viagem;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public void alterarDiringindo(Integer codviagem, Boolean dirigindo) {
		String update = "update viagem set dirigindo = ?  where idViagem = " + codviagem;
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setBoolean(1, dirigindo);
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterarSaida(Integer codviagem, LocalTime data) {
		String update = "update viagem set saida = ? where idViagem = " + codviagem;
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setTime(1, Time.valueOf(data));
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterarChegada(Integer codviagem, LocalTime data) {
		String update = "update viagem set chegada = ? where idViagem = " + codviagem;
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setTime(1, Time.valueOf(data));
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
