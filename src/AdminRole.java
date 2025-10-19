public class AdminRole {
    private EmployeeUserDatabase database;

    public AdminRole() {
        this.database = new EmployeeUserDatabase("Employees.txt");
    }

    
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
    if (employeeId == null || employeeId.trim().isEmpty()) {
        System.err.println(" Employee ID cannot be empty.");
        return;
    }
 
    if (database.contains(employeeId)) {
        System.err.println(" Employee with ID " + employeeId + " already exists.");
        return;
    }
    EmployeeUser newUser = new EmployeeUser(employeeId, name, email, address, phoneNumber);
    database.insertRecord(newUser);
}

    public EmployeeUser[] getListOfEmployees() {
        return database.returnAllRecords().toArray(new EmployeeUser[0]);
    }

    public void removeEmployee(String key) {
        database.deleteRecord(key);
    }

    public void logout() {
        database.saveToFile();
    }
}
