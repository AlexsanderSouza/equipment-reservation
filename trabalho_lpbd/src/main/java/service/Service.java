/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

import model.Alerta;
import model.DAO.DaoDisponivel;
import model.DAO.DaoFuncao;
import model.DAO.DaoFuncaoPermissao;
import model.DAO.DaoInstituicao;
import model.DAO.DaoPermissao;
import model.DAO.DaoRecurso;
import model.DAO.DaoRepeticao;
import model.DAO.DaoReserva;
import model.DAO.DaoRestricaoRecurso;
import model.DAO.DaoTipoRecurso;
import model.DAO.DaoUnidade;
import model.DAO.DaoUsuario;
import model.DAO.DaoUsuarioLogado;
import model.DAO.DaoUsuarioPermissao;
import model.ENTITY.Disponivel;
import model.ENTITY.Funcao;
import model.ENTITY.Instituicao;
import model.ENTITY.Permissao;
import model.ENTITY.Recurso;
import model.ENTITY.Repeticao;
import model.ENTITY.Reserva;
import model.ENTITY.RestricaoRecurso;
import model.ENTITY.TipoRecurso;
import model.ENTITY.Unidade;
import model.ENTITY.Usuario;
import model.ENTITY.UsuarioLogado;

/**
 *
 * @author WigorPaulo
 */
public class Service {

	DaoUsuario vDaoUser = new DaoUsuario();
	DaoUnidade vDaoUnidade = new DaoUnidade();
	DaoFuncao vDaoFuncao = new DaoFuncao();
	DaoInstituicao vDaoInstituicao = new DaoInstituicao();
	DaoReserva vDaoReserva = new DaoReserva();
	DaoRecurso vDaoRecurso = new DaoRecurso();
	DaoTipoRecurso vDaoTipoRecurso = new DaoTipoRecurso();
	DaoDisponivel vDaoDisponivel = new DaoDisponivel();
	DaoPermissao vDaoPermissao = new DaoPermissao();
	DaoFuncaoPermissao vDaoFuncaoPermissao = new DaoFuncaoPermissao();
	DaoRestricaoRecurso vDaoRestricaoRecurso = new DaoRestricaoRecurso();
	DaoUsuarioPermissao vDaoUsuarioPermissao = new DaoUsuarioPermissao();
	DaoUsuarioLogado vDaoUsuarioLogado = new DaoUsuarioLogado();
	DaoRepeticao vDaoRepeticao = new DaoRepeticao();
	
	Alerta vAlerta = new Alerta();
			
