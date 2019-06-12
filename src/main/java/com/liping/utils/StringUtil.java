package com.liping.utils;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
	/**
	 * 金额 分 转化为 元
	 * @param fenStr
	 * @return
	 */
	public static String fen2Yuan(String fenStr){
		String yuan = null;
        final int MULTIPLIER = 100;// 进率：分换算成元
        try{
        	yuan = new BigDecimal(fenStr).divide(new BigDecimal(MULTIPLIER)).setScale(2).toString();
        }catch(Exception e){
        	e.printStackTrace();
        }
	    return yuan;
	}
	
	
	/**
	 * 两个字符串相加
	 * @param a
	 * @param b
	 * @return
	 */
	public static String bigDecimalAdd(String a, String b){
		BigDecimal a1 = new BigDecimal(a);
		BigDecimal b1 = new BigDecimal(b);
		return a1.add(b1).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}

    public static String yuan2Fen(String yuanStr) {
        String yuan = null;
        final int MULTIPLIER = 100;// 进率：分换算成元
        try{
            yuan = new BigDecimal(yuanStr).multiply(new BigDecimal(MULTIPLIER)).setScale(0).toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return yuan;
    }

    /**
     * 将整数转换十六进制字符串
     * 
     * @param src
     *            要转换的整数
     * @param len
     *            转换的字串长度，0表示保留原始长度
     * @param r
     *            要替换的字串
     * @return 转换后的字符串
     */
    static public String convertIntToHexStr(int src, int len)
    {
        String ret = Integer.toHexString(src);
        if (len <= 0)
            return ret;
        else
            return fillString(ret, len, '0', true);
    }


    /**
     * 将整数转换十进制字符串
     * 
     * @param src
     *            要转换的整数
     * @param len
     *            转换的字串长度
     * @param 0
     *            表示保留原始长度
     * @return 转换后的字串,当len<=0时，返回原始的十进制字串长
     *         >0时，前补0，返回len长度的字串,len长度偏小时，直接返回转换后的字串
     */
    static public String convertIntToStr(int src, int len)
    {
        String ret = Integer.toString(src);
        if (len <= 0)
            return ret;
        else
            return fillString(ret, len, '0', true);
    }


    /**
     * 获得当前的日期字串
     * 
     * @return 日期字串,格式：20030101
     */
    static public String getYYYYMMDD()
    {
        java.util.GregorianCalendar d = new java.util.GregorianCalendar();
        int year = d.get(Calendar.YEAR);
        StringBuffer sYear = new StringBuffer(convertIntToStr(year, 4));
        int month = d.get(Calendar.MONTH) + 1;
        int day = d.get(Calendar.DAY_OF_MONTH);
        d = null;
        return sYear.append(convertIntToStr(month, 2)).append(
            convertIntToStr(day, 2)).toString();
    }


    /**
     * 获得当前的时间字串
     * 
     * @return 时间字串,格式：如果时间为06:01:01,则返回：060101
     */
    static public String getHHMISS()
    {
        java.util.GregorianCalendar d = new java.util.GregorianCalendar();
        int hour = d.get(Calendar.HOUR_OF_DAY);
        StringBuffer sHour = new StringBuffer(convertIntToStr(hour, 2));
        int min = d.get(Calendar.MINUTE);
        int sec = d.get(Calendar.SECOND);
        d = null;
        return sHour.append(convertIntToStr(min, 2)).append(
            convertIntToStr(sec, 2)).toString();
    }


    /**
     * 获得当前的日期字串
     * 
     * @return 日期字串,格式：20030101050101
     */
    static public String getYYYYMMDDHHMISS()
    {
        java.util.GregorianCalendar d = new java.util.GregorianCalendar();
        StringBuffer sYear = new StringBuffer(convertIntToStr(d
            .get(Calendar.YEAR), 4));
        return sYear.append(convertIntToStr(d.get(Calendar.MONTH) + 1, 2))
            .append(convertIntToStr(d.get(Calendar.DAY_OF_MONTH), 2)).append(
                convertIntToStr(d.get(Calendar.HOUR_OF_DAY), 2)).append(
                convertIntToStr(d.get(Calendar.MINUTE), 2)).append(
                convertIntToStr(d.get(Calendar.SECOND), 2)).toString();
    }


   
    /**
     * 分解字串到一个列表中
     * 
     * @param list
     *            分解后的字串存放列表，用于返回
     * @param src
     *            源字串
     * @param key
     *            分隔符
     * @return 无
     */
    static public void parseString(ArrayList<String> list, String src,
        String key)
    {
        if (src == null || src == "")
            return;
        // char c=src.charAt(src.length()-key.length());
        if (src.substring(src.length() - key.length(), src.length()).compareTo(
            key) != 0)
            src += key;
        String sTemp = src;
        int pos = sTemp.indexOf(key);
        String item = null;
        while (pos != -1)
        {
            item = sTemp.substring(0, pos);
            sTemp = sTemp.substring(pos + key.length());
            pos = sTemp.indexOf(key);
            if (item != null)
                list.add(item);
        }
        return;
    }


    /**
     * 将字符串用分隔符分成字符串数组
     * 
     * @param s
     *            源字符串
     * @param separator
     *            分隔符
     * @return 新字符串数组
     */
    public static String[] splitString(String s, String separator)
    {
        ArrayList<String> list = new ArrayList<String>();
        parseString(list, s, separator);
        String[] ret = new String[list.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = list.get(i);
        return ret;
    }


    /**
     * 将字符串转换大于或等于最小长度的字符串 举例： fillStr("abc",10,'0',true)将返回："0000000abc";
     * fillStr("abc",10,'0',false)将返回："abc0000000";
     * fillStr("12345678901234",10,'0',true)将返回："12345678901234";
     * 
     * @param str
     *            要转换的字符串
     * @param len
     *            转换后的字符串长度
     * @param c
     *            长度不够时填充的字符
     * @param fillleft
     *            true--向左填充字符c，false--向右填充字符c
     * @return 当str.length()<len时，返回填充后的字符串；否则，返回str
     */
    public static String fillString(String str, int len, char c,
        boolean fillleft)
    {
        if (str != null && str.getBytes().length >= len)
            return str;
        return getFixedLenStr(str, len, c, fillleft);
    }


    /**
     * 将字符串转换固定长度的字符串 举例： toFixedLenStr("abc",10,'0',true)将返回："0000000abc";
     * toFixedLenStr("abc",10,'0',false)将返回："abc0000000";
     * toFixedLenStr("12345678901234",10,'0',true)将返回："1234567890";
     * 
     * @param str
     *            要转换的字符串
     * @param len
     *            转换后的字符串长度
     * @param c
     *            长度不够时填充的字符
     * @param fillleft
     *            true--向左填充字符c，false--向右填充字符c
     * @return 当str.length()<len时，返回填充后的字符串；否则，返回str.substring(0,len)。
     */
    public static String getFixedLenStr(String str, int len, char c,
        boolean fillleft)
    {
        if (str == null)
            str = "";
        if (str.getBytes().length > len)
            return str.substring(0, len);
        else
        {
            int len1 = len - str.getBytes().length;
            if (len1 > 0)
            {
                byte[] b = new byte[len1];
                for (int i = 0; i < len1; i++)
                    b[i] = (byte) c;
                if (fillleft)
                    str = new String(b) + str;
                else
                    str += new String(b);
            }
            return str;
        }
    }


    /**
     * 将一个字节转换成十六进制字符串
     * 
     * @param src
     *            要转换的字节
     * @return 两个字节的十六制字符串
     */
    static public String convertByteToHexStr(byte src)
    {
        byte[] ret = new byte[2];
        ret[0] = (byte) (src >> 4 & 0x0F);
        ret[0] = (byte) (ret[0] > 9 ? (ret[0] - 10 + 'a') : (ret[0] + '0'));
        ret[1] = (byte) (src & 0x0F);
        ret[1] = (byte) (ret[1] > 9 ? (ret[1] - 10 + 'a') : (ret[1] + '0'));
        return new String(ret);
    }


    /**
     * 将十六进制字符串转换成字节数组
     * 
     * @param srcHex
     *            十六进制字符串
     * @return 字节数组
     */
    public static byte[] convertHexStrToByteArray(String srcHex)
    {
        byte[] retByteArray = new byte[srcHex.length() / 2];
        byte tmpByte;
        String sDecode;
        for (int i = 0; i < srcHex.length() / 2; i++)
        {
            sDecode = "0x" + srcHex.substring(2 * i, 2 * i + 2);
            tmpByte = Integer.decode(sDecode).byteValue();
            retByteArray[i] = tmpByte;
        }
        return retByteArray;
    }


    /**
     * 将十六进制字符串转换成一个字节
     * 
     * @param src
     *            要转换的字符串
     * @return 转换后的字节
     */
    static public byte convertHexStrToByte(String src)
    {
        byte b1 = '0', b2 = '0';
        byte[] b = src.getBytes();
        src = src.toLowerCase();
        if (b.length > 1)
        {
            b2 = b[1];
            b1 = b[0];
        }
        else if (b.length > 0)
            b2 = b[0];
        b1 = (byte) (b1 > '9' ? b1 - 'a' + 10 : b1 - '0');
        b2 = (byte) (b2 > '9' ? b2 - 'a' + 10 : b2 - '0');
        return (byte) (b1 << 4 | b2);
    }


    /**
     * 将字节数组转换成十六进制字符串
     * 
     * @param srcByteArray
     *            字节数组
     * @return 十六进制字符串
     */
    public static String convertByteArrayToHexStr(byte[] srcByteArray)
    {
        String sTemp = null;
        StringBuffer sOutLine = new StringBuffer();
        byte[] inByte = srcByteArray;
        for (int iSerie = 0; iSerie < inByte.length; iSerie++)
        {
            // System.out.println("inbyte"+iSerie+inByte[iSerie]);
            if (inByte[iSerie] < 0)
            {
                sTemp = Integer.toHexString(256 + inByte[iSerie]);
            }
            else
            {
                sTemp = Integer.toHexString(inByte[iSerie]);
            }
            if (sTemp.length() < 2)
            {
                sTemp = "0" + sTemp;
            }
            sTemp = sTemp.toUpperCase();
            sOutLine = sOutLine.append(sTemp);
        }
        return sOutLine.toString();
    }


    /**
     * @author qianjf
     * @return byte[]
     * @param byte[]
     *            功能：去掉byte数组最后的0x00
     */

    public static byte[] trimLast0x00(byte[] src)
    {
        if (src == null)
        {
            return null;
        }

        byte dest[] = null;
        for (int i = src.length - 1; i > 0; i--)
        {
            if (src[i] == 0x00)
            {

                continue;
            }
            else
            {
                dest = new byte[i + 1];
                System.arraycopy(src, 0, dest, 0, i + 1);
                break;
            }

        }

        return dest;
    }


    // 获得 非null 字串
    public static String getNotNullStr(String s)
    {
        return (s == null) ? "" : s.trim();
    }


    /**
     * 根据日期返回周几
     * 
     * @param date
     * @return
     */
    public static String getDateIsString(Date date) throws Exception
    {
        return new SimpleDateFormat("E").format(date).replace("星期", "周");
    }


    /**
     * 取系统日期与时间 年月日时分秒 return: e.g.:20111208181901
     */
    public static String getDateAndTimeString()
    {
        Date currentDate = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sf.format(currentDate);
    }


    /**
     * 根据传入的数据取到要缴费的总金额
     * 
     * @param jfChufang
     * @return
     */
    public static Double getMoney(String jfChufang)
    {
        String sc = jfChufang.substring(0, jfChufang.length() - 1);
        double jfMoney = 0;
        String[] strs = sc.split("S");
        for (String str : strs)
        {
            String[] sbs = str.split(":");
            jfMoney += Double.parseDouble(sbs[1].replaceAll(",", ""));
        }
        return jfMoney;
    }


    /**
     * 根据生日计算患者的年龄
     */
    public static String getPatientAge(String birthDay)
    {
        String patientBirthDay = birthDay.substring(0, 4);
        String currentDate = new SimpleDateFormat("yyyy").format(new Date());
        return String.valueOf(Integer.parseInt(currentDate)
                - Integer.parseInt(patientBirthDay));
    }


    /**
     * 判断用户输入的金额是否合法
     * 
     * @param amout
     * @return -1代表不合法,0代表合法
     */
    public static int judgeMoney(String amout)
    {
        int sum = 0;
        for (int i = 0; i < amout.length(); i++)
        {
            char c = amout.charAt(i);
            if (c == '.')
            {
                sum++;
            }
        }
        if (sum > 1
                || new BigDecimal(amout).compareTo(new BigDecimal("0")) == 0)
        {
            return -1;// 代表金额不合法
        }
        return 0;
    }


    /**
     * 获取身份证号
     */
    public String getIdCardNo()
    {
        InputStream inStream = getClass().getResourceAsStream("/idCard.txt");
        System.out.println(inStream);
        byte[] buffer = new byte[1024];
        int len;
        try
        {
            while ((len = inStream.read(buffer)) != -1)
            {
                return new String(buffer, 0, len);
            }
        }
        catch (IOException e)
        {
        }
        return null;
    }


    /**
     * 功能：字符串左边0
     */

    public static String trimLeft0(String src)
    {
        if (src == null)
        {
            return null;
        }
        while (src.startsWith("0") && src.length() > 1)
        {
            src = src.substring(1);
        }

        return src;
    }


    /*
     * 填充数据 len 字节数组长度 原数据总长度 check 填充字节 type填充方式 1左边，2右边 defaultData 默认数据
     * 
     */
    public static String fillByString(int len, String check, int type,
        String defaultData)
    {
        String ret = defaultData;
        int fillLen = len - defaultData.length();
        String fillStr = "";
        if (fillLen > 0)
        {
            for (int i = 0; i < fillLen; i++)
            {
                fillStr = fillStr + check;
            }
        }
        if (type == 1)
            ret = fillStr + defaultData;
        if (type == 2)
            ret = defaultData + fillStr;
        return ret;
    }


    /*
     * 填充数据 湖南专用,最前面加zl(诊疗)
     */
    public static String fillByStringForZL(String defaultData)
    {
        if (defaultData == null)
            defaultData = "";
        int len = 18;
        String check = "0";
        String ret = defaultData;
        int fillLen = len - defaultData.length();
        String fillStr = "";
        for (int i = 0; i < fillLen; i++)
        {
            fillStr = fillStr + check;
        }
        ret = fillStr + defaultData;
        return ret;
    }


    /*
     * 填充数据 湖南专用,最前面加zl(诊疗)
     */
    public static String processBankNum(String defaultData)
    {
        if (defaultData == null)
            defaultData = "";
        int len = 18;
        String check = "0";
        String ret = defaultData;
        if (defaultData != null && !defaultData.equals("")
                && defaultData.length() >= 18)
        {
            return defaultData.substring(0, 18);
        }

        int fillLen = len - defaultData.length();
        String fillStr = "";
        for (int i = 0; i < fillLen; i++)
        {
            fillStr = fillStr + check;
        }
        ret = fillStr + defaultData;
        return ret;
    }


    // 字符串转化为int型
    public static int parseInt(String s)
    {
        return parseInt(s, 0);
    }


    public static int parseInt(String s, int defaultValue)
    {
        int rt = defaultValue;
        try
        {
            s = s.trim();
            rt = Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            rt = defaultValue;
        }
        catch (NullPointerException e)
        {
            rt = defaultValue;
        }
        catch (Exception e)
        {
            rt = defaultValue;
        }
        return rt;
    }


    // 分转换成元,测试例子如下
//  System.out.println(StringUtil.fen2yuan(""));
//  System.out.println(StringUtil.fen2yuan("0"));
//  System.out.println(StringUtil.fen2yuan("1"));
//  System.out.println(StringUtil.fen2yuan("11"));
//  System.out.println(StringUtil.fen2yuan("110"));
//  System.out.println(StringUtil.fen2yuan("1110"));
//  System.out.println(StringUtil.fen2yuan("110001"));
//  System.out.println(StringUtil.fen2yuan("000000100100"));
    
    public static String fen2yuan(String fen)
    {
        if ((fen == null) || (fen.equals("")) || (fen.equals("0")))
        {
            return "0.00";
        }

        while ((fen.substring(0,1).equals("0")) && (fen.length() != 1))
        {
            fen = fen.substring(1);
        }

        if (fen.length() == 1)
        {
            fen = "0.0" + fen;
        }
        else if (fen.length() == 2)
        {
            fen = "0." + fen;
        }
        else
        {
            fen = fen.substring(0, fen.length() - 2) + "."
                    + fen.substring(fen.length() - 2, fen.length());
        }
        return fen;
    }
    

	public static String fomartDateStr(String dateString,String separation)
	{
		if (dateString == null || dateString.equals("")|| dateString.length()!=8 || separation == null ||separation.equals(""))
		{
			return dateString;
		}
		StringBuffer sb = new StringBuffer();
		      
		sb.append(dateString.substring(0, 4));
		sb.append(separation);
		sb.append(dateString.substring(4, 6));
		sb.append(separation);
		sb.append(dateString.substring(6, 8));
		return sb.toString();
	}
  
	public static String formatAccount(String account)
	{
		if (account == null || account == "" )
		{
			return account;
		}
		      
		if(account.length() < 5)
		{
			return account;
		}
		      
		account = account.substring(0,account.length()- 5) + "****" + account.substring(account.length()-1,account.length());
		return account;
	}
  
	/**
	   * 格式化银行卡号
	   * @param bankCardNo
	   * @return
	*/
	public static String formatBankAccount(String bankCardNo){
		  
		bankCardNo = StringUtil.getNotNullStr(bankCardNo).trim();
		 
		if(bankCardNo.length() < 15)
			return bankCardNo;
		  
		return bankCardNo.substring(0, 6) + "*****" + bankCardNo.substring(12);
	}
  
  
	public static String formatName(String sName)
	{
	
		if (sName == null || sName == "" || sName.length() == 1)
		{
			return sName;
		}
	      
		sName = "*" + sName.substring(1);
		      
		return sName;
	}
	

  
	public static String formatMoney(String money){
		try {
			return new DecimalFormat("0.00").format(Double.valueOf(money));
		} catch (Exception e) {
			return "0.00";
		}
	}
	
	/** 
	 * 替换换行制表符等
	 * 注：
	 * \n 回车(\u000a)  
     * \t 水平制表符(\u0009)  
     * \s 空格(\u0008)  
     * \r 换行(\u000d)
	 * @param str 原数据
	 * @return
	 */
	 public static String replaceBlank(String str) {        
		 String dest = "";         
		 if (str!=null) {             
			 Pattern p = Pattern.compile("\\s*|\t|\r|\n");             
			 Matcher m = p.matcher(str);             
			 dest = m.replaceAll("");         
		 }         
		 return dest;     
	} 
	 
	 
	 public static String stringFilter(String str) {
			// 只允许字母和数字
			// String regEx = "[^a-zA-Z0-9]";
			// 清除掉所有特殊字符
			String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\s*|\t|\r|\n]";
			try {
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(str);
				return m.replaceAll("|").trim();
			} catch (Exception e) {
			}
			return "";
		}
 
	 /**
	 * 固定长度16位唯一流水号
	 */
	private static Integer idIndex = 1;

	public static synchronized long nextId() {
		if (idIndex > 999)
			idIndex = 1;
		return Long.parseLong(System.currentTimeMillis()
				+ fillString(String.valueOf(idIndex++), 3, '0', true));
	}

	/**
	 * 格式化金额 保留两位小数
	 * 
	 * @param amt
	 * @return
	 */
	public static String formatAmt(String amt) {
		String yuan = null;
		final int MULTIPLIER = 1;
		yuan = new BigDecimal(amt).divide(new BigDecimal(MULTIPLIER))
				.setScale(2).toString();
		return yuan;
	} 
	
	/**
	 * 将16进制的字符串转换成ascii对应的字符串值
	 * @param code
	 * @return
	 */
	public static String convertHexStrToString(String code){
		 byte[] bytes =   convertHexStrToByteArray(code);
		 String num = "";
		 for (int i = 0; i < bytes.length; i++) {   
			 num += (char)bytes[i];
        }
		 return num;
	}
	
	/* 
	* 16进制数字字符集 
	*/ 
	private static String hexString="0123456789ABCDEF"; 
	/* 
	* 将字符串编码成16进制数字,适用于所有字符（包括中文） 
	*/ 
	public static String encode(String str) 
	{ 
		//根据默认编码获取字节数组 
		byte[] bytes=str.getBytes(); 
		StringBuilder sb=new StringBuilder(bytes.length*2); 
		//将字节数组中每个字节拆解成2位16进制整数 
		for(int i=0;i<bytes.length;i++) 
		{ 
			sb.append(hexString.charAt((bytes[i]&0xf0)>>4)); 
			sb.append(hexString.charAt((bytes[i]&0x0f)>>0)); 
		} 
		return sb.toString(); 
	} 
	/* 
	* 将16进制数字解码成字符串,适用于所有字符（包括中文） 
	*/ 
	public static String decode(String bytes) 
	{ 
		ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2); 
		//将每2位16进制整数组装成一个字节 
		for(int i=0;i<bytes.length();i+=2) 
		baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1)))); 
		return new String(baos.toByteArray()); 
	} 
	
	
	
	/**
	 *得到文件中的六位交易流水号+1
	 *获得流水号，每调用一次，流水号自动加1，如果大于999999，则重新置0
	 *@param fileName 保存流水号的文件名
	 *@return 新的交易流水号
	 */
	public synchronized String getTraceNo() {

		String fileName = this.getClass().getClassLoader().getResource("/").getPath().toString().replaceAll("%20"," ") + "config/TraceNo.txt";
		FileInputStream in = null;
		int TraceNo = 0;
		String CurDate = getYYYYMMDD();
		try {
			in = new FileInputStream(fileName);
			byte b[] = new byte[16];
			in.read(b);
			String s1 = new String(b, 9, 7);
			TraceNo = Integer.parseInt(s1);
			if (TraceNo > 9999999)
				TraceNo = 0;
			else
				TraceNo += 1;
		} catch (Exception e) {
			TraceNo = 0;
			e.printStackTrace();
		}

		try {
			if (in != null)
				in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileOutputStream out = null;
		String traceNo = null;
		try {
			out = new FileOutputStream(fileName);
			traceNo = CurDate +"n"+ convertIntToStr(TraceNo, 7);
			out.write(traceNo.getBytes());
			out.close();
		} catch (Exception e) {
		}
		try {
			if (out != null)
				out.close();
		} catch (Exception e) {
		}

		return traceNo;
	}
	
	/**
	 * 生成20位的HIS交易流水
	 * @param termId 终端编号  
	 * 					截取终端编号的后六位，不足六位补0，加上日期yyyyMMddHHmmss
	 * 					guahao110
	 * @return 返回数据格式 hao11020130510214210
	 */
	public static String generateHISSerim(String termId){
		
		StringBuffer serimBuffer = new StringBuffer();
		
		if(getNotNullStr(termId).length() < 6)
			
			serimBuffer.append(getFixedLenStr(termId, 6, '0', true));
		else if(getNotNullStr(termId).length() > 6)
			
			serimBuffer.append(termId.substring(termId.length() - 6, termId.length()));
		else
			
			serimBuffer.append(termId);
		
		serimBuffer.append(DateUtil.getNowLongTime());
		
		return serimBuffer.toString();
	} 
	
	/**
	 * 按照指定的格式化金额
	 * @param str 要格式化的金额字符串
	 * @return
	 */
	public static String getDecimalFormat(String str) {
		DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
		String outStr = null;
		double d;
		try {
			d = Double.parseDouble(str);
			outStr = fmt.format(d);
		} catch (Exception e) {
			e.printStackTrace();
			return "0.00";
		}
		return outStr;
	}
	//银行卡号打印卡号处理	
	public static String coverCardNoWithStar(String cardNo) {
		String starCardNo = "";
		cardNo = getNotNullStr(cardNo);
		int length = cardNo.trim().length();
		switch(length){
			case 0: starCardNo="";break;
			case 1: starCardNo="*";break;
			case 2: starCardNo=CardNoDeal(cardNo,1,1);break;
			case 3: starCardNo=CardNoDeal(cardNo,1,2);break;
			case 4: starCardNo=CardNoDeal(cardNo,1,3);break;
			case 5: starCardNo=CardNoDeal(cardNo,1,4);break;
			case 6: starCardNo=CardNoDeal(cardNo,1,4);break;
			case 7: starCardNo=CardNoDeal(cardNo,1,4);break;
			case 8: starCardNo=CardNoDeal(cardNo,1,4);break;
			default:starCardNo=CardNoDeal(cardNo,cardNo.length()-7,cardNo.length()-4);
		}
		return starCardNo;
	}
	
	public static String CardNoDeal(String cardNo, int start, int end) {
		if (start > cardNo.length() || end > cardNo.length() || start > end) {
			return cardNo;
		}
		char[] cardNos = cardNo.toCharArray();
		for (int i = start - 1; i < end; i++) {
			cardNos[i] = '*';
		}
		return new String(cardNos);
	}
	
	public static void main(String[] args){
		System.out.println(new StringUtil().getTraceNo());
	}
}
