/**     
 * @文件名称: ExcelExportUtile.java  
 * @类路径: 	cn.com.hxfz.util  
 * @描述: 	使用Excel导出页面列表数据 
 * @作者：	cxl
 * @时间：	2017年7月24日 上午09:48:29  
 * @版本：V1.0     
 */
package cn.com.hxfz.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * 使用Excel导出页面列表数据
 * 
 * @author cxl
 */
public class ExcelExportUtile {
	// 第一步，创建一个webbook，对应一个Excel文件
	public HSSFWorkbook generateExcel() {
		return new HSSFWorkbook();
	}

	@SuppressWarnings("deprecation")
	public HSSFWorkbook generateSheet(HSSFWorkbook wb, String sheetName,
			String[] fields, String [] keys, List<Map<String, Object>> list) {

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		for(int i=0;i<keys.length;i++){
			sheet.setColumnWidth((short) i, (short) (35.7 *200));
		}

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头设置表头居中（style）,设置每一行样式（style2）
		Font f = wb.createFont();//创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 11);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(f);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 创建一个居中格式
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle style2 = wb.createCellStyle();
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setWrapText(true);

		// 设置表头字段名
		HSSFCell cell;

		int m = 0;
		for (String fieldName : fields) {
			cell = row.createCell(m);
			cell.setCellValue(fieldName);
			cell.setCellStyle(style);
			m++;
		}
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			Map<String, Object> data = list.get(i);
			// 第五步，创建单元格，并设置值
			for(int j=0;j<keys.length;j++){
				cell=row.createCell(j);
				cell.setCellValue(data.get(keys[j]) == null?" ": data.get(keys[j]).toString());
				cell.setCellStyle(style2);
			}
		}
		return wb;
	}

	public void export(HSSFWorkbook wb,String sheetName,HttpServletResponse response) {
		// 第六步，实现文件下载保存
		try {
			response.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode(sheetName, "utf-8") + ".xls");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
