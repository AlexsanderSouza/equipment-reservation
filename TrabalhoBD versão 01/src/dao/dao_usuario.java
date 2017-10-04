/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.controller;
import entidy.usuario;

/**
 *
 * @author WigorPaulo
 */
public class dao_usuario {
    controller vCtrl = new controller();
    
    public void inserir(usuario pUser){
        try {
            String vSQL = "INSERT INTO usuario(id, nome, matricula, senha, email, telefone, ativo)"
                                      +"VALUES(?, ?, ?, ?, ?, ?, ?);";
            
            java.sql.PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pUser.getId()));
            st.setString(2, pUser.getNome());
            st.setString(3, pUser.getMatricula());
            st.setString(4, pUser.getSenha());
            st.setString(5, pUser.getEmail());
            st.setString(6, pUser.getTelefone());
            st.setString(7, pUser.getAtivo());
            
            st.execute();
            st.close();
            
            vCtrl.mensagem("Inserido com Sucesso!");
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
           vCtrl.mensagem("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
    
}
