package flappy.cz.uhk.smitkja1.model.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import flappy.cz.uhk.smitkja1.model.tiles.BonusTile;
import flappy.cz.uhk.smitkja1.model.tiles.EmptyTile;
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
			BufferedImage imageOfTheBird = null;
			for (int i = 0; i < typeFound; i++){
				line = br.readLine().split(";");
				String tileType = line[0];
				String tileName = line[1];
				int xPos = Integer.parseInt(line[2]);
				int yPos = Integer.parseInt(line[3]);
				int xSize = Integer.parseInt(line[4]);
				int ySize = Integer.parseInt(line[5]);
				String url = line[6];
				if(tileName.equals("Bird")){
					imageOfTheBird = loadImage(xPos, yPos, xSize, ySize, url);
				}else{
					Tile tile = createTile(tileName, xPos, yPos, xSize, ySize, url);
					tileTypes.put(tileType, tile);
				}
			}
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			Tile[][] tiles = new Tile[rows][columns];			
			System.out.println(rows + "," + columns);
			//prochazi pozici dalzdic
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
			GameBoard gb = new GameBoard(tiles, imageOfTheBird);
			return gb;
		} catch (IOException e) { //dva druhy vyjimek - checked - compiler kontroluje ze je zachytavam, runtime vyjimky - nemusi ze zachytavat
			throw new RuntimeException("Chyba pri cteni souboru", e);
		}
	}

	private Tile createTile(String tileName, int xPos, int yPos, int xSize, int ySize, String url) throws IOException {
			// nacist obrazek z URL
			BufferedImage resizedImage = loadImage(xPos, yPos, xSize, ySize, url);
			// podle typu (clazz) vytvorime instanci patricne tridy
			switch (tileName) {
			case "Wall":
				return new WallTile(resizedImage);
			case "Bonus":
				return new BonusTile(resizedImage);
			case "Empty":
				return new EmptyTile(resizedImage);  
			default:
				throw new RuntimeException("Neznama trida dlazdice " + tileName);
			}
		}

	private BufferedImage loadImage(int xPos, int yPos, int xSize, int ySize, String url)
			throws IOException, MalformedURLException {
		BufferedImage originalImage = ImageIO.read(new URL(url));
		// vyriznout z obrazku sprite podle x,y, a sirka vyska
		BufferedImage croppedImage = originalImage.getSubimage(xPos, yPos, xSize, ySize);
		// zvetsime/zmensime sprite tak, aby pasoval do naseho rozmeru dlazdice
		BufferedImage resizedImage = new BufferedImage(Tile.SIZE, Tile.SIZE, BufferedImage.TYPE_INT_ARGB);
		// TODO nastavit parametry pro scaling
		Graphics2D g = (Graphics2D)resizedImage.getGraphics();
		g.drawImage(croppedImage, 0, 0, Tile.SIZE, Tile.SIZE, null);
		return resizedImage;
	}
	
	
	
}
