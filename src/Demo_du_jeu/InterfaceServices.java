package Demo_du_jeu;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;


public interface InterfaceServices<T> {
	void init();
	/**
	 *
	 * @param g
	 */
	void drawImage(Graphics g);
	/**
	 * ajouter
	 *
	 * @param element
	 */

	void add(T element);

	/**
	 * enlever
	 *
	 * @param element
	 */
	void remove(T element);

	/**
	 * 获取元素列表
	 *
	 * @return
	 */
	CopyOnWriteArrayList<T> getElementList();

	/**
	 *suivre hero
	 */
	void suivrehero(Hero hero);
}
