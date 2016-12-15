package com.adgarcis.huntthewumpus;

public class Board {
	private int sizeX;
	private int sizeY;
	
	public Board(){
		
	}
	
	public void setSize(int x, int y){
		sizeX = x;
		sizeY = y;
	}
	
	public void setSizeX(int x){
		sizeX = x;
	}
	
	public void setSizeY(int y){
		sizeY = y;
	}
	
	public int getSizeX(){
        return sizeX;
    }
    
    public int getSizeY(){
        return sizeY;
    }
}
