package com.asex.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
public class CreatePDF {

	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
	
//		test3("test3.pdf");
//		test4("test4.pdf");
//		test5("test5.pdf");
//		test6("test6.pdf");
//		test7("test7.pdf");
		
//		test8("test8.pdf");
		table("table.pdf");
//		testtable("testtable.pdf");
//		testimage("image.pdf");
	}
	
	
	public static   PdfPTable  createtable() throws DocumentException, MalformedURLException, IOException
	{

		//创建表格
		PdfPTable table = new PdfPTable(2);
		PdfPCell cell;
		//设置中文
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
		Font fontZH = new Font(bfChinese, 12, Font.NORMAL);//加入document： 
		
		for(int i =0;i<8;i++)
		{
			cell = new PdfPCell(new Paragraph("政府估价*"+i,fontZH));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("$4000,000"+i,fontZH));
			table.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("土地面积"+i,fontZH));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("$4000,000"+i,fontZH));
			table.addCell(cell);
		}
		return table;
	}
	/**
	 * 左右文字
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static  void  table(String pdfname) throws DocumentException, MalformedURLException, IOException
	{
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfname));
		document.open();
		//设置中文
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
		Font fontZH = new Font(bfChinese, 12, Font.NORMAL);//加入document： 
		
		//创建图片
		PdfPTable table = new PdfPTable(2);
		
		//创建图片
		Image image = Image.getInstance("resource/test.jpg");
		
		
		PdfPCell cell;
		cell = new PdfPCell(new Phrase());
		cell.setRowspan(20);
		cell.setImage(image);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph("列知文>区》Park",fontZH));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		table.addCell("# 508 1661 ONTARIO ST, V5Y 0C3");
		table.addCell(new PdfPCell(new Paragraph("户型：看见对方就是分手后",fontZH)));
		table.addCell("# 508 1661 ONTARIO ST, V5Y 0C3");
		table.addCell(new PdfPCell(new Paragraph("户型：看见对方就是分手后",fontZH)));
		document.add(table);
		
		Font FontChinese1 = new Font(bfChinese, 14, Font.BOLD);
		Paragraph p = new Paragraph(" $555,000",FontChinese1);
		p.setAlignment(Element.ALIGN_RIGHT);
		p.setSpacingAfter(15f);
		document.add(p);
		
		//创建一条直线
		Paragraph p1 = new Paragraph();
		p1.add(new Chunk(new LineSeparator()));
		document.add(p1);
		
		
		
		/**
		 * 下设3张表格
		 */
		PdfPTable table1 = new PdfPTable(3);
		PdfPTable tableleft = createtable();
		tableleft.setSpacingAfter(15f);
		table1.addCell(tableleft);
		document.add(table1);
		document.add(new Paragraph("\n\r"));
		table1.addCell(tableleft);
		document.add(table1);
		document.add(new Paragraph("\n\r"));
		table1.addCell(tableleft);
		document.add(table1);
		
		Paragraph zhushi = new Paragraph("* 当年新房无法采信政府估价，新房有可能支付",fontZH);
		p.setAlignment(Element.ALIGN_RIGHT);
		p.setSpacingAfter(15f);
		document.add(zhushi);
		
		//创建一条直线
		Paragraph p2 = new Paragraph();
		p2.add(new Chunk(new LineSeparator()));
		document.add(p2);
		
		Paragraph ggss = new Paragraph("公共设施:    空调、冰箱",FontChinese1);
		p.setSpacingBefore(15f);
		document.add(ggss);
		
		//创建一条直线
		Paragraph p3 = new Paragraph();
		p3.add(new Chunk(new LineSeparator()));
		document.add(p3);
				
		
		document.close();
	}
	
	
	public static  void  testimage(String pdfname) throws DocumentException, MalformedURLException, IOException
	{
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfname));
		document.open();
		
		Image image = Image.getInstance("resource/test.jpg");
		float[] widths = { 1f, 4f };

		PdfPTable table = new PdfPTable(widths);
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
		Font fontZH = new Font(bfChinese, 12, Font.NORMAL);//加入document： 
		//插入图片
		table.addCell(new PdfPCell(new Paragraph("图片测试",fontZH)));
		table.addCell(image);

		//调整图片大小
		table.addCell("这是第二张");
		table.addCell(new PdfPCell(image, true));

		//不调整
		table.addCell("This three");
		table.addCell(new PdfPCell(image, false));
		document.add(table);
		document.close();
	}

	/**
	 * 左右文字
	 */
	public static  void  test8(String pdfname) throws FileNotFoundException, DocumentException
	{
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfname));
		document.open();
		
		
		PdfContentByte canvas = writer.getDirectContent();

		Phrase phrase1 = new Phrase("This is a test!left");
		Phrase phrase2 = new Phrase("This is a test!right");
		Phrase phrase3 = new Phrase("This is a test!center");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase1, 10, 500, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase2, 10, 536, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase3, 10, 572, 0);
		document.close();
	}
	/**
	 * header footer
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public static  void  test7(String pdfname) throws FileNotFoundException, DocumentException
	{
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(pdfname));
		doc.open();

		writer.setPageEvent(new PdfPageEventHelper() {

			public void onEndPage(PdfWriter writer, Document document) {
				
				PdfContentByte cb = writer.getDirectContent();
		        cb.saveState();

		        cb.beginText();
				BaseFont bf = null;
				try {
					bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
				} catch (Exception e) {
					e.printStackTrace();
				}
				cb.setFontAndSize(bf, 10);
				
		        //Header
				float x = document.top(-20);
				
		        //左
		        cb.showTextAligned(PdfContentByte.ALIGN_LEFT,
		                           "H-Left",
		                           document.left(), x, 0);
		        //中
		        cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
		        					writer.getPageNumber()+ " page",
		                           (document.right() + document.left())/2,
		                           x, 0);
		        //右
		        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,
		                           "H-Right",
		                           document.right(), x, 0);

		        //Footer
		        float y = document.bottom(-20);

		        //左
		        cb.showTextAligned(PdfContentByte.ALIGN_LEFT,
		                           "F-Left",
		                           document.left(), y, 0);
		        //中
		        cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
		        					writer.getPageNumber()+" page",
		                           (document.right() + document.left())/2,
		                           y, 0);
		        //右
		        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,
		                           "F-Right",
		                           document.right(), y, 0);

		        cb.endText();
		        
		        cb.restoreState();
			}
		});

		doc.open();
		doc.add(new Paragraph("1 page"));        
		doc.newPage();
		doc.add(new Paragraph("2 page"));        
		doc.newPage();
		doc.add(new Paragraph("3 page"));        
		doc.newPage();
		doc.add(new Paragraph("4 page"));
		doc.close();
	}
	/**
	 * 画图
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public static  void  test6(String pdfname) throws FileNotFoundException, DocumentException
	{
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(pdfname));
		document.open();
		
		//左右箭头
		document.add(new VerticalPositionMark() {

			public void draw(PdfContentByte canvas, float llx, float lly,
					float urx, float ury, float y) {
				canvas.beginText();
				BaseFont bf = null;
				try {
					bf = BaseFont.createFont(BaseFont.ZAPFDINGBATS, "", BaseFont.EMBEDDED);
				} catch (Exception e) {
					e.printStackTrace();
				}
				canvas.setFontAndSize(bf, 12);
				
				// LEFT
				canvas.showTextAligned(Element.ALIGN_CENTER, String.valueOf((char) 220), llx - 10, y, 0);
				// RIGHT
				canvas.showTextAligned(Element.ALIGN_CENTER, String.valueOf((char) 220), urx + 10, y + 8, 180);
				
				canvas.endText();
			}
		});

		//直线
		Paragraph p1 = new Paragraph("LEFT");
		p1.add(new Chunk(new LineSeparator()));
		p1.add("R");
		document.add(p1);
		//点线
		Paragraph p2 = new Paragraph("LEFT");
		p2.add(new Chunk(new DottedLineSeparator()));
		p2.add("R");
		document.add(p2);
		//下滑线
		LineSeparator UNDERLINE = new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2);
		Paragraph p3 = new Paragraph("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
		p3.add(UNDERLINE);
		document.add(p3);
		document.close();
	}
	/**
	 * 设置段落 
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public static  void  test5(String pdfname) throws DocumentException, FileNotFoundException
	{
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(pdfname));
		document.open();
		Paragraph p = new Paragraph("In the previous example, you added a header and footer with the showTextAligned() method. This example demonstrates that it’s sometimes more interesting to use PdfPTable and writeSelectedRows(). You can define a bottom border for each cell so that the header is underlined. This is the most elegant way to add headers and footers, because the table mechanism allows you to position and align lines, images, and text.");

		//默认
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(p);

		document.newPage();
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		p.setIndentationLeft(1 * 15f);
		p.setIndentationRight((5 - 1) * 15f);
		document.add(p);

		//居右
		document.newPage();
		p.setAlignment(Element.ALIGN_RIGHT);
		p.setSpacingAfter(15f);
		document.add(p);

		//居左
		document.newPage();
		p.setAlignment(Element.ALIGN_LEFT);
		p.setSpacingBefore(15f);
		document.add(p);

		//居中
		document.newPage();
		p.setAlignment(Element.ALIGN_CENTER);
		p.setSpacingAfter(15f);
		p.setSpacingBefore(15f);
		document.add(p);
		document.close();
	}
	/**
	 * 插入Anchor, Image, Chapter, Section 
	 * @param pdfname
	 * @throws DocumentException
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static  void  test4(String pdfname) throws DocumentException, MalformedURLException, IOException
	{
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(pdfname));
		document.open();
		//Anchor对象: internal and external links
		Paragraph country = new Paragraph();
		Anchor dest = new Anchor("china", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLUE));
		dest.setName("CN");
		dest.setReference("http://www.china.com");//external
		country.add(dest);
		country.add(String.format(": %d sites", 10000));
		document.add(country);

		document.newPage();
		Anchor toUS = new Anchor("Go to first page.", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLUE));
		toUS.setReference("#CN");//internal
		document.add(toUS);

		//Image对象
		document.newPage();
		Image img = Image.getInstance("resource/test.jpg");
		img.setAlignment(Image.LEFT | Image.TEXTWRAP);
		img.setBorder(Image.BOX);
		img.setBorderWidth(10);
		img.setBorderColor(BaseColor.WHITE);
		img.scaleToFit(1000, 72);//大小
		img.setRotationDegrees(-30);//旋转
		document.add(img);

		//Chapter, Section对象（目录）
		document.newPage();
		Paragraph title = new Paragraph("Title");
		Chapter chapter = new Chapter(title, 1);

		title = new Paragraph("Section A");
		Section section = chapter.addSection(title);
		section.setBookmarkTitle("bmk");
		section.setIndentation(30);
		section.setBookmarkOpen(false);
		section.setNumberStyle(
		Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);

		Section subsection = section.addSection(new Paragraph("Sub Section A"));
		subsection.setIndentationLeft(20);
		subsection.setNumberDepth(1);

		document.add(chapter);
		document.close();		
				
	}
	
	/**
	 * 插入Chunk, Phrase, Paragraph, List 
	 * @param pdfname
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static  void  test3(String pdfname) throws FileNotFoundException, DocumentException
	{
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(pdfname));
		document.open();
		document.add(new Chunk("China"));
		document.add(new Chunk(" "));
		Font font = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
		Chunk id = new Chunk("chinese", font);
		id.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
		id.setTextRise(6);
		document.add(id);
		document.add(Chunk.NEWLINE);

		document.add(new Chunk("Japan"));
		document.add(new Chunk(" "));
		Font font2 = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
		Chunk id2 = new Chunk("japanese", font2);
		id2.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
		id2.setTextRise(6);
		id2.setUnderline(0.2f, -2f);
		document.add(id2);
		document.add(Chunk.NEWLINE);

		//Phrase对象: a List of Chunks with leading
		document.newPage();
		document.add(new Phrase("Phrase page"));

		Phrase director = new Phrase();
		Chunk name = new Chunk("China");
		name.setUnderline(0.2f, -2f);
		director.add(name);
		director.add(new Chunk(","));
		director.add(new Chunk(" "));
		director.add(new Chunk("chinese"));
		director.setLeading(24);
		document.add(director);

		Phrase director2 = new Phrase();
		Chunk name2 = new Chunk("Japan");
		name2.setUnderline(0.2f, -2f);
		director2.add(name2);
		director2.add(new Chunk(","));
		director2.add(new Chunk(" "));
		director2.add(new Chunk("japanese"));
		director2.setLeading(24);
		document.add(director2);
				
		//Paragraph对象: a Phrase with extra properties and a newline
		document.newPage();
		document.add(new Paragraph("Paragraph page"));

		Paragraph info = new Paragraph();
		info.add(new Chunk("China "));
		info.add(new Chunk("chinese"));
		info.add(Chunk.NEWLINE);
		info.add(new Phrase("Japan "));
		info.add(new Phrase("japanese"));
		document.add(info);

		//List对象: a sequence of Paragraphs called ListItem
		document.newPage();
		List list = new List(List.ORDERED);
		for (int i = 0; i < 10; i++) {
			ListItem item = new ListItem(String.format("%s: %d movies",
					"country" + (i + 1), (i + 1) * 100), new Font(
					Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE));
			List movielist = new List(List.ORDERED, List.ALPHABETICAL);
			movielist.setLowercase(List.LOWERCASE);
			for (int j = 0; j < 5; j++) {
				ListItem movieitem = new ListItem("Title" + (j + 1));
				List directorlist = new List(List.UNORDERED);
				for (int k = 0; k < 3; k++) {
					directorlist.add(String.format("%s, %s", "Name1" + (k + 1),
							"Name2" + (k + 1)));
				}
				movieitem.add(directorlist);
				movielist.add(movieitem);
			}
			item.add(movielist);
			list.add(item);
		}
		document.add(list);
		document.close();		
				
	}

}
