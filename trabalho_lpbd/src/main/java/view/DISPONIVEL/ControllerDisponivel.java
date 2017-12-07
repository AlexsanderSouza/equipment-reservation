package view.DISPONIVEL;

import java.net.URL;
import java.util.ResourceBundle;

import model.Alerta;
import model.SomarData;
import model.ENTITY.Disponivel;
import model.ENTITY.Repeticao;
import model.ENTITY.Reserva;
import service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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

	@FXML
 	private ComboBox<String> cbxQtde;
 	
	@FXML
	private DatePicker edtRepDataInicio, edtDataReserva, edtRepDataFinal;
	
	@FXML
	private TextField edtQtdeOcorrer, edtHoraInicio, edtHoraFinal;
	
	@FXML
	private CheckBox chkApos, chkEm, chkRepetir;
		
	private TableColumn<Disponivel, String> tbColum1 = new TableColumn<Disponivel, String>(); 		
	private TableColumn<Disponivel, String> tbColum2 = new TableColumn<Disponivel, String>(); 
//	private TableColumn<Disponivel, String> tbColum3 = new TableColumn<Disponivel, String>();
			
	Service vCtrl = new Service();
	Alerta vAlerta = new Alerta();
	
	String gDataReserva;
	String gHoraInicio, gHoraFim;

	public void fecharJanela() {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
    	stage.close();
    }
	
	public void Filtro(){
		
		Boolean vRepetir = chkRepetir.isSelected();
		
		if (vRepetir == false) {
			
			String vDataInicio;
			String vDataFinal;
			try {		
				 vDataInicio = edtDataReserva.getValue().toString()+" "+edtHoraInicio.getText();
		    	 vDataFinal  = edtDataReserva.getValue().toString()+" "+edtHoraFinal.getText(); 
			} catch (Exception e) {
				// TODO: handle exception
				vDataInicio = "";
				vDataFinal  = "";
			}		
			ObservableList<Disponivel> vLista = FXCollections.observableArrayList(vCtrl.ListaDisponivel(vDataInicio, vDataFinal));
	    	tbGrid.setItems(vLista);
	    	
		} else {
			int vRepQtdeDias = 0;
			String vRepDataInicio = "";
			int vRepQtdeOcorrencia = 0;
			String vRepDataFim = "";
			String vDataPesquisa = "";
			String vHoraInicio = "";
			String vHoraFim = "";
			
			
			try {
				vHoraInicio = edtHoraInicio.getText();
			} catch (Exception e) {
				// TODO: handle exception
				vHoraInicio = "";
			}
			
			try {
				vHoraFim = edtHoraFinal.getText();
			} catch (Exception e) {
				// TODO: handle exception
				vHoraFim = "";
			}
			
			try {
				vRepQtdeDias = Integer.parseInt(cbxQtde.getValue());
			} catch (Exception e) {
				// TODO: handle exception
				vRepQtdeDias = 0;
			}
			
			try {
				vRepDataInicio = edtRepDataInicio.getValue().toString();
			} catch (Exception e) {
				// TODO: handle exception
				vRepDataInicio = "";
			}
			
			try {
				vRepQtdeOcorrencia = Integer.parseInt(edtQtdeOcorrer.getText());
			} catch (Exception e) {
				// TODO: handle exception
				vRepQtdeOcorrencia = 0;
			}

			try {
				vRepDataFim = edtRepDataFinal.getValue().toString();
			} catch (Exception e) {
				// TODO: handle exception
				vRepDataFim = "";
			}
			
			SomarData vSomaData = new SomarData();
			String vNewData = "";
			
			//Primeiro Caso
			if ((vRepQtdeDias > 0) && (!vRepDataInicio.equals("") && (vRepQtdeOcorrencia > 0))) {
				vDataPesquisa = "'"+edtDataReserva.getValue().toString()+"','"+vRepDataInicio+"'";
				
				for (int i = 0; i < vRepQtdeOcorrencia; i++) {
					
					vNewData = vSomaData.SomaData(vRepDataInicio,vRepQtdeDias);
					vRepDataInicio = vNewData;
					vDataPesquisa = vDataPesquisa+",'" + vNewData+"'";					
				}
				
				//Chamar a função de pesquisa				
				ObservableList<Disponivel> vLista = FXCollections.observableArrayList(vCtrl.ListarRepeticao(vDataPesquisa, vHoraInicio, vHoraFim));
		    	tbGrid.setItems(vLista);
		    	
		    	gDataReserva = vDataPesquisa;
		    	gHoraInicio = vHoraInicio;
		    	gHoraFim = vHoraFim;
		    	
			}
			
			//Segundo Caso -- Faltar Saber como faz comparação entre data, tem de entrar quando DataInicial < DataFinal
//			if ((vRepQtdeDias > 0) && (!vRepDataInicio.equals("") && (!vRepDataFim.equals("")))) {				
//				vDataPesquisa = "'"+edtDataReserva.getValue().toString()+"','"+vRepDataInicio+"'";
//				
//				for (int i = 0; i < 9999 ; i++) {
//
//					if (vRepDataInicio.compareTo(vRepDataFim) != 1) {					
//						vNewData = vSomaData.SomaData(vRepDataInicio,vRepQtdeDias);
//						vRepDataInicio = vNewData;
//						vDataPesquisa = vDataPesquisa+",'" + vNewData+"'";
//					} else {
//						break;
//					}
//				}
//				
//				//Chamar a função de pesquisa				
//				ObservableList<Disponivel> vLista = FXCollections.observableArrayList(vCtrl.ListarRepeticao(vDataPesquisa, vHoraInicio, vHoraFim));
//		    	tbGrid.setItems(vLista);
//		    	
//		    	gDataReserva = vDataPesquisa;
//		    	gHoraInicio = vHoraInicio;
//		    	gHoraFim = vHoraFim;
//				
//			}
			
		}
    	
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
		
		AlimentarComboBoxRepeticao();
	}
	
	public void inserirReserva(){
		
		Boolean vRepetir = chkRepetir.isSelected();
		
		if (vRepetir == false) {
		
			String vDataInicio;
			String vDataFinal;
			
			Reserva vReserva = new Reserva();
			
			try {
			
			 vDataInicio = edtDataReserva.getValue().toString()+" "+edtHoraInicio.getText();
	    	 vDataFinal  = edtDataReserva.getValue().toString()+" "+edtHoraFinal.getText(); 
	    	
	    	 vReserva.setData_hora_reserva(vDataInicio);
	 		 vReserva.setData_hora_final(vDataFinal);
				
			} catch (Exception e) {
				// TODO: handle exception
				vAlerta.mensagemAlerta("Obrigatorio Informar a Data da Reserva!");
				
			}
	    	
	    	int vUsuarioLogado = vCtrl.ListarUsuarioLogado();
	    	    	
	    	Disponivel vDisponivel = (tbGrid.getSelectionModel().getSelectedItem());
		    
			int vTipoRecurso_Id = Integer.parseInt(vDisponivel.getTipo().substring(0, vDisponivel.getTipo().indexOf(" ")).trim() );
	    				
			vReserva.setId_responsavel(vUsuarioLogado);
			vReserva.setId_destinatario(vUsuarioLogado);
			vReserva.setStatus("ATIVO");
			vReserva.setRepeticao("EVENTO UNICO");
			vReserva.setId_recurso(vCtrl.listarRecursoID(Integer.toString(vTipoRecurso_Id)));
			
			vCtrl.InserirReserva(vReserva);
		} else {
			vAlerta.mensagemAlerta("Falta fazer a função para inserir a Repetição!");
					
			Reserva vReserva = new Reserva();
			int vUsuarioLogado = vCtrl.ListarUsuarioLogado();
	    	
	    	Disponivel vDisponivel = (tbGrid.getSelectionModel().getSelectedItem());
		    
			int vTipoRecurso_Id = Integer.parseInt(vDisponivel.getTipo().substring(0, vDisponivel.getTipo().indexOf(" ")).trim() );
			
			/*Inicio>> Chamar Faz as Reservas*/
			int I = 0;
			String[] vData = gDataReserva.split(",");	
			int Id_Pai = 0;
			int Id_Filho = 0;
			while (true) {
				try {
					String vDataRes = vData[I];
					
					String d = vDataRes.replaceAll("'", "");
					
					vReserva.setData_hora_reserva(d+" "+gHoraInicio);	
					vReserva.setData_hora_final(d+" "+gHoraFim);									
					vReserva.setId_responsavel(vUsuarioLogado);
					vReserva.setId_destinatario(vUsuarioLogado);
					vReserva.setStatus("ATIVO");
					vReserva.setRepeticao("EVENTO UNICO");
					vReserva.setId_recurso(vCtrl.listarRecursoID(Integer.toString(vTipoRecurso_Id)));
					vReserva.setDataReserva(d);
					vReserva.setHoraReservaInicio(gHoraInicio);
					vReserva.setHoraReservaFim(gHoraFim);
					
					vCtrl.InserirReservaRepeticao(vReserva);
					
					if (I == 0) {
						//criar a consulta que traz o ultimo registro inserido
						Reserva vReserva2 = new Reserva();
						vReserva2 = vCtrl.listaUltimoResertro();
						Id_Pai = vReserva2.getId();					
					}else {
						// dai faz o insert na tabela de repetição						
						Reserva vReserva2 = new Reserva();
						vReserva2 = vCtrl.listaUltimoResertro();
						Id_Filho = vReserva2.getId();	
						
						Repeticao vRepeticao = new Repeticao();
						vRepeticao.setId_reserva_pai(Id_Pai);
						vRepeticao.setId_reserva_filho(Id_Filho);
						
						vCtrl.InserirRepeticao(vRepeticao);
						
					}
					
					I = I+1;					
					
				} catch (Exception e) {
					// TODO: handle exception
					break;
				}
				
			}
			
			
			
		}
				
	}
	
	public void AlimentarComboBoxRepeticao() {
		cbxQtde.setTooltip(new Tooltip());
		for (int i = 1; i < 8; i++) {
			cbxQtde.getItems().addAll(Integer.toString(i));
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
//		this.dataTimePicker();
		
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
