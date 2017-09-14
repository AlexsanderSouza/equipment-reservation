/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.dao_usuario;
import entidy.usuario;
import model.alerta;

/**
 *
 * @author WigorPaulo
 */
public class controller {
    
    dao_usuario vDaoUser = new dao_usuario();
    alerta vAlerta = new alerta();
    
    public void InserirUsuario(usuario pUser){
        vDaoUser.inserir(pUser);
    }
    
    public void mensagem(String pMensagem){        
        vAlerta.mensagemAlerta(pMensagem);
    }
    
}
