package Demo_du_jeu;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public enum Keys {


    LEFT(KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_RIGHT),
    UP(KeyEvent.VK_UP),
    DOWN(KeyEvent.VK_DOWN),
    ATTACK(KeyEvent.VK_A),
    HELP(KeyEvent.VK_H);
    

    private final static Set<Integer> cleSet = new HashSet<>();

    Keys(int cleValeur) {
        this.cleValeur = cleValeur;
    }

    private int cleValeur;

    /**
     * Si appuyer un cle
     *
     * @return
     */
    public boolean use() {
        return cleSet.contains(cleValeur);
    }

    /**
     * Ajouter un cle
     *
     * @param keyCode
     */
    public static void add(int keyCode) {
        cleSet.add(keyCode);
    }

    /**
     * Enlever un cle
     *
     * @param keyCode
     */
    public static void remove(int keyCode) {
        cleSet.remove(keyCode);
    }
    
    /**
     * Si null
     *
     * @return
     */
    public static boolean noMove() {
    	if(!cleSet.contains(KeyEvent.VK_LEFT)&&!cleSet.contains(KeyEvent.VK_RIGHT)&&!cleSet.contains(KeyEvent.VK_UP)&&!cleSet.contains(KeyEvent.VK_DOWN)) {
    		return true;
    	}
    	else {
    		return false;
    	}
       
    }
}