/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entity.usuario;
import model.dao.ConexaoDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.alerta;





/**
 *
 * @author WigorPaulo
 */
public class dao_usuario {
    alerta vAlerta = new alerta();
    
    public List<usuario> listar() throws Exception{
        
        List<usuario> vListaUser = new ArrayList<usuario>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from usuario");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            usuario vUser = new usuario();
            vUser.setId(rs.getInt("id"));
            vUser.setNome(rs.getString("nome"));
            vUser.setMatricula(rs.getString("matricula"));
            vUser.setSenha(rs.getString("senha"));
            vUser.setEmail(rs.getString("email"));
            vUser.setTelefone(rs.getString("telefone"));
            vUser.setAtivo(rs.getBoolean("ativo"));
            vListaUser.add(vUser);
        }
        rs.close();
        st.close();
        
        return vListaUser;       
    }

    
    public void inserir(usuario pUser){
        try {
            String vSQL = "INSERT INTO usuario(id, nome, matricula, senha, email, telefone, ativo) "
                                      +"VALUES(?, ?, ?, ?, ?, ?,?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pUser.getId()));
            st.setString(2, pUser.getNome());
            st.setString(3, pUser.getMatricula());
            st.setString(4, pUser.getSenha());
            st.setString(5, pUser.getEmail());
            st.setString(6, pUser.getTelefone());
            st.setBoolean(7, pUser.getAtivo());
            
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
