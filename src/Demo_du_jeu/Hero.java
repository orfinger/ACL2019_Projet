package Demo_du_jeu;


public class Hero extends BaseElement implements Runnable{
	public boolean attack;
	public Hero() {
		super();
		this.x = 30;
		this.y = 30;
		this.path = "src/resource/Character_Right.png";
		attack = false;
	}
	
	public void attack(){
		switch(this.direction) {
		case RIGHT:
			this.path = "src/resource/Character_AttackRight.png";
			break;
		case LEFT:
			this.path = "src/resource/Character_AttackRight.png";
			break;
		case UP:
			this.path = "src/resource/Character_AttackRight.png";
			break;
		case DOWN:
			this.path = "src/resource/Character_AttackRight.png";
			break;
		default:;
		}
	}
	
	public void run() {
		System.out.println("Hello from a thread!");
		 while(attack){
	          this.attack();
	          try {
	            Thread.sleep(1000);
	          } catch (InterruptedException e) {
	            e.printStackTrace();
	          }
	      }
	}    

}
