package Demo_du_jeu;

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
//		panel.addMonstres();
		this.setSize(60 + Labyrinthe.map[0].length*30,60 + Labyrinthe.map.length*30);
		this.add(panel); //add panel to frame
		this.setVisible(true);
		
		KeyListener key=new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				Hero a = new Hero();
				
				if (!hero.intersects(tresor)&&!hero.intersects(monstre)) {

					a.setX(hero.getX());
					a.setY(hero.getY());

					if (KeyEvent.VK_UP == e.getKeyCode()) {
						a.setY(a.getY()-5);
						if (!a.intersects(tresor)&&!a.intersects(monstre)) {
							hero.setY(hero.getY() - 5);
						}
					} else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
						a.setY(a.getY()+5);
						if (!a.intersects(tresor)&&!a.intersects(monstre)) {
							hero.setY(hero.getY() + 5);
						}
					} else if (KeyEvent.VK_LEFT == e.getKeyCode()) {
						a.setX(a.getX()-5);
						if (!a.intersects(tresor)&&!a.intersects(monstre)) {
							hero.setX(hero.getX() - 5);
						}
					} else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
						a.setX(a.getX()+5);
						if (!a.intersects(tresor)&&!a.intersects(monstre)) {
							hero.setX(hero.getX() + 5);
						} 
					} else if (KeyEvent.VK_RIGHT == e.getKeyCode()&&KeyEvent.VK_UP == e.getKeyCode()) {
						a.setX(a.getX()+5);
						a.setY(a.getY()-5);
						if (!a.intersects(tresor)&&!a.intersects(monstre)) {
							hero.setX(hero.getX() + 5);
							hero.setY(hero.getY() - 5);
						}
					} 
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
			
		};
		
		this.addKeyListener(key);
		
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
