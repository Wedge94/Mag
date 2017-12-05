package com.packages;

import java.util.Stack;

public class FixedSizeStack<E> extends Stack<E> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Nie mam pojecia po co to
	
	private final int MAX_SIZE;
	
	private final int POSITION_X;
	
	private final int POSITION_Y;
	
	public int getMAX_SIZE() {
		return MAX_SIZE;
	}
	
	
	FixedSizeStack(int size, int x, int y) {
		super();
		this.MAX_SIZE=size;
		this.POSITION_X = x;
		this.POSITION_Y = y;
	}
	
	public int getPOSITION_X()
	{
		return this.POSITION_X;
	}
	
	public int getPOSITION_Y()
	{
		return this.POSITION_Y;
	}
	
	public E push(E object) {
		if(this.MAX_SIZE==this.size()) {
			throw new IndexOutOfBoundsException("Stack is full");
		}
		else {
			return super.push(object);
		}
	}

}
