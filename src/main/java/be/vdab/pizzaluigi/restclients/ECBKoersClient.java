package be.vdab.pizzaluigi.restclients;

import be.vdab.pizzaluigi.exceptions.KoersClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;

@Component
@Qualifier("ECB")
public class ECBKoersClient implements KoersClient {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ECBKoersClient.class);

    private final URL url;

    public ECBKoersClient(@Value("${ecbKoersURL}") URL url) {
        this.url = url;
    }


    @Override
    public BigDecimal getDollarKoers() {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try (InputStream stream = url.openStream()) {
            XMLStreamReader reader = factory.createXMLStreamReader(stream);
            try {
                while (reader.hasNext()) {
                    if (reader.next() == XMLStreamConstants.START_ELEMENT
                            && "USD".equals(reader.getAttributeValue(null, "currency"))) {
                        LOGGER.info("koers gelezen via ECB");
                        return new BigDecimal(reader.getAttributeValue(null, "rate"));
                    }
                }
                String fout = "XML van ECB bevat geen USD";
                LOGGER.error(fout);
                throw new KoersClientException(fout);
            } finally {
                reader.close();
            }
        } catch (IOException | NumberFormatException | XMLStreamException ex) {
            String fout = "kan koers niet lezen via ECB";
            LOGGER.error(fout, ex);
            throw new KoersClientException(fout);
        }
    }
}

