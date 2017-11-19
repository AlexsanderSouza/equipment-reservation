package view.PERMISSAO;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import model.Alerta;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerPermissao implements Initializable {

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
	private TableView<Permissao> tbGrid;

	// Variavel local

	private Permissao vPermissaoSelecionada;
	
	private String vSalvar = "";
	
	private TableColumn<Permissao, Integer> tbColum1 = new TableColumn<Permissao, Integer>();
	private TableColumn<Permissao, String> tbColum2 = new TableColumn<Permissao, String>();
	private TableColumn<Permissao, String> tbColum3 = new TableColumn<Permissao, String>();

	Service vCtrl = new Service();
	Alerta vAlerta = new Alerta();

	@SuppressWarnings("unchecked")
	public void inserirTabela() {

		try {
			tbColum1.setText("Id");/* SETA O TITULO DA GRID */
			tbColum2.setText("Nome");
			tbColum3.setText("Descri��o");

			tbColum1.setCellValueFactory(new PropertyValueFactory<Permissao, Integer>("id"));/* SETA QUAL CAMPO DA LISTA */
			tbColum2.setCellValueFactory(new PropertyValueFactory<Permissao, String>("nome"));// PENSAR COMO MOSTRAR O
																								// NOME DO USUARIO
			tbColum3.setCellValueFactory(new PropertyValueFactory<Permissao, String>("descricao"));

			tbGrid.getColumns().addAll(tbColum1, tbColum2, tbColum3);

			ObservableList<Permissao> vLista = FXCollections.observableArrayList(vCtrl.ListaPermissao());

			tbGrid.setItems(vLista);

		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Fun��o Inserir na Tabela: \n Erro: " + e.getMessage());
		}

	}
	
	public void alteraVariavelControle(String pTipo) {
    	this.vSalvar = pTipo;
    }

	public void inserirPermissao() {

		Permissao vPermissao = new Permissao();

		vPermissao.setNome(txtNome.getText());
		vPermissao.setDescricao(txtDescricao.getText());
		vPermissao.setAtivo(chkAtivo.isSelected());

		if (this.vSalvar.equals("novo")) {
			vCtrl.inserirPermissao(vPermissao);
		} else if (this.vSalvar.equals("alterar")) {
			Alerta vMsg = new Alerta();
			vMsg.alertaConfirmacao("Deseja realmente Alterar?");
			Optional<ButtonType> result = vMsg.getResult();

			if (result.get() == ButtonType.OK) {
			vPermissao.setId(vPermissaoSelecionada.getId());
			vCtrl.alterarPermissao(vPermissao);
		}
		}

		txtNome.clear();
		txtDescricao.clear();
		chkAtivo.setSelected(true);

	}

	public void filtrar() {
		Integer aux;

		try {
			aux = Integer.parseInt(txtIdPesquisa.getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			aux = null;
		}

		ObservableList<Permissao> vLista = FXCollections
				.observableArrayList(vCtrl.filtrarPermissao(aux, txtNomePesquisa.getText()));

		tbGrid.setItems(vLista);

	}

	public void excluir() {
		Alerta vMsg = new Alerta();
		vMsg.alertaConfirmacao("Deseja realmente Excluir?");
		Optional<ButtonType> result = vMsg.getResult();

		if (result.get() == ButtonType.OK) {
			Permissao vPermissaoSelecionada = tbGrid.getSelectionModel().getSelectedItem();
			int attTabela = tbGrid.getSelectionModel().getSelectedIndex(); // seleciona o index do item a remover

			tbGrid.getItems().remove(attTabela); // atualiza tabela
			vCtrl.excluirPermissaoFuncao(vPermissaoSelecionada);
			vCtrl.excluirPermissaoUsuario(vPermissaoSelecionada);
			vCtrl.excluirPermissao(vPermissaoSelecionada);
		}
	}

	public void moverPag1() {
		ObservableList<Permissao> vLista = FXCollections.observableArrayList(vCtrl.ListaPermissao());

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
		
		vPermissaoSelecionada = tbGrid.getSelectionModel().getSelectedItem();

		txtNome.setText(vPermissaoSelecionada.getNome());
		txtDescricao.setText(vPermissaoSelecionada.getDescricao());
		chkAtivo.setSelected(vPermissaoSelecionada.getAtivo());
	}

	public void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	public void onShow() {
		this.tabPane.setTabMaxHeight(-1);
		this.tabPane.setTabMaxWidth(-1);
		
		this.inserirTabela();
		this.ControlaBotao("novo");
		this.alteraVariavelControle("novo");
	}

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		this.onShow();

		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					inserirPermissao();
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
