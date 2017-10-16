package view.RESTRICAO_RECURSO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class controllerRestricaoRecurso implements Initializable{

	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnGravar,btnImprimir,btnSair, btnAdicionar;
	
	@FXML
	private ComboBox<?> cbxTipoRecurso,cbxFuncao;
    
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
