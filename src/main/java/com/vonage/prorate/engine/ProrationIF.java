
package com.vonage.prorate.engine;

/**
* ProrationIF
* 
* <P> This class calculates proration fee for a service company.
* <p>A company sells services with recurring monthly charges.
* Customers are charged in advance for each month's service. 
* Each customer has a set day of the month when their recurring charges are due. 
* This can be any number from 1 to 31.
* A bill cycle is from billing day to billing day minus 1
* If an customer's billing day falls on a date that does not exist in a month, then the account is billed on the 
* last day of the month.
* If a customer purchases new service, a pro-rated amount is charged for the remaining days in the current
* bill cycle, including the day on which the service was purchased.
* Proration is done by day (not by minute, second, week, etc). 
* The result is rounded to the nearest penny.
*  
*  
* @author Ali Ghobadpour
* @version 0.1
*/

public   interface ProrationIF
{    		       
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
 */
    public abstract  float calcProration(int todayYr, int todayMnt, int today, int chargeDate, float monthlyFee);    
}

