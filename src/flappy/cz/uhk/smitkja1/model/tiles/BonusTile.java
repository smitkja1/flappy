package flappy.cz.uhk.smitkja1.model.tiles;


import java.awt.Graphics;
import java.awt.Image;

public class BonusTile extends AbstractWallTile {
	
	private boolean active;
	
	public BonusTile(Image image) {
		super(image);
		active = true;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
		
	public void draw(Graphics g, int x, int y) {
		if(active){
			g.drawImage(image, x, y, null);
		}
		
	}	
}
