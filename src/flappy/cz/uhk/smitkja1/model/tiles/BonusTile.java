package flappy.cz.uhk.smitkja1.model.tiles;


import java.awt.Graphics;
import java.awt.Image;
import flappy.cz.uhk.smitkja1.model.Tile;

public class BonusTile extends AbstractWallTile {
	
	Tile emptyTile;
	private boolean active;
	//bonusova dlazdice
	public BonusTile(Image image, Tile emptyTile) {
		super(image);
		active = true;
		this.emptyTile = emptyTile;
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
		}else{
			emptyTile.draw(g, x, y);
		}		
	}	
	
	public BonusTile clone(){
		//kolonování bonustile
		return new BonusTile(image, emptyTile);
	}
}
