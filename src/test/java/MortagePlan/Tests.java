package MortagePlan;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests {

	// In a real-world scenario I would definitely 
	// do some more thorough testing with 
	// different corner-cases and such.

	@Test
	@DisplayName("Read input file into list.")
	void readInputFile()
	{
		MortagePlan mp = new MortagePlan();
		mp.readProspects("src/test/resources/prospects.txt");
		
		ArrayList<Mortage> mortages = mp.getMortages();
		
		assertEquals(4, mortages.size());
		assertEquals("Juha",mortages.get(0).getCustomer());
		assertEquals("Karvinen",mortages.get(1).getCustomer());
		assertEquals("Claes Månsson",mortages.get(2).getCustomer());
		assertEquals("Clarencé,Andersson",mortages.get(3).getCustomer());

		assertEquals(1000,mortages.get(0).getTotalLoan(),0.001);
		assertEquals(4356,mortages.get(1).getTotalLoan(),0.001);
		assertEquals(1300.55,mortages.get(2).getTotalLoan(),0.001);
		assertEquals(2000,mortages.get(3).getTotalLoan(),0.001);

		assertEquals(5,mortages.get(0).getMonthlyInterest(),0.001);
		assertEquals(1.27,mortages.get(1).getMonthlyInterest(),0.001);
		assertEquals(8.67,mortages.get(2).getMonthlyInterest(),0.001);
		assertEquals(6,mortages.get(3).getMonthlyInterest(),0.001);

		assertEquals(2,mortages.get(0).getYears(),0.001);
		assertEquals(6,mortages.get(1).getYears(),0.001);
		assertEquals(2,mortages.get(2).getYears(),0.001);
		assertEquals(4,mortages.get(3).getYears(),0.001);
	}

	@Test
	@DisplayName("Test mortage calculation.")
	void testMortageCalculation()
	{
		MortagePlan mp = new MortagePlan();
		mp.readProspects("src/test/resources/prospects.txt");

		ArrayList<Mortage> mortages = mp.getMortages();

		assertEquals(43.87,mortages.get(0).getMonthlyPayment(),0.01);
		assertEquals(62.87,mortages.get(1).getMonthlyPayment(),0.01);
		assertEquals(59.22,mortages.get(2).getMonthlyPayment(),0.01);
		assertEquals(46.97,mortages.get(3).getMonthlyPayment(),0.01);
		
	}
}
