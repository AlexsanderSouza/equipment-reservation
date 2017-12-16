package view.PERMISSAO;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewPermissao {

	public void start() throws Exception {
     	
		
    	Parent root = FXMLLoader.load(getClass().getResource("PERMISSAO.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
      
    }
	
	
}
