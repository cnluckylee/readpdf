package com.asex.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从一堆文字中提取出要素
 * 
 * @author xue777hua
 * 
 */
public class TextExtractor {

	private static final char[][] subStrings = null;

	/**
	 * 提取MLS# |第一行的开头几个
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractMLSNumber(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			String pattern = "MLS# (V|H|F)(\\d{6,})";
			// 创建 Pattern 对象
		      Pattern r = Pattern.compile(pattern);
		      // 现在创建 matcher 对象
		      Matcher m = r.matcher(lines[i]);
		      if (m.find( )) {
//		          System.out.println("Found value: " + m.group(0) );
//		          System.out.println("Found value: " + m.group(1) );
		          result = m.group(0);
		          result = result.replace("MLS#", "").trim();
		          break;
		       }
		     
//			if (lines[i].contains("MLS")) {
//				// MLS# V1012934 Residential Detached
//				result = lines[i].split(" ")[1];
//				break;
//			}
		}
		return result;
	}

	/**
	 * 提取状态 |第五行
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractStatus(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Active")) {
				// MLS# V1012934 Residential Detached
				result = "A";
				break;
			}
			if (lines[i].contains("Sold")) {
				// MLS# V1012934 Residential Detached
				result = "S";
				break;
			}
		}
		return result;
	}

	/**
	 * 提取Address |Property Addr
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractAddress(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Property Addr")) {
				// Property Addr 4143 W 10TH AV
				result = lines[i].replace("Property Addr", "").trim();
				break;
			}
		}
		return result;
	}

	/**
	 * 提取区域 |Vancouver West, Point Grey(MLS的下一行)
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractArea(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			String pattern = "(\\w+),(\\w+|\\s+)";
			// 创建 Pattern 对象
		      Pattern r = Pattern.compile(pattern);
		      // 现在创建 matcher 对象
		      Matcher m = r.matcher(lines[i]);
		      if (m.find( )) {
		          result = lines[i];
		          if(result.isEmpty() == false)
		          break;
		      }
		}
		return result;
	}
	
	
	/**
	 * 提取三级区域 | Complex/Subdiv:
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractComplex(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			// 对于HOUSE类型的，MLS就是在第一行，Area就是在第二行
			if (lines[i].contains("Subdiv")) {
					result = lines[i + 1].trim();
					if(result.contains("Previous Price"))
						result = "";
				if(!"".equals(result))
					break;
			}
			 if("".equals(result) && lines[i].contains("Complex/Subdiv")){
				result = lines[i].replace("Complex/Subdiv:", "").trim();
				break;
			}
			 
		}
		return result;
	}
	
	/**
	 * 
	 */
	
