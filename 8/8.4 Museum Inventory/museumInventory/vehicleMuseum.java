package museumInventory;

import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;

class vehicleMuseum
{
  public static String name = "My Museum";
  static double entryFee = 12.98;
  static Exhibit[] exhibits = new Exhibit[6];
  static int curExhibits = 0;
  static Staff[] staff = new Staff[60];
  static int curStaff = 0;

  private static void addStaff()
  {
    Scanner in = new Scanner(System.in);

    System.out.println("Input name");
    String name = in.next();

    System.out.println("Input Job Title");
    String job = in.next();

    System.out.println("Input salary");
    try
    {
      double salary = in.nextDouble();
    }
    catch (InputMismatchException ex)
    {
      System.out.println("Salary invalid, staff was not added.");
      return;
    }

    staff[curStaff] = new Staff(curStaff, name, job, salary);
    curStaff++;
  }

  private static void removeStaff(int id)
  {
    if (staff[id] != null)
    {
        if (id == 0 && noSavers == 1)
        {
            staff[0] = null;
            noSavers = 0;
            return;
        }
        else if (id > -1 && id < staff.length)
            staff[id] = null;

            for (int i = id+1; i < staff.length; i++)
            {
                if (staff[i] != null)
                {
                    staff[i-1] = staff[i];
                    staff[i-1].id--;
                    staff[i-1].UpdateName(null);

                    staff[i] = null;
                }
                else
                {

                    lblDispNoRoom.setVisible(false);
                    btnAddSaver.setDisable(false);

                    noSavers--;
                    return;
                }
            }

            curStaff++;
        }
        else
        {
            throw new IllegalArgumentException("Value of 'ID' was invalid. Value was " + id + ", it should have been between 0 and " + (staff.length-1) + ".");
        }
    }
    else
    {
        throw new IllegalArgumentException("Value of 'ID' was invalid. Element at position " + id + " in staff did not exist (was null), so cannot be deleted.");
    }
  }

  private static void addExhibit()
  {
    //add new instance of exhibit in exhibits index curExhibits, increment curExhibits
  }

  private static void removeExhibit(int id)
  {
    //call remove for exhibit at index id, then set that element to null.
  }
}

class Staff
{
  int id;
  String name;
  String job;
  double salary;
  Date birthday;
  Date payDate;
  String currentTask;

  public Staff(int id, String name, String job, double salary, Date birthday, Date payDate)
  {
    this.id = id;
    this.name = name;
    this.job = job;
    this.salary = salary;
    this.birthday = birthday;
    this.payDate = payDate;
  }

  void doWork(String task)
  {
    this.currentTask = task;
  }

  void pay()
  {
    if (/*curDate*/ == payDate)
    {
      //increases the employee's balance by salary
      //I am not simulating this far in this code. For this I would need a class for people with a balance.
    }
  }
}

class Exhibit
{
  int id;
  String title;
  String styles;
  Vehicle[] vehicles = new Vehicle[10]
  int noVehicle = 0;

  public static void Exhibit(int id, String title, String styles)
  {
    //assign variables to the current instance.
  }

  void addVehicle(String licencePlate, String History, VehicleType vehicleType)
  {
    //creates a new instance of Vehicle in the next free index.
  }

  void removeVehicle(String licencePlate)
  {
    //Sets the element in vehicles with the licencePlate specified to null.
  }

  void remove()
  {
    //sets all vehicles to null (for each in vehicles, call removeVehicle)
  }

  void clean()
  {
    //Employee cleans an exhibit.
  }
}

class Vehicle
{
  int id;
  String licencePlate;
  String history;
  String colour;
  VehicleType type;
  Date productionDate;

  public static void Vehicle(int id, String licencePlate, String history, VehicleType type)
  {
    //assigns default variables to the current instance of Vehicle.
  }

  public void lookAt()
  {
    //Someone looks at the exhibit, e.g. a visitor.
  }

  void polish()
  {
    //Employee polishes a car.
  }
}

class VehicleType
{
  static VehicleType[] types = new VehicleType[5];
  protected int id;
  protected String name;
  protected int noWheels;
  protected int noSeats;
  protected  String use;

  public static void VehicleType(int id, String name, int noWheels, int noSeats, String use)
  {
    //assigns default variables to vehicleType
  }

  protected static void removeType(String name)
  {
    //sets the VehicleType in types with the specified name to null.
  }
}
