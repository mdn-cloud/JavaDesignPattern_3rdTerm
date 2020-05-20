package scraper.kijiji;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Mukta
 */
public class ItemBuilder {

    private static final String URL_BASE = "https://www.kijiji.ca";

    private static final String ATTRIBUTE_ID = "data-listing-id";
    private static final String ATTRIBUTE_IMAGE = "image";
    private static final String ATTRIBUTE_IMAGE_SRC = "data-src";
    private static final String ATTRIBUTE_IMAGE_NAME = "alt";
    private static final String ATTRIBUTE_PRICE = "price";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_LOCATION = "location";
    private static final String ATTRIBUTE_DATE = "date-posted";
    private static final String ATTRIBUTE_DESCRIPTION = "description";

    private Element element;
    private KijijiItem item;

    ItemBuilder() {
    }

    public ItemBuilder setElement(Element element) {
        this.element = element;
        return this;
    }

    public KijijiItem build() {
        item = new KijijiItem();

        item.setId(element.attr(ATTRIBUTE_ID).trim());

        item.setUrl(URL_BASE + element.getElementsByClass(ATTRIBUTE_TITLE).get(0).child(0).attr("href").trim());

        Elements element1 = element.getElementsByClass(ATTRIBUTE_IMAGE);
        if (element1.isEmpty()) {
            item.setImageUrl("");
        } else {
            item.setImageUrl(element1.get(0).child(0).attr(ATTRIBUTE_IMAGE_SRC).trim());
        }

        Elements element2 = element.getElementsByClass(ATTRIBUTE_IMAGE);
        if (element2.isEmpty()) {
            item.setImageName("");
        } else {
            item.setImageName(element2.get(0).child(0).attr(ATTRIBUTE_IMAGE_NAME).trim());
        }

        Elements element3 = element.getElementsByClass(ATTRIBUTE_PRICE);
        if (element3.isEmpty()) {
            item.setPrice("");
        } else {
            item.setPrice(element3.get(0).text().trim());
        }

        Elements element4 = element.getElementsByClass(ATTRIBUTE_TITLE);
        if (element4.isEmpty()) {
            item.setTitle("");
        } else {
            item.setTitle(element4.get(0).child(0).text().trim());
        }

        Elements element5 = element.getElementsByClass(ATTRIBUTE_DATE);
        if (element5.isEmpty()) {
            item.setDate("");
        } else {
            item.setDate(element5.get(0).text().trim());
        }

        Elements element6 = element.getElementsByClass(ATTRIBUTE_LOCATION);
        if (element6.isEmpty()) {
            item.setLocation("");
        } else {
            item.setLocation(element6.get(0).childNode(0).outerHtml().trim());
        }

        Elements element7 = element.getElementsByClass(ATTRIBUTE_DESCRIPTION);
        if (element7.isEmpty()) {
            item.setDescription("");
        } else {
            item.setDescription(element7.get(0).text().trim());
        }

        return item;
    }

}
