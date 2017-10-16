package view.RECURSO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import model.ENTITY.recurso;
import model.ENTITY.reserva;
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
	private ComboBox<String> cbxTipo,bxUnidade;
	
	@FXML
    private RadioButton chkAtivo;
	
	@FXML TableView tbGrid;
	
	@FXML
	private TextArea txtObs;
	
	@FXML
	private ListView<?> txtRestricao;
	
	private TableColumn<recurso, Integer> tbColum1 = new TableColumn<recurso, Integer>(); 		
	private TableColumn<recurso, String> tbColum2 = new TableColumn<recurso, String>(); 
	private TableColumn<recurso, String> tbColum3 = new TableColumn<recurso, String>(); 
	private TableColumn<recurso, Integer> tbColum4  = new TableColumn<recurso, Integer>(); 
	private TableColumn<recurso, String> tbColum5  = new TableColumn<recurso, String>(); 
	

	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
    @SuppressWarnings("unchecked")
    public void inserirTabela() {
    	try {
			tbColum1.setText("ID");/*SETA O TITULO DA COLUNA*/
			tbColum2.setText("ETIQUETA");
			tbColum3.setText("TIPO");
			tbColum4.setText("UNIDADE");
			tbColum5.setText("Obs");
			
			tbColum1.setCellValueFactory(new PropertyValueFactory<recurso, Integer>("id"));
			tbColum2.setCellValueFactory(new PropertyValueFactory<recurso, String>("etiqueta"));
			tbColum3.setCellValueFactory(new PropertyValueFactory<recurso, String>("nome")); //Nome do tipo de recurso
			tbColum4.setCellValueFactory(new PropertyValueFactory<recurso, Integer>("id_unidade"));
			tbColum5.setCellValueFactory(new PropertyValueFactory<recurso, String>("Observacao"));
			
			tbGrid.getColumns().addAll(tbColum1,tbColum2,tbColum3,tbColum4,tbColum5);
			
			ObservableList<recurso> vRecursoLista = FXCollections.observableArrayList(vCtrl.ListaRecurso());
			
			tbGrid.setItems(vRecursoLista);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: "+e.getMessage());
		}
    }
	
    
    
    
    
    public void alimentaComboBoxTipo() {
    	
    	for(tipoRecurso aux: vCtrl.ListaTipoRecurso())
    	cbxTipo.getItems().add(aux.getId() +"-"+ aux.getNome());
    }
    
    
    
    
    public void inserirRecurso() {
    	
    	recurso vRecurso = new recurso();
    	String[] nomeTipo = new String[3];
    	
    	// Seleciona o id do tipo para inserir no banco
    	String[] vIdTipoString = cbxTipo.getSelectionModel().getSelectedItem().toString().split("-");
    	
    	int vIdtipo = Integer.parseInt(vIdTipoString[0]);
    	
    	String vAtivo = "";
    	if (chkAtivo.isSelected() == true) {
    		vAtivo = "S";
    	} else {
    		vAtivo = "N";
    	}
    	
    	nomeTipo = cbxTipo.getValue().split("-");
    	
    	
    	vRecurso.setEtiqueta(txtEtiqueta.getText());
    	vRecurso.setObservacao(txtObs.getText());
    	vRecurso.setAtivo(vAtivo);
    	vRecurso.setId_tipo_recurso(vIdtipo);
    	vRecurso.setNome(nomeTipo[1]);
    	System.out.println(nomeTipo[1]);
    	
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
		
		inserirTabela();
		alimentaComboBoxTipo();
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				inserirRecurso();
				
			}
		});
		
	}

}
