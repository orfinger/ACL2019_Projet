package Demo_du_jeu;

import java.util.Arrays;
import java.util.Collections;

public class Monstre extends BaseElement implements Runnable{
	private java.util.List<String> list = Arrays.asList("up","down","right","left");
	private static int count=0;
	public Monstre() {
		super();
		this.path = "src/resource/monstre-r.png";
	}

	public void move_alea(boolean inters) {
		
		Monstre a = new Monstre();
		a.setX(this.getX());
		a.setY(this.getY());
		switch(list.get(0)) {
		case "up":
			a.setY(a.getY()-1);
			break;
		case "down":
			a.setY(a.getY()+1);
			break;
		case "right":
			a.setX(a.getX()+1);
			break;
		case "left":
			a.setX(a.getX()-1);
			break;
		default:
		}
		
		System.out.println(count);
	    if (count>6||inters) {
			// shuffle 
			Collections.shuffle(list);
			System.out.println(list.get(0));
			count=0;
		}
	    else{
			this.setX(a.getX());
			this.setY(a.getY());
			count++;
		}
	}
	
	public synchronized void move(){
		switch(this.direction) {
		case RIGHT:
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/monstre-r1.png";
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/monstre-r2.png";
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
			this.path = "src/resource/monstre-r.png";
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/monstre-r1.png";
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/monstre-r.png";
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case LEFT:
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/monstre-l1.png";
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/monstre-l2.png";
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
			this.path = "src/resource/monstre-l.png";
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/monstre-l1.png";
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/monstre-l.png";
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
//		case UP:
//			break;
//		case DOWN:
//			break;
		default:;
		}	
	}
	public void run() {
		while (true) {
			this.move();
		}
	}    
}
