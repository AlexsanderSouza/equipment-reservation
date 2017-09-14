/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.alerta;
import view.LOGIN.view_login;

/**
 *
 * @author WigorPaulo
 */
public class startView extends Application{
    
    private static Stage stage;
    alerta vAlerta = new alerta();
    @Override
    public void start(Stage primaryStage) throws IOException {
      
            stage = primaryStage;
            stage.setTitle("Sistema de Alocação");
            
            primaryStage.show();
            view_login vLoginView = new view_login();
            vLoginView.start();
           
    }
    
    public static void setScene(Parent p){
        Scene scene = new Scene(p);
        stage.setScene(scene);
    }
    
    public static void sair(){
        stage.close();
    }
    
}
