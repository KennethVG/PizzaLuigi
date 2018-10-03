package be.vdab.pizzaluigi.web;

import be.vdab.pizzaluigi.entities.Pizza;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.*;

public class PizzaControllerTest {

    private PizzaController controller;

    @Before
    public void before() {
        controller = new PizzaController();
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
        assertTrue(modelAndView.getModel().get("pizzas") instanceof List);
    }

}