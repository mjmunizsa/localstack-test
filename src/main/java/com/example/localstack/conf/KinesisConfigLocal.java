//package com.example.localstack.conf;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
//import software.amazon.kinesis.common.KinesisClientUtil;
//
//
//@Configuration
//@Profile("local")
//public class KinesisConfigLocal {
//
//	@Value("${localstack.endpoint}")
//	private String endPoint;
//	
//	@Value("${cloud.aws.region.static}")
//	private String region;
//	
//	@Bean
//	@Primary
//	KinesisAsyncClient  amazonKinesisAsync() throws URISyntaxException {
//		//AWSCredentialsProvider awsCreds = new DefaultAWSCredentialsProviderChain();
////		return AmazonKinesisAsyncClientBuilder.standard()
////				.withEndpointConfiguration(new EndpointConfiguration(endPoint, region)).build();
//		
//		return KinesisClientUtil
//				.createKinesisAsyncClient(KinesisAsyncClient.builder().endpointOverride(new URI(endPoint)).region(Region.of(region)));
////		DynamoDbAsyncClient dynamoClient = DynamoDbAsyncClient.builder().region(region).build();
////		CloudWatchAsyncClient cloudWatchClient = CloudWatchAsyncClient.builder().region(region).build();
//		
//	}
//	
//	
//}
