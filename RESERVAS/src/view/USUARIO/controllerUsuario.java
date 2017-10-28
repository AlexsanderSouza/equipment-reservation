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

import model.alertaConfirmacao;
import model.alertaInformacao;
import model.ENTITY.funcao;
import model.ENTITY.permissao;
import model.ENTITY.usuario;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.EventHandler;

/**
 *
 * @author alexsandersouza
 */
public class controllerUsuario implements Initializable {

	@FXML
	private Button btnVoltar, btnNovo, btnAlterar, btnExcluir, btnSalvar, btnImprimir, btnSair, btnFiltrar;

	@FXML
	private TextField txtMatricula, txtNome, txtEmail, txtSenha, txtTelefone, txtIdPesquisa, txtNomePesquisa,
			txtMatriculaPesquisa;

	@FXML
	private Tab ctrlPag1, ctrlPag2;

	@FXML
	private TabPane tabPane;

	@FXML
	private TableView<usuario> tbGrid;

	@FXML
	private ChoiceBox<String> ccBoxFuncao;

	@FXML
	private ComboBox<String> cBoxStatus;

	@FXML
	ListView<String> listViewPermissao;

	@FXML
	private CheckBox chkAtivo;

	// Variavel local
	private usuario vUsuarioSelecionado; // varialvel usada para pegar o id do objeto que foi selecionado na tabela e
											// alterar ao salvar
	private String salvar = "salvarNovo"; // variavel de validação para quando clicar em novo o botaõ salvar volta a

	private TableColumn<usuario, Integer> tbColum1 = new TableColumn<usuario, Integer>();
	private TableColumn<usuario, String> tbColum2 = new TableColumn<usuario, String>();
	private TableColumn<usuario, String> tbColum3 = new TableColumn<usuario, String>();
	private TableColumn<usuario, String> tbColum4 = new TableColumn<usuario, String>();
	private TableColumn<usuario, String> tbColum5 = new TableColumn<usuario, String>();
	private TableColumn<usuario, String> tbColum6 = new TableColumn<usuario, String>();
	private TableColumn<usuario, String> tbColum7 = new TableColumn<usuario, String>();

	Controller vCtrl = new Controller();
	alertaInformacao vAlerta = new alertaInformacao();

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

			tbColum1.setCellValueFactory(
					new PropertyValueFactory<usuario, Integer>("id")); /* SETA QUAL CAMPO DA LISTA */
			tbColum2.setCellValueFactory(new PropertyValueFactory<usuario, String>("matricula"));
			tbColum3.setCellValueFactory(new PropertyValueFactory<usuario, String>("nome"));
			tbColum4.setCellValueFactory(new PropertyValueFactory<usuario, String>("funcao"));
			tbColum5.setCellValueFactory(new PropertyValueFactory<usuario, String>("email"));
			tbColum6.setCellValueFactory(new PropertyValueFactory<usuario, String>("telefone"));
			tbColum7.setCellValueFactory(new PropertyValueFactory<usuario, String>("status"));

			tbGrid.getColumns().addAll(tbColum1, tbColum2, tbColum3, tbColum4, tbColum5, tbColum6, tbColum7);

			ObservableList<usuario> vLista = FXCollections.observableArrayList(vCtrl.ListaUsuario());

