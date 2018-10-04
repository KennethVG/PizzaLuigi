package be.vdab.pizzaluigi.web;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.services.EuroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PizzaControllerTest {

    private PizzaController controller;

    @Mock
    private EuroService euroService;

    @Before
    public void before() {
        controller = new PizzaController(euroService);
    }
    @Test
    public void pizzaWerktSamenMetDeJspPizza() {
        ModelAndView modelAndView = controller.pizza(1);
        assertEquals("pizza", modelAndView.getViewName());
    }
    @Test
    public void pizzaGeeftPizzaDoor() {
        ModelAndView modelAndView = controller.pizza(1);
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
        assertTrue(modelAndView.getModel().get("pizzas") instanceof Map);
    }
}