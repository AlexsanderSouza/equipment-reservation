/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MENU;

import java.net.URL;
import java.util.ResourceBundle;

import model.Alerta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import view.BAIXA_RESERVA.ViewBaixaReserva;
import view.DISPONIVEL.ViewDisponivel;
import view.FUNCAO.ViewFuncao;
import view.INSTITUICAO.ViewInstituicao;
import view.PERMISSAO.ViewPermissao;
import view.RECURSO.ViewRecurso;
import view.RESTRICAO_RECURSO.ViewRestricaoRecurso;
import view.TIPO_RECURSO.ViewTipoRecurso;
import view.UNIDADE.ViewUnidade;
import view.USUARIO.ViewUsuario;



/**
 *
 * @author WIGOR
 */
public class ControllerMenu implements Initializable{
    
    @FXML
    private MenuItem menuUsuario, menuUnidade, menuInstituicao, menuFuncao, menuDisponivel,menuBaixaReserva;
    
    @FXML
    private MenuItem menuTipoRecurso, menuRestricaoRecurso, menuRecurso,menuPermissao;

    Alerta vAlerta = new Alerta();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	
    	menuBaixaReserva.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					ViewBaixaReserva vBaixaReserva = new ViewBaixaReserva();
					vBaixaReserva.start();
				} catch (Exception e) {
					// TODO: handle exception
					vAlerta.mensagemAlerta("Erro ao Abrir Tela de Permiss�o! \n"+"Erro: "+e.getMessage());
				}
			}
		});
    	
    	menuPermissao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	ViewPermissao vPermissao = new ViewPermissao();
                	vPermissao.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Permiss�o! \n"+"Erro: "+e.getMessage());                    
                }
			}
		});
    	
    	menuDisponivel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	ViewDisponivel vDisponivel = new ViewDisponivel();
                	vDisponivel.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela Disp�nivel! \n"+"Erro: "+e.getMessage());                    
                }
			}
		});
    	
    	
    	menuRecurso.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	ViewRecurso vRecurso = new ViewRecurso();
                	vRecurso.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Recurso! \n"+"Erro: "+e.getMessage());                    
                }
			}
		});    	
    	    	    	
    	menuRestricaoRecurso.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	ViewRestricaoRecurso vRestricaoRecurso = new ViewRestricaoRecurso();
                	vRestricaoRecurso.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Restri��o de Recurso! \n"+"Erro: "+e.getMessage());                    
                }
			}
		});
    	
    	
    	menuTipoRecurso.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	ViewTipoRecurso vTipoRecurso = new ViewTipoRecurso();
                	vTipoRecurso.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Tipo de Recurso! \n"+"Erro: "+e.getMessage());                    
                }
			}
		});
    	
    	menuFuncao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	ViewFuncao vFuncao = new ViewFuncao();
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
                	ViewInstituicao vInstituicao = new ViewInstituicao();
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
                	ViewUnidade vUnidade = new ViewUnidade();
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
                	ViewUsuario vUser = new ViewUsuario();
                    vUser.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Usuario! \n"+"Erro: "+e.getMessage());                    
                }
            }
        });
        
    }

    
    
}
