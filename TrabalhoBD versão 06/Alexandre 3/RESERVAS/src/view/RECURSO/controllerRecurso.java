package view.RECURSO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import model.alerta;
import model.entity.recurso;
import model.entity.tipoRecurso;
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
	private ComboBox<String> cbxTipo,cbxUnidade;
	
	@FXML
    private RadioButton chkAtivo;
	
	@FXML
	private TextArea txtObs;
	
	@FXML
	private ListView<?> txtRestricao;

	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
      public void inserirRecurso() {
    	recurso recurso = new recurso();
    	/// Seleciona o id do tipo para inserir no banco
    	String[] idTipoString = cbxTipo.getSelectionModel().getSelectedItem().toString().split("-");
    	int idtipo = Integer.parseInt(idTipoString[0]);
    	
    	recurso.setNome(txtNome.getText());
    	recurso.setEtiqueta(txtEtiqueta.getText());
    	recurso.setObservacao(txtObs.getText());
    	recurso.setAtivo(chkAtivo.isSelected());
    	recurso.setId_tipo_recurso(idtipo);
    	vCtrl.InserirRecurso(recurso);
    	// recurso.setId_tipo_recurso();
    }
    
    public void alimentaComboBoxTipo() {
    	List<tipoRecurso> aux = vCtrl.ListaTipoRecurso();
    	
    	for(tipoRecurso tipo: aux) {  //Implementar LinkHash
    		cbxTipo.getItems().addAll(tipo.getId()+ "-" + tipo.getNome());
    	}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		alimentaComboBoxTipo();
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				inserirRecurso();
				
				
			}
		});
	}

}
