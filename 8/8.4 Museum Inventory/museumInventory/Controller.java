package museumInventory;

import java.util.Scanner;
import java.util.InputMismatchException;

class Controller
{
  public static void main(String[] args)
  {
    Scanner in = vehicleMuseum.in;

    String input = "";

    System.out.println("CLI prompt for museumInventory.");
    while (!input.equals("end") && !input.equals("exit"))
    {
      System.out.print("> ");
      input = in.nextLine();

      switch (input)
      {
        case "set museum name":
          System.out.println("Input new Museum Name");
          String name = in.nextLine();
          vehicleMuseum.name = name;
          break;

        case "get museum name":
          System.out.println(vehicleMuseum.name);
          break;

        case "set entry fee":
          System.out.println("Input new fee");
          double fee = 0;
          try
          {
            fee = in.nextDouble();
          }
          catch (InputMismatchException ex)
          {
            System.out.println("Fee must be a decimal number");
            break;
          }
          vehicleMuseum.entryFee = fee;
          break;

        case "":
          break;

        case "end":
          break;

          case "exit":
            break;

        default:
          System.out.println();
          System.out.println("No command recognised");
          break;
      }
    }
  }
}
