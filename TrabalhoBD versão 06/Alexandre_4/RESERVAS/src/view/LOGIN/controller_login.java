/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LOGIN;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             
              try {
            	  view_menu vMenu = new view_menu();
            	  vMenu.start();                
              } catch (Exception e) {
                  System.out.println("Não foi possuivel Abrir a Janela!");
              }              
		  }
		  });
        
    }
    
}
