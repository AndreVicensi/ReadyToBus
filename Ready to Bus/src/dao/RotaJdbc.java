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
import model.Rota;

public class RotaJdbc implements RotaDao {

	private Conexao conexao;

	public RotaJdbc(Conexao conexao) {
		this.conexao = conexao;
	}

	private MotoristaJdbc motoristaJdbc = new MotoristaJdbc(conexao);

	@Override
	public void inserir(Rota entidade) {
		String insert = "insert into rota values (idRota,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(2, entidade.getMotorista().getIdMotorista());
			insertStmt.setString(1, entidade.getNome());
			insertStmt.executeUpdate();
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			entidade.setIdRota(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer codigo) {
		String delete = "delete from rota where idRota = ?";
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
	public void alterar(Rota entidade) {
		String update = "update rota set idMotorista = ?, nome = ? where idrota = ?";
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setInt(1, entidade.getMotorista().getIdMotorista());
			updateStmt.setString(2, entidade.getNome());
			updateStmt.setInt(3, entidade.getIdRota());
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Rota> listarDaEmpresa(Integer codempresa) {
		Statement stmt = null;
		List<Rota> rotas = new ArrayList<Rota>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from rota r join motorista m on m.idmotorista = r.idmotorista join empresa e on e.idempresa = m.idempresa"
					+ " where e.idempresa=" + codempresa;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Rota rota = new Rota();
				rota.setIdRota(rs.getInt("idRota"));
				rota.setNome(rs.getString("nome"));

				Motorista motorista = new Motorista();
				motorista.setIdMotorista(rs.getInt("idMotorista"));

				rota.setMotorista(motorista);

				rotas.add(rota);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rotas;
	}

	@Override
	public List<Rota> listarRelatorio(Integer codempresa) {
		Statement stmt = null;
		List<Rota> rotas = new ArrayList<Rota>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select r.*, m.nome, e.idempresa from rota r join motorista m on m.idmotorista = r.idmotorista join empresa e on e.idempresa = m.idempresa"
					+ " where e.idempresa=" + codempresa;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Rota rota = new Rota();
				rota.setIdRota(rs.getInt("idRota"));
				rota.setNome(rs.getString("nome"));

				Motorista motorista = new Motorista();
				motorista.setIdMotorista(rs.getInt("idMotorista"));
				motorista.setNome(rs.getString("m.nome"));

				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("e.idempresa"));

				motorista.setEmpresa(empresa);

				rota.setMotorista(motorista);

				rotas.add(rota);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rotas;
	}

	@Override
	public Rota get(Integer codigo) {
		Statement stmt = null;
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from rota where idRota = " + codigo;
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			Rota rota = new Rota();
			rota.setIdRota(rs.getInt("idRota"));
			rota.setNome(rs.getString("nome"));
			rota.setMotorista(motoristaJdbc.get(rs.getInt("idMotorista")));
			return rota;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Rota> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
