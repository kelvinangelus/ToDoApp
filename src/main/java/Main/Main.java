/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

//Quando uma classe está fora do package (namespace) da classe corrente, 
//é necessário utilizar o comando import (using)

package Main;

import Controller.ProjectController;
import Model.Project;
import Util.ConnectionFactory;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author aluno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    /*    
        //Declaração de um objeto Connection que recebe o  
        //connection retornado pelo método getConnection()
        Connection c = ConnectionFactory.getConnection();  
        
        //Fechamento da conexão c executada pelo método 
        //closeConnection da classe ConnectionFactory
        ConnectionFactory.closeConnection(c);
    */
    
    /*
    //Teste Criação de um novo projeto
    ProjectController projectController = new ProjectController();
    
    Project project = new Project();
    project.setName("Projeto Teste");
    project.setDescription("description");
    projectController.save(project);
    */
    
    
    //Teste Atualização do nome do projeto
    ProjectController projectController = new ProjectController();
    
    Project project = new Project();
    project.setId(1);
    project.setName("Novo nome do projeto");
    project.setDescription("description");
    projectController.update(project);
    
    List<Project> projects = projectController.getAll();
    System.out.println("Total de projetos: " + projects.size());
    
    
    //Teste apagar projeto
    //ProjectController projectController = new ProjectController();
    projectController.removeById(1);
    
    }
    
    
}
