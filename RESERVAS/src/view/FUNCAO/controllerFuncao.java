package view.FUNCAO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import model.alerta;
import model.ENTITY.funcao;
import model.ENTITY.permissao;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class controllerFuncao implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
    private TextField txtNome, txtDescricao,txtIdPesquisa,txtNomePesquisa;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab ctrlPag1,ctrlPag2;
	
	@FXML
	private RadioButton chkAtivo;
	
	@FXML
	private TableView<funcao> tbGrid;
	
	@FXML 
	ListView<String> listViewPermissao;
	
	@FXML
	private ChoiceBox<String> ccBoxPermissao;
	
	//Variavel local
	private funcao vFuncaoSelecionada;     //varialvel usada para pegar o id do objeto que foi selecionado na tabela e alterar ao salvar
	private String salvar = "salvarNovo";  //variavel de validação para quando clicar em novo o botaõ salvar volta a inserir e não a alterar objetos
	private String listTable = "table"; //variavel de validação para o botão excluir saber se deve excluir da tabela ou do listView
	private TableColumn<funcao, Integer> tbColum1 = new TableColumn<funcao, Integer>(); 		
	private TableColumn<funcao, String> tbColum2 = new TableColumn<funcao, String>(); 
	private TableColumn<funcao, String> tbColum3 = new TableColumn<funcao, String>();
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
    
    public void alimentaCcBoxPermissao() {
    	List<permissao> permissoes = vCtrl.ListaPermissao();
    	for(permissao aux: permissoes) {
    		ccBoxPermissao.getItems().add(aux.getId()+" - "+aux.getNome());
    	}
    	
    }
    
    public void alimentaListViewPermissao() {
    	String listaCcBox;
    	boolean duplicados = false;
    	int qtd = 0;
    	
    	List<String> listPermissao = listViewPermissao.getItems();
    	
    	listaCcBox = ccBoxPermissao.getSelectionModel().getSelectedItem();
    
    	for(String aux: listPermissao) {  //atribui o validador de valores duplicados
    		qtd = qtd + 1;
    		if(aux.equals(listaCcBox)) {
    			duplicados = true;		
    		}
    	}
    	
    	if(duplicados != true && qtd <=5) {  //não deixa a choiceBox acrescertar valores repetidos e não permite um numero maior que 6 permisões
    		listViewPermissao.getItems().add(listaCcBox);
    	}	
    }
    
    
    
    @SuppressWarnings("unchecked")
	public void inserirTabela(){
    	    	
    	try {
    		tbColum1.setText("Id");/*SETA O TITULO DA GRID*/
        	tbColum2.setText("Nome");
        	tbColum3.setText("Descrição");
        	
    		
    	    tbColum1.setCellValueFactory(new PropertyValueFactory<funcao, Integer>("id"));  /*SETA QUAL CAMPO DA LISTA*/
        	tbColum2.setCellValueFactory(new PropertyValueFactory<funcao, String>("nome")); //PENSAR COMO MOSTRAR O NOME DO USUARIO
        	tbColum3.setCellValueFactory(new PropertyValueFactory<funcao, String>("descricao"));
        	        	
        	tbGrid.getColumns().addAll(tbColum1,tbColum2,tbColum3);
        	
        	ObservableList<funcao> vLista = FXCollections.observableArrayList(vCtrl.ListaFuncao());
        	
        	tbGrid.setItems(vLista);
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: "+e.getMessage());
		}		
		    	
    }
    
    public void inserirFuncao(){
    	String listaCcBox;
        funcao vFuncao = new funcao();
        List<String> listPermissao = listViewPermissao.getItems();
        
        vFuncao.setNome(txtNome.getText());
        vFuncao.setDescricao(txtDescricao.getText());   
	
    	listaCcBox = ccBoxPermissao.getSelectionModel().getSelectedItem();
    
    	for(String aux: listPermissao) {  //atribui o validador de valores duplicados
    		String[] separaId = new String[4]; 
    		separaId = aux.split(" ");
    		///////////////////////////////   Revisar
    	  if(vFuncao.getId_permissao1() == 0) {
    		  vFuncao.setId_permissao1(Integer.parseInt(separaId[0]));
    		  System.out.println(vFuncao.getId_permissao1());
    	  }else if(vFuncao.getId_permissao2() == 0) {
    		  vFuncao.setId_permissao2(Integer.parseInt(separaId[0]));
    		  System.out.println(vFuncao.getId_permissao2());
    	  }else if(vFuncao.getId_permissao3() == 0) {
    		  vFuncao.setId_permissao3(Integer.parseInt(separaId[0]));
    		  System.out.println(vFuncao.getId_permissao3());
    	  }else if(vFuncao.getId_permissao4() == 0) {
    		  vFuncao.setId_permissao4(Integer.parseInt(separaId[0]));
    	  }else if(vFuncao.getId_permissao5() == 0) {
    		  vFuncao.setId_permissao5(Integer.parseInt(separaId[0]));
    	  }else if(vFuncao.getId_permissao6() == 0) {
    		  vFuncao.setId_permissao6(Integer.parseInt(separaId[0]));
    	  }
    	}
  
        
        if(this.salvar.equals("salvarNovo")) {
            vCtrl.InserirFuncao(vFuncao);
    	}else if(this.salvar.equals("alterar")) {
    		vFuncao.setId(vFuncaoSelecionada.getId());
    		vCtrl.alterarFuncao(vFuncao);
    	}
        
      //Limpar dados
        txtNome.clear();
        txtDescricao.clear();
        listViewPermissao.getItems().clear();
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
    	
    	ObservableList<funcao> vLista = FXCollections.observableArrayList(vCtrl.filtrarFuncao(aux, txtNomePesquisa.getText()));
    	
    	tbGrid.setItems(vLista);
  	
    }
    
    
    public void excluir() {
    	
    	if(listTable.equals("table")) { //remove objetos da lista da tabela
    		funcao vFuncaoSelecionada2 = tbGrid.getSelectionModel().getSelectedItem();
        	int attTabela = tbGrid.getSelectionModel().getSelectedIndex();
        	tbGrid.getItems().remove(attTabela);
        	vCtrl.excluirFuncao(vFuncaoSelecionada2);
    	}else if(listTable.equals("listView")) {//remove objetos da lista de permissões
    		
        	int listaIndex = listViewPermissao.getSelectionModel().getSelectedIndex();
        	listViewPermissao.getItems().remove(listaIndex);
    	}
    		
    	
    	      
        
    }
    
    public void moverPag1(){
    	listViewPermissao.getItems().clear();
    	this.salvar = "salvarNovo"; //variavel de validação paraquando clicar em novo o botaõ salvar volta a inserir e não a alterar objetos
    	this.listTable = "table"; 
    	
    	ObservableList<funcao> vLista = FXCollections.observableArrayList(vCtrl.ListaFuncao());
    	tbGrid.setItems(vLista);
    	
    	tabPane.getSelectionModel().select(ctrlPag1);
    }
    
    public void moverPag2(){
    	this.listTable  = "listView";
    	
    	tabPane.getSelectionModel().select(ctrlPag2);
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
		//	btnExcluir.setDisable(true);   //não sera necessário para essa classe
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
    	
    	vFuncaoSelecionada = tbGrid.getSelectionModel().getSelectedItem();
    	
    	txtNome.setText(vFuncaoSelecionada.getNome());
    	txtDescricao.setText(vFuncaoSelecionada.getDescricao());
    	chkAtivo.setSelected(vFuncaoSelecionada.getAtivo());
    	
    	
    	listViewPermissao.getItems().addAll(vCtrl.listViewAlterarFuncao(vFuncaoSelecionada));
    }    
    
    
    
    public void fecharJanela() {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
    }
    
    public void onShow(){
    	this.alimentaCcBoxPermissao();
    	this.inserirTabela();
    	this.ControlaBotao("novo");
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		onShow();
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					inserirFuncao();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		
		ccBoxPermissao.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					alimentaListViewPermissao();
					
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
