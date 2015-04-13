package gmit;

import java.util.Comparator;

/*
 * TRANSPOSE COMPARATOR CLASS
 * 
 * Implement Comparator and used for columnar tranposition during encryption.
 * 
 */

public class TransposeComparator implements Comparator<KeyColumn> {

	// Comparator Overrides
	@Override
	public int compare(KeyColumn o1, KeyColumn o2) {
		if (o1.getKeyCharacter() > o2.getKeyCharacter()) {
			return 1;
		} else if (o1.getKeyCharacter() < o2.getKeyCharacter()) {
			return -1;
		} else if (o1.getKeyCharacter() == o2.getKeyCharacter() && o1.getIndex() < o2.getIndex()) {
			return 1;
		} else if (o1.getKeyCharacter() == o2.getKeyCharacter() && o1.getIndex() > o2.getIndex()) {
			return -1;
		}
		return 0;
	}
}
