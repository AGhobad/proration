
package com.vonage.prorate.main;
/**
* Proration 
* 
* <P> This program calculates proration fee for a service company
* <p>A company sells services with recurring monthly charges.
Customers are charged in advance for each month's service. 

Each customer has a set day of the month when their recurring charges are due. 
This can be any number from 1 to 31.
A bill cycle is from billing day to billing day minus 1
If an customer's billing day 
falls on a date that does not exist in a month, then the account is billed on the last day of the month.

If a customer purchases new service, a pro-rated amount is charged for the remaining days in the current
 bill cycle, 
including the day on which the service was purchased.
Proration is done by day (not by minute, second, week, etc). 

The result must be rounded to the nearest penny.
*  
*  
* @author Ali Ghobadpour
* @version 0.1
*/


import com.vonage.prorate.engine.Proration;
import com.vonage.prorate.utils.ProrationUtils;
import org.apache.log4j.Logger;

public  class ProrationMain
{    
    public static Logger myLogger = Logger.getLogger(ProrationMain.class);      
    public static void main(String[] args) 
    {
	myLogger.debug("Main, number of arguments:" + args.length);
	int todayYr      = 0;
	int todayMnt     = 0;
	int today        = 0;
	int chargeDate   = 0;
	float monthlyFee = 0;
	try
	    {
		// TODO
		// this is not best way of obtaining arsuments
		// better be like this: -yr 1999 -mnt 8....and so on
		// or: charge date could be in form of 18/11/2014
		todayYr = Integer.parseInt(args[0]);
		todayMnt = Integer.parseInt(args[1]);
		today = Integer.parseInt(args[2]);
		chargeDate = Integer.parseInt(args[3]);
		monthlyFee = Float.parseFloat(args[4]);
	    }
	catch( ArrayIndexOutOfBoundsException ex)
	    {  
		myLogger.error("parameter exception:" + ex);
		ProrationUtils.usage();
		return;	    
	    }	    		
	if (!ProrationUtils.validate(todayYr, todayMnt , today, chargeDate, monthlyFee))
	    {
		ProrationUtils.usage();
		return;	    
	    }
	Proration p = new Proration();
	float fee = p.calcProration(todayYr,
				    todayMnt  ,
				    today,
				    chargeDate,
				    monthlyFee
				    );
	myLogger.warn("Amount of prorate fee to charge:" + fee);	
    }
}

