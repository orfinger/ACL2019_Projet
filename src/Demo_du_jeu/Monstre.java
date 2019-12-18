package Demo_du_jeu;

public class Monstre extends BaseElement {

	public Monstre(Hero hero) {
		super();
		this.x = (int) (Math.random() * ((500) + 1)) + 60;
		this.y = (int) (Math.random() * ((100) + 1)) + 60;
		this.path = "src/resource/monstre.jpg";
	}

	public <E extends BaseElement> void suivreHero(E hero) {
		int dx = hero.getX() - this.x;
		int dy = hero.getY() - this.y;
		int radial_distance = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		int range = 300;
		System.out.println(dx + " " + dy);
		if (radial_distance < range) {
			if (dx != 0)
				this.x = this.x + dx / Math.abs(dx);
			if (dy != 0)
				this.y = this.y + dy / Math.abs(dy);
		}

	}

}
