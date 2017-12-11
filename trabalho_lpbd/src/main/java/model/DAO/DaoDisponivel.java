package model.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Alerta;
import model.ENTITY.Disponivel;

public class DaoDisponivel {

	Alerta vAlerta = new Alerta();
	
	public int listarRecursoID(String pTipoRecursoID) throws Exception {
		String vSQL = "";
		
		int vRecursoID = 0;
		
		vSQL = "select id \r\n" + 
				"  from recurso \r\n" + 
				" where id_tipo_recurso = "+pTipoRecursoID+"\r\n" + 
//				"   and id not in (select id_recurso\r\n" + 
//				"				from reserva)\r\n" + 
				" limit 1";
		
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery(vSQL);
	    ResultSet rs = st.getResultSet();
	    while(rs.next()){
	    	vRecursoID = rs.getInt("id");
	    }
		
		return vRecursoID;
	}
    
	public List<Disponivel> ListarRepeticao(String pRepData, String pHoraInicio, String pHoraFim) throws Exception{
		
		String vQtdeDisponivel, vQtdeLocado, vQtdeEstoque;
		String vSQL = "";
		
		vSQL = vSQL+"select rcx.id_tipo_recurso tipo, \r\n" + 
				"		(select tp.nome \r\n" + 
				"		from tipo_recurso tp \r\n" + 
				"		where tp.id = rcx.id_tipo_recurso) nome, \r\n" + 
				"		count(*) qtdedisp,\r\n" + 
				"		 (select  count(*) qtdeloca \r\n" + 
				"		    from reserva rs \r\n" + 
				"		    left join recurso rc on rc.id = rs.id_recurso \r\n" + 
				"		    where 1 = 1 and status = 'ATIVO' AND status = 'PENDENTE' ";
		
		if (!pRepData.equals("")) {
			vSQL = vSQL+"and data_reserva IN ("+pRepData+")";
		}
		
		if (!pHoraInicio.equals("")) {
			vSQL = vSQL+"AND hora_inicio = "+"'"+pHoraInicio+"'";			
		}
		
		if (!pHoraFim.equals("")) {
			vSQL = vSQL+"AND hora_fim = "+"'"+pHoraFim+"'";
		}
		
		vSQL = vSQL + "and rcx.id_tipo_recurso = rc.id_tipo_recurso \r\n" + 
				"		    group by rc.id_tipo_recurso)  qtdeloca\r\n" + 
				"		 from recurso rcx  \r\n" + 
				"		 group by rcx.id_tipo_recurso ";    
		    
		List<Disponivel> vListaDisponivel = new ArrayList<Disponivel>();
		
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
	    st.executeQuery(vSQL);
	    
	    ResultSet rs = st.getResultSet();
	    
	    while(rs.next()){
	    	Disponivel vDisponivel = new Disponivel(); 
	    	
	    	vDisponivel.setTipo(rs.getString("tipo")+" - "+rs.getString("nome"));
	    		
	    	vQtdeDisponivel = rs.getString("qtdedisp");
	    	vQtdeLocado = rs.getString("qtdeloca");
	    	
	    	if (vQtdeDisponivel == null) {
	    		vQtdeDisponivel = "0";
	    	}
	    	
	    	if (vQtdeLocado == null) {
	    		vQtdeLocado = "0";
	    	}
	    	
	    	if (Integer.parseInt(vQtdeDisponivel) > Integer.parseInt(vQtdeLocado)) {
	    	vQtdeEstoque = Integer.toString( Integer.parseInt(vQtdeDisponivel) - Integer.parseInt(vQtdeLocado));
	    	} else {
	    		vQtdeEstoque = "0";
	    	}
	    		
	    	vDisponivel.setQtdedisp(vQtdeEstoque);
	    		    	
	    	vListaDisponivel.add(vDisponivel);
	    }
	    rs.close();
	    st.close();
		
		
		return vListaDisponivel;
	}
	
	public List<Disponivel> listarDisponivel(String pDataInicio,String pDataFim) throws Exception{
	    
		String vQtdeDisponivel, vQtdeLocado, vQtdeEstoque;
		String vSQL = "";
	    	    
	    String vVazia = "";
	    String vNull = null;
	    
	   vSQL = "select rcx.id_tipo_recurso tipo, (select tp.nome from tipo_recurso tp where tp.id = rcx.id_tipo_recurso) nome, "+
	                  "count(*) qtdedisp, "+
		      "(select  count(*) qtdeloca "+
			  "from reserva rs "+
			  "left join recurso rc on rc.id = rs.id_recurso ";
	   
	   		if ( pDataInicio.equals(vNull) || pDataInicio.equals(vVazia) || pDataFim.equals(vNull) || pDataFim.equals(vVazia) ) {
		    vSQL = vSQL+" where 1 = 1";		    
		    
		   } else {
			   vSQL = vSQL+" where data_hora_reserva between "+"'"+pDataInicio+"'"+" and "+"'"+pDataFim+"' ";
		   }
			
	   		vSQL = vSQL+" and rcx.id_tipo_recurso = rc.id_tipo_recurso "+ 
			"group by rc.id_tipo_recurso)  qtdeloca "+
	        "from recurso rcx ";
	    	
		vSQL = vSQL+ " group by rcx.id_tipo_recurso  ";
	    
	    List<Disponivel> vListaDisponivel = new ArrayList<Disponivel>();
	    java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
	    st.executeQuery(vSQL);
	    ResultSet rs = st.getResultSet();
	    while(rs.next()){
	    	Disponivel vDisponivel = new Disponivel(); //chamar a classe disponivel.. e adicionar na grid
	    	
	    	vDisponivel.setTipo(rs.getString("tipo")+" - "+rs.getString("nome"));
	    		
	    	vQtdeDisponivel = rs.getString("qtdedisp");
	    	vQtdeLocado = rs.getString("qtdeloca");
	    	
	    	if (vQtdeDisponivel == null) {
	    		vQtdeDisponivel = "0";
	    	}
	    	
	    	if (vQtdeLocado == null) {
	    		vQtdeLocado = "0";
	    	}
	    	
	    	vQtdeEstoque = Integer.toString( Integer.parseInt(vQtdeDisponivel) - Integer.parseInt(vQtdeLocado));
	    	
	    	vDisponivel.setQtdedisp(vQtdeEstoque);
	    	//vDisponivel.setQtdeloca(rs.getString("qtdeloca"));
	    	
	    	vListaDisponivel.add(vDisponivel);
	    }
	    rs.close();
	    st.close();
	    
	    return vListaDisponivel;       
	}

	
	
}
