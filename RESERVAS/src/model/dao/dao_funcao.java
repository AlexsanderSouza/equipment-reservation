/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.funcao;
import model.ENTITY.permissao;

/**
 *
 * @author WigorPaulo
 */
public class dao_funcao {
	alerta vAlerta = new alerta();

	public List<funcao> listar() throws Exception {

		List<funcao> vListaFuncao = new ArrayList<funcao>();
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery("select * from funcao");
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			funcao vFuncao = new funcao();
			vFuncao.setId(rs.getInt("id"));
			vFuncao.setNome(rs.getString("nome"));
			vFuncao.setDescricao(rs.getString("descricao"));
			vListaFuncao.add(vFuncao);
		}
		rs.close();
		st.close();

		return vListaFuncao;
	}

	public void inserir(funcao pFuncao) {
		try {
			String vSQL = "INSERT INTO funcao(id, nome, descricao, id_permissao1, id_permissao2, id_permissao3, id_permissao4, id_permissao5, id_permissao6) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			st.setString(1, Integer.toString(pFuncao.getId()));
			st.setString(2, pFuncao.getNome());
			st.setString(3, pFuncao.getDescricao());
			st.setInt(4, pFuncao.getId_permissao1());
			st.setInt(5, pFuncao.getId_permissao2());
			st.setInt(6, pFuncao.getId_permissao3());
			st.setInt(7, pFuncao.getId_permissao4());
			st.setInt(8, pFuncao.getId_permissao5());
			st.setInt(9, pFuncao.getId_permissao6());
			st.execute();
			st.close();

			vAlerta.mensagemAlerta("Inserido com Sucesso!");
			ConexaoDataBase.FecharConexao();

		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro na Função INSERIR! \n" + "Erro: " + e.getMessage());
		}
	}

	public void alterar(funcao pFuncao) {

		try {

			int permissaoAtiva;

			if (pFuncao.getAtivo() == true) {
				permissaoAtiva = 1;
			} else {
				permissaoAtiva = 0;
			}

			String vSQL = "UPDATE funcao SET `nome`='" + pFuncao.getNome() + "', `descricao`='"
					+ pFuncao.getDescricao() + "', `ativo`='" + permissaoAtiva + "', `id_permissao1`='"+ pFuncao.getId_permissao1() +"', `id_permissao2`='"+ pFuncao.getId_permissao2() +"', "
					+ "`id_permissao3`='"+ pFuncao.getId_permissao3() +"', `id_permissao4`='"+ pFuncao.getId_permissao4() +"', `id_permissao5`='"+ pFuncao.getId_permissao5() +"', `id_permissao6`='"+ pFuncao.getId_permissao6() +"' WHERE `id`='" + pFuncao.getId() + "'";
			System.out.println(vSQL);
			System.out.println(pFuncao.getAtivo());
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);

			st.execute();
			st.close();
			vAlerta.mensagemAlerta("Alterado com Sucesso!");
			ConexaoDataBase.FecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função alterar! \n" + "Erro: " + e.getMessage());
		}
	}
	
	public List<String> listViewAlterar(funcao pFuncao) {
		try {
			String vSQL = "SELECT per.id, per.nome FROM permissao per left join funcao fc on per.id = fc.id_permissao1 or per.id = fc.id_permissao2 or per.id = fc.id_permissao3 or per.id = fc.id_permissao4\r\n" + 
					"or per.id = fc.id_permissao5 or per.id = fc.id_permissao6 where  fc.id = "+pFuncao.getId()+"";
			
			List<String> vListaFuncao = new ArrayList<String>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				
				vListaFuncao.add(rs.getInt("id") + " - "+ rs.getString("nome"));
			}
			rs.close();
			st.close();

			return vListaFuncao;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro no metodo listViewAlterar na classe dao_funcao! \n" + "Erro: " + e.getMessage());
			return null;
		}
	}
	
	public void excluir(funcao pFuncao) {
    	try {
    		
			String vSQL = "DELETE FROM funcao WHERE `id`='" + pFuncao.getId() +"'";
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			
			st.execute();
	        st.close();
	        vAlerta.mensagemAlerta("Excluido com Sucesso!");
	        ConexaoDataBase.FecharConexao();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função Excluir em dao_funcao! \n"+"Erro: "+e.getMessage());
		}
    	
    }
	
	@SuppressWarnings("unused")
	public List<funcao> filtrar(Integer id,String nome) {
    	try {
    		
			String vSQL = "select * from funcao";

			if(id != null && nome.equals("")){
				vSQL = vSQL + " where id =" +id+ " ";
			}else if((!nome.equals("")) && id == null ) {
				vSQL = vSQL + " where nome = '"+nome+"'";
			}else if(!(nome.equals("") && id == null)) {
				vSQL= vSQL + " where id =" +id+ "  and nome = '" +nome+ "'";
			}
			
			
			
			List<funcao> vListaFuncao = new ArrayList<funcao>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
			    funcao vFuncao = new funcao();
			    vFuncao.setId(rs.getInt("id"));
			    vFuncao.setNome(rs.getString("nome"));
			    vFuncao.setDescricao(rs.getString("descricao"));   
			    vFuncao.setAtivo(rs.getBoolean("ativo"));
			    vFuncao.setId_permissao1(rs.getInt("id_permissao1"));
			    vFuncao.setId_permissao2(rs.getInt("id_permissao2"));
			    vFuncao.setId_permissao3(rs.getInt("id_permissao3"));
			    vFuncao.setId_permissao4(rs.getInt("id_permissao4"));
			    vFuncao.setId_permissao5(rs.getInt("id_permissao5"));
			    vFuncao.setId_permissao6(rs.getInt("id_permissao6"));
			    
			    vListaFuncao.add(vFuncao);
			}
			rs.close();
			st.close();
			
			return vListaFuncao;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função FILTRO! \n"+"Erro: "+e.getMessage());
			return null;
		} 
    }
}
