package com.bie.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.woke.context.WeiXinApplicationContext;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class GuangGAction implements Action, ServletRequestAware, ServletResponseAware, ServletContextAware {
	private HttpServletRequest request;
	private String msg;
	private String msg1;
	private String msg2;
	private String msg3;
	private String title;
	private String centent;
	private String datetime;
	private String img;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getMsg3() {
		return msg3;
	}

	public void setMsg3(String msg3) {
		this.msg3 = msg3;
	}

	public String getMsg2() {
		return msg2;
	}

	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCentent() {
		return centent;
	}

	public void setCentent(String centent) {
		this.centent = centent;
	}

	public String getMsg1() {
		return msg1;
	}

	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	

	/**
	 * �ļ���ȡ�ļ�FileInputStream��ʹ��
	 * 
	 * @throws IOException
	 */

	public String FaBu() throws IOException {
		
		
		
		
		
		
		String url="http://5f4ssm.natappfree.cc/WoKe/index.jsp?id="+id;
		request.setAttribute("url", WeiXinApplicationContext.DOMAIN_URL);
		
		
		
		
		
		
		
		
		
		/*File file = new File("F:/java工程/WoKe/WebContent/index.jsp");
		if (file.isFile()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				
				byte[] buf = new byte[1024];
				StringBuffer sb = new StringBuffer();
			
				int len = 0;
				while ((len = fis.read(buf)) != -1) {
					
					sb.append(new String(buf, 0, len, "utf-8"));
				}
			
				String index = sb.toString();
				// System.out.println(index);
				String text = index;
				BufferedWriter output = null;
				String filename = "index"+id ;
				String FileN = filename + ".jsp";
				try {
					File file1 = new File("F:/java工程/WoKe/WebContent/" + FileN + "");
					if(file1.exists()) {
						
					}else {
						file1.mkdir();
					}
					output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));
					output.write(text);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (output != null)
						output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("发布成功");
		}
		System.out.println("img"+img);
		System.out.println("datetime:"+datetime);*/
		return "GuangGlist";

	}
	
	public String QuXFaBu() throws Exception{
		String filename="inde"+id;
		String FileN=filename+".jsp";
		System.out.println("ɾ���ļ�����:"+FileN);
		delete(new File("E:\\eclipse-workspace\\WoKe\\WebContent\\" + FileN + ""));
		request.setAttribute("url", WeiXinApplicationContext.DOMAIN_URL);	
		return "GuangGlist";
	}
	
	public static void delete(File f) {
		//System.out.println(f);
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for (File file : files) {
				delete(file);
				file.delete();
			}
		}
		f.delete();
	}
	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	 
	} 


