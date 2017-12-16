package view.TIPO_RECURSO;

import java.net.URL;
import java.util.ResourceBundle;

import model.Alerta;
import model.ENTITY.TipoRecurso;
import service.Service;
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

public class ControllerTipoRecurso implements Initializable{

	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
	private TextField txtNome, txtDescricao, txtIdPesquisa, txtNomePesquisa;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab ctrlPag1,ctrlPag2;
	
	@FXML
	private CheckBox chkAtivo;	
	
	@FXML
	private TableView<TipoRecurso> tbGrid;
	
	private TipoRecurso vTipoRecursoSelecionado;     //varialvel usada para pegar o id do objeto que foi selecionado na tabela e alterar ao salvar
	
	private String vSalvar = "";  //variavel de validação para quando clicar em novo o botaõ salvar volta a inserir e não a alterar objetos
	
	//private String listTable = "table"; //variavel de validação para o botão excluir saber se deve excluir da tabela ou do listView
	private TableColumn<TipoRecurso, Integer> tbColum1 = new TableColumn<TipoRecurso, Integer>(); 		
	private TableColumn<TipoRecurso, String> tbColum2 = new TableColumn<TipoRecurso, String>();
	private TableColumn<TipoRecurso, String> tbColum3 = new TableColumn<TipoRecurso, String>();
	
	Service vCtrl = new Service();
    Alerta vAlerta = new Alerta();
    
	@SuppressWarnings("unchecked")
	public void inserirTabela(){
	    	
    	try {
    		
    		tbColum1.setText("Id");/*SETA O TITULO DA GRID*/
        	tbColum2.setText("Nome");
        	tbColum3.setText("Descrição");
        	
    		
    	    tbColum1.setCellValueFactory(new PropertyValueFactory<TipoRecurso, Integer>("id"));  /*SETA QUAL CAMPO DA LISTA*/
        	tbColum2.setCellValueFactory(new PropertyValueFactory<TipoRecurso, String>("nome")); //PENSAR COMO MOSTRAR O NOME DO USUARIO
        	tbColum3.setCellValueFactory(new PropertyValueFactory<TipoRecurso, String>("descricao"));
        	
        	tbGrid.getColumns().addAll(tbColum1,tbColum2,tbColum3);
        	
        	ObservableList<TipoRecurso> vLista = FXCollections.observableArrayList(vCtrl.ListaTipoRecurso());
        	
        	tbGrid.setItems(vLista);
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela2: \n Erro: "+e.getMessage());
		}		
		    	
    }
	
	public void alteraVariavelControle(String pTipo) {
    	this.vSalvar = pTipo;
    }
	
	public void inserirTipo_Recurso(){
    
		TipoRecurso vTipoRecurso = new TipoRecurso();
	    
		vTipoRecurso.setNome(txtNome.getText());
		vTipoRecurso.setDescricao(txtDescricao.getText());
	    vTipoRecurso.setAtivo(chkAtivo.isSelected());
	    
	    if(this.vSalvar.equals("novo")) {
			vCtrl.inserirTipo_Recurso( vTipoRecurso);
		}else if(this.vSalvar.equals("alterar")) {
			 vTipoRecurso.setId(vTipoRecursoSelecionado.getId());
			
	    vCtrl.alterarTipoRecurso(vTipoRecurso);
	
	}
    
    txtNome.clear();
    txtDescricao.clear();
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
		
		 ObservableList<TipoRecurso> vLista = FXCollections.observableArrayList(vCtrl.filtrarTipoRecurso(aux,txtNomePesquisa.getText()));
		
		 tbGrid.setItems(vLista);
	
	}
	
	
	public void excluir() {
		
		TipoRecurso vTipoRecursoSelecionado = tbGrid.getSelectionModel().getSelectedItem();
		int attTabela = tbGrid.getSelectionModel().getSelectedIndex();
		
		vCtrl.excluirTipoRecurso(vTipoRecursoSelecionado);
		tbGrid.getItems().remove(attTabela);
	}
		
	
	public void moverPag1() {	
									 
		ObservableList<TipoRecurso> vLista = FXCollections.observableArrayList(vCtrl.ListaTipoRecurso());
		
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
			btnSalvar.setDisable(true);
			btnSair.setDisable(false);
			chkAtivo.setSelected(true);
			break;
	
		default:
			break;
		}
	}
	
	public void alterarDados() {
		
		 vTipoRecursoSelecionado = (tbGrid.getSelectionModel().getSelectedItem());
		 
		 txtNome.setText(vTipoRecursoSelecionado.getNome());
		 txtDescricao.setText(vTipoRecursoSelecionado.getDescricao());
		 chkAtivo.setSelected(vTipoRecursoSelecionado.getAtivo());
		 
		
	}
	
	public void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}
	
	public void onShow() {
		this.tabPane.setTabMaxHeight(-1);
		this.tabPane.setTabMaxWidth(-1);
		
		this.inserirTabela();
		this.ControlaBotao("novo");
		this.alteraVariavelControle("novo");
	
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
				alteraVariavelControle("novo");
				moverPag2();
				ControlaBotao("voltar");
			}
		});
		
		
		btnVoltar.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub	
				alteraVariavelControle("novo");
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
		
		txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!( txtNome.getText().equals("") )) {
				//System.out.println("Nome: " + cBoxFuncao.getValue() + txtMatricula.getText() + passSenha.getText());
				btnSalvar.setDisable(false);
			}
		});
		
		txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
			if (( txtNome.getText().equals("") )) {
				//System.out.println("Nome: " + cBoxFuncao.getValue() + txtMatricula.getText() + passSenha.getText());
				btnSalvar.setDisable(true);
			}
		});
	  }

	/*public TableColumn<tipoRecurso, String> getTbColum3() {
		return tbColum3;
	}

	public void setTbColum3(TableColumn<tipoRecurso, String> tbColum3) {
		this.tbColum3 = tbColum3;
	}*/
	
}	