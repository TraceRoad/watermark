package com.watermark;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class MarkService implements MarkServiceInterface{
/**
 * 文字水印
 */
	public String watermarkString(File image, String fileImageName,
			String uploadPath, String realPath, String markString) {
		Image read=null;
		FileOutputStream outputStream =null;
		try {
			read = ImageIO.read(image);
			int width = read.getWidth(null);
			int height = read.getHeight(null);
			BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2d = image2.createGraphics();
			graphics2d.drawImage(read, 0,0, width, height,null);
			int fontSize = 50;
			Font font = new Font(markString,Font.BOLD,fontSize);
			graphics2d.setFont(font);
			graphics2d.setColor(Color.black);
			graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3F));
			int length=getPostion(markString.length(),markString);
			//setPostion(length,fontSize,width,height,graphics2d,markString);
			setMorePostion(length, fontSize, width, height, graphics2d, markString);
			graphics2d.dispose();
			outputStream = new FileOutputStream(realPath+"\\"+fileImageName);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
			encoder.encode(image2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
	/*
	 * 設置多個文字水印
	 */
	private void setMorePostion(int length, int fontSize, int width, int height ,Graphics2D graphics2d,String markString){
		int fontWidth=fontSize*length;
		int fontHeigh = fontSize;
		for (int h = 1; h < 8; h++) {
			for (int w = 0; w < 3; w++) {
				graphics2d.drawString(markString, 0+3+fontWidth*w, fontHeigh*h);
			}
			
		}
	}
	/*
	 * 設置單個文字水印
	 */
	private void setPostion(int length, int fontSize, int width, int height ,Graphics2D graphics2d,String markString) {
		int fontWidth=fontSize*length;
		int fontHeigh = fontSize;
		int diffWidth = width-fontWidth;
		int diffHeigh = height-fontHeigh;
		int x = diffWidth;
		int y = diffHeigh+fontSize;
		graphics2d.drawString(markString, x, y);
	}
	/*
	 * 獲取字符換的寬度
	 */
	public int getPostion(int length,String markString){
		char[] charArray = markString.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			String valueOf = String.valueOf(c);
			if(valueOf.getBytes().length>1){
				length++;
			}     
		}
		return length=length%2==0?length/1:length/2+1;
	}
	/**
	 *圖片水印 
	 */
	public String watermakrImage(File image, String fileImageName,
			String uploadPath, String realPath, File markImagte) {

		Image read=null;
		FileOutputStream outputStream =null;
		try {
			read = ImageIO.read(image);
			int width = read.getWidth(null);
			int height = read.getHeight(null);
			BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2d = image2.createGraphics();
			graphics2d.drawImage(read, 0,0, width, height,null);
			int fontSize = 50;
			/*Font font = new Font(markString,Font.BOLD,fontSize);
			graphics2d.setFont(font);
			graphics2d.setColor(Color.black);
			graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3F));
			int length=getPostion(markString.length(),markString);
			//setPostion(length,fontSize,width,height,graphics2d,markString);
			setMorePostion(length, fontSize, width, height, graphics2d, markString);
			graphics2d.dispose();*/
			Image waterImage = ImageIO.read(markImagte);
			graphics2d.drawImage(waterImage, width/2, height/2, null);
			graphics2d.dispose();
			outputStream = new FileOutputStream(realPath+"\\"+fileImageName);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
			encoder.encode(image2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
