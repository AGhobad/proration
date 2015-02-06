
package com.vonage.prorate.engine;
/**
* Proration 
* 
* <P> This class calculates proration fee for a service company
*  
*  
* @author Ali Ghobadpour
* @version 0.1
*/

import java.util.GregorianCalendar;
import java.util.Calendar;
import com.vonage.prorate.utils.ProrationUtils;
import org.apache.log4j.Logger;

public  class Proration implements ProrationIF
{
    public static Logger myLogger;
    private static final int PRECISION;

    static
    {
	myLogger = Logger.getLogger(Proration.class);
	PRECISION = 100;
    }
    private int daysInChargeCycle = 0;
    private int numberofDayeToProrate = 0;

    /******************************************************************/
    /******************************************************************/
    /**
     * <p>
     * Given a subscription Date, a charge Date and a charge Fee, this method calculates the proration fees, if any.
     * <p>The "subscription Date" consists of 3 parts: Year, Month and Day of the Month.
     * The proration fee is rounded to the nearest penny.
     * <p>
     * @param  todayYr   an integer , part of  "subscription Date"
     * @param  todayMnt   an integer , part of  "subscription Date"
     * @param  today   an integer , part of  "subscription Date"
     * @param  chargeDate   an integer , daye of month when recurring charges start.
     * @param  monthlyFee a float, monthly charges for new service
     * @return      proration fee
     * Precondition:  All input parameters are valid and withion their expected range: Month:1-12, Date:1-31, Fee:non-negative, Year:4 digit integer.
     */
    public  float calcProration(int todayYr, int todayMnt, int today, int chargeDate, float monthlyFee)
    {
	myLogger.debug("todayYr:" + todayYr + " todayMnt:" + todayMnt + " today:" + today +
		       " chargeDate:" + chargeDate + " monthlyFee:" + monthlyFee);

	if (!ProrationUtils.validate(todayYr, todayMnt , today, chargeDate, monthlyFee))
	    {
		myLogger.error("Bad input.");
		return 0;
	    }
	
	if (chargeDate == today)
	    {
		myLogger.warn("No Prorate charge is necessary");
		return 0;
	    }
	// In Calendar, range of Months is 0-11
	todayMnt --;

	float prorateFee = 0;

	// first calculate cycle days as well as proration days.
	try
	    {
		if ( chargeDate < today)
		    {			
			/*
			  in this case, we have to calculate total days in billing cycle form 
			  this month's chargeDate to next month's chargeDate
			*/
			calcDaysInCycleForNextMonth(todayYr, todayMnt, today, chargeDate);
			numberofDayeToProrate = today - chargeDate;				
		    }
		else
		    {		
			/*
			  in this case, we have to calculate total days in billing cycle form 
			  last month's chargeDate to 
			  this month's chargeDate
			*/
			calcDaysInCycleForLastMont(todayYr, todayMnt, today,  chargeDate);
			numberofDayeToProrate = chargeDate - today;		
		    }
		 
		prorateFee = calcProrateFee(monthlyFee);
	    }
	catch (Exception ex)
	    {
		myLogger.error("exception:" +ex);
	    }
	return prorateFee;	
    }

    /******************************************************************/
    /******************************************************************/
    
    /*
     * Given two Dates, finds the diff in Days 
     *
     */        
    private void calcDaysInChargeCycle   (Calendar cal1, Calendar cal2)
    {
	// Is there a better way to get diff of 2 Calendar dates?
	// why Calendar does not have a "diffInDays(Calendar)" method?

	int j1 = ProrationUtils.julianDay (cal1);
	int j2 = ProrationUtils.julianDay (cal2);		
	myLogger.debug("*j1:" + j1 + "' j2:" + j2 );
	daysInChargeCycle = j1 - j2 + 1;
    }
    /******************************************************************/
    /******************************************************************/    
    /**
     * <p>
     * Calculates cycle days for previous billing cycle.
     * total days in billing cycle are form chargeDate to next month's chargeDate
     * @param  todayYr   an integer , part of  "subscription Date"
     * @param  todayMnt   an integer , part of  "subscription Date"
     * @param  today   an integer , part of  "subscription Date"
     * @param  chargeDate   an integer , daye of month when recurring charges start.
     *
     */     
    protected void calcDaysInCycleForNextMonth(int todayYr, int todayMnt, int today, int chargeDate)
    {
	Calendar  thisMonthDateOfCharge = new GregorianCalendar();
	thisMonthDateOfCharge.set(todayYr,todayMnt,chargeDate,0,0,0);
	ProrationUtils.printDate("thisMonthDateOfCharge:" , thisMonthDateOfCharge);
	
	Calendar  nextEndOfCycle = (GregorianCalendar) thisMonthDateOfCharge.clone();
	nextEndOfCycle.add(Calendar.MONTH,1);
	nextEndOfCycle.set(Calendar.DATE, chargeDate);
	nextEndOfCycle.add(Calendar.DATE,-1);
	ProrationUtils.printDate("nextEndOfCycle:" , nextEndOfCycle);
	
	calcDaysInChargeCycle(nextEndOfCycle,thisMonthDateOfCharge);	
    }
    /******************************************************************/
    /******************************************************************/
    /**
     * <p>
     * Calculates cycle days for next billing cycle.
     * Total days in billing cycle are form last month's chargeDate to 
     * this month's chargeDate.
     * @param  todayYr   an integer , part of  "subscription Date"
     * @param  todayMnt   an integer , part of  "subscription Date"
     * @param  today   an integer , part of  "subscription Date"
     * @param  chargeDate   an integer , daye of month when recurring charges start.
     *
     */  
    protected  void calcDaysInCycleForLastMont(int todayYr, int todayMnt, int today, int chargeDate)
    {
	Calendar  thisMonthEndOfCycle  = new GregorianCalendar();
	thisMonthEndOfCycle.set(todayYr,todayMnt,chargeDate,0,0,0);
	
	Calendar lastMonthDateOfCharge = (GregorianCalendar) thisMonthEndOfCycle.clone();
	
	thisMonthEndOfCycle.add(Calendar.DATE,-1);
	ProrationUtils.printDate("thisMonthEndOfCycle:", thisMonthEndOfCycle);
	lastMonthDateOfCharge.add(Calendar.MONTH,-1);
	ProrationUtils.printDate("lastMonthDateOfCharge:", lastMonthDateOfCharge);

	calcDaysInChargeCycle(thisMonthEndOfCycle,lastMonthDateOfCharge);	      
    }

    /******************************************************************/
    /******************************************************************/
    /**
     * <p>
     * Calculates proration fee
     * @param float monthlyFee
     * @return     float: proration fee
     *
     */  
    private	float calcProrateFee( float monthlyFee)
    {		
	if (daysInChargeCycle == 0 )
	    {
		myLogger.warn("error, daysInChargeCycle is 0. Please investigate.");
		return 0;
	    }	   
	float prorateFee = (monthlyFee * numberofDayeToProrate ) / daysInChargeCycle;
	prorateFee = (float)Math.round(prorateFee * PRECISION) / PRECISION ;
	myLogger.debug("daysInChargeCycle:" + daysInChargeCycle + "' numberofDayeToProrate:" + numberofDayeToProrate + "' prorateFee:" + prorateFee);
        
	return prorateFee;
    }
    /******************************************************************/
    /******************************************************************/        		       
}

