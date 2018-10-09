package be.vdab.pizzaluigi.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Component
@SessionScope
public class DefaultIdentificatie implements Serializable, Identificatie {
    private static final long serialVersionUID = 1L;

    @Email
    private String emailAdres;

    @Override
    public String getEmailAdres() {
        return emailAdres;
    }

    @Override
    public void setEmailAdres(String adres) {
        this.emailAdres = adres;
    }
}
