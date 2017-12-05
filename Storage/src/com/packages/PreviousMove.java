package com.packages;

import java.util.Date;

public final class PreviousMove {
	private int positionX, positionY, positionZ;
	private Date date;
	private final int id;
	
	PreviousMove(int positionX,int positionY,int positionZ, int id) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.positionZ = positionZ;
		this.id = id;
		date = new Date();
	}


	public String toString() {
		String tempString = new String("ID:"+ id +"\n"+"Date: " + date + "\n" +"Position: "+positionX+" "+positionY+" "
	+positionZ + "\n");
		return tempString;
	}
	
}
