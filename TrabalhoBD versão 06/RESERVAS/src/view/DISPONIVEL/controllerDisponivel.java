package view.DISPONIVEL;

import java.net.URL;
import java.util.ResourceBundle;

import view.COMPONENTE_DATAHORA.DateTimePicker;
import view.RESERVA.viewReserva;
import controller.Controller;
import model.alerta;
import model.entity.disponivel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class controllerDisponivel implements Initializable {
	
	
	@FXML
	private TableView<disponivel> tbGrid;
	
	@FXML
    private Button btnPesquisar,btnReserva;
	
	@FXML
	private VBox vboxDisponivel; //Para usar o botão dinamico de data e hora
	
	//Variavel local
	private TableColumn<disponivel, String> tbColum1 = new TableColumn<disponivel, String>(); 		
	private TableColumn<disponivel, String> tbColum2 = new TableColumn<disponivel, String>(); 
	//private TableColumn<disponivel, String> tbColum3 = new TableColumn<disponivel, String>();
	
	DateTimePicker vDataTimeInicial = new DateTimePicker();
	DateTimePicker vDataTimeFinal = new DateTimePicker();
	
	Controller vCtrl = new Controller();
	alerta vAlerta = new alerta();
	
	public void dataTimePicker() {                                  //insere o botão dinamico de data e hora
    	vboxDisponivel.setSpacing(05);
    	vboxDisponivel.getChildren().add(vDataTimeInicial);
    	vboxDisponivel.getChildren().add(vDataTimeFinal);
    }
	
	public void Filtro(){
		String vDataInicio = vDataTimeInicial.getTextField().getText();
    	String vDataFinal = vDataTimeFinal.getTextField().getText();
				
		ObservableList<disponivel> vLista = FXCollections.observableArrayList(vCtrl.ListaDisponivel(vDataInicio, vDataFinal));
    	tbGrid.setItems(vLista);
	}
	
	@SuppressWarnings("unchecked")
	public void inserirTabela(){
    	    	
    	try {
    		tbColum1.setText("Tipo de Recurso");/*SETA O TITULO DA GRID*/
        	tbColum2.setText("Qtde Disponivel");
        	//tbColum3.setText("Qtde Alocado");
        	
    		
    	    tbColum1.setCellValueFactory(new PropertyValueFactory<disponivel, String>("tipo"));/*SETA QUAL CAMPO DA LISTA*/
        	tbColum2.setCellValueFactory(new PropertyValueFactory<disponivel, String>("qtdedisp"));//PENSAR COMO MOSTRAR O NOME DO USUARIO
        	//tbColum3.setCellValueFactory(new PropertyValueFactory<disponivel, String>("qtdeloca"));
        	        	        	
        	tbGrid.getColumns().addAll(tbColum1,tbColum2);
        	
        	disponivel vDisponivel = new disponivel();
        	vDisponivel.setTipo("");
        	vDisponivel.setQtdedisp("");
        	vDisponivel.setQtdeloca("");
        	
        	ObservableList<disponivel> vLista = FXCollections.observableArrayList(vDisponivel);
        	
        	tbGrid.setItems(vLista);
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: "+e.getMessage());
		}		
		    	
    }
	
	public void onShow(){
		inserirTabela();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.dataTimePicker();
		this.onShow();
		
		btnReserva.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	viewReserva vReserva = new viewReserva();
                	vReserva.start();
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao Abrir Tela de Reserva! \n"+"Erro: "+e.getMessage());                    
                }
			}
		});
		
		btnPesquisar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Filtro();
				
			}
		});
		
	}

}
