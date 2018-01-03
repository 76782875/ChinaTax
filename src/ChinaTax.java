import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class ChinaTax {

	public static void main(String[] args) throws FileNotFoundException {
		BigDecimal monthlySocialSecurity =  new BigDecimal("17817").multiply(new BigDecimal("0.175"));
		ChinaTax taxcalc = new ChinaTax();
		
		BigDecimal totalPackage = new BigDecimal("1800000");
		BigDecimal monthlyAllowance= new BigDecimal("70000");
		BigDecimal optimumBase = taxcalc.getOptimumBase(totalPackage,monthlyAllowance);
		System.out.println("optimumBase with totalPackage="+totalPackage + ",monthlyAllowance= "+monthlyAllowance+" is ="+optimumBase);
	}
	
	
	public BigDecimal getOptimumBase(BigDecimal totalpackage, BigDecimal monthlyAllowance) throws FileNotFoundException{
		BigDecimal monthlySocialSecurity =  new BigDecimal("17817").multiply(new BigDecimal("0.175"));
		BigDecimal yearlyBase = new BigDecimal(1000000);
		
		BigDecimal currentMaxTakeHome = new BigDecimal("0");
		BigDecimal optimumBase = new BigDecimal("0");
		
		int totalInt = totalpackage.intValue();
		int yearlyBaseInt = yearlyBase.intValue();
		
		String storeLast = "";
		
		//PrintWriter out = new PrintWriter("tax.csv");
		//out.println("currentYearlyBase,monthlyBase,monthlyTaxableIncome,monthlyTaxPayable,monthlyTakeHomeNet,yearlyBaseTakeHomeNet,yearEndBonus,bonusTaxPayable,bonusTakeHomeNet,actualTakeHome");
		
		
		for(int currentYearlyBase=yearlyBaseInt; currentYearlyBase<totalInt; currentYearlyBase++){
			
			int bonusInt = totalInt-currentYearlyBase;
			//System.out.println("yearlyBaseInt="+i+",yearlyBonus="+bonusInt); 
			BigDecimal yearEndBonus = new BigDecimal(bonusInt);
			BigDecimal bonusTaxPayable = calculateBonusTax(yearEndBonus);
			BigDecimal bonusTakeHomeNet = yearEndBonus.subtract(bonusTaxPayable);
			
			
			BigDecimal monthlyBase = new BigDecimal(currentYearlyBase).divide(new BigDecimal("12"), RoundingMode.HALF_UP);
			BigDecimal monthlyTaxableIncome = monthlyBase.subtract(monthlyAllowance);
			BigDecimal monthlyTaxPayable = calculateMonthlyTax(monthlyTaxableIncome);
			BigDecimal monthlyTakeHomeNet = monthlyTaxableIncome.subtract(monthlyTaxPayable).subtract(monthlySocialSecurity);
			
			BigDecimal yearlyBaseTakeHomeNet = monthlyTakeHomeNet.multiply(new BigDecimal("12"));
			
			BigDecimal actualTakeHome = yearlyBaseTakeHomeNet.add(bonusTakeHomeNet);
			
			
					
			if(actualTakeHome.compareTo(currentMaxTakeHome)>0){
				
				storeLast = "currentYearlyBase="+currentYearlyBase+
				",monthlyBase="+monthlyBase+
				",monthlyTaxableIncome="+monthlyTaxableIncome+
				",monthlyTaxPayable="+monthlyTaxPayable+
				",monthlyTakeHomeNet="+monthlyTakeHomeNet+
				",yearlyBaseTakeHomeNet="+yearlyBaseTakeHomeNet+
				",yearEndBonus="+yearEndBonus+
				",bonusTaxPayable="+bonusTaxPayable+
				",bonusTakeHomeNet="+bonusTakeHomeNet+
				",actualTakeHome="+actualTakeHome;
			
				
				//System.out.println("new record=" + storeLast);
				//out.println(currentYearlyBase+","+monthlyBase+","+monthlyTaxableIncome+","+monthlyTaxPayable+","+monthlyTakeHomeNet+","+yearlyBaseTakeHomeNet+","+yearEndBonus+","+bonusTaxPayable+","+bonusTakeHomeNet+",="+actualTakeHome);
				currentMaxTakeHome = actualTakeHome;
				optimumBase =  new BigDecimal(currentYearlyBase);
			}else{
				//System.out.println("Skipping monthlyBase="+monthlyBase +" Because actual take home is ="+actualTakeHome + " Previous Take home is " + storeLast);
			}
		}
		System.out.println("***Best combination is :" + storeLast);
		return optimumBase;
	}
	
	
	public BigDecimal calculateBonusTax(BigDecimal totalBonus){
		
		//System.out.println("total Bonus=" +totalBonus);
		
		totalBonus.setScale(2, RoundingMode.HALF_UP);
		BigDecimal divisor =  new BigDecimal("12");
		divisor.setScale(2, RoundingMode.HALF_UP);
		BigDecimal monthlyBonus = totalBonus.divide(divisor,2);
		//System.out.println("Bonus monthly="+monthlyBonus);
		BigDecimal taxRate = getTaxRate(monthlyBonus);
		BigDecimal quickDeduction = getQuickDeduction(monthlyBonus);
		//System.out.println("taxRate="+taxRate+",quickDeduction="+quickDeduction);
		
		BigDecimal actualTax = totalBonus.multiply(taxRate).subtract(quickDeduction);
		//System.out.println("actualTax="+actualTax);
		
		return actualTax;
	}
	
	public BigDecimal calculateMonthlyTax(BigDecimal monthlyIncome){

		//System.out.println("monthly Income=" +monthlyIncome);
		BigDecimal standardDeduction = new BigDecimal("4800");
		
		BigDecimal taxRate= getTaxRate(monthlyIncome);
		BigDecimal quickDeduction= getQuickDeduction(monthlyIncome);
		//System.out.println("tax Rate=" +taxRate + ",standardDeduction="+standardDeduction +",quickDeduction="+quickDeduction);
				
		BigDecimal taxableIncome = monthlyIncome.subtract(standardDeduction);
		
		BigDecimal actualTax = taxableIncome.multiply(taxRate).subtract(quickDeduction);
		//System.out.println("actualTax="+actualTax);
		
		return actualTax;
	}
	
	public BigDecimal getQuickDeduction(BigDecimal monthlyIncome){
		String quickDeduction = "0.00";
		
		if(monthlyIncome.compareTo(new BigDecimal("1500")) <= 0 ){
			quickDeduction = "0.00";	
		}
		if(monthlyIncome.compareTo(new BigDecimal("1500")) > 0  && monthlyIncome.compareTo(new BigDecimal("4500")) <= 0 ){
			quickDeduction = "105";
		}
		if(monthlyIncome.compareTo(new BigDecimal("4500")) > 0  && monthlyIncome.compareTo(new BigDecimal("9000")) <= 0 ){

			quickDeduction = "555";
		}
		if(monthlyIncome.compareTo(new BigDecimal("9000")) > 0  && monthlyIncome.compareTo(new BigDecimal("35000")) <= 0 ){
			quickDeduction = "1005";
		}
		if(monthlyIncome.compareTo(new BigDecimal("35000")) > 0  && monthlyIncome.compareTo(new BigDecimal("55000")) <= 0 ){
			quickDeduction = "2755";
		}
		if(monthlyIncome.compareTo(new BigDecimal("55000")) > 0  && monthlyIncome.compareTo(new BigDecimal("80000")) <= 0 ){

			quickDeduction = "5505";
		}
		if(monthlyIncome.compareTo(new BigDecimal("80000")) > 0 ){

			quickDeduction = "13505";
		}

		return new BigDecimal(quickDeduction);
	}
				
	public BigDecimal getTaxRate(BigDecimal monthlyIncome){
		String taxRate = "0.00";
		
		if(monthlyIncome.compareTo(new BigDecimal("1500")) <= 0 ){
			taxRate = "0.03";	
		}
		if(monthlyIncome.compareTo(new BigDecimal("1500")) > 0  && monthlyIncome.compareTo(new BigDecimal("4500")) <= 0 ){
			taxRate = "0.10";
		}
		if(monthlyIncome.compareTo(new BigDecimal("4500")) > 0  && monthlyIncome.compareTo(new BigDecimal("9000")) <= 0 ){
			taxRate = "0.20";
		}
		if(monthlyIncome.compareTo(new BigDecimal("9000")) > 0  && monthlyIncome.compareTo(new BigDecimal("35000")) <= 0 ){
			taxRate = "0.25";
		}
		if(monthlyIncome.compareTo(new BigDecimal("35000")) > 0  && monthlyIncome.compareTo(new BigDecimal("55000")) <= 0 ){
			taxRate = "0.30";
		}
		if(monthlyIncome.compareTo(new BigDecimal("55000")) > 0  && monthlyIncome.compareTo(new BigDecimal("80000")) <= 0 ){
			taxRate = "0.35";
		}
		if(monthlyIncome.compareTo(new BigDecimal("80000")) > 0 ){
			taxRate = "0.45";
		}

		return new BigDecimal(taxRate) ;
	}

}
