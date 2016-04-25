package com.watermark;

import java.io.File;

public interface MarkServiceInterface {
	public String watermarkString(File image,String fileImageName,String uploadPath,String realPath,String markString);
	public String watermakrImage(File image,String fileImageName,String uploadPath,String realPath,File markImagte);
}
