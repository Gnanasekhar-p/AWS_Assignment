package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.example.demo.entity.BucketFileData;
import com.example.demo.entity.S3BucketData;
import com.example.demo.repo.BucketDataRepository;
import com.example.demo.repo.S3BucketRepository;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class DiscoverS3BucketsService {

	@Autowired
	private S3BucketRepository bucketRepository;
	
	@Autowired
	private BucketDataRepository bucketDataRepo;

	@SuppressWarnings("deprecation")
	public List<Bucket> getS3BucketsList() throws Exception {

		AmazonS3Client s3Client = new AmazonS3Client(new AWSStaticCredentialsProvider(AWS_CREDENTIALS));
		List<Bucket> listBuckets = s3Client.listBuckets();
		List<S3BucketData> list = new ArrayList<>();
		for (Bucket bucket : listBuckets) {
			S3BucketData s3BucketData = new S3BucketData();
			s3BucketData.setBucketName(bucket.getName());
			s3BucketData.setCreationDate(bucket.getCreationDate());
			s3BucketData.setOwnerName(bucket.getOwner().getDisplayName());
			s3BucketData.setOwnerId(bucket.getOwner().getId());
			list.add(s3BucketData);
		}
		return listBuckets;
	}

	public String getS3BucketsData() throws Exception {
		@SuppressWarnings("deprecation")
		AmazonS3Client s3Client = new AmazonS3Client(new AWSStaticCredentialsProvider(AWS_CREDENTIALS));
		s3Client.listBuckets();
		List<Bucket> listBuckets = s3Client.listBuckets();
		List<S3BucketData> list = new ArrayList<>();
		for (Bucket bucket : listBuckets) {
			S3BucketData s3BucketData = new S3BucketData();
			s3BucketData.setBucketName(bucket.getName());
			s3BucketData.setCreationDate(bucket.getCreationDate());
			s3BucketData.setOwnerName(bucket.getOwner().getDisplayName());
			s3BucketData.setOwnerId(bucket.getOwner().getId());
			list.add(s3BucketData);
		}
		bucketRepository.saveAll(list);
		return UUID.randomUUID().toString();
	}

	public String getObjects(String bucketName) {
		String accessKey = "AKIAX5XSI5UTUOMGM5F3";
		String secretKey = "0eWSBl7u3R4kMlACn4YgoyJGyIttks03ILWL/x+r";
		String region = "ap-south-1"; // Change this to your desired region

		// Create an S3 client
		S3Client s3Client = S3Client.builder().region(Region.of(region))
				.credentialsProvider(software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
						.create(AwsBasicCredentials.create(accessKey, secretKey)))
				.build();

		listObjects(s3Client, bucketName);
		return UUID.randomUUID().toString();
	}

	public  void listObjects(S3Client s3Client, String bucketName) {
		try {
			ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(
					software.amazon.awssdk.services.s3.model.ListObjectsV2Request.builder().bucket(bucketName).build());
			List<BucketFileData> list=new ArrayList<BucketFileData>();
			// Iterate through the objects and print their keys (file names)
			for (S3Object s3Object : listObjectsResponse.contents()) {
				BucketFileData bucketFileData = new BucketFileData();
				bucketFileData.setBucketName(bucketName);
				bucketFileData.setFiles(s3Object.key());
				list.add(bucketFileData);
				System.out.println("File Name: " + s3Object.key());
			}
			bucketDataRepo.saveAll(list);
		} catch (S3Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static final AWSCredentials AWS_CREDENTIALS;

	static {

		// Your accesskey and secretkey

		AWS_CREDENTIALS = new BasicAWSCredentials(

				"AKIAX5XSI5UTUOMGM5F3",

				"0eWSBl7u3R4kMlACn4YgoyJGyIttks03ILWL/x+r"

		);

	}

}
