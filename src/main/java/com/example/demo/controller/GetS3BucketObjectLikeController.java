package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.S3BucketData;
import com.example.demo.repo.S3BucketRepository;

@RestController
public class GetS3BucketObjectLikeController {
	@Autowired
	private S3BucketRepository s3BucketRepository;

	@RequestMapping(value = "/get/object/like/{bucketName}", method = RequestMethod.GET)
	public List<S3BucketData> discoverServices(@PathVariable String bucketName) {
		List<S3BucketData> s3BucketData =s3BucketRepository.findByBucketNameContaining(bucketName);
		System.out.println("s3"+s3BucketData.size());
		return s3BucketData;
	}

}
