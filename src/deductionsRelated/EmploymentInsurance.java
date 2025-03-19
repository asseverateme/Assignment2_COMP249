//-------------------------------------------
// Assignment 2
// Written by: Valeria Zadoinov, ID: 40285494
//-------------------------------------------

package deductionsRelated;

public class EmploymentInsurance extends Deductions {
    private static final double RATE = 0.0164;

    @Override
    public double calculateTax(double salary) {
        return salary*RATE;
    }


}
