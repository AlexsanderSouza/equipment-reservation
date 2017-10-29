/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LOGIN;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.alertaInformacao;
import view.startView;

/**
 *
 * @author WIGOR
 */
public class viewlogin {
    alertaInformacao vAlerta = new alertaInformacao();
    
    public  void start() throws IOException{
        //try {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("LOGIN.fxml"));//Aqui vc indica o caminho do sex .fxml

      //  controller_login con = loader.getController();

            Parent janela = FXMLLoader.load(getClass().getResource("LOGIN.fxml"));
             
            startView.setScene(janela);
        //} catch (IOException e) {
        //    vAlerta.mensagemAlerta("Erro na Função START classe view_login!\n"+"Erro: "+e.getMessage());
        //}               
    }
}
