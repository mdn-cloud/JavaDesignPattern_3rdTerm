package logic;

import dal.ItemDAL;
import entity.Item;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mukta
 */
public class ItemLogic extends GenericLogic<Item, ItemDAL> {

    public static final String DESCRIPTION = "description";
    public static final String CATEGORY_ID = "categoryId";
    public static final String IMAGE_ID = "imageId";
    public static final String LOCATION = "location";
    public static final String PRICE = "price";
    public static final String TITLE = "title";
    public static final String DATE = "date";
    public static final String URL = "url";
    public static final String ID = "id";

    public ItemLogic() {
        super(new ItemDAL());
    }

    @Override
    public List<Item> getAll() {
        return get(() -> dao().findAll());
    }

    @Override
    public Item getWithId(int id) {
        return get(() -> dao().findById(id));
    }

    public List<Item> getWithPrice(BigDecimal price) {
        return get(() -> dao().findByPrice(price));
    }

    public List<Item> getWithTitle(String title) {
        return get(() -> dao().findByTitle(title));
    }

    public List<Item> getWithDate(String date) {
        return get(() -> dao().findByDate(date));
    }

    public List<Item> getWithLocation(String location) {
        return get(() -> dao().findByLocation(location));
    }

    public List<Item> getWithDescription(String description) {
        return get(() -> dao().findByDescription(description));
    }

    public Item getWithUrl(String url) {
        return get(() -> dao().findByUrl(url));
    }

    public List<Item> getWithCategory(int categoryId) {
        return get(() -> dao().findByCategory(categoryId));
    }

    @Override
    public List<Item> search(String search) {
        return get(() -> dao().findContaining(search));
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("ID", "Description", "Category", "Image", "Location", "Price", "Title", "Date", "Url");
    }

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(ID, DESCRIPTION, CATEGORY_ID, IMAGE_ID, LOCATION, PRICE, TITLE, DATE, URL);
    }

    @Override
    public List<?> extractDataAsList(Item e) {
        return Arrays.asList(e.getId(), e.getDescription(), e.getCategory().getId(), e.getImage().getId(), e.getLocation(), e.getPrice(), e.getTitle(), e.getDate(), e.getUrl());
    }

    @Override
    public Item createEntity(Map<String, String[]> parameterMap) {
        Item item = new Item();

        item.setId(Integer.parseInt(parameterMap.get(ID)[0]));

        item.setDescription(parameterMap.get(DESCRIPTION)[0]);

        item.setLocation(parameterMap.get(LOCATION)[0]);

        if (parameterMap.get(PRICE)[0] == null) {
            item.setPrice(null);
        }

        String price = parameterMap.get(PRICE)[0];
        price = price.replace("$", "");
        price = price.replace(",", "");

        try {
            item.setPrice(new BigDecimal(price));

        } catch (NumberFormatException e) {

        }

        item.setTitle(parameterMap.get(TITLE)[0]);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            item.setDate(format.parse(parameterMap.get(DATE)[0]));
        } catch (ParseException ex) {
            item.setDate(new Date());
        }

        item.setUrl(parameterMap.get(URL)[0]);

        return item;
    }

}
