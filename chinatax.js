function btnReset() {

  restResult();
  $("#totalPackage")[0].value = 1800000;
  $("#rentalAllowance")[0].value = 15000;
  $("#mealAllowance")[0].value = 0;
  $("#yearlymovingAllowance")[0].value = 0;
  $("#washingAllowance")[0].value = 0;
  $("#yearlyAirTicketAllowance")[0].value = 40000;
  $("#languageAllowance")[0].value = 0;
  $("#yearlyfamilyEducationAllowance")[0].value = 400000;
  $("#monthlySocialSecurity")[0].value = 3117;
  $("#foreignerStartingPoint")[0].value = 4800;
  $("#monthlyAllowanceRate")[0].value =35;
  
}


function btnResetLocal() {

  restResult();
  $("#totalPackage")[0].value = 1800000;
  $("#rentalAllowance")[0].value = 0;
  $("#mealAllowance")[0].value = 0;
  $("#yearlymovingAllowance")[0].value = 0;
  $("#washingAllowance")[0].value = 0;
  $("#yearlyAirTicketAllowance")[0].value = 0;
  $("#languageAllowance")[0].value = 0;
  $("#yearlyfamilyEducationAllowance")[0].value = 0;
  $("#monthlySocialSecurity")[0].value = 3117;
  $("#foreignerStartingPoint")[0].value = 3500;
  $("#monthlyAllowanceRate")[0].value = 0;
  
  
}

function restResult(){
  $("#lblTotalPackage")[0].innerText = 0;
  
  
  $("#lblMonthlyfamilyEducationAllowance")[0].innerText = 0;
  $("#lblMonthlyyAirTicketAllowance")[0].innerText = 0;
  $("#lblForeignerStartingPoint")[0].innerText = 4800;
  $("#lblMonthlySocialSecurity")[0].innerText = 0;
  
  $("#lblMonthlyBase4")[0].innerText = 0;
  $("#lblMonthlyTaxRate4")[0].innerText = 0;
  $("#lblMonthlyTaxableIncome4")[0].innerText = 0;
  $("#lblMonthlyTaxRate4")[0].innerText = 0;
  $("#lblMonthlyQuickDeduction4")[0].innerText = 0;
  $("#lblMonthlyTaxPayable4")[0].innerText = 0;
  $("#lblMonthlyTakeHomeNet4")[0].innerText = 0;
 
  $("#lblMonthlyBase8")[0].innerText = 0;
  $("#lblMonthlyTaxRate8")[0].innerText = 0;
  $("#lblMonthlyTaxableIncome8")[0].innerText = 0;
  $("#lblMonthlyTaxRate8")[0].innerText = 0;
  $("#lblMonthlyQuickDeduction8")[0].innerText = 0;
  $("#lblMonthlyTaxPayable8")[0].innerText = 0;
  $("#lblMonthlyTakeHomeNet8")[0].innerText = 0;
  
  
  $("#lblYearlyBaseTakeHomeNet")[0].innerText = 0;
  $("#lblYearEndBonus")[0].innerText = 0;
  $("#lblBonusTaxRate")[0].innerText = 0;
  $("#lblBonusQuickDeduction")[0].innerText = 0;
  $("#lblBonusTaxPayable")[0].innerText = 0;
  $("#lblBonusTakeHomeNet")[0].innerText = 0;

}

