package flappy.cz.uhk.smitkja1.model.tiles;

import java.awt.Graphics;
import java.awt.Image;

import flappy.cz.uhk.smitkja1.model.Tile;

public abstract class AbstractWallTile implements Tile {
	Image image;

	public AbstractWallTile(Image image) {
		this.image = image;
	}
	
	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}	
	
	
	
}