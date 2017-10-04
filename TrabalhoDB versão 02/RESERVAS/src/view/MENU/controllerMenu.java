/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MENU;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import view.FUNCAO.viewFuncao;
import view.INSTITUICAO.viewInstituicao;
import view.UNIDADE.viewUnidade;
import view.USUARIO.viewUsuario;



/**
 *
 * @author WIGOR
 */
public class controllerMenu implements Initializable{
    
    @FXML
    private MenuItem menuUsuario, menuUnidade, menuInstituicao, menuFuncao;

    alerta vAlerta = new alerta();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	
    	menuFuncao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	viewFuncao vFuncao = new viewFuncao();
                	vFuncao.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Funcao! \n"+"Erro: "+e.getMessage());                    
                }
				
			}
		});
    	
    	menuInstituicao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	viewInstituicao vInstituicao = new viewInstituicao();
                	vInstituicao.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Instituicao! \n"+"Erro: "+e.getMessage());                    
                }
				
			}
		});
        
    	menuUnidade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	viewUnidade vUnidade = new viewUnidade();
                	vUnidade.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Unidade! \n"+"Erro: "+e.getMessage());                    
                }
			}
		});
    	
        menuUsuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                                
                try {
                	viewUsuario vUser = new viewUsuario();
                    vUser.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Usuario! \n"+"Erro: "+e.getMessage());                    
                }
            }
        });
        
    }

    
    
}
