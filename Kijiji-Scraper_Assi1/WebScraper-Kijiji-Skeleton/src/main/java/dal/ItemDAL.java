package dal;

import entity.Item;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mukta
 */
public class ItemDAL extends GenericDAL<Item> {

    public ItemDAL() {
        super(Item.class);
    }

    public ItemDAL(Class<Item> entityClass) {
        super(entityClass);
    }

    public List<Item> findAll() {
        return findResults("Item.findAll", null);
    }

    public Item findById(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return findResult("Item.findById", map);
    }

    public List<Item> findByPrice(BigDecimal price) {
        Map<String, Object> map = new HashMap<>();
        map.put("price", price);
        return findResults("Item.findContaining", map);
    }

    public List<Item> findByTitle(String title) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        return findResults("Item.findContaining", map);
    }

    public List<Item> findByDate(String date) {
        Map<String, Object> map = new HashMap<>();
        map.put("date", date);
        return findResults("Item.findContaining", map);
    }

    public List<Item> findByLocation(String location) {
        Map<String, Object> map = new HashMap<>();
        map.put("location", location);
        return findResults("Item.findContaining", map);
    }

    public List<Item> findByDescription(String description) {
        Map<String, Object> map = new HashMap<>();
        map.put("description", description);
        return findResults("Item.findContaining", map);
    }

    public Item findByUrl(String url) {
        Map<String, Object> map = new HashMap<>();
        map.put("url", url);
        return findResult("Item.findByUrl", map);
    }

    public List<Item> findByCategory(int categoryId) {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryid", categoryId);
        return findResults("Item.findByCategory", map);
    }

    public List<Item> findContaining(String search) {
        Map<String, Object> map = new HashMap<>();
        map.put("search", search);
        return findResults("Item.findContaining", map);
    }

}
