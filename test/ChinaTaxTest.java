import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import org.junit.Test;


public class ChinaTaxTest {

	@Test
	public void testGetOptimumBase() throws FileNotFoundException {
		ChinaTax taxcalc = new ChinaTax();
		BigDecimal totalPackage = new BigDecimal("1800000");
		BigDecimal monthlyAllowance= new BigDecimal("60000");
		BigDecimal optimumBase = taxcalc.getOptimumBase(totalPackage,monthlyAllowance);
		assertEquals(0,optimumBase.compareTo(new BigDecimal("1380006.00")));
		
	}

	@Test
	public void testCalculateBonusTax() {
		ChinaTax taxcalc = new ChinaTax();
		BigDecimal totalBonus = new BigDecimal("420000");
		BigDecimal tax = taxcalc.calculateBonusTax(totalBonus, true);
		assertEquals(0,tax.compareTo(new BigDecimal("103995.00")));
		
		totalBonus = new BigDecimal("660000");
		tax = taxcalc.calculateBonusTax(totalBonus, true);
		assertEquals(0,tax.compareTo(new BigDecimal("195245.00")));
	}

	@Test
	public void testCalculateMonthlyTax() {
		ChinaTax taxcalc = new ChinaTax();
		BigDecimal monthlyIncome = new BigDecimal("39002");
		BigDecimal tax = taxcalc.calculateMonthlyTax(monthlyIncome, true);
		assertEquals(0,tax.compareTo(new BigDecimal("6766.00625")));
		
		monthlyIncome = new BigDecimal("55001");
		tax = taxcalc.calculateMonthlyTax(monthlyIncome, true);
		assertEquals(0,tax.compareTo(new BigDecimal("11369.90750")));
	}


}
