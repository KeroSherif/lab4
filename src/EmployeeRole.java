import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class EmployeeRole {


    
    private productDatabase productDatabase;
    private CustomerProductDatabase customerProductDatabase;


    
    public EmployeeRole() {
        productDatabase = new productDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
    }


    
  public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
    
    if (productID == null || productID.trim().isEmpty()) {
        System.err.println("Product ID cannot be empty.");    
        return;
    }
    
      if (quantity < 0) {
        System.err.println(" Quantity cannot be negative.");
        return;
    }    

    if (productDatabase.contains(productID)) {
        System.err.println(" Product with ID " + productID + " already exists.");
        return;
    }
    
    Product newProduct = new Product(productID, productName, manufacturerName, supplierName, quantity, 0.0f);
    
    productDatabase.insertRecord(newProduct);
    
  }

    public Product[] getListOfProducts() {
  
        return productDatabase.returnAllRecords().toArray(new Product[0]);
    
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
    
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
       
        if (customerSSN == null || customerSSN.trim().isEmpty()) {
        
            System.err.println(" Customer SSN is required.");
            
            return false;
        
        }
        
        if (purchaseDate.isAfter(LocalDate.now())) {
        
            System.err.println(" Purchase date cannot be in the future.");
            
            return false;
        
        }

        Product product = productDatabase.getRecord(productID);
        
        if (product == null || product.getQuantity() <= 0) {

            System.err.println("Product not available or out of stock.");
            
            return false;
        
        }

        product.setQuantity(product.getQuantity() - 1);
        
        CustomerProduct newPurchase = new CustomerProduct(customerSSN, productID, purchaseDate);
        
        customerProductDatabase.insertRecord(newPurchase);
        
        return true;
    
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
    
        if (returnDate.isBefore(purchaseDate)) {
        
            System.err.println("Return date cannot be before purchase date.");
            
            return -1;
        
        }
        
        long days = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        
        if (days > 14) {
        
            System.err.println(" Return period exceeded (max 14 days).");
            
            return -1;
        
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        String searchKey = customerSSN + "," + productID + "," + purchaseDate.format(formatter);

        Product product = productDatabase.getRecord(productID);
        
        if (product == null || !customerProductDatabase.contains(searchKey)) {
        
            System.err.println(" Purchase record not found.");
            
            return -1;
        
        }

        product.setQuantity(product.getQuantity() + 1);
        
        customerProductDatabase.deleteRecord(searchKey);
        
        return product.getPrice();
    
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

