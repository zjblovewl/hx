/**  
 * @类功能说明：导入Excel文档工具类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年3月15日 下午15:45:00   
 * @版本：V1.0  
 */
package cn.com.hxfz.util;

/** 
 * Jun 25, 2012 
 */  
  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.text.DecimalFormat;
import java.util.ArrayList;  
import java.util.List;  
  
import org.apache.commons.io.FilenameUtils;  
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.CellValue;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
  
/**
 * Excel组件.
 *
 * @version 1.0
 * @since 1.0
 */  
public class ExcelHelper {  
  
    /** Excel 2003. */  
    private static final  String XLS = "xls";  
    
    /** Excel 2007. */  
    private static final String XLSX = "xlsx";  
    
    /** 分隔符. */  
    private static final String SEPARATOR = "|";  
  
    /**
     * 由Excel文件的Sheet导出至List.
     *
     * @param file the file
     * @param sheetNum the sheet num
     * @return the list< string>
     * @throws IOException the IO exception
     */  
    public static List<String> exportListFromExcel(File file, int sheetNum)  
            throws IOException {  
        return exportListFromExcel(new FileInputStream(file),  
                FilenameUtils.getExtension(file.getName()), sheetNum);  
    }  
  
    /**
     * 由Excel流的Sheet导出至List.
     *
     * @param is the is
     * @param extensionName the extension name
     * @param sheetNum the sheet num
     * @return the list< string>
     * @throws IOException the IO exception
     */  
    public static List<String> exportListFromExcel(InputStream is,  
            String extensionName, int sheetNum) throws IOException {  
  
        Workbook workbook = null;  
  
        if (extensionName.toLowerCase().equals(XLS)) {  
            workbook = new HSSFWorkbook(is);  
        } else if (extensionName.toLowerCase().equals(XLSX)) {  
            workbook = new XSSFWorkbook(is);  
        }  
  
        return exportListFromExcel(workbook, sheetNum);  
    }  
  
    /**
     * 由指定的Sheet导出至List.
     *
     * @param workbook the workbook
     * @param sheetNum the sheet num
     * @return the list< string>
     */  
    private static List<String> exportListFromExcel(Workbook workbook,  
            int sheetNum) {  
  
        Sheet sheet = workbook.getSheetAt(sheetNum);  
  
        // 解析公式结果  
        FormulaEvaluator evaluator = workbook.getCreationHelper()  
                .createFormulaEvaluator();  
  
        List<String> list = new ArrayList<String>();  
  
        int minRowIx = sheet.getFirstRowNum();  
        int maxRowIx = sheet.getLastRowNum();  
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {  
            Row row = sheet.getRow(rowIx);  
            StringBuilder sb = new StringBuilder();  
  
            short minColIx = row.getFirstCellNum();  
            short maxColIx = row.getLastCellNum();  
            for (short colIx = minColIx; colIx <= maxColIx; colIx++) {  
                Cell cell = row.getCell(new Integer(colIx));  
                CellValue cellValue = evaluator.evaluate(cell);  
                if (cellValue == null) {  
                	sb.append(SEPARATOR+"");
                    continue;  
                }  
                // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了  
                // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html  
                switch (cellValue.getCellType()) {  
                case Cell.CELL_TYPE_BOOLEAN:  
                    sb.append(SEPARATOR + cellValue.getBooleanValue());  
                    break;  
                case Cell.CELL_TYPE_NUMERIC:  
                    // 这里的日期类型会被转换为数字类型，需要判别后区分处理  
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {  
                        sb.append(SEPARATOR + cell.getDateCellValue());  
                    } else {  
                        //sb.append(SEPARATOR + cellValue.getNumberValue());
                    	//这里的整数值类型返回 会带小数点 ".0",所以new DecimalFormat("0").format
                        sb.append(SEPARATOR + new DecimalFormat("#.00").format(cellValue.getNumberValue()));              
                    }  
                    break;  
                case Cell.CELL_TYPE_STRING:  
                    sb.append(SEPARATOR + cellValue.getStringValue());  
                    break;  
                case Cell.CELL_TYPE_FORMULA:  
                    break;  
                case Cell.CELL_TYPE_BLANK:  
                    break;  
                case Cell.CELL_TYPE_ERROR:  
                    break;  
                default:  
                    break;  
                }  
            }  
            list.add(sb.toString());  
        }  
        return list;  
    }  
}  
