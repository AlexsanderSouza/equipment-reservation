package view.RESTRICAO_RECURSO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import model.alertaInformacao;
import model.ENTITY.funcao;
import model.ENTITY.restricaoRecurso;
import model.ENTITY.tipoRecurso;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class controllerRestricaoRecurso implements Initializable{

	@FXML
    private Button btnAlterar,btnExcluir,btnSair, btnAdicionar;
	
	@FXML
	private ComboBox<String> cbxTipoRecurso,cbxFuncao;
    
	@FXML 
	TableView<restricaoRecurso> tbGrid;
	
	Controller vCtrl = new Controller();
    alertaInformacao vAlerta = new alertaInformacao();
    
    public void inserirRestricaoRecurso() {
    	restricaoRecurso vRestricaoRecurso = new restricaoRecurso();
    	
    	
    	
    }
    
    public void alimentaTabela() {
    	//ObservableList<restricaoRecurso> vRestricaoLista = FXCollections.observableArrayList(vCtrl.ListaRecurso());
		
		//tbGrid.setItems(vRestricaoLista);
    }
    
    public void fecharJanela() {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
    	stage.close();
    }
    
    public void alimentaComboBox() {
    	for(tipoRecurso aux: vCtrl.ListaTipoRecurso())
    		cbxTipoRecurso.getItems().add(aux.getId() +"-"+ aux.getNome());
    	
    	List<funcao> funcao = vCtrl.ListaFuncao();
		for (funcao aux : funcao) {
			cbxFuncao.getItems().add(aux.getId() + " - " + aux.getNome());
		}
    }
    
    public void onShow() {
    	alimentaComboBox();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.onShow();
		
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				fecharJanela();
			}
		});
		
	}

}
