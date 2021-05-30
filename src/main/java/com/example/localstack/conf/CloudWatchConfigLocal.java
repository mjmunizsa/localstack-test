//package com.example.localstack.conf;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//
//import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
//import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
//import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClientBuilder;
//
//
//@Configuration
//@Profile("local")
//public class CloudWatchConfigLocal {
//
//	@Value("${localstack.endpoint}")
//	private String endPoint;
//	
//	@Value("${cloud.aws.region.static}")
//	private String region;
//	
//	@Bean
//	@Primary
//	AmazonCloudWatchAsync  amazonCloudWatchAsync() {
//		//AWSCredentialsProvider awsCreds = new DefaultAWSCredentialsProviderChain();
//		return AmazonCloudWatchAsyncClientBuilder.standard()
//				.withEndpointConfiguration(new EndpointConfiguration(endPoint, region)).build();
//	}
//	
//	
//}
