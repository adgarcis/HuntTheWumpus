package com.adgarcis.huntthewumpus;

import java.io.Console;

public class Player {
	private int posX;
    private int posY;
    private boolean death;
    private int arrows;
    private boolean gold;
    public Player() {
        posX = 0;
        posY = 0;
        death = false;
        gold = false;
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
    
    public void setArrows(int nArrows){
    	arrows = nArrows;
    }
    
    public int getArrows(){
    	return arrows;
    }
    
    public boolean isDeath(){
    	return death;
    }
    
    public void setDeath(){
    	death = true;
    }
    
    public boolean isGold(){
    	return gold;
    }
    
    public void setGold(){
    	gold = true;
    }
}
