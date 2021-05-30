package com.example.localstack.consumer;


import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import software.amazon.kinesis.exceptions.InvalidStateException;
import software.amazon.kinesis.exceptions.ShutdownException;
import software.amazon.kinesis.lifecycle.events.InitializationInput;
import software.amazon.kinesis.lifecycle.events.LeaseLostInput;
import software.amazon.kinesis.lifecycle.events.ProcessRecordsInput;
import software.amazon.kinesis.lifecycle.events.ShardEndedInput;
import software.amazon.kinesis.lifecycle.events.ShutdownRequestedInput;
import software.amazon.kinesis.processor.ShardRecordProcessor;
import software.amazon.kinesis.retrieval.KinesisClientRecord;

public class EventProcessor implements ShardRecordProcessor {
	
	 private static final Logger LOG = LoggerFactory.getLogger(EventProcessor.class);

	@Override
	public void initialize(InitializationInput initializationInput) {
		// TODO Auto-generated method stub
		LOG.info("Entrando en initialize");
		
	}

	@Override
	public void processRecords(ProcessRecordsInput processRecordsInput) {
		LOG.info(processRecordsInput.toString());
		
		 for (KinesisClientRecord record : processRecordsInput.records()) {

	            LOG.info("Processing Record For Partition Key : {}", record.partitionKey());
	            String originalData = "";

	            try {
	                byte[] b = new byte[record.data().remaining()];
	                ByteBuffer byteBuf = record.data().get(b);
	                originalData = new String(byteBuf.array(), "UTF-8");

	                LOG.info("Data from kinesis stream : {}", originalData);
	            } catch (Exception e) {
	                LOG.error("Error parsing record {}", e);
	                System.exit(1);
	            }
	            
		 }
	            
		
		try {
            /*
             * KCL assumes that the call to checkpoint means that all records have been
             * processed, records which are passed to the record processor.
             */
            processRecordsInput.checkpointer().checkpoint();

        } catch (Exception e) {
        	LOG.error("Error during Processing of records", e);
        }
		
	}

	@Override
	public void leaseLost(LeaseLostInput leaseLostInput) {
		LOG.info("Entrando en leaseLost");
		
	}

	@Override
	public void shardEnded(ShardEndedInput shardEndedInput) {
		LOG.info("Entrando en shardEnded");
		 try {
	            shardEndedInput.checkpointer().checkpoint();
	        } catch (ShutdownException | InvalidStateException e) {

	            e.printStackTrace();
	        }
		
	}

	@Override
	public void shutdownRequested(ShutdownRequestedInput shutdownRequestedInput) {
		LOG.info("Entrando en shutdownRequested");
		 try {
	            shutdownRequestedInput.checkpointer().checkpoint();
	        } catch (ShutdownException | InvalidStateException e) {

	            e.printStackTrace();
	        }
		
	}

}
