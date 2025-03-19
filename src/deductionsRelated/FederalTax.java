//-------------------------------------------
// Assignment 2
// Written by: Valeria Zadoinov, ID: 40285494
//-------------------------------------------

package deductionsRelated;

public class FederalTax extends Deductions{

    @Override
    public double calculateTax(double salary) {
        if (salary < 16129){
            return 0;
        } else if (salary <= 57375){
            return salary*0.15;
        } else if (salary <= 114750){
            return salary*0.205;
        } else if (salary <= 177882){
            return salary*0.26;
        } else if (salary <= 253414){
            return salary*0.29;
        } else {
            return salary*0.33;
        }
    }
}
