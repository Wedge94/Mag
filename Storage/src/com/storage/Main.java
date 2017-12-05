package com.storage;

import java.util.List;
import java.util.Random;

import com.packages.*;
import com.packages.Package;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Storage cube = new Storage(10,10,10);
		long startTime = System.currentTimeMillis();
		int random1 = 0;
		for(int i=0; i<1000; i++) {
			random1++;
			if(random1 == 1)
			{
				cube.addPackage(TypeOfPackage.toys , 1, "To sa zabawki");
				//cube.addPackageNew(TypeOfPackage.toys , 1, "To sa zabawki");
			}
			else if(random1 == 2)
			{
				
				cube.addPackage(TypeOfPackage.furnitures , 2, "To sa meble");
				//cube.addPackageNew(TypeOfPackage.furnitures , 2, "To sa meble");
			}
			else if(random1 == 3)
			{
				cube.addPackage(TypeOfPackage.jewellery , 3, "To jest bizuteria");
				//cube.addPackageNew(TypeOfPackage.jewellery , 3, "To jest bizuteria");
			}
			else if(random1 == 4)
			{
				cube.addPackage(TypeOfPackage.electronics , 4, "To jest elektronika");
				//cube.addPackageNew(TypeOfPackage.electronics , 4, "To jest elektronika");
			}
			if(random1 == 4)
			{
				random1 =0;
			}
		}
		long finishTime = System.currentTimeMillis();
		//int x = cube.getTemp();
		/*Package tempPackage = cube.getPackageByNumber(378);
		//System.out.println(tempPackage.getID());
		//System.out.println(tempPackage.getDate());
		tempPackage.ShowDateOfAllMoves();
		for(int i=0; i<cube.outsideStorage.size(); i++) {
			System.out.println(cube.outsideStorage.get(i).getID());
		}*/
		System.out.println("That took: " + (finishTime - startTime) + " ms");
		/*for(int i=1; i<=x; i ++) {
			System.out.println("<-------------------------------------->"); 
			cube.getPackageByNumber(i).ShowDateOfAllMoves();
			System.out.println("<-------------------------------------->"); 
		}
		//cube.getAllPackageByType(TypeOfPackage.toys);
		//cube.getHistoryOfPreviousMoves();
		/*List<Package> list = cube.getAllPackageByType(TypeOfPackage.toys);
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println("<-------------------------------------->");  
			System.out.println(list.get(i).getID()); 
			System.out.println(list.get(i).getCountOfMoves());
			System.out.println(list.get(i).getPreviousMove());
			list.get(i).ShowDateOfAllMoves(); 
			System.out.println("<-------------------------------------->"); 
		}*/
	}

}
