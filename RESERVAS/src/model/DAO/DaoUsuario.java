/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import model.Alerta;
import model.DAO.ConexaoDataBase;
import model.ENTITY.Usuario;





/**
 *
 * @author WigorPaulo
 */
public class DaoUsuario {
    Alerta vAlerta = new Alerta();
    
    public List<Usuario> ValidaLogin(Usuario pUser) throws Exception{  //não precisa de uma lista aqui, pois retorna somente um usuario
    	String vSQL = "select user.id, " + 
    			"      user.nome, " +
    			"	   user.matricula, " + 
    			"	   user.senha " + 
    			"  from usuario user " + 
    			" where user.matricula = '"+pUser.getMatricula()+"'"+ 
    			"   and user.senha = '"+pUser.getSenha()+"'";
    	
    	List<Usuario> vListaUser = new ArrayList<Usuario>();
    	
    	java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
    	st.executeQuery(vSQL);
    	
    	ResultSet rs = st.getResultSet();
    	while(rs.next()){
            Usuario vUser = new Usuario();
            vUser.setId(rs.getInt("id"));  
            vUser.setNome(rs.getString("nome"));
            vUser.setMatricula(rs.getString("matricula"));
            vUser.setSenha("senha");
            vListaUser.add(vUser);
    	}
    	rs.close();
        st.close();
        
        return vListaUser;     	
    }
    
    public List<Usuario> listar() throws Exception{

        List<Usuario> vListaUser = new ArrayList<Usuario>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("SELECT us.id, us.nome, us.matricula, us.senha, us.email, us.telefone, us.ativo, us.id_funcao, us.status, fc.nome as nome_funcao FROM usuario us left join funcao fc on us.id_funcao = fc.id");
        
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            Usuario vUser = new Usuario();
            vUser.setId(rs.getInt("id"));
            vUser.setNome(rs.getString("nome"));
            vUser.setMatricula(rs.getString("matricula"));
            vUser.setSenha(rs.getString("senha"));
            vUser.setEmail(rs.getString("email"));
            vUser.setTelefone(rs.getString("telefone"));
            vUser.setAtivo(rs.getBoolean("ativo"));
            vUser.setStatus(rs.getString("status"));
            vUser.setNomeFuncao(rs.getString("nome_funcao"));
            vUser.setId_funcao(rs.getInt("id_funcao"));
            vListaUser.add(vUser);
        }
        rs.close();
        st.close();
        
        return vListaUser;       
    }

    
    public int inserir(Usuario pUser){
        try {
            String vSQL = "INSERT INTO usuario(id, nome, matricula, senha, email, telefone, ativo, id_funcao, status) "
                                      +"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            int lastId = 0;
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, Integer.toString(pUser.getId()));
            st.setString(2, pUser.getNome());
            st.setString(3, pUser.getMatricula());
            st.setString(4, pUser.getSenha());
            st.setString(5, pUser.getEmail());
            st.setString(6, pUser.getTelefone());
            st.setBoolean(7, pUser.getAtivo());
            st.setInt(8, pUser.getId_funcao());
            st.setString(9, pUser.getStatus());
            st.execute();
            
            final ResultSet rs = st.getGeneratedKeys();  //atribui o id gerado
			if (rs.next()) {
			     lastId = rs.getInt(1);
			}
            st.close();
           System.out.println("inserido"); 
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            return lastId;
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        	return 0;
        }    
    }
    
    public void excluir(Usuario pUsuario) {
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
    
    public void alterar(Usuario pUsuario) {

		try {

			int permissaoAtiva;

			if (pUsuario.getAtivo() == true) {
				permissaoAtiva = 1;
			} else {
				permissaoAtiva = 0;
			}

			String vSQL = "UPDATE usuario SET `nome`='" + pUsuario.getNome() + "', `matricula`='"
					+ pUsuario.getMatricula() + "', `ativo`='" + permissaoAtiva + "', `senha`='"+ pUsuario.getSenha() +"', `email`='"+ pUsuario.getEmail() +"', "
					+ "`telefone`='"+ pUsuario.getTelefone() +"', `id_funcao`='"+ pUsuario.getId_funcao() +"', `status`='"+ pUsuario.getStatus() +"' WHERE `id`='" + pUsuario.getId() + "'";
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
    
    
  
	public List<Usuario> filtrar(Integer id,String nome, String matricula) {
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
			
			List<Usuario> vListaUsuario = new ArrayList<Usuario>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
			    Usuario vUsuario = new Usuario();
			    vUsuario.setId(rs.getInt("id"));
			    vUsuario.setMatricula(rs.getString("matricula"));
			    vUsuario.setNome(rs.getString("nome"));   
			    vUsuario.setAtivo(rs.getBoolean("ativo"));
			    vUsuario.setId_funcao(rs.getInt("id_funcao"));
			    vUsuario.setEmail(rs.getString("email"));
			    vUsuario.setTelefone(rs.getString("telefone"));
			    vUsuario.setStatus(rs.getString("status"));
			    vUsuario.setSenha(rs.getString("senha"));
			    vUsuario.setNomeFuncao(rs.getString("nome_funcao"));
			   
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
