package be.vdab.pizzaluigi.services;

import be.vdab.pizzaluigi.restclients.KoersClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EuroServiceTest {

    @Mock
    private KoersClient koersClient;

    private DefaultEuroService euroService;

    @Before
    public void init(){
        when(koersClient.getDollarKoers()).thenReturn(BigDecimal.valueOf(1.5));
        euroService = new DefaultEuroService(new KoersClient[] {koersClient});
    }

    @Test
    public void naarDollar() {
        assertEquals(0, BigDecimal.valueOf(3).compareTo(euroService.naarDollar(BigDecimal.valueOf(2))));
        verify(koersClient).getDollarKoers();
    }
}