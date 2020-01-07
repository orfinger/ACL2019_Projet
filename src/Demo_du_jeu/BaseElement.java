package Demo_du_jeu;

import java.awt.*;
import javax.swing.ImageIcon;


public abstract class BaseElement {
	
	protected int x,y,width,height;
	protected String path;
	protected Image image;
	protected Direction direction;
	
	public BaseElement() {
		
		this.width = Constante.ELEMENT_SIZE;
		this.height = Constante.ELEMENT_SIZE;
		this.direction = Direction.RIGHT;
	}

	public Image getImage() {
		this.image=new ImageIcon(path).getImage();
		return this.image;
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
	
	public void setDirestion(Direction d) {
		this.direction=d;
	}
	
	public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public <E extends BaseElement> boolean intersects(E element) {
        return this.getRectangle().intersects(element.getRectangle());
    }
    
    public <E extends BaseElement> boolean aCote(E element) {
    	int dx = this.getX() - element.getX();
		int dy = this.getY() - element.getY();
		int radial_distance = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    	return (radial_distance<=30);
      
    }
}
