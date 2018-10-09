package be.vdab.pizzaluigi.restclients;

import be.vdab.pizzaluigi.exceptions.KoersClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Scanner;

@Component
@Qualifier("Fixer")
public class FixerKoersClient implements KoersClient {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(FixerKoersClient.class);

    private final URL url;

    public FixerKoersClient(@Value("${fixerKoersURL}") URL url) {
       this.url = url;
    }

    @Override
    public BigDecimal getDollarKoers() {
        LOGGER.info("koers gelezen via Fixer");
        try (Scanner scanner = new Scanner(url.openStream())) {
            String lijn;
            while (scanner.hasNextLine()) {
                lijn = scanner.nextLine();
                if (lijn.trim().startsWith("\"USD\"")) {
                    return new BigDecimal(lijn.replace("\"USD\":", "").trim());
                }
            }
        } catch (IOException e) {
            String fout = "kan koers niet lezen via Fixer";
            LOGGER.error(fout, e);
            throw new KoersClientException(fout);
        }

        return null;
    }
}
