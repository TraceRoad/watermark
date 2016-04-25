package com.watermark;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class WaterMarkAction extends ActionSupport{
	private String imageFileName;
	private File image;
	private String uploadPath;
	private PicInfo picInfo=new PicInfo();
	private String markString = "智网达";
	public String watermark()throws Exception{
		String realPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
		HttpServletRequest request = ServletActionContext.getRequest();
		String realPath2 = request.getSession().getServletContext().getRealPath("/images/intumit_logo.png");
		File fileImage = new File(realPath2);
		//UploadService uploadService = new UploadService();
		//picInfo.setImageURL(uploadService.uploadImage(image, imageFileName, uploadPath, realPath));
		MarkService service = new MarkService();
		/**
		 * 文字水印
		 * 
		 * picInfo.setImageURL(service.watermarkString(image, imageFileName, uploadPath,realPath, markString));
		 */
		/**
		 * 圖片水印
		 */
		picInfo.setImageURL(service.watermakrImage(image, imageFileName, uploadPath, realPath, fileImage));
		return SUCCESS;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public PicInfo getPicInfo() {
		return picInfo;
	}
	public void setPicInfo(PicInfo picInfo) {
		this.picInfo = picInfo;
	}
	
}
