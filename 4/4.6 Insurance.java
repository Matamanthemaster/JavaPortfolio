import java.util.Scanner;
import java.util.InputMismatchException;

class insurance
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    //define input variables with default (null) values
    String type = null;
    int age = -1;

    try
    {
      //input variables
      System.out.print("Bike Type: ");
      type = input.next().toLowerCase();//converts the next string entered into the cmd to lower case.

      System.out.print("Your Age: ");
      age = input.nextInt();
    }
    catch (InputMismatchException e)
    {
      //if the type (for input.nextInt()) is not correct, stop the program.
      System.out.println("Age must be a whole number");
      System.exit(0);
    }

    //validate age
    if (age > -1 && age < 151)
    {

      //var stores the final result
      int totalInsurance = 30;

      if (type.equals("mountain"))
      {
        totalInsurance += 10;
      }

      if (age < 25)
      {
        totalInsurance += 5;
      }

      //output result and answers provided by the client.
      System.out.println("For a " + age + " year old with a " + type + " bike, it will cost you " + totalInsurance + " for insurance.");

    }
    else
    {
      //else, if age is invalid, stop program.
      System.out.println("Age must be between 0 and 150");
      System.exit(0);
    }
  }
}
