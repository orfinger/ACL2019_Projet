package Demo_du_jeu;
import java.util.Random;

public class RandomEnum<E extends Enum<Direction>> {
	  Random RND = new Random();
	  E[] values;

	  public RandomEnum(Class<E> token) {
	    values = token.getEnumConstants();
	  }

	  public E random() {
	    return values[RND.nextInt(values.length)];
	  }
	}
