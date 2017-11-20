package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import conexao.Conexao;
import model.Empresa;
import model.Motorista;
import model.Relatorio;
import model.Rota;

public class EmpresaJdbc implements EmpresaDao {

	private Conexao conexao;

	public EmpresaJdbc(Conexao conexao) {
		this.conexao = conexao;
	}

	// insere uma empresa no banco de dados
	@Override
	public void inserir(Empresa entidade) {
		String insert = "insert into empresa values (idEmpresa,?,?,?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, entidade.getNome());
			insertStmt.setString(2, entidade.getCnpj());
			insertStmt.setString(3, entidade.getLogin());
			insertStmt.setString(4, entidade.getSenha());
			insertStmt.executeUpdate();
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			entidade.setIdEmpresa(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// exclui uma empresa passando o id dela
	@Override
	public void excluir(Integer codigo) {
		String delete = "delete from empresa where idEmpresa = ?";
		PreparedStatement deleteStmt;
		try {
			deleteStmt = conexao.get().prepareStatement(delete);
			deleteStmt.setLong(1, codigo);
			deleteStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// altera os dados da empresa
	@Override
	public void alterar(Empresa entidade) {
		String update = "update empresa set nome = ?, cnpj = ?, login = ?, senha = ? where idEmpresa = ?";
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setString(1, entidade.getNome());
			updateStmt.setString(2, entidade.getCnpj());
			updateStmt.setString(3, entidade.getLogin());
			updateStmt.setString(4, entidade.getSenha());
			updateStmt.setInt(5, entidade.getIdEmpresa());
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// lista todas as empresas, usado para pegar o login
	@Override
	public List<Empresa> listar() {
		Statement stmt = null;
		List<Empresa> empresas = new ArrayList<Empresa>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from empresa";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setNome(rs.getString("nome"));
				empresa.setCnpj(rs.getString("cnpj"));
				empresa.setLogin(rs.getString("login"));
				empresa.setSenha(rs.getString("senha"));
				empresas.add(empresa);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return empresas;
	}

	// seleciona uma empresa com base no seu id
	// vai ser usado no login
	@Override
	public Empresa get(Integer codigo) {
		Statement stmt = null;
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from empresa where idEmpresa = " + codigo;
			ResultSet rs = stmt.executeQuery(sql);
			Empresa empresa = new Empresa();
			empresa.setIdEmpresa(rs.getInt("idEmpresa"));
			empresa.setNome(rs.getString("nome"));
			empresa.setCnpj(rs.getString("cnpj"));
			empresa.setLogin(rs.getString("login"));
			empresa.setSenha(rs.getString("senha"));
			return empresa;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Relatorio> relatorioRotas(Integer codempresa, String order) {
		Statement stmt = null;
		List<Relatorio> dados = new ArrayList<Relatorio>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select r.*, m.nome, e.idempresa, e.nome from rota r join motorista m on m.idmotorista = r.idmotorista join empresa e on e.idempresa = m.idempresa"
					+ " where e.idempresa=" + codempresa + " order by r.nome "+order;
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
				empresa.setNome(rs.getString("e.nome"));

				motorista.setEmpresa(empresa);

				rota.setMotorista(motorista);

				Relatorio relatorio = new Relatorio();
				String tempo = LocalTime.now().toString().substring(0, 8);
				String data = LocalDate.now().toString();

				relatorio.setNomeEmpresa(empresa.getNome());
				relatorio.setNomeMotorista(motorista.getNome());
				relatorio.setNomeRota(rota.getNome());
				relatorio.setDataRelatorio(data);
				relatorio.setHoraRelatorio(tempo);

				dados.add(relatorio);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return dados;
	}

	@Override
	public List<Relatorio> relatorioPassageiros(Integer codempresa, String order) {
		Statement stmt = null;
		List<Relatorio> dados = new ArrayList<Relatorio>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from passageiro p join empresa e on p.idempresa = e.idempresa where e.idempresa="
					+ codempresa + " order by p.nome "+order;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setNome(rs.getString("e.nome"));

				Relatorio relatorio = new Relatorio();
				String tempo = LocalTime.now().toString().substring(0, 8);
				String data = LocalDate.now().toString();

				relatorio.setNomeEmpresa(empresa.getNome());
				relatorio.setNomePassageiro(rs.getString("p.nome"));
				relatorio.setCpfPassageiro(rs.getString("p.cpf"));
				relatorio.setTelefonePassageiro(rs.getString("p.telefone"));

				relatorio.setDataRelatorio(data);
				relatorio.setHoraRelatorio(tempo);

				dados.add(relatorio);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return dados;
	}

}
