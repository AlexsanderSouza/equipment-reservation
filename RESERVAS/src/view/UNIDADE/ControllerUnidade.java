package view.UNIDADE;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import model.Alerta;
import model.MaskFild;
import model.ENTITY.Instituicao;
import model.ENTITY.Unidade;
import service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerUnidade implements Initializable{

	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
    private TextField txtNome,txtEmail,txtTelefone,txtEndereco, txtIdPesquisa, txtNomePesquisa;
	
	@FXML
	private Tab ctrlPag1, ctrlPag2;

	@FXML
	private TabPane tabPane;

	@FXML
	private TableView<Unidade> tbGrid;

	@FXML
	private ComboBox<String> ccBoxInstituicao;
	
	@FXML
	private CheckBox chkAtivo;
	
	// Variavel local
		private Unidade vUnidadeSelecionado; // varialvel usada para pegar o id do objeto que foi selecionado na tabela e
											// alterar ao salvar
		private String vSalvar = ""; // variavel de validação para quando clicar em novo o botaõ salvar volta a

		private TableColumn<Unidade, Integer> tbColum1 = new TableColumn<Unidade, Integer>();
		private TableColumn<Unidade, String> tbColum2 = new TableColumn<Unidade, String>();
		private TableColumn<Unidade, String> tbColum3 = new TableColumn<Unidade, String>();
		private TableColumn<Unidade, String> tbColum4 = new TableColumn<Unidade, String>();
		private TableColumn<Unidade, String> tbColum5 = new TableColumn<Unidade, String>();
		private TableColumn<Unidade, String> tbColum6 = new TableColumn<Unidade, String>();
		
		MaskFild mask = new MaskFild() {
		};
		
	Service vCtrl = new Service();
    Alerta vAlerta = new Alerta();
	
    @SuppressWarnings("unchecked")
	public void inserirTabela() {

		try {
			tbColum1.setText("Id");/* SETA O TITULO DA GRID */
			tbColum2.setText("Nome");
			tbColum3.setText("E-mail");
			tbColum4.setText("Telefone");
			tbColum5.setText("Endereco");
			tbColum6.setText("Instituicao");
		

			tbColum1.setCellValueFactory(new PropertyValueFactory<Unidade, Integer>("id")); /* SETA QUAL CAMPO DA LISTA */
			tbColum2.setCellValueFactory(new PropertyValueFactory<Unidade, String>("nome"));
			tbColum3.setCellValueFactory(new PropertyValueFactory<Unidade, String>("email"));
			tbColum4.setCellValueFactory(new PropertyValueFactory<Unidade, String>("telefone"));
			tbColum5.setCellValueFactory(new PropertyValueFactory<Unidade, String>("endereco"));
			tbColum6.setCellValueFactory(new PropertyValueFactory<Unidade, String>("instituicao"));
			

			tbGrid.getColumns().addAll(tbColum1, tbColum2, tbColum3, tbColum4, tbColum5, tbColum6);

			ObservableList<Unidade> vLista = FXCollections.observableArrayList(vCtrl.ListaUnidade());

			tbGrid.setItems(vLista);

		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: " + e.getMessage());
		}

	}
            
    public void inserirUnidade() {
		String[] instituicaoSelecionada;
		instituicaoSelecionada = ccBoxInstituicao.getSelectionModel().getSelectedItem().split(" ");

		 Unidade vUnidade = new Unidade();
		 
	        vUnidade.setNome(txtNome.getText());
	        vUnidade.setEmail(txtEmail.getText());        
	        vUnidade.setTelefone(txtTelefone.getText());
	        vUnidade.setEndereco(txtEndereco.getText()); 
		    vUnidade.setAtivo(chkAtivo.isSelected());
		    vUnidade.setInstituicao(instituicaoSelecionada[0]);

		if(this.vSalvar.equals("novo") && mask.emailField(txtEmail)) {
			int lastId = vCtrl.InserirUnidade(vUnidade);
			System.out.println(lastId);
    	}else if(this.vSalvar.equals("alterar") && mask.emailField(txtEmail)) {
    		Alerta vMsg = new Alerta();
    		vMsg.alertaConfirmacao("Deseja realmente alterar?");
    		@SuppressWarnings("unused")
			Optional<ButtonType> result = vMsg.getResult();
    		vUnidade.setId(vUnidadeSelecionado.getId());
    		vCtrl.alterarUnidade(vUnidade);
    	}
		
		txtNome.clear();
        txtEmail.clear();
        txtTelefone.clear();
        txtEndereco.clear();
        chkAtivo.setSelected(true);
	}
	
	public void alimentaCcBoxInstituicao() {
		List<Instituicao> instituicao = vCtrl.ListaInstituicao();
		for (Instituicao aux : instituicao) {
			ccBoxInstituicao.getItems().add(aux.getId() + " - " + aux.getNome());
		}

	}
	
	public void filtrar() {
		Integer aux;

		 try {
		 aux = Integer.parseInt(txtIdPesquisa.getText());
		 } catch (NumberFormatException e) {
		 // TODO Auto-generated catch block
		 aux = null;
		 }
		
		 ObservableList<Unidade> vLista = FXCollections.observableArrayList(vCtrl.filtrarunidade(aux,txtNomePesquisa.getText()));
		
		 tbGrid.setItems(vLista);

	}
	
	
	public void excluir() {
		int attTabela = tbGrid.getSelectionModel().getSelectedIndex();
		Unidade vUnidadeSelecionada = tbGrid.getSelectionModel().getSelectedItem();
		
		vCtrl.excluirUnidade(vUnidadeSelecionada);
		tbGrid.getItems().remove(attTabela);
	}

	public void moverPag1() {
		
						
		ObservableList<Unidade> vLista = FXCollections.observableArrayList(vCtrl.ListaUnidade());
		tbGrid.setItems(vLista);
		
		tabPane.getSelectionModel().select(ctrlPag1);
	}

	
	public void moverPag2() {
		tabPane.getSelectionModel().select(ctrlPag2);
	}

	public void alteraVariavelControle(String pTipo) {
    	this.vSalvar = pTipo;
    }
	
	public void ControlaBotao(String pBotao) {
		switch (pBotao) {
		case "novo":
			btnVoltar.setDisable(true);
			btnNovo.setDisable(false);
			btnAlterar.setDisable(false);
			btnExcluir.setDisable(false);
			btnImprimir.setDisable(true);
			btnSalvar.setDisable(true);
			btnSair.setDisable(false);
			break;
		case "voltar":
			btnVoltar.setDisable(false);
			btnNovo.setDisable(true);
			btnAlterar.setDisable(true);
			btnExcluir.setDisable(true);
			btnImprimir.setDisable(true);
			btnSalvar.setDisable(false);
			btnSair.setDisable(false);
			chkAtivo.setSelected(true);
			break;

		default:
			break;
		}
	}
	
	
	public void alterarDados() {
		 vUnidadeSelecionado = tbGrid.getSelectionModel().getSelectedItem();
		 
		 txtNome.setText(vUnidadeSelecionado.getNome());
		 txtEmail.setText(vUnidadeSelecionado.getEmail());
		 txtTelefone.setText(vUnidadeSelecionado.getTelefone());
		 txtEndereco.setText(vUnidadeSelecionado.getEndereco());
		 chkAtivo.setSelected(vUnidadeSelecionado.getAtivo());

	}
	
	public void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}
	@SuppressWarnings("static-access")
	public void txtMaskTelefone() {
		mask.telefoneField(this.txtTelefone);
	}
	
	public void onShow() {
		this.tabPane.setTabMaxHeight(-1);
		this.tabPane.setTabMaxWidth(-1);
		
		this.alimentaCcBoxInstituicao();
		txtTelefone.setPromptText("(99) 99999-9999");
		txtEmail.setPromptText("exemplo@gmail.com");
		this.inserirTabela();
		this.ControlaBotao("novo");
		this.alteraVariavelControle("novo");
		this.txtMaskTelefone();

	}
	
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO

		this.onShow();

		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					inserirUnidade();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});

		ccBoxInstituicao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					//alimentaListViewPermissao();

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});

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

		btnNovo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				alteraVariavelControle("novo");
				moverPag2();
				ControlaBotao("voltar");				
			}
		});

		btnVoltar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				alteraVariavelControle("novo");
				moverPag1();
				ControlaBotao("novo");
			}
		});

		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				fecharJanela();
			}
		});

		btnAlterar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				alteraVariavelControle("alterar");
				alterarDados();
				moverPag2();
				ControlaBotao("voltar");				
			}
		});
		btnFiltrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				filtrar();
			}
		});
	}

}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public TableColumn<unidade, String> getTbColum2() {
		return tbColum2;
	}

	public void setTbColum2(TableColumn<unidade, String> tbColum2) {
		this.tbColum2 = tbColum2;
	}

	public TableColumn<unidade, String> getTbColum3() {
		return tbColum3;
	}

	public void setTbColum3(TableColumn<unidade, String> tbColum3) {
		this.tbColum3 = tbColum3;
	}

	public TableColumn<unidade, String> getTbColum4() {
		return tbColum4;
	}

	public void setTbColum4(TableColumn<unidade, String> tbColum4) {
		this.tbColum4 = tbColum4;
	}

	public TableColumn<unidade, String> getTbColum5() {
		return tbColum5;
	}

	public void setTbColum5(TableColumn<unidade, String> tbColum5) {
		this.tbColum5 = tbColum5;
	}

	public String getSalvar() {
		return salvar;
	}

	public void setSalvar(String salvar) {
		this.salvar = salvar;
	}

	public unidade getvUnidadeSelecionado() {
		return vUnidadeSelecionado;
	}

	public void setvUnidadeSelecionado(unidade vUnidadeSelecionado) {
		this.vUnidadeSelecionado = vUnidadeSelecionado;
	}

}
*/
