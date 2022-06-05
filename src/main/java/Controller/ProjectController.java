/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Project;
import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kelvin Angelus
 */
public class ProjectController {
    
    //Método salvar novo projeto no banco de dados
    public void save(Project project) {
        String SQL = "INSERT INTO projects (name,"
                + "description,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            statement = connection.prepareStatement(SQL);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            
            statement.execute();
            
        }catch(SQLException Ex){
            throw new RuntimeException("Erro ao salvar o projeto", Ex);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void update (Project project){
        String SQL = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(SQL);
            
            //Setando os valores do statement
//            statement.setInt(1, project.getId());
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            
            statement.execute();
            
        }catch(SQLException Ex){
            throw new RuntimeException("Erro ao atualizar o projeto", Ex);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void removeById(int idProject){
        String SQL = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            statement = connection.prepareStatement(SQL);
            
            statement.setInt(1, idProject);
            
            statement.execute();
            
        }catch(SQLException Ex){
            throw new RuntimeException("Erro ao apagar o projeto", Ex);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Project> getAll(){
        String SQL = "SELECT * FROM projects";
            
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            
        //Lista de projetos que será devolvida quando a chamada do método acontecer
        List<Project> projects = new ArrayList<>();
            
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(SQL);
            
            //statement.setInt(1, id);
            
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                projects.add(project);
                
            } 
        }catch(SQLException Ex){
            throw new RuntimeException("Erro ao inserir o projeto", Ex);
        }finally{
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return projects;
    }   
}
