package com.watermark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadService {
	public String uploadImage(File image,String fileImageName,String uploadPath,String realPath){
		FileInputStream inputStream=null;
		FileOutputStream outputStream=null;
		try {
			inputStream = new FileInputStream(image);
			outputStream = new FileOutputStream(realPath+"\\"+fileImageName);
			byte [] buff = new byte[2014];
			
			while(true){
				int read = inputStream.read(buff);
				if(read==-1)break;
				outputStream.write(buff);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return uploadPath+"\\"+fileImageName;
	}
}
