package com.mck.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mck.services.exceptions.FileException;


@Service
public class S3Service {
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	@Autowired
	private AmazonS3 s3client;
	@Value("${s3.bucket}")
	private String bucketName;
	public URI uploadFile(MultipartFile multipartFile) {
		try {

			String fileName = multipartFile.getOriginalFilename();
			InputStream is;
				is = multipartFile.getInputStream();
				String contentType = multipartFile.getContentType();
				return uploadFile(is, fileName, contentType);
			} catch (IOException e) {
				throw new FileException("IO Error" + e.getMessage());
			}
	}
	
	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
		
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType(contentType);
		LOG.info("Beginning upload");
		s3client.putObject(bucketName, fileName, is, meta);	
		
		LOG.info("Completed upload");
//	} catch (AmazonServiceException e) {
//		LOG.info("AmazonServiceException: " + e.getMessage());
//		LOG.info("Status code: " + e.getErrorCode());
//	} catch (AmazonClientException e) {
//		LOG.info("AmazonClientException: " + e.getMessage());
//		
//	}
		
		return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Error while converting URL to URI");
		}
	}
}
