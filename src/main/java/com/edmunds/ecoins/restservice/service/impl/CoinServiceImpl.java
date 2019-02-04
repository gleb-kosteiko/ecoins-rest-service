package com.edmunds.ecoins.restservice.service.impl;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.service.CoinService;
import com.edmunds.ecoins.restservice.service.PublicationService;
import com.edmunds.ecoins.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

/**
 * Date 02/04/2019.
 *
 * @author Raman_Bohush
 */
@Service
public class CoinServiceImpl implements CoinService {
    private final static int TOTAL_COINS = 1000;

    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UserService userService;

    @Override
    public void distributeCoins() {
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
