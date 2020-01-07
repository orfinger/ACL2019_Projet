package Demo_du_jeu;

public class Fantone extends Monstre{
	public Fantone() {
		super();
	}
	
	@Override
	public synchronized void move(){
		switch(this.direction) {
		case RIGHT:
			this.path = "src/resource/fantone-r.png";
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case LEFT:
			this.path = "src/resource/fantone-l.png";	
			try {
				Thread.sleep(200);
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
}
