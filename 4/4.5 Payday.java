import java.util.Scanner;
import java.util.InputMismatchException;

class payday
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    //inputs
    String dayCode = "";
    Double hoursNormal = 0.0;
    Double hoursOvertime = 0.0;

    //Processing & outputs
    int rateNormal = 0;
    int rateOvertime = 0;
    String day = "";
    Double payNormal = 0.0;
    Double payOvertime = 0.0;

    System.out.println("Input a day code (wk for other day, sun for sunday).");

    try
    {
      //convert the next string to lower case
      dayCode = input.next().toLowerCase();

      System.out.print("Normal Hours: ");
      hoursNormal = input.nextDouble();

      System.out.print("Overtime Hours: ");
      hoursOvertime = input.nextDouble();
    }
    catch (InputMismatchException e)
    {
      //InputMismatchException cannot happen on string, so user must have not entered hours correctly
      System.out.println("Normal hours and overtime hours must be numbers.");
      System.exit(0);
    }

    //if hours are >= 0 and <= 168 (number of hours in a week) and so is valid
    if (hoursNormal > -1 && hoursOvertime > -1 && hoursNormal < 169 && hoursOvertime < 169)
    {
      if (dayCode.equals("sun"))
      {
        day = "sunday";

        //sunday pay rates
        rateNormal = 15;
        rateOvertime = 20;
      }
      else if (dayCode.equals("wk"))
      {
        day = "other day";

        //other day pay rates
        rateNormal = 8;
        rateOvertime = 12;
      }
      else
      {
        //invalid day code (not wk or sun)
        System.out.println("You provided an invalid day code.");
        System.exit(0);
      }

      //calculates the pay for the work hours done (using rates for respective day)
      payNormal = rateNormal * hoursNormal;
      payOvertime = rateOvertime * hoursOvertime;

      //print Day, Normal Pay, Overtime Pay, Total Pay
      System.out.println("Day: " + day + ", Normal Pay: " + payNormal + ", Overtime Pay: " + payOvertime + ", Total Pay: " + (payNormal + payOvertime));

    }
    else
    {
      //if the hours entered are < 0 || > 168
      System.out.println("Hours must be between 0 and 168");
    }
  }
}
