package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Reservation;

@Service
public class DiscoverEC2InstancesService {

	public List<String> discoverEC2Service() {
		System.out.println("discovering the ec2 instances:::::::::::");
		AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard()

				.withCredentials(new AWSStaticCredentialsProvider(AWS_CREDENTIALS))

				.withRegion(Regions.AP_SOUTH_1) // Specify the region the instance should be launched in

				.build();

		// Describe instances in the specified region
		DescribeInstancesRequest request = new DescribeInstancesRequest();

		DescribeInstancesResult result = ec2Client.describeInstances(request);
		List<String> instanceList = new ArrayList<>();
		// Process the list of reservations containing instances
		for (Reservation reservation : result.getReservations()) {
			for (com.amazonaws.services.ec2.model.Instance instance : reservation.getInstances()) {
				System.out.println("Instance ID: " + instance.getInstanceId());
				instanceList.add(instance.getInstanceId());
			}
		}
		return instanceList;
	}

	private static final AWSCredentials AWS_CREDENTIALS;

	static {

		AWS_CREDENTIALS = new BasicAWSCredentials(

				"AKIAX5XSI5UTUOMGM5F3",

				"0eWSBl7u3R4kMlACn4YgoyJGyIttks03ILWL/x+r"

		);

	}

}
