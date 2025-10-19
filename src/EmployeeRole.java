import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeRole {

    private ProductDatabase productDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        
        // تحميل البيانات من الملفات عند بدء التشغيل
        productDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    // ✅ 1. addProduct بدون السعر (كما ورد في PDF)
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
        // السعر غير مطلوب في المُواصفات، لذا نستخدم 0.0f كقيمة افتراضية
        Product newProduct = new Product(productID, productName, manufacturerName, supplierName, quantity, 0.0f);
        productDatabase.insertRecord(newProduct);
    }

    // ✅ 2. getListOfProducts
    public Product[] getListOfProducts() {
        return productDatabase.returnAllRecords().toArray(new Product[0]);
    }

    // ✅ 3. getListOfPurchasingOperations
    public CustomerProduct[] getListOfPurchasingOperations() {
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    // ✅ 4. purchaseProduct
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        Product product = productDatabase.getRecord(productID);
        if (product != null && product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
            CustomerProduct newPurchase = new CustomerProduct(customerSSN, productID, purchaseDate);
            customerProductDatabase.insertRecord(newPurchase);
            return true;
        }
        return false;
    }

    // ✅ 5. returnProduct
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        // التحقق من صحة التواريخ
        if (returnDate.isBefore(purchaseDate)) {
            return -1;
        }
        long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (daysBetween > 14) {
            return -1;
        }

        // إنشاء مفتاح البحث
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String searchKey = customerSSN + "," + productID + "," + purchaseDate.format(formatter);

        // التحقق من وجود المنتج والعملية
        Product product = productDatabase.getRecord(productID);
        if (product == null || !customerProductDatabase.contains(searchKey)) {
            return -1;
        }

        // تنفيذ الإرجاع
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(searchKey);
        return product.getPrice(); // يُرجع السعر (نفترض أن getPrice() موجود في Product)
    }

    // ✅ 6. applyPayment
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        ArrayList<CustomerProduct> allPurchases = customerProductDatabase.returnAllRecords();
        for (CustomerProduct c : allPurchases) {
            if (c.getCustomerSSN().equals(customerSSN) && c.getPurchaseDate().equals(purchaseDate)) {
                if (!c.isPaid()) { // فقط إذا لم يكن مدفوعًا
                    c.setPaid(true);
                    return true;
                }
            }
        }
        return false;
    }

    // ✅ 7. logout
    public void logout() {
        productDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}