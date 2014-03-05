package com.sys.utils;

import java.sql.Timestamp;

public class TimeUtils {

	
	private static TimeUtils item=new TimeUtils();
	
	private  Timestamp getCurrentTime() {
		Timestamp currentTime;
		java.util.Date utilDate = new java.util.Date();
		currentTime= new java.sql.Timestamp(utilDate.getTime());
		return currentTime;
	}

	public static Timestamp getNowTime()
	{
		return item.getCurrentTime();
	}
	
}
