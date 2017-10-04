package view.RESERVA;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;

public class controllerReserva implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
	private ComboBox<String> cbxResponsavel,cbxdestinatario,cbxTipoRepeticao,cbxStatus;
	
	@FXML
	private DatePicker edtDataHoraInicio,edtDataHoraFinal;
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
    public void alimentaComboBox(){
    	/*try {
    		String vListaUsuario = "";
    		
    		
    		cbxResponsavel.setTooltip(new Tooltip());
    		cbxResponsavel.getItems().addAll();    
			
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox!");
		} */
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
