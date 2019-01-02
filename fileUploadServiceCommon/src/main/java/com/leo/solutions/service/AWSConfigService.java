package com.leo.solutions.service;
/*
 * @author love.bisaria on 02/01/19
 */

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.context.config.annotation.EnableContextInstanceData;
import org.springframework.stereotype.Service;
import com.google.common.base.Throwables;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@EnableContextInstanceData
public class AWSConfigService {

    private static final Logger log = LoggerFactory.getLogger(AWSConfigService.class);

    public static final Map<String,String> DEFAULT_TARGET_QUEUE_ATTRS =
            ImmutableMap.of("MessageRetentionPeriod", "1209600", //14 days max retention
                    "ReceiveMessageWaitTimeSeconds", "20");

    @Value(value="${aws.region:us-east-1}")
    private String awsRegionName;

    private AWSCredentialsProviderChain awsCredentialsProviderChain;

    @PostConstruct
    public synchronized void startup() {

        this.awsCredentialsProviderChain = new AWSCredentialsProviderChain(new EnvironmentVariableCredentialsProvider());

        log.info("S3 Region is configured to: [{}]", awsRegionName);

        try {
        } catch (IllegalArgumentException e) {
            log.error("Could not start because aws.region configuration value is incorrect.", e);
            Throwables.propagate(e);
        }
    }

    public SQSConnectionFactory getSQSConnectionFactory() {
        return SQSConnectionFactory.builder()
                .withRegion(com.amazonaws.regions.Region.getRegion(Regions.fromName(awsRegionName)))
                .withAWSCredentialsProvider(this.awsCredentialsProviderChain)
                .build();
    }
}
