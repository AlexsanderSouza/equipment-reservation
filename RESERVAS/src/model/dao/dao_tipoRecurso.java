package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.entity.tipoRecurso;

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
            String vSQL = "INSERT INTO tipo_recurso(id, nome, ativo) "
                                      +"VALUES(?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pTipo_recurso.getId()));
            st.setString(2, pTipo_recurso.getNome());         
            st.setBoolean(3, pTipo_recurso.getAtivo());
            
            st.execute();
            st.close();
      
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
}
