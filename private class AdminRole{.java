private class AdminRole{
    private EmployeeUserDataBase database;
    
    public AdminRole(){
        this.database = new EmployeeUserDataBase("Employees.txt");
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber){
        EmployeeUser newUser = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertToRecord(newUser);
    }

    public EmployeeUser[] employeeUser(){
        return database.returnAllRecord().toArray(new EmployeeUser[0]);
    }

    public void removeEmployee(String key){
        database.deleteRecord(key);
    }

    public void logout(){
        database.saveToFile();
    }

}

public class EmployeeRole{
    
    private ProductDatabase productDatabase;
    private CustomerProductDatabase customerProductDatabase;
    
    public EmployeeRole(){
        
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productDatabase = new ProductDatabase("Products.txt");

    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity){
        Product newProduct = new Product(String productID, String productName, String manufacturerName, String supplierName, int quantity);
        productDatabase.insertRecord(newProduct);
    }

    public Product[] getListOfProducts(){
        return ProductDatabase.returnAllRecords().toArray(newProduct[0]);
    }

    boolean CustomerProduct[] getListOfPurchasingOperations(){
        return CustomerProductDatabase.returnAllRecords().toArray(newCustomerPurchase[0]);
    }

    public boolean purchaseProduct(String customerSSN, StringproductID, LocalDate purchaseDate){
        Product product = productDatabase.getrecord(productID);
        if(product != null && product.getQuantity() > 0){
            product.setQuantity(product.getQuantity() - 1);
            CustomerProduct newPurchase = new CustomerProduct(customerSSN, productID, purchaseDate);
            customerProductDatabase.insertRecord(newPurchase);
            return true;
        }
        return false;
    }

  
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        long daysSincePurchase = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (returnDate.isBefore(purchaseDate) || daysSincePurchase > 14) {
            return -1;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String purchaseRecordKey = customerSSN + "," + productID + "," + purchaseDate.format(formatter);
        if (!customerProductDatabase.contains(purchaseRecordKey)) {
            return -1; 
        }
        Product product = productsDatabase.getRecord(productID);
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(purchaseRecordKey);
        return product.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDatepurchaseDate){
        ArrayList<CustomerProduct> allPurchases = customerProductDatabase.returnAllRecords();
        for(int i = 0; i < allPurchases.size(); i++){
            CustomerProduct c = allPurchases.get(i);

            if(c.getCustomerSSN().equals(customerSSN) && c.getPurchaseData().equals(PurchaseData)){
                if(c.isPaid()){
                    c.setPaid(true);
                    return true;
                }
            }
        }

        return false;

    }

    public void logout(){
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}