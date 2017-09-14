/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.USUARIO;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author WigorPaulo
 */
public class view_usuario {
    
    public  void start() throws IOException{
        Parent janela = FXMLLoader.load(getClass().getResource("USUARIO.fxml"));
          
            Stage stage = new Stage();
            stage.setTitle("SisTema de Passagem");
            Scene scene = new Scene(janela); 
            stage.setScene(scene);

            stage.show();            
    }
    
}
