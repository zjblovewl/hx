package cn.com.hxfz.util;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
// TODO: Auto-generated Javadoc

/**
 * Execl根据参数生成模板，可以生成多个sheet页，支持下拉框，必须调用close()方法关闭输入流。.
 *
 * @author liumm
 */
public class ExeclExportTemplate {  
	
	/** The hide sheet. */
	static String HIDE_SHEET = "-h";
	
	/** The rang. */
	int rang = 10;
	
	/** The start. */
	int start = 0;

	/** The output stream. */
	private OutputStream outputStream;
	
	/** The workbook. */
	private HSSFWorkbook workbook;  
	
	/**
	 * The Constructor.
	 *
	 * @param output the output
	 */
	public ExeclExportTemplate(OutputStream output) {
		this.outputStream = output;
		workbook = new HSSFWorkbook();
//		for (int i = 0; i < rang*2; i++) {
//			workbook.createSheet();   
//			workbook.setSheetHidden(i, true);
//		}
	}
	 
	/**
	 * Execl模板导出，可以导出多个sheet.
	 *
	 * @param sheetName sheet名称
	 * @param headers  导出模板的headers参数长度必须为2(隐藏列头、显示列头)，否则模板导入时，无法对应字段
	 * @param map 下拉框参数，Map的key必须在隐藏列头中
	 */
	@SuppressWarnings("unchecked")
	public void writeExcelTemplate(String sheetName, String[][] headers,Map<String,Object>... map) { 
		if(headers.length != 2){
			throw new RuntimeException("导出模板的headers参数长度必须为2(隐藏列头、显示列头)，否则模板导入时，无法对应字段！");
		}
		
		Font font = workbook.createFont();  
		font.setColor(HSSFColor.DARK_BLUE.index2);  
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体
		font.setFontHeightInPoints((short) 11); //设置字体大小  
		font.setFontName("宋体");  

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		style.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
		style.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
		style.setBorderTop(CellStyle.BORDER_THIN);//上边框    
		style.setBorderRight(CellStyle.BORDER_THIN);//右边框
		style.setFillForegroundColor(HSSFColor.YELLOW.index);// 设置背景色    
		style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
		style.setWrapText(true);//自动换行
		style.setLocked(true);
		style.setFont(font); 
		
		HSSFCell cell = null;
		//HSSFCell cell=null;
		String[] hideHearder = headers[0];
		String[] header = headers[1];

		HSSFSheet sheet = workbook.getSheetAt(start);
		workbook.setSheetName(start, sheetName);
		HSSFSheet hsheet = workbook.getSheetAt(start+rang);
		workbook.setSheetName( start+rang, sheetName+HIDE_SHEET);
		start++;
		
		HSSFRow srow = sheet.createRow(0);  
		HSSFRow hrow = hsheet.createRow(0); 
		for (int i = 0; i < header.length; i++) {
			sheet.setColumnWidth(i, 1000 * header[i].length()); 
			cell =  srow.createCell(i);
			cell.setCellValue(header[i]);
			cell.setCellType( HSSFCell.CELL_TYPE_STRING); //设置单元格格式
			cell.setCellStyle(style); 
			cell =  hrow.createCell(i);
			cell.setCellValue(hideHearder[i]);   
		} 
		for (Map<String,Object> m : map) {
			for (String column : m.keySet()) {
				 Object obj = m.get(column);
				if(obj instanceof String[]){ //一级下拉框
					String[] list = (String[])obj;
					createListComboBox(list, sheet, getIndexOfArray(column, hideHearder));
				} 
			} 
		}
		workbook.setSheetHidden(workbook.getSheetIndex(sheetName), false);
		
	}
	
