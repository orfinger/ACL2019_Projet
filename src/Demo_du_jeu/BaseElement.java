package Demo_du_jeu;

import java.awt.*;
import javax.swing.*;

public abstract class BaseElement {
	
	protected int x,y,width,height;
	protected String path;
	protected Image image;
	
	public BaseElement() {
		
		this.width = Constante.ELEMENT_SIZE;
		this.height = Constante.ELEMENT_SIZE;
	}

	public Image getImage() {
		this.image=new ImageIcon("path").getImage();
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
