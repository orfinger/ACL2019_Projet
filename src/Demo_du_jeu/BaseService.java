package Demo_du_jeu;

import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseService<T> implements InterfaceServices<T>{

	private CopyOnWriteArrayList<T> elementList = new CopyOnWriteArrayList<>();//ÔªËØÁÐ±í

	@Override
	public void init() {
		elementList.clear();
	}

	@Override
	public void add(T element) {
		this.elementList.add(element);
	}

	@Override
	public void remove(T element) {
		this.elementList.remove(element);
	}

	@Override
	public CopyOnWriteArrayList<T> getElementList() {
		return elementList;
	}

	@Override
	public void drawImage(Graphics g) {

	}

	/**
	 *suivre hero
	 */
	@Override
	public void suivrehero(Hero hero) {

	}

}
