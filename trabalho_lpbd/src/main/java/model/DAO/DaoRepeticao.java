package model.DAO;

import java.sql.PreparedStatement;

import model.Alerta;
import model.ENTITY.Repeticao;

public class DaoRepeticao {
	
	Alerta vAlerta = new Alerta();
	
	String vSQL;
	PreparedStatement st;
	
	public void InserirRepeticao(Repeticao pRepeticao) {
		try {
			vSQL = "";
			vSQL = vSQL+"INSERT INTO repeticao(id_reserva_origem, id_reserva_new)\r\n" + 
					"         VALUES(?, ?)";
			
			st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			st.setInt(1, pRepeticao.getId_reserva_pai());
			st.setInt(2, pRepeticao.getId_reserva_filho());
			
			st.execute();			
			st.close();
			
			vAlerta.mensagemAlerta("Inserido com Sucesso!");
			ConexaoDataBase.FecharConexao();
					
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função InserirRepeticao! \n" + "Erro: " + e.getMessage());
		}		
	}

}