	/* INICIO >> Funções LISTAR */
	public int ListarUsuarioLogado(){
		try {
			return vDaoUsuarioLogado.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public List<Permissao> ListaPermissaoUsuario(int pIdUsuario) {
		try {
			return vDaoUsuarioPermissao.listarPermissao(pIdUsuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<Usuario> ValidarLogin(Usuario pUser) {
		try {
			return vDaoUser.ValidaLogin(pUser);
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro no ValidarLogin: \n" + e.getMessage());
			return null;
		}
	}
		
	public List<Disponivel> ListarRepeticao(String pRepData, String pHoraInicio, String pHoraFim) {
		try {
			return vDaoDisponivel.ListarRepeticao(pRepData, pHoraInicio, pHoraFim);
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro no Filtro: \n" + e.getMessage());
			return null;
		}
	}

	public List<Disponivel> ListaDisponivel(String pDataInicio, String pDataFim) {
		try {
			return vDaoDisponivel.listarDisponivel(pDataInicio, pDataFim);
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro no Filtro: \n" + e.getMessage());
			return null;
		}
	}

	public int listarRecursoID(String pTipoRecursoID) {
		try {
			return vDaoDisponivel.listarRecursoID(pTipoRecursoID);
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro no Filtro: \n" + e.getMessage());
			return 0;
		}
	}
	
	public List<Permissao> listaFuncaoPermissao(int pIdFuncao) {
		try {
			return vDaoFuncaoPermissao.listar(pIdFuncao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	public List<Permissao> ListaPermissao() {
		try {
			return vDaoPermissao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<Usuario> ListaUsuario() {
		try {
			return vDaoUser.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<Funcao> ListaFuncao() {
		try {
			return vDaoFuncao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<Instituicao> ListaInstituicao() {
		try {
			return vDaoInstituicao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<Unidade> ListaUnidade() {
		try {
			return vDaoUnidade.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	public List<RestricaoRecurso> ListaRestricaoRecurso() {
		try {
			return vDaoRestricaoRecurso.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<Recurso> ListaRecurso() {
		try {
			return vDaoRecurso.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<Reserva> ListaReserva() {
		try {
			return vDaoReserva.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<Reserva> ListaFiltrosReserva(String pId, String pDataInicio, String pDataFim, String pDataInicio2,
			String pDataFim2, String pStatus, String pResponsavel, String pDestinatario) {
		try {
			return vDaoReserva.listarFiltro(pId, pDataInicio, pDataFim, pDataInicio2, pDataFim2, pStatus, pResponsavel,
					pDestinatario);
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro no Filtro: \n" + e.getMessage());
			return null;
		}
	}

	public List<TipoRecurso> ListaTipoRecurso() {
		try {
			return vDaoTipoRecurso.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

	}
	
	public Funcao listarFuncao(int pIdFuncao) {
		try {
			return vDaoFuncao.listarFuncao(pIdFuncao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public List<Permissao> listarPermissaoUsuarioFuncao(int pIdFuncao, int pIdUsuario){ 
		return vDaoUsuarioPermissao.listarPermissaoUsuarioFuncao(pIdFuncao, pIdUsuario);
	}
	
	/* FIM >> Funções LISTAR */

	/* INICIO >> Funções FILTRAR */
	public List<Usuario> filtrarUsuario(Integer pId, String pNome, String pMatricula) {
		return vDaoUser.filtrar(pId, pNome, pMatricula);

	}

	public List<Permissao> filtrarPermissao(Integer id, String nome) {
		try {
			return vDaoPermissao.filtrar(id, nome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<TipoRecurso> filtrarTipoRecurso(Integer pId, String pNome) {
		return vDaoTipoRecurso.filtrar(pId, pNome);
	}

	public List<Recurso> filtrarRecurso(Integer pIdRecurso, Integer pIdTipoRecurso, Integer pIdUnidade,
			String pEtiqueta, String pObs) {
		return vDaoRecurso.filtrar(pIdRecurso, pIdTipoRecurso, pIdUnidade, pEtiqueta, pObs);
	}

	public List<Funcao> filtrarFuncao(Integer pId, String pNome) {
		return vDaoFuncao.filtrar(pId, pNome);
	}

	public List<Unidade> filtrarunidade(Integer pId, String pNome) {
		return vDaoUnidade.filtrar(pId, pNome);
	}

	public List<Instituicao> filtrarinstituicao(Integer pId, String pNome) {
		return vDaoInstituicao.filtrar(pId, pNome);
	}
	
	public Reserva listaUltimoResertro() {
		return vDaoReserva.listaUltimoResertro();
	}
	/* FIM >> Funções FILTRAR */

	/* INICIO >> Funções ALTERAR */
	public void alterarReserva(Reserva pReserva) {
		vDaoReserva.alterar(pReserva);
	}

	public void alterarPermissao(Permissao pPermissao) {
		vDaoPermissao.alterar(pPermissao);
	}

	public void alterarUsuario(Usuario pUsuario) {
		if(pUsuario.getTelefone().length() < 14) {
			vAlerta.mensagemAlerta("Telefone incompleto");
		}else {
			System.out.println(pUsuario.getTelefone().length());
			vDaoUser.alterar(pUsuario);
		}
		
	}

	public void alterarFuncao(Funcao pFuncao) {
		vDaoFuncao.alterar(pFuncao);
	}

	public void alterarInstituicao(Instituicao pInstituicao) {
		vDaoInstituicao.alterar(pInstituicao);
	}

	public void alterarUnidade(Unidade pUnidade) {
		vDaoUnidade.alterar(pUnidade);
	}

	public void alterarTipoRecurso(TipoRecurso pTipoRecurso) {
		vDaoTipoRecurso.alterar(pTipoRecurso);
	}

	public void alterarRecurso(Recurso pRecurso) {
		vDaoRecurso.alterar(pRecurso);
	}
	
	public void alterarUsuarioLogado(UsuarioLogado pUsuarioLogado) {
		vDaoUsuarioLogado.alterar(pUsuarioLogado);
	}
	
	public void alterarReservaStatus(Reserva pReserva) {
		vDaoReserva.alterarStatus(pReserva);
	}
	/* FIM >> Funções ALTERAR */

	/* INICIO >> Funções INSERIR */
	
	public void InserirRepeticao(Repeticao pRepeticao) {
		vDaoRepeticao.InserirRepeticao(pRepeticao);
	}
	
	public void inserirUsuarioPermissao(int pPermissao, int pLastId) {
		vDaoUsuarioPermissao.inserir(pPermissao, pLastId);
	}

	public void InserirFuncaoPermissao(int pPermissao, int pLastId) {
		vDaoFuncaoPermissao.inserir(pPermissao, pLastId);
	}

	public void inserirPermissao(Permissao pPermissao) {
		vDaoPermissao.inserir(pPermissao);
	}

	public int InserirUsuario(Usuario pUser) {
		if(pUser.getTelefone().length() <14) {
			vAlerta.mensagemAlerta("Erro ao salvar: Telefone incompleto");
			return 0;
		}else {
			return vDaoUser.inserir(pUser);
		}
	}

	public int InserirFuncao(Funcao pFuncao) {
		return vDaoFuncao.inserir(pFuncao);
	}

	public int inserirInstituicao(Instituicao pInstituicao) {
		return vDaoInstituicao.inserir(pInstituicao);
		
	}

	public int InserirUnidade(Unidade pUnidade) {
		return vDaoUnidade.inserir(pUnidade);
	}

	public void InserirRecurso(Recurso pRecurso) {
		vDaoRecurso.inserir(pRecurso);
	}

	public void inserirTipo_Recurso(TipoRecurso pTipoRecurso) {
		vDaoTipoRecurso.inserir(pTipoRecurso);
	}

	public void InserirReserva(Reserva pReserva) {
		vDaoReserva.inserir(pReserva);
	}
	
	public void InserirReservaRepeticao(Reserva pReserva) {
		vDaoReserva.InserirReservaRepeticao(pReserva);
	}

	public void InserirRestricaoRecurso(RestricaoRecurso pRestricaoRecurso) {
		vDaoRestricaoRecurso.inserir(pRestricaoRecurso);
	}
	/* FIM >> Funções INSERIR */

	/* INICIO >> Funções EXCLUIR */
	public void excluirPermissao(Permissao pPermissao) {
		vDaoPermissao.excluir(pPermissao);
	}

	public void excluirRestricaoRecurso(RestricaoRecurso pRestricaoRecurso) {
		vDaoRestricaoRecurso.excluir(pRestricaoRecurso);
	}

	public void excluirReserva(Reserva pReserva) {
		vDaoReserva.excluir(pReserva);
	}

	public void excluirUsuario(Usuario pUsuario) {
		vDaoUser.excluir(pUsuario);
	}

	public boolean excluirFuncao(Funcao pFuncao) {
		return vDaoFuncao.excluir(pFuncao);
	}

	public void excluirInstituicao(Instituicao pInstituicao) {
		vDaoInstituicao.excluir(pInstituicao);
	}

	public void excluirUnidade(Unidade pUnidade) {
		vDaoUnidade.excluir(pUnidade);
	}

	public void excluirTipoRecurso(TipoRecurso pTipoRecurso) {
		vDaoTipoRecurso.excluir(pTipoRecurso);
	}

	public void excluirRecurso(Recurso pRecurso) {
		vDaoRecurso.excluir(pRecurso);
	}

	public void excluirPermissaoUsuario(Permissao pPermissao) {
		vDaoUsuarioPermissao.excluirPermissaoUsuario(pPermissao);
	}

	public void excluirUsuarioPermissao(Usuario pUsuario) {
		vDaoUsuarioPermissao.excluirUsuarioPermissao(pUsuario);
	}

	public void excluirFuncaoPermissao(Funcao pFuncao) {
		vDaoFuncaoPermissao.excluirFuncao(pFuncao);
	}

	public void excluirPermissaoFuncao(Permissao pPermissao) {
		vDaoFuncaoPermissao.excluirPermissao(pPermissao);
	}

	/* FIM >> Funções EXCLUIR */

}
