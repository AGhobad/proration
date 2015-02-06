package com.vonage.prorate.engine;


import java.util.GregorianCalendar;
import java.util.Calendar;
import com.vonage.prorate.utils.ProrationUtils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;


public class TestProration
{
    private Proration proration;
    private static final float DIFF = (float)0.01;

    @Before public void setUpMyTest() 
    {
	proration = new Proration ();
    }

   @Test public void testValidParams1() 
    {
	System.out.println("********************************  Running testValidParams1:");
       final float result = proration.calcProration(1999, 4,21,25,93);

       assertEquals(12.00, result,DIFF);
    }
    // service date < charge date 
   @Test public void testValidParams2() 
    {
	System.out.println("********************************  Running testValidParams2:");
	final float result = proration.calcProration(2013, 3,11,25,930);

       assertEquals(465.00, result,DIFF);
    }

        // service date > charge date 
   @Test public void testValidParams3() 
    {
	System.out.println("********************************  Running testValidParams3:");
	final float result = proration.calcProration(2013, 3,29,25,930);

       assertEquals(120.00, result,DIFF);
    }

        // service date - charge date  = 1
   @Test public void testValidOneDayCharge1() 
    {
	System.out.println("********************************  Running testValidOneDayCharge1:");
	final float result = proration.calcProration(2013, 3,29,28,930);

       assertEquals(30.00, result,DIFF);
    }

    // charge date - service date = 1

  @Test public void testValidOneDayCharge2() 
    {
	System.out.println("********************************  Running testValidOneDayCharge2:");
	final float result = proration.calcProration(2013, 3,27,28,930);

       assertEquals(33.21, result,DIFF);
    }

    // service date = charge date
   @Test public void testValidNoCharge() 
    {
	System.out.println("********************************  Running testValidNoCharge:");
	final float result = proration.calcProration(2013, 3,25,25,930);

       assertEquals(0.00, result,DIFF);
    }

    @Test public void testNonValidYr1() 
    {
	System.out.println("********************************  Running testNonValidYr1:");
	final float result = proration.calcProration(199, 0,21,25,93);
	
	assertEquals(0.00, result,DIFF);
    }
    
 @Test public void testNonValidYr2() 
    {
	System.out.println("********************************  Running testNonValidYr2:");
       final float result = proration.calcProration(19991, 0,21,25,93);

       assertEquals(0.00, result,DIFF);
    }


  @Test public void testNonValidMonth1() 
    {
	System.out.println("********************************  Running testNonValidMonth1:");
       final float result = proration.calcProration(1999, 0,21,25,93);

       assertEquals(0.00, result,DIFF);
    }

  @Test public void testNonValidMonth2() 
    {
	System.out.println("********************************  Running testNonValidMonth2:");
       final float result = proration.calcProration(1999, 15,21,25,93);

       assertEquals(0.00, result,DIFF);
    }

  @Test public void testNonValidDate1() 
    {
	System.out.println("********************************  Running testNonValidDate1:");
       final float result = proration.calcProration(1999, 1,-3,25,93);

       assertEquals(0.00, result,DIFF);
    }
  @Test public void testNonValidDate2() 
    {
	System.out.println("********************************  Running testNonValidDate2:");
       final float result = proration.calcProration(1999, 3,33,25,93);

       assertEquals(0.00, result,DIFF);
    }

  @Test public void testNonValidCycleDate1() 
    {
	System.out.println("********************************  Running testNonValidCycleDate1:");
       final float result = proration.calcProration(1999, 3,3,-4,93);

       assertEquals(0.00, result,DIFF);
    }
  @Test public void testNonValidCycleDate2() 
    {
	System.out.println("********************************  Running testNonValidCycleDate2:");
       final float result = proration.calcProration(1999, 3,3,44,93);

       assertEquals(0.00, result,DIFF);
    }
  @Test public void testNonValidFee1() 
    {
	System.out.println("********************************  Running testNonValidFee1:");
       final float result = proration.calcProration(1999, 3,3,4,-93);

       assertEquals(0.00, result,DIFF);
    }

    // leap year, testing Feb
  @Test public void testValidLeapYr1() 
    {
	System.out.println("********************************  Running testValidLeapYr1:");
	final float result = proration.calcProration(2000, 2, 7,11,93);

       assertEquals(12.00, result,DIFF);
    }
  
        // leap year, testing Feb
 @Test public void testValidLeapYr2() 
    {
	System.out.println("********************************  Running testValidLeapYr1:");
       final float result = proration.calcProration(2000, 2,15,11,93);

       assertEquals(12.83, result,DIFF);
    }

    // non-leap year, testing Feb
  @Test public void testValidFebNonLeapYr1() 
    {
	System.out.println("********************************  Running testValidFebNonLeapYr1:");
	final float result = proration.calcProration(2001, 2,7, 11 ,93);

       assertEquals(12.00, result,DIFF);
    }

        // non-leap year, testing Feb
 @Test public void testValidFebNonLeapYr2() 
    {
	System.out.println("********************************  Running testValidFebNonLeapYr2 :");
       final float result = proration.calcProration(2001, 2,15,11,93);

       assertEquals(13.29, result,DIFF);
    }

        // testing Dec, 
  @Test public void testValidDec1() 
    {
	System.out.println("********************************  Running testValidDec1 :");
	final float result = proration.calcProration(2001, 12,7, 11 ,93);

       assertEquals(12.4, result,DIFF);
    }

    // testing Dec, 
    @Test public void testValidDec2 () 
    {
	System.out.println("********************************  Running testValidDec2  :");
       final float result = proration.calcProration(2001, 12,15,11,93);

       assertEquals(12.00, result,DIFF);
    }

    // testing Dec 31st
  @Test public void testValidLastDayOfYr1() 
    {
	System.out.println("********************************  Running testValidLastDayOfYr1  :");
	final float result = proration.calcProration(2001, 12,31, 11 ,93);

       assertEquals(60.0, result,DIFF);
    }
        // testing Jan 1st
 @Test public void testValidFirstDayOfYr1() 
    {
	System.out.println("********************************  Running testValidFirstDayOfYr1  :");
	final float result = proration.calcProration(2001, 1,1, 11 ,93);

       assertEquals(30.0, result,DIFF);
    }

}
