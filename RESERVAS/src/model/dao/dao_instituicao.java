package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.instituicao;

public class dao_instituicao {
alerta vAlerta = new alerta();
    
    public List<instituicao> listar(instituicao pInstituicao) throws Exception{
        
        List<instituicao> vListaInstituicao = new ArrayList<instituicao>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("select * from funcao");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
        	instituicao vInstituicao = new instituicao();
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

    
    public void inserir(instituicao pInstituicao){
        try {
            String vSQL = "INSERT INTO Instituicao(id, nome, email, telefone) "
                                      +"VALUES(?, ?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pInstituicao.getId()));
            st.setString(2, pInstituicao.getNome());
            st.setString(3, pInstituicao.getEmail());
            st.setString(4, pInstituicao.getTelefone());
                        
            st.execute();
            st.close();
           
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Função INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
}
