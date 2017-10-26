import java.util.Scanner;

class capitalCity
{
  public static void main(String[] args)
  {

    //Scanner for console input
    Scanner input = new Scanner(System.in);

    //ask the question, preformat.
    System.out.println("What is the capital city of France?");
    System.out.print("> ");

    //set answer to the user input. Don't care about checking for an invalid value as strings can be any input.
    String answer = input.next();

    //changes to lower case
    if (answer.toLowerCase().equals("paris"))
    {
        System.out.println("Congradulations");
    }
    else
    {
      System.out.println("Incorrect");
    }
  }
}
