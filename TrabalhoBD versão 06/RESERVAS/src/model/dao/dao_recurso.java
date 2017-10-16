package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.entity.recurso;

public class dao_recurso {
alerta vAlerta = new alerta();
    
    public List<recurso> listar() throws Exception{
        
        List<recurso> vListaRecurso = new ArrayList<recurso>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from recurso");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            recurso vRecurso = new recurso();
            
            vRecurso.setId(rs.getInt("id"));
            vRecurso.setNome(rs.getString("nome"));
            vRecurso.setEtiqueta(rs.getString("etiqueta"));
            vRecurso.setObservacao(rs.getString("observacao"));
            vRecurso.setAtivo(rs.getString("ativo"));
            vRecurso.setId_unidade(rs.getInt("id_unidade"));
            vRecurso.setId_tipo_recurso(rs.getInt("id_tipo_recurso"));
            vListaRecurso.add(vRecurso);
        }
        rs.close();
        st.close();
        
        return vListaRecurso;
        
    }

    
    public void inserir(recurso pRecurso){
        try {
            String vSQL = "INSERT INTO recurso(id, nome, etiqueta, observacao, id_unidade, id_tipo_recurso) "
                                      +"VALUES(?, ?, ?, ?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pRecurso.getId()));
            st.setString(2, pRecurso.getNome());
            st.setString(3, pRecurso.getEtiqueta());
            st.setString(4, pRecurso.getObservacao());
            st.setString(5, Integer.toString(pRecurso.getId_unidade()));
            st.setString(6, Integer.toString(pRecurso.getId_tipo_recurso()));
            //st.setString(7, pUser.getAtivo());
            
            st.execute();
            st.close();
      
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
}
