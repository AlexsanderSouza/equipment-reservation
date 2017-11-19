package view.FUNCAO;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import model.Alerta;
import model.Alerta;
import model.ENTITY.Funcao;
import model.ENTITY.Permissao;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

@SuppressWarnings("unused")
public class ControllerFuncao implements Initializable {

	@FXML
	private Button btnVoltar, btnNovo, btnAlterar, btnExcluir, btnSalvar, btnImprimir, btnSair, btnFiltrar;

	@FXML
	private TextField txtNome, txtDescricao, txtIdPesquisa, txtNomePesquisa;

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab ctrlPag1, ctrlPag2;

	@FXML
	private CheckBox chkAtivo;

	@FXML
	private TableView<Funcao> tbGrid;

	@FXML
	ListView<Permissao> listViewPermissao;

	@FXML
	private ComboBox<Permissao> cBoxPermissao;

	// Variavel local
	private Funcao vFuncaoSelecionada; // varialvel usada para pegar o id do objeto que foi selecionado na tabela e
										// alterar ao salvar
	private String vSalvar = ""; // variavel de validação para quando clicar em novo o botaõ salvar volta a
									// inserir e não a alterar objetos
	private String listTable = "table"; // variavel de validação para o botão excluir saber se deve excluir da tabela ou
										// do listView
	private TableColumn<Funcao, Integer> tbColum1 = new TableColumn<Funcao, Integer>();
	private TableColumn<Funcao, String> tbColum2 = new TableColumn<Funcao, String>();
	private TableColumn<Funcao, String> tbColum3 = new TableColumn<Funcao, String>();

	Service vCtrl = new Service();
	Alerta vAlerta = new Alerta();
	
