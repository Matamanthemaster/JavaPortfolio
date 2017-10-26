import java.util.Scanner;
import java.util.InputMismatchException;

class agein2050
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    int birthYear = 0;

    try
    {
      //try to input their birth year and catch errors
      System.out.println("Enter your birth year.");
      System.out.print("Year: ");
      birthYear = input.nextInt();
    }
    catch (InputMismatchException e)
    {
      //Catch an InputMismatchException, they inputted the wrong type. Exit the program.
      System.out.println("You need to enter a birth year as a number.");
      System.exit(0);
    }

    if (birthYear < 2050 && birthYear > -1)
    {
      //get age in 2050 by subtraction.
      int endAge = 2050 - birthYear;

      //output.
      System.out.println("In 2050 you will be " + endAge);
    }
    else
    {
      //Tell the user their input was invalid, exit program.
      System.out.println("You need to enter a valid year (Between 0 and 2049).");
      System.exit(0);
    }
  }
}
