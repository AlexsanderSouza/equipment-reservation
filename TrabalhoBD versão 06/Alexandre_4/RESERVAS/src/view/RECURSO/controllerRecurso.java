package view.RECURSO;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import model.alerta;
import model.entity.recurso;
import model.entity.reserva;
import model.entity.tipoRecurso;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class controllerRecurso implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
	private TextField txtNome,txtEtiqueta;
	
	@FXML
	private ComboBox<String> cbxTipo,cbxUnidade,cbxPesqStatus,cbxPesqAutor,cbxPesqDestinatario;;
	
	@FXML
    private RadioButton chkAtivo;
	
	@FXML
	private DatePicker dtInicial, dtInicial2, dtFim, dtFim2;
	
	@FXML
	private TextField txtPesqID;
	
	@FXML
	private TableView tbGrid;
	
	@FXML
	private TextArea txtObs;
	
	@FXML
	private ListView<?> txtRestricao;
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
	
	//Variavel local
		private TableColumn<reserva, Integer> tbColum1 = new TableColumn<reserva, Integer>(); 		
		private TableColumn<reserva, Integer> tbColum2 = new TableColumn<reserva, Integer>(); 
		private TableColumn<reserva, Integer> tbColum3 = new TableColumn<reserva, Integer>(); 
		private TableColumn<reserva, String> tbColum4  = new TableColumn<reserva, String>(); 
		private TableColumn<reserva, String> tbColum5  = new TableColumn<reserva, String>(); 
		private TableColumn<reserva, String> tbColum6  = new TableColumn<reserva, String>(); 
		
		
		 @SuppressWarnings("unchecked")
			public void inserirTabela(){
		    	    	
		    	try {
		    		tbColum1.setText("ID");/*SETA O TITULO DA GRID*/
		        	tbColum2.setText("ETIQUETA");
		        	tbColum3.setText("NOME");
		        	tbColum4.setText("TIPO");
		        	tbColum5.setText("STATUS");
		        	tbColum6.setText("UNIDADE");
		    		
		    	    tbColum1.setCellValueFactory(new PropertyValueFactory<reserva, Integer>("id"));/*SETA QUAL CAMPO DA LISTA*/
		        	tbColum2.setCellValueFactory(new PropertyValueFactory<reserva, Integer>("etiqueta"));//PENSAR COMO MOSTRAR O NOME DO USUARIO
		        	tbColum3.setCellValueFactory(new PropertyValueFactory<reserva, Integer>("nome"));
		        	tbColum4.setCellValueFactory(new PropertyValueFactory<reserva, String>("id_tipo_recurso"));
		        	tbColum5.setCellValueFactory(new PropertyValueFactory<reserva, String>("status"));
		        	tbColum6.setCellValueFactory(new PropertyValueFactory<reserva, String>("id_unidade"));
		        	        	
		        	tbGrid.getColumns().addAll(tbColum1,tbColum2,tbColum3,tbColum4,tbColum5,tbColum6);
		        	
		        	ObservableList<recurso> vLista = FXCollections.observableArrayList(vCtrl.ListaRecurso());
		        	
		        	tbGrid.setItems(vLista);
					
				} catch (Exception e) {
					// TODO: handle exception
					vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: "+e.getMessage());
				}		
				    	
		    }
		 
		 public void Filtro(){}

	
    
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
		inserirTabela();
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
