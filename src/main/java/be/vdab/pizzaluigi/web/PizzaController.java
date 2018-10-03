package be.vdab.pizzaluigi.web;

import be.vdab.pizzaluigi.entities.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("pizzas")
public class PizzaController {

    private static final String PIZZAS_VIEW = "pizzas";
    private static final String PIZZA_VIEW = "pizza";

//    private final List<Pizza> pizzas = Arrays.asList(
//            new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true),
//            new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
//            new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));

    private final Map<Long, Pizza> pizzas = new LinkedHashMap<>();

    public PizzaController() {
        pizzas.put(1L, new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true));
        pizzas.put(2L, new Pizza(2, "Margherita", BigDecimal.valueOf(5), false));
        pizzas.put(3L, new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));
        pizzas.put(4L, new Pizza(4, "Fungi & Olive", BigDecimal.valueOf(5), false));
    }

    @GetMapping("{id}")
    public ModelAndView pizza(@PathVariable  long id) {
        ModelAndView modelAndView = new ModelAndView(PIZZA_VIEW);
        if (pizzas.containsKey(id)) {
            modelAndView.addObject(pizzas.get(id));
        }
        return modelAndView;
    }

    @GetMapping
    public ModelAndView pizzas() {
        return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzas);
    }
}
