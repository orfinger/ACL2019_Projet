package Demo_du_jeu;

public class Monstre extends BaseElement implements Runnable{

	public Monstre() {
		super();
		this.path = "src/resource/monstre-r.png";
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
