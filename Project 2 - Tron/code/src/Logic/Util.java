package Logic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Class Util. Some Functions that could be Useful.
 */
public class Util {
	/**
	 * Shuffle an Array.
	 *
	 * @param array
	 *            the array to shuffle
	 */
	public static void shuffleArray(int[] array) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i : array) {
			list.add(i);
		}

		Collections.shuffle(list);

		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
	}

}
