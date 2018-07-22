package com.hgldp.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Namespace(value="/")
@ParentPackage(value="struts-default")
@Controller
@Results({@Result(name="success",location="/upload/success.jsp")})
@Scope("prototype")
public class UploadAction extends ActionSupport{

	private File upload;  //上传的文件资源，和form表单中的上传标签的name属性名一致
	private String uploadContentType; // 文件类型
	private String uploadFileName; //文件名称
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	@Action(value="up_load")
	public String upload() throws IOException{
		byte[] b = new byte[1024];
		int len;
		System.out.println(uploadFileName);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(upload));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("/Users/zyq/Documents/image/"+uploadFileName));
		while((len=bis.read(b))!=-1){
			bos.write(b, 0, len);
		}
		bis.close();
		bos.close();
		return SUCCESS;
	}
}
