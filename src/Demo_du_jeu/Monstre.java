package Demo_du_jeu;

public class Monstre extends BaseElement{

	public Monstre() {
		super();
		this.x=(int)(Math.random() * ((500) + 1))+60;
		this.y=(int)(Math.random() * ((100) + 1))+60;
		this.path="src/resource/monstre.jpg";
	}
}
