package com.adgarcis.huntthewumpus;

public class Enemy {
	private int posX;
    private int posY;
    private boolean death = false;
    
    public Enemy(int x, int y){
		posX = x;
		posY = y;
	}
    public void setPosition(int x, int y){
        posX = x;
        posY = y;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public String getPos(){
    	return "Enemy x:" + posX + " y: " + posY;
    }
    public boolean isDeath(){
    	return death;
    }
    public void setDeath(){
    	death = true;
    }

}
