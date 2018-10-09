package be.vdab.pizzaluigi.repository;

import be.vdab.pizzaluigi.entities.Pizza;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PizzaRepository {

    void create(Pizza pizza);
    Optional<Pizza> read(long id);
    void update(Pizza pizza);
    void delete(long id);
    List<Pizza> findAll();
    List<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot);
    long findAantalPizzas();
    List<BigDecimal> findUniekePrijzen();
    List<Pizza> findByPrijs(BigDecimal prijs);

}
