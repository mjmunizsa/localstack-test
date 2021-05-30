package com.example.localstack;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.common.InitialPositionInStream;
import software.amazon.kinesis.common.InitialPositionInStreamExtended;
import software.amazon.kinesis.coordinator.Scheduler;

@SpringBootApplication
public class LocalstackApplication implements CommandLineRunner{
	
	 private static final Logger LOG =
	            LoggerFactory.getLogger(LocalstackApplication.class);
	 
	 
	 @Autowired
	 ApplicationContext context; 

	public static void main(String[] args) {
		SpringApplication.run(LocalstackApplication.class, args);
	}
	
	 @Override
	 public void run(String... args) throws Exception {
		 
        LOG.info("Running consumer application!");
        
        ConfigsBuilder conf = context.getBean(ConfigsBuilder.class);
        Scheduler scheduler =
                new Scheduler(conf.checkpointConfig(), conf.coordinatorConfig(),
                		conf.leaseManagementConfig(), conf.lifecycleConfig(),
                		conf.metricsConfig(), conf.processorConfig(),
                        conf.retrievalConfig().maxListShardsRetryAttempts(5)
                                .initialPositionInStreamExtended(InitialPositionInStreamExtended
                                        .newInitialPosition(InitialPositionInStream.TRIM_HORIZON)));

        Thread schedulerThread = new Thread(scheduler);
        schedulerThread.setDaemon(true);
        schedulerThread.start();
	        
	 }

}
