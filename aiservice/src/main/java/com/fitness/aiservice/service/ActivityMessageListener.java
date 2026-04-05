package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ActivityMessageListener {

    private final ActivityAIService aiService;

    public ActivityMessageListener(ActivityAIService aiService) {
        this.aiService = aiService;
    }

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity) {
        System.out.println("Received activity for processing : " + activity.getId());
        System.out.println("Response from Gemini AI : " + aiService.generateRecommendations(activity));
    }

}
