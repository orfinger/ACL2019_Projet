package Demo_du_jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;


public class DemoFrame extends JFrame{

	/**
	 * v0.4
	 */
	private static final long serialVersionUID = 1L;
	protected String path;

	public DemoFrame() throws InterruptedException, IOException {
		this.setTitle("Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		JPanel choisirNiveau= new JPanel(new GridLayout(1, 1));
        JButton btn1 = new JButton("Niveau 1");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path="Labyrinthe.txt";
            }
        });
        choisirNiveau.add(btn1);
        
        JButton btn2 = new JButton("Niveau 2");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path="Lab2.txt";
            }
        });
        choisirNiveau.add(btn2);
        
        JButton btn3 = new JButton("Niveau 3");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path="Lab3.txt";
            }
        });
        choisirNiveau.add(btn3);
        this.setContentPane(choisirNiveau);
        this.setSize(600,600);
        while(path==null) {
			Thread.sleep(5);
        }
        
        Hero hero = new Hero();
		Tresor tresor = new Tresor();
		Monstre monstre = new Monstre();
		
		Labyrinthe panel = new Labyrinthe(hero,tresor,monstre,path);
		this.setSize(60 + Labyrinthe.lenth*30,60 + Labyrinthe.map.length*30);
		this.setContentPane(panel);
		//this.add(panel); //add panel to frame
		this.requestFocus();
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

				if (Keys.ATTACK.use()) {
					hero.attack = true;
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
		int count1 = 0;
		double speed = 0.1;


		while(true) {
			Thread.sleep(5);
			count+=1; 
			panel.repaint();
			if (count>( (int)1/speed)&&!panel.intersectsMur(monstre)) { //Speed vitesse de deplacement du monstre
				monstre.suivreHero(hero);
				count = 0;
			}

			if (hero.attack) {
				hero.path = "src/resource/Character_AttackRight.png";
				count1++;
				if (hero.aCote(monstre))
					monstre.path = "src/resource/wall.jpg ";
				if (count1==40) {
					hero.path = "src/resource/Character_Right.png";
					hero.attack = false;
					count1=0;
				}
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
