package com.edmunds.ecoins.restservice.controller;

import com.edmunds.ecoins.restservice.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date 02/04/2019.
 *
 * @author Raman_Bohush
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/coins")
public class CoinController {
    private final static int TOTAL_COINS = 1000;

    @Autowired
    private CoinService coinService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/distribution", method = RequestMethod.PUT)
    public void destribute() {
        coinService.distributeCoins();
    }
}
