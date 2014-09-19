package com.asex.test;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.asex.util.PDFUtil;
import com.asex.util.TextExtractor;

public class TextExtractorTest {
	private String sourceText;

	@Before
	public void setUp() throws Exception {
		PDFUtil pdf = new PDFUtil(new File("./input/test3242.pdf"));
		pdf.goToPage(205);
		this.sourceText += pdf.getCurrentPageContent();
		pdf.goToNextPage();
		this.sourceText += pdf.getCurrentPageContent();
		System.out.println(this.sourceText);
	}

	@Ignore
	public void testExtractMLSNumber() {
		System.out.println(TextExtractor.extractMLSNumber(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractStatus() {
		System.out.println(TextExtractor.extractStatus(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractAddress() {
		System.out.println(TextExtractor.extractAddress(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractArea() {
		System.out.println(TextExtractor.extractArea(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractListPrice() {
		System.out.println(TextExtractor.extractListPrice(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractSoldPrice() {
		System.out.println(TextExtractor.extractSoldPrice(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractAssessPrice() {
		System.out.println(TextExtractor.extractAssessPrice(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractSaleDate() {
		System.out.println(TextExtractor.extractSaleDate(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractSoldDate() {
		System.out.println(TextExtractor.extractSoldDate(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractOnSaleTime() {
		System.out.println(TextExtractor.extractOnSaleTime(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractSqFt() {
		System.out.println(TextExtractor.extractSqFt(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractBuiltYear() {
		System.out.println(TextExtractor.extractBuiltYear(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractHouseAge() {
		System.out.println(TextExtractor.extractHouseAge(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractFlatWidth() {
		System.out.println(TextExtractor.extractFlatWidth(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractFlatDepth() {
		System.out.println(TextExtractor.extractFlatDepth(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractKitchenNum() {
		System.out.println(TextExtractor.extractKitchenNum(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractBedrooms() {
		System.out.println(TextExtractor.extractBedrooms(sourceText));
		fail("Not yet implemented");
	}

	@Test
	public void testExtractGroundSize() {
		System.out.println(TextExtractor.extractGroundSize(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractLastSalePrice() {
		System.out.println(TextExtractor.extractLastSalePrice(sourceText));
		fail("Not yet implemented");
	}

	@Ignore
	public void testExtractLastSaleDate() {
		System.out.println(TextExtractor.extractLastSaleDate(sourceText));
		fail("Not yet implemented");
	}
	@Ignore
	public void testExtractHouseType() {
		System.out.println(TextExtractor.extractHouseType(sourceText));
		fail("Not yet implemented");
	}
	@Ignore
	public void testExtractMaintFee(){
		System.out.println(TextExtractor.extractMaintFee(sourceText));
		fail("Not yet implemented");
	}
	

}
