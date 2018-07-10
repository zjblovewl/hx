package cn.com.hxfz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

// TODO: Auto-generated Javadoc
/**
 * Execl写入功能,每次写入一个sheet页，所有sheet写入完成后，必须调用flush()方法Workbook。.
 *
 * @author liumm
 */
public class ExeclExportProcesser {
	/** The logger. */
	private static Logger logger=Logger.getLogger(ExeclExportProcesser.class);	
	
	/** The output stream. */
	private OutputStream outputStream;

	/** The write workbook. */
	private HSSFWorkbook writeWorkbook;;

	/**
	 * The Constructor.
	 *
	 * @param output the output
	 */
	public ExeclExportProcesser(OutputStream output) {
		this.outputStream = output;
		writeWorkbook = new HSSFWorkbook();
	}

	/**
	 * 按sheet页写入Execl.
	 *
	 * @param sheetNum sheet下标，从0开始
	 * @param sheetName sheet名称
	 * @param data 要写入Execl的数据
	 * @param hasHideHeader true表示data[0]是隐藏header,data[1]是显示的header;
	 * false表示data[0]就是显示的header
	 */
	public void writeExcelSheet(int sheetNum, String sheetName, String[][] data, boolean hasHideHeader) {
		HSSFSheet sheet = writeWorkbook.createSheet();
		writeWorkbook.setSheetName(sheetNum, sheetName);
		HSSFRow row = null;
		HSSFCell cell = null;

		Font font = writeWorkbook.createFont();
		font.setColor(HSSFColor.DARK_BLUE.index2);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体

		HSSFCellStyle style = writeWorkbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setFillForegroundColor(HSSFColor.YELLOW.index);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFont(font);

		HSSFCellStyle style2 = writeWorkbook.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		int begin = 0;
		if (hasHideHeader && data.length > 0) {
			row = sheet.createRow(begin);
			row.setZeroHeight(true);
			String[] hideHearder = data[begin];
			for (int i = 0; i < hideHearder.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(hideHearder[i]);
			}
			begin++;
		}
		if (begin < data.length) {
			row = sheet.createRow(begin);
			String[] header = data[begin];
			for (int i = 0; i < header.length; i++) {
				sheet.setColumnWidth(i, 640 * header[i].length());
				cell = row.createCell(i);
				cell.setCellValue(header[i]);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置单元格格式
				cell.setCellStyle(style);
			}
		}

		for (int i = begin + 1; i < data.length; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < data[i].length; j++) {
				cell = row.createCell(j);
				cell.setCellValue(data[i][j]);
				cell.setCellStyle(style2);
			}
		}
	}
	
	/**
	 * Write excel sheet.
	 *
	 * @param sheetNum the sheet num
	 * @param sheetName the sheet name
	 * @param data the data
	 * @param header the header
	 */
	public void writeExcelSheet(int sheetNum, String sheetName, List<Map<String,Object>> data, String[][] header) {
		HSSFSheet sheet = writeWorkbook.createSheet();
		writeWorkbook.setSheetName(sheetNum, sheetName);
		HSSFRow row = null;
		HSSFCell cell = null;

		Font font = writeWorkbook.createFont();
		font.setColor(HSSFColor.DARK_BLUE.index2);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体

		HSSFCellStyle style = writeWorkbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setFillForegroundColor(HSSFColor.YELLOW.index);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFont(font);

		HSSFCellStyle style2 = writeWorkbook.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		//设置表头
		row = sheet.createRow(0);
		row.setZeroHeight(true);
		for(int i=0;i<header.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(header[1][i]);
		}
		for(int j=0;j<data.size();j++){
			int k=j+1;
			for(int i=0;i<header.length;i++){
				cell = row.createCell(k);
				cell.setCellValue(header[0][i]);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置单元格格式
				cell.setCellStyle(style);
			}
		}
		writeWorkbook.setSheetHidden(writeWorkbook.getSheetIndex(sheetName), false);
	}

	/**
	 * 创建多个sheet后，刷新输出流.
	 *
	 * @throws IOException the IO exception
	 */
	public void flush() throws IOException {
		writeWorkbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * Result set to excel.
	 *
	 * @param sheetName the sheet name
	 * @param rs the rs
	 * @throws Exception the exception
	 */
	public void resultSetToExcel(String sheetName, ResultSet rs) throws Exception {
		HSSFSheet sheet = writeWorkbook.createSheet();
		writeWorkbook.setSheetName(0, sheetName);

		Font font = writeWorkbook.createFont();
		font.setColor(HSSFColor.DARK_BLUE.index2);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体

		HSSFCellStyle style = writeWorkbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setFillForegroundColor(HSSFColor.YELLOW.index);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFont(font);

		HSSFCellStyle style2 = writeWorkbook.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		ResultSetMetaData md = rs.getMetaData();

		HSSFRow row = sheet.createRow(0);
		String header = "";
		for (int i = 0; i < md.getColumnCount(); i++) {
			header = md.getColumnName(i);
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(header);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置单元格格式
			cell.setCellStyle(style);
			sheet.setColumnWidth(i, 640 * header.length());

		}

		for (int i = 0; rs.next(); i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < md.getColumnCount(); j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(rs.getObject(j).toString());
				cell.setCellStyle(style2);

			}
		}
		flush();
	}

	/**
	 * Down load export excel.
	 *
	 * @param request the request
	 * @param response the response
	 * @param fileFullName the file full name
	 * @param fileName the file name
	 */
	@SuppressWarnings("resource")
	public void downLoadExportExcel(HttpServletRequest request, HttpServletResponse response, String fileFullName, String fileName) {
		FileInputStream in = null;
		try {
			response.reset();
			response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			response.setContentType("application/x-excel");
			File fileLoad = new File(fileFullName);
			long fileLength = fileLoad.length();
			String length = String.valueOf(fileLength);
			response.setHeader("Content_Length", length);
			in = new FileInputStream(fileLoad);
			int n = 0;
			byte b[] = new byte[1024];
			OutputStream o = response.getOutputStream();
			while ((n = in.read(b)) != -1) {
				o.write(b, 0, n);
			}
		} catch (Exception ex) {
			logger.error(ex);
			System.out.println("导出excel错误,详细----->" + ex.getMessage());
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
					e.printStackTrace();
				}				
			}
		}
	}
}