	/**
	 * excel 模板 某一列值为传入的参数值.
	 *
	 * @param sheetName the sheet name
	 * @param headers the headers
	 * @param map the map
	 */
	@SuppressWarnings("unchecked")
	public void createExcelSheet(String sheetName, String[][] headers,Map<String,Object>... map) { 
		if(headers.length != 2){
			throw new RuntimeException("导出模板的headers参数长度必须为2(隐藏列头、显示列头)，否则模板导入时，无法对应字段！");
		}
		
		Font font = workbook.createFont();  
		font.setColor(HSSFColor.DARK_BLUE.index2);  
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体
		font.setFontHeightInPoints((short) 11); //设置字体大小  
		font.setFontName("宋体");  

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		style.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
		style.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
		style.setBorderTop(CellStyle.BORDER_THIN);//上边框    
		style.setBorderRight(CellStyle.BORDER_THIN);//右边框
		style.setFillForegroundColor(HSSFColor.YELLOW.index);// 设置背景色    
		style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
//		style.setWrapText(true);//自动换行
		style.setLocked(true);
		style.setFont(font); 
		
		Cell cell = null;
		String[] hideHearder = headers[0];
		String[] header = headers[1];

		Sheet sheet = workbook.createSheet();
		workbook.setSheetName(start, sheetName);
		start++;
		
		Row srow = sheet.createRow(0);  
		for (int i = 0; i < header.length; i++) {
			sheet.setColumnWidth(i, 1000 * header[i].length()); 
			cell =  srow.createCell(i);
			cell.setCellValue(header[i]);
			cell.setCellType(Cell.CELL_TYPE_STRING); //设置单元格格式
			cell.setCellStyle(style);
		} 
	 
		for (Map<String,Object> m : map) {
			for (String column : m.keySet()) {
				 Object obj = m.get(column);
				if(obj instanceof String[]){ //一级下拉框
					String[] list = (String[])obj;
					createListComboBox(list, sheet, getIndexOfArray(column, hideHearder));
				} 
			} 
		}
//		workbook.setSheetHidden(workbook.getSheetIndex(sheetName), false);
		
	}
	 
 
   
	/**
	 * Create list combo box.
	 *
	 * @param list the list
	 * @param sheet the sheet
	 * @param colIndex the col index
	 * @return true, if create list combo box
	 */
	private boolean createListComboBox(String[] list, Sheet sheet, int colIndex){
		if(colIndex < 0)
			return false;
		 CellRangeAddressList regions = new CellRangeAddressList(1,999,colIndex,colIndex);  
		 DVConstraint constraint = DVConstraint.createExplicitListConstraint(list);     //生成下拉框内容  
		 DataValidation data_validation = new HSSFDataValidation(regions,constraint);     //绑定下拉框和作用区域  
		 data_validation.createPromptBox("选择提示","请使用下拉方式选择合适的值！");      //设置输入信息提示信息     
		 data_validation.createErrorBox("错误提示","你输入的值未在备选列表中，请下拉选择合适的值！");     //设置输入错误提示信息     
		 sheet.addValidationData(data_validation);   //对sheet页生效  
		 return true;
	}
	  
	 
	/**
	 * Get index of array.
	 *
	 * @param v the v
	 * @param arr the arr
	 * @return the index of array
	 */
	private int getIndexOfArray(String v, String[] arr){
		for (int i = 0; i < arr.length; i++) {
			if(v.equals(arr[i]))
				return i;
		}
		return -1;
	}
	
	/**
	 * 创建多个sheet后，刷新输出流.
	 *
	 * @throws IOException the IO exception
	 */
	public void flush() throws IOException{ 
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
 
	/**
	 * \n 回车( ) \t 水平制表符( ) \s 空格(\u0008) \r 换行( )
	 * 替换全部exp部分.
	 *
	 * @param str the str
	 * @param replacement the replacement
	 * @return the string
	 */
	public static String replaceAll(String str, String replacement) {
		String dest = "";
		if (str != null) {
			Matcher m = Pattern.compile("\\t|\r|\n|\\s").matcher(str);
			dest = m.replaceAll(replacement);
		}
		return dest;
	}
   
}  