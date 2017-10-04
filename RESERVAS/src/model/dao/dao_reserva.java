package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.entity.reserva;

public class dao_reserva {
alerta vAlerta = new alerta();


public List<reserva> listarFiltro(String pId,String pDataInicio,String pDataFim, String pDataInicio2, String pDataFim2, String pStatus, String pResponsavel, String pDestinatario) throws Exception{
    String vSQL = "SELECT * FROM reserva WHERE 1 = 1";
    String vSQL_Aux = "";
    
    String vVazia = "";
    String vNull = null;
    
    if ( pId.equals(vNull) || pId.equals(vVazia) ) {
		
   }else{
	   vSQL_Aux = vSQL_Aux+" AND id in ("+pId+")"; 
   }
    
    if ( pDataInicio.equals(vNull) || pDataInicio.equals(vVazia) || pDataFim.equals(vNull) || pDataFim.equals(vVazia) ) {
		
   } else {
	   vSQL_Aux = vSQL_Aux+" AND data_hora_reserva BETWEEN "+"'"+pDataInicio+"'"+"AND "+"'"+pDataFim+"'";
   }
    
    if ( pDataInicio2.equals(vNull) || pDataInicio2.equals(vVazia) || pDataFim2.equals(vNull) || pDataFim2.equals(vVazia) ) {
		
   } else {
	   vSQL_Aux = vSQL_Aux+" AND data_hora_final BETWEEN "+"'"+pDataInicio2+"'"+"AND "+"'"+pDataFim2+"'";
   }
    	    
    if ( pStatus.equals(vNull) || pStatus.equals(vVazia)  ) {
		
   } else {
	   vSQL_Aux = vSQL_Aux+" AND status = "+"'"+pStatus+"'";
   }
    
    if ( pResponsavel.equals(vNull) || pResponsavel.equals(vVazia)  ) {
		
   } else {
	   vSQL_Aux = vSQL_Aux+" AND id_responsavel = "+pResponsavel;
   }
    if ( pDestinatario.equals(vNull) || pDestinatario.equals(vVazia)  ) {
		
   } else {
	   vSQL_Aux = vSQL_Aux+" AND id_destinatario = "+pDestinatario;
   }
	
	vSQL = vSQL+vSQL_Aux;
    
    List<reserva> vListaReserva = new ArrayList<reserva>();
    java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
    st.executeQuery(vSQL);
    ResultSet rs = st.getResultSet();
    while(rs.next()){
        reserva vReserva = new reserva();
        vReserva.setId(rs.getInt("id"));
        vReserva.setId_responsavel(rs.getInt("id_responsavel"));
        vReserva.setId_destinatario(rs.getInt("id_destinatario"));
        vReserva.setData_hora_reserva(rs.getString("data_hora_reserva"));
        vReserva.setData_hora_final(rs.getString("data_hora_final"));
        vReserva.setRepeticao(rs.getString("repeticao"));
        vReserva.setStatus(rs.getString("status"));
        vListaReserva.add(vReserva);
    }
    rs.close();
    st.close();
    
    return vListaReserva;       
}

    public List<reserva> listar() throws Exception{
        
        List<reserva> vListaReserva = new ArrayList<reserva>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from reserva");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            reserva vReserva = new reserva();
            vReserva.setId(rs.getInt("id"));
            vReserva.setId_responsavel(rs.getInt("id_responsavel"));
            vReserva.setId_destinatario(rs.getInt("id_destinatario"));
            vReserva.setData_hora_reserva(rs.getString("data_hora_reserva"));
            vReserva.setData_hora_final(rs.getString("data_hora_final"));
            vReserva.setRepeticao(rs.getString("repeticao"));
            vReserva.setStatus(rs.getString("status"));
            vListaReserva.add(vReserva);
        }
        rs.close();
        st.close();
        
        return vListaReserva;       
    }

    
    public void inserir(reserva pReserva){
        try {
            String vSQL = "INSERT INTO reserva(id, id_responsavel, id_destinatario, data_hora_reserva, data_hora_final, repeticao, status) "
                                      +"VALUES(?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pReserva.getId()));
            st.setString(2, Integer.toString(pReserva.getId_responsavel()));
            st.setString(3, Integer.toString(pReserva.getId_destinatario()));
            st.setString(4, pReserva.getData_hora_reserva());
            st.setString(5, pReserva.getData_hora_final());
            st.setString(6, pReserva.getRepeticao());
            st.setString(7, pReserva.getStatus());
            //st.setString(8, pUser.getAtivo());
            
            st.execute();
            st.close();
           
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
}
