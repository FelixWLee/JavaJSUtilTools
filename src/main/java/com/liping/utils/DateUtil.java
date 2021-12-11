package com.liping.utils;

import com.liping.model.utils.DateBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;




/**
 * <p>Title: 日期时间处理</p>
 * <p>Description: 工具类</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * @version 1.0
 */
public class DateUtil
{
    private static DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
    static SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdfLongTime = new SimpleDateFormat("yyyyMMddHHmmss");
    static SimpleDateFormat sdfLongTimePlusMill = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
    static SimpleDateFormat sdfLongTimePlus = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DateUtil()
    {
    }
    
    
    /**
	 * Descrption:取得当前日期,格式为:yyyy-MM-dd HH:mm:ss
	 * @return String
	 * @throws Exception
	 */
	public static String getNowPlusTime(){
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new Date().getTime());
			nowDate = sdfLongTimePlus.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nowDate;
	}

    /**
	 * 自定义格式取当前时间的字符串
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String getNowFormat(String format) {
		SimpleDateFormat dateFormate = new SimpleDateFormat(format);
		return dateFormate.format(new java.sql.Date(new Date().getTime()));
	}
	
	/**
	 * 比较两个时间的大小
	 * @param tSrc		比较时间		格式： HHMMSS	e.g.: 173125
	 * @param tTaget	被比较时间	格式： HHMMSS	e.g.: 163548
	 * @return 0:等于	<0:小于		>0:大于
	 */
	public static int compareTime(String tSrc, String tTaget) {
		int tSrcInt = Integer.valueOf(tSrc);
		int tTagetInt = Integer.valueOf(tTaget);
		return tSrcInt - tTagetInt;
	}
    
    /**
     * 返回日期　格式　2008年1月4日　星期四
     * @param date
     * @return
     */
    public static String formatCWDate(Date date)
    {
        if (date == null) return "";
        else
        {
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年M月d日 EEE",Locale.SIMPLIFIED_CHINESE);
            return bartDateFormat.format(date);
        }
    }
    
    /***
     * 返回日期　格式　2008年1月4日
     * @param date
     * @return
     * @param day 相隔天数
     * @return
     */
    public static String addCWDate(int day)
    {
    	String dayStr = "";
        try{
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年M月d日 EEE",Locale.SIMPLIFIED_CHINESE);
            dayStr = bartDateFormat.format(string2Date(dateAdd("d",day,new Date())));
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        }
        return dayStr;
    }
    
    /***
     * 返回日期　格式　2008年1月4日
     * @param date
     * @return
     */
    public static String formatCDate(Date date)
    {
        if (date == null) return "";
        else
        {
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年M月d日");
            return bartDateFormat.format(date);
        }
    }
    
    /***
     * 返回日期　格式　2008年1月4日
     * @param date
     * @return
     * @param day 相隔天数
     * @return
     */
    public static String addCDate(int day)
    {
    	String dayStr = "";
        try{
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年M月d日");
            dayStr = bartDateFormat.format(string2Date(dateAdd("d",day,new Date())));
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        }
        return dayStr;
    }
    
    public static String getSendSMsgDateTime()
    {
    	String time="";
    	try
    	{
    	    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	    Date date = new Date();
    	    time = simpleDateFormat.format(date);
    	}
    	catch(Exception e)
    	{
    		time="format datetime error:"+e.getMessage();
    	}
	    //System.out.println("now time is:"+time);
    	    return time;
    } 
    public static String getNowTime()
    {
        GregorianCalendar gcNow = new GregorianCalendar();
        Date dNow = gcNow.getTime();
        DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
        return df.format(dNow);
    }

    public static String getNowDate()
    {
        GregorianCalendar gcNow = new GregorianCalendar();
        Date dNow = gcNow.getTime();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
        return df.format(dNow);
    }

    public static String getNowDateTime()
    {
        GregorianCalendar gcNow = new GregorianCalendar();
        Date dNow = gcNow.getTime();
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
        return df.format(dNow);
    }

    public static int getThisYear()
    {
        GregorianCalendar gcNow = new GregorianCalendar();
        return gcNow.get(GregorianCalendar.YEAR);
    }

    public static int getThisMonth()
    {
        GregorianCalendar gcNow = new GregorianCalendar();
        return gcNow.get(GregorianCalendar.MONTH) + 1;
    }

    public static int getToDayOfMonth()
    {
        GregorianCalendar gcNow = new GregorianCalendar();
        return gcNow.get(GregorianCalendar.DAY_OF_MONTH);
    }

    /**返当前时间字符串yyyy-mm-dd HH:mm:ss*/
    public static String todayString(){
        return formatSDate(new Date(System.currentTimeMillis()));
    }
    
    public static String formatDate(Date date)
    {
        if (date == null) return "";
        else return df.format(date);
    }

    public static String formatSDate(Date date)
    {
        if (date == null) return "";
        else
        {
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
            return bartDateFormat.format(date);
        }
    }
    
    public static String getNowYYYYMMDD()
    {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
        return bartDateFormat.format(new Date(System.currentTimeMillis()));
    }
    
    public static String getNowYMD()
    {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return bartDateFormat.format(new Date(System.currentTimeMillis()));
    }
    
    public static String getNowYYYYMMDDHHmmss()
    {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return bartDateFormat.format(new Date(System.currentTimeMillis()));
    }
    
    public static String getNowHHmmssSSS()
    {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return bartDateFormat.format(new Date(System.currentTimeMillis())).substring(8);
    }
    
    public static String formatMDate(Date date)
    {
        if (date == null) return "";
        else
        {
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMdHHmmss");
            return bartDateFormat.format(date);
        }
    }
    public static String formatMMDate(Date date)
    {
        if (date == null) return "";
        else
        {
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            return bartDateFormat.format(date);
        }
    }

    public static Date getDate()
    {
    	    Date date=new Date();
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-M-d");
            String datestr=bartDateFormat.format(date);
            try
            {
            date = bartDateFormat.parse(datestr.trim());
            }
            catch(Exception e)
            {
            	System.out.println("turn dateformat find error:"+e.getMessage());
            }
            return date;
    }
    public static String dateAdd(String interval, int number,
            Date date)
    {
        String strTmp = "";
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        if (interval.equals("y"))
        {
            int currYear = gc.get(Calendar.YEAR);
            gc.set(Calendar.YEAR, currYear + number);
        }
        
        else if (interval.equals("m"))
        {
            int currMonth = gc.get(Calendar.MONTH);
            gc.set(Calendar.MONTH, currMonth + number);
        }
        
        else if (interval.equals("d"))
        {
            int currDay = gc.get(Calendar.DATE);
            gc.set(Calendar.DATE, currDay + number);
        }
        
        else if (interval.equals("h"))
        {
            int currDay = gc.get(Calendar.HOUR);
            gc.set(Calendar.HOUR, currDay + number);
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        strTmp = bartDateFormat.format(gc.getTime());
        return strTmp;
    }
    
    public static String dateSAdd(String interval, int number,
            Date date)
    {
        String strTmp = "";
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        if (interval.equals("y"))
        {
            int currYear = gc.get(Calendar.YEAR);
            gc.set(Calendar.YEAR, currYear + number);
        }
        
        else if (interval.equals("m"))
        {
            int currMonth = gc.get(Calendar.MONTH);
            gc.set(Calendar.MONTH, currMonth + number);
        }
        
        else if (interval.equals("d"))
        {
            int currDay = gc.get(Calendar.DATE);
            gc.set(Calendar.DATE, currDay + number);
        }
        
        strTmp = df.format(gc.getTime());
        return strTmp;
    }
    
    public static String dateSAddYYYYMMDD(String interval, int number,
            Date date)
    {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        if (interval.equals("y"))
        {
            int currYear = gc.get(Calendar.YEAR);
            gc.set(Calendar.YEAR, currYear + number);
        }
        
        else if (interval.equals("m"))
        {
            int currMonth = gc.get(Calendar.MONTH);
            gc.set(Calendar.MONTH, currMonth + number);
        }
        
        else if (interval.equals("d"))
        {
            int currDay = gc.get(Calendar.DATE);
            gc.set(Calendar.DATE, currDay + number);
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return bartDateFormat.format(gc.getTime());
    }

    //得到一个月内,三个月内.本月内的时间 
    //0本月内 1 一个月内 2二个月内 3三个月内
    public static String dateYYYYMMDD_DATE(int interval)
    {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        
        if (interval==0)
        {
              gc.set(Calendar.DATE, 1);
        }
        
        else  if (interval==1)
        {
        	  int currMonth = gc.get(Calendar.MONTH);
        	  gc.set(Calendar.MONTH, currMonth - 1);
        }
        else  if (interval==2)
        {
        	  int currMonth = gc.get(Calendar.MONTH);
        	  gc.set(Calendar.MONTH, currMonth - 2);
        }
        else  if (interval==3)
        {
        	  int currMonth = gc.get(Calendar.MONTH);
        	  gc.set(Calendar.MONTH, currMonth - 3);
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
        return bartDateFormat.format(gc.getTime());
    }
    
    public static int dateDiff(Date a, Date b)
    {
        int tempDifference = 0;
        int difference = 0;
        Calendar earlier = Calendar.getInstance();
        Calendar later = Calendar.getInstance();

        if (a.compareTo(b) < 0)
        {
            earlier.setTime(a);
            later.setTime(b);
        }
        else
        {
            earlier.setTime(b);
            later.setTime(a);
        }

        while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR))
        {
            tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }

        if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR))
        {
            tempDifference = later.get(Calendar.DAY_OF_YEAR)
                    - earlier.get(Calendar.DAY_OF_YEAR);
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }

        return difference;
    }
    
    public static int dateDiffByNoFu(Date a, Date b)
    {
        int tempDifference = 0;
        int difference = 0;
        Calendar earlier = Calendar.getInstance();
        Calendar later = Calendar.getInstance();

        earlier.setTime(a);
        later.setTime(b);

        while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR))
        {
            tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }

        if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR))
        {
            tempDifference = later.get(Calendar.DAY_OF_YEAR)
                    - earlier.get(Calendar.DAY_OF_YEAR);
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }

        return difference;
    }

    public static String getDate(int i)
    {
        Calendar tt = Calendar.getInstance();
        int result = tt.get(Calendar.DAY_OF_WEEK);
        int day1 = 0;
        switch (result)
        {
            case 2: //Monday
                day1 = 4;
                break;
            case 3: //Tuesday
                day1 = 3;
                break;
            case 4: //Wednesday
                day1 = 2;
                break;
            case 5: //Thursday
                day1 = 1;
                break;
            case 6: //Friday
                day1 = 0;
                break;
            case 7: //Saturday
                day1 = 6;
                break;
            case 1: //sunday
                day1 = 5;
                break;
        }

        String getDate = dateSAdd("d", (day1+i), new Date());
        return getDate;
    }

    public static boolean checkTime()
    {
        boolean bol = false;
        Calendar tt = Calendar.getInstance();
        int result = tt.get(Calendar.DAY_OF_WEEK);
        Date sdate = new Date();
        int shour = tt.get(Calendar.HOUR_OF_DAY);
        int minute = tt.get(Calendar.MINUTE);
        
        switch (result)
        {
            case 1:
                break;
            case 7:
                break;
            default:
                if ((shour >= 9) && (shour < 12))
                {
                    bol = true;
                    break;
                }
                else if ((shour >= 14) && (shour <= 17))
                {
                    if(shour==17 && minute>30)
                        bol = false;
                    else
                        bol = true;
                    break;
                }
        }
        return bol;
    }
    
    public static String checkIpViewTime(int id)
    {
        String bol = "";
        Calendar tt = Calendar.getInstance();
        int result = tt.get(Calendar.DAY_OF_WEEK);
        Date sdate = new Date();
        int shour = tt.get(Calendar.HOUR_OF_DAY);
        //System.out.println("&&&"+shour);
        
        if (id != 1)
        {

           bol = "disabled";

        }
        return bol;
    }

	/**
	 * 将字符串转化为日期（java.util.Date）
	 * 字符串格式要求为 yyyy-MM-dd hh:mm:ss
	 * **/
	public static Date string2Date(String str) throws Exception {

		int year, month, day, hour, minute, second;
		String s1 = str.substring(0, str.indexOf(" "));
		String s2 = str.substring(str.indexOf(" ") + 1);

		StringTokenizer st = new StringTokenizer(s1, "-");
		year = Integer.parseInt(st.nextToken());
		month = Integer.parseInt(st.nextToken());
		day = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(s2, ":");
		hour = Integer.parseInt(st.nextToken());
		minute = Integer.parseInt(st.nextToken());
		second = Integer.parseInt(st.nextToken());

		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day, hour, minute, second);

		return c.getTime();
	}
	
	/**
     * 格式化字符串日期"2006-3-22 13:20:55"
     * @param date
     * @return
     */
    public static String formatString(String date)
    {
    	Date d1 = new Date();
    	if(date==null || date.equals("")) return "";
    	try
    	{
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			d1 = df.parse(date);
    	}
    	catch(Exception ex)
    	{
    		
    	}
    	return df.format(d1);
    	
    }
    
    /**
     * 格式化字符串日期"2006-3-22 13:20:55"
     * @param date
     * @return
     */
    public static String formatMString(String date)
    {
    	Date d1 = new Date();
    	if(date==null || date.equals("")) return "";
    	try
    	{
	    	DateFormat df = new SimpleDateFormat("yyyyMdHHmmss");
			d1 = df.parse(date);
    	}
    	catch(Exception ex)
    	{
    		
    	}
    	return df.format(d1);
    	
    }
    
    /**
     * 格式化字符串日期"2006-3-22 13:20:55"
     * @param date
     * @return
     */
    public static Date formatDate(String date)
    {
    	Date d1 = new Date();
    	if(date==null || date.equals("")) return new Date();
    	try
    	{
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			d1 = df.parse(date);
    	}
    	catch(Exception ex)
    	{
    		
    	}
    	return d1;
    	
    }
	/**
	 * 将字符串转化为日期（java.util.Date）
	 * 字符串格式要求为 yyyy-MM-dd hh:mm:ss
	 * 转化为yyy-MM-dd的util日期格式
	 * **/
	public static Date string2ShortDate(String str){
		try{
			int year, month, day, hour, minute, second;
			String s1 = str.substring(0, str.indexOf(" "));
			String s2 = str.substring(str.indexOf(" ") + 1);
	
			StringTokenizer st = new StringTokenizer(s1, "-");
			year = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			day = Integer.parseInt(st.nextToken());
	
			st = new StringTokenizer(s2, ":");
			hour = Integer.parseInt(st.nextToken());
			minute = Integer.parseInt(st.nextToken());
			second = Integer.parseInt(st.nextToken());
	
			Calendar c = Calendar.getInstance();
			c.set(year, month - 1, day, 0, 0, 0);
	
			return c.getTime();
		}catch(Exception ex){
			return null;
		}
	}
	

	/**
	 * 将字符串转化为日期（java.sql.Date）
	 * 字符串格式要求为 yyyy-MM-dd hh:mm:ss
	 * 转化为yyy-MM-dd的sql日期格式
	 * **/
	public static java.sql.Date string2SqlDate(String str) throws Exception {

		Date utilDate;
		java.sql.Date date=null;
		try {
			utilDate = DateUtil.string2ShortDate(str);
			date = new java.sql.Date(utilDate.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return date;
	}

	public static String dateString(Object dateObj){	
		if(dateObj!=null)
			return dateString(dateObj.toString());
		else 
			return "";
	}
	
	
	//返回当前周的起始日期和终止日期,
	public static String getWeekRange(){
		Calendar cal = Calendar.getInstance(); 

		int dayOfWeek = cal.get(cal.DAY_OF_WEEK);

		String rString = "";
		
		try {
			Date date1 = string2SqlDate(DateUtil.dateAdd("d",(2-dayOfWeek),new Date(System.currentTimeMillis())));
			Date date2 = string2SqlDate(DateUtil.dateAdd("d",(8-dayOfWeek),new Date(System.currentTimeMillis())));
			rString = "<b>"+date1 +"至"+date2+"</b>";
			return rString;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**比较两个日期字符串大小
     * 1表示str1>str2, -1表示str1<str2, 0表示str1=str2
    **/
    
    public static int compareDateString(String str1,String str2)
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");    	
    	int result = -1;
    	if(str1==null || "".equals(str1) || str2==null || "".equals(str2))
    		return result;
    	try
    	{
    		result = dateFormat.parse(str1).compareTo(dateFormat.parse(str2));
    		return result;
    		
    	}catch(Exception e){
    		 e.printStackTrace();
             return result;
    	}    	
    } 
    
    /**比较两个时间字符串大小
     * 1表示str1>str2, -1表示str1<str2, 0表示str1=str2
    **/
    public static int compareDateTimeString(String str1,String str2)
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d HH:mm");    	
    	int result = -1;
    	if(str1==null || "".equals(str1) || str2==null || "".equals(str2))
    		return result;
    	try
    	{
    		result = dateFormat.parse(str1).compareTo(dateFormat.parse(str2));
    		return result;
    		
    	}catch(Exception e){
    		 e.printStackTrace();
             return result;
    	}    	
    }   
    
    /**
	 * 判断当前日期是星期几<br>
	 * <br>
	 * 
	 * @param pTime
	 *            修要判断的时间<br>
	 * @return dayForWeek 判断结果<br>
	 * @Exception 发生异常<br>
	 */
	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
	/*	if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}*/
		dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		return dayForWeek;
	}
	
	
	public static List<DateBean> getDateList(int setDayAmount){
		List<DateBean> dateList = new ArrayList<DateBean>();
		DateBean dateBean = null;
		String tmpDate = "";
		int tmpWeek = 0;
		
		String tmpArray [] = null;
		tmpDate = dateSAdd("d",0, new Date());//取当天日期
		
		try {
			tmpWeek = dayForWeek(tmpDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//填前空
		for (int i = 0; i < tmpWeek; i++) {
			dateBean = new DateBean();
			dateBean.setMonth("");
			dateBean.setDate("");
			dateBean.setFormatDate("");
			dateBean.setWeek("");
			dateList.add(dateBean);
		}
		
		//添加从今天开始，N个日期
		for (int i = 0; i < setDayAmount; i++) {
			
			dateBean = new DateBean();
			tmpDate = dateSAdd("d", i, new Date());// 取当天日期
			String name = "";
			try {
				int tempweek = dayForWeek(tmpDate);
				switch (tempweek) {
				case 1:
					name = "星期一";
					break;
				case 2:
					name = "星期二";			
					break;
				case 3:
					name = "星期三";
					break;
				case 4:
					name = "星期四";
					break;
				case 5:
					name = "星期五";
					break;
				case 6:
					name = "星期六";
					break;
				case 0:
					name = "星期日";
					break;
				default:
					break;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			tmpArray = tmpDate.split("-");
			dateBean.setMonth(tmpArray[1]+"月");
			dateBean.setDate(tmpArray[2]);
			dateBean.setWeek(name);
			String fDate = "";
			try {
				fDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-M-d").parse(tmpDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(i == 0){
				dateBean.setIsToday("1");
			}
			dateBean.setFormatDate(fDate);
			dateList.add(dateBean);
		}
		
		//后补空
		int blankCount = 7 - (tmpWeek + setDayAmount) % 7;
		for (int i = 0; i < blankCount; i++) {
			dateBean = new DateBean();
			dateBean.setMonth("");
			dateBean.setDate("");
			dateBean.setFormatDate("");
			dateBean.setWeek("");
			dateList.add(dateBean);
		}

		return dateList;
	}

	 /**
     * 返回二个时间相差的分分钟数,如果一个为空，返回为0；
     * @param startDate，比如08：09
     * @param endDate，如18：09
     * @return
     */
    public static int getMinutesDiff(String startTime,String endTime){
         int ret=0;
         if("".equals(startTime.trim()) || "".equals(endTime.trim())){
            // return ret;
         }else{
             String startDateStr[]=startTime.split(":");
             String endDateStr[]=endTime.split(":");
             if(startDateStr[0].startsWith("0")){
                 startDateStr[0]=startDateStr[0].substring(1); 
             }
             if(startDateStr[1].startsWith("0")){
                 startDateStr[1]=startDateStr[1].substring(1); 
             }
             if(endDateStr[0].startsWith("0")){
                 endDateStr[0]=endDateStr[0].substring(1); 
             }
             if(endDateStr[1].startsWith("0")){
                 endDateStr[1]=endDateStr[1].substring(1); 
             }
             int s=Integer.parseInt(startDateStr[0])*60+Integer.parseInt(startDateStr[1]);
             int e=Integer.parseInt(endDateStr[0])*60+Integer.parseInt(endDateStr[1]);
             ret=e-s;
         }
         return ret;
        
    }
    
    
    /**
	 * 返回系统时间的后一个小时
	 * @param hour
	 * @return
	 */
	public static String getTimeByHour(int hour){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+hour);
		return new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
	}
	
	/**
	 * 获取当前日期的前day的日期
	 * @param day
	 * @return
	 */
	public static String getDateByUserNum(int day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();  
        c.add(Calendar.DATE, - day);  
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
	}
	
	/**
	 * Descrption:取得当前日期到毫秒极,格式为:yyyyMMddHHmmssSSSS
	 * @return String
	 * @throws Exception
	 */
	public static String getNowPlusTimeMill(){
		String nowDate = "";
		java.sql.Date date = null;
		date = new java.sql.Date(new Date().getTime());
		nowDate = sdfLongTimePlusMill.format(date);
		return nowDate;
	}
	
	/**
	 * Descrption:取得当前日期时间,格式为:YYYYMMDDHHMISS
	 * @return String
	 * @throws Exception
	 */
	public static String getNowLongTime() {
		String nowTime = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new Date().getTime());
			nowTime = sdfLongTime.format(date);
			return nowTime;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 得到当前日期，格式yyyy-MM-dd。
	 * @return String 格式化的日期字符串
	 */
	public static String getCurrDate() {
		return getFormattedDate(getDateByString(""));
	}
	/**
	 * 根据传入的日期字符串转换成相应的日期对象，如果字符串为空或不符合日期格
	 * 式，则返回当前时间。
	 * @param strDate String 日期字符串
	 * @return java.sql.Timestamp 日期对象
	 * */
	public static java.sql.Timestamp getDateByString(String strDate) {
		if (strDate.trim().equals("")) {
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
		try {
			strDate = getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss") + ".000000000";
			return java.sql.Timestamp.valueOf(strDate);
		} catch (Exception ex) {
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
	}
	
	/**
	 * 对输入的日期字符串按照默认的格式yyyy-MM-dd转换.
	 * @param strDate String 需要进行格式化的日期字符串
	 * @return String 经过格式化后的字符串
	 */
	public static String getFormattedDate(String strDate) {
		return getFormattedDate(strDate, "yyyy-MM-dd");
	}
	/**
	 * 对输入的日期字符串进行格式化,如果输入的是0000/00/00 00:00:00则返回空串.
	 * @param strDate String 需要进行格式化的日期字符串
	 * @param strFormatTo String 要转换的日期格式
	 * @return String 经过格式化后的字符串
	 */
	public static String getFormattedDate(String strDate, String strFormatTo) {
		if (strDate == null || strDate.trim().equals("")) {
			return "";
		}
		strDate = strDate.replace('/', '-');
		strFormatTo = strFormatTo.replace('/', '-');
		if (strDate.equals("0000-00-00 00:00:00") || strDate.equals("1800-01-01 00:00:00")) {
			return "";
		}
		String formatStr = strFormatTo; //"yyyyMMdd";
		if (strDate == null || strDate.trim().equals("")) {
			return "";
		}
		switch (strDate.trim().length()) {
		case 6:
			if (strDate.substring(0, 1).equals("0")) {
				formatStr = "yyMMdd";
			} else {
				formatStr = "yyyyMM";
			}
			break;
		case 8:
			formatStr = "yyyyMMdd";
			break;
		case 10:
			if (strDate.indexOf("-") == -1) {
				formatStr = "yyyy/MM/dd";
			} else {
				formatStr = "yyyy-MM-dd";
			}
			break;
		case 11:
			if (strDate.getBytes().length == 14) {
				formatStr = "yyyy年MM月dd日";
			} else {
				return "";
			}
		case 14:
			formatStr = "yyyyMMddHHmmss";
			break;
		case 19:
			if (strDate.indexOf("-") == -1) {
				formatStr = "yyyy/MM/dd HH:mm:ss";
			} else {
				formatStr = "yyyy-MM-dd HH:mm:ss";
			}
			break;
		case 21:
			if (strDate.indexOf("-") == -1) {
				formatStr = "yyyy/MM/dd HH:mm:ss.S";
			} else {
				formatStr = "yyyy-MM-dd HH:mm:ss.S";
			}
			break;
		default:
			return strDate.trim();
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(formatter.parse(strDate));
			formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(calendar.getTime());
		} catch (Exception e) {

			return "";
		}
	}
	
	public static String getFormattedDateUtil(Date dtDate, String strFormatTo) {
		if (dtDate == null) {
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(dtDate);
		} catch (Exception e) {

			return "";
		}
	}
	/**
	 * 对输入的日期按照默认的格式yyyy-MM-dd转换.
	 * @param dtDate 需要进行格式化的日期字符串
	 * @return String 经过格式化后的字符串
	 */
	public static String getFormattedDate(java.sql.Timestamp dtDate) {
		return getFormattedDate(dtDate, "yyyy-MM-dd");
	}
	public static String getFormattedDate(java.sql.Timestamp dtDate, String strFormatTo) {
		if (dtDate == null) {
			return "";
		}
		if (dtDate.equals(new java.sql.Timestamp(0))) {
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			if (Integer.parseInt(formatter.format(dtDate)) < 1900) {
				return "";
			} else {
				formatter = new SimpleDateFormat(strFormatTo);
				return formatter.format(dtDate);
			}
		} catch (Exception e) {

			return "";
		}
	}
	
	/**
	 * 取得今天是星期几
	 * @return
	 */
	public static String getCurrentWeekDay(){
		String weekString = "";
        final String dayNames[] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        weekString = dayNames[dayOfWeek - 1];
        return weekString;
	}
	
	/**
	 * 生成挂号可选择的日期
	 * @return String类型
	 * 		       格式：星期几,日期,今天|星期几,日期,明天
	 */
	public static String  getRegScheduleDateStr(){
		
		StringBuffer regScheduleBuffer = new StringBuffer();
		
		SimpleDateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formateWeek = new SimpleDateFormat("E");
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		
		regScheduleBuffer.append(formateWeek.format(calendar.getTime()));
		regScheduleBuffer.append(",").append(formateDate.format(calendar.getTime())).append(",");
		regScheduleBuffer.append("今天|");
		
		calendar.add(Calendar.DATE, 1);
		regScheduleBuffer.append(formateWeek.format(calendar.getTime())).append(",");
		regScheduleBuffer.append(formateDate.format(calendar.getTime())).append(",");
		regScheduleBuffer.append("明天|");
		
		return regScheduleBuffer.toString();
	}
	
	/**
	 * Descrption:取得当前日期,格式为:YYYY-MM-DD
	 * @return String
	 * @throws Exception
	 */
	public static String getNowFormateDate() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new Date().getTime());
			nowDate = sdfLong.format(date);
			return nowDate;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Descrption:取得后一天的日期,格式为:YYYY-MM-DD
	 * @return String
	 */
	public static String getNextLongDate(){
		String nextDate = "";
		Date date = new Date();
		date = increaseDay(date,1);
		nextDate = sdfLong.format(date);
		return nextDate;
	}
	
	/**
	 * 得到将date增加指定天数后的date
	 *
	 * @param date 日期
	 * @param intBetween 增加的天数
	 * @return date 加上intBetween天数后的日期
	 */
	public static Date increaseDay(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.DATE, intBetween);
		return calo.getTime();
	}
	
}

