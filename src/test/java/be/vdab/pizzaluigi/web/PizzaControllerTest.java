package be.vdab.pizzaluigi.web;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.services.EuroService;
import be.vdab.pizzaluigi.services.PizzaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PizzaControllerTest {

    private PizzaController controller;

    @Mock
    private EuroService euroService;

    @Mock
    private PizzaService pizzaService;

    @Before
    public void before() {
        when(pizzaService.read(1L))
                .thenReturn(Optional.of(new Pizza(1, "Test", BigDecimal.ONE, true)));
        controller = new PizzaController(euroService, pizzaService);
    }
    @Test
    public void pizzaWerktSamenMetDeJspPizza() {
        ModelAndView modelAndView = controller.pizza(1L);
        verify(pizzaService).read(1L);
        assertEquals("pizza", modelAndView.getViewName());
    }
    @Test
    public void pizzaGeeftPizzaDoor() {
        ModelAndView modelAndView = controller.pizza(1L);
        verify(pizzaService).read(1L);
        assertTrue(modelAndView.getModel().get("pizza") instanceof Pizza);
    }
    @Test
    public void onbestaandePizza() {
        ModelAndView modelAndView = controller.pizza(-1);
        assertFalse(modelAndView.getModel().containsKey("pizza"));
    }
    @Test
    public void pizzasWerktSamenMetDeJspPizzas() {
        ModelAndView modelAndView = controller.pizzas();
        assertEquals("pizzas", modelAndView.getViewName());
    }
    @Test
    public void pizzasGeeftPizzasDoor() {
        ModelAndView modelAndView = controller.pizzas();
        assertTrue(modelAndView.getModel().get("pizzas") instanceof List);
    }
}