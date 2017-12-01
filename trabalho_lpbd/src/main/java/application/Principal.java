/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Alerta;
import view.StartView;

/**
 *
 * @author WIGOR
 */
public class Principal extends Application{
    
    StartView vStart = new StartView();
    Alerta vAlerta = new Alerta();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
            vStart.start(primaryStage);    
    }
     public static void main(String[] args) throws SQLException {
        launch(args);
        
    }
    
}
