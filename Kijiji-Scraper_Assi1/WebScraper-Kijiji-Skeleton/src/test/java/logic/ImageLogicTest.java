package logic;

import common.TomcatStartUp;
import entity.Image;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static logic.CategoryLogic.URL;
import static logic.ImageLogic.NAME;
import static logic.ImageLogic.PATH;

/**
 *
 * @author Mukta
 */
public class ImageLogicTest {
    private static Tomcat tomcat;
    private ImageLogic logic;
    
    @BeforeAll
    final static void setUpBeforeClass() throws Exception {
        TomcatStartUp.createTomcat();
    }
    @AfterAll
    final static void tearDownAfterClass() throws Exception {
        TomcatStartUp.stopAndDestroyTomcat();
    }
    private Map<String, String[]> sampleMap;

    @BeforeEach
    final void setUp() throws Exception {
        logic = new ImageLogic();
        sampleMap = new HashMap<>();
        sampleMap.put(ImageLogic.PATH, new String[]{"Junit5_Test"});
        sampleMap.put(ImageLogic.NAME, new String[]{"junittest"});
        sampleMap.put(ImageLogic.URL, new String[]{"junit-5"});

    }

    @AfterEach
    final void tearDown() throws Exception {
    }

    @Test
    final void testGetAll() {
        List<Image> list = logic.getAll();
        int originalSize = list.size();
        Image testImage = logic.createEntity(sampleMap);
        logic.add(testImage);
        list = logic.getAll();
        assertEquals(originalSize + 1, list.size());
        logic.delete(testImage);
        list = logic.getAll();
        assertEquals(originalSize, list.size());
    }

    @Test
    final void testGetWithId() {
        List<Image> list = logic.getAll();
        Image testImage = list.get(0);
        Image returnedImage = logic.getWithId(testImage.getId());
        if (returnedImage != null) {
            assertEquals(testImage.getId(), returnedImage.getId());
            assertEquals(testImage.getPath(), returnedImage.getPath());
            assertEquals(testImage.getName(), returnedImage.getName());
            assertEquals(testImage.getUrl(), returnedImage.getUrl());
        }
        else {
            Assert.assertNull(returnedImage);
        }
    }

    @Test
    final void testGetWithUrl() {
        List<Image> list = logic.getAll();
        Image testImage = list.get(0);
        List<Image> returnedList = logic.getWithUrl(testImage.getUrl());
        if (returnedList.size() > 0) {
            for (int i = 0; i < returnedList.size(); i++) {
              assertEquals(testImage.getUrl(), returnedList.get(i).getUrl());
            }
            
        } else {
            assertEquals(returnedList.size(), 0);
        }
    }

    @Test
    final void testGetWithPath() {
        List<Image> list = logic.getAll();
        Image testImage = list.get(0);
        Image returnedImage = logic.getWithPath(testImage.getPath());
        assertEquals(testImage.getId(), returnedImage.getId());
        assertEquals(testImage.getPath(), returnedImage.getPath());
        assertEquals(testImage.getName(), returnedImage.getName());
        assertEquals(testImage.getUrl(), returnedImage.getUrl());
    }

    @Test
    final void testGetWIthName() {
        List<Image> list = logic.getAll();
        Image testImage = list.get(0);
        List<Image> returnedList = logic.getWithName(testImage.getName());
        if (returnedList.size()>0) {
            for (int i = 0; i<returnedList.size(); i++) {
                assertEquals(testImage.getName(), returnedList.get(i).getName());
            }
            
        } else {
            assertEquals(returnedList.size(), 0);
        }
    }

    @Test
    final void testSearch() {
        Image testImage = logic.createEntity(sampleMap);
        logic.add(testImage);
        List<Image> returnedList = logic.search(sampleMap.get(PATH)[0]);
        for (int i = 0; i < returnedList.size(); i++) {
           assertEquals(testImage.getPath(), returnedList.get(i).getPath());
        }
        logic.delete(testImage);
    }

    @Test
    final void testCreateEntity() {
        if (sampleMap.containsKey(PATH) && sampleMap.containsKey(NAME) && sampleMap.containsKey(URL)) {
            Image testImage = logic.createEntity(sampleMap);
            assertEquals(sampleMap.get(PATH)[0], testImage.getPath());
            assertEquals(sampleMap.get(NAME)[0], testImage.getName());
            assertEquals(sampleMap.get(URL)[0], testImage.getUrl());
        } else {
            Assertions.assertThrows(NullPointerException.class, () -> logic.createEntity(sampleMap));
        }
    }
    
    @Test
    final void testGetColumnNames() {
        List<String> list = logic.getColumnNames();
        assertEquals(Arrays.asList("ID", "Path", "Name", "Url"), list);
    }

    @Test
    final void testGetColumnCodes() {
        List<String> list = logic.getColumnCodes();
        assertEquals(Arrays.asList(ImageLogic.ID, ImageLogic.PATH, ImageLogic.NAME, ImageLogic.URL), list);
    }  
    
    @Test
    final void testExtractDataAsList() {
        Image testImage = logic.createEntity(sampleMap);
        List<?> exdList = logic.extractDataAsList(testImage);
        assertEquals(exdList.get(0), testImage.getId());
        assertEquals(exdList.get(1), testImage.getPath());
        assertEquals(exdList.get(2), testImage.getName());
        assertEquals(exdList.get(3), testImage.getUrl());
       
    }
    
}
