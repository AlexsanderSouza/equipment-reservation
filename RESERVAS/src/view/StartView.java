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
import model.Alerta;
import view.LOGIN.Viewlogin;

/**
 *
 * @author WIGOR
 */
public class StartView extends Application{
    
    private static Stage stage;
    Alerta vAlerta = new Alerta();
    @Override
    public void start(Stage primaryStage) throws IOException {    
            stage = primaryStage;
            stage.setTitle("Sistema de Alocação");
            
            primaryStage.show();
            Viewlogin vLoginView = new Viewlogin();
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
