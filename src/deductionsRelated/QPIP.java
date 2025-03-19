//-------------------------------------------
// Assignment 2
// Written by: Valeria Zadoinov, ID: 40285494
//-------------------------------------------

package deductionsRelated;

public class QPIP extends Deductions{
    private static final double RATE = 0.00494;

    @Override
    public double calculateTax(double salary) {
        return salary*RATE;
    }

}
