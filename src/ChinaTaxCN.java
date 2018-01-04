import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class ChinaTaxCN {

	
	static BigDecimal monthlySocialSecurity =  new BigDecimal("17817").multiply(new BigDecimal("0.175"));  //based on foreigner in Shanghai
	static BigDecimal foreignerStartingPoint = new BigDecimal("4800");  //based on foreigner in Shanghai
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ChinaTaxCN taxcalc = new ChinaTaxCN();
		//https://zhidao.baidu.com/question/1735056546596979387.html 
		System.out.println("****************************");
		System.out.println("每月住房补贴=15000");
		System.out.println("每月伙食补贴=10000");
		System.out.println("一次性搬迁费=0");
		System.out.println("每月洗衣费=6000 （每天=200）");
		System.out.println("每年回新加坡探亲费两次=40000， 分摊到每月=3333 （按新加坡航空票费）");
		System.out.println("每月语言训练费=0");
		System.out.println("每年子女教育费=400000，分摊到每月= 33333 ");
		System.out.println("每月总报销额 = 67666 ");
		BigDecimal monthlyAllowance= new BigDecimal("67666");
		System.out.println("*****************************");
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
		
		System.out.println("年薪加花红="+totalpackage+", 每月可报销额度="+monthlyAllowance);
		
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
				
				storeLast = "\n全年总工资（包含季度奖）="+currentYearlyBase+
				"\n月应发工资="+monthlyBase+
				"\n月工资应纳税额="+monthlyTaxableIncome+
				"\n月应纳税="+monthlyTaxPayable+
				"\n月税后收入="+monthlyTakeHomeNet+
				"\n年税后工资总收入="+yearlyBaseTakeHomeNet+
				"\n年终奖="+yearEndBonus+
				"\n年终奖应纳税额="+bonusTaxPayable+
				"\n年终奖税后收入="+bonusTakeHomeNet+
				"\n******全年税后总收入*********"+
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
		System.out.println("最佳方案:" + storeLast);
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
			System.out.println("年终奖=" +totalBonus);
			System.out.println("年终奖/12="+monthlyBonus);
			System.out.println("年终奖适用税率 ="+taxRate);
			System.out.println("速算扣除数="+quickDeduction);
			System.out.println("年终奖应纳税="+actualTax);
			System.out.println("年终奖税后收入="+bonusTakeHome);
		}
		
		return actualTax;
	}
	
	public BigDecimal calculateMonthlyTax(BigDecimal monthlyIncome, boolean showLog){


		BigDecimal taxRate= getTaxRate(monthlyIncome);
		BigDecimal quickDeduction= getQuickDeduction(monthlyIncome);
		BigDecimal taxableIncome = monthlyIncome.subtract(foreignerStartingPoint).subtract(monthlySocialSecurity);
		BigDecimal actualTax = taxableIncome.multiply(taxRate).subtract(quickDeduction);
		
		BigDecimal actualTakeHome = monthlyIncome.subtract(actualTax).subtract(monthlySocialSecurity);
		
		if(showLog) {
			System.out.println("月工资应纳税额=" +monthlyIncome);
			System.out.println("月各项社会保险费="+monthlySocialSecurity);
			System.out.println("起征点="+foreignerStartingPoint);
			System.out.println("速算扣除数="+quickDeduction);
			System.out.println("月适用税率=" +taxRate);
			System.out.println("月应纳税="+actualTax);
			System.out.println("月税后收入="+actualTakeHome);
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
