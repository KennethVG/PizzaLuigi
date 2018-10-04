package be.vdab.pizzaluigi.web;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.services.EuroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("pizzas")
public class PizzaController {

    private final EuroService euroService;

    private static final String PIZZAS_VIEW = "pizzas";
    private static final String PIZZA_VIEW = "pizza";
    private static final String PRIJZEN_VIEW = "prijzen";

//    private final List<Pizza> pizzas = Arrays.asList(
//            new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true),
//            new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
//            new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));

    private final Map<Long, Pizza> pizzas = new LinkedHashMap<>();

    public PizzaController(EuroService euroService) {
        this.euroService = euroService;
        pizzas.put(1L, new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true));
        pizzas.put(2L, new Pizza(2, "Margherita", BigDecimal.valueOf(5), false));
        pizzas.put(3L, new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));
        pizzas.put(4L, new Pizza(4, "Fungi & Olive", BigDecimal.valueOf(5), false));
    }

    @GetMapping("{id}")
    public ModelAndView pizza(@PathVariable  long id) {
        ModelAndView modelAndView = new ModelAndView(PIZZA_VIEW);
        if (pizzas.containsKey(id)) {
            Pizza pizza = pizzas.get(id);
            modelAndView.addObject(pizza).addObject("inDollar", euroService.naarDollar(pizza.getPrijs()));
        }
        return modelAndView;
    }

    @GetMapping
    public ModelAndView pizzas() {
        return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzas);
    }

    @GetMapping("prijzen")
    public ModelAndView prijzen() {
        return new ModelAndView(PRIJZEN_VIEW,
                "prijzen", pizzas.values().stream().map(Pizza::getPrijs).collect(Collectors.toSet()));
    }

    @GetMapping(params="prijs")
    public ModelAndView pizzasVanPrijs(BigDecimal prijs) {
        return new ModelAndView(PRIJZEN_VIEW, "pizzas",
                pizzas.values().stream().filter(pizza ->
                        pizza.getPrijs().equals(prijs)).collect(Collectors.toList()))
                .addObject("prijs", prijs)
                .addObject("prijzen", pizzas.values().stream().map(Pizza::getPrijs).collect(Collectors.toSet()));
    }
}
