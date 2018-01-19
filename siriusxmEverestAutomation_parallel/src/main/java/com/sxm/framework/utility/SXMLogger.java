package com.sxm.framework.utility;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;



/**
 * Logger which overrides the Log functions and provides the Execution times 
 * 
 * @author subramanyamp
 *
 */
public class SXMLogger extends Logger{
	
	private static final String TIMERMSG ="Time Taken to Execute the Block ";
	
	protected SXMLogger(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
		
	public void trace(Object message, Throwable t, StopWatch s) {
		// TODO Auto-generated method stub
		super.trace(message+TIMERMSG+s, t);
	}
	
	public void trace(String message, StopWatch s) {
		// TODO Auto-generated method stub
		super.trace(message+TIMERMSG+s);
	}

	public void warn(String message, StopWatch s) {
		// TODO Auto-generated method stub
		super.warn(message+TIMERMSG+s);
	}

	protected void info(String message, StopWatch s) {
		this.info(message+TIMERMSG+s);	
	}
	
	public static void main(String[] args){
		StopWatch s = new StopWatch();
		SXMLogger logger = (SXMLogger) SXMLogger.getLogger(SXMLogger.class.getName());
		logger.info("test", s);		
	}
}