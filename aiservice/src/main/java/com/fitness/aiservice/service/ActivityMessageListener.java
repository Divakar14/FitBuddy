package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ActivityMessageListener {

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity) {
        System.out.println("Received activity for processing : " + activity.getId());
    }

}
