/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller; //package é o namespace em outras linguagens

import Model.Task; //import é o using em outras linguagens
import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
//import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Kelvin Angelus
 */
    public class TaskController {
    
    public void save(Task task){
        
        String SQL = "INSERT INTO tasks ("
        + "idProject," 
        + "name,"
        + "description,"
        + "completed,"
        + "notes,"   
        + "deadLine,"
        + "createdAt,"
        + "updatedAt) VALUES (?,?,?,?,?,?,?,?)";
           
        Connection connection = null;
        PreparedStatement statement = null; 
        
        Logger logger = LoggerFactory.getLogger(TaskController.class);
        
        try{
            
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection(); //Objeto Connection. 
            
            statement = connection.prepareStatement(SQL);
                      
            statement.setInt(1, task.getIdProject());
            //statement.setInt(1, 3);
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadLine().getTime())); //"getTime" retorna uma data no formato long
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
     
        }catch(Exception ex){
            //logger.error("Erro ao salvar tarefa",ex);
            ex.printStackTrace();
            throw new RuntimeException("Erro ao salvar a tarefa "
                    + ex.getMessage(),ex);
        }
        finally{
            ConnectionFactory.closeConnection(connection, statement); //Package Util, Classe ConnectionFactory
        }
    }
    
    public void update(Task task){
        
        String SQL = "UPDATE tasks SET "
                + "idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "notes = ?, "
                + "completed = ?, "
                + "deadLine = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(SQL);
            
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new Date(task.getDeadLine().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            //Executando a query
            statement.execute();
            
        }catch(Exception Ex){
            throw new RuntimeException("Erro ao atualizar a tarefa " + Ex.getMessage(), Ex);
        }
    }
    
    public void removeById(int taskId){
        
        String SQL = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(SQL);
            
            //Setando os valores
            statement.setInt(1, taskId);
            
            //Excutando a query
            statement.execute();
            
        } catch(Exception ex){
            throw new RuntimeException("Erro ao apagar a tarefa");
        } finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Task> getAll(int idProject) {
        String SQL = "SELECT * FROM tasks WHERE idProject = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //Lista de tarefas que será devolvida quando a chamada do método acontecer
        List<Task> tasks = new ArrayList<Task>();
        
        try{
           //Criação da conexão
           connection = ConnectionFactory.getConnection();
    
           statement = connection.prepareStatement(SQL);
           
           //Setando o valor que corresponde ao filtro de busca
           statement.setInt(1, idProject);
           
           //Valor retornado pela execução da query
           resultSet = statement.executeQuery();
           
           //Enquanto houver valores a serem percorridos no meu resultSet
           while(resultSet.next()){
               
               Task task = new Task();
               task.setId(resultSet.getInt("id"));
               task.setIdProject(resultSet.getInt("idProject"));
               task.setName(resultSet.getString("name"));
               task.setDescription(resultSet.getString("description"));
               task.setNotes(resultSet.getString("notes"));
               task.setIsCompleted(resultSet.getBoolean("completed"));
               task.setDeadLine(resultSet.getDate("deadLine"));
               task.setCreatedAt(resultSet.getDate("createdAt"));
               task.setUpdatedAt(resultSet.getDate("updatedAt"));
               
               tasks.add(task);
               
           }
        }catch(Exception Ex){
            throw new RuntimeException("Erro ao inserir a tarefa " + Ex.getMessage(), Ex);
        }finally{
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        //Lista de tarefas que foi criada e carregada do banco de dados
        return tasks;
    }
}
