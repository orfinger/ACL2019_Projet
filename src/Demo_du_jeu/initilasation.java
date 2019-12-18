package Demo_du_jeu;

public class initilasation {
	public int nbMonstre;
	public initilasation(int nbmonster){
		this.nbMonstre=nbmonster;
		
	}
	
	public void cgNbMonster() {
		Constante.nbMonstre=this.nbMonstre;
	}
}
