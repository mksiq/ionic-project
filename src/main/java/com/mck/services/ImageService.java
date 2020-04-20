package com.mck.services;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mck.services.exceptions.FileException;



@Service
public class ImageService {
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(ext) && !"jpg".equals(ext) && !"jpeg".equals(ext)) {
			throw new FileException("Only jpeg, jpg, and png image extensions are accepted");
		}
		
		BufferedImage img;
		
		try {
			img = ImageIO.read(uploadedFile.getInputStream());
			if("png".equals(ext)) {
				img = pngToJpg(img);
			}
			
			return img;
		} catch (IOException e) {
			throw new FileException("Error on reading file");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch ( IOException e) {
			throw new FileException("Error at reading file");
		}
	}
	
//	public BufferedImage cropSquare(BufferedImage sourceImg) {
//		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
//		return Scalr.crop(sourceImg, (sourceImg.getWidth()/2) - (min/2), (sourceImg.getHeight()/2) - (min/2), min, min);
//	}
//	
//	public BufferedImage resize(BufferedImage sourceImg, int size) {
//		return Scalr.resize(sourceImg, Scalr.Method.QUALITY, size);
//	}
	
	public BufferedImage resize(BufferedImage sourceImg, int size) {
		
		   int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		   double percent = (double) size / (double) min;
	       int scaledWidth = (int) (sourceImg.getWidth() * percent);
	       int scaledHeight = (int) (sourceImg.getHeight() * percent);	       
	       BufferedImage outputImage = new BufferedImage(scaledWidth,
	                scaledHeight, sourceImg.getType());
	       Graphics2D g2d = outputImage.createGraphics();
	       g2d.drawImage(sourceImg, 0, 0, scaledWidth, scaledHeight, null);
	       g2d.dispose();
	       
	       return outputImage;

}	
}
