package domain;

import java.util.Scanner;

public class Package 
{
	private TypeOfPackage type;
	private String description;
	private int countOfMoves;
	private String addedDate;
	private int piority;
	private int number;
	
	public void setPriority()
	{
		int i;
		do
		{
			Scanner scan = new Scanner(System.in);
			i = scan.nextInt();
			if(i > 5 || i < 1)
			{
				 System.out.println("Piority must be 1-5!");
			}
			scan.close();
		} while(i < 5 && i > 1);
	}
	Package(TypeOfPackage type, String description, int countOfMoves, String addedDate, int number, int piority)
	{
		this.type = type;
		this.description = description;
		this.countOfMoves = countOfMoves;
		this.addedDate = addedDate;
		this.number = number;
		this.piority = piority;
	}
}
