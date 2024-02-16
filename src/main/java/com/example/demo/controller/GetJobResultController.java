package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetJobResultController {

	@RequestMapping(value = "/job/result/{jobId}", method = RequestMethod.GET)
	public String getDiscoveryResult(@PathVariable String jobId) {
		try {
			// unable to proceed with this because of unavailability of needful information
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return "STATUS";
   
}
}