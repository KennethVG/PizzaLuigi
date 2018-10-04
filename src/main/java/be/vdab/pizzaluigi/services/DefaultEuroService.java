package be.vdab.pizzaluigi.services;

import be.vdab.pizzaluigi.exceptions.KoersClientException;
import be.vdab.pizzaluigi.restclients.KoersClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DefaultEuroService implements EuroService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DefaultEuroService.class);

    private final KoersClient[] koersClients;

    @Autowired
    public DefaultEuroService(KoersClient[] koersClients) {
        this.koersClients = koersClients;
    }

    @Override
    public BigDecimal naarDollar(BigDecimal euro) {
        for (KoersClient koersClient : koersClients) {
            try {
                return euro.multiply(koersClient.getDollarKoers())
                        .setScale(2, RoundingMode.HALF_UP);
            } catch (KoersClientException ex) {
                LOGGER.error("kan dollar koers niet lezen", ex);
            }
        }
        LOGGER.error("kan dollar koers van geen enkele bron lezen");
        return null;
    }

}
