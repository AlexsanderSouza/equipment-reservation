package view.BAIXA_RESERVA;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class viewBaixaReserva {

public void start() throws Exception {
     	
		
    	Parent root = FXMLLoader.load(getClass().getResource("BAIXA_RESERVA.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
      
    }
	
}
