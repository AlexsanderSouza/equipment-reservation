/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.entity.funcao;

/**
 *
 * @author WigorPaulo
 */
public class dao_funcao {
    alerta vAlerta = new alerta();
    
    public List<funcao> listar(funcao pFuncao) throws Exception{
        
        List<funcao> vListaFuncao = new ArrayList<funcao>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from funcao");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            funcao vFuncao = new funcao();
            vFuncao.setId(rs.getInt("id"));
            vFuncao.setNome(rs.getString("nome"));
            vFuncao.setDescricao(rs.getString("descricao"));            
            vListaFuncao.add(vFuncao);
        }
        rs.close();
        st.close();
        
        return vListaFuncao;       
    }

    
    public void inserir(funcao pFuncao){
        try {
            String vSQL = "INSERT INTO funcao(id, nome, descricao) "
                                      +"VALUES(?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pFuncao.getId()));
            st.setString(2, pFuncao.getNome());
            st.setString(3, pFuncao.getDescricao());
                        
            st.execute();
            st.close();
           
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
}
