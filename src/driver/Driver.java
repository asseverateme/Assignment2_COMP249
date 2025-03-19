//-------------------------------------------
// Assignment 2
// Written by: Valeria Zadoinov, ID: 40285494
//-------------------------------------------
/*This program has the purpose of creating a payroll report for the iDroid Solutions company.
The program reads the payroll file payroll.txt with the following information: employee number, first and last name, hours worked and wage.
Then, the program sorts through the lines in this file and finds error lines: ex: a letter where a double should be, etc., and a custom error
for the wage invalid input (MinimumWageException) which checks for the minimum wage to not be less than $15.75.
The error lines will be written to a separate error file payrollError.txt. The valid lines will be used in creating the Employee objects which will be put in an array,
which will be ultimately used to calculate the deductions, net salary, and put into a report file payrollReport.txt.
*/
package driver;

// Import packages
import employeeRelated.*;
import CustomExceptions.*;
import deductionsRelated.*;

// Import everything to read/write to/from file
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Driver {

    // Method that checks the wage to not be less than $15.75 and which will throw the custom MinimumWageException in case the wage is invalid
    public static void checkValidWage(double wage) throws MinimumWageException {
        if(wage < 15.75){
            throw new MinimumWageException();
        }
    }

    // Method that calculates the deductions. Creating objects of each child class that extends its parent class Deductions. Calculating the tax for each deduction, returning their sum.
    public static double calculateDeductions(double grossSalary) {
        EmploymentInsurance employmentInsurance = new EmploymentInsurance();
        FederalTax federalTax = new FederalTax();
        ProvincialTax provincialTax = new ProvincialTax();
        QPIP qpip = new QPIP();
        QPP qpp = new QPP();

        return employmentInsurance.calculateTax(grossSalary)
                + federalTax.calculateTax(grossSalary)
                + provincialTax.calculateTax(grossSalary)
                + qpip.calculateTax(grossSalary)
                + qpp.calculateTax(grossSalary);
    }

    public static void main(String[] args) {

        // Initializing the Scanner and PrintWriter to null
        Scanner inputStream = null;
        PrintWriter outputStream = null;

        int nrOfLines = 0; // keeping track of the nr of lines in the payroll.txt

        // Open the file to check for valid data and count the number of lines do determine array size
        // The number of error lines will be subtracted from the number of total lines
        // The error lines will be written to the payrollError.txt
        try {
            inputStream = new Scanner(new FileInputStream("payroll.txt"));

            // Counting the number of lines in the document (this will determine the number of objects in the array of employees
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                nrOfLines ++;
            }
            inputStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        int arrayOfEmployeesSize = nrOfLines;   // Assigning the number of lines to the size of array

        // Creating the arrayOfEmployees
        // Ultimately another array will be created, the size of which will be adjusted to the number of lines that do not contain any errors
        // This will be done to avoid checking for error lines twice
        Employee[] arrayOfEmployees = new Employee[arrayOfEmployeesSize];

        int nrOfErrorLines = 0; // will count the nr of error lines

        // Reading the file payroll.txt again, this time to add the information to the arrayOfEmployees
        // Any error lines will be added to payrollError.txt which will be created automatically
        // Checking each data attribute on the line before adding it to the array
        try {   // the outer try-catch which will catch errors regarding opening files
            System.out.println(">Opening file payroll...");
            System.out.println();
            System.out.println(">Reading file payroll...");
            System.out.println();
            inputStream = new Scanner(new FileInputStream("payroll.txt")); // opening payroll.txt
            outputStream = new PrintWriter(new FileOutputStream("payrollError.txt",true));  // opening/creating payrollError.txt

            System.out.println(">Error lines found in file payroll:");
            System.out.println();

            int index = 0; // used to manipulate arrayOfEmployess

            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine(); // reading each line
                // Using a separate scanner to read each line to avoid affecting the main Scanner inputStream and to avoid any mistakes carrier to the next iteration
                Scanner lineScanner = new Scanner(line);

                try {   // the nested try-catch which handles each line for invalid input
                    // Check for every exception, if at least one is invalid, it will throw InputMismatchException and the line will
                    // be written to the error file
                    // If the line is valid, it will be added to arrayOfEmployees

                    // Validate employee number, must be of type long
                    if(!lineScanner.hasNextLong()){
                        throw new InputMismatchException("The employee number is invalid");
                    }
                    long employeeNr = lineScanner.nextLong(); // creating an attribute with the read portion which will be added to the array of employees

                    // Validate first name
                    if(!lineScanner.hasNext()){
                        throw new InputMismatchException("Missing first name.");
                    }
                    String firstName = lineScanner.next();

                    // Validate last name
                    if(!lineScanner.hasNext()){
                        throw new InputMismatchException("Missing last name.");
                    }
                    String lastName = lineScanner.next();

                    // Validate hours worked
                    if(!lineScanner.hasNextDouble()){
                        throw new InputMismatchException("Invalid input for hours worked.");
                    }
                    double hoursWorked = lineScanner.nextDouble();

                    // Validate hourly wage, throw custom Exception
                    if(!lineScanner.hasNextDouble()){
                        throw new InputMismatchException("Invalid input for hourly wage.");
                    }
                    double hourlyWage = lineScanner.nextDouble();
                    checkValidWage(hourlyWage); // check if the hourly wage is not less than $15.75

                    // Creating an object using the information from the line and adding it to arrayOfEmployees
                    arrayOfEmployees[index++] = new Employee(employeeNr,firstName,lastName,hoursWorked,hourlyWage);
                }
                catch (InputMismatchException | MinimumWageException e) {
                    System.out.println("Error found on the following line: " + line);
                    System.out.println("Reason: " + e.getMessage() + ". The line will be written to the error file.");
                    nrOfErrorLines++;   // counting the nr of error lines
                    outputStream.println(line); // writing the error line to the error file
                    System.out.println();
                }

                lineScanner.close();
            }
            outputStream.close();
            inputStream.close();
        }
        catch (IOException e) {
            System.out.println("Something went wrong.");
        }


        System.out.println("Therefore, the following lines were written to the error file:");
        System.out.println();

        // Print the error file to the screen
        try{
            inputStream = new Scanner(new FileInputStream("payrollError.txt")); // opening the file with errors
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                System.out.println(line);
            }

            inputStream.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
        }


        // Print the objects from array TO TEST
