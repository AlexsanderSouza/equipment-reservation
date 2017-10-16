package view.UNIDADE;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;


import model.alerta;
import controller.Controller;
import model.entity.unidade;
import model.entity.usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class controllerUnidade implements Initializable{

	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
    private TextField txtNome,txtEmail,txtTelefone,txtEndereco;
	
	@FXML
	private ComboBox cBoxInstituicao;
	
	private HashSet<String> lHUser;
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
	
	public void inserirUnidade(){
        
        unidade vUnidade = new unidade();
        
        vUnidade.setNome(txtNome.getText());
        vUnidade.setEmail(txtEmail.getText());        
        vUnidade.setTelefone(txtTelefone.getText());
        vUnidade.setEndereco(txtEndereco.getText());        
        
        vCtrl.InserirUnidade(vUnidade);
    }
	
	
	public void iniciaComboBox(){
		lHUser = new HashSet<String>();
		List<usuario> auxUser = new ArrayList<usuario>();
		
		auxUser = vCtrl.ListaUsuario();
	
		for(usuario user: auxUser){
			lHUser.add(user.getNome());
					}	
		cBoxInstituicao.getItems().addAll(lHUser);
	}
		

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		iniciaComboBox();
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					inserirUnidade();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});	
	}

}
