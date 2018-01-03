import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;


public class ChinaTaxTest {

	@Test
	public void testGetOptimumBase() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateBonusTax() {
		ChinaTax taxcalc = new ChinaTax();
		BigDecimal totalBonus = new BigDecimal("700000");
		BigDecimal tax = taxcalc.calculateBonusTax(totalBonus );
		assertEquals(0,tax.compareTo(new BigDecimal("239495.00")));
	}

	@Test
	public void testCalculateMonthlyTax() {
		ChinaTax taxcalc = new ChinaTax();
		BigDecimal monthlyIncome = new BigDecimal("120000");
		BigDecimal tax = taxcalc.calculateMonthlyTax(monthlyIncome );
		assertEquals(0,tax.compareTo(new BigDecimal("38335.00")));
	}


}
