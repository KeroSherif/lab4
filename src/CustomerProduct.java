import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author marina sherif
 */
public class CustomerProduct {

    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    // Constructor
    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false; // افتراضيًا الشراء مش مدفوع
    }

    // Getters
    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    // Setter
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    // ✅ دالة إدخال بيانات من المستخدم
    public static CustomerProduct getCustomerProductFromUser() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer SSN: ");
        String ssn = sc.nextLine();

        System.out.print("Enter product ID: ");
        String productId = sc.nextLine();

        System.out.print("Enter purchase date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        return new CustomerProduct(ssn, productId, date);
    }

    // ✅ lineRepresentation → السطر اللي هيتكتب في الملف
    public String lineRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter) + "," + paid;
    }

    // ✅ getSearchKey → المفتاح المميز اللي بيتكون من التلات أجزاء
    public String getSearchKey() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
    }
}
