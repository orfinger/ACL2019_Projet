package Demo_du_jeu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class DemoFrame extends JFrame{
	 
	public DemoFrame() throws InterruptedException {
		this.setTitle("Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(630, 660);
		this.setVisible(true);
		
		Hero hero = new Hero();
		
		Lybrinthe panel = new Lybrinthe(hero);
		this.add(panel); //add panel to frame
		this.setVisible(true);
		
		KeyListener key=new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (KeyEvent.VK_UP==e.getKeyCode()) {
					hero.setY(hero.getY()-5);
				} else if (KeyEvent.VK_DOWN==e.getKeyCode()) {
					hero.setY(hero.getY()+5);
				} else if (KeyEvent.VK_LEFT==e.getKeyCode()) {
					hero.setX(hero.getX()-5);
				} else if (KeyEvent.VK_RIGHT==e.getKeyCode()) {
					hero.setX(hero.getX()+5);
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
	
	public static void main(String[] args) {
		try {
			new DemoFrame();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
