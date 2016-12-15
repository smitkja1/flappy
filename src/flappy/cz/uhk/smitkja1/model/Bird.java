package flappy.cz.uhk.smitkja1.model;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;


public class Bird implements TickAware{
	//fyzika
	static final double koefUp = -5.0;
	static final double koefDown = 2.0;
	static final int ticksFlyingUp = 6;
	//souradnice
	int viewportX;
	double viewportY;
	//rychlost padani/vzletu
	double velocityY = koefDown;
	//kolik ticku zbyva nez ptak zacne padat po nakopnuti
	int ticksToFall = 0;
	
	Image image; //obrazek ptaka
	
	public Bird(int initialX, int initialY, Image image) {
		this.viewportX = initialX;
		this.viewportY = initialY;
		this.image = image;
		
	}
	
	public void kick() {
		velocityY = koefUp;
		ticksToFall = ticksFlyingUp;		
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE/2, null);
		
		//poloha ptaka - pomocna debuggovaci informace fasfa
		//g.setColor(Color.BLACK);
		//g.drawString(viewportX+", "+viewportY, viewportX, (int)viewportY);
	}
	
	public boolean collidesWithRectangle(int x, int y, int w, int h) {
		Ellipse2D.Float birdsFoundary = new Ellipse2D.Float(viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
		return birdsFoundary.intersects(x, y, w, h);	
	}

	@Override
	public void tick(long ticksSinceStart) {
		if (ticksToFall > 0){	
			//ptak jeste leti nahoru
			ticksToFall--;
		}else{
			//ptak pada nebo ma yacit padat
			velocityY = koefDown;
		}
		viewportY = viewportY + velocityY;		
	}

}
