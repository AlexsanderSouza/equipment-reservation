/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

import model.alerta;
import model.DAO.dao_disponivel;
import model.DAO.dao_funcao;
import model.DAO.dao_funcaoPermissao;
import model.DAO.dao_instituicao;
import model.DAO.dao_permissao;
import model.DAO.dao_recurso;
import model.DAO.dao_reserva;
import model.DAO.dao_restricaoRecurso;
import model.DAO.dao_tipoRecurso;
import model.DAO.dao_unidade;
import model.DAO.dao_usuario;
import model.DAO.dao_usuarioLogado;
import model.DAO.dao_usuarioPermissao;
import model.ENTITY.disponivel;
import model.ENTITY.funcao;
import model.ENTITY.instituicao;
import model.ENTITY.permissao;
import model.ENTITY.recurso;
import model.ENTITY.reserva;
import model.ENTITY.restricaoRecurso;
import model.ENTITY.tipoRecurso;
import model.ENTITY.unidade;
import model.ENTITY.usuario;
import model.ENTITY.usuarioLogado;

/**
 *
 * @author WigorPaulo
 */
public class Controller {

	dao_usuario vDaoUser = new dao_usuario();
	dao_unidade vDaoUnidade = new dao_unidade();
	dao_funcao vDaoFuncao = new dao_funcao();
	dao_instituicao vDaoInstituicao = new dao_instituicao();
	dao_reserva vDaoReserva = new dao_reserva();
	dao_recurso vDaoRecurso = new dao_recurso();
	dao_tipoRecurso vDaoTipoRecurso = new dao_tipoRecurso();
	dao_disponivel vDaoDisponivel = new dao_disponivel();
	dao_permissao vDaoPermissao = new dao_permissao();
	dao_funcaoPermissao vDaoFuncaoPermissao = new dao_funcaoPermissao();
	dao_restricaoRecurso vDaoRestricaoRecurso = new dao_restricaoRecurso();
	dao_usuarioPermissao vDaoUsuarioPermissao = new dao_usuarioPermissao();
	dao_usuarioLogado vDaoUsuarioLogado = new dao_usuarioLogado();
	
	alerta vAlerta = new alerta();
			
