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
	private Monstre monstre;
	public static int lenth=0;
	
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
	//private ArrayList<Monstre> monstres;

	private static BufferedReader fichier;
	

	public Labyrinthe(Hero hero,Tresor tresor, Monstre monstre, String chemin) throws IOException {
		GenererLabyrinthe(chemin);
		this.hero = hero;
		this.tresor = tresor;
		this.monstre = monstre;
		
	}
//	public void addMonstres() {
//		int nb=Constante.nbMonstre;
//		 for(int i=0;i<nb;i++) {
//			 Monstre m=new Monstre();
//			 this.monstres.add(m);
//		 }
//	}
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		Image image = new ImageIcon("src/resource/wall.png").getImage();
		Image prairie0 = new ImageIcon("src/resource/prairie0.png").getImage();
		Image prairie1 = new ImageIcon("src/resource/prairie1.png").getImage();
		for (int i=0; i<map.length; i++) {
			for (int j=0; j<lenth; j++) {
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
		g.drawImage(monstre.getImage(), monstre.getX(), monstre.getY(), 30, 30, null);
	}
	
	public static void GenererLabyrinthe(String chemin) throws IOException{
		fichier = new BufferedReader(new FileReader(chemin));
		String ligne;
		ArrayList<int []> liste = new ArrayList<int []>();
		
			
		while( ( ligne = fichier.readLine() ) != null ){
				String [] ligne_lettre = ligne.split("");
				if(ligne_lettre.length>lenth) {
					lenth=ligne_lettre.length;
				}
				int [] ligne_chiffre = new int [lenth];
				for (int j=0;j<ligne_lettre.length;j++) {
					int chiffre = 0;
					if (ligne_lettre[j].equals("1")) {
						chiffre = 1;
					}
					ligne_chiffre[j] = chiffre;
				}
				liste.add(ligne_chiffre);
		}
		map = new int [liste.size()][lenth];
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