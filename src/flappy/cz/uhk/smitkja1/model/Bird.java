package flappy.cz.uhk.smitkja1.model;

import java.awt.Color;
import java.awt.Graphics;


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
	
	public Bird(int initialX, int initialY) {
		this.viewportX = initialX;
		this.viewportY = initialY;
	}
	
	public void kick() {
		velocityY = koefUp;
		ticksToFall = ticksFlyingUp;		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
		
		//poloha ptaka - pomocna debuggovaci informace fasfa
		g.setColor(Color.BLACK);
		g.drawString(viewportX+", "+viewportY, viewportX, (int)viewportY);
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
