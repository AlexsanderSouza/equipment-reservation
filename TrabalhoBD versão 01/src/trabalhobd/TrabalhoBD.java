/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhobd;

import javafx.application.Application;
import javafx.stage.Stage;
import model.alerta;
import view.startView;

/**
 *
 * @author WigorPaulo
 */
public class TrabalhoBD extends Application {
    
    startView vStart = new startView();
    alerta vAlerta = new alerta();
    
    @Override
    public void start(Stage stage) throws Exception {
        vStart.start(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
