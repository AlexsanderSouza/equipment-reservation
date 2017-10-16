package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.restricaoRecurso;

public class dao_restricaoRecurso {
alerta vAlerta = new alerta();
    
    public List<restricaoRecurso> listar(restricaoRecurso pRestricao_recurso) throws Exception{
        
        List<restricaoRecurso> vListaRestricao_recurso = new ArrayList<restricaoRecurso>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from restricao_recurso");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
        	restricaoRecurso vRestricao_recurso = new restricaoRecurso();
            vRestricao_recurso.setId(rs.getInt("id"));
            vRestricao_recurso.setId_funcao(rs.getInt("id_funcao"));
            vRestricao_recurso.setId_tipo_recurso(rs.getInt("id_tipo_recurso"));
            vListaRestricao_recurso.add(vRestricao_recurso);
        }
        rs.close();
        st.close();
        
        return vListaRestricao_recurso;       
    }

    
    public void inserir(restricaoRecurso pRestricao_recurso){
        try {
            String vSQL = "INSERT INTO restricao_recurso(id, id_funcao, id_tipo_recurso) "
                                      +"VALUES(?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pRestricao_recurso.getId()));
            st.setString(2, Integer.toString(pRestricao_recurso.getId_funcao()));
            st.setString(3, Integer.toString(pRestricao_recurso.getId_tipo_recurso()));           
            //st.setString(4, pUser.getAtivo());
            
            st.execute();
            st.close();

           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
}
