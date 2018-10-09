package be.vdab.pizzaluigi.web;

import be.vdab.pizzaluigi.valueobjects.Adres;
import be.vdab.pizzaluigi.valueobjects.DatumTijd;
import be.vdab.pizzaluigi.valueobjects.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/")
public class IndexController {

    private final AtomicInteger aantalKeerBekeken = new AtomicInteger();

    private final Identificatie identificatie;

    @Autowired
    public IndexController(Identificatie identificatie) {
        this.identificatie = identificatie;
    }

    @GetMapping
    public ModelAndView index(@CookieValue(name = "laatstBezocht", required = false) String laatstBezocht, HttpServletResponse response) {
        String boodschap;
        int uur = LocalTime.now().getHour();
        if (uur < 12) {
            boodschap = "Goede morgen";
        } else if (uur < 18) {
            boodschap = "Goede middag";
        } else {
            boodschap = "Goede avond";
        }
        Cookie cookie = new Cookie("laatstBezocht", LocalDateTime.now().toString());
        cookie.setMaxAge(31_536_000);
        response.addCookie(cookie);
        ModelAndView modelAndView = new ModelAndView("index", "boodschap", boodschap)
                .addObject("zaakvoerder", new Persoon("Luigi", "Peperone", 7, true,
                        new Adres("Grote markt", "3", 9700, "Oudenaarde"))).addObject("aantalKeerBekeken",
                aantalKeerBekeken.incrementAndGet());
        if (laatstBezocht != null) {
            modelAndView.addObject("laatstBezocht", new DatumTijd(LocalDateTime.parse(laatstBezocht)));
        }

        modelAndView.addObject("identificatie", identificatie);
        return modelAndView;
    }

}
