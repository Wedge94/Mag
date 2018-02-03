package com.storage;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.packages.*;
import com.packages.Package;



public class Main {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Storage cube = new Storage(10,10,10);
		int random1 = 0;
		final Logger log = Logger.getAnonymousLogger();
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

		List<Package> listOfToys = cube.getAllPackageByType(TypeOfPackage.toys);
		for(int i = 0; i < listOfToys.size(); i++)
		{
			log.log(Level.INFO, "---");
			log.log(Level.INFO, Integer.toString(listOfToys.get(i).getID()));
			log.log(Level.INFO, listOfToys.get(i).getDate().toString());
			log.log(Level.INFO, "---");
		}
		cube.getPackageByNumber(90).showDateOfAllMoves();
		cube.getPackageByNumber(89).showDateOfAllMoves();
	}

}
