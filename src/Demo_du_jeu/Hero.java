package Demo_du_jeu;

import java.awt.event.KeyEvent;

public class Hero extends BaseElement {
	public boolean attack;
	public Hero() {
		super();
		this.x = 30;
		this.y = 30;
		this.path = "src/resource/Character_Right.png";
		attack = false;
	}


}
