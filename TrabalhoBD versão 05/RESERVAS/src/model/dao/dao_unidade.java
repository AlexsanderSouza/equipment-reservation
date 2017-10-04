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
import model.entity.unidade;

/**
 *
 * @author WigorPaulo
 */
public class dao_unidade {
	alerta vAlerta = new alerta();
	
    public List<unidade> listar(unidade pUnidade) throws Exception{
        
        List<unidade> vListaUnidade = new ArrayList<unidade>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from unidade");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            unidade vUnidade = new unidade();
            vUnidade.setId(rs.getInt("id"));
            vUnidade.setNome(rs.getString("nome"));
            vUnidade.setEmail(rs.getString("email"));
            vUnidade.setTelefone(rs.getString("telefone"));            
            vUnidade.setEndereco(rs.getString("endereco")); 
            vListaUnidade.add(vUnidade);
            
        }
        rs.close();
        st.close();
        
        return vListaUnidade;       
    }

	
	public void inserir(unidade pUnidade){
        try {
            String vSQL = "INSERT INTO unidade(id, nome, email, telefone, endereco) "
                                      +"VALUES(?, ?, ?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pUnidade.getId()));
            st.setString(2, pUnidade.getNome());
            st.setString(3, pUnidade.getEmail());
            st.setString(4, pUnidade.getTelefone());
            st.setString(5, pUnidade.getEndereco());
                        
            st.execute();
            st.close();
           System.out.println("inserido"); 
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
    
}
