package view.RESTRICAO_RECURSO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import model.alerta;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class controllerRestricaoRecurso implements Initializable{

	@FXML
    private Button btnExcluir,btnSair, btnAdicionar;
	
	@FXML
	private ComboBox<String> cbxTipoRecurso,cbxFuncao;
	
	@FXML
	private TabPane tabPane;
    
	@FXML 
	TableView<restricaoRecurso> tbGrid;
	
	private TableColumn<restricaoRecurso, String> tbColum1 = new TableColumn<restricaoRecurso, String>();
	private TableColumn<restricaoRecurso, String> tbColum2 = new TableColumn<restricaoRecurso, String>();
	
	Controller vCtrl = new Controller();

    alerta vAlerta = new alerta();


    @SuppressWarnings("unchecked")
	public void inserirTabela() {
    	try {
			tbColum1.setText("Tipo de Recurso");
			tbColum2.setText("Função");
			
			tbColum1.setCellValueFactory(new PropertyValueFactory<restricaoRecurso, String>("nomeTipoRecurso"));
			tbColum2.setCellValueFactory(new PropertyValueFactory<restricaoRecurso, String>("nomeFuncao"));
			
			tbGrid.getColumns().addAll(tbColum1,tbColum2);
			
			alimentaTabela();
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: "+e.getMessage());
		}
    }
    

    
    public void inserirRestricaoRecurso() {
    	restricaoRecurso vRestricaoRecurso = new restricaoRecurso();
    	
    	String[] vIdTipoRecursoString = cbxTipoRecurso.getSelectionModel().getSelectedItem().toString().split("-");
    	String[] vIdFuncaoString = cbxFuncao.getSelectionModel().getSelectedItem().toString().split("-");
    	
    	int vIdTipo = Integer.parseInt(vIdTipoRecursoString[0]);
    	int vIdFuncao = Integer.parseInt(vIdFuncaoString[0]);
    	
    	vRestricaoRecurso.setId_tipo_recurso(vIdTipo);
    	vRestricaoRecurso.setId_funcao(vIdFuncao);
    	
    	vCtrl.InserirRestricaoRecurso(vRestricaoRecurso);

    }
    
    public void alimentaTabela() {
    	ObservableList<restricaoRecurso> vRestricaoLista = FXCollections.observableArrayList(vCtrl.ListaRestricaoRecurso());
		
		tbGrid.setItems(vRestricaoLista);
    }
    
    public void fecharJanela() {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
    	stage.close();
    }
    
    public void excluir() {
    	restricaoRecurso vRestricaoRecurso = tbGrid.getSelectionModel().getSelectedItem();
    	
    	int vTabelaRemover = tbGrid.getSelectionModel().getSelectedIndex();
    	
    	vCtrl.excluirRestricaoRecurso(vRestricaoRecurso);
    	tbGrid.getItems().remove(vTabelaRemover);
    }
    
    public void alimentaComboBox() {
    	for(tipoRecurso aux: vCtrl.ListaTipoRecurso())
    		cbxTipoRecurso.getItems().add(aux.getId() +"-"+ aux.getNome());
    	
    	List<funcao> funcao = vCtrl.ListaFuncao();
		for (funcao aux : funcao) {
			cbxFuncao.getItems().add(aux.getId() + "-" + aux.getNome());
		}
    }
    
    public void LimparCampo() {
    	cbxTipoRecurso.getSelectionModel().select(" ");
    	cbxFuncao.getSelectionModel().select(" ");
    }
    
    public void onShow() {
    	this.tabPane.setTabMaxHeight(-1);
		this.tabPane.setTabMaxWidth(-1);
    	
    	this.alimentaComboBox();
    	this.inserirTabela();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.onShow();
		
		btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					excluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});	
		
		btnAdicionar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				inserirRestricaoRecurso();
				alimentaTabela();
				LimparCampo();
			}
			
		});
		
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				fecharJanela();
			}
		});
		
	}

}
