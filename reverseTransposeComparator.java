package gmit;

import java.util.Comparator;

/*
 * REVERSE TRANSPOSE COMPARATOR CLASS
 * 
 * Implement Comparator and used for columnar tranposition during decryption.
 * 
 */

public class ReverseTransposeComparator implements Comparator<KeyColumn> {
	
	// Comparator Overrides
	@Override
	public int compare(KeyColumn o1, KeyColumn o2) {
		if (o1.getIndex() > o2.getIndex()) {
			return 1;
		} else if (o1.getIndex() < o2.getIndex()) {
			return -1;
		}
		return 0;
	}
}
