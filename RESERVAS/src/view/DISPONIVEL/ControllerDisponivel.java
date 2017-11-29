package view.DISPONIVEL;

import java.net.URL;
import java.util.ResourceBundle;

import view.COMPONENTE_DATAHORA.DateTimePicker;
import model.Alerta;
import model.ENTITY.Disponivel;
import model.ENTITY.Reserva;
import service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerDisponivel implements Initializable {
	
	
	@FXML
	private TableView<Disponivel> tbGrid;
	
	@FXML
    private Button btnPesquisar,btnReserva,btnSair;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private VBox vboxDisponivel; //Para usar o botão dinamico de data e hora
	
	//Variavel local
	private TableColumn<Disponivel, String> tbColum1 = new TableColumn<Disponivel, String>(); 		
	private TableColumn<Disponivel, String> tbColum2 = new TableColumn<Disponivel, String>(); 
	//private TableColumn<disponivel, String> tbColum3 = new TableColumn<disponivel, String>();
	
	DateTimePicker vDataTimeInicial = new DateTimePicker();
	DateTimePicker vDataTimeFinal = new DateTimePicker();
	
	Service vCtrl = new Service();
	Alerta vAlerta = new Alerta();
	
	public void dataTimePicker() {                                  //insere o botão dinamico de data e hora
    	vboxDisponivel.setSpacing(05);
    	vboxDisponivel.getChildren().add(vDataTimeInicial);
    	vboxDisponivel.getChildren().add(vDataTimeFinal);
    }
	
	public void fecharJanela() {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
    	stage.close();
    }
	
	public void Filtro(){
		String vDataInicio = vDataTimeInicial.getTextField().getText();
    	String vDataFinal = vDataTimeFinal.getTextField().getText();
				
		ObservableList<Disponivel> vLista = FXCollections.observableArrayList(vCtrl.ListaDisponivel(vDataInicio, vDataFinal));
    	tbGrid.setItems(vLista);
	}
	
	@SuppressWarnings("unchecked")
	public void inserirTabela(){
    	    	
    	try {
    		tbColum1.setText("Tipo de Recurso");/*SETA O TITULO DA GRID*/
        	tbColum2.setText("Qtde Disponivel");
        	//tbColum3.setText("Qtde Alocado");
        	
    		
    	    tbColum1.setCellValueFactory(new PropertyValueFactory<Disponivel, String>("tipo"));/*SETA QUAL CAMPO DA LISTA*/
        	tbColum2.setCellValueFactory(new PropertyValueFactory<Disponivel, String>("qtdedisp"));//PENSAR COMO MOSTRAR O NOME DO USUARIO
        	//tbColum3.setCellValueFactory(new PropertyValueFactory<disponivel, String>("qtdeloca"));
        	        	        	
        	tbGrid.getColumns().addAll(tbColum1,tbColum2);
        	
        	Disponivel vDisponivel = new Disponivel();
        	vDisponivel.setTipo("");
        	vDisponivel.setQtdedisp("");
        	vDisponivel.setQtdeloca("");
        	
        	ObservableList<Disponivel> vLista = FXCollections.observableArrayList(vDisponivel);
        	
        	tbGrid.setItems(vLista);
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: "+e.getMessage());
		}		
		    	
    }
	
	public void onShow(){
		this.tabPane.setTabMaxHeight(-1);
		this.tabPane.setTabMaxWidth(-1);
		
		inserirTabela();
	}
	
	public void inserirReserva(){
		String vDataInicio = vDataTimeInicial.getTextField().getText();
    	String vDataFinal = vDataTimeFinal.getTextField().getText();
    	int vUsuarioLogado = vCtrl.ListarUsuarioLogado();
    	
    	Disponivel vDisponivel = (tbGrid.getSelectionModel().getSelectedItem());
	    
		int vTipoRecurso_Id = Integer.parseInt(vDisponivel.getTipo().substring(0, vDisponivel.getTipo().indexOf(" ")).trim() );
    			
    	Reserva vReserva = new Reserva();
		
		vReserva.setData_hora_reserva(vDataInicio);
		vReserva.setData_hora_final(vDataFinal);
		vReserva.setId_responsavel(vUsuarioLogado);
		vReserva.setId_destinatario(vUsuarioLogado);
		vReserva.setStatus("ATIVO");
		vReserva.setRepeticao("EVENTO UNICO");
		vReserva.setId_recurso(vCtrl.listarRecursoID(Integer.toString(vTipoRecurso_Id)));
		
		vCtrl.InserirReserva(vReserva);
		
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.dataTimePicker();
		this.onShow();
		
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				fecharJanela();
			}
		});
		
		btnReserva.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
                	//ViewReserva vReserva = new ViewReserva();
                	//vReserva.start();
					inserirReserva();
					
                } catch (Exception e) {
                	vAlerta.mensagemAlerta("Erro ao gravar reserva! \n Erro: "+e.getMessage());
                	//vAlerta.mensagemAlerta("Erro ao Abrir Tela de Reserva! \n"+"Erro: "+e.getMessage());                    
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
