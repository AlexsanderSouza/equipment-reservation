package view.UNIDADE;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import controller.Controller;
import model.entity.unidade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class controllerUnidade implements Initializable{

	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
    private TextField txtNome,txtEmail,txtTelefone,txtEndereco;
	
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
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
