package view.RESERVA;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import model.alerta;
import model.entity.*;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;

public class controllerReserva implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
    private Button btnPreencheComboBox;
	
	@FXML
	private ComboBox<String> cbxResponsavel,cbxdestinatario,cbxTipoRepeticao,cbxStatus;
	
	@FXML
	private DatePicker edtDataHoraInicio,edtDataHoraFinal;
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
    int vTipoComboBox = 0;
    
    //fun�ao para copiar o ID que esta na comboBox
   // String nome = "Vin�cius Mendon�a";
   // String primeiroNome = nome.substring(0, nome.indexOf(" "));
    
    public void inserirReserva(){
    	reserva vReserva = new reserva();
    	String vResponsavel = cbxResponsavel.getValue();
    	String vDestinatario = cbxdestinatario.getValue();
    	String vDataInicio = edtDataHoraInicio.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	String vDataFinal = edtDataHoraFinal.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	
    	vReserva.setId_responsavel( Integer.parseInt( vResponsavel.substring(0, vResponsavel.indexOf(" ")).trim() ));
    	vReserva.setId_destinatario(Integer.parseInt(vDestinatario.substring(0, vDestinatario.indexOf(" ")).trim() ));
    	vReserva.setRepeticao(cbxTipoRepeticao.getValue());
    	vReserva.setStatus(cbxStatus.getValue());
    	vReserva.setData_hora_reserva(vDataInicio);
    	vReserva.setData_hora_final(vDataFinal);
    	
    	vCtrl.InserirReserva(vReserva);
    }
    
    public void preencherUser(List<usuario> user) throws Exception{
    	    	
    	String vListaUsuario = "";
    	    	
        for (usuario user3 : user){
        	if (vTipoComboBox == 1) {
	        	vListaUsuario = Integer.toString(user3.getId())+" - "+user3.getNome();
	        	cbxResponsavel.getItems().addAll(vListaUsuario);
        	}
        	if (vTipoComboBox == 2) {
	        	vListaUsuario = Integer.toString(user3.getId())+" - "+user3.getNome();
	        	cbxdestinatario.getItems().addAll(vListaUsuario);
        	}
        }
        
    }
    
    public void alimentaComboBoxResponsavel(){
    	try {
    		vTipoComboBox = 1;
    		cbxResponsavel.setTooltip(new Tooltip());
    		preencherUser(vCtrl.ListaUsuario());    
			
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Responsavel!");
		} 
    	
    }
    
    public void alimentaComboBoxDestinatario(){
    	try {
    		vTipoComboBox = 2;
    		cbxdestinatario.setTooltip(new Tooltip());
    		preencherUser(vCtrl.ListaUsuario());
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Destinatario!");
		}
    }
    
    public void alimentaComboBoxTipoRepeticao(){
    	try {
    		
    		cbxTipoRepeticao.setTooltip(new Tooltip());
    		cbxTipoRepeticao.getItems().addAll("EVENTO UNICO");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA SEGUNDA");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA TER�A");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA QUARTA");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA QUINTA");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA SEXTA");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA SABADO");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA DOMINGO");
    		cbxTipoRepeticao.getItems().addAll("TODOS OS DIAS");
    		
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Tipo de Repeticao!");
		}
    	
    }
    
    public void alimentaComboBoxStatus(){
    	try {
    		
    		cbxStatus.setTooltip(new Tooltip());
    		cbxStatus.getItems().addAll("ATIVO");
    		cbxStatus.getItems().addAll("PENDENTE");
    		cbxStatus.getItems().addAll("CONCLUIDO");
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Status!");
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				inserirReserva();
				
			}
		});
		
		btnPreencheComboBox.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				alimentaComboBoxResponsavel();
				alimentaComboBoxDestinatario();
				alimentaComboBoxTipoRepeticao();
				alimentaComboBoxStatus();
			}
		});
	}

}
