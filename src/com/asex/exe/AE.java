package com.asex.exe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.asex.model.RecordModel;
import com.asex.util.FileUtil;
import com.asex.util.Log;
import com.asex.util.PDFUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;

public class AE {
	// 1. 列出input目录下的所有文件
	// 2. 对每个pdf进行读取 & 分析，读取势必读取偶数页码
	// 3. 结果输出到 output/test1.pdf中
	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		File[] pdfs = new File("./input").listFiles();
		PDFUtil pdfUtil = null;
		for (File file : pdfs) {
			if (file.getName().endsWith("pdf")) {
				pdfUtil = new PDFUtil(file);
				
				while (pdfUtil.goToNextPage()) {
					String recordFirstPage, recordSecondPage = "";
					recordFirstPage = pdfUtil.getCurrentPageContent();
					if (recordFirstPage.contains("MLS")) {
						pdfUtil.goToNextPage();
						recordSecondPage = pdfUtil.getCurrentPageContent();
					} else
						continue;
					
					RecordModel record = new RecordModel(recordFirstPage + "\n" + recordSecondPage);
					record.extract();
					if(record.getMlsNumber().isEmpty())
						continue;
					try {
//						String tmpFileName = "./output/" + file.getName() + ".txt";
						
						String pdfname = "";
						pdfname =  "./outpdf/" + record.getMlsNumber()+".pdf";
						try {
							
							record.toPdf(pdfname);
							
							
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						String existString = FileUtil.readFile(tmpFileName, "UTF-8");
//						if (!existString.equals(""))
//							FileUtil.writeFile(existString + "\n" + record.toString(), tmpFileName, "UTF-8");
//						else
//							FileUtil.writeFile(record.toString(), tmpFileName, "UTF-8");
					} catch (FileNotFoundException e) {
						Log.logError(e);
					} catch (IOException e) {
						Log.logError(e);
					}
					Log.logInfo("成功提取记录, MLS为" + record.getMlsNumber());
					Log.logInfo(record.toLogString());
				}
				
			}
		}

	}
}