function btnCalc() {
 
  restResult();

  var totalPackage = parseFloat($("#totalPackage").val());
  if (isNaN(totalPackage)) {
    alert("无效的收入金额");
    $("#totalPackage")[0].focus();
    $("#totalPackage")[0].select();
    return;
  }
  $("#lblTotalPackage")[0].innerText = totalPackage;
  
  var yearlyfamilyEducationAllowance = parseFloat($("#yearlyfamilyEducationAllowance").val());
  if (isNaN(yearlyfamilyEducationAllowance)) {
    alert("无效的每年子女教育费");
    $("#yearlyfamilyEducationAllowance")[0].focus();
    $("#yearlyfamilyEducationAllowance")[0].select();
    return;
  }
  var monthlyfamilyEducationAllowance = parseFloat(yearlyfamilyEducationAllowance)/12;
  $("#lblMonthlyfamilyEducationAllowance")[0].innerText = monthlyfamilyEducationAllowance.toFixed(2);
  
  var foreignerStartingPoint = parseFloat($("#foreignerStartingPoint").val());
  if (isNaN(foreignerStartingPoint)) {
    alert("无效的工资起征点");
    $("#foreignerStartingPoint")[0].focus();
    $("#foreignerStartingPoint")[0].select();
    return;
  }
  $("#lblForeignerStartingPoint")[0].innerText = foreignerStartingPoint;
  
  var yearlyAirTicketAllowance = parseFloat($("#yearlyAirTicketAllowance").val());
  if (isNaN(yearlyAirTicketAllowance)) {
    alert("无效的每年探亲费");
    $("#yearlyAirTicketAllowance")[0].focus();
    $("#yearlyAirTicketAllowance")[0].select();
    return;
  }
  var monthlyAirTicketAllowance = parseFloat(yearlyAirTicketAllowance)/12;
  $("#lblMonthlyyAirTicketAllowance")[0].innerText = monthlyAirTicketAllowance.toFixed(2);
  
  
  var yearlymovingAllowance = parseFloat($("#yearlymovingAllowance").val());
  if (isNaN(yearlymovingAllowance)) {
    alert("无效的一次性搬迁费");
    $("#yearlymovingAllowance")[0].focus();
    $("#yearlymovingAllowance")[0].select();
    return;
  }
  var monthlymovingAllowance = parseFloat(yearlymovingAllowance)/12;
  
  var rentalAllowance = parseFloat($("#rentalAllowance").val());
  if (isNaN(rentalAllowance)) {
    alert("无效的每月住房补贴");
    $("#rentalAllowance")[0].focus();
    $("#rentalAllowance")[0].select();
    return;
  }
  
  var mealAllowance = parseFloat($("#mealAllowance").val());
  if (isNaN(mealAllowance)) {
    alert("无效的每月伙食补贴");
    $("#mealAllowance")[0].focus();
    $("#mealAllowance")[0].select();
    return;
  }
  
  var washingAllowance = parseFloat($("#washingAllowance").val());
  if (isNaN(washingAllowance)) {
    alert("无效的每月洗衣补贴");
    $("#washingAllowance")[0].focus();
    $("#washingAllowance")[0].select();
    return;
  }
  
  var languageAllowance = parseFloat($("#languageAllowance").val());
  if (isNaN(languageAllowance)) {
    alert("无效的每月语言训练补贴");
    $("#languageAllowance")[0].focus();
    $("#languageAllowance")[0].select();
    return;
  }
  
  var monthlySocialSecurity = parseFloat($("#monthlySocialSecurity").val());
  if (isNaN(monthlySocialSecurity)) {
    alert("无效的各项社会保险费");
    $("#monthlySocialSecurity")[0].focus();
    $("#monthlySocialSecurity")[0].select();
    return;
  }
  
  var quarterlyBonusRate = parseFloat($("#quarterlyBonusRate").val());
  if (isNaN(quarterlyBonusRate)) {
    alert("无效的季度奖百分比");
    $("#quarterlyBonusRate")[0].focus();
    $("#quarterlyBonusRate")[0].select();
    return;
  }
  
  var monthlyAllowanceRate = parseFloat($("#monthlyAllowanceRate").val());
  if (isNaN(monthlyAllowanceRate)) {
    alert("无效的可报销百分比");
    $("#monthlyAllowanceRate")[0].focus();
    $("#monthlyAllowanceRate")[0].select();
    return;
  }
  
    
    var monthlyTotalAllowance= rentalAllowance + mealAllowance + washingAllowance + monthlyAirTicketAllowance + monthlyfamilyEducationAllowance + languageAllowance + monthlymovingAllowance ;
		
    
    var currentMaxTakeHome = 0;
    var optimumBase = 0;
	var totalInt = totalPackage;
	var allowanceInt = monthlyTotalAllowance;

	var minimumMonthlyCash = 20000 + monthlyTotalAllowance
	
	var yearlyAllowanceInt = minimumMonthlyCash * 12;
	
	var bonusIntlbl = 0;
	
	var yearEndBonuslbl = 0;
	var bonusTaxPayablelbl = 0;
	var bonusTakeHomeNetlbl = 0;
	
	var monthlyAllowancelbl = 0;
	
	var monthlyBaselbl8 = 0;
	var monthlyTaxableIncomelbl8 = 0;
	var monthlyTaxPayablelbl8 = 0;
	var monthlyTakeHomeNetlbl8 = 0;
	
	var monthlyBaselbl4 = 0;
	var monthlyTaxableIncomelbl4 = 0;
	var monthlyTaxPayablelbl4 = 0;
	var monthlyTakeHomeNetlbl4 = 0;
	
	var yearlyBaseTakeHomeNetlbl = 0;
    var actualTakeHomelbl = 0;
	
	for(var currentYearlyBase=yearlyAllowanceInt; currentYearlyBase<totalInt; currentYearlyBase++){

		var bonusInt = totalInt-currentYearlyBase;
		var yearEndBonus = bonusInt;
		var bonusTaxPayable = calculateBonusTax(yearEndBonus);
		var bonusTakeHomeNet = yearEndBonus - bonusTaxPayable;
		
		var monthlyBase=0;
		var monthlyTaxableIncome=0;
		var monthlyTaxPayable=0;
		var monthlyTakeHomeNet=0;
		var yearlyBaseTakeHomeNet=0;
			
		var monthlyBase8=0;
		var monthlyTaxableIncome8=0;
		var monthlyTaxPayable8=0;
		var monthlyTakeHomeNet8=0;
		var monthTakeHomeNet8=0;
		
		var monthlyBase4=0;
		var monthlyTaxableIncome4=0;
		var monthlyTaxPayable4=0;
		var monthlyTakeHomeNet4=0;
		var monthTakeHomeNet4=0;
			
		if(quarterlyBonusRate>0){
		
		    monthlyBase8 = currentYearlyBase/(12*(quarterlyBonusRate/100+1));

			allowanceInt = monthlyBase8 * (monthlyAllowanceRate/100);
			
			monthlyTaxableIncome8 = monthlyBase8 - allowanceInt;
			monthlyTaxPayable8 = calculateMonthlyTax(monthlyTaxableIncome8, foreignerStartingPoint ,monthlySocialSecurity);
			monthlyTakeHomeNet8 = monthlyBase8 - monthlyTaxPayable8 - monthlySocialSecurity;
			monthTakeHomeNet8 =  monthlyTakeHomeNet8* 8;
			
			
			monthlyBase4 = monthlyBase8 * 3 * quarterlyBonusRate/100 + monthlyBase8;
			monthlyTaxableIncome4 = monthlyBase4 - allowanceInt;
			monthlyTaxPayable4 = calculateMonthlyTax(monthlyTaxableIncome4, foreignerStartingPoint ,monthlySocialSecurity);
			monthlyTakeHomeNet4 =  monthlyBase4 - monthlyTaxPayable4 - monthlySocialSecurity;
			
			monthTakeHomeNet4 = monthlyTakeHomeNet4 * 4 ;
			
			yearlyBaseTakeHomeNet = monthTakeHomeNet8 + monthTakeHomeNet4 ;
		
		}else{
		
		    
			monthlyBase = (currentYearlyBase/12);
			
			allowanceInt = monthlyBase * (monthlyAllowanceRate/100);
			monthlyTaxableIncome = monthlyBase - allowanceInt;
			monthlyTaxPayable = calculateMonthlyTax(monthlyTaxableIncome, foreignerStartingPoint ,monthlySocialSecurity);
			monthlyTakeHomeNet =  monthlyBase - monthlyTaxPayable - monthlySocialSecurity;
			yearlyBaseTakeHomeNet = monthlyTakeHomeNet * 12;
		}
		
		var actualTakeHome = yearlyBaseTakeHomeNet + bonusTakeHomeNet;
			
		if(actualTakeHome > currentMaxTakeHome ){
		
		    bonusIntlbl = bonusInt;
		    yearEndBonuslbl = yearEndBonus;
		    monthlyAllowancelbl = allowanceInt;
		    bonusTaxPayablelbl = bonusTaxPayable;
		    bonusTakeHomeNetlbl = bonusTakeHomeNet;
		    
		    monthlyBaselbl4 = monthlyBase4;
		    monthlyTaxableIncomelbl4 = monthlyTaxableIncome4;
		    monthlyTaxPayablelbl4 = monthlyTaxPayable4;
		    monthlyTakeHomeNetlbl4 = monthlyTakeHomeNet4;
		    
		    monthlyBaselbl8 = monthlyBase8;
		    monthlyTaxableIncomelbl8 = monthlyTaxableIncome8;
		    monthlyTaxPayablelbl8 = monthlyTaxPayable8;
		    monthlyTakeHomeNetlbl8 = monthlyTakeHomeNet8;
		    
		    monthlyBaselbl = monthlyBase;
		    monthlyTaxableIncomelbl = monthlyTaxableIncome;
		    monthlyTaxPayablelbl = monthlyTaxPayable;
		    monthlyTakeHomeNetlbl = monthlyTakeHomeNet;
		    
		    yearlyBaseTakeHomeNetlbl = yearlyBaseTakeHomeNet;
		    
			currentMaxTakeHome = actualTakeHome;
			actualTakeHomelbl = actualTakeHome;
			optimumBase = currentYearlyBase;
			
		}
		
	}
	if(quarterlyBonusRate>0){
	        var monthlyBonus2 = bonusIntlbl / 12 ;
	        
	        $("#lblMonthlyTotalAllowance")[0].innerText = monthlyTotalAllowance.toFixed(2);
			$("#lblTotalBase")[0].innerText = optimumBase.toFixed(2);
			$("#lblMonthlySocialSecurity")[0].innerText = monthlySocialSecurity.toFixed(2);
			$("#lblQuarterlyBonusRate")[0].innerText = quarterlyBonusRate.toFixed(2);
			$("#lblMonthlyAllowanceRate")[0].innerText = monthlyAllowanceRate;
			
			$("#lblTotalBaseLessQBonus")[0].innerText = (monthlyBaselbl8 * 12).toFixed(0) ;
			$("#lblTotalQBonus")[0].innerText = (optimumBase - (monthlyBaselbl8 * 12)).toFixed(0) ;
			
			$("#lblBonusTaxPayable")[0].innerText = bonusTaxPayablelbl.toFixed(2);
			$("#lblYearEndBonus")[0].innerText = bonusIntlbl.toFixed(2);
			$("#lblBonusTakeHomeNet")[0].innerText = bonusTakeHomeNetlbl.toFixed(2);
			$("#lblBonusTaxRate")[0].innerText = getTaxRate(monthlyBonus2)*100;
			$("#lblBonusQuickDeduction")[0].innerText = getQuickDeduction(monthlyBonus2) ;
			
			$("#lblMonthlyQuickDeduction4")[0].innerText = getQuickDeduction(monthlyTaxableIncomelbl4).toFixed(2);
			$("#lblMonthlyTaxRate4")[0].innerText = getTaxRate(monthlyTaxableIncomelbl4)*100;
			$("#lblMonthlyBase4")[0].innerText = monthlyBaselbl4.toFixed(2);
			$("#lblMonthlyTaxableIncome4")[0].innerText = monthlyTaxableIncomelbl4.toFixed(2);
			$("#lblMonthlyTaxPayable4")[0].innerText = monthlyTaxPayablelbl4.toFixed(2);
			$("#lblMonthlyTakeHomeNet4")[0].innerText = monthlyTakeHomeNetlbl4.toFixed(2);
			
			$("#lblMonthlyQuickDeduction8")[0].innerText = getQuickDeduction(monthlyTaxableIncomelbl8).toFixed(2);
			$("#lblMonthlyTaxRate8")[0].innerText = getTaxRate(monthlyTaxableIncomelbl8)*100;
			$("#lblMonthlyBase8")[0].innerText = monthlyBaselbl8.toFixed(2);
			$("#lblMonthlyTaxableIncome8")[0].innerText = monthlyTaxableIncomelbl8.toFixed(2);
			$("#lblMonthlyTaxPayable8")[0].innerText = monthlyTaxPayablelbl8.toFixed(2);
			$("#lblMonthlyTakeHomeNet8")[0].innerText = monthlyTakeHomeNetlbl8.toFixed(2);
			
			
			$("#lblYearlyBaseTakeHomeNet")[0].innerText = yearlyBaseTakeHomeNetlbl.toFixed(2);
			$("#lblActualTakeHome")[0].innerText = actualTakeHomelbl.toFixed(2);
		    
	}else{
		    var monthlyBonus2 = bonusIntlbl / 12 ;
	        
	        $("#lblMonthlyTotalAllowance")[0].innerText = monthlyTotalAllowance.toFixed(2);
			$("#lblTotalBase")[0].innerText = optimumBase.toFixed(2);
			$("#lblMonthlySocialSecurity")[0].innerText = monthlySocialSecurity.toFixed(2);
			$("#lblMonthlyAllowanceRate")[0].innerText = monthlyAllowanceRate.toFixed(2);
			$("#lblQuarterlyBonusRate")[0].innerText = quarterlyBonusRate;
			$("#lblMonthlyAllowanceRate")[0].innerText = monthlyAllowanceRate;
			
			$("#lblTotalBaseLessQBonus")[0].innerText = (monthlyBaselbl * 12).toFixed(0) ;
			$("#lblTotalQBonus")[0].innerText = (optimumBase - (monthlyBaselbl * 12)).toFixed(0) ;
			
			$("#lblBonusTaxPayable")[0].innerText = bonusTaxPayablelbl.toFixed(2);
			$("#lblYearEndBonus")[0].innerText = bonusIntlbl.toFixed(2);
			$("#lblBonusTakeHomeNet")[0].innerText = bonusTakeHomeNetlbl.toFixed(2);
			$("#lblBonusTaxRate")[0].innerText = getTaxRate(monthlyBonus2)*100;
			$("#lblBonusQuickDeduction")[0].innerText = getQuickDeduction(monthlyBonus2) ;
			
			$("#lblMonthlyQuickDeduction4")[0].innerText = getQuickDeduction(monthlyTaxableIncomelbl).toFixed(2);
			$("#lblMonthlyTaxRate4")[0].innerText = getTaxRate(monthlyTaxableIncomelbl)*100;
			$("#lblMonthlyBase4")[0].innerText = monthlyBaselbl.toFixed(2);
			$("#lblMonthlyTaxableIncome4")[0].innerText = monthlyTaxableIncomelbl.toFixed(2);
			$("#lblMonthlyTaxPayable4")[0].innerText = monthlyTaxPayablelbl.toFixed(2);
			$("#lblMonthlyTakeHomeNet4")[0].innerText = monthlyTakeHomeNetlbl.toFixed(2);
			
			$("#lblMonthlyQuickDeduction8")[0].innerText = getQuickDeduction(monthlyTaxableIncomelbl).toFixed(2);
			$("#lblMonthlyTaxRate8")[0].innerText = getTaxRate(monthlyTaxableIncomelbl)*100;
			$("#lblMonthlyBase8")[0].innerText = monthlyBaselbl.toFixed(2);
			$("#lblMonthlyTaxableIncome8")[0].innerText = monthlyTaxableIncomelbl.toFixed(2);
			$("#lblMonthlyTaxPayable8")[0].innerText = monthlyTaxPayablelbl.toFixed(2);
			$("#lblMonthlyTakeHomeNet8")[0].innerText = monthlyTakeHomeNetlbl.toFixed(2);
			
			
			$("#lblYearlyBaseTakeHomeNet")[0].innerText = yearlyBaseTakeHomeNetlbl.toFixed(2);
			$("#lblActualTakeHome")[0].innerText = actualTakeHomelbl.toFixed(2);
	}
   
}





