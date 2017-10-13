package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String insert = "insert into passageiro_viagem(idViagem,idPassageiro) values (?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert);
			insertStmt.setInt(1, entidade.getViagem().getIdViagem());
			insertStmt.setInt(2, entidade.getPassageiro().getIdPassageiro());
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
			String sql = "select * from passageiro_viagem";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Passageiro_Viagem passageiro_Viagem = new Passageiro_Viagem();
				Passageiro passageiro = new Passageiro();
				passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
				passageiro_Viagem.setPassageiro(passageiro);
				Viagem viagem = new Viagem();
				viagem.setIdViagem(rs.getInt("idViagem"));
				passageiro_Viagem.setViagem(viagem);
				passageiro_Viagem.setStatus(rs.getInt("status"));
				passageiro_Viagem.setConfirmacao(rs.getBoolean("confirmacao"));
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
			String sql = "select * from passageiro_viagem where idViagem = " + codigo;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Passageiro_Viagem passageiro_Viagem = new Passageiro_Viagem();
				Passageiro passageiro = new Passageiro();
				passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
				passageiro_Viagem.setPassageiro(passageiro);
				Viagem viagem = new Viagem();
				viagem.setIdViagem(rs.getInt("idViagem"));
				passageiro_Viagem.setViagem(viagem);
				passageiro_Viagem.setStatus(rs.getInt("status"));
				passageiro_Viagem.setConfirmacao(rs.getBoolean("confirmacao"));
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

	public void alterarStatus(Passageiro entidade, Integer status, Viagem viagem) {
		String update = "update passageiro_viagem set status = ?  where idPassageiro = " + entidade.getIdPassageiro() +
				" and idViagem = " + viagem.getIdViagem();
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setInt(1, status);
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fazerCheck(Passageiro entidade, Boolean confirmacao) {
		String update = "update passageiro_viagem set confimacao = ?  where idPassageiro = " + entidade.getIdPassageiro();
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setBoolean(1, confirmacao);
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Passageiro> Lista(Passageiro_Viagem passageiro_viagem) {
		Statement stmt = null;
		List<Passageiro> passageiros = new ArrayList<Passageiro>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from passageiro join passageiro_viagem"
					+ " on passageiro.idPassageiro = passageiro_viagem.idPassageiro where idViagem = "
					+ passageiro_viagem.getViagem();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Passageiro passageiro = new Passageiro();
				passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
				passageiro.setNome(rs.getString("nome"));
				passageiro.setLogin(rs.getString("login"));
				passageiro.setSenha(rs.getString("senha"));
				passageiro.setCpf(rs.getString("cpf"));
				passageiro.setTelefone(rs.getString("telefone"));
				passageiros.add(passageiro);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return passageiros;
	}

}
