package author;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.InputMismatchException;

class Book
{
	private String title;
	private int date;
  private String author;

	Book (String t, int d, String a)
	{
		this.title = t;
		this.date = d;
    this.author = a;
	}

	String getTitle()
	{
		return this.title;
	}


	int getDate()
	{
		return this.date;
	}

  String getAuthor()
  {
    return this.author;
  }
}

class Library
{

    public static void main(String[] args)
    {
		Scanner in = new Scanner(System.in);
		String bookTitle;
		int bookPubDate = 0;
    String bookAuthName;
    Book[] book = new Book[2];

    for (int i = 0; i < book.length; i++)
    {
      System.out.println("Input Book " + i + " information;");

      System.out.print("  Book " + i + " Title: ");
      bookTitle = in.next();

      System.out.print("  Book " + i + " Publish Date: ");

      try
      {
        bookPubDate = in.nextInt();
      }
      catch (InputMismatchException ex)
      {
        System.out.println();
        System.out.println("The entered date for book " + i + " is invalid and must be a number.");
        System.exit(0);
      }

        System.out.print("  Book " + i + " Author Name: ");
        bookAuthName = in.next();

        book[i] = new Book(bookTitle, bookPubDate, bookAuthName);
      }


    //age test
		if (book[0].getDate() > book[1].getDate())
    {
			System.out.println(book[0].getTitle() + " is the most recent book.");
    }
		else if (book[1].getDate() > book[0].getDate())
    {
      System.out.println(book[1].getTitle() + " is the most recent book.");
    }
		else
    {
      System.out.println("Both books are the same age.");
    }


    //author test
    if (book[0].getAuthor().equals(book[1].getAuthor()))
    {
      System.out.println("Both books were written by the same author.");
    }
    else
    {
      System.out.println("Both books were not written by the same author.");
    }
	}
}
