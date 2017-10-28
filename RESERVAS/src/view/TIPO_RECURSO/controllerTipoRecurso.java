package view.TIPO_RECURSO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alertaInformacao;
import model.ENTITY.tipoRecurso;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class controllerTipoRecurso implements Initializable{

	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
	private TextField txtNome, txtIdPesquisa, txtNomePesquisa;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab ctrlPag1,ctrlPag2;
	
	@FXML
	private CheckBox chkAtivo;	
	
	@FXML
	private TableView<tipoRecurso> tbGrid;
	
	private tipoRecurso vTipoRecursoSelecionado;     //varialvel usada para pegar o id do objeto que foi selecionado na tabela e alterar ao salvar
	private String salvar = "salvarNovo";  //variavel de validação para quando clicar em novo o botaõ salvar volta a inserir e não a alterar objetos
	//private String listTable = "table"; //variavel de validação para o botão excluir saber se deve excluir da tabela ou do listView
	private TableColumn<tipoRecurso, Integer> tbColum1 = new TableColumn<tipoRecurso, Integer>(); 		
	private TableColumn<tipoRecurso, String> tbColum2 = new TableColumn<tipoRecurso, String>();
	
	Controller vCtrl = new Controller();
    alertaInformacao vAlerta = new alertaInformacao();
    
	@SuppressWarnings("unchecked")
	public void inserirTabela(){
	    	
    	try {
    		
    		tbColum1.setText("Id");/*SETA O TITULO DA GRID*/
        	tbColum2.setText("Nome");
        	
    		
    	    tbColum1.setCellValueFactory(new PropertyValueFactory<tipoRecurso, Integer>("id"));  /*SETA QUAL CAMPO DA LISTA*/
        	tbColum2.setCellValueFactory(new PropertyValueFactory<tipoRecurso, String>("nome")); //PENSAR COMO MOSTRAR O NOME DO USUARIO
        	
        	tbGrid.getColumns().addAll(tbColum1,tbColum2);
        	
        	ObservableList<tipoRecurso> vLista = FXCollections.observableArrayList(vCtrl.ListaTipoRecurso());
        	
        	tbGrid.setItems(vLista);
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela2: \n Erro: "+e.getMessage());
		}		
		    	
    }
	
	
	
	public void inserirTipo_Recurso(){
    
		tipoRecurso vTipoRecurso = new tipoRecurso();
	    
		vTipoRecurso.setNome(txtNome.getText());
	    vTipoRecurso.setAtivo(chkAtivo.isSelected());
	    
	    if(this.salvar.equals("salvarNovo")) {
			vCtrl.inserirTipo_Recurso( vTipoRecurso);
		}else if(this.salvar.equals("alterar")) {
			 vTipoRecurso.setId(vTipoRecursoSelecionado.getId());
			
	    vCtrl.alterarTipoRecurso(vTipoRecurso);
	
	}
    
    txtNome.clear();
    chkAtivo.setSelected(true);

    
	}
	
	public void filtrar() {
		Integer aux;
	
		 try {
		 aux = Integer.parseInt(txtIdPesquisa.getText());
		 } catch (NumberFormatException e) {
		 // TODO Auto-generated catch block
		 aux = null;
		 }
		
		 ObservableList<tipoRecurso> vLista = FXCollections.observableArrayList(vCtrl.filtrarTipoRecurso(aux,txtNomePesquisa.getText()));
		
		 tbGrid.setItems(vLista);
	
	}
	
	
	public void excluir() {
		
		tipoRecurso vTipoRecursoSelecionado = tbGrid.getSelectionModel().getSelectedItem();
		int attTabela = tbGrid.getSelectionModel().getSelectedIndex();
		
		vCtrl.excluirTipoRecurso(vTipoRecursoSelecionado);
		tbGrid.getItems().remove(attTabela);
	}
		
	
	public void moverPag1() {
		this.salvar = "salvarNovo"; //variavel de validação paraquando clicar em novo o botaõ salvar volta a inserir e não a alterar objetos
									 
		ObservableList<tipoRecurso> vLista = FXCollections.observableArrayList(vCtrl.ListaTipoRecurso());
		
		tbGrid.setItems(vLista);
		
		tabPane.getSelectionModel().select(ctrlPag1);
	}

	public void moverPag2() {	
		tabPane.getSelectionModel().select(ctrlPag2);
	}

	public void ControlaBotao(String pBotao) {
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
			chkAtivo.setSelected(true);
			break;
	
		default:
			break;
		}
	}
	
	public void alterarDados() {
	
		 this.salvar = "alterar";
		
		 vTipoRecursoSelecionado = (tbGrid.getSelectionModel().getSelectedItem());
		 
		 txtNome.setText(vTipoRecursoSelecionado.getNome());
		 chkAtivo.setSelected(vTipoRecursoSelecionado.getAtivo());
		 
		
	}
	
	public void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}
	
	public void onShow() {
		this.inserirTabela();
		this.ControlaBotao("novo");
	
	}
	   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
			
		this.onShow();
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					inserirTipo_Recurso();
					} catch (Exception e) {
					// TODO: handle exception
				}
				
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
		
		btnNovo.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moverPag2();
				ControlaBotao("voltar");
			}
		});
		
		
		btnVoltar.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub				
				moverPag1();
				ControlaBotao("novo");				
			}
		});
		
		
		btnSair.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub				
				fecharJanela();
			}
		});
		
		btnAlterar.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub				
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
	  }
	
}	