			tbGrid.setItems(vLista);

		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função Inserir na Tabela: \n Erro: " + e.getMessage());
		}

	}

	public void inserirUsuario() {
		String[] funcaoSelecionada = new String[3];
		funcaoSelecionada = ccBoxFuncao.getSelectionModel().getSelectedItem().split(" ");

		usuario vUsuario = new usuario();

		vUsuario.setNome(txtNome.getText());
		vUsuario.setEmail(txtEmail.getText());
		vUsuario.setSenha(txtSenha.getText());
		vUsuario.setMatricula(txtMatricula.getText());
		vUsuario.setTelefone(txtTelefone.getText());
		vUsuario.setAtivo(chkAtivo.isSelected());
		vUsuario.setFuncao(funcaoSelecionada[0]);
		vUsuario.setStatus(cBoxStatus.getSelectionModel().getSelectedItem().toString());

		if (this.salvar.equals("salvarNovo")) {
			vCtrl.InserirUsuario(vUsuario);
		} else if (this.salvar.equals("alterar")) {
			Optional<ButtonType> result = new alertaConfirmacao("Deseja realmente Alterar?").getResult();

			if (result.get() == ButtonType.OK) {
			vUsuario.setId(vUsuarioSelecionado.getId());
			vCtrl.alterarUsuario(vUsuario);
		}
		}

	}

	public void alimentaCBoxStatus() {
		cBoxStatus.getItems().addAll("Ok", "Pendente", "Suspenso");
	}

	public void alimentaCcBoxFuncao() {
		List<funcao> funcao = vCtrl.ListaFuncao();
		for (funcao aux : funcao) {
			ccBoxFuncao.getItems().add(aux.getId() + " - " + aux.getNome());
		}

	}

	public void alimentaListViewPermissao() {
		listViewPermissao.getItems().clear();

		String[] listaCcBox = new String[3];
		funcao pFuncao = new funcao();

		listaCcBox = ccBoxFuncao.getSelectionModel().getSelectedItem().split(" ");

		pFuncao.setId(Integer.parseInt(listaCcBox[0]));

		List<permissao> listaPermissaoDaFuncao = vCtrl.listaFuncaoPermissao(pFuncao.getId());

		for (permissao aux : listaPermissaoDaFuncao) {
			listViewPermissao.getItems().add(aux.getId() + " - " + aux.getNome());
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

		ObservableList<usuario> vLista = FXCollections
				.observableArrayList(vCtrl.filtrarUsuario(aux, txtNomePesquisa.getText(), txtMatricula.getText()));

		tbGrid.setItems(vLista);

	}

	public void excluir() {
		Optional<ButtonType> result = new alertaConfirmacao("Deseja realmente Excluir?").getResult();

		if (result.get() == ButtonType.OK) {
			int attTabela = tbGrid.getSelectionModel().getSelectedIndex();
			usuario vUsuarioSelecionado2 = tbGrid.getSelectionModel().getSelectedItem();

			vCtrl.excluirUsuario(vUsuarioSelecionado2);
			tbGrid.getItems().remove(attTabela);
		}
	}

	public void moverPag1() {
		txtNome.clear();
		txtEmail.clear();
		txtSenha.clear();
		txtMatricula.clear();
		txtTelefone.clear();
		chkAtivo.setSelected(true);

		listViewPermissao.getItems().clear();
		this.salvar = "salvarNovo"; // variavel de validação para quando clicar em novo o botaõ salvar volta a

		ObservableList<usuario> vLista = FXCollections.observableArrayList(vCtrl.ListaUsuario());
		tbGrid.setItems(vLista);

		tabPane.getSelectionModel().select(ctrlPag1);
	}

	public void moverPag2() {
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

		this.salvar = "alterar";

		vUsuarioSelecionado = tbGrid.getSelectionModel().getSelectedItem();

		txtNome.setText(vUsuarioSelecionado.getNome());
		txtEmail.setText(vUsuarioSelecionado.getEmail());
		txtSenha.setText(vUsuarioSelecionado.getSenha());
		txtMatricula.setText(vUsuarioSelecionado.getMatricula());
		txtTelefone.setText(vUsuarioSelecionado.getTelefone());
		chkAtivo.setSelected(vUsuarioSelecionado.getAtivo());

	}

	public void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	public void onShow() {
		this.alimentaCBoxStatus();
		this.alimentaCcBoxFuncao();
		this.inserirTabela();
		this.ControlaBotao("novo");

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

		ccBoxFuncao.setOnAction(new EventHandler<ActionEvent>() {

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
				moverPag2();
				ControlaBotao("voltar");
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
