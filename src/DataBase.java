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

    
    }

   