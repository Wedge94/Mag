package domain;

import java.awt.List;
import java.util.ArrayList;



public class Storage 
{
	static final int x = 6, y = 6, z = 6;
	ArrayList<Package[][]> cubePositionY = new ArrayList<Package[][]>();
	
	private void addElementsToStorage()
	{
		for(int i = 0; i < y / 3; i++)
		{
			for(int j = 0; i < x / 3; i++)
			{
				for(int k = 0; i < z / 3; i++)
				{
					cubePositionY.add(new Package[j][k]);
				}
			}
		}
	}
}
