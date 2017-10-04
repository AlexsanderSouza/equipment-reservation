/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.dao_funcao;
import model.dao.dao_instituicao;
import model.dao.dao_unidade;
import model.dao.dao_usuario;
import model.entity.funcao;
import model.entity.instituicao;
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
