package Demo_du_jeu;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Labyrinthe extends JPanel {
	
	static int[][] map;
	
	private Hero hero;
	
	public Labyrinthe(String chemin) throws IOException {
		GenererLabyrinthe(chemin);
		this.hero = hero;
		for (int [] l : map) {
			for (int element: l) {
				System.out.print(element);
			}
			System.out.println("");
		}
	}
	
	public Labyrinthe(Hero hero,String chemin) throws IOException {
		GenererLabyrinthe(chemin);
		this.hero = hero;
		
	}
	
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		Image image = new ImageIcon("src/resource/wallunite.jpg").getImage();
		//g.drawImage(image, 30, 30, 30, 30, null);//x,y,w,h
		for (int i=0; i<map.length; i++) {
			for (int j=0; j<map[0].length; j++) {
				if (map[i][j]==1) {
					g.drawImage(image, j*30, i*30, 30, 30, null);
				}
			}
		}
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), 30, 30, null);
	}
	
	public static void GenererLabyrinthe(String chemin) throws IOException{
		BufferedReader fichier = new BufferedReader(new FileReader(chemin));
		String ligne;
		ArrayList<int []> liste = new ArrayList();
		while( ( ligne = fichier.readLine() ) != null ){
				String [] ligne_lettre = ligne.split("");
				int [] ligne_chiffre = new int [ligne.length()];
				for (int j=0;j<ligne.length();j++) {
					int chiffre = 0;
					if (ligne_lettre[j].equals("1")) {
						chiffre = 1;
					}
					ligne_chiffre[j] = chiffre;
				}
				liste.add(ligne_chiffre);
		}
		map = new int [liste.size()][liste.get(0).length];
		for (int i = 0;i<liste.size();i++) {
			map[i] = liste.get(i);
		}
	}
		
}