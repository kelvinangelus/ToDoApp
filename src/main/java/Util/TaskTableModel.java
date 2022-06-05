/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Model.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kelvin Angelus
 */
public class TaskTableModel extends AbstractTableModel{

    String[] columns = {"Nome", "Descri��o", "Prazo", "Tarefa conclu�da", "Editar", "Excluir"};
    
    List<Task> tasks = new ArrayList(); //Cria��o de uma lista de tarefas 
    
    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
  
    @Override
    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex){
        
        return columnIndex == 3;
        
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        
        if(tasks.isEmpty()){
            return Object.class;
        }
        return this.getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            case 0:
                return tasks.get(rowIndex).getName(); //Retorna o nome do projeto selecionado
            case 1:
                return tasks.get(rowIndex).getDescription(); //Retorna a descri��o do projeto selecionado
            case 2:
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(tasks.get(rowIndex).getDeadLine()); //Retorna o prazo do projeto selecionado
            case 3:
                return tasks.get(rowIndex).isIsCompleted(); //Retorna se o projeto est� conclu�do
            case 4:
                return "";
            case 5:
                return "";
            default:
                return "Dados n�o encontrados";
            
        }
    }

    public String[] getColums() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        
        tasks.get(rowIndex).setIsCompleted((boolean) aValue);        
        
    }
    
}