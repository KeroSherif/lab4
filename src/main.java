/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author marina sherif
 */
import java.time.LocalDate;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//         CustomerProduct newPurchase =CustomerProduct.getCustomerProductFromUser();
//        System.out.println("Purchase saved: " + newPurchase.lineRepresentation());
        CustomerProductDatabase db = new CustomerProductDatabase("CustomersProducts.txt");

        // نقرأ البيانات القديمة (لو فيه فايل موجود)
        db.readFromFile();

        // ناخد عملية جديدة من المستخدم
        CustomerProduct newPurchase = CustomerProduct.getCustomerProductFromUser();

        // نضيفها
        db.insertRecord(newPurchase);

        // نحفظ التحديثات في الملف
        db.saveToFile();

        // نعرض اللي جوه قاعدة البيانات بعد التحديث
        System.out.println("\nAll records in file:");
        for (CustomerProduct cp : db.returnAllRecords()) {
            System.out.println(cp.lineRepresentation());
        }
    }
}
