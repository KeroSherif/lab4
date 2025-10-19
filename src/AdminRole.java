public class AdminRole {

    private EmployeeUserDatabase database;

    public AdminRole() {
        this.database = new EmployeeUserDatabase("Employees.txt");
        this.database.readFromFile(); // تحميل البيانات من الملف عند البدء
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
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