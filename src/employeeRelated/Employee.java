//-------------------------------------------
// Assignment 2
// Written by: Valeria Zadoinov, ID: 40285494
//-------------------------------------------

package employeeRelated;

public class Employee {

    private long employeeNr;
    private String firstName;
    private String lastName;
    private double hoursWorked;
    private double hourlyWage;
    private double grossSalary;

    // Default constructor
    public Employee() {
        this.employeeNr = 0;
        this.firstName = "Jane";
        this.lastName = "Doe";
        this.hoursWorked = 0;
        this.hourlyWage = 0;
        this.grossSalary = hoursWorked*hoursWorked*52;
    }

    // Parameterized constructor
    public Employee(long employeeNr, String firstName, String lastName, double hoursWorked, double hourlyWage) {
        this.employeeNr = employeeNr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hoursWorked = hoursWorked;
        this.hourlyWage = hourlyWage;
        this.grossSalary = hoursWorked*hoursWorked*52;
    }

    // Getters and Setters
    public long getEmployeeNr() {
        return employeeNr;
    }
    public void setEmployeeNr(long employeeNr) {
        this.employeeNr = employeeNr;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public double getHoursWorked() {
        return hoursWorked;
    }
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    public double getHourlyWage() {
        return hourlyWage;
    }
    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
    public double getGrossSalary(){
        return grossSalary;
    }

    @Override
    public String toString() {
        return "\n" + "Employee Nr: " + employeeNr
        + "\nFirst Name: " + firstName
        + "\nLast Name: " + lastName
        + "\nHours Worked: " + hoursWorked
        + "\nHours Worked: " + hourlyWage
        + "\nGross Salary: " + String.format("%.2f",grossSalary);
    }
}