	/**
	 * 浴室 | Bathrooms:
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractBathrooms(String sourceText) {
		String result = ASEXUtil.extractPublic(sourceText, "Bedrooms:", 0,1);;
		return result;
	}
	
	/**
	 * 全浴室 | FullBaths:
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractFullBaths(String sourceText) {
		String result = ASEXUtil.extractRegular(sourceText, "Full Baths:", "Full Baths: (\\d+)",1);
		return result;
	}
	
	/**
	 * 半浴室 | HalfBaths:
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractHalfBaths(String sourceText) {

//		String result = ASEXUtil.extractRegular(sourceText, "Half Baths:", "Half Baths: (\\d+)",1);
		String[] lines = sourceText.split("\n");
		String result = "0";
		for (int i = 0; i < lines.length; i++) {
			// 对于HOUSE类型的，MLS就是在第一行，Area就是在第二行
			if (lines[i].contains("Half Baths:")) {
				String pattern = "Half Baths: (\\d+)";
				// 创建 Pattern 对象
			      Pattern r = Pattern.compile(pattern);
			      // 现在创建 matcher 对象
			      Matcher m = r.matcher(lines[i]);
			      if (m.find( )) {
			          result = m.group(1);
			          break;
			       }
				break;
			}
		}
		return result;
	

	}
	
	
	/**
	 * 停车位 | Total Parking:
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractTotalParking(String sourceText) {

		String result = ASEXUtil.extractRegular(sourceText, "Total Parking:", "Total Parking: (\\d+)",1);
		return result;
	

	}
	
	
	/**
	 * 提取上市价格 |List Price
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractListPrice(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("List Price")) {
				// List Price: $1,558,000 Days on Mkt: 12
				result = lines[i].split(" ")[2];
				break;
			}
		}
		return result;
	}

	/**
	 * 提取售出价格 |留空
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractSoldPrice(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Sold Price")||lines[i].contains("Sale Price")) {
				//List Price: $968,000 Sold Price       $900,000 Days on Mkt: 181
				result = lines[i].split(" ")[5];
				break;
			}
		}
		return result;
	}

	/**
	 * 提取政府估价 |Municipal Total
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractAssessPrice(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Municipal Total")) {
				result = lines[i].trim();
				int s = result.indexOf("Municipal Total");
				int e = result.length();
				if(e>0){
					result = result.substring(s,e).trim();
					String[] temp = lines[i].split(" ");
					result = temp[2];
					break;
				}	
			}
		}
		return result;
	}

	/**
	 * 提取总土地 |Gross Land
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractGrossLand(String sourceText) {
		String result = ASEXUtil.extractByTwoString(sourceText, "Gross Land", "Gross","Land",2);
		if(result.equals("Exempt"))
			result = "";
		if(result == "")
		{
			result = ASEXUtil.extractByLineStr(sourceText, "Gross Land","",1);
		}
		return result;
	}
	
	/**
	 * 毛利率改善 | Gross Improve
	 * @param sourceText
	 * @return
	 */
	public static String extractGrossImprove(String sourceText) {
		String result = ASEXUtil.extractByTwoString(sourceText, "Gross Improve", "Gross","Improve",2);
		if(result.equals("Exempt"))
			result = "";
		if(result == "")
		{
			result = ASEXUtil.extractByLineStr(sourceText, "Gross Improve","",1);
		}
		return result;
	}
	
	
	/**
	 * 免征土地 |Exempt Land
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractExemptLand(String sourceText) {
		String result = ASEXUtil.extractByTwoString(sourceText, "Exempt Land", "Exempt","Land",2);
		if(result.equals("Exempt"))
			result = "";
		return result;
	}
	
	/**
	 * 免除改善 | Exempt Improve
	 * @param sourceText
	 * @return
	 */
	public static String extractExemptImprove(String sourceText) {
		String result = ASEXUtil.extractByTwoString(sourceText, "Exempt Improve", "Exempt","Improve",2);
		if(result.equals("Exempt"))
			result = "";
		return result;
	}
	/**
	 * 特色设施 | Features Incl
	 * @param sourceText
	 * @return
	 */
	
