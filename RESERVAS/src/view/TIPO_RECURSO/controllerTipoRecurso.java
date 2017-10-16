package view.TIPO_RECURSO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import model.ENTITY.tipoRecurso;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class controllerTipoRecurso implements Initializable{

	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private RadioButton chkAtivo;	
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
	
    public void inserirTipoRecurso(){
    	tipoRecurso vRecurso = new tipoRecurso();
    	
    	String vAtivo = "";
    	if (chkAtivo.isSelected() == true) {
    		vAtivo = "S";
    	} else {
    		vAtivo = "N";
    	}
    	    	
    	vRecurso.setNome(txtNome.getText());
    	vRecurso.setAtivo(vAtivo);
    	
    	vCtrl.InserirTipoRecurso(vRecurso);
    }
    
    public void ControlaBotao(String pBotao){
    	switch (pBotao) {
		case "novo":
			btnVoltar.setDisable(true);	
			btnNovo.setDisable(false);
			btnAlterar.setDisable(false);
			btnExcluir.setDisable(false);
			btnImprimir.setDisable(true);
			btnSalvar.setDisable(true);
			btnSair.setDisable(false);
			break;
		case "voltar":
			btnVoltar.setDisable(false);	
			btnNovo.setDisable(true);
			btnAlterar.setDisable(true);
			btnExcluir.setDisable(true);
			btnImprimir.setDisable(true);
			btnSalvar.setDisable(false);
			btnSair.setDisable(false);			
			break;
	
		default:
			break;
		}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				inserirTipoRecurso();
			}
		});
		
	}

}
