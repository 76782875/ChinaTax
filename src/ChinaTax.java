import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class ChinaTax {

	
	static BigDecimal monthlySocialSecurity =  new BigDecimal("17817").multiply(new BigDecimal("0.175"));  //based on foreigner in Shanghai
	static BigDecimal foreignerStartingPoint = new BigDecimal("4800");  //based on foreigner in Shanghai
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ChinaTax taxcalc = new ChinaTax();
		//https://zhidao.baidu.com/question/1735056546596979387.html 
		BigDecimal monthlyAllowance= new BigDecimal("60000");

		BigDecimal totalPackage = new BigDecimal("1700000");
		taxcalc.getOptimumBase(totalPackage,monthlyAllowance);
		
		totalPackage = new BigDecimal("1800000");
		taxcalc.getOptimumBase(totalPackage,monthlyAllowance);
		
		totalPackage = new BigDecimal("1900000");
		taxcalc.getOptimumBase(totalPackage,monthlyAllowance);
		
		totalPackage = new BigDecimal("2000000");
		taxcalc.getOptimumBase(totalPackage,monthlyAllowance);
	}
	
	
	public BigDecimal getOptimumBase(BigDecimal totalpackage, BigDecimal monthlyAllowance) throws FileNotFoundException{

		BigDecimal currentMaxTakeHome = new BigDecimal("0");
		BigDecimal optimumBase = new BigDecimal("0");
		
		System.out.println("total yearly package="+totalpackage+", monthly allowance="+monthlyAllowance);
		
		int totalInt = totalpackage.intValue();
		int allowanceInt = monthlyAllowance.intValue();
		
		String storeLast = "";
		
		//PrintWriter out = new PrintWriter("tax.csv");
		//out.println("currentYearlyBase,monthlyBase,monthlyTaxableIncome,monthlyTaxPayable,monthlyTakeHomeNet,yearlyBaseTakeHomeNet,yearEndBonus,bonusTaxPayable,bonusTakeHomeNet,actualTakeHome");
		
		
		for(int currentYearlyBase=allowanceInt; currentYearlyBase<totalInt; currentYearlyBase++){
			
			int bonusInt = totalInt-currentYearlyBase;
			//System.out.println("yearlyBaseInt="+i+",yearlyBonus="+bonusInt); 
			BigDecimal yearEndBonus = new BigDecimal(bonusInt);
			BigDecimal bonusTaxPayable = calculateBonusTax(yearEndBonus, false);
			BigDecimal bonusTakeHomeNet = yearEndBonus.subtract(bonusTaxPayable);
			
			
			BigDecimal monthlyBase = new BigDecimal(currentYearlyBase).divide(new BigDecimal("12"), RoundingMode.HALF_UP);
			BigDecimal monthlyTaxableIncome = monthlyBase.subtract(monthlyAllowance);
			BigDecimal monthlyTaxPayable = calculateMonthlyTax(monthlyTaxableIncome, false);
			BigDecimal monthlyTakeHomeNet = monthlyTaxableIncome.subtract(monthlyTaxPayable).subtract(monthlySocialSecurity);
			
			BigDecimal yearlyBaseTakeHomeNet = monthlyTakeHomeNet.multiply(new BigDecimal("12"));
			
			BigDecimal actualTakeHome = yearlyBaseTakeHomeNet.add(bonusTakeHomeNet);
			
			
					
			if(actualTakeHome.compareTo(currentMaxTakeHome)>0){
				
				storeLast = "\nYearlyBase="+currentYearlyBase+
				"\nmonthlyBase="+monthlyBase+
				"\nmonthlyTaxableIncome="+monthlyTaxableIncome+
				"\nmonthlyTaxPayable="+monthlyTaxPayable+
				"\nmonthlyTakeHomeNet="+monthlyTakeHomeNet+
				"\nyearlyBaseTakeHomeNet="+yearlyBaseTakeHomeNet+
				"\nyearEndBonus="+yearEndBonus+
				"\nbonusTaxPayable="+bonusTaxPayable+
				"\nbonusTakeHomeNet="+bonusTakeHomeNet+
				"\n******actualTakeHome*********"+
				"\n"+actualTakeHome+
				"\n*****************************";
			
				
				//System.out.println("new record=" + storeLast);
				//out.println(currentYearlyBase+","+monthlyBase+","+monthlyTaxableIncome+","+monthlyTaxPayable+","+monthlyTakeHomeNet+","+yearlyBaseTakeHomeNet+","+yearEndBonus+","+bonusTaxPayable+","+bonusTakeHomeNet+",="+actualTakeHome);
				currentMaxTakeHome = actualTakeHome;
				optimumBase =  new BigDecimal(currentYearlyBase);
			}else{
				//System.out.println("Skipping monthlyBase="+monthlyBase +" Because actual take home is ="+actualTakeHome + " Previous Take home is " + storeLast);
			}
		}
		System.out.println("Optimum combination is :" + storeLast);
		return optimumBase;
	}
	
	
	public BigDecimal calculateBonusTax(BigDecimal totalBonus, boolean showLog){
		
		
		totalBonus.setScale(2, RoundingMode.HALF_UP);
		BigDecimal divisor =  new BigDecimal("12");
		divisor.setScale(2, RoundingMode.HALF_UP);
		BigDecimal monthlyBonus = totalBonus.divide(divisor,2);
		BigDecimal taxRate = getTaxRate(monthlyBonus);
		BigDecimal quickDeduction = getQuickDeduction(monthlyBonus);
		BigDecimal actualTax = totalBonus.multiply(taxRate).subtract(quickDeduction);
		BigDecimal bonusTakeHome = totalBonus.subtract(actualTax);
		
		if(showLog) {
			System.out.println("total Bonus=" +totalBonus);
			System.out.println("Bonus monthly="+monthlyBonus);
			System.out.println("taxRate="+taxRate+",quickDeduction="+quickDeduction);
			System.out.println("actualTax="+actualTax);
			System.out.println("monthlyTakeHome="+bonusTakeHome);
		}
		
		return actualTax;
	}
	
	public BigDecimal calculateMonthlyTax(BigDecimal monthlyIncome, boolean showLog){

		BigDecimal taxableIncome = monthlyIncome.subtract(foreignerStartingPoint).subtract(monthlySocialSecurity);
		
		BigDecimal quickDeduction= getQuickDeduction(taxableIncome);
		BigDecimal taxRate= getTaxRate(taxableIncome);
		BigDecimal actualTax = taxableIncome.multiply(taxRate).subtract(quickDeduction);
		
		BigDecimal actualTakeHome = monthlyIncome.subtract(actualTax).subtract(monthlySocialSecurity);
		
		if(showLog) {
			System.out.println("monthlyIncome=" +monthlyIncome);
			System.out.println("taxableIncome=" +taxableIncome);
			System.out.println("tax Rate=" +taxRate + ",foreignerStartingPoint="+foreignerStartingPoint +",quickDeduction="+quickDeduction);
			System.out.println("actualTax="+actualTax);
			System.out.println("monthlyTakehome="+actualTakeHome);
		}
		
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
