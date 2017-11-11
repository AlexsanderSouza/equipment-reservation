package view.RECURSO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import model.ENTITY.recurso;
import model.ENTITY.tipoRecurso;
import model.ENTITY.unidade;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class controllerRecurso implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
	private TextField txtEtiqueta,txtPesqId,txtPesqEtiqueta,txtPesqObservacao;
	
	@FXML
	private ComboBox<String> cbxTipo,cbxPesqTipo,cbxUnidade,cbxPesqUnidade;
	
	@FXML
    private CheckBox chkAtivo;
	
	@FXML
	private Tab ctrlPag1,ctrlPag2;
	
	@FXML 
	TableView<recurso> tbGrid;
	
	@FXML
	private TextArea txtObs;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private ListView<?> txtRestricao;
	
	private recurso vRecurso;
	
	private String vSalvar = "";
	
	private TableColumn<recurso, Integer> tbColum1 = new TableColumn<recurso, Integer>(); 		
	private TableColumn<recurso, String> tbColum2 = new TableColumn<recurso, String>(); 
	private TableColumn<recurso, String> tbColum3 = new TableColumn<recurso, String>(); 
	private TableColumn<recurso, String> tbColum4  = new TableColumn<recurso, String>(); 
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
			tbColum3.setCellValueFactory(new PropertyValueFactory<recurso, String>("nomeTipoRecurso")); //Nome do tipo de recurso
			tbColum4.setCellValueFactory(new PropertyValueFactory<recurso, String>("nomeUnidade"));
			tbColum5.setCellValueFactory(new PropertyValueFactory<recurso, String>("Observacao"));
			
			tbGrid.getColumns().addAll(tbColum1,tbColum2,tbColum3,tbColum4,tbColum5);
			
			alimentaTabela();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: "+e.getMessage());
		}
    }
	
    public void alterarDados() {    	
    	this.vRecurso = (tbGrid.getSelectionModel().getSelectedItem());
    	
    	txtEtiqueta.setText(vRecurso.getEtiqueta());
    	txtObs.setText(vRecurso.getObservacao());
    	
    	cbxTipo.getSelectionModel().select(vRecurso.getId_tipo_recurso()+"-"+vRecurso.getNomeTipoRecurso());    	
    	cbxUnidade.getSelectionModel().select(vRecurso.getId_unidade()+"-"+vRecurso.getNomeUnidade());
    	
    	chkAtivo.setSelected(vRecurso.GetAtivo());
    	
    }
    
    public void excluir() {
    	recurso vRecurso = tbGrid.getSelectionModel().getSelectedItem();
    	
    	int vTabelaRemover = tbGrid.getSelectionModel().getSelectedIndex();
    	
    	vCtrl.excluirRecurso(vRecurso);
		tbGrid.getItems().remove(vTabelaRemover);
    }
    
    public void alimentaTabela() {
    	ObservableList<recurso> vRecursoLista = FXCollections.observableArrayList(vCtrl.ListaRecurso());
		
		tbGrid.setItems(vRecursoLista);
    }
    
    public void alimentaComboBosPesquisa() {
    	for(tipoRecurso aux: vCtrl.ListaTipoRecurso())
        	cbxPesqTipo.getItems().add(aux.getId() +"-"+ aux.getNome());
    	
    	for(unidade aux: vCtrl.ListaUnidade())
    		cbxPesqUnidade.getItems().add(aux.getId() +"-"+ aux.getNome());
    } 
    
    public void alimentaComboBoxCadastro() {
    	
    	for(tipoRecurso aux: vCtrl.ListaTipoRecurso())
    	  cbxTipo.getItems().add(aux.getId() +"-"+ aux.getNome());
    	
    	for(unidade aux: vCtrl.ListaUnidade())
    		cbxUnidade.getItems().add(aux.getId() +"-"+ aux.getNome());
    }
        
    public void fecharJanela() {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
    	stage.close();
    }
    
    public void limparCampo() {
    	txtEtiqueta.clear();
    	cbxTipo.getSelectionModel().select(" ");
    	cbxUnidade.getSelectionModel().select(" ");
    	txtObs.clear();
    }
    
    public void inserirRecurso() {
    	
    	recurso vRecurso = new recurso();
    	    	
    	// Seleciona o id do tipo para inserir no banco
    	String[] vIdTipoString = cbxTipo.getSelectionModel().getSelectedItem().toString().split("-");
    	String[] vIdUnidadeString = cbxUnidade.getSelectionModel().getSelectedItem().toString().split("-");
    	
    	int vIdtipo = Integer.parseInt(vIdTipoString[0]);
    	int vIdUnidade = Integer.parseInt(vIdUnidadeString[0]);
    	    	
    	vRecurso.setEtiqueta(txtEtiqueta.getText());
    	vRecurso.setObservacao(txtObs.getText());
    	vRecurso.setAtivo(chkAtivo.isSelected());
    	vRecurso.setId_tipo_recurso(vIdtipo);
    	vRecurso.setId_unidade(vIdUnidade);
    	
    	if (this.vSalvar.equals("novo")) {
    		vCtrl.InserirRecurso(vRecurso);
    		limparCampo();
    	} 
    	if (this.vSalvar.equals("alterar")) {
    		vRecurso.setId(this.vRecurso.getId());
    		vCtrl.alterarRecurso(vRecurso);
    		limparCampo();
    		this.vSalvar = "salvarnovo";
    	}
    	
    }
    
    public void moverPag2() {
    	tabPane.getSelectionModel().select(ctrlPag2);    	
    }
    
    public void moverPag1() {
    	alimentaTabela();
    	
    	tabPane.getSelectionModel().select(ctrlPag1);
    }
    
    public void filtrar() {
    	Integer vId_recurso;
    	Integer vIdTipoRecurso;
    	Integer vIdUnidade;
    	String vEtiqueta;
    	String vObs;
    	String vAux;
    	
    	try {
    		vId_recurso = Integer.parseInt(txtPesqId.getText());
		} catch (Exception e) {
			// TODO: handle exception
			vId_recurso = null;
		}	
    
    	try {
    		vAux = cbxPesqTipo.getValue();
    		vAux = vAux.substring(0, vAux.indexOf(" ")).trim();
    		vIdTipoRecurso = Integer.parseInt(vAux);
		} catch (Exception e) {
			// TODO: handle exception
			vIdTipoRecurso = null;
		}
    	
    	try {
    		vAux = cbxPesqUnidade.getValue();
    		vAux = vAux.substring(0, vAux.indexOf(" ")).trim();
    		vIdUnidade = Integer.parseInt(vAux);
		} catch (Exception e) {
			// TODO: handle exception
			vIdUnidade = null;
		}
    	    	
    	vEtiqueta = txtPesqEtiqueta.getText();
    	
    	vObs = txtPesqObservacao.getText();
    	    	
    	ObservableList<recurso> vRecursoLista = FXCollections.observableArrayList(vCtrl.filtrarRecurso( vId_recurso, vIdTipoRecurso, vIdUnidade, vEtiqueta, vObs));
		
		tbGrid.setItems(vRecursoLista);    	
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
    
    public void alteraVariavelControle(String pTipo) {
    	this.vSalvar = pTipo;
    }
    
    public void onShow() {
    	this.inserirTabela();
    	this.alimentaComboBosPesquisa();
    	this.alimentaComboBoxCadastro();
    	this.ControlaBotao("novo");
    	this.alteraVariavelControle("novo");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		this.onShow();
		
		btnAlterar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				alteraVariavelControle("alterar");				
				alterarDados();
				moverPag2();
				ControlaBotao("voltar");
			}
		});
		
		btnFiltrar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub				
				filtrar();
			}
		});
		
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
		
		btnVoltar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub				
				moverPag1();				
				ControlaBotao("novo");	
				alteraVariavelControle("novo");
			}
		});
		
		btnNovo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				alteraVariavelControle("salvarnovo");
				moverPag2();
				limparCampo();
				ControlaBotao("voltar");
			}
		});
		
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				fecharJanela();
			}
		});
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				inserirRecurso();
				
			}
		});
		
	}

}
