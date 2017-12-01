/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.USUARIO;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import model.Alerta;
import model.MaskFild;
import model.ENTITY.Funcao;
import model.ENTITY.Permissao;
import model.ENTITY.Usuario;
import service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.event.EventHandler;

/**
 *
 * @author alexsandersouza
 */
public class ControllerUsuario implements Initializable {

	@FXML
	private Button btnVoltar, btnNovo, btnAlterar, btnExcluir, btnSalvar, btnImprimir, btnSair, btnFiltrar;

	@FXML
	private TextField txtMatricula, txtNome, txtEmail, txtTelefone, txtIdPesquisa, txtNomePesquisa,
			txtMatriculaPesquisa;
	@FXML
	private PasswordField passSenha, passConfirmarSenha;

	@FXML
	private Tab ctrlPag1, ctrlPag2;

	@FXML
	private TabPane tabPane;

	@FXML
	private TableView<Usuario> tbGrid;

	@FXML
	private ComboBox<Funcao> cBoxFuncao;

	@FXML
	private ComboBox<String> cBoxStatus;

	@FXML
	private ComboBox<Permissao> cBoxPermissao;

	@FXML
	ListView<Permissao> listViewPermissao;

	@FXML
	private CheckBox chkAtivo;

	// Variavel local
	private Usuario vUsuarioSelecionado; // varialvel usada para pegar o id do objeto que foi selecionado na tabela e
											// alterar ao salvar
	private String vSalvar = ""; // variavel de validação para quando clicar em novo o botaõ salvar volta a

	private TableColumn<Usuario, Integer> tbColum1 = new TableColumn<Usuario, Integer>();
	private TableColumn<Usuario, String> tbColum2 = new TableColumn<Usuario, String>();
	private TableColumn<Usuario, String> tbColum3 = new TableColumn<Usuario, String>();
	private TableColumn<Usuario, String> tbColum4 = new TableColumn<Usuario, String>();
	private TableColumn<Usuario, String> tbColum5 = new TableColumn<Usuario, String>();
	private TableColumn<Usuario, String> tbColum6 = new TableColumn<Usuario, String>();
	private TableColumn<Usuario, String> tbColum7 = new TableColumn<Usuario, String>();

	MaskFild mask = new MaskFild() {
	};
	Service vCtrl = new Service();
	Alerta vAlerta = new Alerta();
	
