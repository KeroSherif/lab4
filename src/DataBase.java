/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Kirolos sherif
 */
import java.io.*;
import java.util.ArrayList;

public abstract class DataBase {
    protected ArrayList<Record> records;
    protected String filename;

    public DataBase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
        readFromFile();
    }

    public abstract Record createRecordFrom(String line);

    public void readFromFile() {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
              while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Record rec = createRecordFrom(line);
                    if (rec != null) {
                        records.add(rec);
                    }
                }
            }
        } catch (IOException e){
           
        }
    }
    
    
    
    
            }

   