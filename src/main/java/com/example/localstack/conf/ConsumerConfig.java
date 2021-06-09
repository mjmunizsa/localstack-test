package com.example.localstack.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import com.example.localstack.consumer.EventRecordProcessorFactory;

import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.common.KinesisClientUtil;

@Configuration
public class ConsumerConfig {

	@Value("${localstack.endpoint:#{null}}")
	private String endPoint;
	
	@Value("${cloud.aws.region.static}")
	private String region;
	
	
	@Value(value = "${aws.stream_name}")
	private String streamName;
	
	@Value(value = "${aws.application_name}")
	private String applicationName;
	
	@Bean
	@Primary
	ConfigsBuilder getConfigBuilder() throws URISyntaxException {
		
		KinesisAsyncClient kinesisClient = null;
		DynamoDbAsyncClient dynamoDbAsyncClient = null;
		CloudWatchAsyncClient cloudWatchAsyncClient = null;
		
		if (StringUtils.hasLength(endPoint)) {
			URI endPointUri = new URI(endPoint);
			
			kinesisClient = KinesisClientUtil
					.createKinesisAsyncClient(KinesisAsyncClient.builder().endpointOverride(endPointUri).region(Region.of(region)));
			dynamoDbAsyncClient = DynamoDbAsyncClient.builder().endpointOverride(endPointUri).region(Region.of(region)).build();
			cloudWatchAsyncClient = CloudWatchAsyncClient.builder().endpointOverride(endPointUri).region(Region.of(region)).build();
		} else {
			kinesisClient = KinesisClientUtil
					.createKinesisAsyncClient(KinesisAsyncClient.builder().region(Region.of(region)));
			dynamoDbAsyncClient = DynamoDbAsyncClient.builder().region(Region.of(region)).build();
			cloudWatchAsyncClient = CloudWatchAsyncClient.builder().region(Region.of(region)).build();
		}
		
		return new ConfigsBuilder(streamName, applicationName, kinesisClient, dynamoDbAsyncClient,
				cloudWatchAsyncClient, applicationName, new EventRecordProcessorFactory());
		
	}
	
	@Bean
	@Primary
	KinesisAsyncClient  amazonKinesisAsync() throws URISyntaxException {
		//AWSCredentialsProvider awsCreds = new DefaultAWSCredentialsProviderChain();
//		return AmazonKinesisAsyncClientBuilder.standard()
//				.withEndpointConfiguration(new EndpointConfiguration(endPoint, region)).build();
		
		return KinesisClientUtil
				.createKinesisAsyncClient(KinesisAsyncClient.builder().endpointOverride(new URI(endPoint)).region(Region.of(region)));
		
	}
	
}
