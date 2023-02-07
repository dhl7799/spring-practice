package com.douzone.fileupload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String SAVE_PATH = "/fileupload-files";
	private static String URL_PATH = "/images";

	public String restore(MultipartFile file) {
		String url = null;
		
		try {
			
			File uploadDirectory = new File(SAVE_PATH);
			if(!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
			}
			if (file.isEmpty()) {
				return url;
			}

			String originalFileName = file.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String saveFileName = generateSaveFileName(extName);
			Long filesize = file.getSize();
			System.out.println("################# " + originalFileName);
			System.out.println("################# " + saveFileName);
			System.out.println("################# " + filesize);

			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
			os.write(data);
			os.close();
			
			url = URL_PATH + "/" + saveFileName;
			
		} catch (IOException e) {
			throw new FileuploadServiceException(e.toString());
		}

		return url;
	}

	private String generateSaveFileName(String extName) {
		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		return filename;
	}

}
