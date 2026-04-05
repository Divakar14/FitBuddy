package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ActivityAIService {

    private final GeminiService geminiService;

    public ActivityAIService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public Recommendation generateRecommendation(Activity activity) {

        String prompt = createPromptForActivity(activity);

        String aiResponse = geminiService.getAnswer(prompt);

        /*String aiResponse = """
                {
                  "candidates": [
                    {
                      "content": {
                        "parts": [
                          {
                            "text": "{\\n    \\"analysis\\": {\\n        \\"overall\\": \\"This was a highly effective 30-minute run covering 5.2 km. Completing more than a 5K in half an hour indicates a strong aerobic base and a solid fitness level. The effort appears to be a moderate-to-high intensity steady-state run, likely performed at a tempo or 'threshold' effort.\\",\\n        \\"pace\\": \\"Your average speed of 10.5 km/h translates to a pace of approximately 5:43 per kilometer. This is an impressive sustained pace for a 30-minute session. Maintaining this speed consistently over 5.2 km suggests good cardiovascular efficiency and muscular endurance.\\",\\n        \\"heartRate\\": \\"A maximum heart rate of 165 bpm suggests you were working at roughly 80-90% of your maximum capacity (depending on age). This indicates a vigorous intensity level that improves VO2 max and anaerobic threshold. It shows you were pushing your limits without entering a full 'red-line' sprint zone.\\",\\n        \\"CaloriesBurned\\": \\"Burning 300 calories in 30 minutes is a high burn rate (10 kcal/min). This is consistent with a vigorous running effort and is very effective for weight management and metabolic health.\\"\\n    },\\n    \\"improvements\\": [\\n        {\\n            \\"area\\": \\"Speed Endurance\\",\\n            \\"recommendation\\": \\"To increase your average speed from 10.5 km/h to 11+ km/h, incorporate 'Fartlek' training where you inject 1-minute bursts of higher speed followed by 2 minutes of your current average pace.\\"\\n        },\\n        {\\n            \\"area\\": \\"Aerobic Capacity\\",\\n            \\"recommendation\\": \\"Try to gradually increase the duration of one run per week to 45-50 minutes at a slightly lower intensity (around 9.0 km/h) to build a deeper aerobic base, which will make your 30-minute runs feel easier.\\"\\n        }\\n    ],\\n    \\"suggestions\\": [\\n        {\\n            \\"workout\\": \\"Interval Speed Work\\",\\n            \\"description\\": \\"After a 5-minute warm-up, perform 5 sets of 400m sprints at 12.0 km/h, followed by 90 seconds of walking or light jogging recovery. Finish with a 5-minute cool-down.\\"\\n        },\\n        {\\n            \\"workout\\": \\"Progression Run\\",\\n            \\"description\\": \\"Run for 30 minutes, starting at 9.5 km/h and increasing the speed by 0.2 km/h every 5 minutes, finishing the last 5 minutes at your maximum sustainable effort.\\"\\n        }\\n    ],\\n    \\"safety\\": [\\n        \\"Ensure you are performing dynamic stretches (leg swings, lunges) before the run to prevent muscle strains, especially given the high heart rate recorded.\\",\\n        \\"Monitor your recovery; if your resting heart rate is elevated the morning after a 165 bpm peak effort, consider a low-impact recovery day (walking or swimming).\\",\\n        \\"Maintain proper hydration before and after the run to compensate for the 300-calorie energy expenditure and fluid loss through sweat.\\"\\n    ]\\n}",
                            "thoughtSignature": "EvkKCvYKAb4+9vt8xyifTwXZRCXJyKkDwBmIPglQYQ6t12msZxgEjc/BwRB0Yo3pD9KVIa7ovrDCvwcaMyFnPs3R3y6opq4rJk/nredvdVbYjA1QIFRKzf4uH++vxzUn1+3vF9bHU3V1O3mPm96D7Ei0MwLJfgc+KbSQS/BKuEQzt/izdHkuywj/4Sz5bvXY1oTVWOFrXuYuBh/vFe3hRR6yAR+tDRhxYKKhgd+CZLa4D1pQD5i6+N9tlxxH6Ha73CLtj72QSzVuEyrIOEl8QsVlO3BcZThbBrkxwKRRmicq3ysIEatLLga0iOYuGxjdMB/yDde7tEzFFDXJzJ8goRwiceR0MkyDW++iz6Pg4dsCN3GFdmVCVv7IJ5fygqxEBpRM1Ksebx1PcOsL+DYlWMLtd7NcCI/HlhGRJmfpfu61xRPqglDNTGkF4PHVpuK3d9MW+g8glVLoBrFrMdcDFAcd6/fJYqj0yvzUtwJ5TTXdn483XrxA1gYDoze+l4gZ2fvPdWy6tCc0E6R2IIqv515fd4HwKrCXKXjBjBav+k2iS1xCmfMDG2T2M3xTSF1yBhzqmZef3zxwW5QKpYfbHK6Z7jv2XEdG4nZvSij7T+zt92hvDfCCl/dLin2uEMoUZY/NgbO0Pf8CG19+FaD7/1WC6Ixblm3hccxp44pmU9fP6WYOoGHk2ve1/f6zm7Ac6C40B4lL9Ub7hvLHGFWqXr3DfYcpiofUlskOqIMI5GBa5lNVkdjukP49oVDrNZOWzYrPTp6jtVoVl3EJ5d37VjtDqGzQcrawo8NkZ1Ffep93tS3t1WU6PeVNJpfn+T6ilIwNWldVlO9xIwaeRULjD7bWcEuQbJQswPhpo3qOPFCLmCsCaKA/s39jLPyg3CjCwzXbY7uTXqpWjsRKy+os063UHli8ckKhvuNhBdXFcHLWlQFeh9ctr/1J2qN8DyqNL8hm2ZauCAhLzIad1LeU2XuhhDzYmgD/hcnA33clYvvPhUAx//kgxUUIrtX/A18ogXRbiG6wRf5ctBwbb3HwtDVNrTc+9xsSb2rH/c3zFI+tRUD+NKuYBvqlOblrFOAfOQ9jw9tjP6WrLK/IaEyfnZ8QiVMyrSQNaGpvDfMyZDdSOKL+ici35Gu+Pqm8SADmx0LDZnep1c0dL17EG6XtJwaYI5cSQFM63tFnueANRneKmAmvkIT146lnE2iPmQqAV1IHKaPSlcd499fkAEL6WJXtGlestI+9CzHQeXtBb3+7vEoDhqHm3/mzP3GueE1WTGsxXkHA+FHrbmDwTE9ibB+6g6RdjTrMSnVrnSn5KjtrnWLUMf0rUnfg0Dc6J07esLcHaWb9lMy916sbFr990amO55kLJSuJDdhJ6lQWfEur8XGzsNl9LNVSzGY4k6uMS9qPDgb71zepiaCLGNAQ1vfqy5rphJBGL0MXPV5aVDGs+H49Q6vPldEbnTD0Nc1vq9QR1xUU5KV1gcQsp7d0x0ZDNFsy/AkGmJVRtfcf+KdbWsxrdAx/D+XwR3RnZ9W2AifzIbMp1hjJ4AQ707ANg6731laXK7Kt7Kv7x3oTmArbvDbsjjFML2jDUp3wqnmNEmAh4cGRzKqe6v+FRjFRCibEc8MghQGrUgejQJ5+BmFf6LatTDiy+hyhdgHFWVCp3APg+Zx6Xo5w5HapGW6YeI31ZPsCSov73SKraULpnVJtx+BCaEPBPoLN17nU3ndzWBtss+eAkfFUpCvFjl9wUx4/Ccg/jOs+95GAlxYn/V2AO1XsQekIyGLlvIglNzjvUWJdCozslvINkvZZ17vcs8fRFsGOKgB8C9u2c25hSSzURx205z/lz+XRjppOmIIC9wn8NXA06XOcPrM1"
                          }
                        ],
                        "role": "model"
                      },
                      "finishReason": "STOP",
                      "index": 0
                    }
                  ],
                  "usageMetadata": {
                    "promptTokenCount": 249,
                    "candidatesTokenCount": 697,
                    "totalTokenCount": 1383,
                    "promptTokensDetails": [
                      {
                        "modality": "TEXT",
                        "tokenCount": 249
                      }
                    ],
                    "thoughtsTokenCount": 437
                  },
                  "modelVersion": "gemini-3-flash-preview",
                  "responseId": "AxDSaaqOENjXg8UPutCAqQg"
                }
                """;*/

        return processAIResponse(activity, aiResponse);

    }

    public Recommendation processAIResponse(Activity activity, String aiResponse) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(aiResponse);
            JsonNode textNode = rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text");

            JsonNode actualJson = mapper.readTree(textNode.asText());

            String minifiedJson = actualJson.toString();

            System.out.println("Processed and Clean Response from AI Response : " + minifiedJson);

            JsonNode analysisJson = mapper.readTree(minifiedJson);
            JsonNode analysisNode = analysisJson.path("analysis");

            StringBuilder fullAnalysis = new StringBuilder();

            addAnalysisSection(fullAnalysis, analysisNode, "overall", "Overall:");
            addAnalysisSection(fullAnalysis, analysisNode, "pace", "Pace:");
            addAnalysisSection(fullAnalysis, analysisNode, "heartRate", "HeartRate:");
            addAnalysisSection(fullAnalysis, analysisNode, "CaloriesBurned", "CaloriesBurned:");

            List<String> improvements = extractImprovements(analysisJson.path("improvements"));

            List<String> suggestions = extractSuggestions(analysisJson.path("suggestions"));

            List<String> safety = extractSafetyGudielines(analysisJson.path("safety"));

            Recommendation recommendation = new Recommendation();

            recommendation.setActivityId(activity.getId());
            recommendation.setUserId(activity.getUserId());
            recommendation.setActivityType(activity.getType());
            recommendation.setRecommendation(fullAnalysis.toString().trim());
            recommendation.setImprovements(improvements);
            recommendation.setSuggestions(suggestions);
            recommendation.setSafety(safety);
            recommendation.setCreatedAt(LocalDateTime.now());

            return recommendation;


        } catch (Exception e) {
            e.printStackTrace();
            return createDefaultRecommendation(activity);
        }

    }

    private Recommendation createDefaultRecommendation(Activity activity) {

        Recommendation recommendation = new Recommendation();

        recommendation.setActivityId(activity.getId());
        recommendation.setUserId(activity.getUserId());
        recommendation.setActivityType(activity.getType());
        recommendation.setRecommendation("Unable to generate detailed analysis");
        recommendation.setImprovements(Collections.singletonList("Continue with your current routine"));
        recommendation.setSuggestions(Collections.singletonList("Consider consulting a fitness professional"));
        recommendation.setSafety(Arrays.asList(
                "Always warm up before exercise",
                "Stay Hydrated",
                "Listen to your body"
        ));
        recommendation.setCreatedAt(LocalDateTime.now());

        return recommendation;

    }

    private List<String> extractSafetyGudielines(JsonNode safetyNode) {

        List<String> saftey = new ArrayList<>();

        if (safetyNode.isArray()) {
            safetyNode.forEach(item -> { saftey.add(item.asText());
            });
        }

        return saftey.isEmpty() ? Collections.singletonList("Follow general Safety Guidelines.") : saftey;

    }

    private List<String> extractSuggestions(JsonNode suggestionsNode) {

        List<String> suggestions = new ArrayList<>();

        if (suggestionsNode.isArray()) {
            suggestionsNode.forEach(improvement -> {
                String workout = improvement.path("workout").asText();
                String description = improvement.path("description").asText();
                suggestions.add(String.format("%s: %s", workout, description));
            });
        }

        return suggestions.isEmpty() ? Collections.singletonList("No specific suggestions provided.") : suggestions;

    }

    private List<String> extractImprovements(JsonNode improvementsNode) {

        List<String> improvements = new ArrayList<>();

        if (improvementsNode.isArray()) {
            improvementsNode.forEach(improvement -> {
                String area = improvement.path("area").asText();
                String detail = improvement.path("recommendation").asText();
                improvements.add(String.format("%s: %s", area, detail));
            });
        }

        return improvements.isEmpty() ? Collections.singletonList("No specific improvements required.") : improvements;

    }

    private void addAnalysisSection(StringBuilder fullAnalysis, JsonNode analysisNode, String key, String prefix) {

        if (!analysisNode.path(key).isMissingNode()) {
            fullAnalysis.append(prefix)
                    .append(analysisNode.path(key).asText())
                    .append("\n\n");
        }

    }

    private String createPromptForActivity(Activity activity) {

        return String.format(""" 
                  Analyze this fitness activity and provide detailed recommendations in the following format
                  {
                      "analysis" : {
                          "overall": "Overall analysis here",
                          "pace": "Pace analysis here",
                          "heartRate": "Heart rate analysis here",
                          "caloriesBurned": "Calories Burned here"
                      },
                      "improvements": [
                          {
                              "area": "Area name",
                              "recommendation": "Detailed Recommendation"
                          }
                      ],
                      "suggestions" : [
                          {
                              "workout": "Workout name",
                              "description": "Detailed workout description"
                          }
                      ],
                      "safety": [
                          "Safety point 1",
                          "Safety point 2"
                      ]
                  }
                
                  Analyze this activity:
                  Activity Type: %s
                  Duration: %d minutes
                  calories Burned: %d
                  Additional Metrics: %s
                
                  provide detailed analysis focusing on performance, improvements, next workout suggestions, and safety guidelines
                  Ensure the response follows the EXACT JSON format shown above."""
                ,
                activity.getType(),
                activity.getDuration(),
                activity.getCaloriesBurned(),
                activity.getAdditionalMetrics()
        );

    }

}
