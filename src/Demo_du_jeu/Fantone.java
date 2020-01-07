package Demo_du_jeu;

public class Fantone extends Monstre{
	public Fantone() {
		super();
		this.path = "src/resource/monstre-r.png";
	}
	
	@Override
	public synchronized void move(){
		switch(this.direction) {
		case RIGHT:
			this.path = "src/resource/monstre-r1.png";
		case LEFT:
			this.path = "src/resource/monstre-l1.png";		
//		case UP:
//			break;
//		case DOWN:
//			break;
		default:;
		}	
	}   
}
