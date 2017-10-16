package view.RECURSO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import model.entity.recurso;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class controllerRecurso implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
	private TextField txtNome,txtEtiqueta;
	
	@FXML
	private ComboBox<?> cbxTipo,cbxUnidade;
	
	@FXML
    private RadioButton chkAtivo;
	
	@FXML
	private TextArea txtObs;
	
	@FXML
	private ListView<?> txtRestricao;

	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
	
    
    
    public void inserirRecurso() {
    	
    	recurso vRecurso = new recurso();
    	
    	// Seleciona o id do tipo para inserir no banco
    	String[] vIdTipoString = cbxTipo.getSelectionModel().getSelectedItem().toString().split("-");
    	
    	int vIdtipo = Integer.parseInt(vIdTipoString[0]);
    	
    	String vAtivo = "";
    	if (chkAtivo.isSelected() == true) {
    		vAtivo = "S";
    	} else {
    		vAtivo = "N";
    	}
    	
    	vRecurso.setNome(txtNome.getText());
    	vRecurso.setEtiqueta(txtEtiqueta.getText());
    	vRecurso.setObservacao(txtObs.getText());
    	vRecurso.setAtivo(vAtivo);
    	vRecurso.setId_tipo_recurso(vIdtipo);
    	
    	vCtrl.InserirRecurso(vRecurso);
    	// recurso.setId_tipo_recurso();
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
				inserirRecurso();
				
			}
		});
		
	}

}
