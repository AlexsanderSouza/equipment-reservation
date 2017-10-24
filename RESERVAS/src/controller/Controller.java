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
import model.DAO.dao_instituicao;
import model.DAO.dao_permissao;
import model.DAO.dao_recurso;
import model.DAO.dao_reserva;
import model.DAO.dao_tipoRecurso;
import model.DAO.dao_unidade;
import model.DAO.dao_usuario;
import model.ENTITY.disponivel;
import model.ENTITY.funcao;
import model.ENTITY.instituicao;
import model.ENTITY.permissao;
import model.ENTITY.recurso;
import model.ENTITY.reserva;
import model.ENTITY.tipoRecurso;
import model.ENTITY.unidade;
import model.ENTITY.usuario;

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
    
    alerta vAlerta = new alerta();
    
    public List<disponivel> ListaDisponivel(String pDataInicio,String pDataFim){
    	try {
    		return vDaoDisponivel.listarDisponivel(pDataInicio, pDataFim);
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro no Filtro: \n"+e.getMessage());
			return null;
		}    	    
    }
    
    
    
    
    
    
    public List<permissao> filtrarPermissao(Integer id, String nome) {
    	try {
			return vDaoPermissao.filtrar(id, nome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
    }
    
    
    public void alterarPermissao(permissao pPermissao) {
    	vDaoPermissao.alterar(pPermissao);
    }
    
    public void excluirPermissao(permissao pPermissao) {
    	vDaoPermissao.excluir(pPermissao);
    }
    
    public void inserirPermissao(permissao pPermissao) {
    	vDaoPermissao.inserir(pPermissao);
    }
    
    public List<permissao> ListaPermissao(){
    	try {
			return vDaoPermissao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
    }
     
   
    
    
    
    
    
    
    
    
    public void alterarUsuario(usuario pUsuario) {
    	vDaoUser.alterar(pUsuario);
    }
    
    public void excluirUsuario(usuario pUsuario) {
    	vDaoUser.excluir(pUsuario);
    }
    
    public List<usuario> filtrarUsuario(Integer pId, String pNome, String pMatricula) {
        return	vDaoUser.filtrar(pId, pNome, pMatricula);
        	
        }
    
    public List<usuario> ListaUsuario(){
    	try {
			return vDaoUser.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;			
		}		
    }
    
    public void InserirUsuario(usuario pUser){
        vDaoUser.inserir(pUser);
    }
    
    
    
    
    
    
    
    public List<funcao> filtrarFuncao(Integer pId, String pNome) {
    	return vDaoFuncao.filtrar(pId, pNome);
    }
    
   
    public void excluirFuncao(funcao pFuncao) {
    		vDaoFuncao.excluir(pFuncao);
    }
    
    public List<String> listViewAlterarFuncao(funcao pFuncao){
    	return vDaoFuncao.listViewAlterar(pFuncao);
    }
    
    public void alterarFuncao(funcao pFuncao) {
    	vDaoFuncao.alterar(pFuncao);
    }
    
    public List<funcao> ListaFuncao() {
    	try {
			return vDaoFuncao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
    }
    
    
    public void InserirFuncao(funcao pFuncao){
    	vDaoFuncao.inserir(pFuncao);    	
    }
    
    
    
    
    
    
    
    
    public void excluirInstituicao(instituicao pInstituicao) {
    	vDaoInstituicao.excluir(pInstituicao);
    }
    
    public void inserirInstituicao(instituicao pInstituicao) {
    	vDaoInstituicao.inserir(pInstituicao);
    }
    
  
    public void alterarInstituicao(instituicao pInstituicao) {
    	vDaoInstituicao.alterar(pInstituicao);
    }
    
    
    public List<instituicao> filtrarinstituicao(Integer pId, String pNome) {
        return	vDaoInstituicao.filtrar(pId, pNome);
    }
    
    public List<instituicao> ListaInstituicao(){
    	try {
    		return vDaoInstituicao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
    }
    
    
  
   
    
    public void InserirUnidade(unidade pUnidade){
    	vDaoUnidade.inserir(pUnidade);    	
    }
    
    
    public void excluirUnidade(unidade pUnidade) {
    	vDaoUnidade.excluir(pUnidade);
    }
    
  
    public void alterarUnidade(unidade pUnidade) {
    	vDaoUnidade.alterar(pUnidade);
    }
    
    
    public List<unidade> filtrarunidade(Integer pId, String pNome) {
        return	vDaoUnidade.filtrar(pId, pNome);
    }
    
    public List<unidade> ListaUnidade(){
    	try {
    		return vDaoUnidade.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
    }
    
    

    
    
    public void InserirRecurso(recurso pRecurso) {
    	vDaoRecurso.inserir(pRecurso);
    }
    
    
    public List<recurso> ListaRecurso(){
    	try {
			return vDaoRecurso.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}    	
    }
    
   
    
    
    
    public void inserirTipo_Recurso (tipoRecurso pTipoRecurso) {
    	vDaoTipoRecurso.inserir(pTipoRecurso);
    }
    
    
    public void excluirTipoRecurso(tipoRecurso pTipoRecurso) {
    	vDaoTipoRecurso.excluir(pTipoRecurso);
    }
    
    public void excluirRecurso(recurso pRecurso) {
    	vDaoRecurso.excluir(pRecurso);
    }  
    
    public void alterarTipoRecurso(tipoRecurso pTipoRecurso) {
    	vDaoTipoRecurso.alterar(pTipoRecurso);
    }
    
    
    public List<tipoRecurso> filtrarTipoRecurso(Integer pId, String pNome) {
        return	vDaoTipoRecurso.filtrar(pId, pNome);
    }
    
    public List<recurso> filtrarRecurso( Integer pIdRecurso, Integer pIdTipoRecurso, Integer pIdUnidade, String pEtiqueta, String pObs ){
    	return vDaoRecurso.filtrar(pIdRecurso, pIdTipoRecurso, pIdUnidade, pEtiqueta, pObs);
    }
    
    public List<tipoRecurso> ListaTipoRecurso(){
    	try {
			return vDaoTipoRecurso.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
    	
    }
   
    
    
    
    
    
    public List<reserva> ListaFiltrosReserva(String pId,String pDataInicio,String pDataFim, String pDataInicio2,String pDataFim2, String pStatus, String pResponsavel, String pDestinatario){
    	try {
    		return vDaoReserva.listarFiltro(pId, pDataInicio, pDataFim, pDataInicio2, pDataFim2, pStatus, pResponsavel, pDestinatario);
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro no Filtro: \n"+e.getMessage());
			return null;
		}    	    
    }
        
    public void InserirReserva(reserva pReserva){
    	vDaoReserva.inserir(pReserva);    	
    }
    
   
    
    public List<reserva> ListaReserva(){
    	try {
			return vDaoReserva.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
    }
    
   
   
   
}
