package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Alerta;
import model.ENTITY.Funcao;
import model.ENTITY.Permissao;

public class DaoFuncaoPermissao {
	
	Alerta vAlerta = new Alerta();
		
	public List<Permissao> listar(int pIdFuncao) throws Exception {

		List<Permissao> vListaPermissao = new ArrayList<Permissao>();
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery("SELECT per.id, per.nome, per.descricao, per.ativo FROM permissao per right join (select * from funcao_permissao where id_funcao = "+ pIdFuncao+ ") fc on per.id = fc.id_permissao");
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			Permissao vPermissao = new Permissao();
			vPermissao.setId(rs.getInt("id"));
			vPermissao.setNome(rs.getString("nome"));
			vPermissao.setDescricao(rs.getString("descricao"));
			vListaPermissao.add(vPermissao);
		}
		rs.close();
		st.close();

		return vListaPermissao;
	}
	
	public void inserir(int pPermissao, int pLastId) {
		try {
			
			String vSQL = "INSERT INTO funcao_permissao (id_funcao, id_permissao) VALUES ( ?, ?);";	
			
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			st.setInt(1, pLastId);
			st.setInt(2, pPermissao);
			
			st.execute();
			st.close();
			ConexaoDataBase.FecharConexao();

		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro na Função INSERIR funcao_permissao! \n" + "Erro: " + e.getMessage());
		}
	}
		
			public void excluirFuncao(Funcao pFuncao) {
		    	try {
		    		
					String vSQL = "DELETE FROM funcao_permissao WHERE `id_funcao`='" + pFuncao.getId() +"'";
					PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
					
					
					st.execute();
			        st.close();
			      
			        ConexaoDataBase.FecharConexao();
			        
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					vAlerta.mensagemAlerta("Erro na Função ExcluirFuncao em dao_funcaoPermissao! \n"+"Erro: "+e.getMessage());
				}
		    	
		    }
			
			public void excluirPermissao(Permissao pPermissao) {
		    	try {
		    		
					String vSQL = "DELETE FROM funcao_permissao WHERE `id_permissao`='" + pPermissao.getId() +"'";
					PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);

					st.execute();
			        st.close();
			      
			        ConexaoDataBase.FecharConexao();
			        
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					vAlerta.mensagemAlerta("Erro na Função ExcluirPermissao em dao_funcaoPermissao! \n"+"Erro: "+e.getMessage());
				}
		    	
		    }
}
