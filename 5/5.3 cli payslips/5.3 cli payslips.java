import java.util.Scanner;
import java.util.InputMismatchException;

//Class defines a custom data structure for an array.
class payCalcWork
{
  //define variables for the object.
  public double inHoursWorked;
  public double inRatePay;
  public double outGrossPay;

  //method for calculating the gross pay.
  public void calcGrossPay()
  {
    //hours worked * hourly rate, then assign it to the gross pay of the current object.
    this.outGrossPay = inHoursWorked * inRatePay;
  }
}

//main class
class payslips
{
  public static void main(String[] args)
  {
    //create an array for my class, that has 5 elements.
    CalcWork[] work = new CalcWork[5];

    //create a variable to input data from a terminal.
    Scanner input = new Scanner(System.in);

    for (int i = 0; i < 5; i++)
    {
      //initiate the current array position as my class.
      work[i] = new CalcWork();

      try
      {
        //Input the next hours worked and store it in the current array row, in the hours worked variable.
        System.out.print("[" + i + "] Input Hours Worked: ");
        work[i].inHoursWorked = input.nextDouble();

        //Input next hourly rate, store in same row, in the hourly rate variable.
        System.out.print("[" + i + "] Input Hourly Rate: ");
        work[i].inRatePay = input.nextDouble();

      }
      catch (InputMismatchException e)
      {
        //catch InputMismatchExceptions, generated when inputted value is not the correct datatype (double)
        //inform the user what went wrong and exit.
        System.out.println("Inputted values must be numbers.");
        System.exit(0);
      }

      //invoke the method calcGrossPay for the current array row.
      work[i].calcGrossPay();
    }

    //labels following output.
    System.out.println();
    System.out.println("Gross Pays:");

    //print each gross pay.
    for (int i = 0; i < 5; i++)
    {
      System.out.println("[" + i + "] " + work[i].outGrossPay);
    }
  }
}
