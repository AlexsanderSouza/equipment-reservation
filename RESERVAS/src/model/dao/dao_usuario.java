/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.DAO.ConexaoDataBase;
import model.ENTITY.usuario;





/**
 *
 * @author WigorPaulo
 */
public class dao_usuario {
    alerta vAlerta = new alerta();
    
    public List<usuario> listar() throws Exception{
    	
    	
        
        List<usuario> vListaUser = new ArrayList<usuario>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("SELECT us.id, us.nome, us.matricula, us.senha, us.email, us.telefone, us.ativo, us.id_funcao, us.status, fc.nome as nome_funcao FROM usuario us left join funcao fc on us.id_funcao = fc.id");
        
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            usuario vUser = new usuario();
            vUser.setId(rs.getInt("id"));
            vUser.setNome(rs.getString("nome"));
            vUser.setMatricula(rs.getString("matricula"));
            vUser.setSenha(rs.getString("senha"));
            vUser.setEmail(rs.getString("email"));
            vUser.setTelefone(rs.getString("telefone"));
            vUser.setAtivo(rs.getBoolean("ativo"));
            vUser.setStatus(rs.getString("status"));
            vUser.setFuncao(rs.getString("nome_funcao"));
            vListaUser.add(vUser);
        }
        rs.close();
        st.close();
        
        return vListaUser;       
    }

    
    public void inserir(usuario pUser){
        try {
            String vSQL = "INSERT INTO usuario(id, nome, matricula, senha, email, telefone, ativo, id_funcao, status) "
                                      +"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pUser.getId()));
            st.setString(2, pUser.getNome());
            st.setString(3, pUser.getMatricula());
            st.setString(4, pUser.getSenha());
            st.setString(5, pUser.getEmail());
            st.setString(6, pUser.getTelefone());
            st.setBoolean(7, pUser.getAtivo());
            st.setString(8, pUser.getFuncao());
            st.setString(9, pUser.getStatus());
            
            st.execute();
            st.close();
           System.out.println("inserido"); 
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
    
    public void excluir(usuario pUsuario) {
    	try {
    		
			String vSQL = "DELETE FROM usuario WHERE `id`='" + pUsuario.getId() +"'";
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			
			st.execute();
	        st.close();
	        vAlerta.mensagemAlerta("Excluido com Sucesso!");
	        ConexaoDataBase.FecharConexao();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função Excluir em dao_usuario! \n"+"Erro: "+e.getMessage());
		}
    	
    }
    
    public void alterar(usuario pUsuario) {

		try {

			int permissaoAtiva;

			if (pUsuario.getAtivo() == true) {
				permissaoAtiva = 1;
			} else {
				permissaoAtiva = 0;
			}

			String vSQL = "UPDATE usuario SET `nome`='" + pUsuario.getNome() + "', `matricula`='"
					+ pUsuario.getMatricula() + "', `ativo`='" + permissaoAtiva + "', `senha`='"+ pUsuario.getSenha() +"', `email`='"+ pUsuario.getEmail() +"', "
					+ "`telefone`='"+ pUsuario.getTelefone() +"', `id_funcao`='"+ Integer.parseInt(pUsuario.getFuncao()) +"', `status`='"+ pUsuario.getStatus() +"' WHERE `id`='" + pUsuario.getId() + "'";
			System.out.println(vSQL);
			System.out.println(pUsuario.getAtivo());
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
    
    
  
	public List<usuario> filtrar(Integer id,String nome, String matricula) {
    	try {
    		
			String vSQL = "SELECT us.id, us.nome, us.matricula, us.senha, us.email, us.telefone, us.ativo, us.id_funcao, us.status, fc.nome as nome_funcao FROM usuario us left join funcao fc on us.id_funcao = fc.id where us.id !=0";

			if(id != null ){
				vSQL = vSQL + " and us.id =" +id+ " ";
			}
			
			if(!nome.equals("")) {
				vSQL = vSQL + " and us.nome = '"+nome+"'";
			}

			if(!matricula.equals("")) {
				vSQL = vSQL + " and us.matricula = '"+ matricula +"'";
			}
			
			
			
			List<usuario> vListaUsuario = new ArrayList<usuario>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
			    usuario vUsuario = new usuario();
			    vUsuario.setId(rs.getInt("id"));
			    vUsuario.setMatricula(rs.getString("matricula"));
			    vUsuario.setNome(rs.getString("nome"));   
			    vUsuario.setAtivo(rs.getBoolean("ativo"));
			    vUsuario.setFuncao(rs.getString("nome_funcao"));
			    vUsuario.setEmail(rs.getString("email"));
			    vUsuario.setTelefone(rs.getString("telefone"));
			    vUsuario.setStatus(rs.getString("status"));
			    vUsuario.setSenha(rs.getString("senha"));
			   
			    vListaUsuario.add(vUsuario);
			}
			rs.close();
			st.close();
			
			return vListaUsuario;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função FILTRO! \n"+"Erro: "+e.getMessage());
			return null;
		} 
    }
}
