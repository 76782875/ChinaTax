import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import org.junit.Test;


public class ChinaTaxTest {

	@Test
	public void testGetOptimumBase() throws FileNotFoundException {
		ChinaTax taxcalc = new ChinaTax();
		BigDecimal totalPackage = new BigDecimal("1800000");
		BigDecimal monthlyAllowance= new BigDecimal("70000");
		BigDecimal optimumBase = taxcalc.getOptimumBase(totalPackage,monthlyAllowance);
		assertEquals(0,optimumBase.compareTo(new BigDecimal("1380006.00")));
		
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
