package be.vdab.pizzaluigi.restclients;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ECBKoersClientTest {

    private ECBKoersClient client;

    @Before
    public void init(){
        client = new ECBKoersClient();
    }

    @Test
    public void getDollarKoers() {
        assertTrue(client.getDollarKoers().compareTo(BigDecimal.ZERO) > 0);
    }
}