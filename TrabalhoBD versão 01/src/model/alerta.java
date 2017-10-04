/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.control.Alert;

/**
 *
 * @author WigorPaulo
 */
public class alerta {
    
    public void mensagemAlerta(String pMensagem){
      Alert vAlerta = new Alert(Alert.AlertType.INFORMATION);
            vAlerta.setTitle("Informativo!");
            vAlerta.setHeaderText(null);
            vAlerta.setContentText(pMensagem);
            vAlerta.showAndWait();
    }
    
    
}
