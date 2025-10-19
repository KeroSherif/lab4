/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/**
 *
 * @author DANAH
 */
public class EmployeeUserDatabase{
    private String fileName;
    private ArrayList<EmployeeUser> records;
    
    public EmployeeUserDatabase(String filename){
        this.fileName = fileName;
        this.records = new ArrayList<>();
    }
    
    public void readFromFile() {
        records.clear();
        try(BufferedReader br =new BufferedReader(new FileReader(fileName))){
            String line;
             while ((line = br.readLine()) != null) {
                EmployeeUser emp = createRecordFrom(line);
                records.add(emp);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    
    public EmployeeUser createRecordFrom(String line){
        String[] parts = line.split(",");
        if (parts.length == 5) {
            return new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
        } else {
            return null;
        }
    }
    
    public ArrayList<EmployeeUser> returnAllRecords(){
       return records; 
    }
    
    public boolean contains(String key ){
     for (EmployeeUser emp : records) {
            if (emp.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
   
    public EmployeeUser getRecord(String key){
        for (EmployeeUser emp : records) {
            if (emp.getSearchKey().equals(key)) {
                return emp;
            }
        }
        return null;
    }
    
   public void insertRecord(EmployeeUser record){
       records.add(record);
   }
   
   public void deleteRecord(String key){
      EmployeeUser toRemove = null;
        for (EmployeeUser emp : records) {
            if (emp.getSearchKey().equals(key)) {
                toRemove = emp;
                break;
            }
        }
        if (toRemove != null) {
            records.remove(toRemove);
        }
    } 
   
   public void saveToFile(){
      try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, false))) {
            for (EmployeeUser emp : records) {
                pw.println(emp.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
} 
    
    
     
    
        
    
    

