package com.asex.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.asex.util.ASEXUtil;
import com.asex.util.GetConfig;
import com.asex.util.TextExtractor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

/**
 * @author xue777hua
 * 
 */
public class RecordModel {
	/**
	 * 本Model提取源记录，通常是奇数页+偶数页的内容综合
	 */
	private String extractSourceText;

	/**
	 * MLS# |第一行的开头几个
	 */
	private String mlsNumber;
	/**
	 * 状态 |第五行
	 */
	private String status;
	/**
	 * Address |Property Addr
	 */
	private String address;
	/**
	 * 区域 |Complex/Subdiv
	 */
	private String area;
	/**
	 * 上市价格 |List Price
	 */
	private String listPrice;
	/**
	 * 售出价格 |留空
	 */
	private String soldPrice;
	/**
	 * 政府估价 |Municipal Total
	 */
	private String assessPrice;
	/**
	 * 上市日期 | List Date
	 */
	private String saleDate;
	/**
	 * 售出日期 | 留空
	 */
	private String soldDate;
	/**
	 * 在市时间 | 留空
	 */
	private String onSaleTime;
	/**
	 * SqFt|Grand Total Floor Area
	 */
	private String SqFt;
	/**
	 * 年份 |Approx Yr Blt 9
	 */
	private String builtYear;
	/**
	 * 屋龄 |Age at List Date
	 */
	private String houseAge;
	/**
	 * 面宽 |Frontage
	 */
	private String flatWidth;
	/**
	 * 面深 |Depth/Size
	 */
	private String flatDepth;
	/**
	 * 厨房 |# Kitchens
	 */
	private String kitchenNum;
	/**
	 * 睡房 | Bedrooms
	 */
	private String bedrooms;
	/**
	 * 土地面积 |Lot Area SqFt
	 */
	private String groundSize;
	/**
	 * 上次交易价格 |Sale History Information 下面的非REJECT的一行，如果没有，则置空
	 */
	private String lastSalePrice;
	/**
	 * 上次交易时间 | Sale History Information 下面的非REJECT的一行，如果没有，则置空
	 */
	private String lastSaleDate;
	
	/**
	 * 房屋类型，House和Apartment两种，这个只用来判断而不提取此数据
	 * 判断的格式为House/或者Apartment/，只要包含这个字样的记录那就是House或者Apartment类型
	 * 当房屋是Apartment的时候，将面宽、面深、土地面积都置空（不提取）
	 * 当房屋是House的时候，将Maint Fee置空（不提取）
	 */
	private String houseType;
	/**
	 * Aprtment的公寓管理费
	 */
	private String maintFee;
	
	/**
	 * 房屋简介
	 */
	private String houseDesc;
	
	/**
	 * 管理公司 | Mgmt. Co's Name
	 */
	private String manageCompany;
	
	/**
	 * 
	 * 地税 | Taxes
	 */
	private String taxes;
	
	/**
	 * 
	 * zoning | Zoning
	 */
	private String zoning;
	
	/**
	 * pid | PID
	 */
	private String pid;
	
	/**
	 *  主楼 |  Area Main
	 */
	private String areaMain;
	
	/**
	 *  楼下 |  Area Down
	 */
	private String areaDown;
	
	/**
	 *  楼上 |  Area Up
	 */
	private String areaUp;
	
	/**
	 *  底下 |  Area Bsmt
	 */
	private String areaBsmt;
	
	/**
	 * 未装修面积 | Unfinished Floor Area
	 */
	private String unfinishedArea;
	
	/**
	 *总房间 | Total # Rooms
	 */
	private String totalRooms;
	
	/**
	 * 储藏室|Locker
	 */
	private String locker;
	
	/**
	 * 屋顶|Type of Roof
	 */
	private String roof;
	
	/**
	 * 结构|Construction
	 */
	private String construction;
	
	/**
	 * 地基|Foundation
	 */
	private String foundation;
	
	/**
	 * Living Room
	 */
	private String livingRoom;
	
	/**
	 * Complex
	 */
	private String complex;
	
	/**
	 * 浴室
	 * Bathrooms
	 */
	private String bathrooms;
	
	/**
	 * 全套卫浴
	 * FullBaths
	 */
	private String fullBaths;
	
	/**
	 * 半浴室
	 * Half Baths
	 */
	private String halfBaths;
	
	/**
	 * 停车位
	 */
	private String totalParking;
	
	/**
	 * 总土地 GrossLand
	 */
	private String grossLand;
	
	/**
	 * 毛利率改善
	 * Gross Improve
	 */
	
	private String grossImprove;
	
	/**
	 * 免征土地
	 * Exempt Land
	 */
	private String exemptLand;
	
	/**
	 * 免除改善
	 * Exempt Improve
	 */
	private String exemptImprove;
	
	/**
	 * 特色设施
	 * Features Incl
	 */
	private String featuresIncl;
	
