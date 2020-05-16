package test.linkedlist;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import linkedlist.CSTLinkedList;
import linkedlist.CSTList;

class CSTListTest {
	private static CSTList<String> list;

	@BeforeEach
	void init() {
		List<String> arrayList = Arrays.asList("ok", "hello", "test");
		list = new CSTLinkedList<String>(arrayList);
	}

	@Test
	void testAddFirst() {
		assertEquals("ok", list.getFirst());	
		list.addFirst("me");
		assertEquals("me", list.getFirst());		
	}

	@Test
	void testAddLast(){
		assertEquals("test", list.getLast());	
		list.addLast("me");
		assertEquals("me", list.getLast());
	}

	@Test
	void testRemoveFirst(){
		assertEquals("ok", list.getFirst());	
		list.removeFirst();
		assertEquals("hello", list.getFirst());
	}

	@Test
	void testRemoveLast(){
		assertEquals("test", list.getLast());	
		list.removeLast();
		assertEquals("hello", list.getLast());
	}

	@Test
	void testIsEmpty(){
		assertFalse(list.isEmpty());
		list.removeFirst();
		list.removeFirst();
		list.removeFirst();
		assertTrue(list.isEmpty());
	}

	@Test
	void testSize(){
		assertEquals(3, list.size());
		list.addLast("new");
		assertEquals(4, list.size());
		list.removeFirst();
		list.removeFirst();
		assertEquals(2, list.size());
	}
	@Test
	void testClear(){
		assertFalse(list.isEmpty());
		list.clear();
		assertTrue(list.isEmpty());
	}

}
