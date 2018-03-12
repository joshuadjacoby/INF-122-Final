
import java.util.Random;

class Dice {
	private int randomint = 0;

	Dice() {
	}

	int roll() {
		Random r = new Random();
		randomint = r.nextInt(7);
		return (randomint == 0 ? 1 : randomint);
	}
}
