import java.util.Scanner;
import java.util.InputMismatchException;

class carpetCost
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    //Input width and length, giving messages to let the user know what is being requested
    System.out.println("Enter carpet length and width in meters.");

    //define and set default doubles for input
    Double length = 0.0, width = 0.0;

    //try to catch InputMismatchException thrown if the input is not the correct type.
    try
    {
      //write the start of lines to symbolise what each input is for.
      System.out.print("Length: ");
      length = input.nextDouble();

      System.out.print("Width: ");
      width = input.nextDouble();

    }
    catch (InputMismatchException e)
    {
      //tell the user their input was not decimal and exit with code 0 (normal)
      System.out.println("You need to input a decimal number.");
      System.exit(0);
    }

    //calculate area and cost of the carpet, where 5 is £5
    Double area = length * width;
    Double cost = area * 5;

    //output results.
    System.out.println("Carpet Area: " + area);
    System.out.println("Carpet Cost : " + cost);

  }
}
