/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MENU;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import view.USUARIO.view_usuario;

/**
 *
 * @author WigorPaulo
 */
public class controller_menu implements Initializable{

    @FXML
    private MenuItem menuUsuario;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuUsuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    view_usuario vUser = new view_usuario();
                    vUser.start();
                } catch (Exception e) {
                    System.out.println("Não foi possuivel Abrir a Janela!");
                }
            }
        });
    }
    
}
