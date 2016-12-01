package flappy.cz.uhk.smitkja1.model.tiles;

import java.awt.Graphics;

import flappy.cz.uhk.smitkja1.model.Tile;

public class WallTile implements Tile{

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
		}
		
	

	


}
