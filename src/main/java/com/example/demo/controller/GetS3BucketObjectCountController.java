package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repo.S3BucketRepository;

@RestController
public class GetS3BucketObjectCountController {
	@Autowired
	private S3BucketRepository s3BucketRepository;

	@RequestMapping(value = "/get/count/{bucketName}", method = RequestMethod.GET)
	public Long discoverServices(@PathVariable String bucketName) {
		long count = s3BucketRepository.countByBucketName(bucketName);
		return count;
	}
}
