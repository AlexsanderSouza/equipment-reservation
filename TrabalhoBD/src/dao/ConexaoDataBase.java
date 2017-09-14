/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author WigorPaulo
 */
public class ConexaoDataBase {
    public static String status = "Não Conectou...";
    
    public ConexaoDataBase() {
 
    }

    public static java.sql.Connection getConexaoMySQL(){
        Connection connection = null;
        
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            
            String serverName = "localhost:3306";
            String mydatabase = "locacao";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root";
            String password = "reis12345";
            
            connection = DriverManager.getConnection(url, username, password);
            
            if (connection != null){
                status = ("STATUS---->Conectado com sucesso!");
                System.out.println(status);
            } else{
                status = ("STATUS---->Não foi possivel realizar conexão");
                System.out.println(status);
            }
            
            return connection;
            
        } catch (ClassNotFoundException e){
            System.out.println("O driver expecificado nao foi encontrado.");
 
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
 
            return null;
        }
    }
    
    public static String statusConection(){
        return status;
    }
    
    public static boolean FecharConexao(){
        try {
            ConexaoDataBase.getConexaoMySQL().close();
            System.out.println("Fechado conexao");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static java.sql.Connection ReiniciarConexao(){
        FecharConexao();
        
        return ConexaoDataBase.getConexaoMySQL();
    }
}
