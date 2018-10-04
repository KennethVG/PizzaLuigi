package be.vdab.pizzaluigi.restclients;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FixerKoersClientTest {

    private FixerKoersClient client;

    @Before
    public void before() {
        client = new FixerKoersClient();
    }
    @Test
    public void deKoersMoetPositiefZijn() {
        assertTrue(client.getDollarKoers().compareTo(BigDecimal.ZERO) > 0);
    }
}