package com.asex.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.*;

public class readExcel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List l=new ArrayList();
		List l1=new ArrayList();
		List l2=new ArrayList();
		List l3=new ArrayList();
		int i,j;
        Sheet sheet;
        Workbook book;
        Cell cell1,cell2,cell3,cell4;
        try { 
            //t.xls为要读取的excel文件名
            book= Workbook.getWorkbook(new File("./config/AvailData.xls")); 
             
            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet=book.getSheet(4); 
            //获取左上角的单元格
            cell1=sheet.getCell(0,5);
            System.out.println("标题："+cell1.getContents()); 
            System.out.println("行数："+cell1.getRow()); 
            
            i=1;
            j=0;
            while(true)
            {
                //获取每一行的单元格 
                cell1=sheet.getCell(0,i);//（列，行）
                
                if("308".equals(cell1.getContents().trim()))
                {
                	int k=1;
                	while(true)
                	{
                		
                		cell2=sheet.getCell(k,i+1);
                		cell3=sheet.getCell(k,i+2);
                		cell4=sheet.getCell(k,i+3);
                		l1.add(cell2.getContents());
                		l2.add(cell3.getContents());
                		l3.add(cell4.getContents());
                		if("".equals(cell2.getContents())==true)    //如果读取的数据为空
                        {
                			break;
                        }
                		k++;
                		System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()+"\t"+cell4.getContents());
                	}

                	 break;
                }
                i++;
                
            }
           book.close(); 
        }
        catch(Exception e)  { } 
        l.add(l1);
        l.add(l2);
        l.add(l3);
        System.out.println(l);
    }
	

}
