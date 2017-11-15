package view.RESERVA;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import view.COMPONENTE_DATAHORA.DateTimePicker;
import model.alerta;
import model.ENTITY.*;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class controllerReserva implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
		
	@FXML
	private ComboBox<String> cbxResponsavel,cbxRecurso,cbxdestinatario,cbxTipoRepeticao,cbxStatus,cbxPesqStatus,cbxPesqAutor,cbxPesqDestinatario;
	
	@FXML
	private DatePicker edtDataHoraInicio,edtDataHoraFinal, dtInicial, dtInicial2, dtFim, dtFim2;
		
	@FXML
	private TableView<reserva> tbGrid;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab ctrlPag1,ctrlPag2;
	
	@FXML
	private VBox vboxReserva; //Para usar o botão dinamico de data e hora
	
	@FXML
	private TextField txtPesqID,txtPesqAutor,txtPesqDestinatario,txtCadHoraInicio,txtCadHoraFim,txtFimHoraInicio,txtFimHoraFim,txtHoraInicio,txtHoraFim;
			
	//Variavel local
	private TableColumn<reserva, Integer> tbColum1 = new TableColumn<reserva, Integer>(); 		
	//private TableColumn<reserva, Integer> tbColum2 = new TableColumn<reserva, Integer>(); 
	private TableColumn<reserva, Integer> tbColum3 = new TableColumn<reserva, Integer>(); 
	private TableColumn<reserva, String> tbColum4  = new TableColumn<reserva, String>(); 
	private TableColumn<reserva, String> tbColum5  = new TableColumn<reserva, String>(); 
	private TableColumn<reserva, String> tbColum6  = new TableColumn<reserva, String>(); 
	
	DateTimePicker dataTimeAutor = new DateTimePicker();
	DateTimePicker dataTimeDestinatario = new DateTimePicker();
	DateTimePicker dataTimeListagem = new DateTimePicker();
	DateTimePicker dataTimePesqDataInicial = new DateTimePicker();
	DateTimePicker dataTimePesqDataFinal = new DateTimePicker();
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
        
    int vTipoComboBox = 0;

    public void Filtro(){
    	String vDataInicio;
    	String vDataFim;
    	String vDataInicio2;
    	String vDataFim2;
    	String vStatus;
    	String vAutor;
    	String vDestinatario;
    	
    	try {
    		vDataInicio = dtInicial.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+" "+txtCadHoraInicio.getText();
        	vDataFim = dtFim.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+" "+txtCadHoraFim.getText();
		} catch (Exception e) {
			// TODO: handle exception
			vDataInicio = "";
			vDataFim = "";
		}
    	
    	try {
    		vDataInicio2 = dtInicial2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+" "+txtFimHoraInicio.getText();
        	vDataFim2 = dtFim2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+" "+txtFimHoraFim.getText();	
		} catch (Exception e) {
			// TODO: handle exception
			vDataInicio2 = "";
			vDataFim2 = "";
		}
 	        	    	
    	try {
    		String vNull = null;
    		
    		vStatus = cbxPesqStatus.getValue();
    		if ( vStatus == vNull ) {
    			vStatus = "";
    		}
		} catch (Exception e) {
			// TODO: handle exception
			vStatus = "";
		}
    	    	
    	try {
    		vAutor = cbxPesqAutor.getValue();
    		vAutor = vAutor.substring(0, vAutor.indexOf(" ")).trim();
		} catch (Exception e) {
			// TODO: handle exception
			vAutor = "";
		}
    	
    	try {
    		vDestinatario = cbxPesqDestinatario.getValue();
    		vDestinatario = vDestinatario.substring(0, vDestinatario.indexOf(" ")).trim();
		} catch (Exception e) {
			// TODO: handle exception
			vDestinatario = "";
		}
    	    	
    	ObservableList<reserva> vLista = FXCollections.observableArrayList(vCtrl.ListaFiltrosReserva(txtPesqID.getText(), vDataInicio, vDataFim, vDataInicio2, vDataFim2, vStatus, vAutor, vDestinatario ) );
    	tbGrid.setItems(vLista);
    
    }
     
    public void excluir() {
    	
    	vAlerta.alertaConfirmacao("Deseja realmente Excluir?");
		Optional<ButtonType> result = vAlerta.getResult();
		if (result.get() == ButtonType.OK) {
    	
	    	reserva vReserva = tbGrid.getSelectionModel().getSelectedItem();
	    	
	    	int vTabelaRemover = tbGrid.getSelectionModel().getSelectedIndex();
	    	
	    	vCtrl.excluirReserva(vReserva);
	    	tbGrid.getItems().remove(vTabelaRemover);
		}
    }
    
    public void dataTimePicker() {                                  //insere o botão dinamico de data e hora
    	vboxReserva.setSpacing(05);
    	vboxReserva.getChildren().add(dataTimeAutor);
    	vboxReserva.getChildren().add(dataTimeDestinatario);
    }
    
    @SuppressWarnings("unchecked")
	public void inserirTabela(){
    	    	
    	try {
    		tbColum1.setText("Código Reserva");/*SETA O TITULO DA GRID*/
        	//tbColum2.setText("Responsável");
        	tbColum3.setText("Destinatario");
        	tbColum4.setText("Data Reserva");
        	tbColum5.setText("Data Fim ");
        	tbColum6.setText("Status");
    		
    	    tbColum1.setCellValueFactory(new PropertyValueFactory<reserva, Integer>("id"));/*SETA QUAL CAMPO DA LISTA*/
        	//tbColum2.setCellValueFactory(new PropertyValueFactory<reserva, Integer>("id_responsavel"));//PENSAR COMO MOSTRAR O NOME DO USUARIO
        	tbColum3.setCellValueFactory(new PropertyValueFactory<reserva, Integer>("id_destinatario"));
        	tbColum4.setCellValueFactory(new PropertyValueFactory<reserva, String>("data_hora_reserva"));
        	tbColum5.setCellValueFactory(new PropertyValueFactory<reserva, String>("data_hora_final"));
        	tbColum6.setCellValueFactory(new PropertyValueFactory<reserva, String>("status"));
        	        	
        	tbGrid.getColumns().addAll(tbColum1,tbColum3,tbColum4,tbColum5,tbColum6);
        	
        	ObservableList<reserva> vLista = FXCollections.observableArrayList(vCtrl.ListaReserva());
        	
        	tbGrid.setItems(vLista);
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: "+e.getMessage());
		}		
		    	
    }
    
   // funçao para copiar o ID que esta na comboBox
   // String nome = "Vinícius Mendonça";
   // String primeiroNome = nome.substring(0, nome.indexOf(" "));
   
    public void fecharJanela() {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
    	stage.close();
    }
    
    public void alimentaTabela() {
    	ObservableList<reserva> vReservaLista = FXCollections.observableArrayList(vCtrl.ListaReserva());
		
		tbGrid.setItems(vReservaLista);
    }
    
    public void inserirReserva(){

    	reserva vReserva = new reserva();
    	
    	String vDestinatario = cbxdestinatario.getValue();
    	String vRecurso = cbxRecurso.getValue();
    	String vDataInicio = dataTimeAutor.getTextField().getText();
    	String vDataFinal = dataTimeDestinatario.getTextField().getText();
    	
    	int vUsuarioLogado = vCtrl.ListarUsuarioLogado();
    	 	
    	vReserva.setId_responsavel(vUsuarioLogado);
    	vReserva.setId_destinatario(Integer.parseInt(vDestinatario.substring(0, vDestinatario.indexOf(" ")).trim() ));
    	vReserva.setId_recurso(Integer.parseInt(vRecurso.substring(0, vRecurso.indexOf(" ")).trim() ));
    	vReserva.setRepeticao(cbxTipoRepeticao.getValue());
    	vReserva.setStatus("ATIVO");
    	vReserva.setData_hora_reserva(vDataInicio);
    	vReserva.setData_hora_final(vDataFinal);
    	
    	vCtrl.InserirReserva(vReserva);
    	
    }
    
    public void preencherRecurso(List<recurso> rec) throws Exception{
    	String vListaRecurso = "";
    	
    	for (recurso vrec : rec){
    		vListaRecurso = Integer.toString(vrec.getId())+" - "+vrec.getNomeTipoRecurso();
    		cbxRecurso.getItems().addAll(vListaRecurso);    		
    	}
    	
    }
    
    public void preencherUser(List<usuario> user) throws Exception{
    	    	
    	String vListaUsuario = "";
    	    	
        for (usuario user3 : user){
        	if (vTipoComboBox == 1) {
	        	vListaUsuario = Integer.toString(user3.getId())+" - "+user3.getNome();
	        	cbxResponsavel.getItems().addAll(vListaUsuario);
	        	cbxPesqAutor.getItems().addAll(vListaUsuario);
        	}
        	if (vTipoComboBox == 2) {
	        	vListaUsuario = Integer.toString(user3.getId())+" - "+user3.getNome();
	        	cbxdestinatario.getItems().addAll(vListaUsuario);
	        	cbxPesqDestinatario.getItems().addAll(vListaUsuario);
        	}
        }
        
    }
    
    public void aliementaComboBoxRecurso(){
    	try {
			cbxRecurso.setTooltip(new Tooltip());
			preencherRecurso(vCtrl.ListaRecurso());
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Recurso!");
		}
    }
    
    public void alimentaComboBoxResponsavel(){
    	try {
    		vTipoComboBox = 1;
    		cbxResponsavel.setTooltip(new Tooltip());
    		cbxPesqAutor.setTooltip(new Tooltip());
    		cbxPesqAutor.getItems().addAll(" ");
    		
    		preencherUser(vCtrl.ListaUsuario());    
    		
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Responsavel!");
		} 
    	
    }
    
    public void alimentaComboBoxDestinatario(){
    	try {
    		vTipoComboBox = 2;
    		cbxdestinatario.setTooltip(new Tooltip());
    		cbxPesqDestinatario.setTooltip(new Tooltip());
    		cbxPesqDestinatario.getItems().addAll(" ");
    		
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
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA TERÇA");
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
    		
    		cbxPesqStatus.setTooltip(new Tooltip());
    		cbxPesqStatus.getItems().addAll("  ");
    		cbxPesqStatus.getItems().addAll("ATIVO");
    		cbxPesqStatus.getItems().addAll("PENDENTE");
    		cbxPesqStatus.getItems().addAll("CONCLUIDO");
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Status!");
		}
    }
    
    public void AlimetaComboBox(){/*Carrega as ComboBox*/
    	alimentaComboBoxResponsavel();
		alimentaComboBoxDestinatario();
		alimentaComboBoxTipoRepeticao();
		alimentaComboBoxStatus();	
		aliementaComboBoxRecurso();
    }
    
    public void onShow(){
    	this.AlimetaComboBox();
    	this.inserirTabela();
    	this.ControlaBotao("novo");
    }
    
    public void moverPag1(){
    	alimentaTabela();
    	
    	tabPane.getSelectionModel().select(ctrlPag1);
    }
    
    public void moverPag2(){
    	tabPane.getSelectionModel().select(ctrlPag2);
    }
    
    public void ControlaBotao(String pBotao){
    	switch (pBotao) {
		case "novo":
			btnVoltar.setDisable(true);	
			btnNovo.setDisable(false);
			btnExcluir.setDisable(false);
			btnImprimir.setDisable(true);
			btnSalvar.setDisable(true);
			btnSair.setDisable(false);
			break;
		case "voltar":
			btnVoltar.setDisable(false);	
			btnNovo.setDisable(true);
			btnExcluir.setDisable(true);
			btnImprimir.setDisable(true);
			btnSalvar.setDisable(false);
			btnSair.setDisable(false);			
			break;
	
		default:
			break;
		}
    }
  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		this.dataTimePicker();
		this.onShow();	
				
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
		
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				fecharJanela();
			}
		});
		
		btnFiltrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Filtro();
			}
		});
		
		btnVoltar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub				
				moverPag1();
				ControlaBotao("novo");				
			}
		});
		
		btnNovo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moverPag2();
				ControlaBotao("voltar");
			}
		});
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				inserirReserva();
				
			}
		});
		
		
	}


}
