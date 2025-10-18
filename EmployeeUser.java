/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DANAH
 */
public class EmployeeUser {
   private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    
   public EmployeeUser(String employeeId, String name, String email, String address,String phoneNumber){
     this.employeeId = employeeId;
     this.name = name;
     this.email = email;
     this.address = address;
     this.phoneNumber = phoneNumber;
}
   public String lineRepresentation() {
        return employeeId + "," + name + "," + email + "," + address + "," + phoneNumber;
    }

    
    public String getSearchKey() {
        return employeeId;
    }
    
}

