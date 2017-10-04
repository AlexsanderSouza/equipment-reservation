/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

import model.alerta;
import model.dao.dao_funcao;
import model.dao.dao_instituicao;
import model.dao.dao_recurso;
import model.dao.dao_reserva;
import model.dao.dao_tipoRecurso;
import model.dao.dao_unidade;
import model.dao.dao_usuario;
import model.entity.funcao;
import model.entity.instituicao;
import model.entity.recurso;
import model.entity.reserva;
import model.entity.tipoRecurso;
import model.entity.unidade;
import model.entity.usuario;

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
    dao_tipoRecurso vDaoTipoRecurso = new dao_tipoRecurso();
    dao_recurso vDaoRecurso = new dao_recurso();
    alerta vAlerta = new alerta();
   
    
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
    
    public List<recurso> ListaRecurso(){
    	try {
			return vDaoRecurso.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
    }
    
    public List<tipoRecurso> ListaTipoRecurso(){
    	try {
			return vDaoTipoRecurso.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
    	
    }
    
    public List<reserva> ListaReserva(){
    	try {
			return vDaoReserva.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
    }
    
    public List<usuario> ListaUsuario(){
    	try {
			return vDaoUser.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;			
		}		
    }
    
    public List<instituicao> listaInstituicao(){
    	try {
			return vDaoInstituicao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		
    }
    
    public void InserirRecurso(recurso pRecurso) {
    	vDaoRecurso.inserir(pRecurso);
    }
    
    public void InserirTipoRecurso(tipoRecurso pTipoRecurso) {
    	vDaoTipoRecurso.inserir(pTipoRecurso);
    }
    
    public void InserirInstituicao(instituicao pInstituicao){
    	vDaoInstituicao.inserir(pInstituicao);    	
    }
    
    public void InserirFuncao(funcao pFuncao){
    	vDaoFuncao.inserir(pFuncao);    	
    }
    
    public void InserirUnidade(unidade pUnidade){
    	vDaoUnidade.inserir(pUnidade);    	
    }
    
    public void InserirUsuario(usuario pUser){
        vDaoUser.inserir(pUser);
    }
        
}
