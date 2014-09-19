package com.asex.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFUtil {
	private PdfReader reader;
	private int totalPage;
	private int currentPage;

	public PdfReader getReader() {
		return reader;
	}

	public void setReader(PdfReader reader) {
		this.reader = reader;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public PDFUtil(File pdfFile) {
		try {
			this.setReader(new PdfReader(new FileInputStream(pdfFile)));
		} catch (FileNotFoundException e) {
			Log.logError(e);
		} catch (IOException e) {
			Log.logError(e);
		}
		this.setTotalPage(this.getReader().getNumberOfPages());
		this.setCurrentPage(0);
	}

	public String getCurrentPageContent() {
		String ret = "";
		try {
			ret = PdfTextExtractor.getTextFromPage(this.getReader(), this.getCurrentPage());
		} catch (IOException e) {
			Log.logError(e);
		}
		return ret;
	}

	public boolean goToNextPage() {
		if (this.currentPage + 1 > this.totalPage)
			return false;
		else {
			this.currentPage++;
			return true;
		}
	}

	public boolean goToPage(int pageNum) {
		if (pageNum > this.totalPage)
			return false;
		else {
			this.currentPage = pageNum;
			return true;
		}
	}
}
