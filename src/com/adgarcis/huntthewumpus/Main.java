package com.adgarcis.huntthewumpus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner; 

public class Main {

	private static Board board;
	private static Player player;
	private static List<WaterWell> waterWellList;
	private static Gold gold;
	private static Enemy enemy;
	private static boolean win = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println ("Empezamos el programa");
		init();
		detect();
		while(!player.isDeath() && !win){
			menu();
			detect();
		}
	}

	public static void console(String texto){
	    System.out.println (texto);
	}
	
	public static void init(){
		board = new Board();
		Scanner sc = new Scanner (System.in); 
		//Creación del tablero parametrizable
		board.setSizeX(capturaOpcion(sc,"Introduce tamaño del eje X del tablero"));
		board.setSizeY(capturaOpcion(sc,"Introduce tamaño del eje Y del tablero"));
		//Creación del objeto Player
		player = new Player();
		
		Random ran = new Random();
		
		//Creamos el lingote de oro
		gold = new Gold(ran.nextInt(board.getSizeX()), ran.nextInt(board.getSizeX()));
		//Creamos el Enemy
		
		int enemyX = ran.nextInt(board.getSizeX());
		int enemyY = ran.nextInt(board.getSizeY());
		
		//comprobamos que no cae en el mismo sitio que el oro (Aunque no se pide)
	    while (enemyX == gold.getPosX() && enemyY == gold.getPosY()) {
	    	console("Cae en la misma posicion que el oro!");
	    	enemyX = ran.nextInt(board.getSizeX());
			enemyY = ran.nextInt(board.getSizeY());
	    }
	    
		enemy = new Enemy(enemyX, enemyY);
		
		//Pedimos cuantos pozos se quieren y se generar aleatoriamente en el tablero
		int nWaterWell = capturaOpcion(sc,"Número de pozos: ");
		//TODO: controlar que el número de pozos no sea mayor que el numero de celdas determinadas ya que el juego podría ser imposible de jugar PD: No se especifica
		waterWellList = new ArrayList<WaterWell>();
		for (int i=0; i< nWaterWell; i++){
			int waterX = ran.nextInt(board.getSizeX());
			int waterY = ran.nextInt(board.getSizeY());
			//comprobamos que no cae en el mismo sitio que el oro ni el enemy (Aunque no se pide)
		    while ((waterX == gold.getPosX() && waterY == gold.getPosY()) || (waterX == enemy.getPosX() && waterY == enemy.getPosY()) || (waterX == player.getPosX() && waterY == player.getPosY())) {
		    	console("Cae en la misma posicion que el oro o el enemy!");
		    	waterX = ran.nextInt(board.getSizeX());
		    	waterX = ran.nextInt(board.getSizeY());
		    }
			WaterWell w = new WaterWell(waterX, waterY); //Le sumo uno para que no caiga en la posicion 0,0 nunca
			waterWellList.add(w);
		}
		
		console(enemy.getPos());
		console(gold.getPos());
		for(int w = 0; w<waterWellList.size(); w++){
			console(waterWellList.get(w).getPos());
		}
		
		player.setArrows(capturaOpcion(sc,"Flechas para el Player:"));
		
	}
	
	public static void menu(){
		console("¿Qué deseas hacer?");
		console("1 moverte hacia arriba");
		console("2 moverte hacia abajo");
		console("3 moverte hacia la derecha");
		console("4 moverte hacia la izquierda");
		console("5 dispara flecha hacia arriba (Te quedan "+player.getArrows()+")");
		console("6 dispara flecha hacia abajo (Te quedan "+player.getArrows()+")");
		console("7 dispara flecha hacia derecha (Te quedan "+player.getArrows()+")");
		console("8 dispara flecha hacia izquierda (Te quedan "+player.getArrows()+")");
		console("9 Salir");
		console("-------------------");
		for(int i = 0; i < board.getSizeX(); i++){
			String fila = "";
			for(int j = 0; j < board.getSizeY(); j++){
				if(i == enemy.getPosX() && j == enemy.getPosY()){
					fila = fila + " E ";
				}else if(i == gold.getPosX() && j == gold.getPosY()){
					fila = fila + " G ";
				}else if(i == player.getPosX() && j == player.getPosY()){
					fila = fila + " P ";
				}else{
					fila = fila + " x ";
				}
				
			}
			console(fila);
		}
		console("-------------------");
		Scanner sc = new Scanner (System.in); 
		int opc = capturaOpcion(sc, "Elige una opción: ");
		switch (opc) {
			case 1: 
				player.setPosition(player.getPosX(), player.getPosY() + 1);
				break;
			case 2: 
				player.setPosition(player.getPosX(), player.getPosY() - 1);
				break;
			case 3: 
				player.setPosition(player.getPosX() + 1, player.getPosY());
				break;
			case 4: 
				player.setPosition(player.getPosX() - 1, player.getPosY());
				break;
			case 5: 
				player.setArrows(player.getArrows()-1);
				if(!enemy.isDeath()){
					for(int i=player.getPosY(); i<board.getSizeY(); i++){
						if(enemy.getPosX() == player.getPosX() && enemy.getPosY() == i){
							console("---Se escucha un grito---");
							enemy.setDeath();
						}
					}
					if(!enemy.isDeath()){
						console("La flecha choca contra la pared");
					}
				}else{
					console("La flecha choca contra la pared");
				}
				
				break;
			case 6: 
				player.setArrows(player.getArrows()-1);
				if(!enemy.isDeath()){
					for(int i=0; i<player.getPosY(); i++){
						if(enemy.getPosX() == player.getPosX() && enemy.getPosY() == i){
							console("---Se escucha un grito---");
							enemy.setDeath();
						}
					}
					if(!enemy.isDeath()){
						console("La flecha choca contra la pared");
					}
				}else{
					console("La flecha choca contra la pared");
				}
				
				break;
			case 7: 
				player.setArrows(player.getArrows()-1);
				if(!enemy.isDeath()){
					for(int i=player.getPosX(); i<board.getSizeX(); i++){
						if(enemy.getPosY() == player.getPosY() && enemy.getPosY() == i){
							console("---Se escucha un grito---");
							enemy.setDeath();
						}
					}
					if(!enemy.isDeath()){
						console("La flecha choca contra la pared");
					}
				}else{
					console("La flecha choca contra la pared");
				}
				
				break;
			case 8: 
				player.setArrows(player.getArrows()-1);
				if(!enemy.isDeath()){
					for(int i=0; i<player.getPosX(); i++){
						if(enemy.getPosY() == player.getPosY() && enemy.getPosX() == i){
							console("---Se escucha un grito---");
							enemy.setDeath();
						}
					}
					if(!enemy.isDeath()){
						console("La flecha choca contra la pared");
					}
				}else{
					console("La flecha choca contra la pared");
				}
				
				break;
			default:
				player.setDeath();
				break;
		}
	}
	
	public static void detect(){
		
		console("Estas en la posición: ");
		console("X: "+player.getPosX());
		console("Y: "+player.getPosY());
		for(int w = 0; w<waterWellList.size(); w++){
			if(waterWellList.get(w).getPosX() == player.getPosX() && waterWellList.get(w).getPosY() == player.getPosY()){
				player.setDeath();
				console("HAS MUERTO");
			}
		}
		
		if(player.getPosX() == enemy.getPosX() && player.getPosY() == enemy.getPosY()){
			player.setDeath();
			console("HAS MUERTO");
		}else if(player.isGold() && player.getPosX() == 0 && player.getPosY() == 0){
			console("HAS GANADO");
			win = true;
		}
		else if(!player.isDeath()){
			if(player.getPosX() == gold.getPosX() && player.getPosY() == gold.getPosY()){
				player.setGold();
				console("TIENES EL ORO!!!!");
				//TODO: eleminar objeto Gold ahora de manera chavacana
				gold.setPosX(-10);
				gold.setPosY(-10);
			}
			for(int i = player.getPosX()-1; i<= player.getPosX()+1; i++){
				for(int j = player.getPosY()-1; j<= player.getPosY()+1; j++){
					if(enemy.getPosX() == i && enemy.getPosY() == j){
						console("Se percibe un hedor");
					}
					if(gold.getPosX() == i && gold.getPosY() == j){
						console("Se percibe un brillo");
					}
					for(int w = 0; w<waterWellList.size(); w++){
						if(waterWellList.get(w).getPosX() == i && waterWellList.get(w).getPosY() == j){
							console("Se percibe una brisa");
						}
					}
				}
			}
		}
	}
	
	public static int capturaOpcion(Scanner sc, String msg){
		int opc;
		do {
		    console(msg);
		    while (!sc.hasNextInt()) {
		    	console("No es un número!");
		        sc.next();
		    }
		    opc = sc.nextInt();
		} while (opc <= 0);
		return opc;
	}
}
