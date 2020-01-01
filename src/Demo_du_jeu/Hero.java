package Demo_du_jeu;

public class Hero extends BaseElement implements Runnable{
	public boolean attack;
	public boolean move;
	protected int paintX,paintY;
	public Hero() {
		super();
		this.x = 30;
		this.y = 30;
		this.paintX = this.x;
		this.paintY = this.y;
		this.height = 30;
		this.width = 21;
		this.path = "src/resource/hero-r.png";
		attack = false;
		move = false;
	}
	public int getPaintX() {
		return this.paintX;
	}
	
	public int getPaintY() {
		return this.paintY;
	}
	public void setPaintX(int x) {
		this.paintX=x;
	}
	
	public void setPaintY(int y) {
		this.paintY=y;
	}
	public synchronized void  standing(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switch(this.direction) {
		case RIGHT:
			this.path = "src/resource/hero-r.png";
			break;
		case LEFT:
			this.path = "src/resource/hero-l.png";
			break;
		case UP:
			this.path = "src/resource/hero-b.png";
			break;
		case DOWN:
			this.path = "src/resource/hero-f.png";
			break;
		default:
			this.path = "src/resource/hero-r.png";
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
	
	public synchronized void attack(){
		switch(this.direction) {
		case RIGHT:
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-ar1.png";
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-ar2.png";
			try {
				Thread.sleep(50);
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
			break;
		case LEFT:		
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-al1.png";
			this.paintX-=17;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-al2.png";
			this.paintX-=3;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-al3.png";
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.paintX+=20;
			this.path = "src/resource/hero-l.png";		
			break;
		case UP:
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-ab1.png";
			this.paintY-=5;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-ab2.png";
			this.paintY+=1;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.paintY+=4;
			this.path = "src/resource/hero-ab3.png";
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-b.png";		
			break;
		case DOWN:
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-af1.png";
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-af2.png";
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.path = "src/resource/hero-af3.png";
			this.paintX-=11;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.paintX+=11;
			this.path = "src/resource/hero-f.png";		
			break;
		default:;
		}
		
		this.attack = false;
	}

	public void run() {
		while (true) {
			synchronized (this.path) {
				if (attack) {
					this.attack();
				} else if (move) {
					this.move();
					this.standing();
				}
			} 
		}
	}    

}
