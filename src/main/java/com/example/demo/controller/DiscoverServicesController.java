package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DiscoverEC2InstancesService;
import com.example.demo.service.DiscoverS3BucketsService;

@RestController
public class DiscoverServicesController {

	@Autowired
	private DiscoverEC2InstancesService discoverEC2InstancesService;

	@Autowired
	private DiscoverS3BucketsService discoverS3BucketsService;

	@RequestMapping(value = "/discover/services", method = RequestMethod.POST)
	public String discoverServices(@RequestBody List<String> services) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		String jobId=null;
		try {
			// Submit tasks for processing each service
			for (String service : services) {
				if (service.equalsIgnoreCase("EC2")) {
			executorService.submit(() -> discoverEC2InstancesService.discoverEC2Service());
				}
				else if(service.equalsIgnoreCase("S3")){
					Future<String> s3Job = executorService.submit(() -> discoverS3BucketsService.getS3BucketsData());
					jobId=s3Job.get();
				}
				else {
					System.out.println("Invalid service");
					return "Invalid service";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
		return jobId;
	}
}
