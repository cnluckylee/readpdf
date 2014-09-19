package com.asex.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class GetConfig {
	public static String getConfig(String line, String searchKey) {
		int i, j;
		Sheet sheet;
		Workbook book;
		Cell cell1, cell2, cell3, cell4;
		try {
			// t.xls为要读取的excel文件名
			book = Workbook.getWorkbook(new File("./config/AvailData.xls"));

			// 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			sheet = book.getSheet(2);
			// 获取左上角的单元格
			cell1 = sheet.getCell(0, 0);

			i = 1;
			j = 0;
			while (true) {
				// 获取每一行的单元格
				cell1 = sheet.getCell(0, i);// （列，行）

				if (line.equals(cell1.getContents().trim())) {
					int k = 1;
					while (true) {
						cell2 = sheet.getCell(k, i + 1);
						String c2 = cell2.getContents().toString()
								.toLowerCase();
						cell3 = sheet.getCell(k, i + 2);
						String c3 = cell3.getContents().toString()
								.toLowerCase();
						cell4 = sheet.getCell(k, i + 3);
						String c4 = cell4.getContents().toString()
								.toLowerCase();
						searchKey = searchKey.toLowerCase();
						if (searchKey.equals(c2) || searchKey.equals(c3)) {
//							System.out.println("匹配到：" + c4 + "\n");
							return c4;

						}
						if ("".equals(cell2.getContents()) == true) // 如果读取的数据为空
						{
							break;
						}
						k++;

						// System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()+"\t"+cell4.getContents());
					}
					break;
				}
				i++;
			}
			book.close();
		} catch (Exception e) {
		}

		return "";

	}

	/**
	 * 获得某配置的数组
	 */
	public static List getConfig(String line) {
		List l = new ArrayList();
		List l1 = new ArrayList();
		List l2 = new ArrayList();
		List l3 = new ArrayList();
		int i, j;
		Sheet sheet;
		Workbook book;
		Cell cell1, cell2, cell3, cell4;
		try {
			// t.xls为要读取的excel文件名
			book = Workbook.getWorkbook(new File("./config/AvailData.xls"));

			// 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			sheet = book.getSheet(2);
			// 获取左上角的单元格
			cell1 = sheet.getCell(0, 0);

			i = 1;
			j = 0;
			while (true) {
				// 获取每一行的单元格
				cell1 = sheet.getCell(0, i);// （列，行）

				if (line.equals(cell1.getContents().trim())) {
					int k = 1;
					while (true) {
						cell2 = sheet.getCell(k, i + 1);
						cell3 = sheet.getCell(k, i + 2);
						cell4 = sheet.getCell(k, i + 3);
						if ("".equals(cell2.getContents()) == true) // 如果读取的数据为空
						{
							break;
						}
						k++;
						l1.add(cell2.getContents());
						l2.add(cell3.getContents());
						l3.add(cell4.getContents());
						// System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()+"\t"+cell4.getContents());
					}
					break;
				}
				i++;
			}
			book.close();
		} catch (Exception e) {
		}
		l.add(l1);
		l.add(l2);
		l.add(l3);
		return l;
	}

	/**
	 * 获取纵向的配置数据
	 */
	public static String VerticalConfig(int sheetnum, String string) {
		String cn = "";
		int i = 0;
		Sheet sheet;
		Workbook book;
		Cell cell1, cell2, cell3, cell4;
		try {
			// t.xls为要读取的excel文件名
			book = Workbook.getWorkbook(new File("./config/AvailData.xls"));

			// 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			sheet = book.getSheet(sheetnum);
			while (true) {
				// 获取左上角的单元格
				cell1 = sheet.getCell(1, i);
				String conf = cell1.getContents().trim();
				if (string.equals(conf)) {
					cell2 = sheet.getCell(2, i);
					cn = cell2.getContents();
					book.close();
					return cn;
				}
				i++;
			}

		} catch (Exception e) {
		}
		return cn;
	}
}
