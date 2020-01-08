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
	private Hero hero;
	private Tresor tresor;  
	private Labyrinthe panel;

	private Thread threadHero;
	private Thread SuivreHero;
	private ArrayList<Monstre> monstrelist; 

	private void initialisation() {
		hero = new Hero();
		tresor = new Tresor();
		monstrelist = new ArrayList<>();
		threadHero = new Thread(hero);
		threadHero.start();

		try {
			panel = new Labyrinthe(hero,tresor,path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
								j.setDirestion(Direction.RIGHT);
								a.setX(a.getX()+1);
							}else if (dx < 0){
								j.setDirestion(Direction.LEFT);
								a.setX(a.getX()-1);
							}
							if (dy > 0) {
								a.setY(a.getY()+1);
							}else if (dy < 0){
								a.setY(a.getY()-1);
							}
							try {
								if (j.getClass()==Class.forName("Demo_du_jeu.Monstre")) {
									if(a.intersects(hero)) {
										hero.meurt();
									}

									if (!a.intersects(hero) && !a.intersects(tresor) && !panel.intersectsMur(a)) {
										j.setX(a.getX());
										j.setY(a.getY());
									}
								}
								else{
									if(a.intersects(hero)) {
										hero.meurt();
									}

									if (!a.intersects(hero)) {
										j.setX(a.getX());
										j.setY(a.getY());
									}			
								}
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
//						else {
//							Monstre a = new Monstre();
//							a.setX(j.getX());
//							a.setY(j.getY());
//							if (Math.random() > 0.5) {
//								if (Math.random() > 0.5) {
//									a.setY(a.getY()+5);
//								}
//								else {
//									a.setY(a.getY()-5);
//								}
//							}else {
//								if (Math.random() > 0.5) {
//									a.setX(a.getX()+5);
//								}
//								else {
//									a.setX(a.getX()-5);
//								}
//							}
//							if (!a.intersects(hero) && !a.intersects(tresor) && !panel.intersectsMur(a)) {
//								j.setX(a.getX());
//								j.setY(a.getY());
//							}
//						}
					}
				}
			}
		});

		SuivreHero.start();
		this.setSize(60 + Labyrinthe.length*30,60 + Labyrinthe.map.length*30);
		this.setContentPane(panel);
		this.requestFocus();
	}

	public DemoFrame() throws InterruptedException, IOException {
		this.setTitle("Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		JPanel choisirNiveau= new JPanel(new GridLayout(3, 1));
		JButton btn1 = new JButton("Niveau 1");
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				path="Lab1.txt";
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

		initialisation();
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
				if(!hero.mort) {
					if (Keys.UP.use()) {
						hero.move = true;
						hero.direction = Direction.UP;
						a.setY(a.getY()-5);
						if (!panel.intersectsMonstre(a)&&!panel.intersectsMur(a)) {
							hero.setY(hero.getY() - 5);	
							hero.setPaintY(hero.getPaintY() - 5);
							if(hero.intersects(tresor)){
								hero.gagne=true;
							}
						}
					} else if (Keys.DOWN.use()) {
						hero.move = true;
						hero.direction = Direction.DOWN;
						a.setY(a.getY()+5);
						if (!panel.intersectsMonstre(a)&&!panel.intersectsMur(a)) {
							hero.setY(hero.getY() + 5);	
							hero.setPaintY(hero.getPaintY() + 5);
							if(hero.intersects(tresor)){
								hero.gagne=true;
							}
						}
					} 
					if (Keys.LEFT.use()) {
						hero.move = true;
						hero.direction = Direction.LEFT;
						a.setX(a.getX()-5);
						if (!panel.intersectsMonstre(a)&&!panel.intersectsMur(a)) {
							hero.setX(hero.getX() - 5);	
							hero.setPaintX(hero.getPaintX() - 5);
							if(hero.intersects(tresor)){
								hero.gagne=true;
							}
						}
					} else if (Keys.RIGHT.use()) {
						hero.move = true;
						hero.direction = Direction.RIGHT;
						a.setX(a.getX()+5);
						if (!panel.intersectsMonstre(a)&&!panel.intersectsMur(a)) {
							hero.setX(hero.getX() + 5);	
							hero.setPaintX(hero.getPaintX() + 5);
							if(hero.intersects(tresor)){
								hero.gagne=true;
							}
						}
					} 

					if (Keys.ATTACK.use()) {
						int longueur_epee = 20;
						hero.attack = true;	
						switch (hero.direction){
						case RIGHT:
							a.setX(a.getX()+longueur_epee);
							panel.tuerMonstre(a);
						case LEFT:
							a.setX(a.getX()-longueur_epee);
							panel.tuerMonstre(a);
						case UP:
							a.setY(a.getY()-longueur_epee);
							panel.tuerMonstre(a);
						case DOWN:
							a.setY(a.getY()+longueur_epee);
							panel.tuerMonstre(a);
						}
					}	
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




		JPanel fini_perdu = new JPanel(new GridLayout(1,2,100,100));
		JPanel fini_gagne = new JPanel(new GridLayout(1,2,100,100));
		JLabel perdu = new JLabel("Perdu!");
		JLabel gagne = new JLabel("Gagné!");
	
		fini_perdu.add(perdu);
		fini_gagne.add(gagne);	
		
		JButton p_recommence = new JButton("Recommencer?");
		p_recommence.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initialisation();			
				synchronized (this) {
					this.notifyAll();
				}
			}
		});
		fini_perdu.add(p_recommence);
		JButton g_recommence = new JButton("Recommencer?");
		g_recommence.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initialisation();			
				synchronized (this) {
					this.notifyAll();
				}
			}
		});
		fini_gagne.add(g_recommence);
		JButton p_quit = new JButton("Quitter?");
		p_quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("quit");
				System.exit(0);
			}
		});
		fini_perdu.add(p_quit);
		JButton g_quit = new JButton("Quitter?");
		g_quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("quit");
				System.exit(0);
			}
		});
		fini_gagne.add(g_quit);
		
		while(true) {
			panel.repaint();
			//			System.out.println(monstrelist.size());
			//			System.out.println(Thread.activeCount());
			if (hero.mort) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {            //Il y a des pb
					e.printStackTrace();
				}
				this.setContentPane(fini_perdu);
				this.setSize(600,600);
				this.requestFocus();
			}
			if (hero.gagne) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {            //Il y a des pb
					e.printStackTrace();
				}
				this.setContentPane(fini_gagne);
				this.setSize(600,600);
				this.requestFocus();
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
