/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/**
 *
 * @author DANAH
 */

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
            }
        }
        return null;
    }

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
    
    
     
    
        
    
    

