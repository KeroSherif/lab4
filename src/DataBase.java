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
          System.err.println("Error writing to file: " + filename); 
        }
    }
    
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Record r : records) {
                pw.println(r.lineRepresentation());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filename);
        }
    }
    
    public void insertRecord(Record record) {
        records.add(record);
    }
    
    
    
    
            }

   