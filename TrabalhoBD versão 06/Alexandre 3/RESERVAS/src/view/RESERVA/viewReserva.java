package view.RESERVA;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class viewReserva {

	public void start() throws Exception {
     	
		
    	Parent root = FXMLLoader.load(getClass().getResource("RESERVA.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
      
    }
	
}
