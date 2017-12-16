package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Alerta;
import model.ENTITY.UsuarioLogado;

public class DaoUsuarioLogado {
	
	 Alerta vAlerta = new Alerta();
	 
	 public void alterar (UsuarioLogado pUsuarioLogado){
	    	
	    	try{
	    			    			    		
	    		String vSQL = "UPDATE usuario_logado SET `usuario_id`=" + Integer.toString(pUsuarioLogado.getUsuario_id());
	    		
				PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);

				st.execute();
				st.close();
			
				ConexaoDataBase.FecharConexao();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				vAlerta.mensagemAlerta("Erro na Função alterar! \n" + "Erro: " + e.getMessage());
	    	}
	    
	 }
	
	 public int listar() throws SQLException {
	        int vUsuarioLogado = 0;
	        
	        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
	        st.executeQuery("select * from usuario_logado");
	        ResultSet rs = st.getResultSet();
	        while(rs.next()){
	        	
	        	vUsuarioLogado = rs.getInt("usuario_id");
	            
	        
	        }
	        rs.close();
	        st.close();
	        
	        return vUsuarioLogado;       
	    }
	 
	 
	
	
}
