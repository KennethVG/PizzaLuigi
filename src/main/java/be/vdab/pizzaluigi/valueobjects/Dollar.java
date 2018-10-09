package be.vdab.pizzaluigi.valueobjects;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public class Dollar {

    @NumberFormat(pattern = "0.00")
    private final BigDecimal waarde;

    public Dollar(BigDecimal waarde) {
        this.waarde = waarde;
    }

    public BigDecimal getWaarde() {
        return waarde;
    }
}
