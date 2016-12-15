package com.adgarcis.huntthewumpus;

public class Gold {
	private int posX;
	private int posY;
	
	public Gold(int x, int y){
		posX = x;
		posY = y;
	}
	
	public void setPosX(int x){
		posX = x;
	}
	
	public void setPosY(int y){
		posY = y;
	}
	
	public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public String getPos(){
    	return "Gold x:" + posX + " y: " + posY;
    }
}