//        for (int i = 0; i < arrayOfEmployees.length; i++) {
//            if(arrayOfEmployees[i] != null) {
//                System.out.println(arrayOfEmployees[i].toString());
//            }
//        }

        // Creating a new array without any null objects
        Employee[] arrayOfEmployeesUpdated = new Employee[nrOfLines-nrOfErrorLines];
        int validIndex = 0; // Track position in the new array
        for (int i = 0; i < arrayOfEmployees.length; i++) {
            if(arrayOfEmployees[i] != null){
                arrayOfEmployeesUpdated[validIndex++] = arrayOfEmployees[i];
            }
        }

        // Displaying general information
        System.out.println();
        System.out.println(">" + nrOfLines + " lines read from file payroll.");
        System.out.println();
        System.out.println(">" + nrOfErrorLines + " lines written to error file." );
        System.out.println();
        System.out.println(">Calculating deductions.");
        System.out.println();
        System.out.println(">Writing report file");
        System.out.println();



        // Create report file and print it to the screen
        try {
            outputStream = new PrintWriter(new FileOutputStream("payrollReport.txt",true));

            outputStream.println("                                  iDroid Solutions                                      ");
            outputStream.println("                                  ----------------                                      ");
            outputStream.printf("%-12s %-10s %-10s %-15s %-15s %-15s%n","Employee Nr.", "First Name", "Last Name", "Gross Salary", "Deductions", "Net Salary");
            outputStream.println("-----------------------------------------------------------------------------");

            // A for-loop that will go through every object in the arrayOfEmployeesUpdated
            for(int i = 0; i<arrayOfEmployeesUpdated.length; i++){
                // Getting all the info from the array to be displayed in the report
                long employeeNr = arrayOfEmployeesUpdated[i].getEmployeeNr();
                String firstName = arrayOfEmployeesUpdated[i].getFirstName();
                String lastName = arrayOfEmployeesUpdated[i].getLastName();
                double grossSalary = arrayOfEmployeesUpdated[i].getGrossSalary();

                // Calculating total deductions
                double deductions = calculateDeductions(grossSalary);
                // Calculating the net salary (gross - deductions)
                double netSalary = grossSalary - deductions;

                // Writing all the information to the report
                outputStream.printf("%-12d %-10s %-10s %-15.2f %-15.2f %-15.2f%n", employeeNr, firstName, lastName, grossSalary, deductions, netSalary);
            }
            outputStream.close();
        }
        catch (IOException e) {
            System.out.println("Something went wrong.");
        }

        // Printing the report to the screen
        try {
            inputStream = new Scanner (new FileInputStream("payrollReport.txt"));

            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
