

package com.vonage.prorate.utils;
/**
* ProrationUtils
* 
* <P> This class contains some helper/static fucntions
*  
*  
* @author Ali Ghobadpour
* @version 0.1
*/



import java.util.Calendar;
import org.apache.log4j.Logger;

public  class ProrationUtils
{
    public static Logger myLogger = Logger.getLogger(ProrationUtils.class);
  
    /******************************************************************/
    /******************************************************************/
    /**
     * <p>
     * Prints Usage.
     *
     */    
    public static void usage()
    {
	myLogger.warn( "This program requires following parameters:\n" + 
	" Start of Service Date: Year: <4 digit year>  Month: <1-12> Date: <1-31>\n" +
	 "Customer Cycle Date: <1-31> \n" +
	 "Monthly Fee:\n" +
	 "Example: 1999 2 13 17 120");
    }
	
    /******************************************************************/
    /******************************************************************/
   /**
     * <p>
     * Prints a Calendar date in short form of dd/mm/yyyy for debugging purposes
     * @param name : a String
     * @param date: a  Calendar date
     *
     */    
    public static void printDate(String name, Calendar date)
    {	
	myLogger.warn(name + " " +   date.get(Calendar.DATE) + "/" + 
		      + (date.get(Calendar.MONTH) +1 ) + "/" + 
		      date.get(Calendar.YEAR) 
		      );
    }			     
    /******************************************************************/
    /******************************************************************/
      /**
     * <p>
     * Calculates a julian date representation of a Calandar
     * @param cal: a Calendar date
     * @return int: julianDay
     *
     */   
    public static int julianDay(Calendar cal ) 
    {	
	int year  = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1;	
	int day   = cal.get(Calendar.DAY_OF_MONTH);
	
	int a = (14 - month) / 12;
	int y = year + 4800 - a;
	int m = month + 12 * a - 3;
	int jdn = day + (153 * m + 2)/5 + 365*y + y/4 - y/100 + y/400 - 32045;
	return jdn;
    }
    /******************************************************************/
    /******************************************************************/ 
          /**
     * <p>
     * Validates given parameter to make sure they are within their ranges
     * @param  todayYr   an integer , part of  "subscription Date"
     * @param  todayMnt   an integer , part of  "subscription Date"
     * @param  today   an integer , part of  "subscription Date"
     * @param  chargeDate   an integer , daye of month when recurring charges start.
     * @param  monthlyFee a float, monthly charges for new service
     *
     */   
    public static boolean validate (int todayYr, int todayMnt, int today, int chargeDate, float monthlyFee)
    {
	if (todayMnt <= 0 || todayMnt >12)
	    return false;

	if (today <= 0 || today > 31)
	    return false;
	
	if (chargeDate  <= 0 || chargeDate > 31)
	    return false;
	
	if (monthlyFee < 0 )
	    return false;

	if (todayYr < 1900 || todayYr > 9999)
	    return false;
	return true;
    }
    /******************************************************************/
    /******************************************************************/
}

