package model;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class alertaConfirmacao {
	Optional<ButtonType> result;
	
	
	public alertaConfirmacao(String pMensagem){
		initComponent(pMensagem);
	}
	
	public void initComponent(String pMensagem){
		
	      Alert vAlerta = new Alert(Alert.AlertType.CONFIRMATION);
	            vAlerta.setTitle("Informativo!");
	            vAlerta.setHeaderText(null);
	            vAlerta.setContentText(pMensagem);
	         //   vAlerta.showAndWait();
	            result = vAlerta.showAndWait();
	            
	    }

	public Optional<ButtonType> getResult() {
		return result;
	}

	public void setResult(Optional<ButtonType> result) {
		this.result = result;
	}
	
	
}
