package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import conexao.Conexao;
import model.Viagem;
import model.Viagem_Ida_Volta;

public class Viagem_Ida_VoltaJdbc implements Viagem_Ida_VoltaDao {

	private Conexao conexao;

	public Viagem_Ida_VoltaJdbc(Conexao conexao) {
		this.conexao = conexao;
	}

	@Override
	public void inserir(Viagem_Ida_Volta entidade) {
		String insert = "insert into viagem_ida_volta(idIda,idVolta) values (?,?)";
		java.sql.PreparedStatement insertStmt;
		try {
			insertStmt = conexao.get().prepareStatement(insert);
			insertStmt.setInt(1, entidade.getIda().getIdViagem());
			insertStmt.setInt(2, entidade.getVolta().getIdViagem());
			insertStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer codigo) {
		String delete = "delete from viagem_ida_volta where idIdaVolta = ?";
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
	public void alterar(Viagem_Ida_Volta entidade) {
		String update = "update viagem_ida_volta set idIda = ?, idVolta = ? where idIdaVolta = "
				+ entidade.getIdIdaVolta();
		PreparedStatement updateStmt;
		try {
			updateStmt = conexao.get().prepareStatement(update);
			updateStmt.setInt(1, entidade.getIda().getIdViagem());
			updateStmt.setInt(2, entidade.getVolta().getIdViagem());
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Viagem_Ida_Volta> listar() {
		Statement stmt = null;
		List<Viagem_Ida_Volta> lista = new ArrayList<Viagem_Ida_Volta>();
		try {
			stmt = (Statement) conexao.get().createStatement();
			String sql = "select * from viagem_ida_volta";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Viagem_Ida_Volta viagem = new Viagem_Ida_Volta();
				Viagem ida = new Viagem();
				ida.setIdViagem(rs.getInt("idIda"));
				Viagem volta = new Viagem();
				volta.setIdViagem(rs.getInt("idVolta"));
				viagem.setIda(ida);
				viagem.setVolta(volta);
				viagem.setIdIdaVolta(rs.getInt("idIdaVolta"));
				lista.add(viagem);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return lista;
	}

	@Override
	public Viagem_Ida_Volta get(Integer codigo) {
		Statement stmt = null;
		try {

			stmt = (Statement) conexao.get().createStatement();
			String sql = "select viv.*, v.* from viagem_ida_volta viv join viagem v on v.idviagem = viv.idvolta from  where idIda = "
					+ codigo;
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			Viagem_Ida_Volta viagemIdaVolta = new Viagem_Ida_Volta();
			Viagem ida = new Viagem();
			Viagem volta = new Viagem();
			volta.setIdViagem(rs.getInt("idVolta"));
			ida.setIdViagem(rs.getInt("idIda"));

			viagemIdaVolta.setIdIdaVolta(rs.getInt("idIdaVolta"));
			viagemIdaVolta.setIda(ida);
			viagemIdaVolta.setVolta(volta);
			return viagemIdaVolta;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
