package flappy.cz.uhk.smitkja1.model;

import java.awt.Graphics;

/* 
 * reprezentuje objekt umisteni do matice herni plochy
 */

public interface Tile {
	
	static final int SIZE = 20; //sirka a vyska dlazdice v pixelech
	
	
	
	void draw(Graphics g, int x, int y); // vykresli dlazdice na platno g, x/y je x/yova souradnice v pixelech kam se dlazdice kresli 



	
	

}
