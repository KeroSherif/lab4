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
 * @author marina sherif
 */
public class CustomerProductDatabase {
    private ArrayList<CustomerProduct> records;  // هنا نخزن كل العمليات
    private String filename;                     // اسم الملف

    // Constructor
    public CustomerProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    // 1️⃣ readFromFile() - قراءة كل البيانات من الملف
    public void readFromFile() {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(createRecordFrom(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. A new one will be created when saving.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 2️⃣ createRecordFrom() - تحويل سطر واحد إلى object
    public CustomerProduct createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");
            String ssn = parts[0];
            String productId = parts[1];
            LocalDate date = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            boolean paid = Boolean.parseBoolean(parts[3]);

            CustomerProduct record = new CustomerProduct(ssn, productId, date);
            record.setPaid(paid);
            return record;
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }

    // 3️⃣ returnAllRecords() - ترجّع كل العمليات
    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;
    }

    // 4️⃣ contains() - تتحقق هل العملية دي موجودة ولا لأ
    public boolean contains(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    // 5️⃣ getRecord() - ترجّع عملية معينة
    public CustomerProduct getRecord(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    // 6️⃣ insertRecord() - تضيف عملية جديدة
    public void insertRecord(CustomerProduct record) {
        records.add(record);
    }

    // 7️⃣ deleteRecord() - تمسح عملية معينة
    public void deleteRecord(String key) {
        records.removeIf(record -> record.getSearchKey().equals(key));
    }

    // 8️⃣ saveToFile() - تكتب البيانات كلها تاني في الفايل
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (CustomerProduct record : records) {
                bw.write(record.lineRepresentation());
                bw.newLine();
            }
            System.out.println("✅ Data saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
