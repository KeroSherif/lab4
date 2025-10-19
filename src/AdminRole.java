public class AdminRole {

    private EmployeeUserDatabase database;

    public AdminRole() {
        this.database = new EmployeeUserDatabase("Employees.txt");
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        // Validation على employeeId
        if (employeeId == null || employeeId.trim().isEmpty()) {
            System.err.println(" Employee ID cannot be empty.");
            return;
        }
        if (database.contains(employeeId)) {
            System.err.println("Employee ID '" + employeeId + "' already exists.");
            return;
        }

        // Validation على name
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Name cannot be empty.");
            return;
        }

        // Validation على email (بسيط)
        if (email == null || email.trim().isEmpty()) {
            System.err.println("Email cannot be empty.");
            return;
        }
        if (!email.contains("@")) {
            System.err.println("Email must contain '@'.");
            return;
        }

        // Validation على address
        if (address == null || address.trim().isEmpty()) {
            System.err.println(" Address cannot be empty.");
            return;
        }

        // Validation على phoneNumber (رقم فقط، طول معقول)
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            System.err.println(" Phone number cannot be empty.");
            return;
        }
        if (!phoneNumber.matches("\\d{10,15}")) {
            System.err.println("Phone number must be 10-15 digits.");
            return;
        }

        // لو كل حاجة صح، أضف الموظف
        EmployeeUser newUser = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(newUser);
        System.out.println("Employee added successfully.");
    }

    public EmployeeUser[] getListOfEmployees() {
        return database.returnAllRecords().toArray(new EmployeeUser[0]);
    }

    public void removeEmployee(String key) {
        if (key == null || key.trim().isEmpty()) {
            System.err.println(" Employee ID cannot be empty.");
            return;
        }
        if (!database.contains(key)) {
            System.err.println(" Employee ID '" + key + "' not found.");
            return;
        }
        database.deleteRecord(key);
        System.out.println(" Employee removed.");
    }

    public void logout() {
        database.saveToFile();
    }
}  








































































