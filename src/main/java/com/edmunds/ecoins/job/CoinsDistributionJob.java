package com.edmunds.ecoins.job;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.service.PublicationService;
import com.edmunds.ecoins.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@Component
public class CoinsDistributionJob {
    private final static int TOTAL_COINS = 1000;

    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UserService userService;

    @Scheduled(fixedDelay = 3*60)
    public void destribute() {
        List<Publication> publications = publicationService.getAllPublishedPublications();

        int totalVotes = publications.stream()
                .mapToInt(Publication::getRating).sum();

        Map<String, Double> userVotes = publications.stream()
                .collect(groupingBy(Publication::getUserId, summingDouble(publication -> (publication.getRating() / (double) totalVotes))));

        for (Map.Entry<String, Double> entry : userVotes.entrySet()) {
            userService.addCoins(entry.getKey(), Long.valueOf(Math.round(TOTAL_COINS * entry.getValue())).intValue());
        }
    }
}
