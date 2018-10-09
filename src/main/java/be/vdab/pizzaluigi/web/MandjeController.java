package be.vdab.pizzaluigi.web;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.services.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("mandje")
public class MandjeController {

    private final Mandje mandje;
    private final PizzaService pizzaService;
    private static final String VIEW = "mandje";
    private static final String REDIRECT_NA_TOEVOEGEN = "redirect:/mandje";

    public MandjeController(Mandje mandje, PizzaService pizzaService) {
        this.mandje = mandje;
        this.pizzaService = pizzaService;
    }

    private List<Pizza> maakPizzasVanPizzaIds(List<Long> pizzaIds) {
        List<Pizza> pizzas = new ArrayList<>(pizzaIds.size());
        for (long id : pizzaIds) {
            pizzaService.read(id).ifPresent(pizzas::add);
        }
        return pizzas;
    }

    @GetMapping
    public ModelAndView toonMandje() {
        return new ModelAndView(VIEW)
                .addObject(new MandjeForm())
                .addObject("allePizzas", pizzaService.findAll())
                .addObject("pizzasInMandje",
                        maakPizzasVanPizzaIds(mandje.getPizzaIds()));
    }

    @PostMapping
    public String voegPizzaToeAanMandje(MandjeForm form) {
        mandje.addPizzaId(form.getPizzaId());
        return REDIRECT_NA_TOEVOEGEN;
    }
}