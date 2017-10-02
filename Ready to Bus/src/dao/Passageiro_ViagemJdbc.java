package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import conexao.Conexao;
import model.Passageiro;
import model.Passageiro_Viagem;
import model.Viagem;

public class Passageiro_ViagemJdbc implements Passageiro_ViagemDao {

	private Conexao conexao;

	public Passageiro_ViagemJdbc(Conexao conexao) {
		this.conexao = conexao;
	}
	
	@Override
	public void inserir(Passageiro_Viagem entidade) {
		String insert = "insert into viagem_passageiro values (?,?,?,?,?,?,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert);
			insertStmt.setInt(1, entidade.getViagem().getIdViagem());
			insertStmt.setInt(2, entidade.getPassageiro().getIdPassageiro());
			insertStmt.setInt(3, entidade.getStatus());
			insertStmt.setBoolean(4, entidade.isConfirmacao());
			insertStmt.setDate(5, Date.valueOf(entidade.getData()));
			insertStmt.setTime(6, Time.valueOf(entidade.getSaida()));
			insertStmt.setTime(7, Time.valueOf(entidade.getSaida()));
			insertStmt.setBoolean(8, entidade.getIndo());
			insertStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer codigo) {
	}

	@Override
	public void alterar(Passageiro_Viagem entidade) {
	}

	@Override
	public List<Passageiro_Viagem> listar() {
		Statement stmt = null;
		List<Passageiro_Viagem> lista = new ArrayList<Passageiro_Viagem>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from viagem_passageiro";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Passageiro_Viagem passageiro_Viagem = new Passageiro_Viagem();
				Passageiro passageiro = new Passageiro();
				passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
				passageiro_Viagem.setPassageiro(passageiro);
				Viagem  viagem = new Viagem(); 
				viagem.setIdViagem(rs.getInt("idViagem"));
				passageiro_Viagem.setViagem(viagem);
				passageiro_Viagem.setStatus(rs.getInt("status"));
				passageiro_Viagem.setConfirmacao(rs.getBoolean("confirmacao"));
				passageiro_Viagem.setData(rs.getDate("data").toLocalDate());
				passageiro_Viagem.setSaida(rs.getTime("saida").toLocalTime());
				passageiro_Viagem.setChegada(rs.getTime("chegada").toLocalTime());
				passageiro_Viagem.setIndo(rs.getBoolean("indo"));
				lista.add(passageiro_Viagem);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return lista;
	}
	
	public List<Passageiro_Viagem> lista(Integer codigo) {
		Statement stmt = null;
		List<Passageiro_Viagem> lista = new ArrayList<Passageiro_Viagem>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from viagem_passageiro where idViagem = "+ codigo;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Passageiro_Viagem passageiro_Viagem = new Passageiro_Viagem();
				Passageiro passageiro = new Passageiro();
				passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
				passageiro_Viagem.setPassageiro(passageiro);
				Viagem  viagem = new Viagem(); 
				viagem.setIdViagem(rs.getInt("idViagem"));
				passageiro_Viagem.setViagem(viagem);
				passageiro_Viagem.setStatus(rs.getInt("status"));
				passageiro_Viagem.setConfirmacao(rs.getBoolean("confirmacao"));
				passageiro_Viagem.setData(rs.getDate("data").toLocalDate());
				passageiro_Viagem.setSaida(rs.getTime("saida").toLocalTime());
				passageiro_Viagem.setChegada(rs.getTime("chegada").toLocalTime());
				passageiro_Viagem.setIndo(rs.getBoolean("indo"));
				lista.add(passageiro_Viagem);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return lista;
	}

	@Override
	public Passageiro_Viagem get(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
