package flappy.cz.uhk.smitkja1.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import flappy.cz.uhk.smitkja1.model.tiles.BonusTile;
import flappy.cz.uhk.smitkja1.model.tiles.WallTile;

public class GameBoard implements TickAware{
	Tile[][] tiles; 
	int shiftX = 30;
	int viewportWidth = 200;
	Bird bird;
	boolean gameOver = false;
	
	public GameBoard(Tile[][] tiles, BufferedImage imageOfTheBird) {
		this.tiles = tiles;
		bird = new Bird(viewportWidth/2, tiles.length*Tile.SIZE/2, imageOfTheBird);
	}
	
	public void drawAndTestCollisions(Graphics g){
		//vykresli gameboard, ptaka a checkuje kolize
		int minJ = shiftX/Tile.SIZE;
		//2 pro zaokrouhleni nahoru po deleni
		int maxJ = minJ + viewportWidth/Tile.SIZE + 2; 
		for (int i = 0; i < tiles.length; i++){
			for (int j = minJ; j < maxJ; j++){
				//datovy typ mohou pretekat dokola, chceme aby se svet opakoval
				//j2 se pohybuje od nula do pocet sloupcu - 1
				int j2 = j % tiles[0].length;
				Tile t = tiles[i][j2];
				if(t != null){				
					int screenX = j*Tile.SIZE - shiftX;
					int screenY = i*Tile.SIZE;
					t.draw(g, screenX, screenY);
					//nakreslime dlazdici a otestujeme moznou kolizi s ptakem
					if (t instanceof WallTile){
						if(bird.collidesWithRectangle(screenX, screenY, Tile.SIZE, Tile.SIZE)){
						System.out.println("kolize");
						gameOver = true;
					}}
					if (t instanceof BonusTile){
						if(bird.collidesWithRectangle(screenX, screenY, Tile.SIZE, Tile.SIZE)){							
						System.out.println("bonus");
						((BonusTile) t).setActive(false);											
					}}
					//TODO pridat bonusy - pres kolizi, jakmile ho ptak sezere tak zmizi	
					}						
				}
			}
		bird.draw(g);
		}


	@Override
	public void tick(long ticksSinceStart) {
		if(!gameOver){
		/*s kazdym tickem ve hre posuneme hru o jeden pixel
		 * pocet vlastnich ticku a pixelu posunu se rovanji
		 */
		shiftX = (int)ticksSinceStart;
		
		//dame vedet ptakovi ze hodiny tickkli
		bird.tick(ticksSinceStart);
		}
	}
	
	public int getHeightFix(){
		return tiles.length*Tile.SIZE;
	}
	
	public void kickTheBird(){
		bird.kick();
	}

}
