package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.restricaoRecurso;

public class dao_restricaoRecurso {
alerta vAlerta = new alerta();
    
    public List<restricaoRecurso> listar() throws Exception{
        
			String vSQL = "select rt.*, tp.nome nomeTipoRecurso, f.nome nomeFuncao\r\n" + 
					"  from restricao_recurso rt\r\n" + 
					"left join tipo_recurso tp on tp.id = rt.id_tipo_recurso \r\n" + 
					"left join funcao f on f.id = rt.id_funcao ";
		
    	
	        List<restricaoRecurso> vListaRestricao_recurso = new ArrayList<restricaoRecurso>();
	        
	        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
	        st.executeQuery(vSQL);
	        
	        ResultSet rs = st.getResultSet();
	        
	        while(rs.next()){
	        	restricaoRecurso vRestricao_recurso = new restricaoRecurso();
	        
	        	vRestricao_recurso.setId(rs.getInt("id"));
	            vRestricao_recurso.setId_funcao(rs.getInt("id_funcao"));
	            vRestricao_recurso.setId_tipo_recurso(rs.getInt("id_tipo_recurso"));
	            vRestricao_recurso.setNomeTipoRecurso(rs.getString("nomeTipoRecurso"));
	            vRestricao_recurso.setNomeFuncao(rs.getString("nomeFuncao"));
	            
	            vListaRestricao_recurso.add(vRestricao_recurso);
	        }
	        rs.close();
	        st.close();
	        
	        return vListaRestricao_recurso;
        
    }
    
    public void excluir(restricaoRecurso pRestricaoRecurso) {
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
    
    public void inserir(restricaoRecurso pRestricaoRecurso){
        try {
            String vSQL = "INSERT INTO restricao_recurso(id, id_funcao, id_tipo_recurso) "
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
