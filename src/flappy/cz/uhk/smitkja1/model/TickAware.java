package flappy.cz.uhk.smitkja1.model;

/*rozhrani pro objekty ktere potrebuji vedet kolik ticku ubehlo od zacatku hry
 * 
 */

public interface TickAware {
	/*
	 * zmeni stav herni entity s ohledem na zmenu herniho casu
	 * ticksSinceStart - kolik ubehlo od zacatku hry 
	 */
	
	public void tick(long ticksSinceStart);
	
	
}
