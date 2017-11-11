package model.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.disponivel;

public class dao_disponivel {

	alerta vAlerta = new alerta();
    
	public List<disponivel> listarDisponivel(String pDataInicio,String pDataFim) throws Exception{
	    
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
		    vAlerta.mensagemAlerta("Falta Informar a Data Inicio e Fim que deseja pesquisar!");
		    
		   } else {
			   vSQL = vSQL+" where data_hora_reserva between "+"'"+pDataInicio+"'"+" and "+"'"+pDataFim+"' ";
		   }
			
	   		vSQL = vSQL+" and rcx.id_tipo_recurso = rc.id_tipo_recurso "+ 
			"group by rc.id_tipo_recurso)  qtdeloca "+
	        "from recurso rcx ";
	    	
		vSQL = vSQL+ " group by rcx.id_tipo_recurso  ";
	    
	    List<disponivel> vListaDisponivel = new ArrayList<disponivel>();
	    java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
	    st.executeQuery(vSQL);
	    ResultSet rs = st.getResultSet();
	    while(rs.next()){
	    	disponivel vDisponivel = new disponivel(); //chamar a classe disponivel.. e adicionar na grid
	    	
	    	vDisponivel.setTipo(rs.getString("tipo")+" - "+rs.getString("nome"));
	    		    	
	    	vQtdeDisponivel = rs.getString("qtdedisp");
	    	vQtdeLocado = rs.getString("qtdeloca");
	    	
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
