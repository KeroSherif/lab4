/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author monic
 */
import java.util.ArrayList;

public class productDatabase extends DataBase {
    public productDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] p = line.split(",");
        if (p.length == 6) {
            try {
                return new Product(p[0], p[1], p[2], p[3], Integer.parseInt(p[4]), Float.parseFloat(p[5]));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public Product getRecord(String key) {
        for (Record r : records) {
            if (r.getSearchKey().equals(key)) {
                return (Product) r;
            }
        }
        return null;
    }

    public ArrayList<Product> returnAllRecords() {
        ArrayList<Product> list = new ArrayList<>();
        for (Record r : records) {
            list.add((Product) r);
        }
        return list;
    }

    public void insertRecord(Product p) {
        records.add(p);
    }
}

