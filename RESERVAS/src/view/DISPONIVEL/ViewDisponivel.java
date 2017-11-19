package view.DISPONIVEL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewDisponivel {

public void start() throws Exception {
     	
		
    	Parent root = FXMLLoader.load(getClass().getResource("DISPONIVEL.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
      
    }
}
