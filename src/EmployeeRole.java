import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeeRole {
    private productDatabase productDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productDatabase = new productDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
        // السعر غير مطلوب في PDF → نستخدم 0.0f
        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity, 0.0f);
        productDatabase.insertRecord(p);
    }

    public Product[] getListOfProducts() {
        return productDatabase.returnAllRecords().toArray(new Product[0]);
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        Product p = productDatabase.getRecord(productID);
        if (p != null && p.getQuantity() > 0) {
            p.setQuantity(p.getQuantity() - 1);
            customerProductDatabase.insertRecord(new CustomerProduct(customerSSN, productID, purchaseDate));
            return true;
        }
        return false;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) return -1;
        long days = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (days > 14) return -1;

        String key = customerSSN + "," + productID + "," +
                     purchaseDate.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Product p = productDatabase.getRecord(productID);
        if (p == null || !customerProductDatabase.contains(key)) return -1;

        p.setQuantity(p.getQuantity() + 1);
        customerProductDatabase.deleteRecord(key);
        return p.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        for (CustomerProduct cp : customerProductDatabase.returnAllRecords()) {
            if (cp.getCustomerSSN().equals(customerSSN) && cp.getPurchaseDate().equals(purchaseDate)) {
                if (!cp.isPaid()) {
                    cp.setPaid(true);
                    return true;
                }
            }
        }
        return false;
    }

    public void logout() {
        productDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}