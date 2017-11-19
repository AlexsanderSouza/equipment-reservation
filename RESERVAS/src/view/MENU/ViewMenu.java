package view.MENU;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Alerta;
import view.StartView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIGOR
 */
public class ViewMenu {
    Alerta vAlerta = new Alerta();
    
    public  void start() {
        try {
                  
            Parent janela = FXMLLoader.load(getClass().getResource("MENU.fxml"));
        
            StartView.setScene(janela);
        } catch (IOException e) {
           vAlerta.mensagemAlerta("Erro na Função START classe view_menu!\n"+"Erro: "+e.getMessage());
        }               
    }
}