	/**
	 * 永久产权
	 * Title to Land
	 */
	private String titletoLand;
	
	/**
	 * 公共设施
	 * Amenities
	 */
	private String amenities;
	
	public String getTitletoLand() {
		return titletoLand;
	}

	public void setTitletoLand(String titletoLand) {
		this.titletoLand = titletoLand;
	}



	
	
	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getFeaturesIncl() {
		return featuresIncl;
	}

	public void setFeaturesIncl(String featuresIncl) {
		this.featuresIncl = featuresIncl;
	}

	public String getGrossLand() {
		return grossLand;
	}

	public void setGrossLand(String grossLand) {
		this.grossLand = grossLand;
	}

	public String getGrossImprove() {
		return grossImprove;
	}

	public void setGrossImprove(String grossImprove) {
		this.grossImprove = grossImprove;
	}

	public String getExemptLand() {
		return exemptLand;
	}

	public void setExemptLand(String exemptLand) {
		this.exemptLand = exemptLand;
	}

	public String getExemptImprove() {
		return exemptImprove;
	}

	public void setExemptImprove(String exemptImprove) {
		this.exemptImprove = exemptImprove;
	}

	public String getTotalParking() {
		return totalParking;
	}

	public void setTotalParking(String totalParking) {
		this.totalParking = totalParking;
	}

	public String getFullBaths() {
		return fullBaths;
	}

	public void setFullBaths(String fullBaths) {
		this.fullBaths = fullBaths;
	}

	public String getHalfBaths() {
		return halfBaths;
	}

	public void setHalfBaths(String halfBaths) {
		this.halfBaths = halfBaths;
	}


	
	
	public String getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(String bathrooms) {
		this.bathrooms = bathrooms;
	}

	public String getComplex() {
		return complex;
	}

	public void setComplex(String complex) {
		this.complex = complex;
	}

	public String getLivingRoom() {
		return livingRoom;
	}

	public void setLivingRoom(String livingRoom) {
		this.livingRoom = livingRoom;
	}

	public String getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(String totalRooms) {
		this.totalRooms = totalRooms;
	}

	public String getLocker() {
		return locker;
	}

	public void setLocker(String locker) {
		this.locker = locker;
	}

	public String getRoof() {
		return roof;
	}

	public void setRoof(String roof) {
		this.roof = roof;
	}

	public String getConstruction() {
		return construction;
	}

	public void setConstruction(String construction) {
		this.construction = construction;
	}

