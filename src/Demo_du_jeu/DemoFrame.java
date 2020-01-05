package Demo_du_jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;


public class DemoFrame extends JFrame{

	/**
	 * v0.4
	 */
	private static final long serialVersionUID = 1L;
	protected String path;

	private Thread threadHero;
	private Thread SuivreHero;
	private ArrayList<Monstre> monstrelist = new ArrayList<>();
	
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
        
        
        threadHero = new Thread(hero);
        
		Labyrinthe panel = new Labyrinthe(hero,tresor,path);
		panel.setMonstreList(monstrelist);
		panel.addMonstre();
		monstrelist = panel.getMonstreList();
		
		for (Monstre j : monstrelist) {
			new Thread(j).start();
		}
		
		SuivreHero = new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						Thread.sleep((int)1000/Constante.MONSTRE_SPEED);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (Monstre j : monstrelist) {
						int dx = hero.getX() - j.x;
						int dy = hero.getY() - j.y;
						int radial_distance = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
						if (radial_distance < Constante.MONSTRE_DETECTION_RANGE) {
							Monstre a = new Monstre();
							a.setX(j.getX());
							a.setY(j.getY());
							if (dx > 0) {
								a.setX(a.getX()+1);
							}else if (dx < 0){
								a.setX(a.getX()-1);
							}
							if (dy > 0) {
								a.setY(a.getY()+1);
							}else if (dy < 0){
								a.setY(a.getY()-1);
							}
							if (!a.intersects(hero)&&!a.intersects(tresor)&&!panel.intersectsMur(a)) {	
								j.setX(a.getX());
								j.setY(a.getY());	
							}
//							else {
//								a.setX(j.getX()+a.getY()-j.getY()); //tourne a droite
//								a.setY(j.getY()-a.getX()+j.getX());
//								System.out.println("ÓÒ"+(a.getX()-j.getX())+"--"+ (a.getY()-j.getY()));
//								if (!a.intersects(tresor)&&!panel.intersectsMur(a)) {
//									j.setX(a.getX());
//									j.setY(a.getY());	
//								}else {
//									a.setX(j.getX()-a.getY()+j.getY()); //tourne a gauche
//									a.setY(j.getY()+a.getX()-j.getX());
//									if (!a.intersects(hero)&&!a.intersects(tresor)&&!panel.intersectsMur(a)) {
//										System.out.println("×ó"+(a.getX()-j.getX())+"--"+ (a.getY()-j.getY()));
//										j.setX(a.getX());
//										j.setY(a.getY());	
//									}
//								}
//							}
							
//							else {
//								a.setX(j.getX()-a.getY()+j.getY()); //tourne a gauche
//								a.setY(j.getX()+a.getX()-j.getX());
//								if (!a.intersects(hero)&&!a.intersects(tresor)&&!panel.intersectsMur(a)) {
//									j.setX(a.getX());
//									j.setY(a.getY());	
//								}else {
//									a.setX(j.getX()+a.getY()-j.getY()); //tourne a droite
//									a.setY(j.getX()-a.getX()+j.getX());
//									if (!a.intersects(hero)&&!a.intersects(tresor)&&!panel.intersectsMur(a)) {
//										j.setX(a.getX());
//										j.setY(a.getY());	
//									}
//								}
//							}
						}		
					}
				}
			}
		});
		SuivreHero.start();
		
		this.setSize(60 + Labyrinthe.length*30,60 + Labyrinthe.map.length*30);
		this.setContentPane(panel);
		this.requestFocus();
		
		this.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				Hero a = new Hero();
				Keys.add(e.getKeyCode());

				a.setX(hero.getX());
				a.setY(hero.getY());
				
				if (Keys.UP.use()) {
					hero.move = true;
					hero.direction = Direction.UP;
					a.setY(a.getY()-5);
					if (!a.intersects(tresor)&&!panel.intersectsMonstre(a)&&!panel.intersectsMur(a)) {
						hero.setY(hero.getY() - 5);	
						hero.setPaintY(hero.getPaintY() - 5);
					}
				} else if (Keys.DOWN.use()) {
					hero.move = true;
					hero.direction = Direction.DOWN;
					a.setY(a.getY()+5);
					if (!a.intersects(tresor)&&!panel.intersectsMonstre(a)&&!panel.intersectsMur(a)) {
						hero.setY(hero.getY() + 5);	
						hero.setPaintY(hero.getPaintY() + 5);
					}
				} 
				if (Keys.LEFT.use()) {
					hero.move = true;
					hero.direction = Direction.LEFT;
					a.setX(a.getX()-5);
					if (!a.intersects(tresor)&&!panel.intersectsMonstre(a)&&!panel.intersectsMur(a)) {
						hero.setX(hero.getX() - 5);	
						hero.setPaintX(hero.getPaintX() - 5);
					}
				} else if (Keys.RIGHT.use()) {
					hero.move = true;
					hero.direction = Direction.RIGHT;
					a.setX(a.getX()+5);
					if (!a.intersects(tresor)&&!panel.intersectsMonstre(a)&&!panel.intersectsMur(a)) {
						hero.setX(hero.getX() + 5);	
						hero.setPaintX(hero.getPaintX() + 5);
					}
				} 

				if (Keys.ATTACK.use()) {
					hero.attack = true;				
				}	
			}

			@Override
			public void keyReleased(KeyEvent e) {
				Keys.remove(e.getKeyCode());
				if (Keys.noMove()) {
					hero.move = false;		
				}	
			}

			@Override
			public void keyTyped(KeyEvent e) {}
		});
		

		
		threadHero.start();
	
		while(true) {
			panel.repaint();
//			System.out.println(monstrelist.size());
//			System.out.println(Thread.activeCount());
//			if (hero.attack) {			
//				if (hero.aCote(monstre))
//					monstre.path = "src/resource/wall.jpg ";
//
//			}
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
