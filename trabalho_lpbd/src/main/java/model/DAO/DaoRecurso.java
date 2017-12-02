package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Alerta;
import model.ENTITY.Recurso;

public class DaoRecurso {
	Alerta vAlerta = new Alerta();
    
	public void alterar(Recurso pRecurso) {
		try {
			String vSQL = "";
			int vAtivo;
			
			if (pRecurso.GetAtivo() == true) {
				vAtivo = 1;
			} else {
				vAtivo = 0;
			}
			
			String vIdUnidade = Integer.toString(pRecurso.getId_unidade());
			String vIdTipoRecurso = Integer.toString(pRecurso.getId_tipo_recurso());
			String vId = Integer.toString(pRecurso.getId());
			
			vSQL = "update recurso set `etiqueta` = "+"'"+pRecurso.getEtiqueta()+"'"+
					              ", `observacao` = "+"'"+pRecurso.getObservacao()+"'"+
					              ", `id_unidade` = "+vIdUnidade +
					         ", `id_tipo_recurso` = "+vIdTipoRecurso+
					        		   ", `ativo` = "+vAtivo +
					        		 " where `id` = "+vId;
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			st.execute();
			st.close();
			
			ConexaoDataBase.FecharConexao();
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função alterar! \n" + "Erro: " + e.getMessage());
		}
	}
	
