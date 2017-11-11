package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import conexao.Conexao;
import model.Motorista;
import model.Passageiro;
import model.Passageiro_Viagem;
import model.Rota;
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
	public Passageiro_Viagem get(Integer codigoPassageiro) {
		Statement stmt = null;
		try {
			// esse select funciona, nao apaga
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from passageiro_viagem pv join passageiro p on pv.idpassageiro = p.idpassageiro "
					+ "join viagem v on v.idviagem = pv.idviagem  join rota r on r.idrota = v.idrota "
					+ "join motorista m on m.idmotorista = r.idmotorista " + "where pv.idpassageiro ="
					+ codigoPassageiro;
			ResultSet rs = stmt.executeQuery(sql);
			Passageiro_Viagem passageiro_viagem = new Passageiro_Viagem();
			Passageiro passageiro = new Passageiro();
			passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
			passageiro.setNome(rs.getString("nome"));
			passageiro.setLogin(rs.getString("login"));
			passageiro.setSenha(rs.getString("senha"));
			passageiro.setCpf(rs.getString("cpf"));
			passageiro.setTelefone(rs.getString("telefone"));

			Viagem viagem = new Viagem();
			viagem.setIdViagem(rs.getInt("idViagem"));
			viagem.setData(rs.getDate("data").toLocalDate());
			viagem.setSaida(rs.getTime("saida").toLocalTime());
			viagem.setChegada(rs.getTime("chegada").toLocalTime());

			viagem.setDirigindo(rs.getBoolean("dirigindo"));
			Rota rota = new Rota();
			rota.setIdRota(rs.getInt("idRota"));
			rota.setNome(rs.getString("nome"));

			passageiro_viagem.setIdViagem(rs.getInt("pv.idViagem"));
			passageiro_viagem.setIdPassageiro(rs.getInt("pv.idpassageiro"));
			passageiro_viagem.setStatus(rs.getInt("status"));
			passageiro_viagem.setConfirmacao(rs.getBoolean("confirmacao"));

			Motorista motorista = new Motorista();
			motorista.setIdMotorista(rs.getInt("idMotorista"));
			motorista.setNome(rs.getString("nome"));
			motorista.setApelido(rs.getString("apelido"));
			motorista.setLogin(rs.getString("login"));
			motorista.setSenha(rs.getString("senha"));

			passageiro_viagem.setPassageiro(passageiro);
			passageiro_viagem.setViagem(viagem);
			rota.setMotorista(motorista);
			viagem.setRota(rota);

			return passageiro_viagem;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public void alterarStatus(Passageiro entidade, Integer status, Viagem viagem) {
		String update = "update passageiro_viagem set status = ?  where idPassageiro = " + entidade.getIdPassageiro()
				+ " and idViagem = " + viagem.getIdViagem();
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setInt(1, status);
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fazerCheck(Passageiro entidade, Boolean confirmacao, Viagem viagem) {
		String update = "update passageiro_viagem set confirmacao = ?  where idPassageiro = "
				+ entidade.getIdPassageiro() + " and idViagem = " + viagem.getIdViagem();
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
	public List<Passageiro_Viagem> ListaPassageiro(Passageiro_Viagem passageiro_viagem) {
		Statement stmt = null;
		List<Passageiro_Viagem> passageiros = new ArrayList<Passageiro_Viagem>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select passageiro.idPassageiro, passageiro.nome, passageiro.telefone, status, confirmacao "
					+ "from passageiro join passageiro_viagem"
					+ " on passageiro.idPassageiro = passageiro_viagem.idPassageiro where idViagem = "
					+ passageiro_viagem.getViagem().getIdViagem() + " order by status asc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Passageiro_Viagem passageiroViagem = new Passageiro_Viagem();
				Passageiro passageiro = new Passageiro();
				passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
				passageiro.setNome(rs.getString("nome"));
				passageiro.setTelefone(rs.getString("telefone"));
				passageiroViagem.setPassageiro(passageiro);
				passageiroViagem.setStatus(rs.getInt("status"));
				passageiroViagem.setConfirmacao(rs.getBoolean("confirmacao"));
				passageiros.add(passageiroViagem);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return passageiros;
	}

	@Override
	public List<Passageiro_Viagem> ListaViagem(Integer codviagem) {
		Statement stmt = null;
		List<Passageiro_Viagem> passageiros = new ArrayList<Passageiro_Viagem>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from view_lista_viagem where idViagem = " + codviagem + " order by status asc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Passageiro_Viagem passageiroViagem = new Passageiro_Viagem();
				Passageiro passageiro = new Passageiro();
				passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
				passageiro.setNome(rs.getString("nome"));
				passageiro.setTelefone(rs.getString("telefone"));
				passageiroViagem.setPassageiro(passageiro);
				passageiroViagem.setStatus(rs.getInt("status"));
				passageiroViagem.setConfirmacao(rs.getBoolean("confirmacao"));
				passageiros.add(passageiroViagem);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return passageiros;
	}

	@Override
	public List<Passageiro_Viagem> ListaMotorista(Integer codmotorista, Integer codviagem) {
		Statement stmt = null;
		List<Passageiro_Viagem> passageiros = new ArrayList<Passageiro_Viagem>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from view_motorista_lista where idViagem = " + codviagem + " and idMotorista = "
					+ codmotorista + " order by status asc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Passageiro_Viagem passageiroViagem = new Passageiro_Viagem();
				Passageiro passageiro = new Passageiro();
				passageiro.setIdPassageiro(rs.getInt("idPassageiro"));
				passageiro.setNome(rs.getString("nome"));
				passageiro.setTelefone(rs.getString("telefone"));
				passageiroViagem.setPassageiro(passageiro);
				passageiroViagem.setStatus(rs.getInt("status"));
				passageiroViagem.setConfirmacao(rs.getBoolean("confirmacao"));
				passageiros.add(passageiroViagem);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return passageiros;
	}

	@Override
	public Motorista getMotorista(Integer codviagem) {
		Statement stmt = null;
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select m.nome from passageiro_viagem pv join passageiro"
					+ " on passageiro.idPassageiro = passageiro_viagem.idPassageiro where idViagem = " + codviagem;
			ResultSet rs = stmt.executeQuery(sql);
			Motorista motorista = new Motorista();

			motorista.setNome(rs.getString("nome"));

			return motorista;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
