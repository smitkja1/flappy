package flappy.cz.uhk.smitkja1.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
			//nacte informace o bunkach
			String[] line = br.readLine().split(";");
			int typeFound = Integer.parseInt(line[0]);	
			//prochazi typy dlazdic
			Map<String, Tile> tileTypes = new HashMap<>();
			for (int i = 0; i < typeFound; i++){
				line = br.readLine().split(";");
				String tileType = line[0];
				String tileName = line[1];
				int xPos = Integer.parseInt(line[2]);
				int yPos = Integer.parseInt(line[3]);
				int xSize = Integer.parseInt(line[4]);
				int ySize = Integer.parseInt(line[5]);
				String url = line[6];
				Tile tile = createTile(tileName, xPos, yPos, xSize, ySize, url);
				tileTypes.put(tileType, tile);
				
			}
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			Tile[][] tiles = new Tile[rows][columns];			
			System.out.println(rows + "," + columns);
			//prochazi pozici dalzdic
			Tile tile = new WallTile();
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
					//ziskame odpovidajici dlazdice z hashmapy
					tiles[i][j] = tileTypes.get(cell);
					
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) { //dva druhy vyjimek - checked - compiler kontroluje ze je zachytavam, runtime vyjimky - nemusi ze zachytavat
			throw new RuntimeException("Chyba pri cteni souboru", e);
		}
	}

	private Tile createTile(String tileName, int xPos, int yPos, int xSize, int ySize, String url) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
