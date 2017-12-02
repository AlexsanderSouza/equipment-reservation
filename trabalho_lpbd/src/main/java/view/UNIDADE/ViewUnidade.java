package view.UNIDADE;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewUnidade {
	
	 public void start() throws Exception {
	     	
			
	    	Parent root = FXMLLoader.load(getClass().getResource("UNIDADE.fxml"));
	        
	        Stage stage = new Stage();
	        Scene scene = new Scene(root);
	        
	        stage.setScene(scene);
	        stage.show();
	      
	    }
	    

}