	public List<Recurso> filtrar(Integer pIdRecurso, Integer pIdTipoRecurso, Integer pIdUnidade, String pEtiqueta, String pObs ) {
		try {
			/*String vSQL = "select rc.*, tr.nome, un.nome nomeUnidade\r\n" + 
						  "  from recurso rc\r\n" + 
						  "left join tipo_recurso tr on tr.id = rc.id_tipo_recurso\r\n" + 
						  "left join unidade un on un.id = rc.id_unidade\r\n" + 
						  " where 1 = 1 ";*/
			
			String vSQL = "";
			String vUsuarioLogado = "";
	         
	         DaoUsuarioLogado vUserLogado = new DaoUsuarioLogado();
	            
	         vUsuarioLogado = Integer.toString(vUserLogado.listar());
	    	 
	    	 vSQL = vSQL + "select rc.*, CONCAT(tp.nome,' ',tp.descricao) nome, un.nome nomeUnidade \r\n" + 
	          		"  from recurso rc \r\n" + 
	          		"left join tipo_recurso tp on tp.id = rc.id_tipo_recurso \r\n" + 
	          		"left join unidade un on un.id = rc.id_unidade\r\n" + 
	          		"  WHERE tp.id NOT IN  (SELECT tr.id\r\n" + 
	          		"  FROM restricao_recurso rr\r\n" + 
	          		"LEFT JOIN tipo_recurso tr ON tr.ID = rr.id_tipo_recurso2 AND tr.ativo = 1 \r\n" + 
	          		"LEFT JOIN funcao f ON f.id = rr.id_funcao2\r\n" + 
	          		"left JOIN usuario u ON u.id_funcao = f.id\r\n" + 
	          		"  WHERE u.id = "+vUsuarioLogado+") ";
			
			if (pIdRecurso != null) {
				String vIdRecurso = Integer.toString(pIdRecurso);
				vSQL = vSQL + " and rc.id = "+ vIdRecurso;
			}
			if (pIdTipoRecurso != null){
				String vIdTipoRecurso = Integer.toString(pIdTipoRecurso);
				vSQL = vSQL + " and rc.id_tipo_recurso = "+vIdTipoRecurso;				
			}
			if (pIdUnidade != null) {
				String vIdUnidade = Integer.toString(pIdUnidade);
				vSQL = vSQL + " and rc.id_unidade = "+vIdUnidade;				
			}
			if (!pEtiqueta.equals("")) {
				vSQL = vSQL + " and rc.etiqueta like "+"'%"+pEtiqueta+"%'";
			}
			if (!pObs.equals("")) {
				vSQL = vSQL + " and rc.observacao like "+"'%"+pObs+"%'";				
			}
			
			List<Recurso> vListaRecurso = new ArrayList<Recurso>();
			
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			
			ResultSet rs = st.getResultSet();
			
			while (rs.next()) {
				Recurso vRecurso = new Recurso();
				vRecurso.setId(rs.getInt("id"));
				vRecurso.setEtiqueta(rs.getString("etiqueta"));
				vRecurso.setObservacao(rs.getString("observacao"));
				vRecurso.setId_unidade(rs.getInt("id_unidade"));
				vRecurso.setId_tipo_recurso(rs.getInt("id_tipo_recurso"));
				vRecurso.setAtivo(rs.getBoolean("ativo"));
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
	
	public void excluir(Recurso pRecurso) {
		try {
			String vSQL = "DELETE FROM recurso WHERE `id` = '"+pRecurso.getId()+"'";
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			st.execute();
	        st.close();

	        ConexaoDataBase.FecharConexao();
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Função DELETAR! \n"+"Erro: "+e.getMessage());
		}
	}
	
    public List<Recurso> listar() throws Exception{
    	 String vSQL = "";
         String vUsuarioLogado = "";
         
         DaoUsuarioLogado vUserLogado = new DaoUsuarioLogado();
            
         vUsuarioLogado = Integer.toString(vUserLogado.listar());
    	 
    	 vSQL = vSQL + "select rc.*, CONCAT(tp.nome,' ',tp.descricao) nome, un.nome nomeUnidade \r\n" + 
          		"  from recurso rc \r\n" + 
          		"left join tipo_recurso tp on tp.id = rc.id_tipo_recurso \r\n" + 
          		"left join unidade un on un.id = rc.id_unidade\r\n" + 
          		"  WHERE tp.id NOT IN  (SELECT tr.id\r\n" + 
          		"  FROM restricao_recurso rr\r\n" + 
          		"LEFT JOIN tipo_recurso tr ON tr.ID = rr.id_tipo_recurso2 AND tr.ativo = 1 \r\n" + 
          		"LEFT JOIN funcao f ON f.id = rr.id_funcao2\r\n" + 
          		"left JOIN usuario u ON u.id_funcao = f.id\r\n" + 
          		"  WHERE u.id = "+vUsuarioLogado+") ";
          
         
        List<Recurso> vListaRecurso = new ArrayList<Recurso>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery(vSQL);
        
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            Recurso vRecurso = new Recurso();
            
            vRecurso.setId(rs.getInt("id"));
            vRecurso.setEtiqueta(rs.getString("etiqueta"));
            vRecurso.setObservacao(rs.getString("observacao"));
            vRecurso.setAtivo(rs.getBoolean("ativo"));
            vRecurso.setId_unidade(rs.getInt("id_unidade"));
            vRecurso.setNomeUnidade(rs.getString("nomeUnidade"));
            vRecurso.setId_tipo_recurso(rs.getInt("id_tipo_recurso"));
            vRecurso.setNomeTipoRecurso(rs.getString("nome"));
            vListaRecurso.add(vRecurso);
        }
        rs.close();
        st.close();
        
        return vListaRecurso;
        
    }

    public void inserir(Recurso pRecurso){
        try {
            String vSQL = "INSERT INTO recurso(id, etiqueta, observacao, id_unidade, id_tipo_recurso,ativo) "
                                      +"VALUES(?, ?, ?, ?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pRecurso.getId()));
            st.setString(2, pRecurso.getEtiqueta());
            st.setString(3, pRecurso.getObservacao());
            st.setString(4, Integer.toString(pRecurso.getId_unidade()));
            st.setString(5, Integer.toString(pRecurso.getId_tipo_recurso()));
            st.setBoolean(6, pRecurso.GetAtivo());
            
            st.execute();
            st.close();

            ConexaoDataBase.FecharConexao();
            vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
}
