package flappy.cz.uhk.smitkja1.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import flappy.cz.uhk.smitkja1.model.GameBoard;
//import flappy.cz.uhk.smitkja1.model.service.CSVGameBoardLoader;
//import flappy.cz.uhk.smitkja1.model.service.GameBoardLoader;


@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	BoardPanel pnl = new BoardPanel();
	GameBoard gameboard;
	long x = 0;
	
	class BoardPanel extends JPanel {
		@Override
		public void paint(Graphics g){
			super.paint(g); //vykresli prazdny panel asfgasgf
			gameboard.drawAndTestCollisions(g); // vykresli herni plochu
		}
	}
	
	
	public MainWindow() {
		/*try (InputStream i = new FileInputStream("level3.csv");){
			GameBoardLoader loader = new CSVGameBoardLoader(i);
			gameboard = loader.loadLevel();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		
		}*/ 
		LevelPicker picker = new LevelPicker();
		gameboard = picker.pickAndLoadLevel();
		
		//gameboard = new GameBoard();
		add(pnl,BorderLayout.CENTER);
		pnl.setPreferredSize(new Dimension (200, gameboard.getHeightFix()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				System.out.println("Mys " + e);
				gameboard.kickTheBird();
				}
		});
		
		//timer z jawax.swing - lepe pracuje s oknem
		Timer t = new Timer(20, e -> {
			gameboard.tick(x++);
			pnl.repaint();
		});
		
		t.start();
		
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(() -> {
				MainWindow w = new MainWindow();
				w.setVisible(true);
				});
	}
	

}
