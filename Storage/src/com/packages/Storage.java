package com.packages;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Storage {
	private FixedSizeStack<Package>[][] storage;
	private final int SIZE_X, SIZE_Y, SIZE_Z;
	private List<PreviousMove> storagePreviousMoves;
	private HashMap<Integer,Package> hashMapOfPackages;
	private List<Package> outsideStorage;
	private Multimap<TypeOfPackage, Package> hashByType = ArrayListMultimap.create();
	
	private List<FixedSizeStack<Package>> peekList; 
	protected final Logger log = Logger.getLogger(getClass().getName());
	
	
	
	
	@SuppressWarnings("unchecked")
	public Storage(int sizeX,int sizeY,int sizeZ) {
		this.SIZE_X = sizeX;
		this.SIZE_Y = sizeY;
		this.SIZE_Z = sizeZ;
		this.storage = (FixedSizeStack<Package>[][]) new FixedSizeStack[this.SIZE_X][this.SIZE_Y];
		this.peekList = new ArrayList<FixedSizeStack<Package>>();
		for(int i=0; i<SIZE_X; i++)
		{
			for(int j=0; j<SIZE_Y; j++) 
			{
				this.storage[i][j] = new FixedSizeStack<Package>(SIZE_Z, i, j);
				peekList.add(this.storage[i][j]);
			}
		}
		this.hashMapOfPackages = new HashMap<Integer,Package>();
		this.storagePreviousMoves = new ArrayList<PreviousMove>();
		this.outsideStorage = new ArrayList<Package>();
	}
	
	private boolean checkIfCanAddPackage(FixedSizeStack s)
	{
		if(s.size() == s.getMAX_SIZE())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
	public void addPackage(TypeOfPackage type, int priority , String description) throws IllegalArgumentException
	{
		if(priority >= 5 && priority <= 1)
		{
			throw new IllegalArgumentException("Priorty must be a number between 1 to 5");
		}
		if(addToStack(type,priority,description))
		{
			log.log(Level.INFO, "Package was added");
		}
		else
		{
			log.log(Level.INFO, "Package wasn't added, stack is full");
		}
	}
	private Package createPackage(TypeOfPackage type, int priority,String description, int positionX,int positionY,int positionZ) {
		Package tempPackage = new Package(type,priority,description,positionX,positionY,positionZ);
		storagePreviousMoves.add(tempPackage.getPreviousMove());
		addToHashMaps(tempPackage);
		return tempPackage;
	}
	private boolean addToStack(TypeOfPackage type, int priority , String description) 
	{
		for(int indexX=0; indexX<SIZE_X; indexX++)
		{
			for(int indexY=0; indexY<SIZE_Y; indexY++) 
			{
				if(checkIfStackIsNotFull(indexX, indexY))
				{
					if(this.storage[indexX][indexY].size() == 0)
					{
						this.storage[indexX][indexY].push(createPackage(type,priority,description,indexX,indexY,
								(this.storage[indexX][indexY].getMAX_SIZE() - this.storage[indexX][indexY].size())));
						return true;
					}
					else 
					{
						if(this.storage[indexX][indexY].peek().getPRIORTY() <= priority) 
						{
							this.storage[indexX][indexY].push(createPackage(type,priority,description,indexX,indexY,
									(this.storage[indexX][indexY].getMAX_SIZE() - this.storage[indexX][indexY].size())));
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	private boolean movePackageFromPreviousPosition(Package packageToMove, int previousX,int previousY) 
	{
		for(int indexX=0; indexX<SIZE_X; indexX++)
		{
			for(int indexY=0; indexY<SIZE_Y; indexY++) 
			{
				if(indexX==previousX && indexY==previousY) {
					continue;
				}
				if(checkIfStackIsNotFull(indexX, indexY))
				{
					if(checkIfICanPush(packageToMove, indexX, indexY))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean checkIfICanPush(Package packageToMove, int indexX, int indexY)
	{
		if(this.storage[indexX][indexY].size() == 0)
		{
			pushPackage(packageToMove, indexX, indexY);
			return true;
		}
		else 
		{
			if(this.storage[indexX][indexY].peek().getPRIORTY() <= packageToMove.getPRIORTY()) 
			{
				pushPackage(packageToMove, indexX, indexY);
				return true;
			}
		}
		return false;
	}
	
	private void pushPackage(Package packageToMove, int indexX, int indexY)
	{
		this.storage[indexX][indexY].push(packageToMove);
		packageToMove.setPosition(indexX, indexY, 
				this.storage[indexX][indexY].getMAX_SIZE() - this.storage[indexX][indexY].size());
		storagePreviousMoves.add(packageToMove.getPreviousMove());
	}
	
	private boolean checkIfStackIsNotFull(int indexX, int indexY)
	{
		if(this.storage[indexX][indexY].getMAX_SIZE() != this.storage[indexX][indexY].size())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	private void addToHashMaps(Package tempPackage) {
		this.hashByType.put(tempPackage.getType(), tempPackage);
		this.hashMapOfPackages.put(tempPackage.getID(), tempPackage);
	}
	public Package getPackageByNumber(int number) {
		Package packageToGet = this.hashMapOfPackages.get(number);
		if(packageToGet.getPositionZ() == 0) {
			this.storage[packageToGet.getPositionX()][packageToGet.getPositionY()].pop();
			return packageToGet;
		}
		else if(packageToGet.getPositionZ() == -1)
		{
			outsideStorage.remove(outsideStorage.indexOf(packageToGet));
			return packageToGet;
		}
		else
		{
			return PeekFromStack(packageToGet);
		}
	}
	
	private Package PeekFromStack(Package packageToGet)
	{
		try {
			while (packageToGet !=this.storage[packageToGet.getPositionX()][packageToGet.getPositionY()].peek())
			{
				Package tempPackage = this.storage[packageToGet.getPositionX()][packageToGet.getPositionY()].pop();
				if(movePackageFromPreviousPosition(tempPackage,tempPackage.getPositionX(),tempPackage.getPositionY())==false)
				{
					tempPackage.setPosition(-1, -1, -1);
					storagePreviousMoves.add(tempPackage.getPreviousMove());
					outsideStorage.add(tempPackage);
				}
			}
			this.storage[packageToGet.getPositionX()][packageToGet.getPositionY()].pop();
			tryToAddPackageFromOutSideStorage();
			return packageToGet;
		}
		catch(EmptyStackException e)
		{
			log.log(Level.INFO, "Package was taken out");
		}
		return packageToGet;
	}
	
	private void tryToAddPackageFromOutSideStorage() {
		for(int i=0; i<outsideStorage.size(); i++) {
			if(movePackageFromPreviousPosition(outsideStorage.get(i),-1,-1)) {
				outsideStorage.remove(i);
			}
		}
	}
	public List<Package> getAllPackageByType(TypeOfPackage type) {
		List<Package> hashList = (List<Package>) this.hashByType.get(type);
		List<Package> listOfPackage = new ArrayList<Package>();
		for(int i = 0; i < hashList.size(); i++)
		{
			listOfPackage.add(getPackageByNumber(hashList.get(i).getID()));
		}
		return listOfPackage;
	}
	public void getHistoryOfPreviousMoves() {
		for(int i=0; i<this.storagePreviousMoves.size(); i++) {
			log.log(Level.INFO, "Move number: "+(i+1));
			log.log(Level.INFO, this.storagePreviousMoves.get(i).toString());
		}
	}
}

