package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.tipoRecurso;

public class dao_tipoRecurso {
alerta vAlerta = new alerta();





    
    public List<tipoRecurso> listar() throws Exception{
        
        List<tipoRecurso> vListaTipo_recurso = new ArrayList<tipoRecurso>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from tipo_recurso");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
        	tipoRecurso vTipo_recurso = new tipoRecurso();
            vTipo_recurso.setId(rs.getInt("id"));
            vTipo_recurso.setNome(rs.getString("nome"));      
            vTipo_recurso.setAtivo(rs.getBoolean("ativo"));
            vListaTipo_recurso.add(vTipo_recurso);
        }
        rs.close();
        st.close();
        
        return vListaTipo_recurso;       
    }

    
    public void inserir(tipoRecurso pTipo_recurso){
        try {
            String vSQL = "INSERT INTO tipo_recurso(id, nome) "
                                      +"VALUES(?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pTipo_recurso.getId()));
            st.setString(2, pTipo_recurso.getNome());         
            //st.setString(3, pUser.getAtivo());
            
            st.execute();
            st.close();
      
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
    
    
 public void alterar (tipoRecurso pTipo_recurso){
    	
    	try{
    		
    		int permissaoAtiva;
    		
    		if (pTipo_recurso.getAtivo() == true) {
    			permissaoAtiva =1;	
    		} else{
    			permissaoAtiva = 0;
    			
    		}
    		
    		String vSQL = "UPDATE tipo_recurso SET `nome`='" + pTipo_recurso.getNome()
    		+ "',`ativo`='" + permissaoAtiva + "' WHERE `id` ='" + pTipo_recurso.getId() + "'";
    		System.out.println(vSQL);
    		System.out.println(pTipo_recurso.getAtivo());
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
    
    
    
 public void excluir(tipoRecurso pTipo_recurso) {
 		try {
			String vSQL = "DELETE FROM tipo_recurso WHERE `id`='" + pTipo_recurso.getId() +"'";
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
						
			st.execute();
	        st.close();
	        
	        vAlerta.mensagemAlerta("Excluido com Sucesso!");
	        ConexaoDataBase.FecharConexao();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função DELETAR! \n"+"Erro: "+e.getMessage());
		}
 	
 }
 
 
 public List<tipoRecurso> filtrar(Integer id,String nome) {
 	try {
 		
			String vSQL = "select * from tipo_recurso";

			if(id != null && nome.equals("")){
				vSQL = vSQL + " where id =" +id+ " ";
			}else if((!nome.equals("")) && id == null ) {
				vSQL = vSQL + " where nome = '"+nome+"'";
			}else if(!(nome.equals("") && id == null)) {
				vSQL= vSQL + " where id =" +id+ "  and nome = '" +nome+ "'";
			}

			List<tipoRecurso> vListaTipo_recurso = new ArrayList<tipoRecurso>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
			    tipoRecurso vtipoRecurso = new tipoRecurso();
			    vtipoRecurso.setId(rs.getInt("id"));
			    vtipoRecurso.setNome(rs.getString("nome"));   
			    vtipoRecurso.setAtivo(rs.getBoolean("ativo"));
			    vListaTipo_recurso.add(vtipoRecurso);
			}
			rs.close();
			st.close();
			
			return vListaTipo_recurso;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função FILTRO! \n"+"Erro: "+e.getMessage());
			return null;
		} 
 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
