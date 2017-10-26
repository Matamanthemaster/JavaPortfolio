import java.util.Scanner;
import java.util.InputMismatchException;

class incomeExpenditure
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    //vars to be inputted by scanner and for processing.
    Double threshold = 0.0;
    Double income = 0.0;
    Double expenditure = 0.0;
    Double total = 0.0;

    try
    {
      //input vars
      System.out.print("Threshold: ");
      threshold = input.nextDouble();
      System.out.print("Income: ");
      income = input.nextDouble();
      System.out.print("Expenditure: ");
      expenditure = input.nextDouble();
    }
    catch (InputMismatchException e)
    {
      //if invalid type then exit program.
      System.out.println("You need to input a number (whole or decimal).");
      System.exit(0);
    }

    //check all variables inputted are valid (>= 0)
    if (income >= 0 && expenditure >= 0)
    {
      //calculate total money left.
      total = income - expenditure;

      if (total < threshold)
      {
        //outcome
        System.out.println();//blank line to seperate inputs and outputs
        System.out.println("Put some money in the bank!");
      }
      else
      {
        //outcome
        System.out.println();//blank line to seperate inputs and outputs
        System.out.println("SPEND SPEND SPEND!");
      }
    }
    else
    {
      //if income and expenditure is < 0
      System.out.println("Incomes and Expenditures must be positive.");
    }

  }
}
