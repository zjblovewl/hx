package com.bie.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
 
import javax.servlet.http.HttpServlet;
 
/**
 * 统计访问量
 * @author sun
 *
 */
public class Counter extends HttpServlet{
 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 写入文件的方法
	 * @param filename
	 * @param count
	 */
	public static void writeFile(String filename,long count){
		try{
			PrintWriter out=new PrintWriter(new FileWriter(filename));
			out.println(count);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 读文件的方法
	 * @param filename
	 * @return
	 */
	public static long readFromFile(String filename){
		File file=new File(filename);
		long count=0;
		if(!file.exists()){
			try{
				file.createNewFile();
			}catch(Exception e){
				e.printStackTrace();
			}
			writeFile(filename,0);
		}
		
		try{
			BufferedReader in=new BufferedReader(new FileReader(file));
			try{
				count=Long.parseLong(in.readLine());
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		return count;
	}
 
}
