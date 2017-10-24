package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.recurso;

public class dao_recurso {
	alerta vAlerta = new alerta();
    
	public List<recurso> filtrar(Integer pIdRecurso, Integer pIdTipoRecurso, Integer pIdUnidade, String pEtiqueta, String pObs ) {
		try {
			String vSQL = "select rc.*, tr.nome, un.nome nomeUnidade\r\n" + 
						  "  from recurso rc\r\n" + 
						  "left join tipo_recurso tr on tr.id = rc.id_tipo_recurso\r\n" + 
						  "left join unidade un on un.id = rc.id_unidade\r\n" + 
						  " where 1 = 1 ";
			
			if (pIdRecurso != null) {
				vSQL = vSQL + " and rc.id = "+ pIdRecurso;
			}
			if (pIdTipoRecurso != null){
				vSQL = vSQL + " and rc.id_tipo_recurso = "+pIdTipoRecurso;				
			}
			if (pIdUnidade != null) {
				vSQL = vSQL + " and rc.id_unidade = "+pIdUnidade;				
			}
			if (!pEtiqueta.equals("")) {
				vSQL = vSQL + " and rc.etiqueta like "+"'%"+pEtiqueta+"%'";
			}
			if (!pObs.equals("")) {
				vSQL = vSQL + " and rc.observacao like "+"'%"+pObs+"%'";				
			}
			
			List<recurso> vListaRecurso = new ArrayList<recurso>();
			
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			
			ResultSet rs = st.getResultSet();
			
			while (rs.next()) {
				recurso vRecurso = new recurso();
				vRecurso.setId(rs.getInt("id"));
				vRecurso.setEtiqueta(rs.getString("etiqueta"));
				vRecurso.setObservacao(rs.getString("observacao"));
				vRecurso.setId_unidade(rs.getInt("id_unidade"));
				vRecurso.setId_tipo_recurso(rs.getInt("id_tipo_recurso"));
				vRecurso.setAtivo(rs.getString("ativo"));
				vRecurso.setNomeTipoRecurso(rs.getString("nome"));
				vRecurso.setNomeUnidade(rs.getString("nomeUnidade"));
				
				vListaRecurso.add(vRecurso);
			}
			rs.close();
			st.close();
			
			return vListaRecurso;
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função FILTRO! \n"+"Erro: "+e.getMessage());
			return null;
		}
	}
	
	public void excluir(recurso pRecurso) {
		try {
			String vSQL = "DELETE FROM recurso WHERE `id` = '"+pRecurso.getId()+"'";
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			st.execute();
	        st.close();
	        
	        vAlerta.mensagemAlerta("Excluido com Sucesso!");
	        ConexaoDataBase.FecharConexao();
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função DELETAR! \n"+"Erro: "+e.getMessage());
		}
	}
	
    public List<recurso> listar() throws Exception{
    	 String vSQL = "";
         
         vSQL = vSQL + "select rc.*, tp.nome nome from recurso rc "+
                       "left join tipo_recurso tp on tp.id = rc.id_tipo_recurso ";
         
        List<recurso> vListaRecurso = new ArrayList<recurso>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery(vSQL);
        
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            recurso vRecurso = new recurso();
            
            vRecurso.setId(rs.getInt("id"));
            vRecurso.setEtiqueta(rs.getString("etiqueta"));
            vRecurso.setObservacao(rs.getString("observacao"));
            vRecurso.setAtivo(rs.getString("ativo"));
            vRecurso.setId_unidade(rs.getInt("id_unidade"));
            vRecurso.setId_tipo_recurso(rs.getInt("id_tipo_recurso"));
            vRecurso.setNomeTipoRecurso(rs.getString("nome"));
            vListaRecurso.add(vRecurso);
        }
        rs.close();
        st.close();
        
        return vListaRecurso;
        
    }

    public void inserir(recurso pRecurso){
        try {
            String vSQL = "INSERT INTO recurso(id, etiqueta, observacao, id_unidade, id_tipo_recurso) "
                                      +"VALUES(?, ?, ?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pRecurso.getId()));
          //  st.setString(2, pRecurso.getNome());
            st.setString(2, pRecurso.getEtiqueta());
            st.setString(3, pRecurso.getObservacao());
            st.setString(4, Integer.toString(pRecurso.getId_unidade()));
            st.setString(5, Integer.toString(pRecurso.getId_tipo_recurso()));
            //st.setString(7, pUser.getAtivo());
            
            st.execute();
            st.close();
      
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
}