	public String getFoundation() {
		return foundation;
	}

	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}

	
	
	public String getUnfinishedArea() {
		return unfinishedArea;
	}

	public void setUnfinishedArea(String unfinishedArea) {
		this.unfinishedArea = unfinishedArea;
	}

	public String getAreaMain() {
		return areaMain;
	}

	public void setAreaMain(String areaMain) {
		this.areaMain = areaMain;
	}

	public String getAreaDown() {
		return areaDown;
	}

	public void setAreaDown(String areaDown) {
		this.areaDown = areaDown;
	}

	public String getAreaUp() {
		return areaUp;
	}

	public void setAreaUp(String areaUp) {
		this.areaUp = areaUp;
	}

	public String getAreaBsmt() {
		return areaBsmt;
	}

	public void setAreaBsmt(String areaBsmt) {
		this.areaBsmt = areaBsmt;
	}
	
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getZoning() {
		return zoning;
	}

	public void setZoning(String zoning) {
		this.zoning = zoning;
	}

	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public String getManageCompany() {
		return manageCompany;
	}

	public void setManageCompany(String manageCompany) {
		this.manageCompany = manageCompany;
	}

	public String getHouseDesc() {
		return houseDesc;
	}

	public void setHouseDesc(String houseDesc) {
		this.houseDesc = houseDesc;
	}

	public String getMlsNumber() {
		return mlsNumber;
	}

	public void setMlsNumber(String mlsNumber) {
		this.mlsNumber = mlsNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getListPrice() {
		return listPrice;
	}

	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}

	public String getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(String soldPrice) {
		this.soldPrice = soldPrice;
	}

	public String getAssessPrice() {
		return assessPrice;
	}

	public void setAssessPrice(String assessPrice) {
		this.assessPrice = assessPrice;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(String soldDate) {
		this.soldDate = soldDate;
	}

	public String getOnSaleTime() {
		return onSaleTime;
	}

	public void setOnSaleTime(String onSaleTime) {
		this.onSaleTime = onSaleTime;
	}

	public String getSqFt() {
		return SqFt;
	}

	public void setSqFt(String sqFt) {
		SqFt = sqFt;
	}

	public String getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(String builtYear) {
		this.builtYear = builtYear;
	}

	public String getHouseAge() {
		return houseAge;
	}

	public void setHouseAge(String houseAge) {
		this.houseAge = houseAge;
	}

	public String getFlatWidth() {
		return flatWidth;
	}

	public void setFlatWidth(String flatWidth) {
		this.flatWidth = flatWidth;
	}

	public String getFlatDepth() {
		return flatDepth;
	}

	public void setFlatDepth(String flatDepth) {
		this.flatDepth = flatDepth;
	}

	public String getKitchenNum() {
		return kitchenNum;
	}

	public void setKitchenNum(String kitchenNum) {
		this.kitchenNum = kitchenNum;
	}

	public String getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}

	public String getGroundSize() {
		return groundSize;
	}

	public void setGroundSize(String groundSize) {
		this.groundSize = groundSize;
	}

	public String getLastSalePrice() {
		return lastSalePrice;
	}

	public void setLastSalePrice(String lastSalePrice) {
		this.lastSalePrice = lastSalePrice;
	}

	public String getLastSaleDate() {
		return lastSaleDate;
	}

	public void setLastSaleDate(String lastSaleDate) {
		this.lastSaleDate = lastSaleDate;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getMaintFee() {
		return maintFee;
	}

	public void setMaintFee(String maintFee) {
		this.maintFee = maintFee;
	}

	public String toLogString() {
		StringBuilder sb = new StringBuilder("本条记录为：");
		sb.append("公共设施: ").append(this.getAmenities()).append("\t");
		sb.append("特色设施: ").append(this.getFeaturesIncl()).append("\t");
		sb.append("MSL#: ").append(this.getMlsNumber()).append("\t");
		sb.append("状态: ").append(this.getStatus()).append("\t");
		sb.append("Address: ").append(this.getAddress()).append("\t");
		sb.append("区域: ").append(this.getArea()).append("\t");
		sb.append("第三级区域: ").append(this.getComplex()).append("\t");
		sb.append("上市价格: ").append(this.getListPrice()).append("\t");
		sb.append("售出价格: ").append(this.getSoldPrice()).append("\t");
		sb.append("政府估价: ").append(this.getAssessPrice()).append("\t");
		sb.append("上市日期: ").append(this.getSaleDate()).append("\t");
		sb.append("售出日期: ").append(this.getSoldDate()).append("\t");
		sb.append("在市时间: ").append(this.getOnSaleTime()).append("\t");
		sb.append("SqFt: ").append(this.getSqFt()).append("\t");
		sb.append("年份: ").append(this.getBuiltYear()).append("\t");
		sb.append("屋龄: ").append(this.getHouseAge()).append("\t");
		sb.append("面宽: ").append(this.getFlatWidth()).append("\t");
		sb.append("面深: ").append(this.getFlatDepth()).append("\t");
		sb.append("厨房: ").append(this.getKitchenNum()).append("\t");
		sb.append("睡房: ").append(this.getBedrooms()).append("\t");
		
		sb.append("浴室: ").append(this.getBathrooms()).append("\t");
		sb.append("全浴室: ").append(this.getFullBaths()).append("\t");
		sb.append("半浴室: ").append(this.getHalfBaths()).append("\t");
		
		sb.append("土地面积: ").append(this.getGroundSize()).append("\t");
		sb.append("上次交易价格: ").append(this.getLastSalePrice()).append("\t");
		sb.append("上次交易时间: ").append(this.getLastSaleDate()).append("\t");
		sb.append("Apartment管理费: ").append(this.getMaintFee()).append("\t");
		sb.append("房屋简介: ").append(this.getHouseDesc()).append("\t");
		sb.append("管理公司: ").append(this.getManageCompany()).append("\t");
		sb.append("地税: ").append(this.getTaxes()).append("\t");
		sb.append("zoning: ").append(this.getZoning()).append("\t");
		sb.append("pid: ").append(this.getPid()).append("\t");
		sb.append("楼上: ").append(this.getAreaUp()).append("\t");
		sb.append("楼下: ").append(this.getAreaDown()).append("\t");
		sb.append("主楼: ").append(this.getAreaMain()).append("\t");
		sb.append("地下: ").append(this.getAreaBsmt()).append("\t");
		sb.append("未装修面积: ").append(this.getUnfinishedArea()).append("\t");
		sb.append("总房间: ").append(this.getTotalRooms()).append("\t");
		sb.append("储藏室: ").append(this.getLocker()).append("\t");
		sb.append("屋顶: ").append(this.getRoof()).append("\t");
		sb.append("结构: ").append(this.getConstruction()).append("\t");
		sb.append("地基: ").append(this.getFoundation()).append("\t");
		sb.append("Living Room: ").append(this.getLivingRoom()).append("\t");
		sb.append("停车位: ").append(this.getTotalParking()).append("\t");
		
		
		sb.append("总土地: ").append(this.getGrossLand()).append("\t");
		sb.append("毛利率改善: ").append(this.getGrossImprove()).append("\t");
		sb.append("免征土地: ").append(this.getExemptLand()).append("\t");
		sb.append("免除改善: ").append(this.getExemptImprove()).append("\t");
		sb.append("永久产权: ").append(this.getTitletoLand()).append("\t");
		
		
		
		
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append(this.getMlsNumber()).append("\t");
		sb.append(this.getStatus()).append("\t");
		sb.append(this.getAddress()).append("\t");
		sb.append(this.getArea()).append("\t");
		sb.append(this.getComplex()).append("\t");
		

		
		
		sb.append(this.getListPrice()).append("\t");
		sb.append(this.getSoldPrice()).append("\t");
		sb.append(this.getAssessPrice()).append("\t");
		sb.append(this.getSaleDate()).append("\t");
		sb.append(this.getSoldDate()).append("\t");
		sb.append(this.getOnSaleTime()).append("\t");
		sb.append(this.getSqFt()).append("\t");
		sb.append(this.getBuiltYear()).append("\t");
		sb.append(this.getHouseAge()).append("\t");
		sb.append(this.getFlatWidth()).append("\t");
		sb.append(this.getFlatDepth()).append("\t");
		sb.append(this.getKitchenNum()).append("\t");
		sb.append(this.getBedrooms()).append("\t");
		
		sb.append(this.getBathrooms()).append("\t");
		sb.append(this.getFullBaths()).append("\t");
		sb.append(this.getHalfBaths()).append("\t");
		
		
		sb.append(this.getGroundSize()).append("\t");
		sb.append(this.getLastSalePrice()).append("\t");
		sb.append(this.getLastSaleDate()).append("\t");
		sb.append(this.getMaintFee()).append("\t");
		sb.append(this.getHouseDesc()).append("\t");
		sb.append(this.getManageCompany()).append("\t");
		sb.append(this.getTaxes()).append("\t");
		sb.append(this.getZoning()).append("\t");
		sb.append(this.getPid()).append("\t");
		sb.append(this.getAreaUp()).append("\t");
		sb.append(this.getAreaDown()).append("\t");
		sb.append(this.getAreaMain()).append("\t");
		sb.append(this.getAreaBsmt()).append("\t");
		sb.append(this.getUnfinishedArea()).append("\t");
		sb.append(this.getTotalRooms()).append("\t");
		sb.append(this.getLocker()).append("\t");
		sb.append(this.getRoof()).append("\t");
		sb.append(this.getConstruction()).append("\t");
		sb.append(this.getFoundation()).append("\t");
		sb.append(this.getLivingRoom()).append("\t");
		sb.append(this.getTotalParking()).append("\t");
		
		sb.append(this.getGrossLand()).append("\t");
		sb.append(this.getGrossImprove()).append("\t");
		sb.append(this.getExemptLand()).append("\t");
		sb.append(this.getExemptImprove()).append("\t");
		sb.append(this.getFeaturesIncl()).append("\t");
		sb.append(this.getAmenities()).append("\t");
		sb.append(this.getTitletoLand()).append("\t");
		return sb.toString();
	}

	/**
	 * @param text
	 */
	public RecordModel(String text) {
		this.extractSourceText = text;
		// 初始化
		this.setMlsNumber("");
		this.setHouseType("");
		this.setStatus("");
		this.setAddress("");
		this.setArea("");
		this.setComplex("");
		this.setListPrice("");
		this.setSoldPrice("");
		this.setAssessPrice("");
		this.setSaleDate("");
		this.setSoldDate("");
		this.setOnSaleTime("");
		this.setSqFt("");
		this.setBuiltYear("");
		this.setHouseAge("");
		this.setFlatWidth("");
		this.setFlatDepth("");
		this.setKitchenNum("");
		this.setBedrooms("");
		this.setGroundSize("");
		this.setLastSalePrice("");
		this.setLastSaleDate("");
		this.setMaintFee("");
		this.setHouseDesc("");
		this.setManageCompany("");
		this.setTaxes("");
		this.setZoning("");
		this.setPid("");
		this.setAreaUp("");
		this.setAreaDown("");
		this.setAreaMain("");
		this.setAreaBsmt("");
		this.setUnfinishedArea("");
		this.setTotalRooms("");
		this.setLocker("");
		this.setRoof("");
		this.setConstruction("");
		this.setFoundation("");
		this.setLivingRoom("");
		
		this.setBathrooms("0");
		this.setFullBaths("0");
		this.setHalfBaths("0");
		this.setTotalParking("");
		
		this.setGrossLand("0");
		this.setGrossImprove("0");
		this.setExemptLand("0");
		this.setExemptImprove("");
		this.setFeaturesIncl("");
		this.setAmenities("");
		this.setTitletoLand("");
	}

	/**
	 * 从extractSourceText中提取出各个字段
	 */
	public void extract() {
		// 提取MLS号码
		this.setMlsNumber(TextExtractor.extractMLSNumber(this.extractSourceText));
		// 提取房屋类型
		this.setHouseType(TextExtractor.extractHouseType(this.extractSourceText));
		// 提取状态
		this.setStatus(TextExtractor.extractStatus(this.extractSourceText));
		// 提取Address
		this.setAddress(TextExtractor.extractAddress(this.extractSourceText));
		// 提取区域
		this.setArea(TextExtractor.extractArea(this.extractSourceText));
		//提取三级区域
		this.setComplex(TextExtractor.extractComplex(this.extractSourceText));
		// 提取上市价格
		this.setListPrice(TextExtractor.extractListPrice(this.extractSourceText));
		// 提取售出价格
		this.setSoldPrice(TextExtractor.extractSoldPrice(this.extractSourceText));
		// 提取政府估价
		this.setAssessPrice(TextExtractor.extractAssessPrice(this.extractSourceText));
		// 提取上市日期
		this.setSaleDate(TextExtractor.extractSaleDate(this.extractSourceText));
		// 提取售出日期
		this.setSoldDate(TextExtractor.extractSoldDate(this.extractSourceText));
		// 提取在市时间
		this.setOnSaleTime(TextExtractor.extractOnSaleTime(this.extractSourceText));
		// 提取SqFt
		this.setSqFt(TextExtractor.extractSqFt(this.extractSourceText));
		// 提取年份
		this.setBuiltYear(TextExtractor.extractBuiltYear(this.extractSourceText));
		// 提取屋龄
		this.setHouseAge(TextExtractor.extractHouseAge(this.extractSourceText));
		// HOUSE类型，提取面宽
		if(this.getHouseType().equals("HOUSE"))
			this.setFlatWidth(TextExtractor.extractFlatWidth(this.extractSourceText));
		// HOUSE类型，提取面深
		if(this.getHouseType().equals("HOUSE"))
			this.setFlatDepth(TextExtractor.extractFlatDepth(this.extractSourceText));
		// 提取厨房
		this.setKitchenNum(TextExtractor.extractKitchenNum(this.extractSourceText));
		// 提取睡房
		this.setBedrooms(TextExtractor.extractBedrooms(this.extractSourceText));
		// HOUSE类型，提取土地面积
		if(this.getHouseType().equals("HOUSE"))
			this.setGroundSize(TextExtractor.extractGroundSize(this.extractSourceText));
		// 提取上次交易价格
		this.setLastSalePrice(TextExtractor.extractLastSalePrice(this.extractSourceText));
		// 提取上次交易时间
		this.setLastSaleDate(TextExtractor.extractLastSaleDate(this.extractSourceText));
		// Apartment类型，提取Maint Fee，Apartment的房屋管理费
		if(this.getHouseType().equals("APARTMENT"))
			this.setMaintFee(TextExtractor.extractMaintFee(this.extractSourceText));
		this.setHouseDesc(TextExtractor.extractHouseDesc(this.extractSourceText));
		this.setManageCompany(TextExtractor.extractManageCompany(this.extractSourceText));
		this.setTaxes(TextExtractor.extractTaxes(this.extractSourceText));
		this.setZoning(TextExtractor.extractZoning(this.extractSourceText));
		this.setPid(TextExtractor.extractPid(this.extractSourceText));
		this.setAreaUp(TextExtractor.extractAreaUp(this.extractSourceText));
		this.setAreaDown(TextExtractor.extractAreaDown(this.extractSourceText));
		this.setAreaMain(TextExtractor.extractAreaMain(this.extractSourceText));
		this.setAreaBsmt(TextExtractor.extractAreaBsmt(this.extractSourceText));
		this.setUnfinishedArea(TextExtractor.extractUnfinishedArea(this.extractSourceText));
		
		this.setTotalRooms(TextExtractor.extractTotalRooms(this.extractSourceText));
		this.setLocker(TextExtractor.extractLocker(this.extractSourceText));
		this.setRoof(TextExtractor.extractRoof(this.extractSourceText));
		this.setConstruction(TextExtractor.extractConstruction(this.extractSourceText));
		this.setFoundation(TextExtractor.extractFoundation(this.extractSourceText));
		this.setLivingRoom(TextExtractor.extractLivingRoom(this.extractSourceText));
		
		this.setBathrooms(TextExtractor.extractBathrooms(this.extractSourceText));
		this.setFullBaths(TextExtractor.extractFullBaths(this.extractSourceText));
		this.setHalfBaths(TextExtractor.extractHalfBaths(this.extractSourceText));
		this.setTotalParking(TextExtractor.extractTotalParking(this.extractSourceText));
		
		this.setGrossLand(TextExtractor.extractGrossLand(this.extractSourceText));
		this.setGrossImprove(TextExtractor.extractGrossImprove(this.extractSourceText));
		this.setExemptLand(TextExtractor.extractExemptLand(this.extractSourceText));
		this.setExemptImprove(TextExtractor.extractExemptImprove(this.extractSourceText));
		this.setFeaturesIncl(TextExtractor.extractFeaturesIncl(this.extractSourceText));
		this.setAmenities(TextExtractor.extractAmenities(this.extractSourceText));
		this.setTitletoLand(TextExtractor.extractTitletoLand(this.extractSourceText));
	
		
	}
	
	public void toPdf(String pdfname) throws DocumentException, IOException
	{
		
		Document document = new Document(PageSize.A4, 50, 50, 80, 80);
		
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfname));
		document.open();
		//设置中文
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
		Font fontZH = new Font(bfChinese, 14, Font.NORMAL);//加入document： 
		Font fontsmall = FontFactory.getFont(FontFactory.COURIER,6.7f);
		Font fontmiddle = FontFactory.getFont(FontFactory.COURIER,12f);
		Paragraph h = new Paragraph("");
		h.setAlignment(Element.ALIGN_RIGHT);
		document.add(h);
		
		PdfPCell cell;
		PdfPTable table = new PdfPTable(2);
		
		cell = new PdfPCell(new Phrase());
		try{
			cell = new PdfPCell(new Phrase());
			File f = null;  
			f = new File("resource/");  
			File[] files = f.listFiles(); // 得到f文件夹下面的所有文件。  
			List<File> list = new ArrayList<File>();
			String imgfilepath = "";//图片路径
			for (File file : files) {  
				if(file.getPath().contains(this.getMlsNumber())){
					imgfilepath = file.getPath();  
					break;
				}
			}  
			
			
//			String filepath = "resource/"+this.getMlsNumber()+".jpg";
//			System.out.println(imgfilepath+"\n");
			Image image = Image.getInstance(imgfilepath);
			cell.setRowspan(8);
			cell.setImage(image);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
		}catch(Exception e)
		{
			table = new PdfPTable(1);
			
		}
		
		table.setWidthPercentage(100);
		String[]  strs = this.getArea().split(",");
		String area = GetConfig.VerticalConfig(4, strs[0]);
		try{
			area +=">"+strs[1]+"区";
			if(this.getComplex().isEmpty() == false)
				area += ">"+this.getComplex();
		}catch(Exception e)
		{
			
		}
		
		cell = new PdfPCell(new Paragraph(area,fontZH));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph(this.getAddress(),fontZH));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph("户型： "+this.getBedrooms()+"卧"+this.getBathrooms()+"浴室"+this.getFullBaths()+"全浴室"+this.getHalfBaths()+"半浴室",fontZH));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
		String titletoLand = GetConfig.getConfig("132", this.getTitletoLand().trim());
		cell = new PdfPCell(new Paragraph(titletoLand,fontZH));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Paragraph("上市日期： "+this.getSaleDate(),fontZH));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph("销出日期： "+this.getSoldDate(),fontZH));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph("销出价格： "+this.getSoldPrice(),fontZH));		
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
		
		document.add(table);
		String sgs = "";
		if(!this.getListPrice().trim().isEmpty())
		{
			int grossLand = 0;
			try{
				grossLand = Integer.parseInt(this.getListPrice().replace("$", "").replace(",", ""));
			}catch(Exception e)
			{
				
			}
			
			int setSqFt = Integer.parseInt(this.getSqFt().replace("$", "").replace(",", ""));
			float gs = grossLand/setSqFt;
			sgs = gs+"/尺";
			float yhs = 0;
			if(grossLand>2000)
			{
				yhs = (float) (grossLand*0.02-2000);
			}else
				yhs = (float) (grossLand*0.01);
			sgs +="\n"+"印花税：$"+yhs;
		}
		
		
		
		
		Font FontChinese1 = new Font(bfChinese, 10, Font.BOLD);
		Paragraph p = new Paragraph(this.getListPrice()+"\n"+sgs,FontChinese1);
		p.setAlignment(Element.ALIGN_RIGHT);
		p.setSpacingBefore(-50f);
		document.add(p);
		
		//创建一条直线
		Paragraph p1 = new Paragraph();
		p1.add(new Chunk(new LineSeparator()));
		document.add(p1);
		
		if(this.getAmenities().isEmpty() == false)
		{
			String[] Amenities = this.getAmenities().split(",");
			String Amenitiess = "";
			for(int i =0 ;i<Amenities.length;i++)
			{
				String tmp1 = GetConfig.getConfig("330", Amenities[i].trim());
				if(tmp1.isEmpty() == false)
					Amenitiess +=tmp1+",";
			}
			Amenitiess = Amenitiess.substring(0, Amenitiess.length()-1);
			Paragraph amenities = new Paragraph(" 公共设施："+Amenitiess,FontChinese1);

			document.add(amenities);
		}
		if(this.getFeaturesIncl().isEmpty() == false)
		{
			String[] FeaturesIncls = this.getFeaturesIncl().split(",");
			String FeaturesIncl = "";
			for(int i =0 ;i<FeaturesIncls.length;i++)
			{
				String tmp2 = GetConfig.getConfig("312", FeaturesIncls[i].trim());
				if(tmp2.isEmpty() == false)
					FeaturesIncl +=tmp2+",";
			}
			FeaturesIncl = FeaturesIncl.substring(0, FeaturesIncl.length()-1);
			Paragraph featuresIncl = new Paragraph(" 特色设施："+FeaturesIncl,FontChinese1);
			document.add(featuresIncl);
		}
		

		
		
		//创建一条直线
		Paragraph p11 = new Paragraph();
		p1.add(new Chunk(new LineSeparator()));
		document.add(p11);
		
		/**
		 * 下设3张表格
		 */
		PdfPTable table1 = new PdfPTable(3);
		table1.setWidthPercentage(100);
		int builtYear = Integer.parseInt(this.getBuiltYear());
		String strYear = this.getBuiltYear();
		if(builtYear<1900)
			strYear = "不详";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String addstar = "";
		if(builtYear>=year)
			addstar = "*";
		List Vertical11=new ArrayList();
		Vertical11.add("政府估价"+addstar+":");
		Vertical11.add("土地：");
		Vertical11.add("建筑物：");
		Vertical11.add("减免：");
		Vertical11.add(" ");
		Vertical11.add("年份：");
		Vertical11.add("管理费：");
		Vertical11.add("地税：");
		
		
				
		List Vertical12=new ArrayList();
		Vertical12.add(this.getAssessPrice());
		Vertical12.add(this.getGrossLand());//不对
		Vertical12.add(this.getGrossImprove());//不对
		Vertical12.add(this.getExemptImprove());//不对
		Vertical12.add(" ");
		
		Vertical12.add(strYear);
		Vertical12.add(this.getMaintFee());
		Vertical12.add(this.getTaxes());
		
		

		PdfPTable tableleft = createtable(Vertical11,Vertical12);
		
		table1.addCell(tableleft);
		document.add(table1);
		document.add(new Paragraph("\n\r"));
		
		List Vertical21=new ArrayList();
		Vertical21.add("土地面积:");
		Vertical21.add("面宽x进深：");
		Vertical21.add("居住面积：");
		Vertical21.add("主层：");
		Vertical21.add("楼上：");
		Vertical21.add("楼下：");
		Vertical21.add("地下：");
		Vertical21.add("未修建面积：");
		
		List Vertical22=new ArrayList();
		Vertical22.add(this.getGroundSize());
		String wd ="";
		if(!this.getFlatWidth().isEmpty() && !this.getFlatDepth().isEmpty())
		 wd = this.getFlatWidth()+"尺x"+this.getFlatDepth()+"尺";
		Vertical22.add(wd);
		String sqftc = ASEXUtil.inc2mi(this.getSqFt());
		Vertical22.add(this.getSqFt()+sqftc);//不对
		
		String AreaMainc = ASEXUtil.inc2mi(this.getAreaMain());
		Vertical22.add(this.getAreaMain()+AreaMainc);
		
		String areaupc = ASEXUtil.inc2mi(this.getAreaUp());
		Vertical22.add(this.getAreaUp()+areaupc);//不对

		String AreaDownc = ASEXUtil.inc2mi(this.getAreaDown());
		Vertical22.add(this.getAreaDown()+AreaDownc);
		
		String AreaBsmtc = ASEXUtil.inc2mi(this.getAreaBsmt());
		Vertical22.add(this.getAreaBsmt()+AreaBsmtc);
		
		String UnfinishedAreac = ASEXUtil.inc2mi(this.getUnfinishedArea());
		Vertical22.add(this.getUnfinishedArea()+UnfinishedAreac);
		
		PdfPTable tableleft2 = createtable(Vertical21,Vertical22);
		tableleft2.setWidthPercentage(40);
		table1.addCell(tableleft2);
		document.add(table1);
