package net.sahin.springvw.campaign;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.Optional;

@Slf4j
@ToString
@EqualsAndHashCode
public class Opengraph implements Serializable {

    public final String imageUrl;
    public final int imageWidth;
    public final int imageHeight;

    public Opengraph(String imageUrl, int imageWidth, int imageHeight) {
        this.imageUrl = imageUrl;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public static Optional<Opengraph> of(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("meta[property*=\"og:\"]");
            return Optional.of(new Opengraph(
                    selectString(elements, "og:image"),
                    selectNumber(elements, "og:image:width"),
                    selectNumber(elements, "og:image:height")
            ));
        } catch (Exception e) {
            log.error("{} : {}", e.getMessage(), url);
            log.trace(e.getMessage(), e);
            return Optional.empty();
        }
    }

    private static String selectString(Elements elements, String selector) {
        Elements e = selectElements(elements, selector);
        return e.isEmpty() ? "" : e.first().attr("content");
    }

    private static int selectNumber(Elements elements, String selector) {
        try {
            Elements e = selectElements(elements, selector);
            return e.isEmpty() ? 0 : Integer.parseInt(e.first().attr("content"));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private static Elements selectElements(Elements elements, String selector) {
        return elements.select("[property=\"" + selector + "\"]");
    }
}
