/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

//import java.net.URL;
import java.sql.Connection; //Pacote "java.sql", classe "Connection"
import java.sql.DriverManager; //Pacote "java.sql", classe "DriverManager"
import java.sql.PreparedStatement; //Pacote "java.sql", classe "PreparedStatement"
import java.sql.ResultSet;

/**
 *
 * @author Kelvin Angelus
 */
public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/ToDoapp";
    public static final String USER = "root";
    public static final String PASS = "";
    
    public static Connection getConnection(){ //Métodos static podem ser chamados
        try{                                  //sem necessitar instanciar a classe  
            Class.forName(DRIVER);            //Carrega o driver "DRIVER" para a aplicação
            return DriverManager.getConnection(URL, USER, PASS);
        }catch(Exception Ex){
            throw new RuntimeException("Erro na conexão com o banco de dados",Ex);
        }
    }
    
    //"static" permite que o método closeConnection possa ser chamado sem 
    //a necessidade de criação de um objeto ConnectionFactory
    public static void closeConnection(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }
        }
        catch(Exception Ex){
            throw new RuntimeException("Erro ao fechar a conexão");
        }        
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement){
        try{
            if (connection != null){
                connection.close();
            }
            
            if(statement != null){                  
                statement.close();
            }
        }
        catch(Exception Ex){
            throw new RuntimeException("Erro ao fechar a conexão");
        }        
    }
    
    public static void closeConnection(Connection connection, 
            PreparedStatement statement, ResultSet resultSet){
        try{
            if (connection != null){
                connection.close();
            }
            
            if(statement != null){                  
                statement.close();
            }
            
            if (resultSet != null){
                resultSet.close();
            }
        }
        catch(Exception Ex){
            throw new RuntimeException("Erro ao fechar a conexão");
        }        
    }
}
