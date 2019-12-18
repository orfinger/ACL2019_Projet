package Demo_du_jeu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;


public class DemoFrame extends JFrame{
	 
	/**
	 * v0.4
	 */
	private static final long serialVersionUID = 1L;

	public DemoFrame() throws InterruptedException, IOException {
		this.setTitle("Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
		
		Hero hero = new Hero();
		Tresor tresor = new Tresor();
		Monstre monstre = new Monstre();
		
		Labyrinthe panel = new Labyrinthe(hero,tresor,monstre,"Labyrinthe.txt");
		this.setSize(60 + Labyrinthe.map[0].length*30,60 + Labyrinthe.map.length*30);
		this.add(panel); //add panel to frame
		this.setVisible(true);
		
//		 this.addKeyListener(new KeyAdapter() {
//	            @Override
//	            public void keyPressed(KeyEvent e) {
//	                Keys.add(e.getKeyCode());
//	            }
//
//	            @Override
//	            public void keyReleased(KeyEvent e) {
//	                Keys.remove(e.getKeyCode());
//	            }
//	        });
		
		this.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				Hero a = new Hero();
				Keys.add(e.getKeyCode());
				if (!hero.intersects(tresor)&&!hero.intersects(monstre)) {

					a.setX(hero.getX());
					a.setY(hero.getY());

					if (Keys.UP.use()) {
						a.setY(a.getY()-5);
						if (!a.intersects(tresor)&&!a.intersects(monstre)) {
							hero.setY(hero.getY() - 5);
						}
					} else if (Keys.DOWN.use()) {
						a.setY(a.getY()+5);
						if (!a.intersects(tresor)&&!a.intersects(monstre)) {
							hero.setY(hero.getY() + 5);
						}
					} 
					if (Keys.LEFT.use()) {
						a.setX(a.getX()-5);
						if (!a.intersects(tresor)&&!a.intersects(monstre)) {
							hero.setX(hero.getX() - 5);
						}
					} else if (Keys.RIGHT.use()) {
						a.setX(a.getX()+5);
						if (!a.intersects(tresor)&&!a.intersects(monstre)) {
							hero.setX(hero.getX() + 5);
						} 
					} 
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				Keys.remove(e.getKeyCode());
			}

			@Override
			public void keyTyped(KeyEvent e) {}
			
		});
		
		while(true) {
			Thread.sleep(5);
			panel.repaint();
		}
	}
	
	public static void main(String[] args) throws IOException {
		try {
			initilasation niveau= new initilasation(2);
			new DemoFrame();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