	public static String extractFeaturesIncl(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Features Incl")) {
				result = lines[i].replace("Features Incl:", "");
				break;
			}
		}
		return result;
	}
	/**
	 * 公共设施 | Amenities
	 * @param sourceText
	 * @return
	 */
	
	public static String extractAmenities(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Amenities:")) {
				result = lines[i].replace("Amenities:", "");
				break;
			}
		}
		return result;
	}
	/**
	 * 永久产权 | extractTitletoLand
	 * @param sourceText
	 * @return
	 */
	public static String extractTitletoLand(String sourceText){
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Title to Land:")) {
				result = lines[i].trim();
				int s = result.indexOf("Land:")+5;
				int e = result.length();
				if(e>0)
					result = result.substring(s,e).trim();
				break;
			}
		}
		return result;
	}
	/**
	 * 提取上市日期 | List Date
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractSaleDate(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("List Date")) {
				// List Date: 14-Jun-13 Expiry Date: 16-Aug-13
				try{
					result = ASEXUtil.getDateString(lines[i].split(" ")[2]);
				}catch(Exception e)
				{
				}
				if(result!="")
					break;
			}
		}
		return result;
	}

	/**
	 * 提取售出日期 | 留空
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractSoldDate(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Sold Date")||lines[i].contains("Sale Date")) {
				//List Date: 10-Jan-13 Sold Date:       10-Jul-13 Expiry Date: 31-Jul-13
				try{
					result = ASEXUtil.getDateString(lines[i].split(" ")[5]);
				}catch(Exception e)
				{
					
				}
				
				break;
			}
		}
		return result;
	}

	/**
	 * 提取在市时间 | 留空
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractOnSaleTime(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Days on Mkt:")) {
				//List Price: $968,000 Sold Price       $900,000 Days on Mkt: 181
				// List Price: $750,000 Days on Mkt: 318
				try {
					if(sourceText.contains("Active"))
						result = lines[i].split(" ")[6].trim();
					else 
						result = lines[i].split(" ")[9].trim();
					break;
				} catch (Exception e) {
					result = ""; //有时候Days on Mkt: 后面的数字不存在，则直接忽略
				}
			}
		}
		return result;
	}

	/**
	 * 提取SqFt|Grand Total Floor Area
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractSqFt(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Grand Total Floor Area")) {
				// Grand Total Floor Area: 1,957 8 
				try {
					result = lines[i].split(" ")[4];
				} catch (ArrayIndexOutOfBoundsException e) {
					// 如果出现数组越界异常了，说明这个数值不在这一行，而是在当前的下一行
					result = lines[i + 1].trim();
				}
				break;
			}
		}
		return result;
	}

	/**
	 * 提取年份 |Approx Yr Blt
	 * $1,558,000 Frontage Metric: 15.240 Approx Yr Blt: 1927
	 * @param sourceText
	 * @return
	 */
	public static String extractBuiltYear(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Approx Yr Blt")) {
				try {
					String[] subStrings = lines[i].split(" ");
					for (int j = 0; j < subStrings.length; j++) {
						if (subStrings[j].equals("Blt:")){
							result = subStrings[j + 1];
							break;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					// 如果出现了数组越界，那就说明在下一行
					result = lines[i + 1].trim();
				}
				break;
			}
		}
		if (result!="" && Integer.parseInt(result) > 2050)
			result = "OT";
		return result;
	}

	/**
	 * 提取屋龄 |Age at List Date
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractHouseAge(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Age at List Date")) {
				// 86
				// Age at List Date:
				result = lines[i - 1].trim();
				break;
			}
		}
		if (result!="" && Integer.parseInt(result) > 150)
			result = "OT";
		return result;
	}

	/**
	 * 提取面宽 |Frontage
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractFlatWidth(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		try { // if result is with a wrong format or null, then reset it to empty string
			for (int i = 0; i < lines.length; i++) {
				if (lines[i].contains("Frontage:")) {
					// Previous Price: Frontage: 50.00 ft
					result = lines[i].split(" ")[3];
					break;
				}
			}
			Double.parseDouble(result);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	/**
	 * 提取面深 |Depth/Size
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractFlatDepth(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Depth/Size")) {
				// Depth/Size:
				// 115 Bathrooms: 3
				result = lines[i + 1].split(" ")[0];
				break;
			}
		}
		try { // if result is with a wrong format, then reset it to empty string
			Double.parseDouble(result);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	/**
	 * 提取厨房 |# Kitchens
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractKitchenNum(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Kitchens")) {
				// Finished Floor Up: 523 # Kitchens: 2 2  4 Piece; Ensuite: N;
				// Level: Main F.
				String[] temp = lines[i].split(" ");
				for (int j = 0; j < temp.length; j++) {
					if (temp[j].contains("Kitchens")) {
						result = temp[j + 1];
					}
				}
				if (!result.equals(""))
					break;
			}
		}
		return result;
	}

	/**
	 * 提取睡房 | Bedrooms
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractBedrooms(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Bedrooms:")) {
				// Bedrooms: 4
				result = lines[i].split(" ")[1];
				break;
			}
		}
		return result;
	}

	/**
	 * 提取土地面积 |Lot Area SqFt
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractGroundSize(String sourceText) {
		// Lot Area SqFt:
		// 5,750 Full Baths: 2
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Lot Area SqFt")) {
				result = lines[i + 1].split(" ")[0];
				break;
			}
		}
		return result;
	}

	/**
	 * 提取上次交易价格 |Sale History Information 下面的非REJECT的第一行，如果没有，则置空
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractLastSalePrice(String sourceText) {
		// Date Price Document # Type of Sales Transaction
		// 06/25/2008 $503,500 BB872870 REJECT NOT SUITED SALE ANALSIS
		// 03/07/1997 $500,000 BL82124 REJECT NOT SUITED SALE ANALSIS
		// 08/26/1988 $240,000 GB96512 IMPRV SINGLE PROP CASH TRANSAC
		String[] lines = sourceText.split("\n");
		String result = "";
		boolean flag = false;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Sales Transaction")) {
				flag = true;
			}
			// 进入了 Type of Sales Transaction 区域
			if (flag && lines[i].contains("$") && !lines[i].contains("REJECT")) {
				result = ASEXUtil.getSalesPrice(lines[i]);
			}
			if (!result.equals(""))
				break;
		}
		return result;
	}

	/**
	 * 提取上次交易时间 | Sale History Information 下面的非REJECT的一行，如果没有，则置空
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractLastSaleDate(String sourceText) {
		// Date Price Document # Type of Sales Transaction
		// 06/25/2008 $503,500 BB872870 REJECT NOT SUITED SALE ANALSIS
		// 03/07/1997 $500,000 BL82124 REJECT NOT SUITED SALE ANALSIS
		// 08/26/1988 $240,000 GB96512 IMPRV SINGLE PROP CASH TRANSAC
		String[] lines = sourceText.split("\n");
		String result = "";
		boolean flag = false;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Sales Transaction")) {
				flag = true;
			}
			// 进入了 Type of Sales Transaction 区域
			if (flag && lines[i].contains("$") && !lines[i].contains("REJECT")) {
				result = ASEXUtil.getSalesDate(lines[i]);
			}
			if (!result.equals(""))
				break;
		}
		return result;
	}
	
	/**
	 * 提取房屋类型：目前就House和Apartment两种 
	 * @param sourceText
	 * @return
	 */
	public static String extractHouseType(String sourceText) {
		/* Type: House/Single Family */
		String result = "";
		if (sourceText.contains("Apartment/"))
			result = "APARTMENT";
		if (sourceText.contains("House/"))
			result = "HOUSE";
		return result;
	}
	
	/**
	 * 提取Apartment的房屋管理费
	 * Mgmt. Co's Phone: 604-931-8666 Maint Fee: $314.00
	 * @param sourceText
	 * @return
	 */
	public static String extractMaintFee(String sourceText){
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Maint Fee")) {
				String[] subStrings = lines[i].split(" ");
				for (int j = 0; j < subStrings.length; j++) {
					if (subStrings[j].equals("Fee:")) {
						result = subStrings[j + 1];
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
	 * 提取HouseDesc |Property HouseDesc
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractHouseDesc(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Realtor Remarks:")) {
				// Property Addr 4143 W 10TH AV
				for(int j=i+2;j<lines.length;j++)
				{
					if(lines[j].contains("REA Full Realtor") || lines[j].contains("RED Full Realtor"))
						break;
					result += lines[j].trim();
				}
			}
		}
		return result;
	}
	
	/**
	 * 提取管理公司 |Property ManageCompany
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractManageCompany(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Mgmt. Co's Name:")) {
				String[] subStrings = lines[i].split("Bylaw");
				result = subStrings[0].replace("Mgmt. Co's Name:","").trim();
			}
		}
		return result;
	}
	
	/**
	 * 提取地税 |Property ManageCompany
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractTaxes(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Taxes:")) {
				result = lines[i + 2].trim();
				if("".equals(result) || !result.contains("$"))
					result = lines[i].replace("Taxes:","").trim();
				if(result.contains("Baths"))
					result = "";
				break;
			}
		}
		return result;
	}
	/**
	 * 提取地税 |Property ManageCompany
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractZoning(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Zoning:")) {
				try{
					result = lines[i + 1].split(" ")[4];
				}catch(Exception e)
				{
					result = lines[i].replace("Zoning:","").trim();
				}
				break;
			}
		}
		return result;
	}
	
	

	
	
	/**
	 * 提取AreaUp |Property AreaUp
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractAreaUp(String sourceText) {
		String result =  ASEXUtil.extractPublic(sourceText, "Finished Levels", 1,1);
		try{
			int i = Integer.parseInt(result);
		}catch(NumberFormatException e)
		{
			String[] lines = sourceText.split("\n");
			for (int i = 0; i < lines.length; i++) {
				if (lines[i].contains("Finished Floor Up:")) {
					try{
						result = lines[i].replace("Finished Floor Up: ", "").split(" ")[0];
						 int ii = Integer.parseInt(result);
					}catch(NumberFormatException e2)
					{
						if(!result.contains(","))
						result = "";
					}
					break;
				}
			}
		}
		return result;

//		return result;
	}
	
	/**
	 * 提取Area Down |Property  Area Down
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractAreaDown(String sourceText) {
		String result = ASEXUtil.extractPublic(sourceText, "Area Down:", 1,1);
		try{
			int i = Integer.parseInt(result);
		}catch(NumberFormatException e)
		{
			String[] lines = sourceText.split("\n");
			for (int i = 0; i < lines.length; i++) {
				if (lines[i].contains("Finished Floor Down:")) {
					try{
						result = lines[i].replace("Finished Floor Down: ", "").split(" ")[0];
						 int ii = Integer.parseInt(result);
					}catch(NumberFormatException e2)
					{
						if(!result.contains(","))
						result = "";
					}
					break;
				}
			}
		}
		return result;
	}

	
	/**
	 * 主层 |Property AreaMain
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractAreaMain(String sourceText) {
		String result = ASEXUtil.extractPublic(sourceText, "Total # Rooms:", 1,1);
		
			try{
				int i = Integer.parseInt(result);
			}catch(NumberFormatException e)
			{
				String[] lines = sourceText.split("\n");
				for (int i = 0; i < lines.length; i++) {
					if (lines[i].contains("Main Floor Area:")) {
						try{
							result = lines[i].replace("Main Floor Area: ", "").split(" ")[0];
							 int ii = Integer.parseInt(result);
						}catch(NumberFormatException e2)
						{
							if(!result.contains(","))
							result = "";
						}
						break;
					}
				}
			}
		
		return result;
	}
	
	/**
	 * 地下面积 |Property AreaBsmt
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractAreaBsmt(String sourceText) {
		String result = ASEXUtil.extractPublic(sourceText, "Area Down:", 1,1);
		if("".equals(result))
		{
			try{
				int i = Integer.parseInt(result);
			}catch(NumberFormatException e)
			{
				String[] lines = sourceText.split("\n");
				for (int i = 0; i < lines.length; i++) {
					if (lines[i].contains("Finished Floor Bsmt:")) {
						try{
							result = lines[i].replace("Finished Floor Bsmt: ", "").split(" ")[0];
							 int ii = Integer.parseInt(result);
						}catch(NumberFormatException e2)
						{
							if(!result.contains(","))
							result = "";
						}
						break;
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 未装修面积 |Property UnfinishedArea
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractUnfinishedArea(String sourceText) {
		String result = ASEXUtil.extractPublic(sourceText, "Unfinished Floor Area:", -1,0);
		return result;
	}
	
	
	/**
	 * 总房间 |Property TotalRooms
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractTotalRooms(String sourceText) {
		String result = ASEXUtil.extractRegular(sourceText, "Total # Rooms","Total # Rooms: (\\d+)",1);
		try{
			int i = Integer.parseInt(result);
		}catch(NumberFormatException e)
		{
			result = "";
		}
		return result;
	}
	
	/**
	 * 储藏室 |Property Locker
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractLocker(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Locker:")) {
			try{
				String[] tmp =  lines[i].split(" ");
				int co = tmp.length;
				result = tmp[co-1].trim();
				if(result == "Locker:")
					result = "";
			} catch (Exception e) {
				result = lines[i + 1].trim();
			}
				break;
			}
		}
		return result;
	}
	
	
	/**
	 * 屋顶 |Property Roof
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractRoof(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Type of Roof:")) {
				result = lines[i].trim();
				result = result.replace("Type of Roof:", "");
//				result = result.replace(" - ", "-");
				int e = result.indexOf("Possession:");
				if(e>0)
					result = result.substring(0,e).trim();
				break;
			}
		}
		return result;
	}
	
	/**
	 * 结构 |Property Construction
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractConstruction(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Construction:")) {
				result = lines[i].trim();
				result = result.replace("Construction:", "");
				int e = result.indexOf("Parking");
				if(e>0)
					result = result.substring(0,e).trim();
				break;
			}
		}
		return result;
		
	}
	
	/**
	 * 储藏室 |Property Foundation
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractFoundation(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Foundation:")) {
				result = lines[i].replace("Foundation:","").trim();
				int e = result.indexOf("Parking");
				if(e>0)
					result = result.substring(0,e).trim();
				break;
			}
		}
		return result;
	}
	
	public static String extractLivingRoom(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		String[] rooms ;
		for (int i = 0; i < lines.length; i++) {
			// 
			if ((lines[i].contains("Main F. ") || lines[i].contains("Below ") || lines[i].contains("Bsmt ")) && !lines[i].contains("Bsmt Height")) {
				List config = GetConfig.getConfig("308");
				
				List l1 = (List) config.get(0);
				List l2 = (List) config.get(1);
				int l1c = l1.size();
				int flag = 0;
				String tmp = lines[i].replace("Main F. ","主层 ").trim();
				for(int ii=0;ii<l1c;ii++)
				{
					String k1 = l2.get(ii).toString();
					String k2 = k1.replace(" ", "");
					tmp = tmp.replace(k1,k2).trim();
					if(tmp.contains(k2))
						flag = 1;
				}
				tmp = tmp.replace("Above","楼上").trim();
				tmp = tmp.replace("Bsmt","楼底").trim();
				tmp = tmp.replace("Below","地下室").trim();
				tmp = tmp.replace(" X X ","X").trim();
				tmp = tmp.replace(" X X","").trim();
				tmp = tmp.replace("XX","").trim();
				tmp = tmp.replace(" X ","X").trim();
				tmp = tmp.replace(" X"," ").trim();
				if(flag>0)
					result += "#"+tmp;
			}
		}
		return result;
	}
	
	/**
	 * 提取pid |Property pid
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String extractPid(String sourceText) {
		String[] lines = sourceText.split("\n");
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("PID:")) {
			try{
				result = lines[i + 1].split(" ")[1];
			} catch (Exception e) {
				result = lines[i + 1].trim();
			}
				
				break;
			}
		}
		return result;
	}
	
	
}
