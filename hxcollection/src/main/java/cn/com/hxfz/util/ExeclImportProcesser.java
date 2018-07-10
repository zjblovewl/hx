package cn.com.hxfz.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// TODO: Auto-generated Javadoc

/**
 * Execl读取功能,每次读取一个sheet页，所有sheet读取完成后，必须调用close()方法关闭输入流。.
 *
 * @author liumm
 */
public class ExeclImportProcesser {  
	
	/** The input stream. */
	private InputStream inputStream;
	 
	/** The read workbook. */
	private Workbook readWorkbook = null;
	
	/**
	 * The Constructor.
	 *
	 * @param fileName the file name
	 * @param input the input
	 * @throws IOException the IO exception
	 */
	public ExeclImportProcesser(String fileName,InputStream input) throws IOException {
		this.inputStream = input;
		if (fileName.endsWith(".xls"))
			readWorkbook = new HSSFWorkbook(inputStream);
		else if (fileName.endsWith(".xlsx"))  
        	readWorkbook = new XSSFWorkbook(inputStream);    
        else  
            throw new RuntimeException("不支持该导入格式!");
	}
	
	 /**
 	 * Get work book.
 	 *
 	 * @return the work book
 	 */
 	public  int getWorkBook(){
		 return readWorkbook.getNumberOfSheets();
	 }
	
	 /**
 	 * Get sheet name.
 	 *
 	 * @param index the index
 	 * @return the sheet name
 	 */
 	public String getSheetName(int index){
		 return readWorkbook.getSheetName(index);
	 }
	 
	 /**
 	 * 根据sheet序号读取Excel表格内容.
 	 *
 	 * @param sheetNum sheet下标，从0开始
 	 * @return 从表格的第一行开始读取数据
 	 */
	public String[][] readExcelSheet(int sheetNum){   
        Sheet sheet1 = readWorkbook.getSheetAt(sheetNum); 
        if(sheet1 == null)
        	return null;
    	 int rowNum = sheet1.getLastRowNum();
         int cellNum = rowNum > 0 ? sheet1.getRow(0).getPhysicalNumberOfCells() : 0; 
         String data[][]= new String[rowNum+1][cellNum]; 
         Row row = null;
         Cell cell = null;
         for (int i = 0 ; i <= rowNum ; i++) {   
         	row = sheet1.getRow(i);
             for (int j = 0; j < cellNum; j++) {
             	cell = row.getCell(j);
             	if(cell != null){
	             	cell.setCellType(Cell.CELL_TYPE_STRING);
	             	data[i][j] = cell.getStringCellValue();
             	}else
             		data[i][j] = "";
//             	System.out.println("value "+data[i][j]);
             }     
         }     
             
      
        return data;
	}
	
	/**
	 * 根据sheet序号读取Excel表格不同sheet页的内容.
	 *
	 * @param sheetNum sheet下标
	 * @return 从表格的第二行开始读取数据
	 */
	public String[][] readExcelSheetFromTwo(int sheetNum){ //从Excel表格的第二行开始读取数据
        Sheet sheet1 = readWorkbook.getSheetAt(sheetNum); 
        String data[][]=null;
        if(sheet1!=null&&sheet1.getRow(0)!=null&&sheet1.getRow(1)!=null){
        	 int rowNum = sheet1.getLastRowNum();
             int cellNum = sheet1.getRow(0).getPhysicalNumberOfCells();   
             data = new String[rowNum+1][cellNum];  
             Row row = null;
             Cell cell = null;
        	 for (int i = 1 ; i <= rowNum ; i++) {            	
              	row = sheet1.getRow(i);
              	if(row.getCell(1)!=null){
              		for (int j = 0; j < cellNum; j++) {
                      	cell = row.getCell(j);
                      	if(j!=0&&j!=8&&j!=7&&cell==null)
                      		break;
                      	if(cell==null){
                      		data[i][j]="";
                      	}else{
                      		cell.setCellType(Cell.CELL_TYPE_STRING);
                          	data[i][j] = cell.getStringCellValue();
                      	}
                      	System.out.println("value "+data[i][j]);
                      } 
              	}
              } 
         
        }
        return data;
        
	}
	 
	/**
	 * 读取多个sheet后，关闭输入流.
	 *
	 * @throws IOException the IO exception
	 */
	public void close() throws IOException{ 
		inputStream.close();
	}
	
 
}
