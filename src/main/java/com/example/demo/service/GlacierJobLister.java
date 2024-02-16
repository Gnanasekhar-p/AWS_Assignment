package com.example.demo.service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glacier.GlacierClient;
import software.amazon.awssdk.services.glacier.model.GlacierJobDescription;
import software.amazon.awssdk.services.glacier.model.ListJobsRequest;
import software.amazon.awssdk.services.glacier.model.ListJobsResponse;

public class GlacierJobLister {

    public static void main(String[] args) {
        // Specify the AWS region
        Region region = Region.AP_SOUTH_1; // Change this to your desired region

        // Create a Glacier client
        GlacierClient glacierClient = GlacierClient.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        // Specify your Glacier vault name
        String vaultName = "your-vault-name"; // Change this to your Glacier vault name

        // List the jobs in the specified vault
        listJobs(glacierClient, vaultName);
    }

    private static void listJobs(GlacierClient glacierClient, String vaultName) {
        ListJobsResponse listJobsResponse = glacierClient.listJobs(ListJobsRequest.builder()
                .accountId("-") // Use "-" for the current account
                .vaultName(vaultName)
                .build());

        for (GlacierJobDescription jobListElement : listJobsResponse.jobList()) {
            System.out.println("Job ID: " + jobListElement.jobId());
            // You can access other job details as needed
        }
    }
}
