package flappy.cz.uhk.smitkja1.model.service;
/*
 * spolecne rozhrani pro tridy umoznujici nacitani levelu
 */

import flappy.cz.uhk.smitkja1.model.GameBoard;

public interface GameBoardLoader {
	/*
	 * nacte level (herni plochu) fsafas
	 */
	GameBoard loadLevel();

}
