import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Kirolos sherif
 */


public class CustomerProductDatabase extends DataBase {
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] p = line.split(",");
        if (p.length == 4) {
            try {
                LocalDate date = LocalDate.parse(p[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                CustomerProduct cp = new CustomerProduct(p[0], p[1], date);
                cp.setPaid(Boolean.parseBoolean(p[3]));
                return cp;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public CustomerProduct getRecord(String key) {
        for (Record r : records) {
            if (r.getSearchKey().equals(key)) {
                return (CustomerProduct) r;
            }
        }
        return null;
    }

    public ArrayList<CustomerProduct> returnAllRecords() {
        ArrayList<CustomerProduct> list = new ArrayList<>();
        for (Record r : records) {
            list.add((CustomerProduct) r);
        }
        return list;
    }

    public void insertRecord(CustomerProduct cp) {
        records.add(cp);
    }
}