	Callback cellFactory = new Callback<ListView<Permissao>, ListCell<Permissao>>() {
		@Override
		public ListCell<Permissao> call(ListView<Permissao> param) {
			final ListCell<Permissao> cell = new ListCell<Permissao>() {
				// {
				// super.setPrefWidth(100);
				// }

				public void updateItem(Permissao item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setGraphic(null);
					} else {
						setText(item.getId() + " - " + item.getNome());
					}
					// if (item != null) {
					// setText(item);
					// if (item.contains("11 - aaa")) {
					// setTextFill(Color.RED);
					// }
					// else if (item.contains("Low")){
					// setTextFill(Color.GREEN);
					// }
					// else {
					// setTextFill(Color.BLACK);
					// }
					// }
					// else {
					// setText(null);
					// }
				}
			};
			return cell;
		}
	};
	
	
	public void alimentaCcBoxPermissao() {
		List<Permissao> permissoes = vCtrl.ListaPermissao();
		// for (permissao aux : permissoes) {
		cBoxPermissao.getItems().addAll(permissoes); // aux.getId() + " - " + aux.getNome()
		// }

		
		cBoxPermissao.setButtonCell((ListCell) cellFactory.call(null));
		cBoxPermissao.setCellFactory(cellFactory);
	}

	public void alteraVariavelControle(String pTipo) {
		this.vSalvar = pTipo;
	}

	public void alimentaListViewPermissao() {
		Permissao listaCcBox;
		boolean duplicados = false;
		// int qtd = 0; limitar qtd de permissão

		List<Permissao> listPermissao = listViewPermissao.getItems();

		listaCcBox = cBoxPermissao.getSelectionModel().getSelectedItem();

		for (Permissao aux : listPermissao) { // atribui o validador de valores duplicados
			// qtd = qtd + 1; limitar qtd de permissão
			if (aux.equals(listaCcBox)) {
				duplicados = true;
			}
		}

		if (duplicados != true) { // acrescentar && qtd <=5 para limitar qtd de permissão //não deixa a choiceBox
									// acrescertar valores repetidos e não permite um numero maior que 6 permisões
			listViewPermissao.getItems().add(listaCcBox);
			listViewPermissao.setCellFactory(cellFactory);
		}
	}

	@SuppressWarnings("unchecked")
	public void inserirTabela() {

		try {
			tbColum1.setText("Id");/* SETA O TITULO DA GRID */
			tbColum2.setText("Nome");
			tbColum3.setText("Descrição");

			tbColum1.setCellValueFactory(new PropertyValueFactory<Funcao, Integer>("id")); /* SETA QUAL CAMPO DA LISTA */
			tbColum2.setCellValueFactory(new PropertyValueFactory<Funcao, String>("nome")); // PENSAR COMO MOSTRAR O
																							// NOME DO USUARIO
			tbColum3.setCellValueFactory(new PropertyValueFactory<Funcao, String>("descricao"));

			tbGrid.getColumns().addAll(tbColum1, tbColum2, tbColum3);

			ObservableList<Funcao> vLista = FXCollections.observableArrayList(vCtrl.ListaFuncao());

			tbGrid.setItems(vLista);

		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: " + e.getMessage());
		}

	}

	public void inserirFuncao() {
		Permissao listaCcBox;
		Funcao vFuncao = new Funcao();
		List<Permissao> listPermissao = listViewPermissao.getItems();

		vFuncao.setNome(txtNome.getText());
		vFuncao.setDescricao(txtDescricao.getText());
		vFuncao.setAtivo(chkAtivo.isSelected());

		listaCcBox = cBoxPermissao.getSelectionModel().getSelectedItem();

		if (this.vSalvar.equals("novo")) {
			int lastId = vCtrl.InserirFuncao(vFuncao); // retorna oultimo id da função inserida

			for (Permissao aux : listPermissao) { // atribui o validador de valores duplicados
//				String[] separaId = new String[4];
//				separaId = aux.split(" ");
//				System.out.println(separaId[0]);
				vCtrl.InserirFuncaoPermissao(aux.getId(), lastId); // insere na tabela de relacionamento

			}
		} else if (this.vSalvar.equals("alterar")) {
			Alerta vMsg = new Alerta();
			vMsg.alertaConfirmacao("Deseja realmente Alterar?");
			Optional<ButtonType> result = vMsg.getResult();

			if (result.get() == ButtonType.OK) {
				vFuncao.setId(vFuncaoSelecionada.getId());
				vCtrl.alterarFuncao(vFuncao);
				vCtrl.excluirFuncaoPermissao(vFuncao);

				for (Permissao aux : listPermissao) { // atribui o validador de valores duplicados
//					String[] separaId = new String[4];
//					separaId = aux.split(" ");
//					System.out.println(separaId[0]);
					vCtrl.InserirFuncaoPermissao(aux.getId(), vFuncao.getId()); // insere na tabela de relacionamento

				}
			}
			
		}
		// Limpar dados
		txtNome.clear();
		txtDescricao.clear();
		listViewPermissao.getItems().clear();
		chkAtivo.setSelected(true);
		this.listViewPermissao.setCellFactory(null);                 //Isso resolve um bug do callBack
		this.listViewPermissao.setCellFactory(cellFactory); //Isso resolve um bug do callBack
	}

	public void filtrar() {
		Integer aux;

		try {
			aux = Integer.parseInt(txtIdPesquisa.getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			aux = null;
		}

		ObservableList<Funcao> vLista = FXCollections
				.observableArrayList(vCtrl.filtrarFuncao(aux, txtNomePesquisa.getText()));

		tbGrid.setItems(vLista);

	}

	public void excluir() {
		Alerta vMsg = new Alerta();
		vMsg.alertaConfirmacao("Deseja realmente Excluir?");
		Optional<ButtonType> result = vMsg.getResult();
		boolean val = true;
		if (result.get() == ButtonType.OK) {
			if (listTable.equals("table")) { // remove objetos da lista da tabela
				Funcao vFuncaoSelecionada2 = tbGrid.getSelectionModel().getSelectedItem(); 
				val = vCtrl.excluirFuncao(vFuncaoSelecionada2);
				int attTabela = tbGrid.getSelectionModel().getSelectedIndex();
				if(val == true) {
					tbGrid.getItems().remove(attTabela); //
				}
			} else if (listTable.equals("listView")) {// remove objetos da lista de permissões

				int listaIndex = listViewPermissao.getSelectionModel().getSelectedIndex();
				listViewPermissao.getItems().remove(listaIndex);
			}
		}

	}

	public void moverPag1() {
		this.listViewPermissao.setCellFactory(null);                 //Isso resolve um bug do callBack
		this.listViewPermissao.setCellFactory(cellFactory); //Isso resolve um bug do callBack
		listViewPermissao.getItems().clear();

		this.listTable = "table";

		ObservableList<Funcao> vLista = FXCollections.observableArrayList(vCtrl.ListaFuncao());
		tbGrid.setItems(vLista);

		tabPane.getSelectionModel().select(ctrlPag1);
	}

	public void moverPag2() {
		this.listTable = "listView";

		tabPane.getSelectionModel().select(ctrlPag2);
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
			// btnExcluir.setDisable(true); //não sera necessário para essa classe
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
		vFuncaoSelecionada = tbGrid.getSelectionModel().getSelectedItem();

		txtNome.setText(vFuncaoSelecionada.getNome());
		txtDescricao.setText(vFuncaoSelecionada.getDescricao());
		chkAtivo.setSelected(vFuncaoSelecionada.getAtivo());
		List<Permissao> listaPermissaoDaFuncao = vCtrl.listaFuncaoPermissao(vFuncaoSelecionada.getId());

		
			listViewPermissao.getItems().addAll(listaPermissaoDaFuncao);
			listViewPermissao.setCellFactory(cellFactory);

	}

	public void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	public void onShow() {
		this.tabPane.setTabMaxHeight(-1);
		this.tabPane.setTabMaxWidth(-1);
		
		this.alimentaCcBoxPermissao();
		this.inserirTabela();
		this.ControlaBotao("novo");
		this.alteraVariavelControle("novo");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		onShow();

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

		cBoxPermissao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					alimentaListViewPermissao();

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
