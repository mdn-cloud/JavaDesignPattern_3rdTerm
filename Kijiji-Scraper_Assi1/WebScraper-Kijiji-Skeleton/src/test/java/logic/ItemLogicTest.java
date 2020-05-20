package logic;

import common.TomcatStartUp;
import entity.Item;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Mukta
 */
public class ItemLogicTest {

    private ItemLogic logic;
    private Item item;
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
        logic = new ItemLogic();      
        sampleMap = new HashMap<>();
        sampleMap.put(ItemLogic.DESCRIPTION, new String[]{"Junit 5 Test"});
        sampleMap.put(ItemLogic.LOCATION, new String[]{"junit"});
        sampleMap.put(ItemLogic.PRICE, new String[]{"junit5"});
        sampleMap.put(ItemLogic.TITLE, new String[]{"junit5"});
        sampleMap.put(ItemLogic.DATE, new String[]{"junit5"});
        sampleMap.put(ItemLogic.URL, new String[]{"junit5"});
        sampleMap.put(ItemLogic.ID, new String[]{"5"});
    }

    @AfterEach
    final void tearDown() throws Exception {
    }

    @Test
    final void testGetAll() {
        List<Item> list = logic.getAll();
        int originalSize = list.size();
        Item testItem = logic.createEntity(sampleMap);
        testItem.setImage(new ImageLogic().getAll().get(0));
        testItem.setCategory(new CategoryLogic().getAll().get(0));
        logic.add(testItem);
        list = logic.getAll();
        assertEquals(originalSize + 1, list.size());
        logic.delete(testItem);
        list = logic.getAll();
        assertEquals(originalSize, list.size());
    }
    
    @Test
    final void testGetWithId() {
        List<Item> list = logic.getAll();
        Item testItem = list.get(0);
        Item returnedItem = logic.getWithId(testItem.getId());
        if (returnedItem != null) {
            assertEquals(testItem.getId(), testItem.getId());
            assertEquals(testItem.getDescription(), testItem.getDescription());
            assertEquals(testItem.getCategory(), testItem.getCategory());
            assertEquals(testItem.getImage(), testItem.getImage());
            assertEquals(testItem.getLocation(), testItem.getLocation());
            assertEquals(testItem.getPrice(), testItem.getPrice());
            assertEquals(testItem.getTitle(), testItem.getTitle());
            assertEquals(testItem.getDate(), testItem.getDate());
            assertEquals(testItem.getUrl(), testItem.getUrl());
        }else {
            Assert.assertNull(returnedItem);
        }
    }

    @Test
    final void testGetWithPrice() {
        BigDecimal bd1 = new BigDecimal("25");
        BigDecimal bd2 = new BigDecimal("25.00");
        assertEquals( bd1.compareTo( bd2), 0);    
    }
    
    @Test
    final void testGetWithTitle() {
        List<Item> list = logic.getAll();
        Item testItem = list.get(0);
        List<Item> returnedList = logic.getWithTitle(testItem.getTitle());
            for (int m = 0; m < returnedList.size(); m++) {
                assertEquals(testItem.getTitle(), returnedList.get(m).getTitle());
            }
    }

    @Test
    final void testGetWithDate() {
        List<Item> list = logic.getAll();
        Item testItem = list.get(0);
        List<Item> returnedList;
        returnedList = logic.getWithDate(testItem.getDate().toString());
               if (returnedList.size() > 0) {
            for (int i = 0; i < returnedList.size(); i++) {
                assertEquals(testItem.getTitle(), returnedList.get(i).getDate());
            }
        } else {
            assertEquals(returnedList.size(), 0);
        }
    }
    @Test
    final void testGetWithLocation() {
        List<Item> list = logic.getAll();
        Item testItem = list.get(0);
        List<Item> returnedList = logic.getWithLocation(testItem.getLocation());
        if (returnedList.size() > 0) {
            for (int k = 0; k < returnedList.size(); k++) {
                assertEquals(testItem.getLocation(), returnedList.get(k).getLocation());
            }
        } else {
            assertEquals(returnedList.size(), 0);
        }
    }

    @Test
    final void testGetWithDescription() {
        List<Item> list = logic.getAll();
        Item testItem = list.get(0);
        List<Item> returnedList = logic.getWithDescription(testItem.getDescription());

        if (returnedList.size() > 0) {
            for (int j = 0; j < returnedList.size(); j++) {
                assertEquals(testItem.getDescription(), returnedList.get(j).getDescription());
            }
        } else {
            assertEquals(returnedList.size(), 0);
        }
    }

    @Test
    final void testGetWithUrl() {
        List<Item> list = logic.getAll();
        Item testItem = list.get(0);
        Item returnedItem = logic.getWithId(testItem.getId());
        assertEquals(testItem.getId(), returnedItem.getId());
        assertEquals(testItem.getDescription(), returnedItem.getDescription());
        assertEquals(testItem.getCategory(), returnedItem.getCategory());
        assertEquals(testItem.getImage(), returnedItem.getImage());
        assertEquals(testItem.getLocation(), returnedItem.getLocation());
        assertEquals(testItem.getPrice(), returnedItem.getPrice());
        assertEquals(testItem.getTitle(), returnedItem.getTitle());
        assertEquals(testItem.getDate(), returnedItem.getDate());
        assertEquals(testItem.getUrl(), returnedItem.getUrl());
    }

    @Test
    final void testGetWithCategory() {
        List<Item> list = logic.getAll();
        Item testItem = list.get(0);
        List<Item> returnedList = logic.getWithCategory(testItem.getCategory().getId());
        if (returnedList.size() > 0) {
            for (int j = 0; j < returnedList.size(); j++) {
                assertEquals(testItem.getCategory(), returnedList.get(j).getCategory());
            }
        } else {
            assertEquals(returnedList.size(), 0);
        }
    }
    
    @Test
    final void testSearch() {
        List<Item> list = logic.getAll();
        Item testItem = list.get(list.size()-1);
        List<Item> returnedList = logic.search(testItem.getTitle());
        for (int i = 0; i < returnedList.size(); i++) {
            assertEquals(testItem.getTitle(), returnedList.get(i).getTitle());
        }
    }
    
    @Test
    final void testCreateEntity() {
        if (sampleMap.containsKey(ItemLogic.DESCRIPTION) && sampleMap.containsKey(ItemLogic.LOCATION) && sampleMap.containsKey(ItemLogic.PRICE)
                && sampleMap.containsKey(ItemLogic.TITLE) && sampleMap.containsKey(ItemLogic.DATE) && sampleMap.containsKey(ItemLogic.URL)) {
            item = logic.createEntity(sampleMap);

            assertEquals(sampleMap.get(ItemLogic.DESCRIPTION)[0], item.getDescription());
            assertEquals(sampleMap.get(ItemLogic.LOCATION)[0], item.getLocation());
            assertEquals(sampleMap.get(ItemLogic.TITLE)[0], item.getTitle());
            assertEquals(sampleMap.get(ItemLogic.URL)[0], item.getUrl());

        } else {
            Assertions.assertThrows(NullPointerException.class, () -> logic.createEntity(sampleMap));
        }
    }

    @Test
    final void testGetColumnNames() {
        List<String> list = logic.getColumnNames();
        assertEquals(Arrays.asList("ID", "Description", "Category", "Image", "Location", "Price", "Title", "Date", "Url"), list);
    }

    @Test
    final void testGetColumnCodes() {
        List<String> list = logic.getColumnCodes();
        assertEquals(Arrays.asList(ItemLogic.ID, ItemLogic.DESCRIPTION, ItemLogic.CATEGORY_ID, ItemLogic.IMAGE_ID, ItemLogic.LOCATION, ItemLogic.PRICE, ItemLogic.TITLE, ItemLogic.DATE, ItemLogic.URL), list);
    }

    @Test
    final void testExtractDataAsList() {
        List<Item> list1 = logic.getAll();
        Item e = list1.get(0);
        List<?> list = logic.extractDataAsList(e);
        assertEquals(e.getId(), list.get(0));
        assertEquals(e.getDescription(), list.get(1));
        assertEquals(e.getCategory().getId(), list.get(2));
        assertEquals(e.getImage().getId(), list.get(3));
        assertEquals(e.getLocation(), list.get(4));
        assertEquals(e.getPrice(), list.get(5));
        assertEquals(e.getTitle(), list.get(6));
        assertEquals(e.getDate(), list.get(7));
        assertEquals(e.getUrl(), list.get(8));
    }

}
