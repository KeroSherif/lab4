/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author marina sherif
 */
import java.io.*;
import java.util.ArrayList;

public abstract class DataBase<T extends Record> {
    protected ArrayList<T> records;
    protected String filename;

    public DataBase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
        readFromFile();
    }

    public abstract T createRecordFrom(String line);

    public void readFromFile() {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    T rec = createRecordFrom(line);
                    if (rec != null) records.add(rec);
                }
            }
        } catch (IOException e) {
            System.err.println("File not found or error reading: " + filename);
        }
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (T record : records) {
                pw.println(record.lineRepresentation());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filename);
        }
    }

    public boolean contains(String key) {
        return getRecord(key) != null;
    }

    public T getRecord(String key) {
        for (T record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(T record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        records.removeIf(r -> r.getSearchKey().equals(key));
    }

    public ArrayList<T> returnAllRecords() {
        return records;
    }
}