package misc;

import static org.junit.Assert.*;

import org.junit.Test;

public class MiscTest {

	@Test
	public void test() {
		
		       int number = 5;

		       // String format below will add leading zeros (the %0 syntax) 
		       // to the number above. 
		       // The length of the formatted string will be 3 characters.

		       String formatted = String.format("%03d", number);

		       System.out.println("Number with leading zeros: " + formatted);
		
	}

}
