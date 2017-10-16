/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.USUARIO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import controller.Controller;
import model.entity.usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;

/**
 *
 * @author alexsandersouza
 */
public class controllerUser implements Initializable {
        
    @FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
     
    @FXML
    private TextField txtNome,txtEmail,txtSenha,txtMatricula,txtTelefone,textFieldId;
   
    @FXML
    private Tab ctrlPag1, ctrlPag2;
    
    @FXML
    private TabPane tabPanelPai;
    
    @FXML
    private RadioButton chkAtivo;
    
    Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
   
    public void inserirUsuario(){
        
        usuario vUser = new usuario();
        
        vUser.setNome(txtNome.getText());
        vUser.setEmail(txtEmail.getText());
        vUser.setSenha(txtSenha.getText());
        vUser.setMatricula(txtMatricula.getText());
        vUser.setTelefone(txtTelefone.getText());
        vUser.setAtivo(chkAtivo.getText());
        
        vCtrl.InserirUsuario(vUser);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
    	    	
        btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {              
                try {                	
                  inserirUsuario();            
                } catch (Exception e) {
                    vAlerta.mensagemAlerta("Não foi possivel Salvar! \n "+ "Erro: "+e.getMessage());
                }
            }
        	});
    }    
    
}
