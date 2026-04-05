package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ActivityMessageListener {

    private final ActivityAIService aiService;

    private final RecommendationRepository recommendationRepository;

    public ActivityMessageListener(ActivityAIService aiService, RecommendationRepository recommendationRepository) {
        this.aiService = aiService;
        this.recommendationRepository = recommendationRepository;
    }

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity) {
        System.out.println("Received activity for processing : " + activity.getId());
        //System.out.println("Response from GEMINI AI : " + aiService.generateRecommendation(activity));
        Recommendation recommendation = aiService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }

}
