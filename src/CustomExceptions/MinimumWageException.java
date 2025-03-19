//-------------------------------------------
// Assignment 2
// Written by: Valeria Zadoinov, ID: 40285494
//-------------------------------------------

package CustomExceptions;

public class MinimumWageException extends Exception{
    public MinimumWageException(){
        super("Error: The minimum wage should not be below $15.75");
    }
}
