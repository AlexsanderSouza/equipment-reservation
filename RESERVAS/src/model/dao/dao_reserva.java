package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.reserva;

public class dao_reserva {
	
	alerta vAlerta = new alerta();

	public void excluir(reserva pReserva) {
		try {
			String vSQL = "DELETE FROM reserva WHERE `id` = '"+pReserva.getId()+"'";
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			st.execute();
	        st.close();
	        
	        ConexaoDataBase.FecharConexao();
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função DELETAR! \n"+"Erro: "+e.getMessage());
		}
	}
	
	public void alterar(reserva pReserva) {
		try {
			String vSQL = "";
			
			String vIdResponsavel = Integer.toString(pReserva.getId_responsavel());
			String vIdDestinatario = Integer.toString(pReserva.getId_destinatario());
			String vIdRecurso = Integer.toString(pReserva.getId_recurso());
			String vId = Integer.toString(pReserva.getId());
			
			vSQL =  "update reserva set id_responsavel = "+vIdResponsavel + 
					"                , id_destinatario = "+vIdDestinatario+ 
					"                     , id_recurso = "+vIdRecurso+ 
					"			   , data_hora_reserva = "+"'"+pReserva.getData_hora_reserva()+"'"+ 
					"                , data_hora_final = "+"'"+pReserva.getData_hora_final()+"'"+ 
					"                      , repeticao = "+"'"+pReserva.getRepeticao()+"'"+ 
					"                         , status = "+"'"+pReserva.getStatus()+"'"+ 
					"                         where id = "+vId;
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			st.execute();
			st.close();
			
			ConexaoDataBase.FecharConexao();			
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função alterar! \n" + "Erro: " + e.getMessage());
		}
	}
	
	public List<reserva> listarFiltroDisponivel(String pId, String pDataInicio, String pDataFim, String pDataInicio2,
			String pDataFim2, String pStatus, String pResponsavel, String pDestinatario) throws Exception {
		String vSQL = "select ((select count(rc.id_tipo_recurso) qtde_estoque " + "from recurso rc "
				+ "where rc.id_tipo_recurso = 1 )-(select count(rs.id_recurso) qtde_gasto " + "from reserva rs "
				+ "left join recurso rc on rc.id = rs.id_recurso and rc.id_tipo_recurso = 1 "
				+ "where rs.id_recurso = 1)) qtde_disponivel, rs.* " + "from reserva rs "
				+ "left join recurso rc on rc.id = rs.id_recurso and rc.id_tipo_recurso = 1 "
				+ "where rs.id_recurso = 1 ";

		String vSQL_Aux = "";

		String vVazia = "";
		String vNull = null;

		if (pId.equals(vNull) || pId.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND id in (" + pId + ")";
		}

		if (pDataInicio.equals(vNull) || pDataInicio.equals(vVazia) || pDataFim.equals(vNull)
				|| pDataFim.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND data_hora_reserva BETWEEN " + "'" + pDataInicio + "'" + "AND " + "'" + pDataFim
					+ "'";
		}

		if (pDataInicio2.equals(vNull) || pDataInicio2.equals(vVazia) || pDataFim2.equals(vNull)
				|| pDataFim2.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND data_hora_final BETWEEN " + "'" + pDataInicio2 + "'" + "AND " + "'" + pDataFim2
					+ "'";
		}

		if (pStatus.equals(vNull) || pStatus.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND status = " + "'" + pStatus + "'";
		}

		if (pResponsavel.equals(vNull) || pResponsavel.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND id_responsavel = " + pResponsavel;
		}
		if (pDestinatario.equals(vNull) || pDestinatario.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND id_destinatario = " + pDestinatario;
		}

		vSQL = vSQL + vSQL_Aux + " group by  qtde_disponivel ";

		List<reserva> vListaReserva = new ArrayList<reserva>();
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery(vSQL);
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			reserva vReserva = new reserva();
			vReserva.setId(rs.getInt("qtde_disponivel"));
			vReserva.setId_responsavel(rs.getInt("id_recurso"));
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

	public List<reserva> listarFiltro(String pId, String pDataInicio, String pDataFim, String pDataInicio2,
			String pDataFim2, String pStatus, String pResponsavel, String pDestinatario) throws Exception {
		String vSQL = "SELECT * FROM reserva WHERE 1 = 1";
		String vSQL_Aux = "";

		String vVazia = "";
		String vNull = null;

		if (pId.equals(vNull) || pId.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND id in (" + pId + ")";
		}

		if (pDataInicio.equals(vNull) || pDataInicio.equals(vVazia) || pDataFim.equals(vNull)
				|| pDataFim.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND data_hora_reserva BETWEEN " + "'" + pDataInicio + "'" + "AND " + "'" + pDataFim
					+ "'";
		}

		if (pDataInicio2.equals(vNull) || pDataInicio2.equals(vVazia) || pDataFim2.equals(vNull)
				|| pDataFim2.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND data_hora_final BETWEEN " + "'" + pDataInicio2 + "'" + "AND " + "'" + pDataFim2
					+ "'";
		}

		if (pStatus.equals(vNull) || pStatus.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND status = " + "'" + pStatus + "'";
		}

		if (pResponsavel.equals(vNull) || pResponsavel.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND id_responsavel = " + pResponsavel;
		}
		if (pDestinatario.equals(vNull) || pDestinatario.equals(vVazia)) {

		} else {
			vSQL_Aux = vSQL_Aux + " AND id_destinatario = " + pDestinatario;
		}

		vSQL = vSQL + vSQL_Aux;

		List<reserva> vListaReserva = new ArrayList<reserva>();
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery(vSQL);
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
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

	public List<reserva> listar() throws Exception {

		List<reserva> vListaReserva = new ArrayList<reserva>();
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery("select * from reserva");
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
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

	public void inserir(reserva pReserva) {
		try {
			String vSQL = "INSERT INTO reserva(id, id_responsavel, id_destinatario, id_recurso, data_hora_reserva, data_hora_final, repeticao, status) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			st.setString(1, Integer.toString(pReserva.getId()));
			st.setString(2, Integer.toString(pReserva.getId_responsavel()));
			st.setString(3, Integer.toString(pReserva.getId_destinatario()));
			st.setString(4, Integer.toString(pReserva.getId_recurso()));
			st.setString(5, pReserva.getData_hora_reserva());
			st.setString(6, pReserva.getData_hora_final());
			st.setString(7, pReserva.getRepeticao());
			st.setString(8, pReserva.getStatus());
			// st.setString(8, pUser.getAtivo());

			st.execute();
			st.close();

			vAlerta.mensagemAlerta("Inserido com Sucesso!");
			ConexaoDataBase.FecharConexao();

		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro na Função INSERIR! \n" + "Erro: " + e.getMessage());
		}
	}
}
