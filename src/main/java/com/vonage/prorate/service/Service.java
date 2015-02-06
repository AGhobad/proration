
package com.vonage.prorate.service;
/**
* Service 
* 
* <P> This class represents the Service
*  
*  
* @author Ali Ghobadpour
* @version 0.1
*/

import java.util.Date;
import java.util.List;

public  class Service
{
    //TODO:
    //Implement all needed functionality for the service
  

    /******************************************************************/
    /******************************************************************/
  
    /**
     * <p>
     * determine if today is customer's bill cycle
     @ param customerBillDay int:1-31
     @ param dayOfMonth int:1-31
     *
     */      
    
    private boolean isTodayBillingDayForCustomer(int customerBillDay /* 1-31*/, int dayOfMonth /*1-31*/)
    {
	//TODO: Add functionality
	return false;
    }

    /******************************************************************/
    /******************************************************************/
  

    /**
     * <p>
     * go thru databse/list of all customers and assemble a list of customers who are due today. 
     * Do this by calling todayIsBillingDayForCustomer for each customer
     * @param month, int
     * @paranm dayOfMonth, int
     *
     */    
    private  List<Customer>  ListOfCustomersWhoAreDueToday(int month, int dayOfMonth)
    {
	//TODO: add functionality
	return null;
    }

    /******************************************************************/
    /******************************************************************/
             
    /**
     * <p>     
     * get a list of customers who are due today (by calling ListOfCustomersWhoAreDueToday) and send them notification to pay their bill 
     *
     * @param month, integer 
     * @param  dayOfMonth, integer
     */    
    public void sendNotificationToTodayDueCustomer(int month, int dayOfMonth)
    {
	//TODO: add functionality
    }
}
