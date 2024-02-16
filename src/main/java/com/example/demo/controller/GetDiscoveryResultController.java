package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.Bucket;
import com.example.demo.service.DiscoverEC2InstancesService;
import com.example.demo.service.DiscoverS3BucketsService;

@RestController
public class GetDiscoveryResultController {
	@Autowired
	private DiscoverS3BucketsService amazonS3BucketsService;

	@Autowired
	private DiscoverEC2InstancesService discoverEC2InstancesService;

	@RequestMapping(value = "/discover/result/{service}", method = RequestMethod.GET)
	public List<?> getDiscoveryResult(@PathVariable String service) throws Exception {

		if (service.equalsIgnoreCase("S3")) {
			List<Bucket> listBuckets = amazonS3BucketsService.getS3BucketsList();
			return listBuckets;
		} else if (service.equalsIgnoreCase("EC2")) {
			List<String> discoverEC2Service = discoverEC2InstancesService.discoverEC2Service();
			return discoverEC2Service;
		}
		return null;
	}
}
