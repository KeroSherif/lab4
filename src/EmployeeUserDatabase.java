/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
<<<<<<< Updated upstream
=======
import java.io.BufferedReader;
>>>>>>> Stashed changes
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/**
 *
 * @author DANAH
 */
<<<<<<< Updated upstream

public class EmployeeUserDatabase extends DataBase {
    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public EmployeeUser createRecordFrom(String line) {
        String[] p = line.split(",");
        if (p.length == 5) {
            return new EmployeeUser(p[0], p[1], p[2], p[3], p[4]);
        }
        return null;
    }

    public EmployeeUser getRecord(String key) {
        for (Record r : records) {
            if (r.getSearchKey().equals(key)) {
                return (EmployeeUser) r;
=======
public class EmployeeUserDatabase {
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
>>>>>>> Stashed changes
            }
        }
        return null;
    }
<<<<<<< Updated upstream

    public ArrayList<EmployeeUser> returnAllRecords() {
        ArrayList<EmployeeUser> list = new ArrayList<>();
        for (Record r : records) {
            list.add((EmployeeUser) r);
        }
        return list;
    }

    public void insertRecord(EmployeeUser e) {
        records.add(e);
    }
    
}
=======
    
   public void insertRecord(EmployeeUser record){
       records.add(record);
   }
   
   
} 
>>>>>>> Stashed changes
    
    
     
    
        
    
    

