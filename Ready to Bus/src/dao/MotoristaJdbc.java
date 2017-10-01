package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import conexao.Conexao;
import model.Empresa;
import model.Motorista;

public class MotoristaJdbc implements MotoristaDao {

	private Conexao conexao;

	public MotoristaJdbc(Conexao conexao) {
		this.conexao = conexao;
	}
	
	private EmpresaJdbc empresaJdbc = new EmpresaJdbc(conexao);
	//insere motorista no banco de dados
	@Override
	public void inserir(Motorista entidade) {
		String insert = "insert into motorista values (idMotorista,?,?,?,?,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, entidade.getNome());
			insertStmt.setString(2, entidade.getApelido());
			insertStmt.setString(3, entidade.getLogin());
			insertStmt.setString(4, entidade.getSenha());
			insertStmt.setInt(5, entidade.getEmpresa().getIdEmpresa());
			insertStmt.setBoolean(6, entidade.isDirigindo());
			insertStmt.executeUpdate();
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			entidade.setIdMotorista(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer codigo) {
		String delete = "delete from motorista where idMotorista = ?";
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
	public void alterar(Motorista entidade) {
		String update = "update motorista set nome = ?, apelido = ?, login = ?, senha = ?,"
				+ "dirigindo = ?  where idMotorista = ?";
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);	
			updateStmt.setString(1, entidade.getNome());
			updateStmt.setString(2, entidade.getApelido());
			updateStmt.setString(3, entidade.getLogin());
			updateStmt.setString(4, entidade.getSenha());
			updateStmt.setBoolean(5, entidade.isDirigindo());
			updateStmt.setInt(6, entidade.getIdMotorista());
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Motorista> listar() {
		Statement stmt = null;
		List<Motorista> motoristas = new ArrayList<Motorista>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from motorista";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			     Motorista motorista = new Motorista();
				motorista.setIdMotorista(rs.getInt("idMotorista"));
				motorista.setNome(rs.getString("nome"));
				motorista.setApelido(rs.getString("apelido"));
				motorista.setLogin(rs.getString("login"));
				motorista.setSenha(rs.getString("senha"));
				Empresa empresa = empresaJdbc.get(rs.getInt("idEmpresa"));
				motorista.setEmpresa(empresa);
				motorista.setDirigindo(rs.getBoolean("dirigindo"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return motoristas;
	}

	@Override
	public Motorista get(Integer codigo) {
		Statement stmt = null;
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from motorista where idMotorista = "+codigo;
			ResultSet rs = stmt.executeQuery(sql);
			Motorista motorista = new Motorista();
			motorista.setIdMotorista(rs.getInt("idMotorista"));
			motorista.setNome(rs.getString("nome"));
			motorista.setApelido(rs.getString("apelido"));
			motorista.setLogin(rs.getString("login"));
			motorista.setSenha(rs.getString("senha"));
			motorista.setEmpresa(empresaJdbc.get(rs.getInt("idEmpresa")));
			motorista.setDirigindo(rs.getBoolean("dirigindo"));
				return motorista;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
