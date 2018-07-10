/*
 * ExcelModel.java
 */
package cn.com.hxfz.util;

import java.io.OutputStream;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

// TODO: Auto-generated Javadoc
/**
 * 导出Excel的数据模型
 * <p>
 * 使用POI实现，不要用于导出大数据量Excel，会有性能问题 创建日期：2012-7-24<br>
 * 修改历史：<br>
 * 修改日期：<br>
 * 修改作者：<br>
 * 修改内容：<br>.
 *
 * @author Atom Group
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class ExcelModel {
	/** The logger. */
	private static Logger logger=Logger.getLogger(ExcelModel.class);

	/** The Constant TYPE_STRING. */
	public static final int TYPE_STRING = 1;
	
	/** The Constant TYPE_INTEGER. */
	public static final int TYPE_INTEGER = 2;
	
	/** The Constant TYPE_NUMBER. */
	public static final int TYPE_NUMBER = 3;
	
	/** The Constant TYPE_PERCENT. */
	public static final int TYPE_PERCENT = 4;
	
	/** The Constant TYPE_MONEY. */
	public static final int TYPE_MONEY = 5;
	
	/** The Constant TYPE_DATE. */
	public static final int TYPE_DATE = 6;
	
	/** The Constant TYPE_TIME. */
	public static final int TYPE_TIME = 7;
	
	/** The Constant TYPE_DATETIME. */
	public static final int TYPE_DATETIME = 8;

	/** 导出的数据. */
	private ArrayList<JSONObject> jsonObjList;

	/** Excel每一列的头，如果没有，就从DBTableObject的column信息中获取. */
	private ArrayList<String[]> columnHeadArr = null;
	
	/** Excel的列名，如果没有，就从DBTableObject的column信息中获取. */
	private ArrayList<String[]> columnNameArr = null;
	
	/** 每一列对应的公共代码Code. */
	private ArrayList<Map<String, String>[]> comCodeMapArr = null;
	
	/** 每一列的数据类型. */
	private ArrayList<int[]> columnTypeArr = null;

	/** The column width arr. */
	private ArrayList<short[]> columnWidthArr = null;

	/** The percent format. */
	private String percentFormat = "0.0%";
	
	/** The number format. */
	private String numberFormat = "0.0";
	
	/** The money format. */
	private String moneyFormat = "￥#,##0";
	
	/** The date formart. */
	private String dateFormart = "yyyy/MM/dd";
	
	/** The time format. */
	private String timeFormat = "hh:mm";
	
	/** The datetime format. */
	private String datetimeFormat = "yyyy/MM/dd hh:mm";
	
	/** The integer format. */
	private String integerFormat = "0";
	
	/** The string format. */
	private String stringFormat = "@";
	
	/** The sheet name. */
	private ArrayList<String> sheetName = new ArrayList<String>();

	/** The work book. */
	private HSSFWorkbook workBook = null;
	
	/** The head cell style. */
	private HSSFCellStyle headCellStyle = null;
	
	/** The string cell style. */
	private HSSFCellStyle stringCellStyle = null;
	
	/** The percent cell style. */
	private HSSFCellStyle percentCellStyle = null;
	
	/** The number cell style. */
	private HSSFCellStyle numberCellStyle = null;
	
	/** The money cell style. */
	private HSSFCellStyle moneyCellStyle = null;
	
	/** The integer cell style. */
	private HSSFCellStyle integerCellStyle = null;
	
	/** The date cell style. */
	private HSSFCellStyle dateCellStyle = null;
	
	/** The time cell style. */
	private HSSFCellStyle timeCellStyle = null;
	
	/** The datetime cell style. */
	private HSSFCellStyle datetimeCellStyle = null;

	/**
	 * 导出成为Excel.
	 *
	 * @param outputFileName the output file name
	 * @param response the response
	 * @throws Exception the exception
	 */
	public void exportExcel(String outputFileName, HttpServletResponse response)
			throws Exception {
		try {
			response.setContentType("application/octet-stream");
			// response.setHeader("Content-Disposition", "attachment;filename="
			// + new String(outputFileName.getBytes("UTF-8"), "ISO-8859-1"));

			response.setHeader("Content-Disposition",
					"attachment; filename=Export.xls");// 设定导出的文件名
			//
			doExport(response.getOutputStream());
		} catch (Exception e) {
			logger.error(e);
			response.reset();
			response.setContentType("text/html; charset=UTF-8");
			String msg = e.getMessage();
			if (msg != null) {
				msg = msg.replaceAll("\"", "'").replaceAll("\r\n",
						" \\\\r\\\\n");
			}
			// String s = "<script>alert(\"导出'" + outputFileName + "'时出错:" + msg
			// + "\");</script>";
			String s = "<script>alert(\"Export error:" + msg + "\");</script>";
			response.getOutputStream().print(s);
			e.printStackTrace();
		} finally {
			response.flushBuffer();
		}

	}

	/**
	 * 准备Excel并导出.
	 *
	 * @param outputStream the output stream
	 * @throws Exception the exception
	 */
	public void doExport(OutputStream outputStream) throws Exception {
		getWorkBook().write(outputStream);
	}

	/**
	 * Get work book.
	 *
	 * @return the work book
	 */
	public HSSFWorkbook getWorkBook() {
		if (workBook == null) {
			equipWorkbook();
		}
		return workBook;
	}

	/**
	 * 装配Excel对象.
	 *
	 * @return Excel对象
	 */
	
	protected void equipWorkbook() {
		// 按照顺序准备列名，列头和列类型和宽度

		/*
		 * prepareColumnName(); prepareColumnHead(); prepareColumnType();
		 * prepareColumnWidth();
		 */

		workBook = new HSSFWorkbook();

		for (int x = 0; x < jsonObjList.size(); x++) {
			/*
			 * String[] columnHeads = columnHeadArr.get(x); String[] columnNames
			 * = columnNameArr.get(x); short[] columnWidths =
			 * columnWidthArr.get(x);
			 */
			String sName = sheetName.get(x);
			HSSFSheet sheet = workBook.createSheet(sName);
			JSONObject jsonObj = (JSONObject) jsonObjList.get(x);
			if (jsonObj.get("maxLength") != null) {
				int rowCount = (Integer) jsonObj.get("maxLength");
				for (int rowIdx = 0; rowIdx <= rowCount; rowIdx++) {
					jsonObj.remove("maxLength");
					Set keySet = jsonObj.keySet();
					Object[] keyValueArr = keySet.toArray();
//					String[] keyValueArr = (String[]) keySet.toArray();
					HSSFRow dataRow = sheet.createRow(rowIdx);
					for (int keyIdx = 0; keyIdx < keyValueArr.length; keyIdx++) {
						Object obj = keyValueArr[keyIdx];						
						String keyValue = String.valueOf(obj);
						if (rowIdx == 0) {
							HSSFCell headCell = dataRow.createCell(keyIdx);
							headCell.setCellValue(keyValue);
							headCell.setCellType(HSSFCell.CELL_TYPE_STRING);
							sheet.setColumnWidth(keyIdx, 2000);
						} else {							
							JSONArray dataList = (JSONArray) jsonObj.get(keyValue);
							String dataValue = "";							
							if (dataList.size() >= rowIdx) {
								dataValue = dataList.get(rowIdx-1).toString();
								
							}
							HSSFCell dataCell = dataRow.createCell(keyIdx);
							dataCell.setCellValue(dataValue);
							dataCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						}
					}
				}
			}
		}
	}

	/**
	 * 根据类型组装Cell.
	 *
	 * @param columnIndex the column index
	 * @param dataRow the data row
	 * @param x the x
	 * @return the HSSF cell
	 */
	protected HSSFCell equipDataCell(int columnIndex, HSSFRow dataRow, int x) {
		int[] columnTypes = columnTypeArr.get(x);
		String[] columnNames = columnNameArr.get(x);
		JSONObject jsonObj = (JSONObject) jsonObjList.get(x);
		Map<String, String>[] comCodeMaps = comCodeMapArr.get(x);
		int type = columnTypes[columnIndex];
		HSSFCell dataCell;
		dataCell = dataRow.createCell(columnIndex);
		String fieldName = columnNames[columnIndex];

		// 根据类别类别创建不同类型的单元格
		switch (type) {
		case TYPE_INTEGER:
			// 整数
			dataCell.setCellStyle(getIntegerCellStyle());
			dataCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			dataCell.setCellValue(jsonObj.getLong(fieldName));
			break;
		case TYPE_NUMBER:
			// 数字
			dataCell.setCellStyle(getNumberCellStyle());
			dataCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			dataCell.setCellValue(jsonObj.getDouble(fieldName));
			break;
		case TYPE_PERCENT:
			// 百分比
			dataCell.setCellStyle(getPercentCellStyle());
			dataCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			dataCell.setCellValue(jsonObj.getDouble(fieldName));
			break;
		case TYPE_MONEY:
			// 金额
			dataCell.setCellStyle(getMoneyCellStyle());
			dataCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			dataCell.setCellValue(jsonObj.getDouble(fieldName));
			break;
		case TYPE_DATE:
			// 日期
			dataCell.setCellStyle(getDateCellStyle());
			dataCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			if ((Date) jsonObj.get(fieldName) == null) {
				dataCell.setCellValue("");
			} else {
				dataCell.setCellValue((Date) jsonObj.get(fieldName));
			}
			break;
		case TYPE_TIME:
			// 时间
			dataCell.setCellStyle(getTimeCellStyle());
			dataCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			if ((Date) jsonObj.get(fieldName) == null) {
				dataCell.setCellValue("");
			} else {
				dataCell.setCellValue((Date) jsonObj.get(fieldName));
			}
			break;
		case TYPE_DATETIME:
			// 日期时间
			dataCell.setCellStyle(getDatetimeCellStyle());
			dataCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			if ((Date) jsonObj.get(fieldName) == null) {
				dataCell.setCellValue("");
			} else {
				dataCell.setCellValue((Date) jsonObj.get(fieldName));
			}
			break;
		default:
			// 字符串
			dataCell.setCellStyle(getStringCellStyle());
			dataCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 值
			String value = jsonObj.getString(fieldName);
			// 公共代码转换
			if (comCodeMapArr != null && comCodeMaps[columnIndex] != null) {
				// 如果存在公共代码Map
				// 通过value获取显示value
				String dispValue = comCodeMaps[columnIndex].get(value);
				if (StringUtils.isNotEmpty(dispValue)) {
					// 如果显示value不为空，那么就替换value
					value = dispValue;
				}
			}
			HSSFRichTextString richString = new HSSFRichTextString(value);
			dataCell.setCellValue(richString);
			// dataCell.setCellValue(value);
			break;
		}
		return dataCell;
	}

	/**
	 * 列头的Style.
	 *
	 * @return the head cell style
	 */
	public HSSFCellStyle getHeadCellStyle() {
		if (headCellStyle != null) {
			return headCellStyle;
		}
		headCellStyle = workBook.createCellStyle();
		// 列头加粗
		HSSFFont headFont = workBook.createFont();
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headCellStyle.setFont(headFont);
		// 设置边缘实线
		headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return headCellStyle;
	}

	/**
	 * 数字的单元格式.
	 *
	 * @return the number cell style
	 */
	public HSSFCellStyle getNumberCellStyle() {
		if (numberCellStyle != null) {
			return numberCellStyle;
		}
		numberCellStyle = workBook.createCellStyle();

		// 设置边缘实线
		numberCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		numberCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		numberCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		numberCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置数据格式
		HSSFDataFormat format = workBook.createDataFormat();
		numberCellStyle.setDataFormat(format.getFormat(numberFormat));
		return numberCellStyle;
	}

	/**
	 * 金额单元格式.
	 *
	 * @return the money cell style
	 */
	public HSSFCellStyle getMoneyCellStyle() {
		if (moneyCellStyle != null) {
			return moneyCellStyle;
		}
		moneyCellStyle = workBook.createCellStyle();

		// 设置边缘实线
		moneyCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		moneyCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		moneyCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		moneyCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置数据格式
		HSSFDataFormat format = workBook.createDataFormat();
		moneyCellStyle.setDataFormat(format.getFormat(moneyFormat));
		return moneyCellStyle;
	}

	/**
	 * 百分比单元格.
	 *
	 * @return the percent cell style
	 */
	public HSSFCellStyle getPercentCellStyle() {
		if (percentCellStyle != null) {
			return percentCellStyle;
		}
		percentCellStyle = workBook.createCellStyle();

		// 设置边缘实线
		percentCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		percentCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		percentCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		percentCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置数据格式
		HSSFDataFormat format = workBook.createDataFormat();
		percentCellStyle.setDataFormat(format.getFormat(percentFormat));
		return percentCellStyle;
	}

	/**
	 * 整数单元格时.
	 *
	 * @return the integer cell style
	 */
	public HSSFCellStyle getIntegerCellStyle() {
		if (integerCellStyle != null) {
			return integerCellStyle;
		}
		integerCellStyle = workBook.createCellStyle();

		// 设置边缘实线
		integerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		integerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		integerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		integerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置数据格式
		HSSFDataFormat format = workBook.createDataFormat();
		integerCellStyle.setDataFormat(format.getFormat(integerFormat));
		return integerCellStyle;
	}

	/**
	 * 日期单元格格式.
	 *
	 * @return the date cell style
	 */
	public HSSFCellStyle getDateCellStyle() {
		if (dateCellStyle != null) {
			return dateCellStyle;
		}
		dateCellStyle = workBook.createCellStyle();

		// 设置边缘实线
		dateCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置数据格式
		HSSFDataFormat format = workBook.createDataFormat();
		dateCellStyle.setDataFormat(format.getFormat(dateFormart));
		return dateCellStyle;
	}

	/**
	 * 时间单元格格式.
	 *
	 * @return the time cell style
	 */
	public HSSFCellStyle getTimeCellStyle() {
		if (timeCellStyle != null) {
			return timeCellStyle;
		}
		timeCellStyle = workBook.createCellStyle();

		// 设置边缘实线
		timeCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		timeCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		timeCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		timeCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置数据格式
		HSSFDataFormat format = workBook.createDataFormat();
		timeCellStyle.setDataFormat(format.getFormat(timeFormat));
		return timeCellStyle;
	}

	/**
	 * 日期时间单元格格式.
	 *
	 * @return the datetime cell style
	 */
	public HSSFCellStyle getDatetimeCellStyle() {
		if (datetimeCellStyle != null) {
			return datetimeCellStyle;
		}
		datetimeCellStyle = workBook.createCellStyle();

		// 设置边缘实线
		datetimeCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		datetimeCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		datetimeCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		datetimeCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置数据格式
		HSSFDataFormat format = workBook.createDataFormat();
		datetimeCellStyle.setDataFormat(format.getFormat(datetimeFormat));
		return datetimeCellStyle;
	}

	/**
	 * 日期时间单元格格式.
	 *
	 * @return the string cell style
	 */
	public HSSFCellStyle getStringCellStyle() {
		if (stringCellStyle != null) {
			return stringCellStyle;
		}
		stringCellStyle = workBook.createCellStyle();

		// 设置边缘实线
		stringCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		stringCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		stringCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		stringCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		HSSFDataFormat format = workBook.createDataFormat();
		stringCellStyle.setDataFormat(format.getFormat(stringFormat));
		return stringCellStyle;
	}

	/**
	 * 准备ColumnNameArr信息.
	 */
	@SuppressWarnings("unchecked")
	protected void prepareColumnName() {
		for (int x = 0; x < columnNameArr.size(); x++) {
			String[] columnNames = columnNameArr.get(x);
			if (columnNames != null && columnNames.length > 0) {
				return;
			}
			JSONObject jsonObj = (JSONObject) jsonObjList.get(x);
			Set<String> keySet = jsonObj.keySet();
			columnNames = new String[keySet.size()];
			for (int i = 0; i < columnNames.length; i++) {
				// 取出所有的column name
				String columnName = (String) keySet.toArray()[i];
				columnNames[i] = columnName;
			}
		}
	}

	/**
	 * 准备ColumnHeadArr信息.
	 */
	protected void prepareColumnHead() {
		if (columnHeadArr != null) {
			return;
		}
		for (int x = 0; x < columnHeadArr.size(); x++) {
			String[] columnHeads = columnHeadArr.get(x);
			String[] columnNames = columnNameArr.get(x);
			if (columnHeads != null && columnHeads.length >= columnNames.length) {
				// 如果存在，并且不比column Name少 则不需要从DBTableOBject中获取
				return;
			}
			/*
			 * DBTableColumn columnInfo = data.get(x).getColInfor(); columnHeads
			 * = new String[columnInfo.size()]; for (int i = 0; i <
			 * columnHeads.length; i++) { // 根据column name取得column head String
			 * columnName = columnHeads[i]; columnHeads[i] =
			 * columnInfo.getDisplayName(columnName); }
			 */
		}
	}

	/**
	 * 准备每一列的类型.
	 */
	protected void prepareColumnType() {
		if (columnTypeArr != null) {
			return;
		}
		for (int x = 0; x < columnTypeArr.size(); x++) {
			int[] columnTypes = columnTypeArr.get(x);
			String[] columnNames = columnNameArr.get(x);
			if (columnTypes != null && columnTypes.length >= columnNames.length) {
				// 如果存在，并且不比column Name少 则不需要从DBTableOBject中获取
				return;
			}
			/*
			 * DBTableColumn columnInfo = data.get(x).getColInfor(); columnTypes
			 * = new int[columnInfo.size()]; for (int i = 0; i <
			 * columnTypes.length; i++) { // 根据column name取得column type String
			 * columnName = columnNames[i]; int sqlType =
			 * columnInfo.getType(columnName); columnTypes[i] =
			 * transType(sqlType); }
			 */
		}
	}

	/**
	 * 准备每一列的宽度.
	 */
	protected void prepareColumnWidth() {
		if (columnWidthArr != null) {
			return;
		}
		columnWidthArr = new ArrayList<short[]>();
		for (int x = 0; x < jsonObjList.size(); x++) {
			int[] columnTypes = columnTypeArr.get(x);
			JSONObject jsonObj = (JSONObject) jsonObjList.get(x);
			Set keySet = jsonObj.keySet();
			short[] columnWidths = new short[keySet.size()];
			for (int i = 0; i < columnTypes.length; i++) {
				// 根据column name取得column type
				int columnType = columnTypes[i];
				short columnWidth = getwidthByType(columnType);
				columnWidths[i] = columnWidth;
			}
			columnWidthArr.add(columnWidths);
		}
	}

	/**
	 * 根据类型设置列宽.
	 *
	 * @param type the type
	 * @return the width by type
	 */
	protected short getwidthByType(int type) {
		switch (type) {
		case TYPE_INTEGER:
			return 2000;
		case TYPE_NUMBER:
			return 2000;
		case TYPE_PERCENT:
			return 2000;
		case TYPE_MONEY:
			return 3000;
		case TYPE_DATE:
			return 3000;
		case TYPE_TIME:
			return 2000;
		case TYPE_DATETIME:
			return 4000;

		default:
			return 4000;
		}
	}

	/**
	 * 将Sql Type 转换为Excel Model Type.
	 *
	 * @param sqlType the sql type
	 * @return the int
	 */
	@SuppressWarnings("unused")
	private int transType(int sqlType) {
		switch (sqlType) {
		case Types.BIGINT:
			return TYPE_INTEGER;
		case Types.DATE:
			return TYPE_DATE;
		case Types.DECIMAL:
			return TYPE_NUMBER;
		case Types.DOUBLE:
			return TYPE_NUMBER;
		case Types.FLOAT:
			return TYPE_NUMBER;
		case Types.INTEGER:
			return TYPE_INTEGER;
		case Types.SMALLINT:
			return TYPE_INTEGER;
		case Types.TIME:
			return TYPE_TIME;
		case Types.TIMESTAMP:
			return TYPE_DATETIME;
		case Types.TINYINT:
			return TYPE_INTEGER;
		default:
			return TYPE_STRING;
		}
	}

	/**
	 * The Constructor.
	 *
	 * @param jsonObjList the json obj list
	 */
	public ExcelModel(ArrayList<JSONObject> jsonObjList) {
		this.jsonObjList = jsonObjList;
	}

	/**
	 * 设置列头名
	 * <p>
	 * 注意需要和列名数量一致 如果不设置，将从数据库表内获取注释.
	 *
	 * @param columnHeadArr the new excel每一列的头，如果没有，就从DBTableObject的column信息中获取
	 */
	public void setColumnHeadArr(ArrayList<String[]> columnHeadArr) {
		this.columnHeadArr = columnHeadArr;
	}

	/**
	 * 设置列名
	 * <p>
	 * 每一列的列名对应数据库字段名.
	 *
	 * @param columnNameArr the new excel的列名，如果没有，就从DBTableObject的column信息中获取
	 */
	public void setColumnNameArr(ArrayList<String[]> columnNameArr) {
		this.columnNameArr = columnNameArr;
	}

	/**
	 * 设置每列的公共代码转换Map
	 * <p>
	 * 注意需要和列名数量一致 Map的key是公共代码CODE，value是公共代码NAME.
	 *
	 * @param comCodeMapArr the new 每一列对应的公共代码Code
	 */
	public void setComCodeMapArr(ArrayList<Map<String, String>[]> comCodeMapArr) {
		this.comCodeMapArr = comCodeMapArr;
	}

	/**
	 * 设置每列的类型
	 * <p>
	 * 注意需要和列名数量一致 如果不设置，将会从数据库内获取字段类型进行转换.
	 *
	 * @param columnTypeArr the new 每一列的数据类型
	 */
	public void setColumnTypeArr(ArrayList<int[]> columnTypeArr) {
		this.columnTypeArr = columnTypeArr;
	}

	/**
	 * 设置每一列宽度
	 * <p>
	 * 注意和列名数量一致 如果不设置，将会根据类型自动设置.
	 *
	 * @param columnWidthArr the column width arr
	 */
	public void setColumnWidthArr(ArrayList<short[]> columnWidthArr) {
		this.columnWidthArr = columnWidthArr;
	}

	/**
	 * 设置百分比格式
	 * <p>
	 * 默认 0.0%
	 *
	 * @param percentFormat the percent format
	 */
	public void setPercentFormat(String percentFormat) {
		this.percentFormat = percentFormat;
	}

	/**
	 * 设置数值格式
	 * <p>
	 * 默认0.0
	 *
	 * @param numberFormat the number format
	 */
	public void setNumberFormat(String numberFormat) {
		this.numberFormat = numberFormat;
	}

	/**
	 * 设置金额格式
	 * <p>
	 * 默认￥#,##0.
	 *
	 * @param moneyFormat the money format
	 */
	public void setMoneyFormat(String moneyFormat) {
		this.moneyFormat = moneyFormat;
	}

	/**
	 * 设置日期格式
	 * <p>
	 * 默认yyyy/m/d.
	 *
	 * @param dateFormart the date formart
	 */
	public void setDateFormart(String dateFormart) {
		this.dateFormart = dateFormart;
	}

	/**
	 * 设置时间格式
	 * <p>
	 * 默认 HH:MM.
	 *
	 * @param timeFormat the time format
	 */
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	/**
	 * 设置日期时间格式
	 * <p>
	 * 默认 yyyy/m/d HH:MM.
	 *
	 * @param datetimeFormat the datetime format
	 */
	public void setDatetimeFormat(String datetimeFormat) {
		this.datetimeFormat = datetimeFormat;
	}

	/**
	 * 设置String的格式
	 * <p>.
	 *
	 * @param stringFormat the string format
	 */
	public void setStringFormat(String stringFormat) {
		this.stringFormat = stringFormat;
	}

	/**
	 * 设置整数格式
	 * <p>
	 * 默认0.
	 *
	 * @param integerFormat the integer format
	 */
	public void setIntegerFormat(String integerFormat) {
		this.integerFormat = integerFormat;
	}

	/**
	 * 设置第一页名称
	 * <p>
	 * 默认Sheet1.
	 *
	 * @param sheetName the sheet name
	 */
	public void setSheetName(ArrayList<String> sheetName) {
		this.sheetName = sheetName;
	}

}
