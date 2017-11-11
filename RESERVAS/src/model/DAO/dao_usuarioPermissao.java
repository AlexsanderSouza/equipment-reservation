package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import model.alerta;
import model.ENTITY.permissao;
import model.ENTITY.usuario;

public class dao_usuarioPermissao {
	alerta vAlerta = new alerta();

	public List<permissao> listarPermissao(int pIdUsuario) throws Exception {

		List<permissao> vListaPermissao = new ArrayList<permissao>();
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery(
				"SELECT per.id, per.nome, per.descricao, per.ativo FROM permissao per right join (select * from usuario_permissao where id_usuario = "
						+ pIdUsuario + ") fc on per.id = fc.id_permissao");
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			permissao vPermissao = new permissao();
			vPermissao.setId(rs.getInt("id"));
			vPermissao.setNome(rs.getString("nome"));
			vPermissao.setDescricao(rs.getString("descricao"));
			vListaPermissao.add(vPermissao);
		}
		rs.close();
		st.close();

		return vListaPermissao;
	}
	
	public List<permissao> listarPermissaoUsuarioFuncao(int pIdFuncao, int pIdUsuario) { //retorna todas as permissões que estão relacionadas com usuario mas não com função

		try {
			List<permissao> vListaPermissao = new ArrayList<permissao>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery("SELECT per.id, per.nome, per.descricao, per.ativo FROM permissao per right join (SELECT upe.id_permissao FROM usuario_permissao upe "
					+ "WHERE upe.id_permissao NOT IN  (select id_permissao from funcao_permissao where id_funcao = "+pIdFuncao+") and upe.id_usuario ="+pIdUsuario+") fc on per.id = fc.id_permissao;");
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				permissao vPermissao = new permissao();
				vPermissao.setId(rs.getInt("id"));
				vPermissao.setNome(rs.getString("nome"));
				vPermissao.setDescricao(rs.getString("descricao"));
				vListaPermissao.add(vPermissao);
			}
			rs.close();
			st.close();

			return vListaPermissao;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public void inserir(int pPermissao, int pLastId) {
		try {

			String vSQL = "INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ( ?, ?);";
			
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			st.setInt(1, pLastId);
			st.setInt(2, pPermissao);

			st.execute();
			st.close();
			ConexaoDataBase.FecharConexao();

		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro na Função INSERIR usuario_permissao! \n" + "Erro: " + e.getMessage());
		}
	}

	public void excluirUsuarioPermissao(usuario pUsuario) {
		try {

			String vSQL = "DELETE FROM usuario_permissao WHERE `id_usuario`='" + pUsuario.getId() + "'";
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);

			st.execute();
			st.close();

			ConexaoDataBase.FecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta(
					"Erro na Função ExcluirFuncao em dao_usuarioPermissao! \n" + "Erro: " + e.getMessage());
		}

	}

	public void excluirPermissaoUsuario(permissao pPermissao) {
		try {

			String vSQL = "DELETE FROM usuario_permissao WHERE `id_permissao`='" + pPermissao.getId() + "'";
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);

			st.execute();
			st.close();

			ConexaoDataBase.FecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta(
					"Erro na Função ExcluirPermissao em dao_usuarioPermissao! \n" + "Erro: " + e.getMessage());
		}

	}
}