	Callback cellFactoryFuncao = new Callback<ListView<Funcao>, ListCell<Funcao>>() {
		@Override
		public ListCell<Funcao> call(ListView<Funcao> param) {
			final ListCell<Funcao> cell = new ListCell<Funcao>() {
				// {
				// super.setPrefWidth(100);
				// }

				public void updateItem(Funcao item, boolean empty) {
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

	Callback cellFactoryPermissao = new Callback<ListView<Permissao>, ListCell<Permissao>>() {
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

	@SuppressWarnings("unchecked")
	public void inserirTabela() {

		try {
			tbColum1.setText("Id");/* SETA O TITULO DA GRID */
			tbColum2.setText("Matricula");
			tbColum3.setText("Nome");
			tbColum4.setText("Função");
			tbColum5.setText("E-mail");
			tbColum6.setText("Telefone");
			tbColum7.setText("Status");

			tbColum1.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("id")); /* SETA QUAL CAMPO DA LISTA */
			tbColum2.setCellValueFactory(new PropertyValueFactory<Usuario, String>("matricula"));
			tbColum3.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
			tbColum4.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nomeFuncao"));
			tbColum5.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));
			tbColum6.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefone"));
			tbColum7.setCellValueFactory(new PropertyValueFactory<Usuario, String>("status"));

			tbGrid.getColumns().addAll(tbColum1, tbColum2, tbColum3, tbColum4, tbColum5, tbColum6, tbColum7);

			ObservableList<Usuario> vLista = FXCollections.observableArrayList(vCtrl.ListaUsuario());

			tbGrid.setItems(vLista);

		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: " + e.getMessage());
		}

	}

	public void inserirUsuario() {
		Funcao funcaoSelecionada = new Funcao();
		funcaoSelecionada = cBoxFuncao.getSelectionModel().getSelectedItem();
		List<Permissao> vListViewPermissao = listViewPermissao.getItems();
		Usuario vUsuario = new Usuario();
		
		vUsuario.setId_funcao(funcaoSelecionada.getId());
		vUsuario.setNome(txtNome.getText());
		vUsuario.setEmail(txtEmail.getText());
		vUsuario.setSenha(passSenha.getText());
		vUsuario.setMatricula(txtMatricula.getText());
		vUsuario.setTelefone(txtTelefone.getText());
		vUsuario.setAtivo(chkAtivo.isSelected());

		vUsuario.setStatus(cBoxStatus.getSelectionModel().getSelectedItem().toString());

		if (passSenha.getText().equals(passConfirmarSenha.getText())) {
			if (this.vSalvar.equals("novo") && mask.emailField(txtEmail)) {
				int lastId = vCtrl.InserirUsuario(vUsuario);
				System.out.println(lastId);
				for (Permissao aux : vListViewPermissao) {
					vCtrl.inserirUsuarioPermissao(aux.getId(), lastId);
				}
			} else if (this.vSalvar.equals("alterar") && mask.emailField(txtEmail)) {
				Alerta vMsg = new Alerta();
				vMsg.alertaConfirmacao("Deseja realmente alterar?");
				Optional<ButtonType> result = vMsg.getResult();

				if (result.get() == ButtonType.OK) {
					vUsuario.setId(vUsuarioSelecionado.getId());
					vCtrl.excluirUsuarioPermissao(vUsuario);
					for (Permissao aux : vListViewPermissao) {
						vCtrl.inserirUsuarioPermissao(aux.getId(), vUsuario.getId());
					}
					vCtrl.alterarUsuario(vUsuario);
				}
			}
		} else {
			vAlerta.mensagemAlerta("Senhas diferentes \n ERRO ");
		}

	}

	public void alimentaCBoxStatus() {
		cBoxStatus.getItems().addAll("Ok", "Pendente", "Suspenso");
	}

	public void alimentaCcBoxFuncao() {
		cBoxFuncao.getItems().clear();
		cBoxFuncao.getItems().addAll(vCtrl.ListaFuncao());
		cBoxFuncao.setButtonCell((ListCell) cellFactoryFuncao.call(null));
		cBoxFuncao.setCellFactory(cellFactoryFuncao);

	}

	public void alimentacBoxPermissao() {
		cBoxPermissao.getItems().clear();
		cBoxPermissao.getItems().add(null);
		cBoxPermissao.getItems().addAll(vCtrl.ListaPermissao());
		cBoxPermissao.setButtonCell((ListCell) cellFactoryPermissao.call(null));
		cBoxPermissao.setCellFactory(cellFactoryPermissao);
	}

	public void alimentaListViewPermissaoFuncao() {
		listViewPermissao.getItems().clear();
		if (cBoxFuncao.getSelectionModel().getSelectedItem() != null) {
			System.out.println(cBoxFuncao.getSelectionModel().getSelectedIndex());
			boolean duplicados = false;
			Funcao vCBoxFuncao = new Funcao();

			vCBoxFuncao = cBoxFuncao.getSelectionModel().getSelectedItem();
			List<Permissao> listaPermissaoDaFuncao = vCtrl.listaFuncaoPermissao(vCBoxFuncao.getId());
			this.listViewPermissao.getItems().addAll(listaPermissaoDaFuncao);
			if (vSalvar.equals("alterar")) {
				List<Permissao> listPermissaoUsuarioFuncao = vCtrl.listarPermissaoUsuarioFuncao(
						this.vUsuarioSelecionado.getId_funcao(), this.vUsuarioSelecionado.getId());
				for (Permissao aux : listPermissaoUsuarioFuncao) {
					duplicados = false;
					for (Permissao aux2 : listaPermissaoDaFuncao) {
						if (aux.getId() == aux2.getId()) {
							duplicados = true;
						}
					}
					if (duplicados == false) {
						this.listViewPermissao.getItems().add(aux);
					}

				}

			}
			listViewPermissao.setCellFactory(null);
			listViewPermissao.setCellFactory(cellFactoryPermissao);
		}
	}

	public void alimentaListViewPermissao() {
		Permissao listaCcBox;
		boolean duplicados = false;
		// int qtd = 0; limitar qtd de permissão

		List<Permissao> listPermissao = this.listViewPermissao.getItems();
		listaCcBox = cBoxPermissao.getSelectionModel().getSelectedItem();

		for (Permissao aux : listPermissao) { // atribui o validador de valores duplicados
			// qtd = qtd + 1; limitar qtd de permissão
			if (aux.getId() == listaCcBox.getId()) {
				duplicados = true;
			}
		}

		if (duplicados != true) { // acrescentar && qtd <=5 para limitar qtd de permissão //não deixa a choiceBox
									// acrescertar valores repetidos e não permite um numero maior que 6 permisões
			this.listViewPermissao.getItems().add(listaCcBox);
		}
	}

	public void alimentaListViewPermissaoAlterar() {
		this.listViewPermissao.getItems().clear();
		List<Permissao> listaPermissaoDoUsuario = vCtrl.ListaPermissaoUsuario(vUsuarioSelecionado.getId());
		this.listViewPermissao.getItems().addAll(listaPermissaoDoUsuario);
	}

	public void filtrar() {
		Integer aux;

		try {
			aux = Integer.parseInt(txtIdPesquisa.getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			aux = null;
		}

		ObservableList<Usuario> vLista = FXCollections
				.observableArrayList(vCtrl.filtrarUsuario(aux, txtNomePesquisa.getText(), txtMatricula.getText()));

		tbGrid.setItems(vLista);

	}

	public void excluir() {

		if (this.ctrlPag1.isSelected()) {
			Alerta vMsg = new Alerta();
			vMsg.alertaConfirmacao("Deseja realmente Excluir?");
			Optional<ButtonType> result = vMsg.getResult();
			if (result.get() == ButtonType.OK) {
				int attTabela = tbGrid.getSelectionModel().getSelectedIndex();
				Usuario vUsuarioSelecionado2 = tbGrid.getSelectionModel().getSelectedItem();

				vCtrl.excluirUsuario(vUsuarioSelecionado2);
				tbGrid.getItems().remove(attTabela);
			}
		}
		if (this.ctrlPag2.isSelected()) {// remove objetos da lista de permissões
			List<Permissao> vListaPermissao = vCtrl
					.listaFuncaoPermissao(cBoxFuncao.getSelectionModel().getSelectedItem().getId());
			Permissao vPermissao = listViewPermissao.getSelectionModel().getSelectedItem();
			boolean validaPermissaoFuncao = false;
			for (Permissao aux : vListaPermissao) {
				if (aux.getId() == vPermissao.getId()) {
					validaPermissaoFuncao = true;
				}
			}
			if (validaPermissaoFuncao == false) {
				int listaIndex = listViewPermissao.getSelectionModel().getSelectedIndex();
				listViewPermissao.getItems().remove(listaIndex);
				this.listViewPermissao.setCellFactory(null); // Isso resolve um bug do callBack
				this.listViewPermissao.setCellFactory(cellFactoryPermissao); // Isso resolve um bug do callBack
			} else {
				vAlerta.mensagemAlerta("Não é possivel excluir a permissao originaria de função");
			}
		}
	}

	public void moverPag1() {
		this.listViewPermissao.setCellFactory(null); // Isso resolve um bug do callBack
		this.listViewPermissao.setCellFactory(cellFactoryPermissao); // Isso resolve um bug do callBack
		txtNome.clear();
		txtEmail.clear();
		passSenha.clear();
		txtMatricula.clear();
		txtTelefone.clear();
		chkAtivo.setSelected(true);
		passConfirmarSenha.clear();
		this.listViewPermissao.getItems().clear();

		ObservableList<Usuario> vLista = FXCollections.observableArrayList(vCtrl.ListaUsuario());
		tbGrid.setItems(vLista);

		tabPane.getSelectionModel().select(ctrlPag1);
	}

	public void moverPag2() {
		alimentaCcBoxFuncao(); // remover futuramente e colocar no botão novo e carregalo no alterar
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
			btnExcluir.setDisable(false);
			btnImprimir.setDisable(true);
			btnSalvar.setDisable(false);
			btnSair.setDisable(false);
			chkAtivo.setSelected(true);
			break;

		default:
			break;
		}
	}

	public void carregarDados() {
		int indexFuncao = 0;
		vUsuarioSelecionado = tbGrid.getSelectionModel().getSelectedItem();
		txtNome.setText(vUsuarioSelecionado.getNome());
		txtEmail.setText(vUsuarioSelecionado.getEmail());
		passSenha.setText(vUsuarioSelecionado.getSenha());
		passConfirmarSenha.setText(vUsuarioSelecionado.getSenha());
		txtMatricula.setText(vUsuarioSelecionado.getMatricula());
		txtTelefone.setText(vUsuarioSelecionado.getTelefone());
		chkAtivo.setSelected(vUsuarioSelecionado.getAtivo());
		alimentacBoxPermissao();
		alimentaCcBoxFuncao();
		alimentaListViewPermissaoAlterar();
		Funcao vFuncao = vCtrl.listarFuncao(vUsuarioSelecionado.getId_funcao());
		cBoxFuncao.setPromptText(vFuncao.getId() + " - " + vFuncao.getNome());
		cBoxStatus.setValue(vUsuarioSelecionado.getStatus());
	}

	public void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();

	}

	public void alteraVariavelControle(String pTipo) {
		this.vSalvar = pTipo;
	}

	public void txtMaskTelefone() {
		mask.telefoneField(this.txtTelefone);
	}

	public void txtMaskMatricula() {
		mask.numericField(txtMatricula);
	}

	public void onShow() {
		this.tabPane.setTabMaxHeight(-1);
		this.tabPane.setTabMaxWidth(-1);
		
		this.listViewPermissao.setCellFactory(cellFactoryPermissao);
		passConfirmarSenha.setPromptText("Confirme sua senha");
		txtTelefone.setPromptText("(99) 99999-9999");
		txtEmail.setPromptText("exemplo@gmail.com");
		this.alimentaCBoxStatus();
		this.inserirTabela();
		this.ControlaBotao("novo");
		this.alteraVariavelControle("novo");
		this.txtMaskTelefone();
		this.txtMaskMatricula();
		// estudar isso depois cBoxFuncao.getItems().contains(vUsuarioSelecionado);

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO

		onShow();

		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					inserirUsuario();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});

		cBoxFuncao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					alimentaListViewPermissaoFuncao();

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
				alimentacBoxPermissao();
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
				moverPag2();
				carregarDados();
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
