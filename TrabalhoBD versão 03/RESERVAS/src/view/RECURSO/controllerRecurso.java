package view.RECURSO;

import java.net.URL;
import java.util.ResourceBundle;

import model.alerta;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class controllerRecurso implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
	private TextField txtNome,txtEtiqueta;
	
	@FXML
	private ComboBox<?> cbxTipo,cbxUnidade;
	
	@FXML
    private RadioButton chkAtivo;
	
	@FXML
	private TextArea txtObs;
	
	@FXML
	private ListView<?> txtRestricao;

	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
