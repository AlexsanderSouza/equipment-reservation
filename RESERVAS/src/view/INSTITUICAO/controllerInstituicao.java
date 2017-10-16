package view.INSTITUICAO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import model.ENTITY.instituicao;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class controllerInstituicao implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
    private TextField txtNome,txtEmail,txtTelefone;
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
    public void inserirInstituicao(){
        
        instituicao vInstituicao = new instituicao();
        
        vInstituicao.setNome(txtNome.getText());
        vInstituicao.setEmail(txtEmail.getText());        
        vInstituicao.setTelefone(txtTelefone.getText());   
        
        vCtrl.InserirInstituicao(vInstituicao);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					inserirInstituicao();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
		
	}

}
