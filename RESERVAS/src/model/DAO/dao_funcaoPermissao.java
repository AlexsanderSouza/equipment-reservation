package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.funcao;
import model.ENTITY.permissao;

public class dao_funcaoPermissao {
	
	alerta vAlerta = new alerta();
		
	public List<permissao> listar(int pIdFuncao) throws Exception {

		List<permissao> vListaPermissao = new ArrayList<permissao>();
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery("SELECT per.id, per.nome, per.descricao, per.ativo FROM permissao per right join (select * from funcao_permissao where id_funcao = "+ pIdFuncao+ ") fc on per.id = fc.id_permissao");
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
	
	public void inserir(String pPermissao, int pLastId) {
		try {
			
			String vSQL = "INSERT INTO funcao_permissao (id_funcao, id_permissao) VALUES ( ?, ?);";	
			
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			st.setInt(1, pLastId);
			st.setInt(2, Integer.parseInt(pPermissao));
			
			st.execute();
			st.close();
			ConexaoDataBase.FecharConexao();

		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro na Função INSERIR funcao_permissao! \n" + "Erro: " + e.getMessage());
		}
	}
	
	
		
}
