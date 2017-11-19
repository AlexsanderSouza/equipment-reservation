/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import model.Alerta;
import model.ENTITY.Unidade;

/**
 *
 * @author WigorPaulo
 */
public class DaoUnidade {
	Alerta vAlerta = new Alerta();
	
	
    public List<Unidade> listar() throws Exception{
        
        List<Unidade> vListaUnidade = new ArrayList<Unidade>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("SELECT uni.id, uni.nome, uni.email, uni.telefone, uni.endereco, uni.ativo, uni.id_instituicao, inst.nome as nome_instituicao FROM unidade uni left join instituicao inst on uni.id_instituicao = inst.id");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            Unidade vUnidade = new Unidade();
            vUnidade.setId(rs.getInt("id"));
            vUnidade.setNome(rs.getString("nome"));
            vUnidade.setEmail(rs.getString("email"));
            vUnidade.setTelefone(rs.getString("telefone"));            
            vUnidade.setEndereco(rs.getString("endereco"));
            vUnidade.setAtivo(rs.getBoolean("ativo"));
            vUnidade.setInstituicao(rs.getString("nome_instituicao")); 
            vListaUnidade.add(vUnidade);
            
        }
        rs.close();
        st.close();
        
        return vListaUnidade;       
    }

	
	public int inserir(Unidade pUnidade){
        try {
            String vSQL = "INSERT INTO unidade(id, nome, email, telefone, endereco, ativo, id_instituicao) "
                                      +"VALUES(?, ?, ?, ?, ?, ?, ?);";
            int lastId = 0;
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, Integer.toString(pUnidade.getId()));
            st.setString(2, pUnidade.getNome());
            st.setString(3, pUnidade.getEmail());
            st.setString(4, pUnidade.getTelefone());
            st.setString(5, pUnidade.getEndereco());
            st.setBoolean(6, pUnidade.getAtivo());
            st.setString(7, pUnidade.getInstituicao());
                        
            st.execute();
            final ResultSet rs = st.getGeneratedKeys();  //atribui o id gerado
			if (rs.next()) {
			     lastId = rs.getInt(1);
			}
            st.close();
           System.out.println("inserido"); 
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            return lastId;
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        	return 0;
        }    
    }
    
	public void alterar(Unidade pUnidade) {

		try {

			int permissaoAtiva;

			if (pUnidade.getAtivo() == true) {
				permissaoAtiva = 1;
			} else {
				permissaoAtiva = 0;
			}

			String vSQL = "UPDATE unidade SET `nome`='" + pUnidade.getNome()
    		+ "',`email`='" + pUnidade.getEmail() + "', `telefone`='" + pUnidade.getTelefone() + "', `endereco`='" + pUnidade.getEndereco() + "', `id_instituicao`='" + Integer.parseInt(pUnidade.getInstituicao())
    		+ "',`ativo`='" + permissaoAtiva + "' WHERE `id` ='" + pUnidade.getId() + "'";
    		System.out.println(vSQL);
    		System.out.println(pUnidade.getAtivo());
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
	
	public void excluir(Unidade pUnidade) {
    	try {
    		
			String vSQL = "DELETE FROM unidade WHERE `id`='" + pUnidade.getId() +"'";
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
	
	public List<Unidade> filtrar(Integer id,String nome) {
    	try {
    		
			String vSQL = "SELECT uni.id, uni.nome, uni.email, uni.telefone, uni.endereco, uni.ativo, uni.id_instituicao, inst.nome as nome_instituicao FROM unidade uni left join instituicao inst on uni.id_instituicao = inst.id where uni.id !=0";
			               
			if(id != null){
				vSQL = vSQL + " and uni.id = "+ id + "";
			}
			
			if(!nome.equals("")){
				vSQL = vSQL + " and uni.nome like '%"+nome+"%'";
			}
			
			
			List<Unidade> vListaUnidade = new ArrayList<Unidade>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
			    Unidade vUnidade = new Unidade();
			    vUnidade.setId(rs.getInt("id"));
			    vUnidade.setNome(rs.getString("nome"));
			    vUnidade.setEmail(rs.getString("email"));
			    vUnidade.setTelefone(rs.getString("telefone"));
			    vUnidade.setEndereco(rs.getString("endereco"));
			    vUnidade.setInstituicao(rs.getString("nome_instituicao"));
			    vUnidade.setAtivo(rs.getBoolean("ativo"));
			    			    
			    vListaUnidade.add(vUnidade);
			}
			rs.close();
			st.close();
			
			return vListaUnidade;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Função FILTRO! \n"+"Erro: "+e.getMessage());
			return null;
		} 
    }
	
	
}
