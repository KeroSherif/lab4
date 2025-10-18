import java.time.LocalDate;
import java.util.Scanner;

public class InputHelper {

    private static final Scanner sc = new Scanner(System.in);

    public static CustomerProduct getCustomerProductFromUser() {
        System.out.print("Enter customer SSN: ");
        String ssn = sc.nextLine();

        System.out.print("Enter product ID: ");
        String productId = sc.nextLine();

        System.out.print("Enter purchase date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        return new CustomerProduct(ssn, productId, date);
    }

    public static Product getProductFromUser() {
        System.out.print("Enter product ID: ");
        String id = sc.nextLine();

        System.out.print("Enter product name: ");
        String name = sc.nextLine();

        System.out.print("Enter manufacturer name: ");
        String manufacturer = sc.nextLine();

        System.out.print("Enter supplier name: ");
        String supplier = sc.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Enter price: ");
        float price = Float.parseFloat(sc.nextLine());

        return new Product(id, name, manufacturer, supplier, quantity, price);
    }

    public static EmployeeUser getEmployeeFromUser() {
        System.out.print("Enter employee ID: ");
        String id = sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        return new EmployeeUser(id, name, email, address, phone);
    }
}
