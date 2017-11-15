/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LOGIN;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.alerta;
import model.ENTITY.usuario;
import model.ENTITY.usuarioLogado;
import view.MENU.view_menu;
/**
 *
 * @author WIGOR
 */
public class controller_login implements Initializable{
    @FXML
    private TextField txtUsuario,txtSenha;
    @FXML
    private Button btnAlterarSenha,btnEntrar;
    
    Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
    public Boolean validaUsuario() {
    	try {
    		Boolean vResult = true;
    		
    		String vLogin = txtUsuario.getText().trim();
        	String vSenha = txtSenha.getText().trim();
        	
        	usuarioLogado vUsuarioLogado = new usuarioLogado();
       
        	if (vLogin.equals("0") && vSenha.equals("0")) {        		
        		vResult = true;        		
        	} else {
        	
	        	String vExisteUser = "";
	        	
	        	usuario vUser = new usuario();
	        	
	        	vUser.setMatricula(vLogin);
	        	vUser.setSenha(vSenha);
	        	
	        	for (usuario user : vCtrl.ValidarLogin(vUser)) {
	        		vExisteUser = vExisteUser + user.getNome();
	        		vUsuarioLogado.setUsuario_id(user.getId());
	            	      
	        	}
	        	vCtrl.alterarUsuarioLogado(vUsuarioLogado);
	        	if (vExisteUser != "") {
	        		vResult = true;
	        	} else {
	        		vResult = false;
	        	}
        	}
        	
        	return vResult;
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro ao Validar Usuário!");
			return false;
		}
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    	txtSenha.setText("0"); //remover depois 
    	txtUsuario.setText("0"); //remover depois
    	
        btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             
              try {
            	  if (validaUsuario())   {               		  
            		  view_menu vMenu = new view_menu();
                	  vMenu.start(); 
            	  } else {
            		  vAlerta.mensagemAlerta("Usuário Invalido!");
            	  }
            		             	             	                 
              } catch (Exception e) {
            	  vAlerta.mensagemAlerta("Erro ao Iniciar o Sistema! \n"+"Erro: "+e.getMessage());  
              }              
		  }
		  });
        
    }
    
}
