package view.MENU;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.alertaInformacao;
import view.startView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIGOR
 */
public class view_menu {
    alertaInformacao vAlerta = new alertaInformacao();
    
    public  void start() {
        try {
                  
            Parent janela = FXMLLoader.load(getClass().getResource("MENU.fxml"));
        
            startView.setScene(janela);
        } catch (IOException e) {
           vAlerta.mensagemAlerta("Erro na Função START classe view_menu!\n"+"Erro: "+e.getMessage());
        }               
    }
}