//		document.add(new Paragraph("\n\r"));
		
		List Vertical31=new ArrayList();
		Vertical31.add("卧室:");
		Vertical31.add("全卫：");
		Vertical31.add("半卫：");
		Vertical31.add("车位：");
		Vertical31.add("总房间：");
		Vertical31.add("储藏室：");
		Vertical31.add("屋顶：");
		Vertical31.add("结构：");
		Vertical31.add("地基：");
		
		//读取配置文件
		String roof =  GetConfig.getConfig("236",this.getRoof());
		String construction =  GetConfig.getConfig("228",this.getConstruction());	
		String foundation =  GetConfig.getConfig("230",this.getFoundation());
		List Vertical32=new ArrayList();
		Vertical32.add(this.getBedrooms());
		Vertical32.add(this.getFullBaths());
		Vertical32.add(this.getHalfBaths());//不对
		Vertical32.add(this.getTotalParking());//不对
		Vertical32.add(this.getTotalRooms());//不对
		Vertical32.add(this.getLocker());
		Vertical32.add(roof);
		Vertical32.add(construction);
		Vertical32.add(foundation);
		
		
		PdfPTable tableleft3 = createtable(Vertical31,Vertical32);
		table1.addCell(tableleft3);
		document.add(table1);
		
		if(!"".equals(addstar))
		{
			Paragraph zhushi = new Paragraph("* 当年新房无法采信政府估价，新房有可能支付额外税费",fontZH);
			p.setAlignment(Element.ALIGN_RIGHT);
			p.setSpacingAfter(15f);
			document.add(zhushi);
		}
		
		
		//创建一条直线
		Paragraph p2 = new Paragraph();
		p2.add(new Chunk(new LineSeparator()));
		p2.setSpacingAfter(15f);
		document.add(p2);
		//详细情况
		PdfPTable detailedDistribution = DetailedDistribution(this.getLivingRoom());
		document.add(detailedDistribution);
		//物业介绍
		Paragraph ggss = new Paragraph("物业介绍：",FontChinese1);
		p.setSpacingBefore(15f);
		document.add(ggss);
		String descs = this.getHouseDesc();
		if(descs.length()>740)
			descs = descs.substring(0, 740);
		Paragraph desc = new Paragraph(descs,fontmiddle);
		document.add(desc);
		
