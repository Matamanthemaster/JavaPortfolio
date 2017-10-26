import java.util.Scanner;
import java.util.InputMismatchException;

class money
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    System.out.println("Input the number of notes you have");

    int noFives = 0;
    int noTens = 0;
    int noTwenties = 0;

    try
    {
      //input the quantities of each notes the client has.
      System.out.print("£5: ");
      noFives = input.nextInt();

      System.out.print("£10: ");
      noTens = input.nextInt();

      System.out.print("£20: ");
      noTwenties = input.nextInt();
    }
    catch (InputMismatchException e)
    {
      //Invalid inputs.
      System.out.println("Only whole numbers can be used to represent the quantity of each note.");
      System.exit(0);
    }

    //validation
    if (noFives < 0 || noTens < 0 || noTwenties < 0)
    {
      System.out.println("You cannot have negative quantities of notes.");
      System.exit(0);
    }
    else
    {
      //calculate end numbers for display.
      int totalNotes = noFives + noTens + noTwenties;
      int totalValue = (noFives * 5) + (noTens * 10) + (noTwenties * 20);

      System.out.println("For the " + totalNotes + " notes, you have a total of £" + totalValue + ".");
    }
  }
}
