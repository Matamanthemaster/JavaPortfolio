import java.util.Scanner;
import java.util.InputMismatchException;

class mealCost
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    Double mealCost = 0.0;
    Double fivePercent = 0.0;

    try
    {
      System.out.println("Input a meal price.");
      System.out.print("£");

      mealCost = input.nextDouble();
    }
    catch (InputMismatchException e)
    {
      System.out.println("You need to input a whole or decimal number.");
      System.exit(0);
    }

    //if positive
    if (mealCost >= 0)
    {
      if (mealCost >= 10)
      {
        //take 5% of the meal cost away
        fivePercent = (mealCost / 100) * 5;
        mealCost -= fivePercent;
      }

      //outcome
      System.out.println("The meal cost £" + mealCost);
    }
    else
    {
      //if mealCost is negative
      System.out.println("Meal costs must be positive.");
    }
  }
}
