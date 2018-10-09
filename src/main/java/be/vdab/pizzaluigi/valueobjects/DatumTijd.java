package be.vdab.pizzaluigi.valueobjects;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class DatumTijd {

    @DateTimeFormat
    private final LocalDateTime waarde;

    public DatumTijd(LocalDateTime waarde) {
        this.waarde = waarde;
    }

    public LocalDateTime getWaarde() {
        return waarde;
    }
}
