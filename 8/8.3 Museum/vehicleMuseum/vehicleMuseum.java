package vehicleMuseum;

import java.util.Date;

class vehicleMuseum
{
  public static String name;
  static double entryFee;
  static Exhibit[] exhibits = new Exhibit[6];
  static int curExhibits = 0;
  static Staff[] staff = new Staff[60];
  static int curStaff = 0;

  private static void addStaff()
  {
    //add new instance of staff in staff index curStaff, increment curStaff
  }

  private static void removeStaff(int id)
  {
    //set staff at index id to null
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
  int birthday;
  int payDate;
  String currentTask;

  public static void Staff(int id, String name, String job, double salary, int birthday, int payDate)
  {
    //assign variables to the current instance
  }

  void doWork(String task)
  {
    //sets currentTask as task
  }

  void pay()
  {
    //increases the employee's balance by salary if current date is payDate.
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

  void addVehicle()
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
  Integer noDoors;
  Integer engineSize;
  String fuelType;
  int productionDate;

  public static void Vehicle(int id, String licencePlate, String history, String colour, VehicleType type, Integer noDoors, Integer engineSize, String fuelType, int productionDate)
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
  protected String use;

  public static void VehicleType(int id, String name, int noWheels, int noSeats, String use)
  {
    //assigns default variables to vehicleType
  }

  public static void getTypes()
  {
    //returns a list of all types, including IDs.
  }

  protected static void removeType(String name)
  {
    //sets the VehicleType in types with the specified name to null.
  }
}
