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

import com.mysql.jdbc.Statement;

import model.Alerta;
import model.ENTITY.Funcao;


/**
 *
 * @author WigorPaulo
 */
public class DaoFuncao {
	Alerta vAlerta = new Alerta();

	public List<Funcao> listar() throws Exception {

		List<Funcao> vListaFuncao = new ArrayList<Funcao>();
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery("select * from funcao");
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			Funcao vFuncao = new Funcao();
			vFuncao.setId(rs.getInt("id"));
			vFuncao.setNome(rs.getString("nome"));
			vFuncao.setDescricao(rs.getString("descricao"));
			vListaFuncao.add(vFuncao);
		}
		rs.close();
		st.close();

		return vListaFuncao;
	}

	public int inserir(Funcao pFuncao) {
		try {
			String vSQL = "INSERT INTO funcao(id, nome, descricao,ativo) "
					+ "VALUES(?, ?, ?,?);";
			int lastId = 0;
			
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL, Statement.RETURN_GENERATED_KEYS); //Statement.RETURN_GENERATED_KEYS  retorna o id gerado para essa função
			
			st.setString(1, Integer.toString(pFuncao.getId()));
			st.setString(2, pFuncao.getNome());
			st.setString(3, pFuncao.getDescricao());
			st.setBoolean(4, pFuncao.getAtivo());
			st.execute();

			final ResultSet rs = st.getGeneratedKeys();  //atribui o id gerado
			if (rs.next()) {
			     lastId = rs.getInt(1);
			}
			st.close();
			
			vAlerta.mensagemAlerta("Inserido com Sucesso!");
			
			ConexaoDataBase.FecharConexao();
			return lastId;

		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro na Função INSERIR! \n" + "Erro: " + e.getMessage());
			return 0;
		}
	}

	public void alterar(Funcao pFuncao) {

		try {

			int permissaoAtiva;

			if (pFuncao.getAtivo() == true) {
				permissaoAtiva = 1;
			} else {
				permissaoAtiva = 0;
			}

			String vSQL = "UPDATE funcao SET `nome`='" + pFuncao.getNome() + "', `descricao`='" + pFuncao.getDescricao() + "', `ativo`=" + permissaoAtiva + " WHERE `id`='" + pFuncao.getId() + "'";
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
	
	
	
	public boolean excluir(Funcao pFuncao) {
    	try {
    		
			String vSQL = "DELETE FROM funcao WHERE `id`='" + pFuncao.getId() +"'";
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			
			st.execute();
	        st.close();
	        vAlerta.mensagemAlerta("Excluido com Sucesso!");
	        ConexaoDataBase.FecharConexao();
	        return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função Excluir em dao_funcao! \n"+"Erro: "+e.getMessage());
			return false;
		}
    	
    }
	

	public List<Funcao> filtrar(Integer id,String nome) {
    	try {
    		
			String vSQL = "select * from funcao";

			if(id != null && nome.equals("")){
				vSQL = vSQL + " where id =" +id+ " ";
			}else if((!nome.equals("")) && id == null ) {
				vSQL = vSQL + " where nome = '"+nome+"'";
			}else if(!(nome.equals("") && id == null)) {
				vSQL= vSQL + " where id =" +id+ "  and nome = '" +nome+ "'";
			}
			
			
			
			List<Funcao> vListaFuncao = new ArrayList<Funcao>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
			    Funcao vFuncao = new Funcao();
			    vFuncao.setId(rs.getInt("id"));
			    vFuncao.setNome(rs.getString("nome"));
			    vFuncao.setDescricao(rs.getString("descricao"));   
			    vFuncao.setAtivo(rs.getBoolean("ativo"));
			    
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
	
	public Funcao listarFuncao(int pIdFuncao) throws Exception{

        Funcao vFuncao = new Funcao();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("SELECT  * from funcao where id = "+pIdFuncao+"");
        
        ResultSet rs = st.getResultSet();
        if(rs.next()){
            vFuncao.setId(rs.getInt("id"));
            vFuncao.setNome(rs.getString("nome"));
            vFuncao.setDescricao(rs.getString("descricao"));
            vFuncao.setAtivo(rs.getBoolean("ativo"));
        }
        rs.close();
        st.close();
        
        return vFuncao;       
    }
}
