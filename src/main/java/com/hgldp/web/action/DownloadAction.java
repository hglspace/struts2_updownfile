package com.hgldp.web.action;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgldp.web.utils.EncodingFileNameUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class DownloadAction extends ActionSupport{

	
	private String filename;
	
	
	public void setFilename(String filename) throws UnsupportedEncodingException {
		this.filename = new String(filename.getBytes("iso8859-1"),"utf-8");
	}


	@Override
	public String execute() throws Exception {

		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		//将文件的mime类型存入值栈，然后在struts.xml中配置的文件类型从值栈中获取
		ActionContext.getContext().put("contentType", mimeType);
		//获取浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("user-agent");
		//通过浏览器类型对需要下载的附件名称进行编码
		String encodeDownloadFilename = EncodingFileNameUtil.encodeDownloadFilename(filename,agent);
		//设置文件的下载方式，attachment:以附件形式进行下载，filename：下载的文件名
		String contentDisposition = "attachment;filename="+encodeDownloadFilename;
		//将下载方式存入值栈，然后在struts.xml中配置文件下载类型的时候，从值栈中获取下载类型
		ActionContext.getContext().put("contentDisposition", contentDisposition);
		//提供一个下载文件的输入流来读取需要下载的文件
		ActionContext.getContext().put("inputStream", new FileInputStream("/Users/zyq/Documents/image/"+filename));
		return SUCCESS;
	}

	
}
