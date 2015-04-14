package gmit;

import java.util.Comparator;

/*
 * REVERSE TRANSPOSE COMPARATOR CLASS
 * 
 * Implement Comparator and used for columnar tranposition during decryption.
 * 
 * Big O: Best = O(N^2), Worst = O(N^2) (see footnote)
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

//Footnote: *
//As a generalization a for loop that is implicitly O(N) as 
//the statment inside the loop executes exactly the same amount
//of times as the control variable i (Equivelant to N).
//A for loop inside a for loop is generally O(N^2).
