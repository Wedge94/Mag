package com.packages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Package {
	
	private int positionX,positionY,positionZ;
	private TypeOfPackage type;
	private String description;
	private int countOfMoves;
	private List<PreviousMove> previous;
	private Date addedDate;
	private static int lastID=0;
	private final int ID;
	private final int PRIORTY;
	
	public Package(TypeOfPackage type, int PRIORTY , String description,  int positionX, int positionY, int positionZ) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.positionZ = positionZ;
		lastNumberIncrement();
		previous = new ArrayList<PreviousMove>();
		this.ID = lastID;
		this.description = description;
		this.type = type;
		this.PRIORTY = PRIORTY;
		this.addedDate = new Date();
		this.addPreviousMove();
	}
	
	private static void lastNumberIncrement() {
		lastID++;
	}

	String getDescription() {
		return description;
	}

	TypeOfPackage getType() {
		return type;
	}

	public int getCountOfMoves() {
		return countOfMoves;
	}

	public Date getDate() {
		return addedDate;
	}
	
	public void ShowDateOfAllMoves() {
		for(int i=0; i<previous.size(); i++) {
			System.out.println(previous.get(i).toString());
		}
	}

	public int getID() {
		return ID;
	}

	public int getPRIORTY() {
		return PRIORTY;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
	
	public int getPositionZ() {
		return positionZ;
	}
	
	void addPreviousMove() {
		this.previous.add(new PreviousMove(positionX,positionY,positionZ,ID));
	}
	public PreviousMove getPreviousMove() {
		return previous.get(previous.size()-1);
	}
	void setPosition(int positionX, int positionY, int positionZ) {
		this.positionY = positionY;
		this.positionX = positionX;
		this.positionZ = positionZ;
		addPreviousMove();
	}
}
