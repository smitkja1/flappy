package flappy.cz.uhk.smitkja1.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import flappy.cz.uhk.smitkja1.model.GameBoard;
import flappy.cz.uhk.smitkja1.model.Tile;
import flappy.cz.uhk.smitkja1.model.tiles.WallTile;

public class CSVGameBoardLoader implements GameBoardLoader{

	private InputStream is;
	
	public CSVGameBoardLoader(InputStream is) {
		this.is = is;
	}	
	
	@Override
	public GameBoard loadLevel() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String[] line = br.readLine().split(";");
			int typeFound = Integer.parseInt(line[0]);							
			for (int i = 0; i < typeFound; i++){
				br.readLine(); // posuneme se za data asfasf
			}
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			Tile[][] tiles = new Tile[rows][columns];			
			System.out.println(rows + "," + columns);
			for (int i = 0; i < rows; i++){
				line = br.readLine().split(";");
				for (int j = 0; j < columns; j++){
					String cell;
					if (j < line.length){
						//bunka je v CSV pritomna
						cell = line[j];
					} else {
						//bunka v CSV chybi = povazujeme ji za prazdnou
						cell = "";
					}
					if (!"".equals(cell)){
						tiles[i][j] = new WallTile();
					}
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) { //dva druhy vyjimek - checked - compiler kontroluje ze je zachytavam, runtime vyjimky - nemusi ze zachytavat
			throw new RuntimeException("Chyba pri cteni souboru", e);
		}
	}
	
	
}
