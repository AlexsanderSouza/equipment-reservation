package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Alerta;
import model.ENTITY.Permissao;

public class DaoPermissao {
			
Alerta vAlerta = new Alerta();
    
    public List<Permissao> listar() throws Exception{
        
        List<Permissao> vListaPermissao = new ArrayList<Permissao>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from permissao");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            Permissao vPermissao = new Permissao();
            vPermissao.setId(rs.getInt("id"));
            vPermissao.setNome(rs.getString("nome"));
            vPermissao.setDescricao(rs.getString("descricao"));   
            vPermissao.setAtivo(rs.getBoolean("ativo"));
            vListaPermissao.add(vPermissao);
        }
        rs.close();
        st.close();
        
        return vListaPermissao;       
    }

    
    public void inserir(Permissao pPermissao){
        try {
            String vSQL = "INSERT INTO permissao(id, nome, descricao, ativo) "
                                      +"VALUES(?, ?, ?,?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pPermissao.getId()));
            st.setString(2, pPermissao.getNome());
            st.setString(3, pPermissao.getDescricao());
            st.setBoolean(4, pPermissao.getAtivo());
                        
            st.execute();
            st.close();
           
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
    
    public void alterar(Permissao pPermissao) {
    	
    	try {
    		
    		int permissaoAtiva;
    		
    		if(pPermissao.getAtivo() == true) {
    			permissaoAtiva = 1;
    		}else {
    			permissaoAtiva = 0;
    		}
    		
			String vSQL = "UPDATE permissao SET `nome`='"+ pPermissao.getNome() +"', `descricao`='" + pPermissao.getDescricao() +"', `ativo`='"+permissaoAtiva+"' WHERE `id`='"+pPermissao.getId()+"'";
			System.out.println(vSQL);
			System.out.println(pPermissao.getAtivo());
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			
			st.execute();
			st.close();
			vAlerta.mensagemAlerta("Alterado com Sucesso!");
			ConexaoDataBase.FecharConexao();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função alterar! \n"+"Erro: "+e.getMessage());
		}
    }
    
    public void excluir(Permissao pPermissao) {
    	try {
    		
			String vSQL = "DELETE FROM permissao WHERE `id`='" + pPermissao.getId() +"'";
			System.out.println(vSQL);
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
    
  
	public List<Permissao> filtrar(Integer id,String nome) {
    	try {
    		
			String vSQL = "select * from permissao";

			if(id != null && nome.equals("")){
				vSQL = vSQL + " where id =" +id+ " ";
			}else if((!nome.equals("")) && id == null ) {
				vSQL = vSQL + " where nome = '"+nome+"'";
			}else if(!(nome.equals("") && id == null)) {
				vSQL= vSQL + " where id =" +id+ "  and nome = '" +nome+ "'";
			}
			
			
			
			List<Permissao> vListaPermissao = new ArrayList<Permissao>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
			    Permissao vPermissao = new Permissao();
			    vPermissao.setId(rs.getInt("id"));
			    vPermissao.setNome(rs.getString("nome"));
			    vPermissao.setDescricao(rs.getString("descricao"));   
			    vPermissao.setAtivo(rs.getBoolean("ativo"));
			    vListaPermissao.add(vPermissao);
			}
			rs.close();
			st.close();
			
			return vListaPermissao;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função FILTRO! \n"+"Erro: "+e.getMessage());
			return null;
		} 
    }
}
