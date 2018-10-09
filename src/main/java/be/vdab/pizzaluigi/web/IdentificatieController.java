package be.vdab.pizzaluigi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("identificatie")
public class IdentificatieController {
    private static final String VIEW = "identificatie";
    private final Identificatie identificatie;
    private static final String REDIRECT_NA_SUBMIT = "redirect:/";

    @Autowired
    public IdentificatieController(Identificatie identificatie) {
        this.identificatie = identificatie;
    }

    @GetMapping
    public ModelAndView identificatie() {
        return new ModelAndView(VIEW, "identificatie", identificatie);
    }

    @PostMapping
    public String identificatie(@Valid DefaultIdentificatie identificatie,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return VIEW;
        }
        this.identificatie.setEmailAdres(identificatie.getEmailAdres());
        return REDIRECT_NA_SUBMIT;
    }
}
