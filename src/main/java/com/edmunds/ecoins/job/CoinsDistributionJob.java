package com.edmunds.ecoins.job;

import com.edmunds.ecoins.restservice.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoinsDistributionJob {
    private final static int TOTAL_COINS = 1000;

    @Autowired
    private CoinService coinService;

    //@Scheduled(fixedDelay = 3*60) uncomment whet it's needed
    public void destribute() {
        coinService.distributeCoins();
    }
}
