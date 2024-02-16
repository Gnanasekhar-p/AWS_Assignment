package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DiscoverS3BucketsService;

@RestController
public class GetS3BucketObjectsController {

	@Autowired
	private DiscoverS3BucketsService discoverS3BucketsService;

	@RequestMapping(value = "/get/object/{bucketName}", method = RequestMethod.GET)
	public String discoverServices(@PathVariable String bucketName) throws Exception {
		System.out.println("/get/object/:::::::::::");
		String jobId = discoverS3BucketsService.getObjects(bucketName);
		return jobId;
	}
}
