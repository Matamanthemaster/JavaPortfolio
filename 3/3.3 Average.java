import java.util.Scanner;
import java.util.InputMismatchException;

class average
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    Double average = 0.0;

    System.out.println("Please input the 4 numbers to mean.");

    for (int i = 0; i < 4; i++)
    {

      try
      {
        System.out.print(i + 1 + ": ");

        //increment by the input.
        average += input.nextDouble();

      }
      catch (InputMismatchException e)
      {
        //catch the user supplying invalid type.
        System.out.println("Value inputed was not a number.");
        System.exit(0);
      }
    }

    average = average / 4;

    //output
    System.out.println("The average of the 4 supplied numbers is " + average + ".");
  }
}