function calculateBonusTax(totalBonus){
		if (totalBonus <= 0) {
			return 0;
		}
		var monthlyBonus = totalBonus / 12 ;
		var taxRate = getTaxRate(monthlyBonus);
		var quickDeduction = getQuickDeduction(monthlyBonus);
		var actualTax = totalBonus * taxRate - quickDeduction;
		

		return actualTax;
}
	
	
function calculateMonthlyTax(monthlyIncome, foreignerStartingPoint, monthlySocialSecurity ){
    var taxableIncome = monthlyIncome - foreignerStartingPoint - monthlySocialSecurity ;
    if (taxableIncome <= 0) {
		return 0;
	}
	var quickDeduction= getQuickDeduction(taxableIncome);
	var taxRate= getTaxRate(taxableIncome);
	actualTax = taxableIncome * taxRate - quickDeduction;
	return actualTax;
}

function getTaxRate(monthlyIncome){
	var taxRate = 0.00;
	
	monthlyIncome = monthlyIncome.toFixed(2);
	
	   if (monthlyIncome <= 1500) {
	    taxRate = 0.03;
	  } else if (monthlyIncome > 1500 && monthlyIncome <= 4500) {
		taxRate= 0.1;
	  } else if (monthlyIncome > 4500 && monthlyIncome <= 9000) {
	    taxRate = 0.2;
	  } else if (monthlyIncome > 9000 && monthlyIncome <= 35000) {
	    taxRate = 0.25;
	  } else if (monthlyIncome > 35000 && monthlyIncome <= 55000) {
	    taxRate = 0.3;
	  } else if (monthlyIncome > 55000 && monthlyIncome<= 80000) {
	    taxRate = 0.35;
	  } else {
	    taxRate = 0.45;
	  }

	return taxRate;
}



function getQuickDeduction(monthlyIncome){
	var quickDeduction = 0.00;
	
	monthlyIncome = monthlyIncome.toFixed(2);
	
	   if (monthlyIncome <= 1500) {
	    quickDeduction = 0;
	  } else if (monthlyIncome > 1500 && monthlyIncome <= 4500) {
    quickDeduction= 105
	  } else if (monthlyIncome > 4500 && monthlyIncome <= 9000) {
	    quickDeduction = 555
	  } else if (monthlyIncome > 9000 && monthlyIncome <= 35000) {
	    quickDeduction = 1005
	  } else if (monthlyIncome > 35000 && monthlyIncome <= 55000) {
	    quickDeduction = 2755
	  } else if (monthlyIncome > 55000 && monthlyIncome<= 80000) {
	    quickDeduction = 5505
	  } else {
	    quickDeduction = 13505;
	  }

	return quickDeduction;
}
