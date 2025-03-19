//-------------------------------------------
// Assignment 2
// Written by: Valeria Zadoinov, ID: 40285494
//-------------------------------------------

package deductionsRelated;

public class ProvincialTax extends Deductions{

    @Override
    public double calculateTax(double salary) {
        if ((salary > 18571) && (salary <= 53255)){
            return salary*0.14;
        } else if (salary <= 106494){
            return salary*0.19;
        } else if (salary <= 129590){
            return salary*0.24;
        } else if (salary > 129590){
            return salary*0.2575;
        } else {
            return 0;
        }
    }
}