//		//创建一条直线
//		Paragraph p3 = new Paragraph();
//		p3.add(new Chunk(new LineSeparator()));
//		document.add(p3);
		
		Paragraph footstr = new Paragraph(this.getMlsNumber()+"|"+this.getPid()+"|"+this.getManageCompany()+"|"+"-"+"|"+this.getZoning(),fontsmall);
		footstr.setSpacingBefore(10f);
		footstr.setAlignment(Element.ALIGN_CENTER);
		document.add(footstr);
		
		
		document.close();
	}
	
	
	public static   PdfPTable  DetailedDistribution(String str) throws DocumentException, IOException
	{
		//创建表格
		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(100);
		PdfPCell cell;
		//设置中文
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
		Font fontZH = new Font(bfChinese, 12, Font.NORMAL);//加入document： 
		String[] string = str.split("#");
		for(int i=0;i<string.length;i++)
		{
			String[] strings = string[i].split(" ");
			for(int j=0;j<strings.length;j++)
			{
				
				String v1 =strings[j].trim();
				if(v1==" " || v1=="" || v1==null || v1.isEmpty())
					continue; 
				if(v1.contains("X"))
				{
					String[] vv = v1.split("X");
					
					String vv1 = vv[0].toString().trim();
					float xx = Float.parseFloat(getYingCun(vv1));
					cell = new PdfPCell(new Paragraph(xx+""));
					table.addCell(cell);
					
					
					String vv2 = vv[1].toString().trim();
					float yy = Float.parseFloat(getYingCun(vv2));
					cell = new PdfPCell(new Paragraph(yy+""));
					table.addCell(cell);
					
					
					float tt = (float) (Math.rint(xx*yy*10)/10);
					String tt1 = tt+"";
					cell = new PdfPCell(new Paragraph(tt1));
					table.addCell(cell);
				}else{
					List config = GetConfig.getConfig("308");
					
					
					List l2 = (List) config.get(1);
					List l3 = (List) config.get(2);
					int l1c = l2.size();
					String roomcn = strings[j].trim();//房间中文名
					for(int ii=0;ii<l1c;ii++)
					{
						String k1 = l2.get(ii).toString();
						String k2 = k1.replace(" ", "").trim();
						if(k2.equals(roomcn))
						{
							roomcn = l3.get(ii).toString();
							break;
						}
							
						
					}
					cell = new PdfPCell(new Paragraph(roomcn,fontZH));
					table.addCell(cell);
				}
			}
			
		}
		return table;
	}
	

		
		public static String getYingCun (String temp)
		{
			String yingcun= "";

			if(temp.indexOf("'") == -1)
			{
				//如果没有符号，那就为英寸
				yingcun = temp;
				return yingcun;
			}
			else
			{
				//如果有符号，截取符号前面
				String[] s1 = temp.split("'");
				if(s1.length==1)
				{
					double x = Double.parseDouble(s1[0]);
					yingcun = x +"" ;
				}
				if(s1.length==2)
				{
					double x = Double.parseDouble(s1[0]);
					double y = 0;
					try{
						y = Double.parseDouble(s1[1]);
						y = Math.rint(y*10/12)/10;
						 
					}catch(Exception e)
					{
						
					}
					yingcun =  x + y + "";
				}
				return yingcun;
			}
			
		}



	public static   PdfPTable  createtable(List Vertical1,List Vertical2) throws DocumentException, MalformedURLException, IOException
	{

		//创建表格
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		PdfPCell cell;
		//设置中文
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
		Font fontZH = new Font(bfChinese, 12, Font.NORMAL);//加入document： 
		int l = Vertical1.size();
		for(int i =0;i<l;i++)
		{
			String v1 = Vertical1.get(i).toString();
			cell = new PdfPCell(new Paragraph(v1,fontZH));
			table.addCell(cell);
			String v2 = Vertical2.get(i).toString();
			cell = new PdfPCell(new Paragraph(v2,fontZH));
			table.addCell(cell);
		}
		return table;
	}

}
