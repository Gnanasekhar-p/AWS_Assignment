package com.example.demo.service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glacier.GlacierClient;
import software.amazon.awssdk.services.glacier.model.DescribeJobRequest;
import software.amazon.awssdk.services.glacier.model.DescribeJobResponse;

public class GlacierJobStatusChecker {

    public static void main(String[] args) {
        // Specify the AWS region
        Region region = Region.US_EAST_1; // Change this to your desired region

        // Specify your AWS access key and secret key
        String accessKey = "your-access-key";
        String secretKey = "your-secret-key";

        // Create Glacier client with explicit credentials
        GlacierClient glacierClient = GlacierClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();

        // Specify the Job ID you want to check
        String jobId = "your-job-id"; // Replace with the actual Job ID

        // Check the status of the job
        JobStatus jobStatus = getJobStatus(glacierClient, jobId);
        System.out.println("Job Status: " + jobStatus);
    }

    private static JobStatus getJobStatus(GlacierClient glacierClient, String jobId) {
        DescribeJobResponse describeJobResponse = glacierClient.describeJob(DescribeJobRequest.builder()
                .accountId("-") // Use "-" for the current account
                .vaultName("your-vault-name") // Replace with the actual vault name
                .jobId(jobId)
                .build());

        return JobStatus.valueOf(describeJobResponse.statusMessage());
    }

    public enum JobStatus {
        IN_PROGRESS, SUCCEEDED, FAILED, UNKNOWN
    }
}
