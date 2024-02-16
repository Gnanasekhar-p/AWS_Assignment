package com.example.demo.service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glacier.GlacierClient;
import software.amazon.awssdk.services.glacier.model.DescribeVaultOutput;
import software.amazon.awssdk.services.glacier.model.ListVaultsRequest;
import software.amazon.awssdk.services.glacier.model.ListVaultsResponse;

public class GlacierVaultLister {

    public static void main(String[] args) {
        // Specify the AWS region
        Region region = Region.AP_SOUTH_1; // Change this to your desired region

        // Specify your AWS access key and secret key
        String accessKey = "AKIAX5XSI5UTUOMGM5F3";
        String secretKey = "0eWSBl7u3R4kMlACn4YgoyJGyIttks03ILWL/x+r";

        // Create Glacier client with explicit credentials
        GlacierClient glacierClient = GlacierClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();

        // List all available vaults
        listVaults(glacierClient);
    }

    private static void listVaults(GlacierClient glacierClient) {
        ListVaultsResponse listVaultsResponse = glacierClient.listVaults(ListVaultsRequest.builder()
                .accountId("-") // Use "-" for the current account
                .build());

        for (DescribeVaultOutput vault : listVaultsResponse.vaultList()) {
            System.out.println("Vault Name: " + vault.vaultName());
            // You can access other vault details as needed
        }
    }
}
