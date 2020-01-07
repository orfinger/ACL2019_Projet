package Demo_du_jeu;

import java.awt.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Labyrinthe extends JPanel {
	
	/**
	 * v0.4
	 */
	private static final long serialVersionUID = 1L;

	static int[][] map;
	
	private Hero hero;
	private Tresor tresor;
	public static int length;
	
	private static ArrayList<Mur> murlist = new ArrayList<>(); 
	public ArrayList<Mur> getMurList(){
		return murlist;
	}
	
	public <E extends BaseElement> boolean intersectsMur(E element){
		for (Mur j : murlist) {
			if (j.intersects(element)) {
				return true;
			}
		}
		return false;
	}
	
	private ArrayList<Monstre> monstrelist;
	
	public ArrayList<Monstre> getMonstreList(){
		return monstrelist;
	}
	public void setMonstreList(ArrayList<Monstre> a){
		this.monstrelist=a;
	}
	
	public <E extends BaseElement> boolean intersectsMonstre(E element){
		for (Monstre j : monstrelist) {
			if (j.intersects(element)) {
				return true;
			}
		}
		return false;
	}
	
	public <E extends BaseElement> void tuerMonstre(E element){
		for (int j = 0; j<monstrelist.size();j++) {
			if (monstrelist.get(j).intersects(element)) {
				monstrelist.remove(j);
				break;
			}			
		}

	}
	
	
	public void addMonstre() {
		for(int i=0;i<Constante.nbMonstre;i++) {
			Monstre m=new Monstre();
			m.setX((int) (Math.random() * length *30)); 
			m.setY((int) (Math.random() * map.length *30)); 
			if(!this.intersectsMur(m)&&!this.intersectsMonstre(m)) {
				this.monstrelist.add(m);
			}
			else {
				i--;
			}
		}/*
		for(int i=0;i<Constante.nbFantones;i++) {
			Fantone m=new Fantone();
			m.setX((int) (Math.random() * length *30)); 
			m.setY((int) (Math.random() * map.length *30)); 
			if(!this.intersectsMur(m)&&!this.intersectsMonstre(m)) {
				this.monstrelist.add(m);
			}
			else {
				i--;
			}
		}*/
	}
	
	
	
	
	private static BufferedReader fichier;
	

	public Labyrinthe(Hero hero,Tresor tresor, String chemin) throws IOException {
		GenererLabyrinthe(chemin);
		this.hero = hero;
		this.tresor = tresor;	
	}

	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		Image image = new ImageIcon("src/resource/wall.png").getImage();
		Image prairie0 = new ImageIcon("src/resource/prairie0.png").getImage();
		Image prairie1 = new ImageIcon("src/resource/prairie1.png").getImage();
		for (int i=0; i<map.length; i++) {
			for (int j=0; j<length; j++) {
				if (map[i][j]==1) {
					g.drawImage(image, j*30, i*30, null);
				}else {
					if (map[i][j]==2) {
						g.drawImage(prairie1, j * 30, i * 30, null);
					}
					else {
						g.drawImage(prairie0, j * 30, i * 30, null);
					}
				}
			}
		}
		g.drawImage(hero.getImage(), hero.getPaintX(), hero.getPaintY(), null);
		g.drawImage(tresor.getImage(), tresor.getX(), tresor.getY(), null);
		
		for (Monstre j : monstrelist) {
			g.drawImage(j.getImage(), j.getX(), j.getY(), null);
		}
	}
	
	public static void GenererLabyrinthe(String chemin) throws IOException{
		fichier = new BufferedReader(new FileReader(chemin));
		String ligne;
		ArrayList<int []> liste = new ArrayList<int []>();
		
			
		while( ( ligne = fichier.readLine() ) != null ){
				String [] ligne_lettre = ligne.split("");
				if(ligne_lettre.length>length) {
					length=ligne_lettre.length;
				}
				int [] ligne_chiffre = new int [length];
				for (int j=0;j<ligne_lettre.length;j++) {
					int chiffre = 0;
					if (ligne_lettre[j].equals("1")) {
						chiffre = 1;
					}
					ligne_chiffre[j] = chiffre;
				}
				liste.add(ligne_chiffre);
		}
		map = new int [liste.size()][length];
		for (int i = 0;i<liste.size();i++) {
			for(int j=0;j<liste.get(i).length;j++) {
				map[i][j] = liste.get(i)[j];
				if (map[i][j]!=1) {
					if (Math.random()>0.8) {
						map[i][j]=2;
					}
				}
			}
			
		}
		for (int i = 0;i<map.length;i++) {
			for (int j = 0;j<map[i].length;j++) {
				if (map[i][j]==1) {
					Mur mur=new Mur();
					mur.setX(j*30);
					mur.setY(i*30);
					murlist.add(mur);
				}
			}
		}
	}
	
}