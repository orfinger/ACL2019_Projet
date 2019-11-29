package Demo_du_jeu;

import java.awt.*;
import javax.swing.*;

public class Tresor {

	private int x,y,width,height;
	private Image image;
	public Tresor() {
		this.image=new ImageIcon("src/resource/tresor.jpg").getImage();
		this.width=30;
		this.height=30;
		this.x=90;
		this.y=90;
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}

}
