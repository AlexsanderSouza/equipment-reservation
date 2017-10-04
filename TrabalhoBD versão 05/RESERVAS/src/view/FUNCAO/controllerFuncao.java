package view.FUNCAO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import controller.Controller;
import model.entity.funcao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class controllerFuncao implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
    private TextField txtNome, txtDescricao;
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
    public void inserirFuncao(){
        
        funcao vfuncao = new funcao();
        
        vfuncao.setNome(txtNome.getText());
        vfuncao.setDescricao(txtDescricao.getText());        
  
        
        vCtrl.InserirFuncao(vfuncao);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
		
	}

}
