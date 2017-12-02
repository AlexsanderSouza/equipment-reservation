package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Alerta;
import model.ENTITY.TipoRecurso;

public class DaoTipoRecurso {
	
	Alerta vAlerta = new Alerta();

    public List<TipoRecurso> listar() throws Exception{
        
        List<TipoRecurso> vListaTipo_recurso = new ArrayList<TipoRecurso>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from tipo_recurso");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
        	TipoRecurso vTipo_recurso = new TipoRecurso();
            vTipo_recurso.setId(rs.getInt("id"));
            vTipo_recurso.setNome(rs.getString("nome"));
            vTipo_recurso.setDescricao(rs.getString("descricao")); 
            vTipo_recurso.setAtivo(rs.getBoolean("ativo"));
            vListaTipo_recurso.add(vTipo_recurso);
        }
        rs.close();
        st.close();
        
        return vListaTipo_recurso;       
    }

    
    public void inserir(TipoRecurso pTipo_recurso){
        try {
            String vSQL = "INSERT INTO tipo_recurso(id, nome, descricao, ativo) "
                                      +"VALUES(?, ?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pTipo_recurso.getId()));
            st.setString(2, pTipo_recurso.getNome());
            st.setString(3, pTipo_recurso.getDescricao());
            st.setBoolean(4, pTipo_recurso.getAtivo());
            
            st.execute();
            st.close();
      
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
    
    
 public void alterar (TipoRecurso pTipo_recurso){
    	
    	try{
    		
    		int permissaoAtiva;
    		
    		if (pTipo_recurso.getAtivo() == true) {
    			permissaoAtiva =1;	
    		} else{
    			permissaoAtiva = 0;
    			
    		}
    		
    		String vSQL = "UPDATE tipo_recurso SET `nome`='" + pTipo_recurso.getNome()+"', `descricao`='"+ pTipo_recurso.getDescricao() + "',`ativo`='" + permissaoAtiva + "' WHERE `id`='" + pTipo_recurso.getId() + "'";
    		System.out.println(vSQL);
			System.out.println(pTipo_recurso.getAtivo());
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);

			st.execute();
			st.close();
			vAlerta.mensagemAlerta("Alterado com Sucesso!");
			ConexaoDataBase.FecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função alterar! \n" + "Erro: " + e.getMessage());
    	}
    
    }
    
    
    
 public void excluir(TipoRecurso pTipo_recurso) {
 		try {
			String vSQL = "DELETE FROM tipo_recurso WHERE `id`='" + pTipo_recurso.getId() +"'";
			
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
						
			st.execute();
	        st.close();
	        
	        vAlerta.mensagemAlerta("Excluido com Sucesso!");
	        ConexaoDataBase.FecharConexao();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função DELETAR! \n"+"Erro: "+e.getMessage());
		}
 	
 }
 
 
 public List<TipoRecurso> filtrar(Integer id,String nome) {
 	try {
 		
			String vSQL = "select * from tipo_recurso";

			if(id != null && nome.equals("")){
				vSQL = vSQL + " where id =" +id+ " ";
			}else if((!nome.equals("")) && id == null ) {
				vSQL = vSQL + " where nome like  '%"+nome+"%'";
			}else if(!(nome.equals("") && id == null)) {
				vSQL= vSQL + " where id =" +id+ "  and nome = '" +nome+ "'";
			}

			List<TipoRecurso> vListaTipo_recurso = new ArrayList<TipoRecurso>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
			    TipoRecurso vtipoRecurso = new TipoRecurso();
			    vtipoRecurso.setId(rs.getInt("id"));
			    vtipoRecurso.setNome(rs.getString("nome"));
			    vtipoRecurso.setDescricao(rs.getString("descricao")); 
			    vtipoRecurso.setAtivo(rs.getBoolean("ativo"));
			    vListaTipo_recurso.add(vtipoRecurso);
			}
			rs.close();
			st.close();
			
			return vListaTipo_recurso;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função FILTRO! \n"+"Erro: "+e.getMessage());
			return null;
		} 
 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
