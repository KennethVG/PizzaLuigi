package be.vdab.pizzaluigi.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class DefaultMandje implements Serializable, Mandje {

    private static final long serialVersionUID = 1L;
    private final List<Long> pizzaIds = new ArrayList<>();

    @Override
    public void addPizzaId(long pizzaId) {
        pizzaIds.add(pizzaId);
    }

    @Override
    public List<Long> getPizzaIds() {
        return pizzaIds;
    }
}
