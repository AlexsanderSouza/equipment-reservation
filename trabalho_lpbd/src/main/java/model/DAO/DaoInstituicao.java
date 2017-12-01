package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import model.Alerta;
import model.ENTITY.Instituicao;

public class DaoInstituicao {
	
Alerta vAlerta = new Alerta();

    
    public List<Instituicao> listar() throws Exception{
        
        List<Instituicao> vListaInstituicao = new ArrayList<Instituicao>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from instituicao");  //estava selecionando de funcao kk
        ResultSet rs = st.getResultSet();
        while(rs.next()){
        	Instituicao vInstituicao = new Instituicao();
        	vInstituicao.setId(rs.getInt("id"));
        	vInstituicao.setNome(rs.getString("nome"));
        	vInstituicao.setEmail(rs.getString("email"));
        	vInstituicao.setTelefone(rs.getString("telefone"));
            vListaInstituicao.add(vInstituicao);
        }
        rs.close();
        st.close();
        
        return vListaInstituicao;       
    }

    
    public int inserir(Instituicao pInstituicao){
        try {
            String vSQL = "INSERT INTO instituicao(id, nome, email, telefone) VALUES(?, ?, ?, ?);";
            int lastId = 0;
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, pInstituicao.getId());
            st.setString(2, pInstituicao.getNome());
            st.setString(3, pInstituicao.getEmail());
            st.setString(4, pInstituicao.getTelefone());
            st.execute();
            
            final ResultSet rs = st.getGeneratedKeys();  //atribui o id gerado
			if (rs.next()) {
			     lastId = rs.getInt(1);
			}
            st.close();
           
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            return lastId;
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    return 0;
    }
    
    
    
    public void alterar (Instituicao pinstituicao){
    	
    	try{
    		
    		int permissaoAtiva;
    		
    		if (pinstituicao.getAtivo() == true) {
    			permissaoAtiva =1;	
    		} else{
    			permissaoAtiva = 0;
    			
    		}
    		
    		String vSQL = "UPDATE instituicao SET `nome`='" + pinstituicao.getNome()
    		+ "',`email`='" + pinstituicao.getEmail() + "', `telefone`='" + pinstituicao.getTelefone() 
    		+ "',`ativo`='" + permissaoAtiva + "' WHERE `id` ='" + pinstituicao.getId() + "'";
    		System.out.println(vSQL);
    		System.out.println(pinstituicao.getAtivo());
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
    
    public void excluir(Instituicao pinstituicao){
    	try {
    		
			String vSQL = "DELETE FROM instituicao WHERE `id`='" + pinstituicao.getId() +"'";
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			
			st.execute();
	        st.close();
	        vAlerta.mensagemAlerta("Excluido com Sucesso!");
	        ConexaoDataBase.FecharConexao();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função Excluir em dao_funcao! \n"+"Erro: "+e.getMessage());
		}	
    	
      }
    
    public List<Instituicao> filtrar(Integer id,String nome) {
    	try{
    		
			String vSQL = "select * from instituicao where id != 0";
			
			if(id != null){
				vSQL = vSQL + " and id = "+ id + "";
			}
			
			if(!nome.equals("")){
				vSQL = vSQL + " and nome like '%"+nome+"%'";
			}
			 	
    	List<Instituicao> vListainstituicao = new ArrayList<Instituicao>();
		java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
		st.executeQuery(vSQL);
		ResultSet rs = st.getResultSet();
		while(rs.next()){
		    Instituicao vinstituicao = new Instituicao();
		    vinstituicao.setId(rs.getInt("id"));
		    vinstituicao.setNome(rs.getString("nome")); 
		    vinstituicao.setEmail(rs.getString("email"));
		    vinstituicao.setTelefone(rs.getString("telefone"));
		    vinstituicao.setAtivo(rs.getBoolean("ativo"));
		    
		    vListainstituicao.add(vinstituicao);
		}
		rs.close();
		st.close();
		
		return vListainstituicao;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		vAlerta.mensagemAlerta("Erro na Função FILTRO! \n"+"Erro: "+e.getMessage());
		return null;
	} 

       }



}
