package flappy.cz.uhk.smitkja1.model.tiles;

import java.awt.Graphics;
import java.awt.Image;

import flappy.cz.uhk.smitkja1.model.Tile;

public class WallTile implements Tile{
	Image image;

	
	public WallTile(Image image) {
		this.image = image;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);		
		// g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
		}
		
	

	


}
