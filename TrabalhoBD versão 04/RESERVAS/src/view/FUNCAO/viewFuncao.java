package view.FUNCAO;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class viewFuncao {

	public void start() throws Exception {
     	
		
    	Parent root = FXMLLoader.load(getClass().getResource("FUNCAO.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
      
    }
}
