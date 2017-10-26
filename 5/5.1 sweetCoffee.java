import java.util.Scanner;
import java.util.InputMismatchException;

class sweetCoffee
{
  public static void main(String[] args)
  {
    //scanner object to run functions to get data from the cli.
    Scanner input = new Scanner(System.in);

    //prints the expected answers answers
    System.out.println("How do you like to sweeten your coffee?");
    System.out.println("0: I do not");
    System.out.println("1: With Sugar");
    System.out.println("2: With Sweetener");
    System.out.println();

    //vars to store values
    int[] answers = new int[5];
    int nothing = 0, sweetener = 0, sugar = 0;

    //standard for loop, 0-4
    for (int i = 0; i < 5; i++)
    {
      //prints for the input to look pretty with a start of line, also indicates the person who should input.
      System.out.print("Person " + i + ": ");

      //input initial answer

      try
      {

        answers[i] = input.nextInt();

        //if the user enters an invalid number, ask them again until their answer is valid
        while (answers[i] < 0 || answers[i] > 2)
        {
          System.out.println("Please enter 0, 1, or 2.");
          System.out.print("Person " + i + ": ");

          answers[i] = input.nextInt();
        }

      }
      catch (InputMismatchException e)
      {
        System.out.println("Answers must be whole numbers.");
        System.exit(0);
      }

      //count the number of answers
      if (answers[i] == 0)
      {
        nothing++;
      }
      else if (answers[i] == 1)
      {
        sugar++;
      }
      else if (answers[i] == 2)
      {
        sweetener++;
      }
    }

    //output the totals
    System.out.println("of the 5 people, " + nothing + " do not sweeten their coffee, " + sugar + " use sugar and " + sweetener + " use sweetener.");

    //for each user, output their result as a string.
    for (int i = 0; i < answers.length; i++)
    {
      if (answers[i] == 0)
      {
        System.out.println("Person " + i + " does not sweeten coffee.");
      }
      else if (answers[i] == 1)
      {
        System.out.println("Person " + i + " drinks coffee with sugar.");
      }
      else if (answers[i] == 2)
      {
        System.out.println("Person " + i + " drinks coffee with sweetener.");
      }
    }
  }
}
