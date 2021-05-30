package com.example.localstack.consumer;

import org.springframework.stereotype.Component;

import software.amazon.kinesis.processor.ShardRecordProcessor;
import software.amazon.kinesis.processor.ShardRecordProcessorFactory;

@Component
public class EventRecordProcessorFactory implements ShardRecordProcessorFactory {

	@Override
	public ShardRecordProcessor shardRecordProcessor() {
		return new EventProcessor();
	}
	

}
