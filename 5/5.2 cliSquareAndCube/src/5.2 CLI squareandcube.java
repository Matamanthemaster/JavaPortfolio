import java.util.Scanner;
import java.util.InputMismatchException;

class cliSquareAndCube
{
  public static void main(String[] args)
  {
    //variable for gettint values from the console
    Scanner input = new Scanner(System.in);

    //array to hold the results.
    double[] results = new double[7];

    //variables to be inputted by the user.
    double base = 0;
    double exp = 0;

    //loop 1 to calculate exponential functions from user data.
    for (int i = 0; i < 7; i++)
    {
      try
      {
        //input the number base.
        System.out.print("Exponential Function " + i + " Base: ");
        base = input.nextDouble();

        //input the exponent.
        System.out.print("Exponential Function " + i + " Exponent: ");
        exp = input.nextDouble();
      }
      catch (InputMismatchException e)
      {
        //when the user enters an incorrect type (not an int), and Scanner throws the InputMismatchException

        //exit and tell the user why.
        System.out.println("Bases and Exponents should only be numbers");
        System.exit(0);

      }

      //after the try catch for this loop, calculate the exponent based on the user input.
      results[i] = Math.pow(base, exp);

    }



    //create whitespace and heading in terminal
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("Results:");

    //loop 2 to display the results of the exponential functions
    for (int i = 0; i < 7; i++)
    {
      System.out.println(i + " " + results[i]);
    }

    System.out.println("End of Results.");
  }
}
