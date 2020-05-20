package logic;

import common.TomcatStartUp;
import entity.Category;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Mukta
 */
public class CategoryLogicTest {
    
private CategoryLogic logic;
    private Map<String, String[]> sampleMap;

    @BeforeAll
    final static void setUpBeforeClass() throws Exception {
        TomcatStartUp.createTomcat();
    }

    @AfterAll
    final static void tearDownAfterClass() throws Exception {
        TomcatStartUp.stopAndDestroyTomcat();
    }

    @BeforeEach
    final void setUp() throws Exception {
        logic = new CategoryLogic();
        sampleMap = new HashMap<>();
        sampleMap.put(CategoryLogic.TITLE, new String[]{"Junit 5 Test"});
        sampleMap.put(CategoryLogic.URL, new String[]{"junit"});
        
    }

    @AfterEach
    final void tearDown() throws Exception {
    }

    @Test
    final void testGetAll() {
        List<Category> list = logic.getAll();
        int originalSize = list.size();
        assertEquals(originalSize, logic.getAll().size());
        assertEquals(list.get(originalSize-1), logic.getAll().get(list.size()-1));
    }

    @Test
    final void testGetWithId() {
        List<Category> list = logic.getAll();
        Category testCategory = list.get(0);
        Category returnedCategory = logic.getWithId(testCategory.getId());
        assertEquals(testCategory.getId(), returnedCategory.getId());
        assertEquals(testCategory.getTitle(), returnedCategory.getTitle());
        assertEquals(testCategory.getUrl(), returnedCategory.getUrl());
    }

    @Test
    final void testGetWIthUrl() {
        List<Category> list = logic.getAll();
        Category testCategory = list.get(0);
        Category returnedCategory = logic.getWithUrl(testCategory.getUrl());
        assertEquals(testCategory.getId(), returnedCategory.getId());
        assertEquals(testCategory.getTitle(), returnedCategory.getTitle());
        assertEquals(testCategory.getUrl(), returnedCategory.getUrl());
    }

    @Test
    final void testGetWIthTitle() {
        List<Category> list = logic.getAll();
        Category testCategory = list.get(0);
        Category returnedCategory = logic.getWithTitle(testCategory.getTitle());
        assertEquals(testCategory.getId(), returnedCategory.getId());
        assertEquals(testCategory.getTitle(), returnedCategory.getTitle());
        assertEquals(testCategory.getUrl(), returnedCategory.getUrl());

    }

    @Test
    final void testSearch() {
        
        Category testCategory = logic.getAll().get(0);
        List<Category> list = logic.search(testCategory.getTitle());
            for(int i=0; i<list.size(); i++){
              assertEquals(list.get(i).getTitle(), testCategory.getTitle());
        }
    }
    
    @Test
    final void testCreateEntity() {
        Category testCategory = logic.createEntity(sampleMap);
        assertEquals(testCategory.getTitle(), "Junit 5 Test");
        assertEquals(testCategory.getUrl(), "junit");
    }
    
    @Test
    final void testGetColumnNames() {
        List<String> cnList = logic.getColumnNames();
        List<?> hardCodedList = Arrays.asList("ID", "Title", "Url");
        assertIterableEquals(cnList, hardCodedList);
    }
    
    @Test
    final void testGetColumnCodes() {
        List<String> ccList = logic.getColumnCodes();
        List<?> hardCodedList = Arrays.asList(CategoryLogic.ID, CategoryLogic.TITLE, CategoryLogic.URL);
        assertIterableEquals(ccList, hardCodedList);
    }
    
    @Test
    final void testExtractDataAsList() {
        Category testCategory = logic.createEntity(sampleMap);
        List<?> ccList = logic.extractDataAsList(testCategory);
        assertEquals(ccList.get(1), testCategory.getTitle());
        assertEquals(ccList.get(2), testCategory.getUrl());
    }
}
