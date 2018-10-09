package be.vdab.pizzaluigi.web;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.services.EuroService;
import be.vdab.pizzaluigi.services.PizzaService;
import be.vdab.pizzaluigi.valueobjects.Dollar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("pizzas")
public class PizzaController {

    private final EuroService euroService;
    private final PizzaService pizzaService;

    private static final String PIZZAS_VIEW = "pizzas";
    private static final String PIZZA_VIEW = "pizza";
    private static final String PRIJZEN_VIEW = "prijzen";
    private static final String VAN_TOT_PRIJS_VIEW = "vantotprijs";
    private static final String TOEVOEGEN_VIEW = "toevoegen";
    private static final String REDIRECT_URL_NA_TOEVOEGEN="redirect:/pizzas";

//    private final List<Pizza> pizzas = Arrays.asList(
//            new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true),
//            new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
//            new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));

//    private final Map<Long, Pizza> pizzas = new LinkedHashMap<>();

    @Autowired
    public PizzaController(EuroService euroService, PizzaService pizzaService) {
        this.euroService = euroService;
        this.pizzaService = pizzaService;
//        pizzas.put(1L, new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true));
//        pizzas.put(2L, new Pizza(2, "Margherita", BigDecimal.valueOf(5), false));
//        pizzas.put(3L, new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));
//        pizzas.put(4L, new Pizza(4, "Fungi & Olive", BigDecimal.valueOf(5), false));
    }

    @GetMapping("{id}")
    public ModelAndView pizza(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView(PIZZA_VIEW);
        pizzaService.read(id).ifPresent(pizza -> {
            modelAndView.addObject(pizza);
            modelAndView.addObject("inDollar", new Dollar(euroService.naarDollar(pizza.getPrijs())));
        });
//        if (pizzas.containsKey(id)) {
//            Pizza pizza = pizzas.get(id);
//            modelAndView.addObject(pizza).addObject("inDollar", euroService.naarDollar(pizza.getPrijs()));
//        }
        return modelAndView;
    }

    @GetMapping
    public ModelAndView pizzas() {
        return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzaService.findAll());
        // pizzas);
    }

    @GetMapping("prijzen")
    public ModelAndView prijzen() {
        return new ModelAndView(PRIJZEN_VIEW,
                "prijzen", pizzaService.findUniekePrijzen());
        //pizzas.values().stream().map(Pizza::getPrijs).collect(Collectors.toSet()));
    }

    @GetMapping(params = "prijs")
    public ModelAndView pizzasVanPrijs(BigDecimal prijs) {
        return new ModelAndView(PRIJZEN_VIEW, "pizzas", pizzaService.findByPrijs(prijs))
//                pizzas.values().stream().filter(pizza ->
//                        pizza.getPrijs().equals(prijs)).collect(Collectors.toList()))
                .addObject("prijs", prijs)
                .addObject("prijzen", pizzaService.findUniekePrijzen());
//                        pizzas.values().stream().map(Pizza::getPrijs).collect(Collectors.toSet()));
    }

    @GetMapping(params = {"van", "tot"})
    public ModelAndView findVanTotPrijs(@Valid VanTotPrijsForm form, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView(VAN_TOT_PRIJS_VIEW);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        List<Pizza> pizzas =
                pizzaService.findByPrijsBetween(form.getVan(), form.getTot());
        if (pizzas.isEmpty()) {
            bindingResult.reject("geenPizzas");
        } else {
            modelAndView.addObject("pizzas", pizzas);
        }
        return modelAndView;
    }

    @GetMapping("vantotprijs")
    public ModelAndView findVanTotPrijs() {
        VanTotPrijsForm form = new VanTotPrijsForm();
        form.setVan(BigDecimal.ZERO);
        form.setTot(BigDecimal.ZERO);
        return new ModelAndView(VAN_TOT_PRIJS_VIEW).addObject(form);
    }

    @GetMapping("toevoegen")
    public ModelAndView toevoegen() {
        return new ModelAndView(TOEVOEGEN_VIEW).addObject(new Pizza());
    }

    @PostMapping("toevoegen")
    public ModelAndView toevoegen(@Valid Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(TOEVOEGEN_VIEW);
        }
        pizzaService.create(pizza);

        redirectAttributes.addAttribute("boodschap", "Pizza  " + pizza.getNaam() + " toegevoegd");

        return new ModelAndView(REDIRECT_URL_NA_TOEVOEGEN);
    }
}
