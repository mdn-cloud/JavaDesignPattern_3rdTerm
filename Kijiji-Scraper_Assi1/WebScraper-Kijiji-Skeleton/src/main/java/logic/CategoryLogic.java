package logic;

import common.ValidationException;
import dal.CategoryDAL;
import entity.Category;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mukta
 */
public class CategoryLogic extends GenericLogic<Category, CategoryDAL> {

    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String ID = "id";

    public CategoryLogic() {
        super(new CategoryDAL());
    }

    @Override
    public List<Category> getAll() {
        return get(() -> dao().findAll());
    }

    @Override
    public Category getWithId(int id) {
        return get(() -> dao().findById(id));
    }

    public Category getWithUrl(String url) {
        return get(() -> dao().findByUrl(url));
    }

    public Category getWithTitle(String title) {
        return get(() -> dao().findByTitle(title));
    }

    @Override
    public List<Category> search(String search) {
        return get(() -> dao().findContaining(search));
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("ID", "Title", "Url");
    }

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(ID, TITLE, URL);
    }

    @Override
    public List<?> extractDataAsList(Category e) {
        return Arrays.asList(e.getId(), e.getTitle(), e.getUrl());
    }

    @Override
    public Category createEntity(Map<String, String[]> parameterMap) {
        Category category = new Category();
        if (parameterMap.containsKey(ID)) {
            category.setId(Integer.parseInt(parameterMap.get(ID)[0]));
        }
        if (parameterMap.get(TITLE)[0] == null || parameterMap.get(TITLE)[0].isEmpty()) {
            throw new ValidationException("Please enter TITLE.");
        }
        category.setTitle(parameterMap.get(TITLE)[0]);

        if (parameterMap.get(URL)[0] == null || parameterMap.get(URL)[0].isEmpty()) {
            throw new ValidationException("Please enter URL.");
        }
        category.setUrl(parameterMap.get(URL)[0]);
        return category;
    }

}
