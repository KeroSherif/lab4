/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author marina sherif
 */
public class main {

    public static void main(String[] args) {
        // CustomerProduct newPurchase =CustomerProduct.getCustomerProductFromUser();
        
        // System.out.println("Purchase saved: " + newPurchase.lineRepresentation());
        CustomerProductDatabase db = new CustomerProductDatabase("CustomersProducts.txt");

        db.readFromFile();

        CustomerProduct newPurchase = CustomerProduct.getCustomerProductFromUser();

        db.insertRecord(newPurchase);

        db.saveToFile();

        System.out.println("\nAll records in file:");
        for (CustomerProduct cp : db.returnAllRecords()) {
            System.out.println(cp.lineRepresentation());
        }

        System.out.println("✅ All purchases displayed successfully!");
    }
}
