package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Alerta;
import model.ENTITY.RestricaoRecurso;

public class DaoRestricaoRecurso {
Alerta vAlerta = new Alerta();
    
    public List<RestricaoRecurso> listar() throws Exception{
        
			String vSQL = "select rt.*, tp.nome nomeTipoRecurso, f.nome nomeFuncao\r\n" + 
					"  from restricao_recurso rt\r\n" + 
					"left join tipo_recurso tp on tp.id = rt.id_tipo_recurso2 \r\n" + 
					"left join funcao f on f.id = rt.id_funcao2 ";
		
    	
	        List<RestricaoRecurso> vListaRestricao_recurso = new ArrayList<RestricaoRecurso>();
	        
	        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
	        st.executeQuery(vSQL);
	        
	        ResultSet rs = st.getResultSet();
	        
	        while(rs.next()){
	        	RestricaoRecurso vRestricao_recurso = new RestricaoRecurso();
	        
	        	vRestricao_recurso.setId(rs.getInt("id"));
	            vRestricao_recurso.setId_funcao(rs.getInt("id_funcao2"));
	            vRestricao_recurso.setId_tipo_recurso(rs.getInt("id_tipo_recurso2"));
	            vRestricao_recurso.setNomeTipoRecurso(rs.getString("nomeTipoRecurso"));
	            vRestricao_recurso.setNomeFuncao(rs.getString("nomeFuncao"));
	            
	            vListaRestricao_recurso.add(vRestricao_recurso);
	        }
	        rs.close();
	        st.close();
	        
	        return vListaRestricao_recurso;
        
    }
    
    public void excluir(RestricaoRecurso pRestricaoRecurso) {
    	try {
    		String vSQL = "DELETE FROM restricao_recurso WHERE `id` = '"+pRestricaoRecurso.getId()+"'";
    		
    		PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL); 
    		
    		st.execute();
	        st.close();

	        ConexaoDataBase.FecharConexao();
    		
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função DELETAR! \n"+"Erro: "+e.getMessage());
		}
    }
    
    public void inserir(RestricaoRecurso pRestricaoRecurso){
        try {
            String vSQL = "INSERT INTO restricao_recurso(id, id_funcao2, id_tipo_recurso2) "
                                      +"VALUES(?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pRestricaoRecurso.getId()));
            st.setString(2, Integer.toString(pRestricaoRecurso.getId_funcao()));
            st.setString(3, Integer.toString(pRestricaoRecurso.getId_tipo_recurso()));           
                       
            st.execute();
            st.close();

           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
    
}
