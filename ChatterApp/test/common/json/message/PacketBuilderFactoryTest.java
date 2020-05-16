package common.json.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


public class PacketBuilderFactoryTest {
	
	@Test
	void testCreateFactoryStringBadName() {
		
		assertThrows(IllegalArgumentException.class, ()-> PacketBuilderFectory.createFactory("5456"));
	}
	
	@Test
	void testCreateFactoryStringNull() {
		
		assertThrows(IllegalArgumentException.class, ()-> PacketBuilderFectory.createFactory((String)null));

	}
	@Test
	void testCreateFactoryStringAck() {
		AckBuilder aBuilder = new AckBuilder();
		PacketBuilderFectory.createFactory("AckBuilder");
		assertEquals(aBuilder.getClass(), PacketBuilderFectory.createFactory("AckBuilder").getClass());
	}
	
	@Test
	void testCreateFactoryStringMessage() {
		MessageBuilder mBuilder = new MessageBuilder();
		PacketBuilderFectory.createFactory("MessageBuilder");
		assertEquals(mBuilder.getClass(), PacketBuilderFectory.createFactory("MessageBuilder").getClass());
	}
	
	@Test
	void testCreateFactoryClassOfBadClass() {
		assertThrows(IllegalArgumentException.class, ()-> PacketBuilderFectory.createFactory(String.class));
	}
	
	@Test
	void testCreateFactoryClassOfNull() {
		assertThrows(IllegalArgumentException.class, ()-> PacketBuilderFectory.createFactory((String)null));

	}
	
	@Test
	void testCreateFactoryClassOfAck() {
		AckBuilder aBuilder = new AckBuilder();
		PacketBuilderFectory.createFactory(AckBuilder.class);
		assertEquals(aBuilder.getClass(), PacketBuilderFectory.createFactory(AckBuilder.class).getClass());
	}
	
	@Test
	void testCreateFactoryClassOfMessage() {
		MessageBuilder mBuilder = new MessageBuilder();
		PacketBuilderFectory.createFactory(MessageBuilder.class);
		assertEquals(mBuilder.getClass(), PacketBuilderFectory.createFactory(MessageBuilder.class).getClass());
	}
}

