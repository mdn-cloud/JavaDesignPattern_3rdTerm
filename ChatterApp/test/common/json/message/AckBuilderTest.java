package common.json.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AckBuilderTest {

	private ObjectMapper objectMapper;
	private AckBuilder builder;
	private String user;
	private String error;
	private int ack;
	
	@BeforeEach	
	void setup() {
		builder= PacketBuilderFectory.createFactory(AckBuilder.class);
		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
	}
	
	@Test
	void testBuild() {
		AckPacket ap = new AckPacket("Mukta", null, 5);
		AckPacket ap1 = builder.setUser("Mukta").setError(null).setAck(5).build();
		assertEquals(ap, ap1);
	}
	
	@Test
	void testBuildNoAck() {
		assertThrows(IllegalArgumentException.class, ()-> builder.setUser("Mukta").setError(null).build());
	}
	
	@Test
	void testBuildEmptyUser() {
		assertThrows(IllegalArgumentException.class, ()-> builder.setUser("").setError(null).setAck(5).build());

	}
	
	@Test
	void testBuildNullUser() {
		assertThrows(IllegalArgumentException.class, ()-> builder.setUser(null).setError(null).setAck(5).build());

	}
	
	@Test
	void testBuildNullError() {
		
			builder.setUser("Mukta");
			builder.setError(null);
			builder.setAck(5);
			builder.build();
			
			AckPacket ap = builder.build();
			assertEquals("Mukta", ap.getUsername());
			assertEquals(null, ap.getError());
			assertEquals(5, ap.getAck());
	}
	
	@Test
	void testBuildEmptyError() {
		
		assertThrows(IllegalArgumentException.class, ()-> builder.setUser("Mukta").setError("").setAck(5).build());

	}
	
	@Test
	void testObjectToJson() throws JsonMappingException, JsonProcessingException {
		AckPacket ap1 = builder.setUser("Mukta").setError(null).setAck(5).build();
		String j = objectMapper.writeValueAsString(ap1);
		AckPacket ap2 = objectMapper.readValue(j, AckPacket.class);
		assertEquals(ap1, ap2);
	}
	
	@Test
	void testObjectToJsonNull() throws JsonMappingException, JsonProcessingException {
		AckPacket ap1 = builder.setUser("Mukta").setError(null).setAck(5).build();
		String j = objectMapper.writeValueAsString(ap1);
		AckPacket ap2 = objectMapper.readValue(j, AckPacket.class);
		assertEquals(ap1, ap2);
	}
	
	@Test
	void testJsonToObject() throws JsonProcessingException {
		ObjectNode node = objectMapper.createObjectNode();
		node.put( "user", "Mukta");
		node.put( "error", (String)error);
		node.put( "ack", 5);
		AckPacket packet = objectMapper.treeToValue(node, AckPacket.class);
		AckPacket ap = new AckPacket("Mukta", null, 5);
		assertEquals(packet, ap);
	}
}
