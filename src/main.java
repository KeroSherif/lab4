import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  Inventory Management System - Lab 4");
        System.out.println("========================================");

        // Admin Role
        System.out.println("\n[1] Adding an employee...");
        AdminRole admin = new AdminRole();
        EmployeeUser emp = InputHelper.getEmployeeFromUser();
        admin.addEmployee(emp.getEmployeeId(), emp.getName(), emp.getEmail(), emp.getAddress(), emp.getPhoneNumber());
        System.out.println("✅ Employee added.");

        // Employee Role
        System.out.println("\n[2] Adding a product...");
        EmployeeRole employee = new EmployeeRole();
        Product p = InputHelper.getProductFromUser();
        employee.addProduct(p.getProductID(), p.getProductName(), p.getManufacturerName(), p.getSupplierName(), p.getQuantity());
        System.out.println(" Product added (price ignored per spec).");

        // Purchase
        System.out.println("\n[3] Purchasing a product...");
        CustomerProduct cp = InputHelper.getCustomerProductFromUser();
        boolean bought = employee.purchaseProduct(cp.getCustomerSSN(), cp.getProductID(), cp.getPurchaseDate());
        System.out.println("Purchase result: " + (bought ? "Success " : "Failed  (check product ID!)"));

        // Payment
        boolean paid = employee.applyPayment(cp.getCustomerSSN(), cp.getPurchaseDate());
        System.out.println("Payment applied: " + (paid ? "Yes " : "No "));

        // Return (only if purchase succeeded)
        if (bought) {
            System.out.println("\n[4] Returning product (within 14 days)...");
            double refund = employee.returnProduct(cp.getCustomerSSN(), cp.getProductID(), cp.getPurchaseDate(), LocalDate.now());
            if (refund >= 0) {
                System.out.printf(" Return successful. Refund: %.2f EGP\n", refund);
            } else {
                System.out.println(" Return failed (check date or product).");
            }
        }

        // Save & Exit
        admin.logout();
        employee.logout();
        System.out.println("\n✅ All data saved. System exited.");
        System.out.println("========================================");
    }
}