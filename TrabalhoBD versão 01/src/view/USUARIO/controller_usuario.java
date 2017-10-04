/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.USUARIO;

import controller.controller;
import entidy.usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author WigorPaulo
 */
public class controller_usuario implements Initializable{

    @FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
    
    @FXML
    private TextField txtNome,txtEmail,txtSenha,txtMatricula,txtTelefone;
  
    controller vCtrl = new controller();
    
     public void inserirUsuario(){
        try {
            usuario vUser = new usuario();
            
            vUser.setNome(txtNome.getText());
            vUser.setEmail(txtEmail.getText());
            vUser.setSenha(txtSenha.getText());
            vUser.setMatricula(txtMatricula.getText());
            vUser.setTelefone(txtTelefone.getText());
           // vUser.setAtivo(chkAtivo.getText());
            
            vCtrl.InserirUsuario(vUser);
        } catch (Exception e) {
            vCtrl.mensagem("Erro na Função Inserir Usuario!\n"+"Erro: "+e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vCtrl.mensagem("Estou aqui!");
            }
        });
              
    }
    
    
    
    
    
}
