/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MENU;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import view.startView;

/**
 *
 * @author WigorPaulo
 */
public class view_menu {
      
    public  void start() throws IOException{
        Parent janela = FXMLLoader.load(getClass().getResource("MENU.fxml"));
        
            startView.setScene(janela);              
    }
    
}
