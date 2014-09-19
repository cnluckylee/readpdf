package com.asex.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ASEXUtil {
	/**
	 * 把14-Jun-13 转换为2013/06/14
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(String date) {
		String reuslt = "";
		String day = date.split("-")[0];
		String month = date.split("-")[1].toUpperCase();
		String year = date.split("-")[2];
		if (year.charAt(0) >= '5') // 如果年份开头大于5， 例如
									// 98，则肯定是1998,；如果年份开小于5，则肯定是2015之类的
			year = "19" + year;
		else
			year = "20" + year;
		if (month.startsWith("JAN"))
			month = "01";
		else if (month.startsWith("FEB"))
			month = "02";
		else if (month.startsWith("MAR"))
			month = "03";
		else if (month.startsWith("APR"))
			month = "04";
		else if (month.startsWith("MAY"))
			month = "05";
		else if (month.startsWith("JUN"))
			month = "06";
		else if (month.startsWith("JUL"))
			month = "07";
		else if (month.startsWith("AUG"))
			month = "08";
		else if (month.startsWith("SEP"))
			month = "09";
		else if (month.startsWith("OCT"))
			month = "10";
		else if (month.startsWith("NOV"))
			month = "11";
		else if (month.startsWith("DEC"))
			month = "12";
		reuslt = year + "/" + month + "/" + day;
		return reuslt;
	}

	/**
	 * 08/26/1988 $240,000 GB96512 IMPRV SINGLE PROP CASH TRANSAC
	 * 
	 * @param s
	 * @return
	 */
	public static String getSalesPrice(String s) {
		return s.split(" ")[1];
	}

	/**
	 * 08/26/1988 $240,000 GB96512 IMPRV SINGLE PROP CASH TRANSAC
	 * 
	 * @param s
	 * @return
	 */
	public static String getSalesDate(String s) {
		String temp = s.split(" ")[0];
		String[] dates = temp.split("/");
		return dates[2] + "/" + dates[1] + "/" + dates[0];
	}
	
	/**
	 * 公共提取方法
	 */
	public  static String extractPublic(String sourceText,String contains,int nexti,int spliti) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains(contains)) {
			try{
				result = lines[i + nexti].split(" ")[spliti];
			} catch (Exception e) {
				result = lines[i + 1].trim();
			}
				break;
			}
		}
		return result;
	}
	
	/**
	 * 正则提取方法
	 */
	public static String extractRegular(String sourceText,String contains,String pattern,int groupnum)
	{
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			// 对于HOUSE类型的，MLS就是在第一行，Area就是在第二行
			if (lines[i].contains(contains)) {
				
				// 创建 Pattern 对象
			      Pattern r = Pattern.compile(pattern);
			      // 现在创建 matcher 对象
			      Matcher m = r.matcher(lines[i]);
			      if (m.find( )) {
			          result = m.group(groupnum);
			          break;
			       }
				break;
			}
		}
		return result;
	}
	
	/**
	 * 根据两个连续的字符串确定值
	 */
	public static String extractByTwoString(String sourceText,String findString,String searchString1,String searchString2,int groupnum)
	{
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains(findString)) {
				// Actual Total $1,440,600 Municipal Total $1,440,600 School
				// Total $1,440,600
				String[] temp = lines[i].split(" ");
				for (int j = 0; j < temp.length; j++) {
					if (temp[j].equals(searchString1) && temp[j+1].equals(searchString2)) {
						result = temp[j + groupnum];
						break;
					}
				}
				if (!result.equals(""))
					break;
			}
		}
		return result;
	}
	
	/**
	 * 平方英尺转平方米
	 */
	public static String  inc2mi(String inc)
	{
		String str = "";
		float mi=0;
		if(!inc.isEmpty())
		{
			int s = Integer.parseInt(inc.replace(",", ""));
			if(s>0 && !"".equals(inc) && !"0".equals(inc)){
				mi = (float) (Integer.parseInt(inc.replace(",", ""))*0.09);
				if(mi>0)
					str = "\n("+mi+"平)";
			}
		}
		return str;
	}
	/**
	 * 通过行信息来获取数据
	 */
	public static String extractByLineStr(String sourceText,String findString,String endString,int groupnum)
	{
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains(findString)) {
				result = lines[i].trim();
				int s = result.indexOf(findString);
				int e = result.length();
				if(endString !="")
				 e = result.indexOf(endString);
				if(e>0){
					result = result.substring(s,e).trim();
					String[] temp = lines[i].split(" ");
					result = temp[groupnum];
					break;
				}	
			}
		}
		return result;
	}
}
