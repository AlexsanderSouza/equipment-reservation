/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.USUARIO;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author alexsandersouza
 */
public class ViewUsuario {
    
   
    public void start() throws Exception {
     	
	
    	Parent root = FXMLLoader.load(getClass().getResource("USUARIO.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
      
    }
    
}
