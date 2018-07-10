package cn.com.hxfz.util;

import java.io.File;
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.PrintWriter;  
import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
  
  
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.filechooser.FileSystemView;  
public class EntityGenerator {
	private static final String DRIVER = "org.postgresql.Driver";  
	private static final String SAVE_CATALOG = "D:\\source_path";  //生成的Java文件存在本地的路径
	private static final String SAVE_PACKAGE = "cn.com.hxfz.model"; //生成的Java文件所属的包路径
	private static final String PGSQL_URL = "jdbc:postgresql://192.168.50.215:5432/hxdb?useUnicode=true&characterEncoding=utf8";
	private static final String PGSQL_USER = "postgres";
	private static final String PGSQL_PASSWORD = "123456";
	private static Connection getConnection = null;  
	  
	  
	public EntityGenerator()  
	{  
	super();  
	getConnection = getConnections();  
	  
	DatabaseMetaData dbmd;  
	try  
	{  
	dbmd = getConnection.getMetaData();  
	ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });  
	//迭代所有的表  
	while(resultSet.next()){  
	//表名  
	String tableName = resultSet.getString("TABLE_NAME");
	//字段信息集合  
	ResultSet colrs = dbmd.getColumns(null, "%", tableName, "%");  
	  
	//创建类文件  
	if (getSavePath() == null) break;  
	String classFilePath = getSavePath() + initialCap(tableName) + ".java";  
	//文件输出流  
	FileWriter fw = new FileWriter(classFilePath);  
	PrintWriter pw = new PrintWriter(fw);  
	//先写package import文本  
	StringBuffer fileBuffer = new StringBuffer();  
	if(SAVE_PACKAGE != null && !SAVE_PACKAGE.equals("")) fileBuffer.append("package " + SAVE_PACKAGE + ";\r\n");  
	  
	//类实体文本  
	StringBuffer contentBuffer = new StringBuffer();  
	//类名称  
	contentBuffer.append("\r\n\r\npublic class " + initialCap(tableName) + "{\r\n");  
	//属性文本  
	StringBuffer attrBuffer = new StringBuffer();  
	//方法文本  
	StringBuffer methodBuffer = new StringBuffer("\r\n");  
	  
	//写入属性 setter和getter方法  
	while(colrs.next()){  
	                    addField(attrBuffer, colrs);  
	                    if (sqlType2JavaType(colrs.getString("TYPE_NAME")).equals("Date"))  
	                    {  
	                    fileBuffer.append("import java.util.Date;\r\n");  
	                    }  
	                    addMethod(methodBuffer, colrs);  
	                }  
	  
	contentBuffer.append(attrBuffer);  
	contentBuffer.append(methodBuffer);  
	contentBuffer.append("}\r\n");  
	  
	fileBuffer.append(contentBuffer);  
	  
	pw.println(fileBuffer.toString());  
	  
	pw.flush();  
	pw.close();  
	  
	}  
	} catch (SQLException | IOException e)  
	{  
	// TODO Auto-generated catch block  
	e.printStackTrace();  
	}  
	          
	}  
	  
	 private static List<String> isFieldList = new ArrayList<String>();  
	    private static List<String> longList = Arrays.asList("bigint", "int8", "bigserial", "serial8");  
	    private static List<String> intList = Arrays.asList("smallint", "int2", "int4","numeric", "serial", "serial4");  
	    private static List<String> doubleList = Arrays.asList("decimal", "real", "float4", "double", "float8");  
	    private static List<String> stringList = Arrays.asList("character", "varchar", "char", "bpchar", "text");  
	    private static List<String> timeList = Arrays.asList("interval", "timestamp", "date", "time");  
	    static {  
	        isFieldList.addAll(longList);  
	        isFieldList.addAll(intList);  
	        isFieldList.addAll(doubleList);  
	        isFieldList.addAll(stringList);  
	        isFieldList.addAll(timeList);  
	    }  
	    
	  
	/** 
	* 建立数据库连接 
	* @return 
	*/  
	public static Connection getConnections()  
	{  
	try  
	{  
	// Properties props =new Properties();  
	// props.put("remarksReporting","true");  
	Class.forName(DRIVER);  
	getConnection = DriverManager.getConnection(PGSQL_URL, PGSQL_USER, PGSQL_PASSWORD);  //DriverManager.getConnection(URL);  
	} catch (Exception e)  
	{  
	e.printStackTrace();  
	}  
	return getConnection;  
	}  
	  
	  
	public static String getSavePath()  
	{  
	String path = null;  
	if(SAVE_CATALOG == null || SAVE_CATALOG.equals(""))  
	{  
	//获取当前用户桌面路径  
	FileSystemView fsv=FileSystemView.getFileSystemView();  
	       path = fsv.getHomeDirectory().toString();  
	  
	}else  
	{  
	path = SAVE_CATALOG;  
	}  
	  
	if(!SAVE_PACKAGE.equals(""))  path = path + "/" + SAVE_PACKAGE.replace(".", "/") + "/";  
	System.out.println(path);  
	  
	//创建目录  
	File dir = new File(path);  
	if (!dir.exists())  
	{  
	if (dir.mkdirs())  
	{  
	System.out.println("创建目录成功！");  
	} else  
	{  
	System.out.println("创建目录失败！");  
	return null;  
	}  
	}  
	  
	return path;  
	  
	}  
	  
	/** 
	* 生成成员属性 
	* @param contentBuffer 
	* @param resultSet 
	* @return 
	* @throws SQLException 
	*/  
	private StringBuffer addField(StringBuffer contentBuffer, ResultSet resultSet) throws SQLException  
	{  
	if (contentBuffer == null || resultSet == null) return contentBuffer;  
	//先写注释  
	contentBuffer.append("\r\n");  
	contentBuffer.append("\t/**\r\n");  
	contentBuffer.append("\t *\t" + resultSet.getString("REMARKS") + "\r\n");  
	contentBuffer.append("\t */\r\n");  
	  
	//写属性  
	contentBuffer.append("\tprivate " + sqlType2JavaType(resultSet.getString("TYPE_NAME")) + " "   
	+ columnName2AttrName(resultSet.getString("COLUMN_NAME")) + ";\r\n");  
	  
	return contentBuffer;  
	}  
	  
	/** 
	* 生成属性setter getter 
	* @param contentBuffer 
	* @param resultSet 
	* @return 
	* @throws SQLException 
	*/  
	private StringBuffer addMethod(StringBuffer contentBuffer, ResultSet resultSet) throws SQLException  
	{  
	if (contentBuffer == null || resultSet == null) return contentBuffer;  
	//属性名  
	String attrName = columnName2AttrName(resultSet.getString("COLUMN_NAME"));  
	//属性名首字母大写  
	String capMethodName = initialCap(attrName);  
	//属性类型  
	String attrType = sqlType2JavaType(resultSet.getString("TYPE_NAME"));  
	  
	contentBuffer.append("\tpublic void set" + capMethodName + "(" + attrType + " " +   
	attrName + "){\r\n");  
	contentBuffer.append("\t\tthis." + attrName + "=" + attrName + ";\r\n");  
	contentBuffer.append("\t}\r\n\r\n");  
	contentBuffer.append("\tpublic " + attrType + " get" + capMethodName + "(){\r\n");  
	contentBuffer.append("\t\treturn " + attrName + ";\r\n");  
	contentBuffer.append("\t}\r\n\r\n");  
	return contentBuffer;  
	}  
	  
	/** 
	* 功能：将输入字符串的首字母改成大写 
	* @param str 
	* @return 
	*/  
	private String initialCap(String str)   
	{  
/*	char[] ch = str.toCharArray();  
	if(ch[0] >= 'a' && ch[0] <= 'z'){  
	ch[0] = (char)(ch[0] - 32);  
	}  
	  
	return new String(ch); */ 
		str = str.replace("tab_","");
		if (str == null) return str;  
		if (str.contains("_"))  
		{  
		char[] ch = str.toCharArray();
		
		if(ch[0] >= 'a' && ch[0] <= 'z'){  
			ch[0] = (char)(ch[0] - 32);  
		}    
		for (int index = 0 ;index < ch.length ; index++)  
		{  
		if (ch[index] == '_' && index > 0 && index < ch.length - 1)  
		{  
		if(ch[index + 1] >= 'a' && ch[index + 1] <= 'z'){  
		ch[index + 1] = (char)(ch[index + 1] - 32);  
		}  
		}  
		}  
		
		return new String(ch).replace("_", "");  
		}  
		char[] chc = str.toCharArray();
		
		if(chc[0] >= 'a' && chc[0] <= 'z'){  
			chc[0] = (char)(chc[0] - 32);  
		}    
		return new String(chc);  
	}  
	  
	/** 
	* 将数据表字段转化成类的属性名称  （下划线后一字符小写转大写，去掉下划线） 
	* @param columnName 
	* @return 
	*/  
	private static String columnName2AttrName(String columnName)  
	{  
	if (columnName == null) return columnName;  
	if (columnName.contains("_"))  
	{  
	char[] ch = columnName.toCharArray();  
	for (int index = 0 ;index < ch.length ; index++)  
	{  
	if (ch[index] == '_' && index > 0 && index < ch.length - 1)  
	{  
	if(ch[index + 1] >= 'a' && ch[index + 1] <= 'z'){  
	ch[index + 1] = (char)(ch[index + 1] - 32);  
	}  
	}  
	}  
	  
	return new String(ch).replace("_", "");  
	}  
	return columnName;  
	}  
	  
	/** 
	* 功能：获得列的数据类型 
	* @param sqlType 
	* @return 
	*/  
	private String sqlType2JavaType(String sqlType) {  
	  
	/*if(sqlType.equalsIgnoreCase("bit")){  
	return "boolean";  
	}else if(sqlType.equalsIgnoreCase("tinyint")){  
	return "byte";  
	}else if(sqlType.equalsIgnoreCase("smallint")){  
	return "short";  
	}else if(sqlType.equalsIgnoreCase("int")){  
	return "int";  
	}else if(sqlType.equalsIgnoreCase("bigint")){  
	return "long";  
	}else if(sqlType.equalsIgnoreCase("float")){  
	return "float";  
	}else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")   
	|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")   
	|| sqlType.equalsIgnoreCase("smallmoney")){  
	return "double";  
	}else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")   
	|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")   
	|| sqlType.equalsIgnoreCase("text")){  
	return "String";  
	}else if(sqlType.equalsIgnoreCase("datetime")){  
	return "Date";  
	}else if(sqlType.equalsIgnoreCase("image")){  
	return "Blod";  
	}  */
		String type = sqlType;
        int idx = type.indexOf("(");  
        if (idx >= 0) {  
            type = type.substring(0, idx);  
        }  
        if (longList.contains(type)) {  
            return "long";  
        } else if (intList.contains(type)) {  
            if ("NUMERIC".equals(type)) {  
                String[] arr = sqlType.replaceAll("NUMERIC", "").replace("(", "").replace(")", "").split(",");  
                if (Integer.parseInt(arr[1]) > 1) {  
                    return "double";  
                } else if (Integer.parseInt(arr[0]) > 5) {  
                    return "long";  
                }  
            }  
            return "Integer";  
        } else if (doubleList.contains(type)) {  
            return "double";  
        } else if (stringList.contains(type)) {  
            return "String";  
        } else if (timeList.contains(type)) {  
            return "Date";  
        }   
        return "";  	
	}  
	  
	public static void main(String[] args)  
	{  
	// TODO Auto-generated method stub  
	new EntityGenerator();  
	}  
	  
	  
	}  