package Demo_du_jeu;

public class Hero extends BaseElement implements Runnable{
	public boolean attack;
	public boolean move;
	public Hero() {
		super();
		this.x = 30;
		this.y = 30;
		this.height = 30;
		this.width = 21;
		this.path = "src/resource/hero-r.png";
		attack = false;
		move = false;
	}
	
	
	public void move(){
		switch(this.direction) {
		case RIGHT:
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-r1.png";
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-r2.png";
			break;
		case LEFT:
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-l1.png";
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-l2.png";
			break;
		case UP:
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-b1.png";
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-b2.png";
			break;
		case DOWN:
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-f1.png";
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-f2.png";
			break;
		default:;
		}
		
	}
	
	public void attack(){
//		switch(this.direction) {
//		case RIGHT:
//			this.path = "src/resource/hero-ar1.png";
//			break;
//		case LEFT:
//			this.path = "src/resource/hero-ar1.png";
//			break;
//		case UP:
//			this.path = "src/resource/hero-ar1.png";
//			break;
//		case DOWN:
//			this.path = "src/resource/hero-ar1.png";
//			break;
//		default:;
//		}
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.path = "src/resource/hero-ar1.png";
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.path = "src/resource/hero-ar2.png";
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.path = "src/resource/hero-ar3.png";
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.path = "src/resource/hero-r.png";
		this.attack = false;
	}

	public void run() {
		synchronized (this.path) {
			if (attack) {
				this.attack();
			}
			if (move) {
				this.move();
			}
		}
	}    

}
