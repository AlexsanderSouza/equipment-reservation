/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.USUARIO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import controller.Controller;
import model.entity.usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.EventHandler;

/**
 *
 * @author alexsandersouza
 */
public class controllerUser implements Initializable {
        
    @FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
     
    @FXML
    private TextField txtNome,txtEmail,txtSenha,txtMatricula,txtTelefone,textFieldId;
    
    
   
    @FXML
    private Tab ctrlPag1, ctrlPag2;
    
    @FXML
    private TabPane tabPanelPai;
    
    @FXML
	public  TableView<usuario> tbGrid;
    
     
    private TableColumn tbColumn1 = new TableColumn("Matricula");
    private TableColumn tbColumn2 = new TableColumn("Nome");
    private TableColumn tbColumn3 = new TableColumn("Função");
    private TableColumn tbColumn4 = new TableColumn("Email");
    private TableColumn tbColumn5 = new TableColumn("Telefone");
    private TableColumn tbColumn6 = new TableColumn("Status");
    
    @FXML
    private RadioButton chkAtivo;
    
    Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
    public void abilitaPagina(){
	    //ctrlPag1.setClosable(true);	
		//ctrlPag2.setClosable(false);
		//tabPanelPai.getTabs().addAll(ctrlPag1,ctrlPag2);
    	//tabPanelPai.getSelectionModel().select(ctrlPag2);
    }
    
    public void inserirUsuario(){
        
        usuario vUser = new usuario();
        
        vUser.setNome(txtNome.getText());
        vUser.setEmail(txtEmail.getText());
        vUser.setSenha(txtSenha.getText());
        vUser.setMatricula(txtMatricula.getText());
        vUser.setTelefone(txtTelefone.getText());
        vUser.setAtivo(chkAtivo.isSelected());
        vCtrl.InserirUsuario(vUser);
    }
    
    public void inserirTabela(){
    	
    
    	tbColumn1.setCellValueFactory(new PropertyValueFactory<usuario, String>("nome"));
    	tbColumn2.setCellValueFactory(new PropertyValueFactory<usuario, String>("matricula"));
    	tbColumn3.setCellValueFactory(new PropertyValueFactory<usuario, String>("senha"));
    	tbColumn4.setCellValueFactory(new PropertyValueFactory<usuario, String>("email"));
    	tbColumn5.setCellValueFactory(new PropertyValueFactory<usuario, String>("telefone"));
    	tbColumn6.setCellValueFactory(new PropertyValueFactory<usuario, String>("ativo"));
    	
    	tbGrid.getColumns().addAll(tbColumn1,tbColumn2,tbColumn3,tbColumn4,tbColumn5,tbColumn6);
    //	tbGrid.getItems().clear();
//    	System.out.println(vCtrl.ListaUsuario());
         ObservableList<usuario> listaOb = FXCollections.observableArrayList(vCtrl.ListaUsuario());
       //  System.out.println(listaOb);
			tbGrid.setItems(listaOb);
        // tbGrid.getItems().setAll(listaOb);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
    	
    	this.inserirTabela();
    	btnNovo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				abilitaPagina();	
			}
		});
    	
        btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {              
                try {                	
                  inserirUsuario();            
                } catch (Exception e) {
                    vAlerta.mensagemAlerta("Não foi possivel Salvar! \n "+ "Erro: "+e.getMessage());
                }
            }
        	});
    }    
    
}
