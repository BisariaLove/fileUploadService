package com.leo.solutions.service;
/*
 * @author love.bisaria on 27/12/18
 */

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InitializeAWSSQSQueues implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(InitializeAWSSQSQueues.class);

    @Value("${aws.sqs.queue.name}")
    private String queueName;

    public void initializeQueues(){

        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        log.info("Creating queue: {}", queueName);
        CreateQueueRequest create_request = new CreateQueueRequest(queueName)
                .addAttributesEntry("DelaySeconds", "60")
                .addAttributesEntry("MessageRetentionPeriod", "86400");

        try {
            sqs.createQueue(create_request);
        } catch (AmazonSQSException e) {
            if (e.getErrorCode().equals("QueueAlreadyExists")) {
                log.info("[{}] : already exists.", queueName);
            } else{
                throw e;
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        initializeQueues();
    }

}