	/* INICIO >> Fun��es LISTAR */
	public int ListarUsuarioLogado(){
		try {
			return vDaoUsuarioLogado.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public List<permissao> ListaPermissaoUsuario(int pIdUsuario) {
		try {
			return vDaoUsuarioPermissao.listarPermissao(pIdUsuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<usuario> ValidarLogin(usuario pUser) {
		try {
			return vDaoUser.ValidaLogin(pUser);
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro no ValidarLogin: \n" + e.getMessage());
			return null;
		}
	}

	public List<disponivel> ListaDisponivel(String pDataInicio, String pDataFim) {
		try {
			return vDaoDisponivel.listarDisponivel(pDataInicio, pDataFim);
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro no Filtro: \n" + e.getMessage());
			return null;
		}
	}

	public List<permissao> listaFuncaoPermissao(int pIdFuncao) {
		try {
			return vDaoFuncaoPermissao.listar(pIdFuncao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	public List<permissao> ListaPermissao() {
		try {
			return vDaoPermissao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<usuario> ListaUsuario() {
		try {
			return vDaoUser.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<funcao> ListaFuncao() {
		try {
			return vDaoFuncao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<instituicao> ListaInstituicao() {
		try {
			return vDaoInstituicao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<unidade> ListaUnidade() {
		try {
			return vDaoUnidade.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	public List<restricaoRecurso> ListaRestricaoRecurso() {
		try {
			return vDaoRestricaoRecurso.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<recurso> ListaRecurso() {
		try {
			return vDaoRecurso.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<reserva> ListaReserva() {
		try {
			return vDaoReserva.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<reserva> ListaFiltrosReserva(String pId, String pDataInicio, String pDataFim, String pDataInicio2,
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

	public List<tipoRecurso> ListaTipoRecurso() {
		try {
			return vDaoTipoRecurso.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

	}
	
	public funcao listarFuncao(int pIdFuncao) {
		try {
			return vDaoFuncao.listarFuncao(pIdFuncao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public List<permissao> listarPermissaoUsuarioFuncao(int pIdFuncao, int pIdUsuario){ 
		return vDaoUsuarioPermissao.listarPermissaoUsuarioFuncao(pIdFuncao, pIdUsuario);
	}
	
	/* FIM >> Fun��es LISTAR */

	/* INICIO >> Fun��es FILTRAR */
	public List<usuario> filtrarUsuario(Integer pId, String pNome, String pMatricula) {
		return vDaoUser.filtrar(pId, pNome, pMatricula);

	}

	public List<permissao> filtrarPermissao(Integer id, String nome) {
		try {
			return vDaoPermissao.filtrar(id, nome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<tipoRecurso> filtrarTipoRecurso(Integer pId, String pNome) {
		return vDaoTipoRecurso.filtrar(pId, pNome);
	}

	public List<recurso> filtrarRecurso(Integer pIdRecurso, Integer pIdTipoRecurso, Integer pIdUnidade,
			String pEtiqueta, String pObs) {
		return vDaoRecurso.filtrar(pIdRecurso, pIdTipoRecurso, pIdUnidade, pEtiqueta, pObs);
	}

	public List<funcao> filtrarFuncao(Integer pId, String pNome) {
		return vDaoFuncao.filtrar(pId, pNome);
	}

	public List<unidade> filtrarunidade(Integer pId, String pNome) {
		return vDaoUnidade.filtrar(pId, pNome);
	}

	public List<instituicao> filtrarinstituicao(Integer pId, String pNome) {
		return vDaoInstituicao.filtrar(pId, pNome);
	}
	/* FIM >> Fun��es FILTRAR */

	/* INICIO >> Fun��es ALTERAR */
	public void alterarReserva(reserva pReserva) {
		vDaoReserva.alterar(pReserva);
	}

	public void alterarPermissao(permissao pPermissao) {
		vDaoPermissao.alterar(pPermissao);
	}

	public void alterarUsuario(usuario pUsuario) {
		vDaoUser.alterar(pUsuario);
	}

	public void alterarFuncao(funcao pFuncao) {
		vDaoFuncao.alterar(pFuncao);
	}

	public void alterarInstituicao(instituicao pInstituicao) {
		vDaoInstituicao.alterar(pInstituicao);
	}

	public void alterarUnidade(unidade pUnidade) {
		vDaoUnidade.alterar(pUnidade);
	}

	public void alterarTipoRecurso(tipoRecurso pTipoRecurso) {
		vDaoTipoRecurso.alterar(pTipoRecurso);
	}

	public void alterarRecurso(recurso pRecurso) {
		vDaoRecurso.alterar(pRecurso);
	}
	
	public void alterarUsuarioLogado(usuarioLogado pUsuarioLogado) {
		vDaoUsuarioLogado.alterar(pUsuarioLogado);
	}
	
	public void alterarReservaStatus(reserva pReserva) {
		vDaoReserva.alterarStatus(pReserva);
	}
	/* FIM >> Fun��es ALTERAR */

	/* INICIO >> Fun��es INSERIR */
	
	public void inserirUsuarioPermissao(int pPermissao, int pLastId) {
		vDaoUsuarioPermissao.inserir(pPermissao, pLastId);
	}

	public void InserirFuncaoPermissao(int pPermissao, int pLastId) {
		vDaoFuncaoPermissao.inserir(pPermissao, pLastId);
	}

	public void inserirPermissao(permissao pPermissao) {
		vDaoPermissao.inserir(pPermissao);
	}

	public int InserirUsuario(usuario pUser) {
		return vDaoUser.inserir(pUser);
	}

	public int InserirFuncao(funcao pFuncao) {
		return vDaoFuncao.inserir(pFuncao);
	}

	public int inserirInstituicao(instituicao pInstituicao) {
		return vDaoInstituicao.inserir(pInstituicao);
		
	}

	public int InserirUnidade(unidade pUnidade) {
		return vDaoUnidade.inserir(pUnidade);
	}

	public void InserirRecurso(recurso pRecurso) {
		vDaoRecurso.inserir(pRecurso);
	}

	public void inserirTipo_Recurso(tipoRecurso pTipoRecurso) {
		vDaoTipoRecurso.inserir(pTipoRecurso);
	}

	public void InserirReserva(reserva pReserva) {
		vDaoReserva.inserir(pReserva);
	}

	public void InserirRestricaoRecurso(restricaoRecurso pRestricaoRecurso) {
		vDaoRestricaoRecurso.inserir(pRestricaoRecurso);
	}
	/* FIM >> Fun��es INSERIR */

	/* INICIO >> Fun��es EXCLUIR */
	public void excluirPermissao(permissao pPermissao) {
		vDaoPermissao.excluir(pPermissao);
	}

	public void excluirRestricaoRecurso(restricaoRecurso pRestricaoRecurso) {
		vDaoRestricaoRecurso.excluir(pRestricaoRecurso);
	}

	public void excluirReserva(reserva pReserva) {
		vDaoReserva.excluir(pReserva);
	}

	public void excluirUsuario(usuario pUsuario) {
		vDaoUser.excluir(pUsuario);
	}

	public boolean excluirFuncao(funcao pFuncao) {
		return vDaoFuncao.excluir(pFuncao);
	}

	public void excluirInstituicao(instituicao pInstituicao) {
		vDaoInstituicao.excluir(pInstituicao);
	}

	public void excluirUnidade(unidade pUnidade) {
		vDaoUnidade.excluir(pUnidade);
	}

	public void excluirTipoRecurso(tipoRecurso pTipoRecurso) {
		vDaoTipoRecurso.excluir(pTipoRecurso);
	}

	public void excluirRecurso(recurso pRecurso) {
		vDaoRecurso.excluir(pRecurso);
	}

	public void excluirPermissaoUsuario(permissao pPermissao) {
		vDaoUsuarioPermissao.excluirPermissaoUsuario(pPermissao);
	}

	public void excluirUsuarioPermissao(usuario pUsuario) {
		vDaoUsuarioPermissao.excluirUsuarioPermissao(pUsuario);
	}

	public void excluirFuncaoPermissao(funcao pFuncao) {
		vDaoFuncaoPermissao.excluirFuncao(pFuncao);
	}

	public void excluirPermissaoFuncao(permissao pPermissao) {
		vDaoFuncaoPermissao.excluirPermissao(pPermissao);
	}

	/* FIM >> Fun��es EXCLUIR */

}
