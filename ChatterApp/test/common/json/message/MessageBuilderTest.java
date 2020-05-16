package common.json.message;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

class MessageBuilderTest {

	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
	private ObjectMapper objectMapper;
	private MessageBuilder builder;
	private LocalDateTime now;
	private String user;
	private String message;

	@BeforeEach	
	void setup() {
		builder= PacketBuilderFectory.createFactory(MessageBuilder.class);
		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		user = "Mukta";
		message = "this is a message";
		now = LocalDateTime.of(2020, 03, 25, 12, 55);
	}

	@Test
	void testBuildWithStringDate() {

		String s= "11/12/20 1:22 PM";
		MessagePacket mp = new MessagePacket(user, LocalDateTime.parse(s, DATE_FORMAT), message);
		MessagePacket mp1 = builder.setUser(user).setDate(s).setMessage(message).build();
		assertEquals (mp, mp1);
	}

	@Test
	void testBuild() {
		MessagePacket ap = new MessagePacket("Mukta", now, "My message");
		MessagePacket ap1 = builder.setUser("Mukta").setDate(now).setMessage("My message").build();
		assertEquals(ap, ap1);
	}

	@Test
	void testBuildEmptyUser() {
		assertThrows(IllegalArgumentException.class, ()-> builder.setUser("").setDate(LocalDateTime.of(2020, 03, 25, 12, 55)).setMessage("This Is").build());
	}

	@Test
	void testBuildNullUser(){
		assertThrows(IllegalArgumentException.class, ()-> builder.setUser(null).setDate(LocalDateTime.of(2020, 03, 25, 12, 55)).setMessage("My Message").build());
	}


	@Test
	void testBuildEmptyMessage() {

		assertThrows(IllegalArgumentException.class, ()-> builder.setUser("Mukta").setMessage("").setDate(LocalDateTime.of(2020, 03, 25, 12, 55)).build());
	}

	@Test
	void testBuildNullMessage() {
		assertThrows(IllegalArgumentException.class, ()-> builder.setUser("Mukta").setDate(LocalDateTime.of(2020, 03, 25, 12, 55)).setMessage(null).build());
	}

	@Test
	void testBuildNullDate() {
		assertThrows(IllegalArgumentException.class, ()-> builder.setUser("Mukta").setDate((LocalDateTime)null).setMessage("this is a message").build());
	}

	@Test
	void testBuildNullDateString() {
		assertThrows(NullPointerException.class, ()-> builder.setUser("Mukta").setDate((String)null).setMessage("this is a message").build());

	}

	@Test
	void testObjectToJson() throws JsonMappingException, JsonProcessingException {
		MessagePacket ap1 = builder.setUser("Mukta").setDate(LocalDateTime.of(2020, 03, 25, 12, 55)).setMessage("My message").build();
		String j = objectMapper.writeValueAsString(ap1);
		MessagePacket ap2 = objectMapper.readValue(j, MessagePacket.class);
		assertEquals(ap1, ap2);
	}

	@Test
	void testJsonToObject() throws JsonProcessingException {
		ObjectNode node = objectMapper.createObjectNode();

		node.put( "date", LocalDateTime.of(2020, 03, 25, 12, 55).toString());
		node.put( "user", "Mukta");
		node.put( "message", "this is a message");
		MessagePacket packet = objectMapper.treeToValue(node, MessagePacket.class);
		MessagePacket ap = new MessagePacket( user, now, message);
		assertEquals(packet, ap);
	}

}
