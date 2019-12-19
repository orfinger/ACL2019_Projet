package Demo_du_jeu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
		Monstre monstre = new Monstre(hero);

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

				a.setX(hero.getX());
				a.setY(hero.getY());


				if (Keys.UP.use()) {
					hero.direction = Direction.UP;
					a.setY(a.getY()-5);
					if (!a.intersects(tresor)&&!a.intersects(monstre)&&!panel.intersectsMur(a)) {
						hero.setY(hero.getY() - 5);		
					}
				} else if (Keys.DOWN.use()) {
					hero.direction = Direction.DOWN;
					a.setY(a.getY()+5);
					if (!a.intersects(tresor)&&!a.intersects(monstre)&&!panel.intersectsMur(a)) {
						hero.setY(hero.getY() + 5);		
					}
				} 
				if (Keys.LEFT.use()) {
					hero.direction = Direction.LEFT;
					a.setX(a.getX()-5);
					if (!a.intersects(tresor)&&!a.intersects(monstre)&&!panel.intersectsMur(a)) {
						hero.setX(hero.getX() - 5);	
					}
				} else if (Keys.RIGHT.use()) {
					hero.direction = Direction.RIGHT;
					a.setX(a.getX()+5);
					if (!a.intersects(tresor)&&!a.intersects(monstre)&&!panel.intersectsMur(a)) {
						hero.setX(hero.getX() + 5);		
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


		int count = 0;
		double speed = 0.1;


		while(true) {
			Thread.sleep(5);
			count+=1; 
			panel.repaint();
			if (count>( (int)1/speed)&&!panel.intersectsMur(monstre)) { //Speed vitesse de deplacement du monstre
				monstre.suivreHero(hero);
				count = 0;
			}

		}
}

public static void main(String[] args) throws IOException {
	try {
		new DemoFrame();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
