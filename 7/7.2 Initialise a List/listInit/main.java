package listInit;

class Main
{
  public static void main(String[] names)
  {
    String[] arrOut = new String[names.length];

    for (int i = 0; i < names.length; i++)
    {
      //get the element in the array that are equally as far away from either the ends
      int x = (names.length - 1) - i;

      //swap
      arrOut[i] = names[x];
    }

    for (int i = 0; i < names.length; i++)
    {
      //add the name to the list
      System.out.print(arrOut[i]);

      //if the current index is not last, add a comma to make it an actual list.
      if (i != arrOut.length -1)
      {
        System.out.print(", ");
      }
    }
  }
}
